package graphics.v1;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class GlowBall extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Use a black background to make the glow pop
        setBackground(Color.BLACK);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = 100;

        // Define colors: White center -> Cyan glow -> Transparent edge
        float[] fractions = {0.0f, 0.2f, 1.0f};
        Color[] colors = {Color.WHITE, new Color(0, 255, 255, 200), new Color(0, 255, 255, 0)};

        RadialGradientPaint paint = new RadialGradientPaint(
            centerX, centerY, radius, fractions, colors);

        g2d.setPaint(paint);
        g2d.fill(new Ellipse2D.Double(centerX - radius, centerY - radius, radius * 2, radius * 2));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Glowing Ball");
        frame.add(new GlowBall());
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

