package main;

import entity.Bird;
import entity.Platform;
import obstacles.CylinderList;
import utility.ImageTools;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable {

    private long startTime;
    private boolean animationOn = false;
    private final Bird bird;
    private CylinderList cylinders;
    private final Image background;
    private final Platform platform;


    public GamePanel(Bird bird) {
        this.bird = bird;
        platform = new Platform();
        KeyHandler keyHandler = new KeyHandler(this.bird, this);
        this.addKeyListener(keyHandler);
        setBackground(Color.GRAY);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        background = ImageTools.readImageAndResize("background-day.png",1.7);
        cylinders = new CylinderList();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(background, 0, -125, null);
        cylinders.draw(g2d);
        bird.draw(g2d);
        platform.draw(g2d);
    }

    public void run() {

        long lastUpdateTime = System.nanoTime();
        long targetTime = 1000000000 / 120;
        double targetDelta = 0.001;

        while (animationOn) {
            collisionCheck();
            long currentTime = System.nanoTime();
            double elapsedTime = (currentTime - lastUpdateTime) / 1_000_000_000.0;

            double seconds = ((currentTime - startTime) / 1_000_000_000.0);

            if (elapsedTime >= targetDelta) {
                cylinders.animate();
                animateBird(seconds);
                animatePlatform();
                lastUpdateTime = currentTime;
            }

            long sleepTime = targetTime - (currentTime - lastUpdateTime);
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime / 1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            repaint();
        }
    }

    private void collisionCheck() {
        if(bird.contains(555))
            animationOn = false;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    private void animateBird(double ticks){
        bird.animation(ticks);
    }

    private void animatePlatform(){
        platform.animatePlatform();
    }

    public boolean isAnimationOn() {
        return animationOn;
    }

    public void setAnimationOn(boolean animationOn) {
        this.animationOn = animationOn;
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
