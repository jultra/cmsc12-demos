package graphics.v5;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class GlowingBall {
    protected float x, y, radius;
    protected Color color;

    public GlowingBall(float x, float y, int radius, Color color) {
        this.x = x; this.y = y; this.radius = radius;
        this.color = color;
    }

    public void update() { /* To be overridden */ }

    public void draw(Graphics2D g2d) {
        float[] fractions = {0.0f, 0.2f, 1.0f};
        Color[] colors = {Color.WHITE, color, new Color(color.getRed(), color.getGreen(), color.getBlue(), 0)};
        g2d.setPaint(new RadialGradientPaint(x, y, radius, fractions, colors));
        g2d.fill(new Ellipse2D.Float(x - radius, y - radius, radius * 2, radius * 2));
    }
}



