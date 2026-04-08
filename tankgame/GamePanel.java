package tankgame;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;


public class GamePanel extends JPanel{

    // int tankX = 50, tankY = 50, tankWidth=35, tankHeight = 25;
    // int turretWidth = 20, turretHeight = 10, 
    //     turretX = tankX + tankWidth - 10, 
    //     turretY = tankY + (tankWidth - turretWidth),
    //     turretOffset = 10;

    // double tankDirection = 0;

    // int velocityX = 4, velocityY = 4;

    Tank player;

    // public GamePanel(){
    //     player = new Tank();
    // }

    // public void update(){

    //     player.update();

    //     repaint();
        
    // }

    ArrayList<Tank> enemyTanks = new ArrayList<Tank>();

    public void addEnemyTank(Tank tank){
        enemyTanks.add(tank);
    }

    public void setPlayer(Tank player){
        this.player = player;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        player.draw(g2d);

        for(Tank enemyTank: enemyTanks){
            enemyTank.draw(g2d);
        }

    }
    
}
