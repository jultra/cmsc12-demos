package graphics.v3;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class GravityGlowBall extends JPanel {
    private float x = 100, y = 100;
    private float vx = 3, vy = 0;   // Initial horizontal/vertical speed
    private float gravity = 0.5f;   // Strength of gravity
    private float damping = 0.75f;  // Energy kept after a bounce (75%)
    private final int radius = 35;

    public GravityGlowBall() {
        setBackground(Color.BLACK);
        // Timer for physics and repainting (~60 FPS)
        new Timer(16, e -> {
            updatePhysics();
            repaint();
        }).start();
    }

    private void updatePhysics() {
        // 1. Apply gravity to vertical velocity
        vy += gravity;

        // 2. Update positions
        x += vx;
        y += vy;

        // 3. Wall Bouncing (Left/Right)
        if (x - radius < 0) {
            x = radius;
            vx *= -damping;
        } else if (x + radius > getWidth()) {
            x = getWidth() - radius;
            vx *= -damping;
        }

        // 4. Floor/Ceiling Bouncing
        if (y + radius > getHeight()) {
            y = getHeight() - radius; // Snap to floor
            vy *= -damping;           // Reverse and lose energy
            
            // Friction on the floor to stop horizontal sliding
            if (Math.abs(vy) < 0.5) {
                vy = 0;
                vx *= 0.98; // Slide friction
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Neon Glow Effect
        float[] fractions = {0.0f, 0.2f, 1.0f};
        Color[] colors = {Color.WHITE, new Color(255, 50, 255, 200), new Color(255, 50, 255, 0)};

        RadialGradientPaint rgp = new RadialGradientPaint(x, y, radius, fractions, colors);
        g2d.setPaint(rgp);
        g2d.fill(new Ellipse2D.Float(x - radius, y - radius, radius * 2, radius * 2));
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Gravity Glow");
        f.add(new GravityGlowBall());
        f.setSize(600, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}

