package game;

import javax.swing.JFrame;

public class GameTester {

    public static void main(String[] args){
        JFrame gameWindow = new JFrame();
        gameWindow.setSize(500, 500);

        GameCanvas gameCanvas = new GameCanvas();
        
        gameWindow.add(gameCanvas);

        GameState state = gameCanvas.getGameState();

        // Tank tank1 = new Tank(20, 60);
        // gameObjects.add(tank1);
        // gameCanvas.setTank(tank1);

        gameWindow.addKeyListener(new StateController(state));
        
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setVisible(true);
    }    
}
