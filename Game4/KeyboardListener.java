package Game4;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardListener extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_RIGHT: // → Right
                if (ThreadsController.directionSnake != 2) {
                    ThreadsController.directionSnake = 1;
                }
                break;

            case KeyEvent.VK_UP: // ↑ Up
                if (ThreadsController.directionSnake != 4) {
                    ThreadsController.directionSnake = 3;
                }
                break;

            case KeyEvent.VK_LEFT: // ← Left
                if (ThreadsController.directionSnake != 1) {
                    ThreadsController.directionSnake = 2;
                }
                break;

            case KeyEvent.VK_DOWN: // ↓ Down
                if (ThreadsController.directionSnake != 3) {
                    ThreadsController.directionSnake = 4;
                }
                break;

            default:
                break;
        }
    }
}
