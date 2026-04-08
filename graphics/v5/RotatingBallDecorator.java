package graphics.v5;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class RotatingBallDecorator extends GlowingBall {
    private GlowingBall decoratedBall;
    private double angle = 0;
    private double rotationSpeed;

    public RotatingBallDecorator(GlowingBall ball, double rotationSpeed) {
        // Initialize the superclass with the same basic properties
        super(ball.x, ball.y, (int)ball.radius, ball.color);
        this.decoratedBall = ball;
        this.rotationSpeed = rotationSpeed;
    }

    @Override
    public void update() {
        // 1. Let the original ball handle its physics (Falling, Wavy, etc.)
        decoratedBall.update();
        
        // 2. Sync the decorator's coordinates with the inner ball
        this.x = decoratedBall.x;
        this.y = decoratedBall.y;
        
        // 3. Update the rotation angle
        angle += rotationSpeed;
    }

    @Override
    public void draw(Graphics2D g2d) {
        AffineTransform old = g2d.getTransform();

        // Apply rotation specifically around the ball's center
        g2d.rotate(angle, x, y); 
        
        // Draw the original ball in this rotated coordinate space
        decoratedBall.draw(g2d);

        // Optional: Draw a "spinning" detail (like a white line) so you can see it rotate
        g2d.setColor(new Color(255, 255, 255, 100));
        g2d.drawLine((int)x, (int)(y - radius), (int)x, (int)(y + radius));

        g2d.setTransform(old);
    }
}
