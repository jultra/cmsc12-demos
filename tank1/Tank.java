package tank1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Tank extends GameObject{

    int turretWidth = 20, turretHeight = 10;


    public int getTurretHeight() {
        return turretHeight;
    }


    public int getTurretWidth() {
        return turretWidth;
    }


    public Tank(int x, int y, int width, int height){
        super(x, y, width, height);
        // this.x = x;
        // this.y = y;
        // this.width = width;
        // this.height = height;
    }


    public int getHeight() {
        return height;
    }

    public void draw(Graphics2D g2d){
        
        g2d.setColor(Color.RED);
        // g2d.draw(new Rectangle(20, 20, 30, 20));
        g2d.fill(new Rectangle(getX(), getY(), getWidth(), getHeight()));

        g2d.setColor(Color.BLACK);
        g2d.fill(new Rectangle(getX()+ getWidth() - 10, 
                        getY() + (getHeight()-getTurretHeight())/2, 
                        getTurretWidth(), 
                        getTurretHeight()));

    }

    
}