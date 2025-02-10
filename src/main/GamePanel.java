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
        this.keyHandler = new KeyHandler(this.bird , this);
        this.addKeyListener(this.keyHandler);
        setBackground(Color.GRAY);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        background = ImageTools.readImageAndResize("background-day.png",1.7);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(background, 0, -125, null);
        bird.draw(g2d);
        platform.draw(g2d);
    }




    public void run() {
        long lastUpdateTime = System.nanoTime();
        long targetTime = 1000000000 / 60; // 60 FPS, adjust if you need a different frame rate
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

            // Only repaint if necessary to avoid unnecessary redraws
            long sleepTime = targetTime - (currentTime - lastUpdateTime);
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime / 1000000); // sleep for the remaining time in milliseconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            repaint(); // Call repaint less often, controlled by the sleep time
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
