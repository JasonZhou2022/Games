import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Collider extends JPanel implements ActionListener{
	
	int width; int height;
	int x = 0; int y = 0;
	Color c; boolean collided = false; boolean isAttacking = false;
	int health; int direction = 0; boolean invincible = false; boolean knockback = false;
	int collisionTimer = 0; Point center; Image current; int timer = 0;
	int damage = 0; int dir = 0; //copies the direction
	
	public int getTimer() {
		return timer;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getWIDTH() {
		return width;
	}

	public Image getCurrent() {
		return current;
	}

	public void setCurrent(Image current) {
		this.current = current;
	}

	public boolean isCollided() {
		return collided;
	}

	public void setCollided(boolean collided) {
		this.collided = collided;
	}

	public boolean isAttacking() {
		return isAttacking;
	}

	public void setAttacking(boolean isAttacking) {
		this.isAttacking = isAttacking;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public boolean isInvincible() {
		return invincible;
	}

	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}

	public int getCollisionTimer() {
		return collisionTimer;
	}

	public void setTimer(int timer) {
		this.collisionTimer = timer;
	}

	public int getHEIGHT() {
		return height;
	}

   public int getXCoord() {
		return x;
	}

	public int getYCoord() {
		return y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setX(int x) {
		this.x = x;
		center.setLocation(x + width / 2, center.getY());
		
	}

	public void setY(int y) {
		this.y = y;
		center.setLocation(center.getX(), y + width / 2);
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public Collider() {
	
		c = new Color(255, 0 ,0);
		health = 20;
		
	}
	
	public Collider(int x, int y, int width, int height, int health, int direction, Color c, int damage) throws IOException {
		this.x = x;
		this.y = y;
		this.damage = damage;
		this.width = width;
		this.height = height;
		this.health = health;
		this.direction = direction;
		
		this.c = c;
		
		center = new Point((this.x + width / 2) - 10, this.y + height / 2);
	}
	
	public boolean checkForSourceCollision(pointSource source) {
		
		//left
		if (source.isCollidable()) {
			 if (Math.abs(x - source.getX()) < 5 && source.getY() > y && source.getY() < y + height) {
				 source.setVelocityX(0);
				 source.setVelocityY(0);
				 source.setTravelling(false);
				 source.setCollidable(false);
				 return true;
			 }
				
			//right
			else if (Math.abs(x + width - source.getX()) < 5 && source.getY() > y && source.getY() < y + height) {
				source.setVelocityX(0);
				source.setVelocityY(0);
				source.setTravelling(false);
				source.setCollidable(false);
				return true;
			}
				
			//top
			else if (Math.abs(y - source.getY()) < 5 && source.getX() > x && source.getX() < x + width) {
				source.setVelocityY(0);
				source.setVelocityX(0);
				source.setTravelling(false);
				source.setCollidable(false);
				return true;
			}
				
			//bottom
			else if (Math.abs(y + height - source.getY()) < 5 && source.getX() > x && source.getX() < x + width) {
				source.setVelocityY(0);
				source.setVelocityX(0);
				source.setTravelling(false);
				source.setCollidable(false);
				return true;
			}
		}

		 return false;		
	}
	
	public void checkForParticleCollision(ArrayList<Particle> arr) {
		for (int i = 0; i < arr.size(); i++) {
			//left
			if (Math.abs(x - arr.get(i).getX()) < 5 && arr.get(i).getY() > y && arr.get(i).getY() < y + height)
				arr.get(i).setVelocityX(arr.get(i).getVelocityX() * -1);
			//right
			else if (Math.abs(x + width - arr.get(i).getX()) < 5 && arr.get(i).getY() > y && arr.get(i).getY() < y + height)
				arr.get(i).setVelocityX(arr.get(i).getVelocityX() * -1);
			//top
			else if (Math.abs(y + - arr.get(i).getY()) < 5 && arr.get(i).getX() > x && arr.get(i).getX() < x + width)
				arr.get(i).setVelocityY(arr.get(i).getVelocityY() * -1);
			//bottom
			else if (Math.abs(y + height - arr.get(i).getY()) < 5 && arr.get(i).getX() > x && arr.get(i).getX() < x + width)
				arr.get(i).setVelocityY(arr.get(i).getVelocityY() * -1);
		}	
	}
	
	protected double calculate(Collider c) {

		return Math.sqrt(Math.pow(this.getCenter().getX() - c.getCenter().getX(), 2) + Math.pow(this.getCenter().getY() - c.getCenter().getY(), 2));
		
	}
	
	protected double calculate(int pX, int pY) {

		return Math.sqrt(Math.pow(Math.abs(center.getX() - pX), 2) + Math.pow(Math.abs(center.getY() - pY), 2));
		
	}
	
	public void checkForCollider(Collider c) {

		if (((this.getCenter().getX() <= 35 && this.getCenter().getY() >= 550) && calculate(c) <= 132 && c.isAttacking() && !collided)) {
			collided = true;
		}
		else if ((this.getCenter().getX() >= 685 && this.getCenter().getY() >= 550) && calculate(c) <= 143 && c.isAttacking() && !collided) {
			collided = true;
		}
		else if ((this.getCenter().getX() <= 35 && this.getCenter().getY() <= 60) && calculate(c) <= 143 && c.isAttacking() && !collided) {
			collided = true;
		}
		else if ((this.getCenter().getX() >= 685 && this.getCenter().getY() <= 60)  && !collided  && calculate(c) <= 150 && c.isAttacking() && !collided) 
			collided = true;
		else if ((int)calculate(c) < 110 && c.isAttacking() && !collided) {

			collided = true;
		}
		
	}
	public void checkForDamage(Collider c) {
		//2 = North
		//1 = East
		//-2 = South
		//-1 = West

		
		if (collided && c.isAttacking && !invincible) {
			health -= c.damage;
			invincible = true;
			knockback = true;
		}

		int kb = 5; //keep in mind that the total knock back is kb * 10
		//collisionTimer++;
		if (collided) {
			collisionTimer++;
		}
			
		
		if (knockback) { //handles the KB for the player
			
			if (c.getDirection() == 2) {
				y -= kb;
				center.setLocation(center.getX(), center.getY() - kb);
			}
			else if (c.getDirection() == 1) {
				x += kb;
				center.setLocation(center.getX() + kb, center.getY());
			}
				
			else if (c.getDirection() == -2) {
				
				y += kb;
				center.setLocation(center.getX(), center.getY() + kb);
			}
				
			else if (c.getDirection() == -1) {
				x -= kb;
				center.setLocation(center.getX() - kb, center.getY());
			}
		}
		if (collisionTimer >= 10) {
			knockback = false;
		}

		if(collisionTimer >= 25) {
			collisionTimer = 0;
			collided = false;
			invincible = false;
		}
			
			
			
		
	}
	
	public Point recalcCenter() {
		return new Point(this.x + current.getWidth(null) / 2 - 10, this.y + current.getHeight(null) / 2);
	}
	
	private int GCD(int num, int den)
	{
		if (num == 0 || den == 0)
			return num + den;
		
		return GCD(den, num % den);
	}
	
	public Point calcSlope(Collider c) {
		int x = (int)(this.center.getX() - c.center.getX());
		int y = (int)(this.center.getY() - c.center.getY());
		
		int GCD = GCD(y, x);
		
		return new Point(x / GCD, y / GCD);
	}
	
	public Point calcSlope(int x, int y) {
		int xDiff = this.x - x;
		int yDiff = this.y - y;
		
		int GCD = GCD(y, x);
		
		return new Point(xDiff / GCD, yDiff / GCD);
	}
	
	public void imageStabilizer() {
		Point nCenter = recalcCenter();
		int xDif = (int)(center.getX() - nCenter.getX());
		int yDif = (int)(center.getY() - nCenter.getY());
		x += xDif;
		y += yDif;
		nCenter = recalcCenter();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//checkForParticleCollision(Particle.allParticles);
		//repaint();
	}
	
}
