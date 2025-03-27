package main;

import entity.Bird;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	private Bird bird;
	private GamePanel gamePanel;
	private boolean firstTime = true;
	public KeyHandler(Bird bird, GamePanel gamePanel) {
		this.bird = bird;
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
			 if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				 if(firstTime) {
					 gamePanel.startApp();
					 firstTime = false;
				 }
				 bird.setInitialY(bird.getCenter().getY()/75.0);
				 gamePanel.setStartTime(System.nanoTime());
			 }

			 if (e.getKeyCode() == KeyEvent.VK_ESCAPE && gamePanel.isAnimationOn()) {
				 gamePanel.setAnimationOn(false);
			 }else if (e.getKeyCode() == KeyEvent.VK_ESCAPE && !gamePanel.isAnimationOn()) {
				 gamePanel.startApp();
			 }
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
