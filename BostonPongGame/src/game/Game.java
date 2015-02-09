package game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	
	public static PlayerPaddle player;
	public static AIPaddle ai;
	public static Ball ball;
	public static Obstacle obstacle;
	InputHandler IH;
	public BufferedImage buffImg = new BufferedImage(800, 450, BufferedImage.TYPE_INT_ARGB);
	JFrame frame; //game window
	public final int WIDTH = 800;
	public final int HEIGHT = WIDTH / 16 * 9; // set 16x9 wide screen
	public final Dimension GAMESIZE = new Dimension(WIDTH, HEIGHT);
	public final String TITLE = "Team Boston Pong";
	private boolean isGameOver = false;
	private boolean gameRunning = false;
	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

	public boolean isGameRunning() {
		return gameRunning;
	}

	public void setGameRunning(boolean gameRunning) {
		this.gameRunning = gameRunning;
	}

	
	
	@Override
	public void run() {

		while (gameRunning) {
			tick(); // calculates position
			render(); // draws on the screen
			
				try {
					Thread.sleep(5);
				} catch (Exception e){
					e.printStackTrace();
				}
		}

	}
	
	public synchronized void start() {  //start the game, create and starts thread
		gameRunning = true;
		new Thread(this).start();
	}
	
	public synchronized void stop() { //stop the game, stop the running thread
		gameRunning = false;
		System.exit(0);
	}

	public Game() { // constructor of the class
		frame = new JFrame();
		setMinimumSize(GAMESIZE);
		setPreferredSize(GAMESIZE);
		setMaximumSize(GAMESIZE);

		frame.add(this, BorderLayout.CENTER); // add the game canvas window in
												// the frame, at the center
		frame.pack(); // pack everything into one piece

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // set default
																// operation
																// when close to
																// exit
		frame.setVisible(true);
		frame.setResizable(false); // cannot resize game window
		frame.setTitle(TITLE);
		frame.setLocationRelativeTo(null); // sets window location in the center
											// of the screen
		player = new PlayerPaddle(0, 80); //creates new paddle object -> our player
		ai = new AIPaddle(getWidth() - 5, 80);  //creates right paddle -> AI player
		IH = new InputHandler(this); //create instance of InputHandler
		frame.addKeyListener(IH);; //add this instance to the frame, 
								  //it will be listening for keys
		ball = new Ball(getWidth()/2, 80); //create the ball
		obstacle = new Obstacle(getWidth() / 2, 80); //create obstacle -> Obstacle
		try {
			buffImg = ImageIO.read(new File("src/game/images/back1.jpg")); //load background image
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sound.MUSIC.loop();

	}

	public void tick() {  //update state
		player.tick(this); //update player position 
		ai.tick(this); //updates AI player position
		ball.tick(this); //updates ball position
		obstacle.tick(this); //updates obstacle position
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3); // set 3 buffers
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		// draw graphics on the screen, starts at 0, 0 - top left, high and wide
		// as much as the frame
		g.drawImage(buffImg, 0, 0, getWidth(), getHeight(), null); //draws background
		g.setColor(Color.WHITE); //set player score text color
		g.setFont(new Font("Arial", Font.PLAIN, 16));
		g.drawString("score: " + player.getScore(), 20, 25); //draw scores 
		g.drawString("score: " + ai.getScore(), getWidth() - 80, 25);
		if (player.getScore() == 30 || ai.getScore() == 30 ) {
			g.setFont(new Font("Arial", Font.BOLD, 48));
			String win = (player.getScore() > ai.getScore() ? "player 1" : "player 2") + " wins";
			isGameOver = true;
			if (isGameOver) {
				Sound.MUSIC.stop();
			}
			g.drawString("GAME OVER", 270, 140);
			g.drawString(win, 270, 200);
		}
		player.render(g); //draws left paddle
		ai.render(g); //draws right paddle
		ball.render(g); //draws the ball
		obstacle.render(g); //draws obstacle
		
		bs.show(); // shows the next available buffer
		g.dispose(); // releases the drew graphics instead of garbage collector
	}

}
