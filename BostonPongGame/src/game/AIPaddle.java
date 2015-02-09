package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class AIPaddle {
	private int x, y; // paddle coordinates in the window
	private int width = 15; // paddle width
	private int height = 100; // paddle height
	private int speed = 1;
	private int score = 0;

	private boolean isTwoPlayer = false;
	private boolean goingUp = false;
	private boolean goingDown = false;

	private Rectangle boundingBox;
	

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isTwoPlayer() {
		return isTwoPlayer;
	}

	public void setTwoPlayer(boolean isTwoPlayer) {
		this.isTwoPlayer = isTwoPlayer;
	}

	public boolean isGoingUp() {
		return goingUp;
	}

	public void setGoingUp(boolean goingUp) {
		this.goingUp = goingUp;
	}

	public boolean isGoingDown() {
		return goingDown;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setGoingDown(boolean goingDown) {
		this.goingDown = goingDown;
	}

	public Rectangle getBoundingBox() {
		return boundingBox;
	}

	public void setBoundingBox(Rectangle boundingBox) {
		this.boundingBox = boundingBox;
	}

	public AIPaddle(int x, int y) {
		this.x = x;
		this.y = y;

		boundingBox = new Rectangle(x, y, width, height); // create bounding box for collisions
		boundingBox.setBounds(x, y, width, height); // set the bounding borders of that Rectangle
	}

	public void tick(Game game) {
		

		boundingBox.setBounds(x, y, width, height);
		if (!game.isGameOver()) {
			if (!isTwoPlayer) {  // if two player is no checked AI controls the paddle
				if (game.player.getScore() + game.ai.score > 7) {
					this.speed = ((game.player.getScore() + game.ai.score)/15 + 2); 
				}
				if (game.ball.getY() < y + height/2 && y >= 0) {
					// if the paddle is under the ball and is in the screen
					y -= speed; // paddle goes up
				} else if (game.ball.getY() > y + height/2 && y + height <= game.getHeight()){
					// if paddle is over the ball and in the screen
					y += speed; // it goes down
				}
			} else { // if two players is checked paddle is under 2nd player control
				this.speed = 3;
				if (goingUp && y > 0) { 
					y -= speed;
				}
				if (goingDown && y < game.getHeight() - height) {
					y += speed;
				}
			}	
		} else {this.speed = 0;}
		
	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);

	}
}
