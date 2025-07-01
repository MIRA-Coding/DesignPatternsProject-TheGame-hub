package Game5;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class SliderInput {

	private int min;        // Minimum slider value
	private int max;        // Maximum slider value
	private int x;          // X-position of the slider bar
	private int y;          // Y-position of the slider bar

	private int width = 250;    // Width of the slider bar
	private int height = 10;    // Height of the slider bar

	private String label;       // Label text shown above the slider

	private int sliderX;        // X-position of the movable slider knob
	private int sliderWidth = 10;   // Width of the slider knob
	private int sliderHeight = 30;  // Height of the slider knob
	private boolean sliderGrapped = false;  // Whether the slider is being dragged

	//----------------------------------------------------------//

	// Constructor initializes slider position, range, and label
	public SliderInput(int x, int y, int min, int max, String label) {
		this.min = min;
		this.max = max;
		this.x = x;
		this.y = y;
		this.label = label;

		// Center the slider knob horizontally on the track
		this.sliderX = (width / 2) - (sliderWidth / 2);
	}

	// Draws the slider and handles interaction logic
	public Graphics2D draw(Graphics2D g) {

		// Detect if the user is dragging within the slider bounds
		if (GamePanel.dragging && GamePanel.cursorX > (sliderX - 10) + x && GamePanel.cursorX < sliderX + (sliderWidth + 10) + x && GamePanel.cursorY > y && GamePanel.cursorY < y + height) {
			sliderGrapped = true;
		}
		// Stop dragging when the mouse is released
		if (!GamePanel.dragging) {
			sliderGrapped = false;
		}
		// Update sliderX while dragging and cursor is within bounds
		if (sliderGrapped && GamePanel.cursorX > x + (sliderWidth / 2) && GamePanel.cursorX < x + width - 1) {
			sliderX = GamePanel.cursorX - x - (sliderWidth / 2);
		}

		// Draw slider bar
		g.setColor(Color.GRAY);
		g.fillRect(x, y, width, height);

		// Draw slider knob
		g.setColor(Color.BLACK);
		g.fillRect(sliderX + x, y - (sliderHeight / 3), sliderWidth, sliderHeight);

		// Draw slider label
		g.setColor(Color.BLACK);
		Font f = new Font("Calibri", Font.BOLD, 24);
		g.setFont(f);
		g.drawString(label, (width / 2) - (sliderWidth / 2), y - 12);

		return g;
	}

	// Calculates and returns the current value of the slider based on knob position
	public int getValue() {
		return (int) ((double) (sliderX + (sliderWidth / 2)) / (double) width * (double) (max - min));
	}
}
