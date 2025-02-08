package main;

import entity.Bird;
import entity.Platform;
import obstacles.Cylinders;
import utility.ImageTools;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    public static long startTime;
    public static double seconds;
    private boolean animationOn = false;
    private Bird bird;
    private KeyHandler keyHandler ;
    private ArrayList<Cylinders> cylinders;
    private boolean initialised = false;
    private Image background;
    private Platform platform;


    public GamePanel(Bird bird) {
        this.bird = bird;
        platform = new Platform();
        this.keyHandler = new KeyHandler(this.bird);
        this.addKeyListener(this.keyHandler);
        setBackground(Color.GRAY);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        background = ImageTools.readImageAndResize("background.png", 1.4);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(background, 0, 0, null);
        bird.draw(g2d);
        platform.draw(g2d);
    }




    public void run() {
        long lastUpdateTime = System.nanoTime();
        double targetDelta = 0.01;

        while (animationOn) {
            long currentTime = System.nanoTime();
            double elapsedTime = (currentTime - lastUpdateTime) / 1_000_000_000.0;


            seconds = ((currentTime - startTime) / 1_000_000_000.0);
            animateBird(seconds);


            if (elapsedTime >= targetDelta) {
                animatePlatform();
                lastUpdateTime = currentTime;
            }

            repaint();
        }
    }

    private void animateBird(double ticks){
        bird.animation(ticks);
    }

    private void animatePlatform(){
        platform.animatePlatform();
    }

    public void startApp() {

        if (!animationOn) {
            Thread processAnimation = new Thread(this);
            startTime = System.nanoTime();
            processAnimation.start();
            animationOn = true;
        }
    }

}
