package tank1.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Bullet extends GameObject{
    

    // int width = 8, height = 5;

    int velocityX = 20; 

    public int getWidth() {
        return width;
    }

    public int getHeight(){
        return height;
    }

    public Bullet(int x, int y){
        super(x, y, 8, 5);
    }

    public void draw(Graphics2D g2d){

        g2d.setColor(Color.GRAY);
        g2d.fill(new Rectangle(x, y, width, height));
    }

    public void update(){
        this.x = this.x + velocityX;
    }
}
