package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener{

	public InputHandler(Game game) {  //constructor
		game.addKeyListener(this);
	}
	@Override
	public void keyPressed(KeyEvent e) { 	//'e' as event
		int keyCode = e.getKeyCode();
		// Player 1 controls
		if (keyCode == KeyEvent.VK_W) {  //key 'W' is for left paddle going up
			Game.player.goingUp = true;
		}
		if (keyCode == KeyEvent.VK_S) {  //key 'S' is for left paddle going down
			Game.player.goingDown = true;
		}
		// Player 2 controls
		if (keyCode == KeyEvent.VK_UP) { // key 'UP' is for right paddle going up
			Game.ai.goingUp = true;
		}
		if (keyCode == KeyEvent.VK_DOWN) { // key 'DOWN' is for right paddle going down
			Game.ai.goingDown = true;
		}
		
		// other controls
		if (keyCode == KeyEvent.VK_ESCAPE) { // key 'ESCAPE' is for program shut down
			new MainMenu();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		// Player 1 controls
		if (keyCode == KeyEvent.VK_W) {  //key 'W' is for paddle going up
			Game.player.goingUp = false;
		}
		if (keyCode == KeyEvent.VK_S) {  //key 'S' is for paddle going down
			Game.player.goingDown = false;
		}
		// Player 2 controls
		if (keyCode == KeyEvent.VK_UP) { // key 'UP' is for right paddle going up
			Game.ai.goingUp = false;
		}
		if (keyCode == KeyEvent.VK_DOWN) { // key 'DOWN' is for right paddle going down
			Game.ai.goingDown = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	
}
