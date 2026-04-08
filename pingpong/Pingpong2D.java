package pingpong;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.*;

public class Pingpong2D extends JFrame {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static final int FPS = 60;

    // Load the image once to save memory and CPU
    private static final Image BALL_IMAGE = new ImageIcon("images/UP-Seal.png")
            .getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);

    private final GamePanel gamePanel;
    private final Timer gameTimer;
    private final ArrayList<Ball> balls = new ArrayList<>();
    private final Paddle paddle;
    private int score = 0;

    // Movement flags
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    public Pingpong2D() {
        setTitle("Pinoy Pong - Refactored");
        //setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        paddle = new Paddle();
        gamePanel = new GamePanel();
        add(gamePanel);

        // Key Bindings (Better than KeyListener for games)
        setupKeyBindings();

        // Swing Timer runs logic on the Event Dispatch Thread (Thread-Safe)
        gameTimer = new Timer(1000 / FPS, e -> updateGame());

        //pack();
        setLocationRelativeTo(null);
        
        // Start the game immediately or wait for Space
        gameTimer.start();
    }

    private void updateGame() {
        // Move Paddle
        if (leftPressed) paddle.move(-7);
        if (rightPressed) paddle.move(7);

        // Spawn ball every second (60 frames)
        if (new Random().nextInt(60) == 0) {
            balls.add(new Ball());
        }

        // Update Balls
        Iterator<Ball> it = balls.iterator();
        while (it.hasNext()) {
            Ball b = it.next();
            b.update();

            // Wall Bouncing
            if (b.x < 0 || b.x > WIDTH - 30) b.vx *= -1;
            if (b.y < 0) b.vy *= -1;

            // Paddle Collision
            if (b.getBounds().intersects(paddle.getBounds())) {
                score++;
                it.remove();
                continue;
            }

            // Game Over / Bottom bound
            if (b.y > HEIGHT) {
                it.remove(); // Or trigger game over logic here
            }
        }
        gamePanel.repaint();
    }

    private void setupKeyBindings() {
        InputMap im = gamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = gamePanel.getActionMap();

        im.put(KeyStroke.getKeyStroke("A"), "left");
        im.put(KeyStroke.getKeyStroke("released A"), "stopLeft");
        im.put(KeyStroke.getKeyStroke("D"), "right");
        im.put(KeyStroke.getKeyStroke("released D"), "stopRight");

        am.put("left", new AbstractAction() { public void actionPerformed(ActionEvent e) { leftPressed = true; }});
        am.put("stopLeft", new AbstractAction() { public void actionPerformed(ActionEvent e) { leftPressed = false; }});
        am.put("right", new AbstractAction() { public void actionPerformed(ActionEvent e) { rightPressed = true; }});
        am.put("stopRight", new AbstractAction() { public void actionPerformed(ActionEvent e) { rightPressed = false; }});
    }

    // Inner class for the Drawing Surface
    class GamePanel extends JPanel {
        public GamePanel() {
            setPreferredSize(new Dimension(WIDTH, HEIGHT));
            setBackground(Color.BLACK);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw Score
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 18));
            g2d.drawString("Score: " + score, 20, 30);

            // Draw Paddle
            g2d.setColor(Color.RED);
            g2d.fillRect(paddle.x, paddle.y, paddle.w, paddle.h);

            // Draw Balls
            for (Ball b : balls) {
                g2d.drawImage(BALL_IMAGE, b.x, b.y, null);
            }
        }
    }

    // Logical Ball Class (No UI components inside)
    class Ball {
        int x, y, vx, vy;
        Random r = new Random();

        Ball() {
            x = r.nextInt(WIDTH - 30);
            y = 50;
            vx = (r.nextBoolean() ? 1 : -1) * (r.nextInt(3) + 3);
            vy = r.nextInt(3) + 3;
        }

        void update() {
            x += vx;
            y += vy;
        }

        Rectangle getBounds() { return new Rectangle(x, y, 30, 30); }
    }

    // Logical Paddle Class
    class Paddle {
        int x = WIDTH / 2 - 40, y = HEIGHT - 40, w = 80, h = 15;

        void move(int dx) {
            x += dx;
            // Bound check
            x = Math.max(0, Math.min(WIDTH - w, x));
        }

        Rectangle getBounds() { return new Rectangle(x, y, w, h); }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Pingpong2D().setVisible(true));
    }
}

