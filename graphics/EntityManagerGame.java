package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

// THE BLUEPRINT

// A SIMPLE ENEMY CLASS
class Enemy implements GameObject {
    double x, y, dy = 2;
    public Enemy(int x, int y) { this.x = x; this.y = y; }

    public void update() {
        y += dy; // Move down
        if (y > 500 || y < 50) dy *= -1; // Bounce
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.fill(getBounds());
    }

    public Rectangle2D getBounds() { return new Rectangle2D.Double(x, y, 30, 30); }
}

public class EntityManagerGame extends Canvas implements Runnable {
    private List<GameObject> entities = new ArrayList<>();
    
    public EntityManagerGame() {
        // Add various objects to our "World"
        entities.add(new Enemy(200, 100));
        entities.add(new Enemy(400, 300));
        entities.add(new Enemy(600, 200));
        // You could add a Player class here too!
    }

    private void update() {
        for (GameObject obj : entities) {
            obj.update();
        }
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) { createBufferStrategy(3); return; }
        Graphics2D g2d = (Graphics2D) bs.getDrawGraphics();

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // THE POWER OF OOP: One loop draws everything
        for (GameObject obj : entities) {
            obj.draw(g2d);
        }

        g2d.dispose();
        bs.show();
    }

    public void run() {
        while (true) {
            update();
            render();
            try { Thread.sleep(16); } catch (Exception e) {}
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Entity System");
        EntityManagerGame game = new EntityManagerGame();
        f.add(game);
        f.setSize(800, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        new Thread(game).start();
    }
}
