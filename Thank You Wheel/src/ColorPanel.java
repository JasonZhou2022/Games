import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ColorPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image wheel;
	Image ticker;
	int wheelRadius = 240;
	int triangleHalfWidth = 50;
	int triangleHalfHeight = 46;
	private double startDegree = 0;
	private double startRemainder = 0;
	private double degree = 0;
	private boolean spin = false;
	ArrayList<String> messages;
	int messageIndex = -1;
	
	public ColorPanel() throws IOException {
		
		this.setBounds(0, 0, 800, 800);
		this.setBackground(Color.pink);
		
		startDegree = (int)(Math.random() * 360); //randomizes starting position of wheel
		startRemainder = startDegree % 30;
		startDegree -= startRemainder;
		
		wheel = ImageIO.read(new File("Wheel.png"));
		ticker = ImageIO.read(new File("Ticker.png"));
		
		messages = new ArrayList<>();
		messages.add("Thank You for Teaching Me How To Drive!"); //0
		messages.add("Thank You for Helping Me Make My Computer!"); //1
		messages.add("Thank You for Getting Me Into Running!"); //2
		messages.add("Thank You for Being My Dad!"); //3
		messages.add("Thank You for Showing Me UMich!"); //4
		messages.add("Thank You for Helping Me \nApply to My First Job!"); //5
		messages.add("Thank You for Listening \nto My Bullshit Everyday!"); //6
		messages.add("Thank You for Planning All of Our Vacations!"); //7
		messages.add("Thank You for Answering All of My Questions!"); //8
		messages.add("Thank You for Helping Me Deal with My Eczema!"); //9
		messages.add("Thank You for Coming to \nMy Races and Track Meets!"); //10
		messages.add("Thank You for Always Being There For Me!"); //11
		
	}
	
	private void drawString(Graphics g, String text, int x, int y) {
        int lineHeight = g.getFontMetrics().getHeight();
        for (String line : text.split("\n"))
            g.drawString(line, x, y += lineHeight);
    }
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g); //calls super
		
		Font font = new Font("Monospaced", Font.BOLD, 30); //creates a font object for the string
		g.setFont(font); //sets the font
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(400, 400);
		
		if (messageIndex == -1) {
			g.setColor(Color.white);
			g.drawString("Happy Father's Day!", -150, -325);
			g.setColor(Color.black);
			//wheel
			g2d.rotate(Math.toRadians(startDegree + degree));
			g2d.setColor(Color.black);
			g2d.drawRect(0, 0, 10, 10);
			g.drawImage(wheel, -1 * wheelRadius, -1 * wheelRadius, null);
			
			//ticker
			double remainder = 360 - degree;
			double startRemainder = 360 - startDegree;
			g2d.rotate(Math.toRadians(remainder + startRemainder)); //stabilize rotation to keep drawings stationary
			g2d.drawImage(ticker, -1* triangleHalfWidth, -1 * wheelRadius - 50, null);
			
			//spin box
			//drawing already stabilized (only need to run line 55 once to stabilize)
			g.setColor(Color.blue);
			g2d.fillRect(-50, 300, 100, 50);
			g.setColor(Color.white);
			g2d.drawString("Spin!", -45, 335);
		}
		else
			g2d.setColor(Color.white);
		
		if (messageIndex == 0)
			g2d.drawString(messages.get(messageIndex), -340, 0);
		else if (messageIndex == 1)
			g2d.drawString(messages.get(messageIndex), -375, 0);
		else if (messageIndex == 2)
			g2d.drawString(messages.get(messageIndex), -340, 0);
		else if (messageIndex == 3)
			g2d.drawString(messages.get(messageIndex), -225, 0);
		else if (messageIndex == 4)
			g2d.drawString(messages.get(messageIndex), -270, 0);
		else if (messageIndex == 5)
			 drawString(g, messages.get(messageIndex), -200, -50);
			//g2d.drawString(messages.get(messageIndex), -375, 0);
		else if (messageIndex == 6)
			drawString(g, messages.get(messageIndex), -200, -50);
		else if (messageIndex == 7)
			g2d.drawString(messages.get(messageIndex), -385, 0);
		else if (messageIndex == 8)
			g2d.drawString(messages.get(messageIndex), -385, 0);
		else if (messageIndex == 9)
			g2d.drawString(messages.get(messageIndex), -395, 0);
		else if (messageIndex == 10)
			drawString(g, messages.get(messageIndex), -205, -50);
		else if (messageIndex == 11)
			g2d.drawString(messages.get(messageIndex), -350, 0);
		
		
		
	}
	
	public double getDegree() {
		return degree;
	}
	
	public void setDegree(double Degree) {
		degree = Degree;
	}
	
	public double getStartDegree() {
		return startDegree;
	}
	
	public void setStartDegree(double start) {
		startDegree = start;
	}
	
	public boolean getSpin() {
		return spin;
	}
	
	public void setSpin(boolean s) {
		spin = s;
	}
	
	public ArrayList<String> getList() {
		return messages;
	}
	
	public void setList(ArrayList<String> arr) {
		messages = arr;
	}
	
	public int getMessageIndex() {
		return messageIndex;
	}
	
	public void setMessageIndex(int index) {
		messageIndex = index;
	}
	

	
}
