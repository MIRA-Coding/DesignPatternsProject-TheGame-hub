package Game5_update;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;

public abstract class Cannon {
    protected int angle;
    protected int size;
    protected int power;
    protected Color color;
    protected int diameter;
    protected int x = 500;
    protected int y = GamePanel.HEIGHT - 150;
    protected int ballX, ballY;
    protected int width = 300;

    protected int fireButtonX = 105, fireButtonY = 320;
    protected int fireButtonWidth = 129, fireButtonHeight = 59;
    protected int clearButtonX = 105, clearButtonY = 400;
    protected int clearButtonWidth = 100, clearButtonHeight = 40;

    public Cannon(int angle, int size, int power, Color color) {
        this.angle = angle;
        this.size = size;
        this.power = power;
        this.color = color;
        this.diameter = size + 50;
    }

    public abstract Graphics2D draw(Graphics2D g, ArrayList<Ball> balls);

    protected Graphics2D drawCannon(Graphics2D g) {
        int[] xPoly = {x, x + width, x + width, x};
        int[] yPoly = {y, y, y + diameter, y + diameter};

        for (int i = 0; i < xPoly.length; i++) {
            int[] rotated = rotateXY(xPoly[i], yPoly[i], angle, x, y + diameter);
            xPoly[i] = rotated[0];
            yPoly[i] = rotated[1];
        }

        for (int i = 0; i < yPoly.length; i++) {
            yPoly[i] += y + 100 - yPoly[3];
        }

        ballX = xPoly[1] + (xPoly[2] - xPoly[1]) - diameter - 2;
        ballY = yPoly[1];

        g.setColor(Color.BLACK);
        g.fillPolygon(new Polygon(xPoly, yPoly, 4));
        g.setColor(new Color(139, 69, 19));
        g.fillOval(x - 25, GamePanel.HEIGHT - 100, 100, 100);

        return g;
    }

    protected Graphics2D drawButtons(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillRect(fireButtonX, fireButtonY, fireButtonWidth, fireButtonHeight);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Helvetica", Font.BOLD, 48));
        g.drawString("FIRE", fireButtonX + 7, fireButtonY + fireButtonHeight - 10);

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(clearButtonX, clearButtonY, clearButtonWidth, clearButtonHeight);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Calibri", Font.BOLD, 32));
        g.drawString("CLEAR", clearButtonX + 7, clearButtonY + clearButtonHeight - 10);

        return g;
    }

    protected boolean isClickWithin(int x, int y, int w, int h) {
        return GamePanel.cursorX >= x && GamePanel.cursorX <= x + w &&
               GamePanel.cursorY >= y && GamePanel.cursorY <= y + h;
    }

    protected int[] rotateXY(int x, int y, int angle, int cx, int cy) {
        double tempX = x - cx;
        double tempY = y - cy;
        double rotatedX = tempX * Math.cos(angle / 100.0) - tempY * Math.sin(angle / 100.0);
        double rotatedY = tempX * Math.sin(angle / 100.0) + tempY * Math.cos(angle / 100.0);
        return new int[]{(int) (rotatedX + cx), (int) (rotatedY + cy)};
    }
}