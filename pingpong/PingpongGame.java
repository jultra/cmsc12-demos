package pingpong;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

public class PingpongGame extends JFrame{

    int WIDTH = 500;
    int HEIGHT = 500;

    JLabel ball;
    JLabel p1Paddle;
    JPanel playingTable;
    JLabel scoreLabel;

    boolean aPressed;
    boolean dPressed;

    ArrayList<Ball> balls = new ArrayList<Ball>();
    
    Random rand = new Random();
   
    public boolean spacePressed;
    public ArrayList<JLabel> bullets = new ArrayList<>();

    public PingpongGame(){
        setResizable(false);
        setTitle("Pinoy Pong");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        

        playingTable = new JPanel();
        playingTable.setLayout(null);
        playingTable.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(playingTable);

        p1Paddle = new JLabel();
        p1Paddle.setBackground(Color.RED);
        p1Paddle.setOpaque(true);
        p1Paddle.setSize(40, 15);
        p1Paddle.setLocation((WIDTH- p1Paddle.getWidth())/2, HEIGHT - p1Paddle.getHeight());
        playingTable.add(p1Paddle);

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setBounds(10, 10, 
                    scoreLabel.getPreferredSize().width, 
                    scoreLabel.getPreferredSize().height);
        playingTable.add(scoreLabel);

        addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_D){
                    dPressed = true;
                }

                if(e.getKeyCode() == KeyEvent.VK_A){
                    aPressed = true;
                }

                if(e.getKeyCode() == KeyEvent.VK_S){
                    new GameLoop().start();
                }

                if(e.getKeyCode() == KeyEvent.VK_SPACE){
                    spacePressed = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_D){
                    dPressed = false;
                }

                if(e.getKeyCode() == KeyEvent.VK_A){
                    aPressed = false;
                }

                if(e.getKeyCode() == KeyEvent.VK_SPACE){
                    spacePressed = false;
                }
            }
        });

        pack();
        setLocationRelativeTo(null);

    }

    class GameLoop extends Thread {
        // int ballY = -5;
        // int ballX = 3;
        int frameCounter = 0;
        int score = 0;
        @Override
        public void run(){
            
            initBoard();

            while(true){
               
                
                updateBalls();
                updatePaddle();
                if(frameCounter % 6 == 0){
                    shootBullets();
                }
                
                if(frameCounter % 30 == 0){
                    createRandomBall();
                }
                repaint();
        
                try{
                    Thread.sleep(1000/60);
                    frameCounter++;
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }

        private void shootBullets() {
           if(spacePressed){
                JLabel bullet = new JLabel();
                bullet.setBounds(p1Paddle.getLocation().x + p1Paddle.getWidth()/2, p1Paddle.getLocation().y-10, 5, 10);
                bullet.setOpaque(true);
                bullet.setBackground(Color.RED);
                playingTable.add(bullet);
                bullets.add(bullet); 
           }
        }

        private void initBoard() {
            createRandomBall();
            p1Paddle.setLocation((getWidth()- p1Paddle.getWidth())/2, getHeight() - 60);
        }

        public void createRandomBall(){
            Ball ball = new Ball();
            playingTable.add(ball);
            balls.add(ball);
        }

        public void updateBalls(){
            Iterator<Ball> ballsIterator = balls.iterator();
            while(ballsIterator.hasNext()){
                Ball ball = ballsIterator.next();
                Iterator<JLabel> bulletsIterator = bullets.iterator();
                while(bulletsIterator.hasNext()){
                    JLabel bullet = bulletsIterator.next();
                    if(bullet.getBounds().intersects(ball.getBounds())){
                        bulletsIterator.remove();
                        ballsIterator.remove();
                        // SwingUtilities.invokeLater(() -> {
                            playingTable.remove(bullet);
                            playingTable.remove(ball);
                        // });
                        break;
                    }
                }
            }

            ballsIterator = balls.iterator();
            while(ballsIterator.hasNext()){
                Ball ball = ballsIterator.next();
                if(ball.getBounds().intersects(p1Paddle.getBounds())){
                    score++;
                    scoreLabel.setText("Score: " + score);
                    scoreLabel.setBounds(10, 10, 
                        scoreLabel.getPreferredSize().width, 
                        scoreLabel.getPreferredSize().height);
                    ballsIterator.remove();

                    //SwingUtilities.invokeLater(() -> playingTable.remove(ball));
                    playingTable.remove(ball);
                    
                }
            }
            
            ballsIterator = balls.iterator();
            while(ballsIterator.hasNext()){
                Ball ball = ballsIterator.next();
                
                Iterator<Ball> ballsIterator2 = balls.iterator();
                while(ballsIterator2.hasNext()){
                    Ball ball2 = ballsIterator2.next();
                    if(ball != ball2){
                        if(ball.getBounds().intersects(ball2.getBounds())){
                            int vx = ball.ballX;
                            int vy = ball.ballY;
                            ball.ballX = ball2.ballX;
                            ball.ballY = ball2.ballY;
                            
                            ball2.ballX = vx;
                            ball2.ballY = vy;
                            ball2.setLocation(ball2.getLocation().x + ball2.ballX, ball2.getLocation().y + ball2.ballY);
                        }
                    }
                }
                if(ball.getLocation().y < 0 && ball.ballY < 0) {
                    ball.ballY = ball.ballY * -1;
                }

                if(ball.getLocation().x + ball.getWidth() > getWidth()-10 && ball.ballX > 0){
                    ball.ballX = ball.ballX * -1;
                }

                if(ball.getLocation().x < 0 && ball.ballX < 0){
                    ball.ballX = ball.ballX * -1;
                }
                if(ball.getLocation().y > getHeight()- 50 && ball.ballY > 0){
                    // JOptionPane.showMessageDialog(rootPane, "Game over!");
                    // return;
                    ball.ballY = ball.ballY * -1;
                }

                ball.setLocation(ball.getLocation().x + ball.ballX, ball.getLocation().y + ball.ballY);                
            }

            Iterator<JLabel> bulletsIterator = bullets.iterator();
            while(bulletsIterator.hasNext()){
                    JLabel bullet = bulletsIterator.next();
                    bullet.setLocation(bullet.getLocation().x, bullet.getLocation().y-5);
            }
        }

        public void updatePaddle(){
            if(aPressed) p1Paddle.setLocation(p1Paddle.getLocation().x - 5, p1Paddle.getLocation().y);
            if(dPressed) p1Paddle.setLocation(p1Paddle.getLocation().x + 5, p1Paddle.getLocation().y);
        }

        
    }

    class Ball extends JLabel{
        int ballX = (rand.nextInt(3) + 3) * (rand.nextBoolean() == true ? -1 : 1);
        int ballY = (rand.nextInt(3) + 3) *  (rand.nextBoolean() == true ? -1 : 1);

        public Ball(){
            setBackground(new Color(rand.nextInt(255), 
                                       rand.nextInt(255), 
                                       rand.nextInt(255)));
            setOpaque(true);
            setIcon(new ImageIcon(new ImageIcon("images/UP-Seal.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
            setSize(10, 10);
            setLocation(rand.nextInt(400), rand.nextInt(400));
        }
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                new PingpongGame().setVisible(true);
            }
        });
    }
}
