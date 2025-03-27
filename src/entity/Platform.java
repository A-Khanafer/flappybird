package entity;

import interfaces.Drawable;
import utility.ImageTools;

import java.awt.*;

public class Platform implements Drawable {
    private final Image platformImage;
    private int xPosition1;
    private int xPosition2;
    private int xPosition3;
    private final int platformWidth;

    public Platform() {
        platformImage = ImageTools.readImageAndResize("base.png", 1.0);
        platformWidth = platformImage.getWidth(null);
        System.out.println("platformWidth = " + platformWidth);
        xPosition1 = 0;
        xPosition2 = platformWidth;
        xPosition3 = platformWidth * 2;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(platformImage, xPosition1, 560, null);
        g2d.drawImage(platformImage, xPosition2 , 560, null);
        g2d.drawImage(platformImage, xPosition3 , 560, null);
    }

    public void animatePlatform() {
        xPosition1 -= 2;
        xPosition2 -= 2;
        xPosition3 -= 2;

        if (xPosition1 <= -platformWidth) {
            xPosition1 = xPosition3 + platformWidth;
        }
        if (xPosition2 <= -platformWidth) {
            xPosition2 = xPosition1 + platformWidth;
        }
        if (xPosition3 <= -platformWidth) {
            xPosition3 = xPosition2 + platformWidth;
        }
    }
}
