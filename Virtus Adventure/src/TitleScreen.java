import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class TitleScreen { /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//title screen for the game

	JPanel panel;
	
	public TitleScreen() {
		panel = new JPanel();
//		this.setBounds(0, 0, 640, 480);
//		this.setBackground(Color.blue);
	}
	
	public void start(Graphics g) { //creates the start screen for the game
		//this.setBackground(Color.red);
		g.setColor(Color.blue); //sets the color to red
		Font font = new Font("Monospaced", Font.PLAIN, 50); //creates a font object for the string
		g.setFont(font); //sets the font
		//TITLE
		g.drawString("Escape Room", 100, 100); //draws a string
		//WHITE PLAY BUTTON
		g.setColor(Color.white); 
		g.fillRect(175, 225, 400, 100); //creates a box for the play button
		g.fillRect(175, 375, 400, 100); //creates a box for the play button
		font = new Font("Monospaced", Font.PLAIN, 10); //new font
		g.setColor(Color.black);
		g.drawString("Play", 310, 290); //draws a string
		g.drawString("Controls", 260, 440);
		//WHITE QUIT BUTTON
		g.setColor(Color.white); //sets the color to white
		g.fillRect(175, 525, 400, 100); //creates a box for the quit button
		g.setColor(Color.black); //sets the color black
		g.drawString("Quit", 310, 590); //draws a string
	}
	
}
