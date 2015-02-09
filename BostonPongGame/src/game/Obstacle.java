package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Obstacle {

	private int x, y; // paddle coordinates in the window
	private int width = 15; // paddle width
	private int height = 40; // paddle height
	private int speed = 1;

	private boolean isTwoPlayer = false;
	private boolean goingUp = true;
	private boolean goingDown = false;

	private Rectangle boundingBox;

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

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
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

	public void setGoingDown(boolean goingDown) {
		this.goingDown = goingDown;
	}

	public Rectangle getBoundingBox() {
		return boundingBox;
	}

	public void setBoundingBox(Rectangle boundingBox) {
		this.boundingBox = boundingBox;
	}

	public Obstacle(int x, int y) {
		this.x = x;
		this.y = y;

		boundingBox = new Rectangle(x, y, width, height); // create bounding box
															// for collisions
		boundingBox.setBounds(x, y, width, height); // set the bounding borders
													// of that Rectangle
	}

	@SuppressWarnings("static-access")
	public void tick(Game game) {
		if (!game.isGameOver()) {
			if ((game.player.getScore() + game.ai.getScore() + 1) % 10 == 0
					&& this.height <= 200) {
				this.height = 60 * ((game.player.getScore() + game.ai.getScore() + 1) / 10);
			}

			boundingBox.setBounds(x, y, width, height);
			if (goingUp && y >= 0) {
				y -= speed;
				if (y == 0) {
					goingUp = false;
					goingDown = true;
				}
			} else if (goingDown && y <= game.getHeight() - height) {
				if (y == game.getHeight() - height) {
					goingUp = true;
					goingDown = false;
				}
				y += speed;
			}
		} else {
			this.speed = 0;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);

	}

}
