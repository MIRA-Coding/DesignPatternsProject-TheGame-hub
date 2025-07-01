package Game4_update;

import java.util.ArrayList;

public class ThreadsController extends Thread {
	ArrayList<ArrayList<DataOfSquare>> Squares = new ArrayList<>();
	Tuple headSnakePos;
	int sizeSnake = 3;
	long speed = 50;
	public static int directionSnake;
	ArrayList<Tuple> positions = new ArrayList<>();
	Tuple foodPosition;

	// استراتيجية الحركة
	private MoveStrategy moveStrategy;

	ThreadsController(Tuple startPosition) {
		Squares = Window.Grid;
		headSnakePos = new Tuple(startPosition.x, startPosition.y);
		directionSnake = 1;
		positions.add(new Tuple(headSnakePos.getX(), headSnakePos.getY()));
		foodPosition = new Tuple(Window.height - 1, Window.width - 1);
		spawnFood(foodPosition);
		moveStrategy = new MoveRight(); // البداية نحو اليمين
	}

	public void run() {
		while (true) {
			applyStrategy();
			checkCollision();
			moveExterne();
			deleteTail();
			pauser();
		}
	}

	private void applyStrategy() {
		switch (directionSnake) {
			case 1:
				moveStrategy = new MoveRight();
				break;
			case 2:
				moveStrategy = new MoveLeft();
				break;
			case 3:
				moveStrategy = new MoveUp();
				break;
			case 4:
				moveStrategy = new MoveDown();
				break;
		}
		moveStrategy.move(headSnakePos, positions);
	}

	private void pauser() {
		try {
			sleep(speed);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void checkCollision() {
		Tuple currentPos = positions.get(positions.size() - 1);
		for (int i = 0; i <= positions.size() - 2; i++) {
			boolean biteItself = currentPos.getX() == positions.get(i).getX() && currentPos.getY() == positions.get(i).getY();
			if (biteItself) {
				stopTheGame();
			}
		}

		boolean eatingFood = currentPos.getX() == foodPosition.y && currentPos.getY() == foodPosition.x;
		if (eatingFood) {
			System.out.println("Yummy!");
			sizeSnake++;
			foodPosition = getValAleaNotInSnake();
			spawnFood(foodPosition);
		}
	}

	private void stopTheGame() {
		System.out.println("COLLISION!");
		while (true) {
			pauser();
		}
	}

	private void spawnFood(Tuple pos) {
		Squares.get(pos.x).get(pos.y).lightMeUp(1);
	}

	private Tuple getValAleaNotInSnake() {
		Tuple p;
		int ranX = (int) (Math.random() * 19);
		int ranY = (int) (Math.random() * 19);
		p = new Tuple(ranX, ranY);
		for (int i = 0; i < positions.size(); i++) {
			if (p.getY() == positions.get(i).getX() && p.getX() == positions.get(i).getY()) {
				ranX = (int) (Math.random() * 19);
				ranY = (int) (Math.random() * 19);
				p = new Tuple(ranX, ranY);
				i = 0;
			}
		}
		return p;
	}

	private void moveExterne() {
		for (Tuple t : positions) {
			int y = t.getX();
			int x = t.getY();
			Squares.get(x).get(y).lightMeUp(0);
		}
	}

	private void deleteTail() {
		int cmpt = sizeSnake;
		for (int i = positions.size() - 1; i >= 0; i--) {
			if (cmpt == 0) {
				Tuple t = positions.get(i);
				Squares.get(t.y).get(t.x).lightMeUp(2);
			} else {
				cmpt--;
			}
		}

		cmpt = sizeSnake;
		for (int i = positions.size() - 1; i >= 0; i--) {
			if (cmpt == 0) {
				positions.remove(i);
			} else {
				cmpt--;
			}
		}
	}
}