package graphics.v3;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class BouncingGlowBall extends JPanel {
    private float x = 100, y = 100;
    private float vx = 4, vy = 3; // Movement speed
    private final int radius = 40;
    private float pulseAngle = 0;

    public BouncingGlowBall() {
        setBackground(Color.BLACK);
        // Timer runs at ~60 FPS
        new Timer(16, e -> {
            updatePhysics();
            repaint();
        }).start();
    }

    private void updatePhysics() {
        x += vx;
        y += vy;

        // Bounce off walls
        if (x - radius < 0 || x + radius > getWidth()) vx *= -1;
        if (y - radius < 0 || y + radius > getHeight()) vy *= -1;
        
        pulseAngle += 0.1; // Speed of the subtle pulse
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Calculate pulsing size
        float currentRadius = (float)(radius * (1.0 + 0.1 * Math.sin(pulseAngle)));

        // Gradient: Core (White) -> Inner Glow (Cyan) -> Edge (Transparent)
        float[] fractions = {0.0f, 0.2f, 1.0f};
        Color[] colors = {
            Color.WHITE, 
            new Color(0, 255, 255, 200), 
            new Color(0, 255, 255, 0)
        };

        RadialGradientPaint rgp = new RadialGradientPaint(x, y, currentRadius, fractions, colors);
        g2d.setPaint(rgp);
        g2d.fill(new Ellipse2D.Float(x - currentRadius, y - currentRadius, currentRadius * 2, currentRadius * 2));
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Glowing Bouncer");
        f.add(new BouncingGlowBall());
        f.setSize(600, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
