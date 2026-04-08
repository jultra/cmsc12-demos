package game;

import java.awt.Color;

public class TankFactory {
    
    public static Tank createEnemyTank(){
        
        int x =GameState.randomness.nextInt(GameCanvas.AREA_WIDTH);
        int y = GameState.randomness.nextInt(GameCanvas.AREA_HEIGHT);

                        
        Tank tank = new EnemyTank(x, y);

        if(GameState.randomness.nextBoolean()){
            tank.setDx(0);
            tank.setDirection(90);
        }else{
            tank.setDy(0);
            tank.setDirection(0);
        }

        tank.setColor(Color.RED);
        tank.setSize(20, 20);
        return tank;
    }
}
