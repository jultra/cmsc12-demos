package tank1;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import java.util.ArrayList;

import javax.swing.JPanel;

import javax.swing.Timer;

public class GameCanvas extends JPanel{

    ArrayList<Tank> tanks = new ArrayList<Tank>();

    ArrayList<Bullet> bullets = new ArrayList<Bullet>();

    ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

    Timer gameLoop;

    public GameCanvas(){
        for(int i = 0; i < 10; i++){
            gameObjects.add(new Tank(GameTester.randomness.nextInt(800),
                                GameTester.randomness.nextInt(800),
                                30, 20));
        }

        for(int i = 0; i < 10; i++){
            gameObjects.add(new Bullet(GameTester.randomness.nextInt(800),
                                GameTester.randomness.nextInt(800)));
        }

        // frame is 60 frame per sec, 1000 ms/60 ~ 16ms
        gameLoop = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // update the state of the gameobjects
                // for each gameobject obj:
                //        obj.update()
                for(GameObject obj: gameObjects){
                    obj.update();
                }
                // repaint()  
                repaint();
            }
        });

    }

    public void startGame(){
        gameLoop.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // g2d.drawString("Tank Game", 800/2, 800/2);

        // g2d.setColor(Color.RED);
        // // g2d.draw(new Rectangle(20, 20, 30, 20));
        // g2d.fill(new Rectangle(tank1.getX(), tank1.getY(), tank1.getWidth(), tank1.getHeight()));

        // g2d.setColor(Color.BLACK);
        // g2d.fill(new Rectangle(tank1.getX()+ tank1.getWidth() - 10, tank1.getY() + (tank1.getHeight()-tank1.getTurretHeight())/2, tank1.getTurretWidth(), tank1.getTurretHeight()));
        // tank1.draw(g2d);
        // tank2.draw(g2d);

        // for(Tank tank: tanks){
        //     tank.draw(g2d);
        // }

        // for(Bullet bullet: bullets){
        //     bullet.draw(g2d);
        // }

        for(GameObject obj: gameObjects){
            obj.draw(g2d);
        }

    }
    
}
