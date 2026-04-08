package graphics;

import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;

interface GameObject {
    void update();
    void draw(Graphics2D g2d);
    Rectangle2D getBounds();
}