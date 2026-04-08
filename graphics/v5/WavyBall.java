package graphics.v5;

import java.awt.Color;

class WavyBall extends GlowingBall {
    private float startX;
    private float angle = 0;

    public WavyBall(float x, float y, int r, Color c) {
        super(x, y, r, c);
        this.startX = x;
    }

    @Override
    public void update() {
        y += 1.5f; // Vertical speed
        angle += 0.1f; // Frequency
        x = startX + (float)(Math.sin(angle) * 50); // 50 is the "width" of the wave
    }
}
