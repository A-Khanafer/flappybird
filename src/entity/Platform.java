package entity;

import interfaces.Drawable;
import utility.ImageTools;

import java.awt.*;

public class Platform implements Drawable {
    private Image platformImage;
    private int xPosition1;
    private int xPosition2;
    private int platformWidth;

    public Platform() {
        platformImage = ImageTools.readImageAndResize("base.png", 1.0);
        platformWidth = platformImage.getWidth(null);
        xPosition1 = 0;
        xPosition2 = platformWidth - 5;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(platformImage, xPosition1, 540, null);
        g2d.drawImage(platformImage, xPosition2 , 540, null);
    }

    public void animatePlatform() {
        xPosition1 -= 1;
        xPosition2 -= 1;

        if (xPosition1 <= -platformWidth) {
            xPosition1 = xPosition2 + platformWidth -5;
        }
        if (xPosition2 <= -platformWidth) {
            xPosition2 = xPosition1 + platformWidth -5;
        }
    }
}
