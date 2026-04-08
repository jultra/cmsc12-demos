package game;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class EnemyTank extends Tank {

    static ArrayList<Bullet> enemyBullets = new ArrayList<Bullet>();

    public EnemyTank(int x, int y){
        super(x, y);
    }

    @Override
    public void update(GameState state){
        
        if( x - (width + tWidth) > GameCanvas.AREA_WIDTH){
            x = -5;
            updateTurretPosition();
        }

        if( y - height > GameCanvas.AREA_HEIGHT){
            y = -5;
            updateTurretPosition();
        }

        x += dx;
        tX += dx;

        y += dy;
        tY += dy;

        if(GameState.randomness.nextInt(0, 1000) < 100){
            Bullet bullet = fireBullet();
            enemyBullets.add(bullet);
        }
    }

    @Override
    public void draw(Graphics2D g2d){
        drawTank(g2d);
    }
    
    public static void updateEnemyBullets(GameState state){
        for(Bullet bullet : enemyBullets){
            bullet.update(state);
        }
    }
}
