package graphics.v3;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.RenderingHints;

public class CollisionWorld extends JPanel {
    private List<GlowingBall> balls = new ArrayList<>();

    public CollisionWorld() {
        setBackground(Color.BLACK);
        // Create a few balls with random positions/colors
        balls.add(new GlowingBall(100, 100, 3, 2, 40, Color.CYAN));
        balls.add(new GlowingBall(400, 100, -3, 1, 35, Color.MAGENTA));
        balls.add(new GlowingBall(250, 50, 1, 5, 45, Color.GREEN));

        new Timer(16, e -> {
            // Update physics
            for (GlowingBall b : balls) b.update(getWidth(), getHeight(), 0.3f, 0.8f);

            // Check every pair of balls (Nested Loop)
            for (int i = 0; i < balls.size(); i++) {
                for (int j = i + 1; j < balls.size(); j++) {
                    balls.get(i).checkCollision(balls.get(j));
                }
            }
            repaint();
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (GlowingBall b : balls) b.draw(g2d);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Glowing Collisions");
        f.add(new CollisionWorld());
        f.setSize(600, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}

