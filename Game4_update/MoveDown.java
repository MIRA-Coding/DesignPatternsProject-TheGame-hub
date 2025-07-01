package Game4_update;

import java.util.ArrayList;

public class MoveDown implements MoveStrategy {
    public void move(Tuple head, ArrayList<Tuple> positions) {
        head.ChangeData(head.x, (head.y + 1) % 20);
        positions.add(new Tuple(head.x, head.y));
    }
}