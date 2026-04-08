package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Bullet implements GameObject {

    // bullets location;
    int x, y;

    int dx=2, dy=0;

    static int height = 5, width = 8;

    public Bullet (int x, int y){
        this.x = x;
        this.y = y;
    }


    @Override
    public void update(GameState state) {
       this.x += dx;
       this.y += dy;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.fillRect(x, y, width, height);
    }


    public void setVelocity(int dx, int dy) {
       this.dx = dx;
       this.dy = dy;
    }

    public Rectangle2D getBounds(){
        return new Rectangle(x, y, width, height);
    }
    
}
