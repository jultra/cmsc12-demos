package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

public class Tank implements GameObject{

    public final static int VERTICAL = 1;
    public final static int HORIZONTAL = 2;

    /**
     * The current position of the tank.
     */
    protected int x, y;
    public int getY() {
        return y;
    }


    public void setY(int y) {
        this.y = y;
    }


    public int getX() {
        return x;
    }


    public void setX(int x) {
        this.x = x;
    }

    protected int tX, tY;

    protected int dx = 1, dy = 1;

    public int getDy() {
        return dy;
    }


    public void setDy(int dy) {
        this.dy = dy;
    }


    public int getDx() {
        return dx;
    }


    public void setDx(int dx) {
        this.dx = dx;
    }

    /**
     * controls the orientation
     */
    protected int width = 30, height = 20;

    protected int tWidth = 20, tHeight = 10;

    private int direction = 0;
    public int getDirection() {
        return direction;
    }

    private int orientation = HORIZONTAL;

    public int getOrientation() {
        return orientation;
    }


    public void setOrientation(int direction) {
        this.orientation = direction;
    }

    ArrayList<Bullet> bullets = new ArrayList<Bullet>();

    private Color color = Color.BLUE;



    public Tank(int x, int y){
        this.x = x;
        this.y = y;
        updateTurretPosition();
    }


    public void update(GameState state){
        if(state.isD_PRESSED()){
            // orientation = HORIZONTAL;
            direction = 0;
            orientation = HORIZONTAL;
            
            if(dx < 0){
                dx *= -1;
                // this.tX = x+width-5;
                // this.tY = y + (height-tHeight)/2;
            }

            if(x >= 0 && x < GameCanvas.AREA_WIDTH){
                x += dx;
                tX += dx;
            }
        }
        if(state.isA_PRESSED()){
            // orientation = HORIZONTAL;

            direction = 180;
            orientation = HORIZONTAL;

            if(dx > 0 ){
                dx *= -1;

                // this.tX = x-5;
                // this.tY = y + (height-tHeight)/2;
            }

            if(x > 0 && x <= GameCanvas.AREA_WIDTH){
                x += dx;
                tX += dx;
            }
                
        }

        if(state.isS_PRESSED()){
            // orientation = VERTICAL;
            direction = 90;
            orientation = VERTICAL;
            if(dy < 0){
                dy *= -1;
                
                // this.tX = x + (width - tWidth)/2;
                // this.tY = y+height-5;
            }

            if(y >= 0 && y < GameCanvas.AREA_HEIGHT){
                y += dy;
                tY += dy;
            }
        }

        if(state.isW_PRESSED()){
            // orientation = VERTICAL;
            direction = 270;
            orientation = VERTICAL;

            if(dy > 0 ){
                dy *= -1;
                
                // this.tX = x + (width - tWidth)/2;
                // this.tY = y-5;
            }

            if(y > 0 && y <= GameCanvas.AREA_HEIGHT){
                y += dy;
                tY += dy;
            }
        }

        if(state.isSPACE_PRESSED() && state.getTimer() % 3 == 0){
            Bullet bullet = fireBullet();
            bullets.add(bullet);
        }

        // for(Bullet bullet:bullets){
        //     bullet.update(state);
        // }

        updateBullets(state);
    }


    protected void updateBullets(GameState state) {
        Iterator<Bullet> bIterator = bullets.iterator();
        while(bIterator.hasNext()){
            Bullet bullet = bIterator.next();

            if( bullet.x > GameCanvas.AREA_WIDTH || bullet.x < 0 || bullet.y < 0 || bullet.y > GameCanvas.AREA_HEIGHT){
                bIterator.remove();
            }

            bullet.update(state);
        }
    }


    protected Bullet fireBullet() {
        Bullet bullet;

        if(orientation == HORIZONTAL){
            if(dx > 0){
                bullet = new Bullet(x+width+tWidth-5, y + (height-Bullet.height)/2);
            }else{
                bullet = new Bullet(x, y + (height-Bullet.height)/2);
            }
            
            bullet.setVelocity(dx*4, 0);
        }else{
            if(dy > 0){
                bullet = new Bullet(x + (width-Bullet.width)/2, height + y + tHeight);
            }else{
                bullet = new Bullet(x + (width-Bullet.width)/2, y-tHeight-5);
            }
            bullet.setVelocity(0, dy*4);
        }
        return bullet;
    }

    public void draw(){
        System.out.println(toString());
    }

    public void draw(Graphics2D g2d){

        drawBullets(g2d);

        drawTank(g2d);
    }


    private void drawBullets(Graphics2D g2d) {
        for(Bullet bullet:bullets){
            bullet.draw(g2d);
        }
    }


    protected void drawTank(Graphics2D g2d) {
        AffineTransform oldTransform = g2d.getTransform();

        g2d.rotate(Math.toRadians(direction), x+width/2, y+height/2);

        g2d.setColor(color);
        g2d.fillRect(x, y, width, height);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(tX, tY, tWidth, tHeight);

        
        g2d.setTransform(oldTransform);
    }


    public void setColor(Color c){
        this.color = c;
    }

    public void setSize(int width, int height){
        this.width = width;
        this.height = height;
        updateTurretPosition();
    }

    protected void updateTurretPosition(){
        this.tX = (x + width)-5;
        this.tY = y + (height-tHeight)/2;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        updateTurretPosition();
    }

    public void setVelocity(int dx, int dy){
        this.dx = dx;
        this.dy = dy;
    }

    public void setDirection(int direction){
        this.direction = direction;

        if( direction == 0 || direction == 180){
            this.orientation = HORIZONTAL;
        }else{
            this.orientation = VERTICAL;
        }
    }

    public Rectangle2D getBounds(){
        if(orientation == HORIZONTAL){
            return new Rectangle(x, y, width+tWidth, height);
        }else{
            return new Rectangle(x, y, width, height + tHeight);
        }
        
    }


    
    @Override
    public String toString(){
        return "Tank at position ("+x+","+y+")";
    }
    
}
