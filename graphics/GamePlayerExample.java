package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.Rectangle2D;

public class GamePlayerExample extends Canvas implements Runnable {
    private boolean running = true;
    private int x = 100, y = 100;
    private int dx = 0, dy = 0; // Velocity
    
    // Animation state
    private int frame = 0;
    private long lastFrameTime = 0;
    private final int FRAME_DELAY = 100; // ms between frames

    public GamePlayerExample() {
        // Handle Keyboard Input
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_LEFT)  dx = -4;
                if(e.getKeyCode() == KeyEvent.VK_RIGHT) dx = 4;
                if(e.getKeyCode() == KeyEvent.VK_UP)    dy = -4;
                if(e.getKeyCode() == KeyEvent.VK_DOWN)  dy = 4;
            }
            public void keyReleased(KeyEvent e) {
                dx = 0; dy = 0;
            }
        });
    }

    private void update() {
        x += dx;
        y += dy;

        // Animation logic: cycle through 4 "frames"
        if (System.currentTimeMillis() - lastFrameTime > FRAME_DELAY) {
            if (dx != 0 || dy != 0) { // Only animate if moving
                frame = (frame + 1) % 4;
            } else {
                frame = 0; // Idle frame
            }
            lastFrameTime = System.currentTimeMillis();
        }
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics2D g2d = (Graphics2D) bs.getDrawGraphics();
        
        // 1. Setup Canvas
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // 2. Draw "Sprite" (Vector version since we don't have a file)
        // If we had an image, we'd use: g2d.drawImage(frames[frame], x, y, null);
        g2d.translate(x, y);
        
        // Draw a body
        g2d.setColor(Color.BLUE);
        g2d.fillRect(0, 0, 40, 40);
        
        // Draw "legs" that move based on the frame
        g2d.setColor(Color.WHITE);
        int legOffset = (frame % 2 == 0) ? 5 : 15;
        g2d.fillRect(5, 40, 10, legOffset);
        g2d.fillRect(25, 40, 10, 20 - legOffset);

        g2d.dispose();
        bs.show();
    }

    public void run() {
        while (running) {
            update();
            render();
            try { Thread.sleep(16); } catch (Exception e) {}
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Game Player");
        GamePlayerExample game = new GamePlayerExample();
        frame.add(game);
        frame.pack();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        game.requestFocus(); // Important for KeyListener!
        new Thread(game).start();
    }
}
