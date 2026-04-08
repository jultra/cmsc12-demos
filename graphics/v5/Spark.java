package graphics.v5;

import java.awt.*;

class Spark {
    float x, y, vx, vy, alpha = 1.0f;
    Color color;

    public Spark(float x, float y, Color color) {
        this.x = x; this.y = y;
        this.color = color;
        // Random explosion direction
        this.vx = (float) (Math.random() * 6 - 3);
        this.vy = (float) (Math.random() * -6); // Mostly upward
    }

    public void update() {
        x += vx; y += vy;
        vy += 0.2f;   // Gravity for sparks
        alpha = alpha - 0.05f > 0 ? alpha - 0.05f : 0 ; // Fade out
    }

    public boolean isDead() { return alpha <= 0; }

    public void draw(Graphics2D g2d) {
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.setColor(color);
        g2d.fillOval((int)x, (int)y, 3, 3); // Tiny dot
    }
}

