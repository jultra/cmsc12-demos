package graphics.v5;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

class SparkyBouncingBall extends GlowingBall {
    private List<Spark> sparks = new ArrayList<>();
    private float vx = 3, vy = 0, gravity = 0.4f, damping = 0.7f;

    public SparkyBouncingBall(float x, float y, int r, Color c) { super(x, y, r, c); }

    @Override
    public void update() {
        vy += gravity;
        x += vx; y += vy;

        if( x > 800) x = -20.0f;

        // Bounce Logic
        if (y + radius > 580) { // Assuming 500 is floor
            y = 500 - radius;
            vy *= -damping;
            emitSparks(); // <--- SPAWN SPARKS ON BOUNCE
        }
        
        // Update existing sparks
        sparks.removeIf(Spark::isDead);
        for (Spark s : sparks) s.update();
    }

    private void emitSparks() {
        for (int i = 0; i < 10; i++) {
            sparks.add(new Spark(x, y + radius, color));
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        // Draw the sparks
        for (Spark s : sparks) s.draw(g2d);
        
        // Reset composite and draw the ball
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        super.draw(g2d);
    }
}

