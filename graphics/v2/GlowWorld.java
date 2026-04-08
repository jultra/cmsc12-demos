package graphics.v2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GlowWorld extends JPanel {
    private List<GlowingBall> balls = new ArrayList<>();

    public GlowWorld() {
        setBackground(Color.BLACK);
        
        // Create 5 balls at different spots with different colors
        balls.add(new GlowingBall(100, 100, 50, 0.5, Color.CYAN));
        balls.add(new GlowingBall(300, 150, 70, 0.03, Color.MAGENTA));
        balls.add(new GlowingBall(200, 300, 40, 0.08, Color.ORANGE));
        balls.add(new GlowingBall(100, 350, 60, 0.04, Color.GREEN));
        balls.add(new GlowingBall(350, 350, 30, 0.06, Color.RED));

        Timer timer = new Timer(20, e -> {
            for (GlowingBall ball : balls) ball.update();
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Loop through and draw all balls
        for (GlowingBall ball : balls) {
            ball.draw(g2d);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Multiple Glowing Balls");
        frame.add(new GlowWorld());
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

