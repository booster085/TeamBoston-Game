package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PlayerPaddle {
	
	private int x, y; //paddle coordinates in the window
	private int width = 15; //paddle width
	private int height = 100; //paddle height
	
	private boolean goingUp = false;
	private boolean goingDown = false;	
	private int speed = 3;
	private int score = 0;
	
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
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

	public void setGoingDown(boolean goingDown) {
		this.goingDown = goingDown;
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

	public Rectangle getBoundingBox() {
		return boundingBox;
	}

	public void setBoundingBox(Rectangle boundingBox) {
		this.boundingBox = boundingBox;
	}

	Rectangle boundingBox;

	public PlayerPaddle(int x, int y) {
		this.x = x;
		this.y = y;
		
		boundingBox  = new Rectangle(x, y, width, height); //create bounding box for collisions
		boundingBox.setBounds(x, y, width, height); //set the bounding borders of that Rectangle
	}
	
	public void tick(Game game) {
		if (!game.isGameOver()) {
			boundingBox.setBounds(x, y, width, height);
			if (game.player.score + game.ai.getScore() > 29) {
				this.speed = ((game.player.score + game.ai.getScore())/15 + 2); 
			}
			
			if (goingUp && y > 0) {
				y -= speed;
			}
			if (goingDown && y < game.getHeight() - height) {
				y += speed;
			}
		} else {this.speed = 0;}
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, width, height);
		
	}
}
