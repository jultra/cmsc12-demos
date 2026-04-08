package tank1;

import java.util.Random;

import javax.swing.JFrame;

public class GameTester extends JFrame{

    final static Random randomness = new Random();


    public GameTester(){
        setSize(800, 800);
        setTitle("Tank Battle Royale!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        

        GameCanvas gameCanvas = new GameCanvas();

        add(gameCanvas);


    }

    public void startGame(){
        setVisible(true);
    }

    public static void main(String[] args) {
        new GameTester().startGame();;
    }
    
}
