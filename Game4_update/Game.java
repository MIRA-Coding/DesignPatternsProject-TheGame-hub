package Game4_update;

public class Game {
    public static void main(String[] args) {
        Snake snake = new Snake(5, 5); // موقع البداية

        // حركة لليمين
        snake.setMoveStrategy(new MoveRight());
        snake.move();
        snake.printPosition(); // (6,5)

        // حركة لأسفل
        snake.setMoveStrategy(new MoveDown());
        snake.move();
        snake.printPosition(); // (6,6)

        // حركة لليسار
        snake.setMoveStrategy(new MoveLeft());
        snake.move();
        snake.printPosition(); // (5,6)

        // حركة لأعلى
        snake.setMoveStrategy(new MoveUp());
        snake.move();
        snake.printPosition(); // (5,5)
    }
}