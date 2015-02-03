package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class AIPaddle {
	int x, y; // paddle coordinates in the window
	int width = 15; // paddle width
	int height = 100; // paddle height
	int speed = 3;
	int score = 0;

	boolean isTwoPlayer = false;
	boolean goingUp = false;
	boolean goingDown = false;

	Rectangle boundingBox;

	public AIPaddle(int x, int y) {
		this.x = x;
		this.y = y;

		boundingBox = new Rectangle(x, y, width, height); // create bounding box for collisions
		boundingBox.setBounds(x, y, width, height); // set the bounding borders of that Rectangle
	}

	public void tick(Game game) {
		

		boundingBox.setBounds(x, y, width, height);
		if (!isTwoPlayer) {  // if two player is no checked AI controls the paddle
			if (game.player.score > 8 || game.ai.score > 8) {
				this.speed = ((game.player.score + game.ai.score)/10 + 1); 
			}
			if (game.ball.y < y + height/2 && y >= 0) {
				// if the paddle is under the ball and is in the screen
				y -= speed; // paddle goes up
			} else if (game.ball.y > y + height/2 && y + height <= game.getHeight()){
				// if paddle is over the ball and in the screen
				y += speed; // it goes down
			}
		} else { // if two players is checked paddle is under 2nd player control
			if (goingUp && y > 0) { 
				y -= speed;
			}
			if (goingDown && y < game.getHeight() - height) {
				y += speed;
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);

	}
}
