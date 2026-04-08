package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferStrategy;

public class MiniGame extends Canvas implements Runnable {
    private double angle = 0; // For rotation
    private double x = 100;   // Player X position

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3); // Triple buffering
            return;
        }

        Graphics2D g2d = (Graphics2D) bs.getDrawGraphics();

        // 1. RENDERING HINTS: Smooth out the edges
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Clear Screen
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // 2. COORDINATE TRANSFORMATIONS
        // Save the current state
        AffineTransform old = g2d.getTransform();
        
        g2d.translate(x, 150);     // Move to player position
        g2d.rotate(Math.toRadians(angle), 25, 25); // Rotate around center (25,25)

        // 3. SHAPE API & PAINT
        g2d.setColor(Color.CYAN);
        Rectangle2D player = new Rectangle2D.Double(0, 0, 50, 50);
        g2d.fill(player); // Fill the shape
        
        g2d.setStroke(new BasicStroke(3)); // Thick outline
        g2d.setColor(Color.WHITE);
        g2d.draw(player); // Stroke the shape

        // Restore state for the next object (like UI)
        g2d.setTransform(old);

        // 4. TEXT RENDERING
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Press Alt+F4 to quit (just kidding)", 20, 40);

        g2d.dispose();
        bs.show();
    }

    public void update() {
        angle += 2; // Spin
        x += 1;     // Drift right
        if (x > getWidth()) x = -50;
    }

    @Override
    public void run() {
        while (true) {
            update();
            render();
            try { Thread.sleep(16); } catch (InterruptedException e) {} // ~60 FPS
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Java 2D Game Tech");
        MiniGame game = new MiniGame();
        frame.add(game);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        new Thread(game).start();
    }
}
