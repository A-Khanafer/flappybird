package obstacles;

import interfaces.Drawable;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public class Cylinders implements Drawable {

    private int x = 500;
    private int y = 0;
    private final int height = 635;
    private final int width = 100;
    private final int gapHeight = 150;
    private final int gapWidth = 100;
    private Area gap;
    private Area cylinder;

    public Cylinders() {
        createGeometry();
        x = 500;
    }

    
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.black);
        g2d.draw(cylinder);
    }

    public void animate() {
        x -= 2;
        createGeometry();
    }

    private void createGeometry(){
        cylinder = new Area(new Rectangle2D.Double(x,y,width,height));
        gap = new Area(new Rectangle2D.Double(x,250,gapWidth,gapHeight));
        cylinder.subtract(gap);
    }
}
