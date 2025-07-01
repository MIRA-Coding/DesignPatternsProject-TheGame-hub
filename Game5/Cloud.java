package Game5;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Cloud {
    private int size;       // Diameter of the cloud parts
    private double x;       // X-coordinate of the cloud (moves horizontally)
    private int y;          // Y-coordinate of the cloud (static vertical position)
    private double speed;   // Speed of cloud movement
    private Random rand = new Random();  // For generating random values

    // Constructor initializes the cloud with random position and size
    public Cloud() {
        resetCloud();                         // Set size, speed, and y-position
        x = rand.nextInt(GamePanel.WIDTH);    // Start at a random x within screen
    }

    // Main method to draw the cloud
    public Graphics2D draw(Graphics2D g) {
        update();  // Update cloud's position

        g.setColor(new Color(230, 255, 255));  // Light blue/white cloud color

        // Draw three overlapping ovals to form the cloud shape
        g.fillOval((int) x, y, size, size);  // First puff
        g.fillOval((int) x + (size / 2) + (size / 6), y - (size / 3), size, size); // Top puff
        g.fillOval((int) x + size + (size / 3), y, size, size); // Right puff

        return g;
    }

    // Update method for moving the cloud
    private void update() {
        x += speed;  // Move cloud horizontally

        // If the cloud moves beyond the screen, reset it
        if (x > GamePanel.WIDTH) {
            resetCloud();
        }
    }

    // Reset the cloud to a random position off-screen and with random size/speed
    private void resetCloud() {
        y = rand.nextInt(400);                   // Random height (0 to 399)
        size = rand.nextInt(30) + 30;            // Random size between 30 and 60
        x = -((size * 2.5) + rand.nextInt(400)); // Start off-screen to the left
        speed = Math.random() * 0.5 + 0.25;       // Speed between 0.25 and 0.75
    }
}
