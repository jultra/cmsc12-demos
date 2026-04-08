package graphics.v5;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BallWorld extends JPanel {
    private List<GlowingBall> balls = new ArrayList<>();

    public BallWorld() {
        setBackground(Color.BLACK);

        // Add one of each type for the demo
        balls.add(new FallingBall(100, 50, 30, Color.RED));
        balls.add(new HorizontalBall(50, 200, 25, Color.CYAN));
        balls.add(new ZigzagBall(300, 50, 30, Color.GREEN));
        balls.add(new WavyBall(500, 50, 35, Color.MAGENTA));

        GlowingBall falling = new FallingBall(100, 50, 30, Color.RED);
        balls.add(new RotatingBallDecorator(falling, 0.1));

        // A Wavy Ball that rotates backwards
        GlowingBall wavy = new WavyBall(300, 50, 25, Color.BLUE);
        balls.add(new RotatingBallDecorator(wavy, -0.05));

        // Create a base ball (e.g., a WavyBall)
        GlowingBall base = new WavyBall(200, 50, 30, Color.MAGENTA);

        // Add Rotation
        GlowingBall rotated = new RotatingBallDecorator(base, 0.1);

        // Add Trail on top of the rotated ball
        GlowingBall finalBall = new TrailBallDecorator(rotated);

        balls.add(finalBall);


        balls.add(new SparkyBouncingBall(250, 50, 25, Color.GREEN));
        // Game Loop (60 FPS)
        new Timer(16, e -> {
            updateAndCleanup();
            repaint();
        }).start();
    }

    private void updateAndCleanup() {
        Iterator<GlowingBall> it = balls.iterator();
        while (it.hasNext()) {
            GlowingBall b = it.next();
            b.update();

            // Remove balls that fall off the screen (Performance)
            if (b.y > getHeight() + 100 || b.x > getWidth() + 100 || b.x < -100) {
                it.remove();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Clears the screen with the background color
        
        Graphics2D g2d = (Graphics2D) g;
        
        // 1. Enable Anti-Aliasing for smooth circles
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // 2. Loop through all balls and let them draw themselves
        for (GlowingBall ball : balls) {
            ball.draw(g2d); 
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Glow Ball Varieties");
        f.add(new BallWorld());
        f.setSize(800, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}

