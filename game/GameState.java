package game;

import java.util.Random;

public class GameState {
    private boolean D_PRESSED = false;

    private long timer = 0;

    final static Random randomness = new Random();

    private boolean GAME_OVER = false;

    public boolean isGAME_OVER() {
        return GAME_OVER;
    }

    public void setGAME_OVER(boolean gAME_OVER) {
        GAME_OVER = gAME_OVER;
    }

    public boolean isD_PRESSED() {
        return D_PRESSED;
    }

    public void setD_PRESSED(boolean d_PRESSED) {
        D_PRESSED = d_PRESSED;
    }

    private boolean A_PRESSED = false;

    public boolean isA_PRESSED() {
        return A_PRESSED;
    }

    public void setA_PRESSED(boolean a_PRESSED) {
        A_PRESSED = a_PRESSED;
    }

    private boolean W_PRESSED = false;


    public boolean isW_PRESSED() {
        return W_PRESSED;
    }

    public void setW_PRESSED(boolean w_PRESSED) {
        W_PRESSED = w_PRESSED;
    }

    private boolean S_PRESSED = false;
    
    private boolean SPACE_PRESSED;

    public boolean isSPACE_PRESSED() {
        return SPACE_PRESSED;
    }

    public boolean isS_PRESSED() {
        return S_PRESSED;
    }

    public void setS_PRESSED(boolean s_PRESSED) {
        S_PRESSED = s_PRESSED;
    }

    public void setSPACE_PRESSED(boolean b) {
        SPACE_PRESSED = b;
    }

    public void incrementTimer(){
        timer++;
    }

    public long getTimer(){
        return timer;
    }
}
