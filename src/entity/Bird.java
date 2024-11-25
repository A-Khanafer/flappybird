package entity;

import interfaces.Drawable;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public class Bird implements Drawable {

    private double x;
    private double y;
    private double width;
    private double height;
    private Image image;
    private Area area;

    public Bird(int x, int y, int width, int height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
        area = new Area(new Rectangle2D.Double(x, y, width, height));
    }

    @Override
    public void draw(Graphics2D g2d) {

    }


}
