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
				 bird.setInitialY(bird.getCenter().getY()/75.0);
				 GamePanel.startTime = System.nanoTime();
		        }
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
