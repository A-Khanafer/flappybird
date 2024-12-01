package main;

import entity.Bird;
import obstacles.Cylinders;
import utility.ImageTools;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    public static long startTime;
    public static double seconds;
    private boolean animationOn = false;
    private Bird bird;
    private KeyHandler keyHandler ;
    private ArrayList<Cylinders> cylinders;


    public GamePanel(Bird bird) {
        this.bird = bird;
        this.keyHandler = new KeyHandler(this.bird);
        this.addKeyListener(this.keyHandler);
        setBackground(Color.GRAY);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }


    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.YELLOW);


        g2d.fill(new Rectangle2D.Double(0, 600, getWidth(), getHeight()));
        g2d.drawImage(ImageTools.readImageAndResize("topPlatform.png" , 2.0), 0,588 ,null);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        bird.draw(g2d);
    }


    @Override
    public void run() {
        while (animationOn) {
            seconds = ((System.nanoTime() - startTime)/1_000_000_000.0);
            animateBird(seconds);
            repaint();
        }
    }

    private void animateBird(double ticks){
        bird.animation(ticks);
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
