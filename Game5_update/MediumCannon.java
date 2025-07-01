package Game5_update;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class MediumCannon extends Cannon {
    public MediumCannon() {
        super(-63, 40, 110, Color.YELLOW);
    }

    @Override
    public Graphics2D draw(Graphics2D g, ArrayList<Ball> balls) {
        g = drawBalls(g, balls);
        g = drawCannon(g);
        g = drawButtons(g);
        return g;
    }

    private Graphics2D drawBalls(Graphics2D g, ArrayList<Ball> balls) {
        if (GamePanel.click && isClickWithin(fireButtonX, fireButtonY, fireButtonWidth, fireButtonHeight)) {
            double angleRad = angle / 100.0;
            int speedX = (int) (power * Math.cos(angleRad));
            int speedY = (int) (-power * Math.sin(angleRad));
            Ball newBall = new Ball(ballX, ballY, diameter, speedX, speedY, color);
            balls.add(newBall);
            GamePanel.click = false;
        }

        if (GamePanel.click && isClickWithin(clearButtonX, clearButtonY, clearButtonWidth, clearButtonHeight)) {
            balls.clear();
            GamePanel.click = false;
        }

        for (Ball b : balls) {
            g.setColor(b.getColor());
            g.fillOval(b.getX(), b.getY(), b.getDiameter(), b.getDiameter());
            b.update();
        }

        return g;
    }


}