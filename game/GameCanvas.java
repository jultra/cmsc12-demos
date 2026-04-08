package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Iterator;

public class GameCanvas extends JPanel{

    static final int AREA_WIDTH = 500;
    
    static final int AREA_HEIGHT = 500;

    GameState state;
    
    Tank player;

    ArrayList<GameObject> gameObjects;

    private Timer gameLoop;
    
    private Timer enemySpawner;

    public GameCanvas(){

        state = new GameState();

        gameObjects = new ArrayList<GameObject>();

        player = new Tank( AREA_WIDTH/2, AREA_HEIGHT/2);
        
        gameObjects.add(player);
        
        gameLoop = new Timer(16, new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e){
                state.incrementTimer();

                for(GameObject obj: gameObjects){
                    obj.update(state);
                }

                EnemyTank.updateEnemyBullets(state);

                performCollisionDetection();
                
                repaint();
            }
        });

        gameLoop.start();

        enemySpawner = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

                //Tank enemyTank = new Tank(100, 40);
                //enemyTank.setColor(Color.RED); this is not good design, we only want to create an enemy tank!!!!
                gameObjects.add(TankFactory.createEnemyTank());

            }
        });

        // enemySpawner.setRepeats(false);

        enemySpawner.start();
    }

    protected void performCollisionDetection() {

        // for each enemy tank
        // check if a player bullet has hit the enemy tank
        // if yes, remove enemy tank from the gameObjects list

        
        Iterator<Bullet> pBulletIterator = player.bullets.iterator();

        while (pBulletIterator.hasNext()) {
            Bullet bullet = pBulletIterator.next();
            
            Iterator<GameObject> enemyIterator = gameObjects.iterator();

            while(enemyIterator.hasNext()){
                GameObject eT = enemyIterator.next();

                if(eT == player) continue;

                // check if eT has collided with bullet
                // if yes, remove bullet from list
                //        remove tank from list

                //System.out.println(eT.getBounds() + "," + bullet.getBounds());

                if(eT.getBounds().intersects(bullet.getBounds())){
                    enemyIterator.remove();
                    pBulletIterator.remove();
                   // System.out.println("hit!");
                }
            }
        }

        Iterator<Bullet> eBulletsIterator = EnemyTank.enemyBullets.iterator();
        while(eBulletsIterator.hasNext()){
            Bullet bullet = eBulletsIterator.next();

            if(player.getBounds().intersects(bullet.getBounds())){
                gameLoop.stop();
                enemySpawner.stop();
                state.setGAME_OVER(true);
                JOptionPane.showMessageDialog(null, "You've been hit! Game over!");
                
            }
        }
        
    }

    public GameState getGameState(){
        return state;
    }

    public void setPlayer(Tank tank){
        this.player = tank;
    }

    

    // public void setGameObjects(ArrayList<GameObject> gameObjects){
    //     this.gameObjects = gameObjects;
    // }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);


        Graphics2D g2d = (Graphics2D) g;

        if(state.isGAME_OVER()){
            g2d.drawString("You've been hit! Game over!", AREA_WIDTH/2, AREA_HEIGHT/2);
            return;
        }
        
        //tank.draw(g2d);
        for(GameObject obj: gameObjects){
            obj.draw(g2d);
        }

        for(GameObject bullets: EnemyTank.enemyBullets){
            bullets.draw(g2d);
        }

        //g2d.drawString(tank.toString(), tank.x, tank.y);
        
    }
}
