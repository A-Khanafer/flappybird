package entity;

import interfaces.Drawable;
import utility.ImageTools;
import utility.PhysicsTool;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Bird implements Drawable {

    public final static double PIXEL_PER_METER = 75.0;
    private double x;
    private double y;
    private double leftUpperCornerX;
    private double leftUpperCornerY;
    private double width;
    private double height;
    private double verticalVelocity= -5.427188981;
    private Image image;
    private Area area;
    private Point2D.Double center;
    private double initialY;



    public Bird(double x, double y, double width, String filePathForImage) {
        this.x = x * PIXEL_PER_METER;
        this.y = y * PIXEL_PER_METER;
        this.center = new Point2D.Double(this.x/2.0 , this.y/2.0);
        this.width = width;
        this.height = this.width * 0.72;
        this.image = ImageTools.readImageAndResize(filePathForImage, (int)this.width,  (int)this.height);
        this.leftUpperCornerX = center.getX() - (width / 2.0);
        createGeometry();
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.draw(area);
        g2d.drawImage(image, (int) leftUpperCornerX, (int) leftUpperCornerY, null);
    }



    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getVerticalVelocity() {
        return verticalVelocity;
    }

    public void setVerticalVelocity(double verticalVelocity) {
        this.verticalVelocity = verticalVelocity;
    }

    public Point2D.Double getCenter() {
        return this.center;
    }

    public double getInitialY() {
        return initialY;
    }

    public void setInitialY(double initialY) {
        this.initialY = initialY;
    }

    public void animation(double ticks){
        center.y = PhysicsTool.yPositionEquation(ticks, initialY , verticalVelocity)*PIXEL_PER_METER;
        createGeometry();
    }

    private void createGeometry(){
        leftUpperCornerY = center.getY() - (height / 2.0);
        area = new Area(new Rectangle2D.Double(leftUpperCornerX, leftUpperCornerY, width, height));
    }


}
