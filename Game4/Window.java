package Game4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Main game window. Initializes the grid and starts the game.
 */
public class Window extends JFrame {

    private static final long serialVersionUID = -2542001418764869760L;

    public static ArrayList<ArrayList<DataOfSquare>> Grid;
    public static final int width = 20;
    public static final int height = 20;

    public Window() {

        // Initialize the 2D grid
        Grid = new ArrayList<>();
        for (int row = 0; row < width; row++) {
            ArrayList<DataOfSquare> rowData = new ArrayList<>();
            for (int col = 0; col < height; col++) {
                rowData.add(new DataOfSquare(2)); // 2 = default color (white)
            }
            Grid.add(rowData);
        }

        // Set up the layout of the window (20x20 grid)
        getContentPane().setLayout(new GridLayout(width, height, 0, 0));

        // Add each square to the window
        for (int row = 0; row < width; row++) {
            for (int col = 0; col < height; col++) {
                getContentPane().add(Grid.get(row).get(col).square);
            }
        }

        // Set initial snake position
        Tuple initialPosition = new Tuple(10, 10);

        // Create and start the game controller
        ThreadsController controller = new ThreadsController(initialPosition);
        controller.start();

        // Attach keyboard listener
        this.addKeyListener((KeyListener) new KeyboardListener());


    }
}
