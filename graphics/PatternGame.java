package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;


// 1. Wavy Enemy: Moves horizontally but bobs up and down on a Sine Wave
class WavyEnemy implements GameObject {
    double x, y, startY;
    double angle = 0; // The "secret sauce" for curves

    public WavyEnemy(int x, int y) {
        this.x = x;
        this.startY = y;
    }

    public void update() {
        x += 2; // Constant forward speed
        angle += 0.1; // Speed of the wave
        
        // y = center + (sin(angle) * amplitude)
        y = startY + Math.sin(angle) * 50; 

        if (x > 800) x = -50; // Wrap around screen
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.MAGENTA);
        g2d.fill(new Rectangle2D.Double(x, y, 30, 30));
    }
	
	public Rectangle2D getBounds() { return new Rectangle2D.Double(x, y, 30, 30); }
}

// 2. Circling Enemy: Orbits a fixed center point
class CirclingEnemy implements GameObject {
    double centerX, centerY;
    double angle = 0;
    double radius = 80;

    public CirclingEnemy(int x, int y) {
        this.centerX = x;
        this.centerY = y;
    }

    public void update() {
        angle += 0.05; // Rotation speed
    }

    public void draw(Graphics2D g2d) {
        // Polar to Cartesian conversion:
        // x = center + cos(angle) * r
        // y = center + sin(angle) * r
        double posX = centerX + Math.cos(angle) * radius;
        double posY = centerY + Math.sin(angle) * radius;

        g2d.setColor(Color.ORANGE);
        g2d.fillOval((int)posX, (int)posY, 30, 30);
    }
	
	public Rectangle2D getBounds() { 
		double posX = centerX + Math.cos(angle) * radius;
        double posY = centerY + Math.sin(angle) * radius;
		return new Rectangle2D.Double(posX, posY, 30, 30); 
	}
}

public class PatternGame extends Canvas implements Runnable {
    private List<GameObject> entities = new ArrayList<>();
    
    public PatternGame() {
        entities.add(new WavyEnemy(0, 100));
        entities.add(new WavyEnemy(0, 400));
        entities.add(new CirclingEnemy(400, 300));
    }

    private void update() {
        for (GameObject obj : entities) obj.update();
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) { createBufferStrategy(3); return; }
        Graphics2D g2d = (Graphics2D) bs.getDrawGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        for (GameObject obj : entities) obj.draw(g2d);

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
        JFrame f = new JFrame("Non-Linear Movement");
        PatternGame game = new PatternGame();
        f.add(game);
        f.setSize(800, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        new Thread(game).start();
    }
}
