package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class MainMenu extends JFrame {

	private static final long serialVersionUID = 2L;

	int screenWidth = 400;
	int screenHeight = screenWidth / 16 * 9;

	int buttonWidth = 100;
	int buttonHeight = 40;

	JButton Play, Quit;
	JCheckBox twoPlayer;
	public MainMenu() {

		addButtons();
		addActions();

		getContentPane().setLayout(null);
		// Set buttons positions
		Play.setBounds((screenWidth - buttonWidth) / 2, 5, buttonWidth, buttonHeight);
		Quit.setBounds((screenWidth - buttonWidth) / 2, 50, buttonWidth, buttonHeight);
		twoPlayer.setBounds((screenWidth - buttonWidth) / 2, 95, buttonWidth, buttonHeight);
		

		// adding buttons and check box 
		getContentPane().add(Play);
		getContentPane().add(Quit);
		getContentPane().add(twoPlayer);

		pack(); // pack all in one piece
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(screenWidth, screenHeight);
		setTitle("Boston Pong Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}

	private void addButtons() {
		Play = new JButton("Play");
		Quit = new JButton("Quit");
		twoPlayer = new JCheckBox("Two Players?");
	}

	private void addActions() {
		Play.addActionListener(new ActionListener() { // Set action listener on Play button
			@Override
			public void actionPerformed(ActionEvent e) { // when Play is pressed:
				dispose(); // Releases all of the native screen resources used by 
						//this Window, its subcomponents, and all of its owned children
				Game game = new Game();
				
				if (twoPlayer.isSelected()) {
					game.ai.isTwoPlayer = true;
				} else {
					game.ai.isTwoPlayer = false;
				}
				game.start();;  // creates new game and start

			}
		});
		Quit.addActionListener(new ActionListener() { //Set action listener on Quit button
			@Override
			public void actionPerformed(ActionEvent e) { // When action is performed Quit button is pressed:
				System.exit(0); // Shut down the program
			}
			
		});

	}
}
