package Game4_update;

import java.util.ArrayList;

public class Snake {
    private int x;
    private int y;
    private MoveStrategy moveStrategy;

    public Snake(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public void move() {
        if (moveStrategy != null) {
            Tuple head = new Tuple(x, y); 
            ArrayList<Tuple> positions = new ArrayList<>();
            positions.add(head);
            moveStrategy.move(head, positions); 
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void printPosition() {
        System.out.println("Snake position: (" + x + ", " + y + ")");
    }
}
