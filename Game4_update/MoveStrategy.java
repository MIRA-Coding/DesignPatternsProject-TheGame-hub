package Game4_update;

import java.util.ArrayList;

public interface MoveStrategy {
    void move(Tuple head, ArrayList<Tuple> positions);
}