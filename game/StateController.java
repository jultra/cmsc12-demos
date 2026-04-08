package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StateController extends KeyAdapter {

        GameState state;

        public StateController(GameState state){
            this.state = state;
        }
       
            
        @Override
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_D){
                state.setD_PRESSED(true);
            }

            if(e.getKeyCode() == KeyEvent.VK_A){
                state.setA_PRESSED(true);   
            }

            if(e.getKeyCode() == KeyEvent.VK_W){
                state.setW_PRESSED(true);
            }

            if(e.getKeyCode() == KeyEvent.VK_S){
                state.setS_PRESSED(true);
            }

            if(e.getKeyCode() == KeyEvent.VK_SPACE){
                state.setSPACE_PRESSED(true);
            }


        }

        @Override
        public void keyReleased(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_D){
                state.setD_PRESSED(false);
            }

            if(e.getKeyCode() == KeyEvent.VK_A){
                state.setA_PRESSED(false);
            }

            if(e.getKeyCode() == KeyEvent.VK_W){
                state.setW_PRESSED(false);
            }

            if(e.getKeyCode() == KeyEvent.VK_S){
                state.setS_PRESSED(false);
            }

            if(e.getKeyCode() == KeyEvent.VK_SPACE){
                state.setSPACE_PRESSED(false);
            }
        }
    

    }

