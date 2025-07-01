package Game5_update;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable, MouseListener, MouseMotionListener {
	
	public static int WIDTH = 1200;
	public static int HEIGHT = 500;
	
	private Thread thread;
	private boolean running;
	private BufferedImage image;
	private Graphics2D g;
	
	private int fps = 60;
	
	public String getSelectedBall() { return selectedBallType; }
	
	private String selectedBallType = "ball1"; // Default 
	private Cannon cannon = CannonFactory.createCannon(selectedBallType); 
	
	
	private ArrayList<Ball> balls = new ArrayList<>(); 

	private Cloud cloud1 = new Cloud();
	private Cloud cloud2 = new Cloud();
	private Cloud cloud3 = new Cloud();
	
    private int ball1ButtonX = 50;
    private int ball1ButtonY = 200;
    private int buttonDiameter1 = 90;
    
    private int ball2ButtonX = 160;
    private int ball2ButtonY = 213;
    private int buttonDiameter2 = 70;
    
    private int ball3ButtonX = 250;
    private int ball3ButtonY = 230;
    private int buttonDiameter3 = 40;
	//----------------------------------------------------------//
	
	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		addMouseMotionListener(this);
		addMouseListener(this);
	}
	
	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public void run() {
		running = true;
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//fps
		long startTime;
		long URDTimeMillis;
		long waitTime;
		long targetTime = 1000 / fps;
		
		//-------------------- GAME LOOP -----------------//
		while (running) {
			startTime = System.nanoTime();
			
			gameRender();
			gameDraw();
			
			//fps
			URDTimeMillis = (System.nanoTime() - startTime) / 1000000;
			waitTime = targetTime - URDTimeMillis;
			try {
				Thread.sleep(waitTime);
			} catch (Exception e) {}
		}
	}
	
	private void gameRender() {
        // Background and grass
        g.setColor(new Color(197, 234, 243));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(new Color(28, 232, 119));
        g.fillRect(0, HEIGHT - 50, WIDTH, 50);

        // Clouds
        g = cloud1.draw(g);
        g = cloud2.draw(g);
        g = cloud3.draw(g);

        // Cannon
        g = cannon.draw(g, balls);
        
        // Game text
        g.setColor(Color.BLACK);
        Font f = new Font("Calibri", Font.BOLD, 72);
        g.setFont(f);
        g.drawString("Cannon Simulator", (WIDTH/4)+40, 85);
        f = new Font("Helvetica", Font.BOLD, 24);
        g.setFont(f);
        g.drawString("A totally accurate simulation of using a cannon!", (WIDTH/4)+30, 115);

        // Ball selection buttons
        g.setColor(selectedBallType.equals("ball1") ? Color.RED : Color.LIGHT_GRAY);
        g.fillOval(ball1ButtonX, ball1ButtonY, buttonDiameter1, buttonDiameter1);
        g.setColor(selectedBallType.equals("ball2") ? Color.YELLOW : Color.LIGHT_GRAY);
        g.fillOval(ball2ButtonX, ball2ButtonY, buttonDiameter2, buttonDiameter2);
        g.setColor(selectedBallType.equals("ball3") ? Color.BLUE : Color.LIGHT_GRAY);
        g.fillOval(ball3ButtonX, ball3ButtonY, buttonDiameter3, buttonDiameter3);
        
        g.setColor(Color.BLACK);
        f = new Font("Calibri", Font.BOLD, 20);
        g.setFont(f);
        g.drawString("1", ball1ButtonX + 40, ball1ButtonY + ball1ButtonY/4);
        g.drawString("2", ball2ButtonX + 29, ball1ButtonY + ball1ButtonY/4 + 5);
        g.drawString("3", ball3ButtonX + 15, ball1ButtonY + ball1ButtonY/4 + 7);
    }
	
	private void gameDraw() {
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}
	
	//-------------------- MOUSE LISTENING -----------------//
	static boolean dragging = false;
	static boolean click = false;
	static int cursorX = 0;
	static int cursorY = 0;
	
	@Override
    public void mouseClicked(MouseEvent e) {
        click = true;
        cursorX = e.getX();
        cursorY = e.getY();
        // Check if Ball 1 button is clicked
        if (cursorX >= ball1ButtonX && cursorX <= ball1ButtonX + buttonDiameter1 
            && cursorY >= ball1ButtonY && cursorY <= ball1ButtonY + buttonDiameter1) {
            selectedBallType = "ball1";
            cannon = CannonFactory.createCannon(selectedBallType);
        }
        // Check if Ball 2 button is clicked
        if (cursorX >= ball2ButtonX && cursorX <= ball2ButtonX + buttonDiameter2 
            && cursorY >= ball2ButtonY && cursorY <= ball2ButtonY + buttonDiameter2) {
            selectedBallType = "ball2";
            cannon = CannonFactory.createCannon(selectedBallType);
        }
        // Check if Ball 3 button is clicked
        if (cursorX >= ball3ButtonX && cursorX <= ball3ButtonX + buttonDiameter3 
            && cursorY >= ball3ButtonY && cursorY <= ball3ButtonY + buttonDiameter3) {
            selectedBallType = "ball3";
            cannon = CannonFactory.createCannon(selectedBallType);
        }
    }
	
	@Override
	public void mouseDragged(MouseEvent e) {
		dragging = true;
		cursorX = e.getX();
		cursorY = e.getY();
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		dragging = false;
		click = false;
		cursorX = e.getX();
		cursorY = e.getY();
	}
	
	//EMPTY FUNCTIONS
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
}