package entity;

import interfaces.Drawable;
import utility.ImageTools;
import utility.PhysicsTool;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Bird implements Drawable {

    public final static double PIXEL_PER_METER = 75.0;
    private double x;
    private double y;
    private final double leftUpperCornerX;
    private double leftUpperCornerY;
    private double width;
    private double height;
    private double verticalVelocity= -5.427188981;
    private Image image;
    private Area area;
    private Point2D.Double center;
    private double initialY;
    private int counter = 0;
    private double rotationAngle = 0.0;
    private Image[] birdFrames;
    private int frameIndex =0;

    public Bird(double x, double y, double width, String filePathForImage) {
        this.x = x * PIXEL_PER_METER;
        this.y = y * PIXEL_PER_METER;
        this.center = new Point2D.Double(this.x/2.0 , this.y/2.0);
        this.width = width;
        this.height = this.width * 0.72;
        this.image = ImageTools.readImageAndResize(filePathForImage, (int)this.width,  (int)this.height);
        this.leftUpperCornerX = center.getX() - (width / 2.0);
        birdFrames = new Image[]{
                ImageTools.readImageAndResize("yellowbird-upflap.png", (int) this.width, (int) this.height),
                ImageTools.readImageAndResize("yellowbird-midflap.png", (int) this.width, (int) this.height),
                ImageTools.readImageAndResize("yellowbird-downflap.png", (int) this.width, (int) this.height)
        };
        createGeometry();
    }

    @Override
    public void draw(Graphics2D g2d) {
        Graphics2D g = (Graphics2D) g2d.create();
        g.draw(area);
        g.rotate(Math.toRadians(rotationAngle), center.getX(), center.getY());
        g.drawImage(image, (int) leftUpperCornerX, (int) leftUpperCornerY, null);
    }

    public Point2D.Double getCenter() {
        return this.center;
    }

    public void setInitialY(double initialY) {
        this.initialY = initialY;
        System.out.println(initialY);
    }

    public void animation(double ticks){
        // Update position based on physics equation
        center.y = PhysicsTool.yPositionEquation(ticks, initialY , verticalVelocity) * PIXEL_PER_METER;

        // Smooth rotation update
        updateTilt();

        // Update image animation
        imageAnimation();

        // Update hitbox
        createGeometry();
    }

    private void updateTilt() {
        double targetAngle;

        if (center.getY()/75.0 < initialY) {
            targetAngle = -25; // Tilt upwards
            rotationAngle += (targetAngle - rotationAngle) * 0.2;
        } else {
            targetAngle = 90;  // Tilt downwards
            rotationAngle += (targetAngle - rotationAngle) * 0.15;
        }

    }

    private void createGeometry(){
        leftUpperCornerY = center.getY() - (height / 2.0);
        area = new Area(new Rectangle2D.Double(leftUpperCornerX, leftUpperCornerY, width, height));
    }

    private void imageAnimation(){
        counter++;

        if (counter % 15 == 0) { // Change frame every 20 ticks (≈166ms at 120 FPS)
            frameIndex = (frameIndex + 1) % birdFrames.length;
            image = birdFrames[frameIndex]; // Switch to next frame
        }

        if (counter >= 60) { // Reset periodically to prevent overflow
            counter = 0;
        }
    }

}
