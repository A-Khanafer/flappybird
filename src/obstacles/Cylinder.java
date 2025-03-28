package obstacles;

import interfaces.Drawable;
import utility.ImageTools;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Cylinder implements Drawable {

    private final Image pipeImageTop;
    private final Image pipeImageBottom;
    private int x = 500;
    private int gapY;
    private final int height = 635;
    private final int width = 75;
    private final int gapHeight = 150;
    private final int gapWidth = 85;
    private Area gap;
    private Area cylinder;
    private final int type;

    public Cylinder(int x) {
        createGeometry();
        this.x = x;
        Random rand = new Random();
        type = rand.nextInt(6);
        switch(type) {
            case 0:
                gapY= 50;
                break;
            case 1:
                gapY= 100;
                break;
            case 2:
                gapY= 150;
                break;
            case 3:
                gapY= 200;
                break;
            case 4:
                gapY= 250;
                break;
            case 5:
                gapY= 300;
                break;
            default:
                break;
        }
        pipeImageTop = ImageTools.readImageAndResize("pipe-green.png", 1.5);
        pipeImageBottom = ImageTools.readImageAndResize("pipe-green.png", 1.5);
    }

    
    @Override
    public void draw(Graphics2D g2d) {
        Graphics2D g2dCopy = (Graphics2D) g2d.create();
        g2d.setColor(Color.black);
        g2d.draw(cylinder);
        g2d.drawImage(pipeImageBottom,x - 1,gapY + gapHeight,null);
        g2dCopy.rotate(Math.toRadians(-180), (double) x /250 , (double) (gapY - gapHeight) /2);
        g2dCopy.drawImage(pipeImageTop,x - 1,(gapY - gapHeight),null);
    }

    public void animate() {
        x -= 2;
        createGeometry();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getWidth() {
        return width;
    }

    private void createGeometry(){
        int y = 0;
        cylinder = new Area(new Rectangle2D.Double(x, y,width,height));
        gap = new Area(new Rectangle2D.Double(x,gapY,gapWidth,gapHeight));
        cylinder.subtract(gap);
    }
}
