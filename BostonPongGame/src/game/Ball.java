package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball {
	
	int x, y; //ball coordinates
	int size = 16; //ball size 16x16
	int vx, vy; //velocity x and y
	int speed = 2;

	
	Rectangle boundingBox;
		
	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		vx = speed;
		vy = speed;
		
		boundingBox = new Rectangle(x, y, size, size); //create bounding box for collisions
		boundingBox.setBounds(x, y, size, size);		//set the bounding borders of that Rectangle
	}
	
	public void tick(Game game) {
		this.speed = game.player.score/5 + 2;
		
		boundingBox.setBounds(x, y, size, size);
		
		if (x <= 0) {  								//if ball is on the left border
			game.ai.score++;
			vx = speed; 							//it will goes on the right (2)
		} else if (x + size >= game.getWidth()) { //if ball is on the right border
			game.player.score++;
			vx = -speed; 							//it will goes in the left (-2)
		}
		if (y <= 0) {  								//if ball is on the top border
			vy = speed; 							//it will goes down (2)
		} else if (y + size >= game.getHeight()) { //if ball is on the bottom border
			vy = -speed;  							//it will goes up
		}
		
		x += vx;
		y += vy;
		
		paddleCollide(game); //detects for collisions
	}
	
	public void paddleCollide(Game game) {
		if (boundingBox.intersects(game.player.boundingBox )) { //if ball intersects player paddle
			vx = speed;											//it changes direction
		} else if (boundingBox.intersects(game.ai.boundingBox)) { //if ball intersects ai paddle
			if (this.x > game.ai.x) {
				vx = speed;
			} else {
			vx = -speed; //it changes direction
			}
		} else if (boundingBox.intersects(game.obstacle.boundingBox)) {
				if (this.x > game.obstacle.x) {
					vx = speed;
				} else {
				vx = -speed; //it changes direction
			}
	}
	}
	
	public void render(Graphics g) {	//Here we can put some image for the ball
		g.setColor(Color.RED);
		g.fillOval(x, y, size, size);
	}
}
