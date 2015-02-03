package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PlayerPaddle {
	
	int x, y; //paddle coordinates in the window
	int width = 15; //paddle width
	int height = 130; //paddle height
	
	boolean goingUp = false;
	boolean goingDown = false;	
	int speed = 2;
	int score = 0;
	
	Rectangle boundingBox;

	public PlayerPaddle(int x, int y) {
		this.x = x;
		this.y = y;
		
		boundingBox  = new Rectangle(x, y, width, height); //create bounding box for collisions
		boundingBox.setBounds(x, y, width, height); //set the bounding borders of that Rectangle
	}
	
	public void tick(Game game) {
		
		boundingBox.setBounds(x, y, width, height);
		
		if (goingUp && y > 0) {
			y -= speed;
		}
		if (goingDown && y < game.getHeight() - height) {
			y += speed;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
		
	}
}
