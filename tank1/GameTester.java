package tank1;

import java.util.Random;

import javax.swing.JFrame;

public class GameTester extends JFrame{

    final static Random randomness = new Random();

    GameCanvas gameCanvas;

    final static int GAME_WIDTH = 800;
    final static int GAME_HEIGHT = 800;

    public GameTester(){
        setSize(GAME_WIDTH, GAME_HEIGHT);
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
