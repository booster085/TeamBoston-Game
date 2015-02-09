package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

public class Ball {
	
	private int x, y; //ball coordinates
	private int size = 16; //ball size 16x16
	private int vx, vy; //velocity x and y
	private int speed = 2;

	
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Rectangle getBoundingBox() {
		return boundingBox;
	}

	public void setBoundingBox(Rectangle boundingBox) {
		this.boundingBox = boundingBox;
	}

	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		vx = speed;
		vy = speed;
		
		boundingBox = new Rectangle(x, y, size, size); //create bounding box for collisions
		boundingBox.setBounds(x, y, size, size);		//set the bounding borders of that Rectangle
	}
	
	@SuppressWarnings("static-access")
	public void tick(Game game) {
		if (!game.isGameOver()) {
			if (game.player.getScore() + game.ai.getScore() > 9) {
				this.speed = ((game.player.getScore() + game.ai.getScore())/10 + 2); 
			}
			boundingBox.setBounds(x, y, size, size);
			
			if (x <= 0) {  								//if ball is on the left border
				game.ai.setScore(game.ai.getScore() + 1);
				Sound.POINT.play();
				vx = speed; 							//it will goes on the right (2)
			} else if (x + size >= game.getWidth()) { //if ball is on the right border
				game.player.setScore(game.player.getScore() + 1);
				Sound.POINT.play();
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
		} else {this.speed = 0;}
		
	}
	
	@SuppressWarnings("static-access")
	public void paddleCollide(Game game) {
		if (boundingBox.intersects(game.player.boundingBox )) { //if ball intersects player paddle
			Sound.HIT.play();
			vx = speed;											//it changes direction
		} else if (boundingBox.intersects(game.ai.getBoundingBox())) { //if ball intersects ai paddle
			Sound.HIT.play();
			if (this.x > game.ai.getX()) {
				vx = speed;
			} else {
			vx = -speed; //it changes direction
			}
		} else if (boundingBox.intersects(game.obstacle.getBoundingBox())) {
			Sound.HIT.play();
				if (this.x > game.obstacle.getX()) {
					vx = speed;
				} else {
				vx = -speed; //it changes direction
			}
	}
	}
	
	public void render(Graphics g) {	//Here we can put some image for the ball
		g.setColor(Color.WHITE);
		g.fillOval(x, y, size, size);
	}
}
