package tankgame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class TankGame extends JFrame{

    final static int FRAME_WIDTH = 800;
    final static int FRAME_HEIGHT = 600;
    final static int MOVE_RIGHT = 1;
    final static int MOVE_LEFT = 2;

    static boolean MOVING_RIGHT = false;
    static boolean MOVING_LEFT = false;
    static boolean MOVING_UP = false;
    static boolean MOVING_DOWN = false;

    public static Random randomness = new Random(1);

    ArrayList<Tank> enemyTanks = new ArrayList<Tank>();

    public TankGame(){
        setTitle("Tank Battle Royale!");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);

        GamePanel gameCanvas = new GamePanel();
        add(gameCanvas);
        addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyPressed(KeyEvent e) { //WASD keys for movements
                if(e.getKeyCode() == KeyEvent.VK_D){
                    MOVING_RIGHT = true;
                    // gameCanvas.update();
                }

                if(e.getKeyCode() == KeyEvent.VK_A){
                    MOVING_LEFT = true;
                    // gameCanvas.update();
                }

                if(e.getKeyCode() == KeyEvent.VK_W){
                    MOVING_UP = true;
                }

                if(e.getKeyCode() == KeyEvent.VK_S){
                    MOVING_DOWN = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_D){
                    MOVING_RIGHT = false;
               }

               if(e.getKeyCode() == KeyEvent.VK_A){
                    MOVING_LEFT = false;
               }

               if(e.getKeyCode() == KeyEvent.VK_W){
                    MOVING_UP = false;
                }

                if(e.getKeyCode() == KeyEvent.VK_S){
                    MOVING_DOWN = false;
                }
            }
        });

        Tank player = new Tank(50, 50, 0, Tank.PLAYER);

        gameCanvas.setPlayer(player);

        //we can create a game Thread
        // timer executes its events in the EDT
        Timer gameLoop = new Timer(16, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                player.update();
                gameCanvas.repaint();

                for(Tank enemyTank: enemyTanks){
                    enemyTank.update();
                }
            }
        });

        gameLoop.start();

        Timer enemySpawner = new Timer(1000, new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               Tank enemyTank = spawnEnemyTank();
               enemyTanks.add(enemyTank);
               gameCanvas.addEnemyTank(enemyTank);
            }
        });

        enemySpawner.start();
    }

    private Tank spawnEnemyTank(){
        return new Tank(randomness.nextInt(50, FRAME_WIDTH - 50), randomness.nextInt(50, FRAME_HEIGHT-50), randomness.nextInt(90), Tank.ENEMY);
    }

    public void startApp(){
        setVisible(true);
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                new TankGame().startApp();
            }
        });
    }
    
}
