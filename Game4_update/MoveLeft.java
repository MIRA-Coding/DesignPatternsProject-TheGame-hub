package Game4_update;

import java.util.ArrayList;

public class MoveLeft implements MoveStrategy {
    public void move(Tuple head, ArrayList<Tuple> positions) {
        if (head.x - 1 < 0)
            head.ChangeData(19, head.y);
        else
            head.ChangeData((head.x - 1) % 20, head.y);
        positions.add(new Tuple(head.x, head.y));
    }
}