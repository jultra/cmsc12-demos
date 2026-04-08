package tank1;

import java.util.Random;

import javax.swing.JFrame;

public class GameTester extends JFrame{

    final static Random randomness = new Random();

    GameCanvas gameCanvas;

    public GameTester(){
        setSize(800, 800);
        setTitle("Tank Battle Royale!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        

        gameCanvas = new GameCanvas();

        add(gameCanvas);


    }

    public void startGame(){
        setVisible(true);
        gameCanvas.startGame();
    }

    public static void main(String[] args) {
        new GameTester().startGame();;
    }
    
}
