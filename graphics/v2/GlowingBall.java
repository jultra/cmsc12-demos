package graphics.v2;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class GlowingBall {
    private double x, y, radius;
    private double angle;
    private double pulseSpeed;
    private Color color;

    public GlowingBall(double x, double y, double radius, double pulseSpeed, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.pulseSpeed = pulseSpeed;
        this.color = color;
        this.angle = Math.random() * Math.PI * 2; // Random starting point
    }

    public void update() {
        angle += pulseSpeed;
    }

    public void draw(Graphics2D g2d) {
        float currentRadius = (float) (radius * (1.0 + 0.2 * Math.sin(angle)));
        
        // Setup Gradient (Center -> Glow -> Transparent)
        float[] fractions = {0.0f, 0.3f, 1.0f};
        Color[] colors = {
            Color.WHITE, 
            new Color(color.getRed(), color.getGreen(), color.getBlue(), 180), 
            new Color(color.getRed(), color.getGreen(), color.getBlue(), 0)
        };

        RadialGradientPaint paint = new RadialGradientPaint(
            (float)x, (float)y, currentRadius, fractions, colors);

        g2d.setPaint(paint);
        g2d.fill(new Ellipse2D.Double(x - currentRadius, y - currentRadius, currentRadius * 2, currentRadius * 2));
    }
}

