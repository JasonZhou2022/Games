import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class pointSource implements ActionListener {

	int x = 0; int y = 0; int radius; int velocityX; int velocityY;
	Particle[] particles;
	Timer timer; int direction; boolean travelling = true; boolean collidable = true;
	int xDir = 0; int angle;
	//static ArrayList<pointSource> allSources = new ArrayList<pointSource>();
	
	pointSource(int x, int y, int r, int vX, int vY, int dir) {
		
		direction = dir;
		xDir = dir;
		this.x = x;
		this.y = y;
		radius = r;
		
		velocityX = vX;
		velocityY =vY;
		
		//finding angle based on velocity, then converting radians into degrees
		double preAngle = (Math.atan2(vY, vX));
		preAngle *= 180;
		preAngle /= Math.PI;
		
		angle = (int)preAngle;
		
		timer = new Timer(100, this);
		timer.start();
		
		particles = new Particle[100];
		
		for (int i = 0; i < particles.length; i++)
			particles[i] = new Particle(x,y);
			//particles.add(new Particle(x, y));
		
		//allSources.add(this);
	}
	
	private double calculate(int pX, int pY) {
		
		return Math.sqrt(Math.pow(Math.abs(x - pX), 2) + Math.pow(Math.abs(y - pY), 2));
		
	}
	
	private void kill() {
		for (int i = 0; i < particles.length; i++) {
			
			if (calculate(particles[i].getX(), particles[i].getY()) >= radius) {
				
				particles[i].setC(new Color(0,0,0,0));
				particles[i] = new Particle(this.x, this.y);
			}
			
		}
	}
	
	public void concentrate() {
		
		for (int i = 0; i < particles.length; i++) {
		
			if (calculate(particles[i].getX(), particles[i].getY()) >= radius) {
				
				particles[i].setC(new Color(0,0,0,0));
				particles[i] = new Particle();
				//particles.remove(i);
				//particles.add(new Particle());
				
			}
			
		}
		
	}
	
	private void moveSource() {
		x += velocityX;
		for (int i = 0; i < particles.length; i++)
			particles[i].setX(particles[i].getX() + velocityX / 2);	
			
		y += velocityY;
		for (int i = 0; i < particles.length; i++)
			particles[i].setY(particles[i].getY() + velocityY / 2);	
	}
	
	public void travel() {
		//2 = North
		//1 = East
		//-2 = South
		//-1 = West
		
		if (travelling) {
			kill();
			moveSource();
			for (int i = 0; i < particles.length; i++) {

				particles[i].move();
				particles[i].setVelocityY((int)(Math.random() * getVelocityY()) - (getVelocityY() / 2));
				particles[i].setVelocityX((int)(Math.random() * getVelocityX()) - (getVelocityX() / 2));
			}
		}
		else {
			for (int i = 0; i < particles.length; i++) {
				if (particles[i] != null) {
					particles[i].move();
					if (particles[i].getC().getAlpha() >= 21)
						particles[i].setC(new Color(particles[i].getC().getRed(), particles[i].getC().getGreen(), particles[i].getC().getBlue(), particles[i].getC().getAlpha() - 20));
				}
				
			}
		}
	}
	/*
	 * public static void checkForOutofBounds() {
		for (int i = 0; i < allSources.size(); i++) {
			if (!allSources.get(i).isCollidable() && allSources.get(i).getParticles()[0].getC().getAlpha() < 30) {
				
				for (int j = 0; j < allSources.get(i).getParticles().length; j++)
					allSources.get(i).getParticles()[i] = null;
				allSources.remove(i);
				return;
			}
				
			if (allSources.get(i).getX() < 0 || allSources.get(i).getX() > 750) {
				
				for (int j = 0; j < allSources.get(i).getParticles().length; j++)
					allSources.get(i).getParticles()[j] = null;
				allSources.get(i).setParticles(null);
				allSources.remove(i);
				return;
			}
			if (allSources.get(i).getY() < 0 || allSources.get(i).getY() > 610) {
				
				for (int j = 0; j < allSources.get(i).getParticles().length; j++)
					allSources.get(i).getParticles()[j] = null;
				allSources.get(i).setParticles(null);
				allSources.remove(i);
				
				return;
			}
				
		}
	}
	 */
	

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getRadius() {
		return radius;
	}


	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Particle[] getParticles() {
		return particles;
	}

	public void setParticles(Particle[] particles) {
		this.particles = particles;
	}

	public int getVelocityX() {
		return velocityX;
	}

	public int getVelocityY() {
		return velocityY;
	}

	public void setVelocityX(int velocityX) {
		this.velocityX = velocityX;
	}

	public void setVelocityY(int velocityY) {
		this.velocityY = velocityY;
	}
	
	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public boolean isTravelling() {
		return travelling;
	}

	public void setTravelling(boolean travelling) {
		this.travelling = travelling;
	}
	
	public boolean isCollidable() {
		return collidable;
	}

	public void setCollidable(boolean collidable) {
		this.collidable = collidable;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//y += 1;
		
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	
	
}
