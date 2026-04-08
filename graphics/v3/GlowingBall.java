package graphics.v3;

import java.awt.*;
import java.awt.geom.Ellipse2D;

class GlowingBall {
    float x, y, vx, vy, radius;
    Color color;

    public GlowingBall(float x, float y, float vx, float vy, int radius, Color color) {
        this.x = x; this.y = y; this.vx = vx; this.vy = vy;
        this.radius = radius;
        this.color = color;
    }

    public void update(int width, int height, float gravity, float damping) {
        vy += gravity;
        x += vx; y += vy;

        // Wall collisions
        if (x - radius < 0) { x = radius; vx *= -damping; }
        if (x + radius > width) { x = width - radius; vx *= -damping; }
        if (y + radius > height) { y = height - radius; vy *= -damping; }
    }

    public void draw(Graphics2D g2d) {
        float[] fractions = {0.0f, 0.2f, 1.0f};
        Color[] colors = {Color.WHITE, color, new Color(color.getRed(), color.getGreen(), color.getBlue(), 0)};
        g2d.setPaint(new RadialGradientPaint(x, y, radius, fractions, colors));
        g2d.fill(new Ellipse2D.Float(x - radius, y - radius, radius * 2, radius * 2));
    }

    // Check collision with another ball
    public void checkCollision(GlowingBall other) {
        float dx = other.x - this.x;
        float dy = other.y - this.y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        float minDistance = this.radius + other.radius;

        if (distance < minDistance) {
            // 1. Resolve overlap (prevent balls sticking together)
            float overlap = minDistance - distance;
            float nx = dx / distance; // Normal X
            float ny = dy / distance; // Normal Y
            
            this.x -= nx * overlap / 2;
            this.y -= ny * overlap / 2;
            other.x += nx * overlap / 2;
            other.y += ny * overlap / 2;

            // 2. Simple velocity swap (Elastic Collision)
            float tempVx = this.vx;
            float tempVy = this.vy;
            this.vx = other.vx;
            this.vy = other.vy;
            other.vx = tempVx;
            other.vy = tempVy;
        }
    }
}

