package graphics.v5;

import java.awt.*;

class ZigzagBall extends GlowingBall {
    private int timer = 0;
    private float vx = 4.0f;

    public ZigzagBall(float x, float y, int r, Color c) { super(x, y, r, c); }

    @Override
    public void update() {
        y += 2; // Constant fall
        timer++;
        if (timer % 20 == 0) vx *= -1; // Change direction every 20 frames
        x += vx;
    }
}

