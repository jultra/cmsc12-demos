package tank1.test;

import java.util.Random;

import javax.swing.JFrame;


import tank1.core.GameCanvas;

public class GameTester extends JFrame{

    public final static Random randomness = new Random();

    GameCanvas gameCanvas;

    public final static int GAME_WIDTH = 800;
    public final static int GAME_HEIGHT = 800;

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
