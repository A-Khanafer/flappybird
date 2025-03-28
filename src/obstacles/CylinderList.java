package obstacles;

import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class CylinderList {
    private final CopyOnWriteArrayList<Cylinder> cylinders = new CopyOnWriteArrayList<>();
    private final int spawnX = 500; // Initial spawn position
    private final int minSpacing = 250; // Minimum spacing between cylinders
    private final Random rand = new Random();

    public CylinderList() {
        // Start with two cylinders at different positions
        cylinders.add(new Cylinder(spawnX));
        cylinders.add(new Cylinder(spawnX + minSpacing ));
    }

    public void animate() {
        // Ensure two cylinders are present at different positions
        if (cylinders.size() <= 2) {
            int lastX = cylinders.isEmpty() ? spawnX : cylinders.getLast().getX();
            cylinders.add(new Cylinder(lastX + minSpacing)); // Vary spacing slightly
        }
        for (Cylinder cylinder : cylinders) {
            cylinder.animate();
            if (cylinder.getX() + cylinder.getWidth() < 0) {
                cylinders.remove(cylinder); // No ConcurrentModificationException
            }
        }


    }


    public void draw(Graphics2D g2d) {
        for (Cylinder cylinder : cylinders) {
            cylinder.draw(g2d);
        }
    }
}