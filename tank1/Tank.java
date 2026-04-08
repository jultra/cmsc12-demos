package tank1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class Tank extends GameObject{

    int turretWidth = 20, turretHeight = 10;

    int velocityX = 5;


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

    public void update(){

        if(this.x + this.width + turretWidth > GameTester.GAME_WIDTH || this.x < 0){
            velocityX *= -1;
            this.x = this.x + velocityX;

            if(velocityX > 0){
                rotation = 0;
            }else{
                rotation = 180;
            }
        }

        this.x = this.x + velocityX;
    }

    public int getHeight() {
        return height;
    }

    public void draw(Graphics2D g2d){

        AffineTransform oldTransform = g2d.getTransform();
        
        Rectangle2D bounds = getBounds();

        double centerX = bounds.getCenterX();
        double centerY = bounds.getCenterY();

        g2d.rotate(Math.toRadians(rotation), centerX, centerY);
        
        g2d.setColor(Color.RED);
        // g2d.draw(new Rectangle(20, 20, 30, 20));
        g2d.fill(new Rectangle(getX(), getY(), getWidth(), getHeight()));

        g2d.setColor(Color.BLACK);
        g2d.fill(new Rectangle(getX()+ getWidth() - 10, 
                        getY() + (getHeight()-getTurretHeight())/2, 
                        getTurretWidth(), 
                        getTurretHeight()));


        g2d.setTransform(oldTransform);

    }

    public Rectangle2D getBounds(){
        return new Rectangle(x, y, width+turretWidth, height+turretHeight);
    }

    
}