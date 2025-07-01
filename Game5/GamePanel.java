package Game5;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GamePanel extends JPanel implements Runnable, MouseListener, MouseMotionListener {

    // Panel dimensions
    public static int WIDTH = 1200;
    public static int HEIGHT = 500;

    // Mouse interaction states
    public static boolean dragging = false;
    public static boolean click = false;
    public static int cursorX = 0;
    public static int cursorY = 0;

    // Game loop components
    private Thread thread;
    private boolean running;
    private BufferedImage image;  // Off-screen image buffer
    private Graphics2D g;         // Graphics context for rendering
    private int fps = 60;         // Frames per second

    // Core simulation object
    private Simulation simulation;

    // Constructor: initialize panel and simulation
    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
        addMouseMotionListener(this);
        addMouseListener(this);

        // Build simulation using Builder design pattern
        SimulationBuilderInterface builder = new ConcreteSimulationBuilder();
        SimulationDirector director = new SimulationDirector(builder);
        director.construct(); // Construct simulation components
        simulation = director.getSimulation();
    }

    // Called when the panel is added to the display hierarchy
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            thread.start(); // Start the game loop thread
        }
    }

    // Main game loop
    public void run() {
        running = true;

        // Prepare off-screen buffer
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        long startTime;
        long URDTimeMillis;
        long waitTime;
        long targetTime = 1000 / fps; // Target frame duration in milliseconds

        // Game loop: render and draw every frame
        while (running) {
            startTime = System.nanoTime();

            gameRender();  // Draw everything to the image buffer
            gameDraw();    // Draw buffer to screen

            // Time calculation and frame rate control
            URDTimeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - URDTimeMillis;
            try {
                Thread.sleep(waitTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Rendering method for drawing game elements
    private void gameRender() {
        // Draw sky background
        g.setColor(new Color(197, 234, 243));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw ground
        g.setColor(new Color(28, 232, 119));
        g.fillRect(0, HEIGHT - 50, WIDTH, 50);

        // Draw clouds
        for (Cloud cloud : simulation.clouds) {
            g = cloud.draw(g);
        }

        // Draw cannon with slider values for angle, size, power
        g = simulation.cannon.draw(g,
                simulation.angleSlider.getValue(),
                simulation.sizeSlider.getValue(),
                simulation.powerSlider.getValue());

        // Draw UI sliders
        g = simulation.angleSlider.draw(g);
        g = simulation.sizeSlider.draw(g);
        g = simulation.powerSlider.draw(g);

        // Draw game title and subtitle
        g.setColor(Color.BLACK);
        g.setFont(new Font("Calibri", Font.BOLD, 72));
        g.drawString("Cannon Simulator", 460, 85);
        g.setFont(new Font("Helvetica", Font.BOLD, 24));
        g.drawString("A totally accurate simulation of using a cannon!", 453, 115);
    }

    // Display the rendered image to the screen
    private void gameDraw() {
        Graphics g2 = this.getGraphics();       // Get graphics context for panel
        g2.drawImage(image, 0, 0, null);        // Draw the buffered image
        g2.dispose();                           // Dispose to free resources
    }

    // Mouse click handler
    @Override
    public void mouseClicked(MouseEvent e) {
        click = true;  // Register a click (used by Cannon)
    }

    // Mouse drag handler
    @Override
    public void mouseDragged(MouseEvent e) {
        dragging = true;
        cursorX = e.getX();  // Update current cursor position
        cursorY = e.getY();
    }

    // Mouse move handler
    @Override
    public void mouseMoved(MouseEvent e) {
        dragging = false;
        click = false;
        cursorX = e.getX();
        cursorY = e.getY();
    }

    // Unused mouse events (but must be implemented)
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
}

