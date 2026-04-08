package graphics.v5;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.LinkedList;

public class TrailBallDecorator extends GlowingBall {
    private GlowingBall decoratedBall;
    private LinkedList<Point.Float> history = new LinkedList<>();
    private final int MAX_TRAIL_LENGTH = 15; // Number of trail segments

    public TrailBallDecorator(GlowingBall ball) {
        super(ball.x, ball.y, (int)ball.radius, ball.color);
        this.decoratedBall = ball;
    }

    @Override
    public void update() {
        decoratedBall.update();
        
        // Sync position with the internal ball
        this.x = decoratedBall.x;
        this.y = decoratedBall.y;

        // Record the current position for the trail
        history.addFirst(new Point.Float(x, y));
        
        // Keep the trail at a fixed length
        if (history.size() > MAX_TRAIL_LENGTH) {
            history.removeLast();
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        // 1. Draw the trail segments first (so they appear behind the ball)
        int count = 0;
        for (Point.Float p : history) {
            // Calculate fading: transparency and size decrease as we go back in time
            float alpha = 1.0f - (float) count / MAX_TRAIL_LENGTH;
            float scale = 1.0f - ((float) count / MAX_TRAIL_LENGTH) * 0.5f;
            float trailRadius = radius * scale;

            // Apply transparency
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha * 0.4f));

            // Reuse the glow colors but adjust for the trail
            float[] fractions = {0.0f, 1.0f};
            Color[] colors = {color, new Color(color.getRed(), color.getGreen(), color.getBlue(), 0)};
            g2d.setPaint(new RadialGradientPaint(p.x, p.y, trailRadius, fractions, colors));
            
            g2d.fill(new Ellipse2D.Float(p.x - trailRadius, p.y - trailRadius, trailRadius * 2, trailRadius * 2));
            count++;
        }

        // 2. Reset transparency and draw the actual ball on top
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        decoratedBall.draw(g2d);
    }
}

