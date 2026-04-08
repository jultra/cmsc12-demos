package tankgame;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.Rectangle;
import java.awt.Shape;

public class Tank {
    int tankX = 50, tankY = 50, tankWidth=35, tankHeight = 25;
    int turretWidth = 20, turretHeight = 10, 
        turretX = tankX + tankWidth - 10, 
        turretY = tankY + (tankWidth - turretWidth),
        turretOffset = 10;

    double tankDirection = 0;

    int velocityX = 4, velocityY = 4;

    final static int ENEMY = 1;
    final static int PLAYER = 2;

    int type = PLAYER;

    public Tank(int tankX, int tankY, int tankDirection, int type){
        this.tankX = tankX;
        this.tankY = tankY;
        this.tankDirection = tankDirection;
        this.type = type;
    }

    public void update(){
        if(type == ENEMY){

            AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(tankDirection), tankX + tankWidth/2, tankY + tankHeight/2);

            Shape rotatedShape = at.createTransformedShape(new Rectangle(tankX, tankY, tankWidth + turretWidth, tankHeight + turretHeight));

            Rectangle rotatedBounds = rotatedShape.getBounds();

            if(rotatedBounds.x + rotatedBounds.width > TankGame.FRAME_WIDTH || rotatedBounds.x < 0){
                velocityX *= -1;
            }

            if(rotatedBounds.y + rotatedBounds.height > TankGame.FRAME_HEIGHT || rotatedBounds.y < 0){
                velocityY *= -1;
            }

            

            // tankX = tankX + (int) (velocityX * Math.cos(Math.toRadians(tankDirection)));
            // tankY = tankY + (int) (velocityY * Math.sin(Math.toRadians(tankDirection)));
            tankX = tankX + velocityX;
            tankY = tankY + velocityY;

            tankDirection = Math.toDegrees(Math.atan2(velocityY, velocityX));

            

        }else{    
            if(TankGame.MOVING_RIGHT){
                tankX = tankX + velocityX;
                tankDirection = 0;
            }

            if(TankGame.MOVING_LEFT){
                tankX = tankX - velocityX;
                tankDirection = 180;
            }

            if(TankGame.MOVING_UP){
                tankY = tankY - velocityY;
                tankDirection = 270;
            }

            if(TankGame.MOVING_DOWN){
                tankY = tankY + velocityY;
                tankDirection = 90;
            }
        }
    }

    public void draw(Graphics2D g2d){
        AffineTransform oldTransform = g2d.getTransform();

        g2d.rotate(Math.toRadians(tankDirection), tankX + tankWidth/2, tankY + tankHeight/2);
        g2d.setColor(Color.BLUE);
        g2d.fill(new Rectangle(tankX, tankY, tankWidth, tankHeight));

        g2d.setColor(Color.BLACK);
        g2d.fill(new Rectangle(tankX + tankWidth - turretOffset, tankY + (tankHeight - turretHeight)/2, turretWidth, turretHeight));
        
        g2d.setTransform(oldTransform);
    }
}
