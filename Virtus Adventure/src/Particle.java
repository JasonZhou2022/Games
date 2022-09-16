import java.awt.Color;
import java.util.ArrayList;

public class Particle {

	int x; int y; int velocityX = 0; int velocityY = 0;
	static ArrayList<Particle> allParticles = new ArrayList<Particle>();
	Color c;
	
	public Particle() {
		x= 375; y = 375;
		velocityX = (int)(Math.random() * 10) - 5;
		velocityY = (int)(Math.random() * 10) - 5;

		c = Color.red;
		allParticles.add(this);
	}
	public Particle(int x, int y) {
		this.x = x;
		this.y = y;
		velocityX = (int)(Math.random() * 10 - 5);
		velocityY = (int)(Math.random() * 10 - 5);
		c = Color.red;
		allParticles.add(this);
	}
	
	public Color getC() {
		return c;
	}
	public void setC(Color c) {
		this.c = c;
	}
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
	public int getVelocityX() {
		return velocityX;
	}
	public void setVelocityX(int velocityX) {
		this.velocityX = velocityX;
	}
	public int getVelocityY() {
		return velocityY;
	}
	public void setVelocityY(int velocityY) {
		this.velocityY = velocityY;
	}
	public ArrayList<Particle> getAllParticles() {
		return allParticles;
	}
	public void setAllParticles(ArrayList<Particle> allParticles) {
		this.allParticles = allParticles;
	}
	
	
	public void move() {
		
		if (c.getGreen() < 240)
			c = new Color(c.getRed(), c.getGreen() + 15, c.getBlue());

		x += velocityX;
		y += velocityY;
		
		velocityX += (int)(Math.random() * 3) - 1;
		velocityY += (int)(Math.random() * 3) - 1;

	}
	
	public static void checkForOutofBounds() {
		for (int i = 0; i < allParticles.size(); i++) {
			if (allParticles.get(i).getX() < 0 || allParticles.get(i).getX() > 750) {
				allParticles.remove(i);
				
			}	
			else if (allParticles.get(i).getY() < 0 || allParticles.get(i).getY() > 610) {
				allParticles.remove(i);
			}
				
			
		}
		
	}
}
