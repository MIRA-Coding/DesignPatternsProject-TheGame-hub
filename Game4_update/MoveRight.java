package Game4_update;

import java.util.ArrayList;

public class MoveRight implements MoveStrategy {
    public void move(Tuple head, ArrayList<Tuple> positions) {
        head.ChangeData((head.x + 1) % 20, head.y);
        positions.add(new Tuple(head.x, head.y));
    }
}