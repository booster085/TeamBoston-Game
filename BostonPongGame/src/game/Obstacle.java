package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Obstacle {
	
	int x, y; // paddle coordinates in the window
	int width = 15; // paddle width
	int height = 40; // paddle height
	int speed = 1;
	
	boolean isTwoPlayer = false;
	boolean goingUp = true;
	boolean goingDown = false;

	Rectangle boundingBox;

	public Obstacle(int x, int y) {
		this.x = x;
		this.y = y;

		boundingBox = new Rectangle(x, y, width, height); // create bounding box for collisions
		boundingBox.setBounds(x, y, width, height); // set the bounding borders of that Rectangle
	}

	public void tick(Game game) {

		boundingBox.setBounds(x, y, width, height);
//		
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
 	   }
//	}

	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);

	}

}
