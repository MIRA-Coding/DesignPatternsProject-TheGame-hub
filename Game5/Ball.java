package Game5;

import java.awt.Color;

public class Ball {
	private double x;                // X-coordinate of the ball
	private double y;                // Y-coordinate of the ball
	private double diameter;         // Diameter of the ball
	
	private double speedX;           // Speed in the horizontal direction
	private double speedY;           // Initial speed in the vertical direction
	
	private double velocity;         // Current vertical velocity
	private Color color;             // Color of the ball
	
	//----------------------------------------------------------//
	
	// Constructor to initialize the ball's properties
	public Ball(int x, int y, int diameter, int speedX, int speedY, Color color) {
		this.x = (double) x;
		this.y = (double) y;
		this.diameter = (double) diameter;

		// Simulate reduced horizontal speed (divided by 4.5)
		this.speedX = (double) speedX / 4.5;

		// Simulate upward initial vertical speed (negative direction)
		this.speedY = (double) ((speedY / 4.5) * -1);

		this.color = color;

		// Initialize vertical velocity
		this.velocity = this.speedY;
	}
	
	// Getters for position, size, and color
	public int getX() {
		return (int) x;
	}
	public int getY() {
		return (int) y;
	}
	public int getDiameter() {
		return (int) diameter;
	}
	public Color getColor() {
		return color;
	}
	
	// Update method to calculate the ball's physics per frame
	public void update() {
		
		//-------- X (Horizontal Movement) ----------//
		
		// Update horizontal position
		x = x + speedX;

		// Apply air resistance to horizontal speed
		speedX = speedX * 0.996;

		// Reverse direction if it hits the left or right wall
		if (x > GamePanel.WIDTH - diameter || x < 0) {
			speedX = speedX * -1;
		}
		
		// Stop the ball if horizontal speed is very low
		if (speedX < 0 && speedX > -0.1 || speedX > 0 && speedX < 0.1) {
			speedX = 0;
		}
		
		// Apply additional resistance if the ball is on the ground and barely moving
		if (velocity < 0.1 && y == GamePanel.HEIGHT - diameter) {
			speedX = speedX * 0.996;
		}
		
		//-------- Y (Vertical Movement) ----------//
		
		// Apply air resistance to vertical velocity
		velocity = velocity * 0.999;

		// Apply gravity
		velocity = velocity + 0.4;

		// Update vertical position
		y = y + velocity;
		
		// If it hits the bottom of the panel
		if (y + diameter >= GamePanel.HEIGHT) {
			// Bounce up with reduced force
			velocity = velocity * -1 + 0.55;

			// If the next bounce would keep it below the floor, clamp its position
			if (velocity + 0.4 + y + diameter >= GamePanel.HEIGHT) {
				y = GamePanel.HEIGHT - diameter;
			}
		}
	}
}
