package utility;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * This class contains utilities for image processing.
 * Note the methods that allow resizing an image.
 *
 * @author Ahmad Khanafer
 */
public class ImageTools {

    /**
     * Reads the image file given as a parameter and returns
     * a corresponding Image object.
     *
     * @param fileName The name of the image file
     * @return An Image object for this image
     */
    public static Image readImage(String fileName) {
        Image img = null;
        URL fileURL = ImageTools.class.getClassLoader().getResource(fileName);
        if (fileURL == null) {
            JOptionPane.showMessageDialog(null, "Image file not found: " + fileName);
        } else {
            try {
                img = ImageIO.read(fileURL);
            } catch (IOException e) {
                System.out.println("Error reading the image file: " + fileName);
            }
        }
        return img;
    } // end method

    /**
     * Reads the image file given as a parameter, resizes
     * the image by applying the same scaling factor for both width and height
     * (which avoids any distortion in the image).
     *
     * Returns a corresponding Image object.
     *
     * See also the second signature of this method,
     * which allows specifying precise resolutions for width and height.
     *
     * @param fileName The name of the image file
     * @param zoomFactor Scaling factor (1 means no change)
     * @return An Image object for this resized image
     */
    public static Image readImageAndResize(String fileName, double zoomFactor) {
        Image img = null;
        Image resizedImg = null;

        img = readImage(fileName);
        if (img != null) {
            resizedImg = img.getScaledInstance(
                    (int) (zoomFactor * img.getWidth(null)),
                    (int) (zoomFactor * img.getHeight(null)),
                    Image.SCALE_SMOOTH);
        }
        return resizedImg;
    } // end method

    /**
     * Reads the image file given as a parameter, resizes
     * the image to the new desired resolution.
     *
     * Returns a corresponding Image object.
     *
     * Note: if resoX and resoY are not proportional to the initial dimensions of
     * the image, it introduces distortion (will appear more stretched in one direction).
     * See also the second signature of this method which allows specifying a scaling factor instead.
     *
     * @param fileName The name of the image file
     * @param resoX New width in pixels
     * @param resoY New height in pixels
     * @return An Image object for this resized image
     */
    public static Image readImageAndResize(String fileName, int resoX, int resoY) {
        Image img = null;
        Image resizedImg = null;

        img = readImage(fileName);
        if (img != null) {
            resizedImg = img.getScaledInstance(resoX, resoY, Image.SCALE_SMOOTH);
        }
        return resizedImg;
    }

    /**
     * Associates an image with a button by appropriately resizing the image.
     *
     * @param fileName The name of the image file
     * @param button The button to which the image will be associated
     */
    public static void readImageAndPlaceOnButton(String fileName, JButton button) {
        Image resizedImg = null;

        resizedImg = readImageAndResize(fileName, button.getWidth(), button.getHeight());

        if (resizedImg != null) {
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);

            button.setText("");
            button.setIcon(new ImageIcon(resizedImg));
            button.setBorderPainted(true);

            resizedImg.flush();
        }
    } // end method
}
