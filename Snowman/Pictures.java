import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Pictures extends JPanel{
	private Color c;
	private int circleCount;
	private int flakeCount = 0;
	private int[][] flakes;
	
	public Pictures(Color color) {
		c = color;
		this.setBackground(c);
		circleCount = 5;
		flakeCount = 100;
		flakes = new int[flakeCount][2];
		for (int i = 0; i < flakeCount; i++)
		{
			flakes[i][0] = (int)(Math.random() * 600);
			flakes[i][1] = -(int)(Math.random() * 600);
		}
	} 
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for (int i = 0; i < circleCount; i++)
			g.drawOval(100, 200 + 50*i, 60, 60);	//draws an 'oval'
		
		g.setColor(Color.green); //sets the color that the program will draw in
		g.fill3DRect(300, 50, 100, 200, true); //makes a rectangle
		
		drawSnowFlakes(g);
		g.setFont(new Font("Arial", Font.ITALIC, 40));
		g.drawString("Happy Holidays", 300, 300);
	}
	
	public void drawSnowFlakes(Graphics g)
	{
		g.setColor(Color.white);
		for (int i = 0; i < flakeCount; i++)
		{
			int randomX = flakes[i][0];
			int randomY = flakes[i][1];
			
			g.drawLine(randomX - 3, randomY, randomX + 3, randomY); //horizontal line
			g.drawLine(randomX, randomY - 3, randomX, randomY + 3); //vertical line
			g.drawLine(randomX - 3, randomY - 3, randomX + 3, randomY + 3); //top left to bottom right, diagonal
			g.drawLine(randomX + 3, randomY - 3, randomX - 3, randomY + 3); //top right to bottom left, diagonal
			flakes[i][1] += 3;
			flakes[i][0] += (Math.random() * 5 - 2);
			
			//if (flakes[i][0] > 600)
				//flakes[i][0] = 0;
		}
			
	}
}
