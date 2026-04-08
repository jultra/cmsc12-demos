package tank1;

import java.awt.Graphics2D;

public abstract class GameObject {

    int x, y;

    public int getY() {
        return y;
    }


    public int getX() {
        return x;
    }


    int height, width;


    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }

    public GameObject(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    public abstract void draw(Graphics2D g2d);

    public abstract void update();
    
}
