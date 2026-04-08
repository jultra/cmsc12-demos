package graphics.v4;

import java.awt.Color;

class FallingBall extends GlowingBall {
    private float vy = 0, gravity = 0.4f;

    public FallingBall(float x, float y, int r, Color c) { super(x, y, r, c); }

    @Override
    public void update() {
        vy += gravity;
        y += vy;
    }
}

