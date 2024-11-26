package entity;

import interfaces.Drawable;
import utility.ImageTools;
import utility.PhysicsTool;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Bird implements Drawable {

    private final static double PIXEL_PER_METER = 75.0;
    private double x;
    private double y;
    private double leftUpperCornerX;
    private double leftUpperCornerY;
    private double width;
    private double height;
    private double verticalVelocity;
    private Image image;
    private Area area;
    private Point2D.Double center;


    public Bird(double x, double y, double width, String filePathForImage) {
        this.x = x * PIXEL_PER_METER;
        this.y = y * PIXEL_PER_METER;
        this.center = new Point2D.Double(this.x/2.0 , this.y/2.0);
        this.width = width;
        this.height = this.width * 0.72;
        this.image = ImageTools.readImageAndResize(filePathForImage, (int)this.width,  (int)this.height);
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

    public void animation(double ticks){
        double yMeters = center.getY() / PIXEL_PER_METER;
        yMeters = PhysicsTool.yPositionEquation(ticks, center.y / PIXEL_PER_METER, verticalVelocity);
        center.y = yMeters * PIXEL_PER_METER;
        createGeometry();
        verticalVelocity = PhysicsTool.verticalVelocityEquation(ticks, verticalVelocity);

    }

    private void createGeometry(){

        leftUpperCornerX = center.getX() - (width / 2.0);
        leftUpperCornerY = center.getY() - (height / 2.0);
        System.out.println("leftUpperCornerX: " + leftUpperCornerX + " leftUpperCornerY: " + leftUpperCornerY);
        area = new Area(new Rectangle2D.Double(leftUpperCornerX, leftUpperCornerY, width, height));

    }


}
