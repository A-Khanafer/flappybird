package obstacles;

import interfaces.Drawable;
import utility.ImageTools;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Cylinder implements Drawable {

    private final Image pipeImage;
    private int x = 500;
    private int gapY;
    private final int width = 75;
    private final int gapHeight = 150;
    private Area cylinder;

    public Cylinder(int x) {
        createGeometry();
        this.x = x;
        Random rand = new Random();
        int type = rand.nextInt(6);
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
        pipeImage = ImageTools.readImageAndResize("pipe-green.png", 1.5);
    }

    
    @Override
    public void draw(Graphics2D g2d) {
        Graphics2D g2dCopy = (Graphics2D) g2d.create();

        g2dCopy.drawImage(pipeImage,x - 1,gapY + gapHeight,null);
        g2dCopy.rotate(Math.toRadians(180), (double) x- width , (double) gapY/2);
        g2dCopy.drawImage(pipeImage,x -227,0,null);
        g2dCopy.dispose();
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
        int height = 635;
        cylinder = new Area(new Rectangle2D.Double(x, y,width, height));
        int gapWidth = 85;
        Area gap = new Area(new Rectangle2D.Double(x, gapY, gapWidth, gapHeight));
        cylinder.subtract(gap);
    }
}
