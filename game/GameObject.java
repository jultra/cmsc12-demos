package game;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public interface GameObject {
    void update(GameState state);
    void draw(Graphics2D g2d);
    Rectangle2D getBounds();
}
