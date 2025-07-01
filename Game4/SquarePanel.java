package Game4;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a single square cell in the game grid.
 * It can change its background color to reflect game state.
 */
public class SquarePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor: Initializes the panel with the given background color.
     *
     * @param initialColor The initial background color of the panel.
     */
    public SquarePanel(Color initialColor) {
        setBackground(initialColor);
    }

    /**
     * Changes the background color of the panel and repaints it.
     *
     * @param newColor The new background color to apply.
     */
    public void ChangeColor(Color newColor) {
        setBackground(newColor);
        repaint();
    }
}
