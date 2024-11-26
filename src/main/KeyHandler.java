package main;

import entity.Bird;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	Bird bird;

	public KeyHandler(Bird bird) {
		this.bird = bird;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
			 if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				 bird.setVerticalVelocity(-2.94);
				 GamePanel.startTime = System.nanoTime();
				 System.out.println(GamePanel.seconds);
				 System.out.println("jayz");
		        }
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
