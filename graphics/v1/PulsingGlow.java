package graphics.v1;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class PulsingGlow extends JPanel {
    private double scale = 1.0;
    private double angle = 0;

    public PulsingGlow() {
        setBackground(Color.BLACK);
        
        // Timer runs every 20ms (~50 FPS)
        Timer timer = new Timer(20, e -> {
            angle += 0.05; // Controls the speed of the pulse
            // Sine moves between -1 and 1. This maps it to 0.8 to 1.2 scale.
            scale = 1.0 + 0.2 * Math.sin(angle);
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        
        // Base radius multiplied by the current pulse scale
        float currentRadius = (float)(100 * scale);

        // Gradient: White center -> Cyan glow -> Transparent edge
        float[] fractions = {0.0f, 0.3f, 1.0f};
        Color[] colors = {
            Color.WHITE, 
            new Color(0, 255, 255, 180), 
            new Color(0, 255, 255, 0)
        };

        RadialGradientPaint paint = new RadialGradientPaint(
            centerX, centerY, currentRadius, fractions, colors);

        g2d.setPaint(paint);
        g2d.fill(new Ellipse2D.Double(
            centerX - currentRadius, 
            centerY - currentRadius, 
            currentRadius * 2, 
            currentRadius * 2));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pulsating Glow");
        frame.add(new PulsingGlow());
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

