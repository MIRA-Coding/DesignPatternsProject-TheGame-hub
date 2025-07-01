package Game5;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;

public class Cannon {

	// Coordinates and dimensions for the fire and clear buttons
	private int fireButtonX = 105;
	private int fireButtonY = 350;
	private int fireButtonWidth = 100;
	private int fireButtonHeight = 50;
	private int clearButtonX = 1075;
	private int clearButtonY = 25;
	private int clearButtonWidth = 100;
	private int clearButtonHeight = 40;

	// Coordinates and dimensions for the color selection UI
	private int colorSelectionX = 50;
	private int colorSelectionY = 75;
	private int colorBoxWidth = 20;
	private Color colorSelected = new Color(85, 85, 85); // Default selected color

	// Cannon size properties
	private int diameter = 100;
	private int width = 300;

	// Cannon base position
	private int x = 500;
	private int y = GamePanel.HEIGHT - diameter - 50;

	// Ball spawn position
	private int ballX;
	private int ballY;

	// Cannon control parameters
	private int angle;
	private int size;
	private int power;

	// List of all fired balls
	private ArrayList<Ball> balls = new ArrayList<Ball>();

	//----------------------------------------------------------//

	// Constructor
	public Cannon() {
	}

	// Main draw method called in the game loop
	public Graphics2D draw(Graphics2D g, int angle, int size, int power) {
		this.angle = angle;
		this.size = size;
		this.power = power;

		g = drawBalls(g);              // Draw and update all balls
		g = drawCannon(g);             // Draw the cannon itself
		g = drawButtons(g);            // Draw the fire and clear buttons
		g = drawColorSelection(g);     // Draw the color selection UI

		return g;
	}

	// Draw the cannon using a rotated polygon and a wheel
	private Graphics2D drawCannon(Graphics2D g) {

		diameter = size + 50;

		// Cannon rectangle polygon (4 corners)
		int xPoly[] = {x, x + width, x + width, x};
		int yPoly[] = {y, y, y + diameter, y + diameter};
		int i;

		// Apply rotation to each corner
		for (i = 0; i < xPoly.length; i++) {
			int newXY[] = rotateXY(xPoly[i], yPoly[i], angle, x, y + diameter);
			xPoly[i] = newXY[0];
			yPoly[i] = newXY[1];
		}

		// Adjust cannon's Y-position to stay anchored
		for (i = 0; i < xPoly.length; i++) {
			yPoly[i] = yPoly[i] + y + 100 - yPoly[3];
		}

		// Calculate ball spawn location
		ballX = xPoly[1];
		ballY = yPoly[1];
		ballX += xPoly[2] - xPoly[1] - diameter - 2;

		// Draw cannon body
		g.setColor(Color.BLACK);
		Polygon poly = new Polygon(xPoly, yPoly, xPoly.length);
		g.fillPolygon(poly);

		// Draw cannon wheel
		g.setColor(new Color(139, 69, 19));
		g.fillOval(x - 25, GamePanel.HEIGHT - 100, 100, 100);

		return g;
	}

	// Draw all the balls, check for FIRE and CLEAR button clicks
	private Graphics2D drawBalls(Graphics2D g) {

		// If FIRE button is clicked, calculate ball velocity based on angle and power
		if (GamePanel.click == true && GamePanel.cursorX > fireButtonX && GamePanel.cursorX < fireButtonX + fireButtonWidth && GamePanel.cursorY > fireButtonY && GamePanel.cursorY < fireButtonY + fireButtonHeight) {
			balls.add(new Ball(
				ballX,
				ballY,
				diameter,
				(int) (((double) power * -1) - ((double) power * -1) / ((double) 157) * (angle * -1)),
				(int) (((double) power * -1) / ((double) 157) * (angle * -1)),
				colorSelected
			));
			GamePanel.click = false;
		}

		// If CLEAR button is clicked, remove all balls
		if (GamePanel.click == true && GamePanel.cursorX > clearButtonX && GamePanel.cursorX < clearButtonX + fireButtonWidth && GamePanel.cursorY > clearButtonY && GamePanel.cursorY < clearButtonY + clearButtonHeight) {
			balls.removeAll(balls);
			GamePanel.click = false;
		}

		// Draw and update each ball
		for (int i = 0; i < balls.size(); i++) {
			g.setColor(balls.get(i).getColor());
			g.fillOval(balls.get(i).getX(), balls.get(i).getY(), balls.get(i).getDiameter(), balls.get(i).getDiameter());
			balls.get(i).update();
		}

		return g;
	}

	// Draw the FIRE and CLEAR buttons
	private Graphics2D drawButtons(Graphics2D g) {

		// FIRE button
		g.setColor(Color.RED);
		g.fillRect(fireButtonX, fireButtonY, fireButtonWidth, fireButtonHeight);
		g.setColor(Color.BLACK);
		Font f = new Font("Calibri", Font.BOLD, 48);
		g.setFont(f);
		g.drawString("FIRE", fireButtonX + 7, fireButtonY + fireButtonHeight - 10);

		// CLEAR button
		g.setColor(Color.CYAN);
		g.fillRect(clearButtonX, clearButtonY, clearButtonWidth, clearButtonHeight);
		g.setColor(Color.BLACK);
		f = new Font("Calibri", Font.BOLD, 32);
		g.setFont(f);
		g.drawString("CLEAR", clearButtonX + 7, clearButtonY + clearButtonHeight - 10);

		return g;
	}

	// Draw color selection boxes and handle clicks to change selected color
	private Graphics2D drawColorSelection(Graphics2D g) {

		g.setColor(Color.BLACK);
		Font f = new Font("Calibri", Font.BOLD, 24);
		g.setFont(f);
		g.drawString("Color", colorSelectionX, colorSelectionY - 12);

		// Define available colors
		Color[] boxColors = new Color[]{
			new Color(85, 85, 85),
			new Color(3, 61, 180),
			new Color(255, 0, 0),
			new Color(27, 137, 60),
			new Color(255, 177, 14),
			new Color(164, 73, 164)
		};

		// Draw color boxes and selection highlight
		for (int i = 0; i < boxColors.length; i++) {
			if (boxColors[i].getRGB() == colorSelected.getRGB()) {
				// Draw border around selected color
				g.setColor(Color.BLACK);
				g.fillRect(colorSelectionX + colorBoxWidth * i * 2 - 4, colorSelectionY - 4, colorBoxWidth + 8, colorBoxWidth + 8);
			}

			// Draw color box
			g.setColor(boxColors[i]);
			g.fillRect(colorSelectionX + colorBoxWidth * i * 2, colorSelectionY, colorBoxWidth, colorBoxWidth);

			// If clicked, set as selected color
			if (GamePanel.click == true && GamePanel.cursorX > (colorSelectionX + colorBoxWidth * i * 2) && GamePanel.cursorX < (colorSelectionX + colorBoxWidth * i * 2) + colorBoxWidth && GamePanel.cursorY > colorSelectionY && GamePanel.cursorY < colorSelectionY + colorBoxWidth) {
				colorSelected = boxColors[i];
				GamePanel.click = false;
			}
		}

		return g;
	}

	// Helper method to rotate a point (x, y) around a center point (cx, cy) by given angle
	private int[] rotateXY(int x, int y, int angle, int cx, int cy) {

		// Translate point to origin
		double tempX = x - cx;
		double tempY = y - cy;

		// Apply rotation matrix (angle is scaled down by 100)
		double rotatedX = tempX * Math.cos((double) angle / 100) - tempY * Math.sin((double) angle / 100);
		double rotatedY = tempX * Math.sin((double) angle / 100) + tempY * Math.cos((double) angle / 100);

		// Translate point back to original center
		x = (int) (rotatedX + cx);
		y = (int) (rotatedY + cy);

		return new int[]{x, y};
	}
}
