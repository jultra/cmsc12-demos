package graphics.v5;

import java.awt.Color;

class HorizontalBall extends GlowingBall {
    private float vx = 3.0f;

    public HorizontalBall(float x, float y, int r, Color c) { super(x, y, r, c); }

    @Override
    public void update() {
        x += vx;
    }
}

