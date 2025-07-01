package Game4;

import java.util.ArrayList;

/**
 * Controls all game logic. Most important class in the Snake game project.
 */
public class ThreadsController extends Thread {

    ArrayList<ArrayList<DataOfSquare>> Squares = new ArrayList<>();
    Tuple headSnakePos;
    int sizeSnake = 3;
    long speed = 50;

    public static int directionSnake;

    ArrayList<Tuple> positions = new ArrayList<>();
    Tuple foodPosition;

    // Constructor: initialize game state
    ThreadsController(Tuple positionDepart) {
        Squares = Window.Grid;

        headSnakePos = new Tuple(positionDepart.x, positionDepart.y);
        directionSnake = 1;

        Tuple headPos = new Tuple(headSnakePos.getX(), headSnakePos.getY());
        positions.add(headPos);

        foodPosition = new Tuple(Window.height - 1, Window.width - 1);
        spawnFood(foodPosition);
    }

    @Override
    public void run() {
        while (true) {
            moveInterne(directionSnake);
            checkCollision();
            moveExterne();
            deleteTail();
            pauser();
        }
    }

    // Adds delay between each movement
    private void pauser() {
        try {
            sleep(speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Detects collision with self or food
    private void checkCollision() {
        Tuple currentHead = positions.get(positions.size() - 1);

        // Self-collision detection
        for (int i = 0; i < positions.size() - 1; i++) {
            boolean biteItself = currentHead.getX() == positions.get(i).getX()
                    && currentHead.getY() == positions.get(i).getY();
            if (biteItself) {
                stopTheGame();
            }
        }

        // Eating food
        boolean eatingFood = currentHead.getX() == foodPosition.y && currentHead.getY() == foodPosition.x;
        if (eatingFood) {
            System.out.println("Yummy!");
            sizeSnake += 1;
            foodPosition = getValAleaNotInSnake();
            spawnFood(foodPosition);
        }
    }

    // Infinite pause to simulate game over
    private void stopTheGame() {
        System.out.println("COLLISION!\n");
        while (true) {
            pauser();
        }
    }

    // Renders food in grid
    private void spawnFood(Tuple foodPositionIn) {
        Squares.get(foodPositionIn.x).get(foodPositionIn.y).lightMeUp(1);
    }

    // Finds a random position not occupied by the snake
    private Tuple getValAleaNotInSnake() {
        Tuple p;
        do {
            int ranX = (int) (Math.random() * 20);
            int ranY = (int) (Math.random() * 20);
            p = new Tuple(ranX, ranY);
        } while (isInSnake(p));
        return p;
    }

    private boolean isInSnake(Tuple p) {
        for (Tuple t : positions) {
            if (p.getY() == t.getX() && p.getX() == t.getY()) {
                return true;
            }
        }
        return false;
    }

    // Updates head position based on direction
    // 1:right  2:left  3:up  4:down
    private void moveInterne(int dir) {
        switch (dir) {
            case 1: // Right
                headSnakePos.ChangeData((headSnakePos.x + 1) % 20, headSnakePos.y);
                break;
            case 2: // Left
                int leftX = (headSnakePos.x - 1 < 0) ? 19 : (headSnakePos.x - 1) % 20;
                headSnakePos.ChangeData(leftX, headSnakePos.y);
                break;
            case 3: // Up
                int upY = (headSnakePos.y - 1 < 0) ? 19 : (headSnakePos.y - 1) % 20;
                headSnakePos.ChangeData(headSnakePos.x, upY);
                break;
            case 4: // Down
                headSnakePos.ChangeData(headSnakePos.x, (headSnakePos.y + 1) % 20);
                break;
        }
        positions.add(new Tuple(headSnakePos.x, headSnakePos.y));
    }

    // Marks all current snake positions as active (0)
    private void moveExterne() {
        for (Tuple t : positions) {
            int y = t.getX();
            int x = t.getY();
            Squares.get(x).get(y).lightMeUp(0);
        }
    }

    // Trims snake tail and updates removed squares to white (2)
    private void deleteTail() {
        int remaining = sizeSnake;

        // Visually remove excess tail from GUI
        for (int i = positions.size() - 1; i >= 0; i--) {
            if (remaining == 0) {
                Tuple t = positions.get(i);
                Squares.get(t.y).get(t.x).lightMeUp(2);
            } else {
                remaining--;
            }
        }

        // Actually remove extra data from positions
        remaining = sizeSnake;
        for (int i = positions.size() - 1; i >= 0; i--) {
            if (remaining == 0) {
                positions.remove(i);
            } else {
                remaining--;
            }
        }
    }
}
