import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Boss extends Collider{
	Image[] standingImages; Player player; Image[] leftSwing; Image[] rightSwing;
	Image[] leftSideSwing; Image[] rightSideSwing; Image[] leftFrontSwing; Image[] rightFrontSwing;
	boolean shortRange = false; int timer = 0; Image[] leftWalk; Image[] rightWalk; Image[] backWalk; Image[] frontWalk; 
	int left = 0; int right = 0; int back = 0; int front; int phase = 0; boolean targ = true; int classTimer = 0; boolean atMiddle = false;
	boolean melee = true; ArrayList<pointSource> fireballs; int cap = (int)(Math.random() * 30 + 20); Image skull;
	public Boss() throws IOException {
		//int x, int y, int width, int height, Color c
		super(375, 0, 256, 256, 100, -2, Color.red, 2);
		fireballs = new ArrayList<pointSource>();
		standingImages = new Image[4];
		
		skull = ImageIO.read(new File("skull.png"));
		
		standingImages[0] = ImageIO.read(new File("Malum.png"));
		standingImages[1] = ImageIO.read(new File("MalumBack.png"));
		standingImages[2] = ImageIO.read(new File("MalumRightSide.png"));
		standingImages[3] = ImageIO.read(new File("MalumLeftSide.png"));
		
		leftWalk = new Image[3];
		rightWalk = new Image[3];
		backWalk = new Image[2];
		frontWalk = new Image[2];
		
		frontWalk[0] = ImageIO.read(new File("MalumFrontWalk1.png"));
		frontWalk[1] = ImageIO.read(new File("MalumFrontWalk2.png"));
		backWalk[0] = ImageIO.read(new File("MalumBackWalk1.png"));
		backWalk[1] = ImageIO.read(new File("MalumBackWalk2.png"));
		rightWalk[0] = ImageIO.read(new File("MalumRightWalk1.png"));
		rightWalk[1] = ImageIO.read(new File("MalumRightWalk2.png"));
		rightWalk[2] = ImageIO.read(new File("MalumRightWalk3.png"));
		leftWalk[0] = ImageIO.read(new File("MalumLeftWalk1.png"));
		leftWalk[1] = ImageIO.read(new File("MalumLeftWalk2.png"));
		leftWalk[2] = ImageIO.read(new File("MalumLeftWalk3.png"));
		
		leftSwing = new Image[2];
		rightSwing = new Image[2];
		
		rightSwing[0] = ImageIO.read(new File("MalumRightSwing1.png"));
		rightSwing[1] = ImageIO.read(new File("MalumRightSwing1.png"));
		leftSwing[0] = ImageIO.read(new File("MalumLeftSwing1.png"));
		leftSwing[1] = ImageIO.read(new File("MalumLeftSwing2.png"));
		
		leftSideSwing = new Image[2]; 
		rightSideSwing = new Image[2]; 
		leftFrontSwing = new Image[2]; 
		rightFrontSwing = new Image[2];
		
		leftSideSwing[0] = ImageIO.read(new File("MalumLeftSideSwing1.png"));
		leftSideSwing[1] = ImageIO.read(new File("MalumLeftSideSwing2.png"));
		rightSideSwing[0] = ImageIO.read(new File("MalumRightSideSwing1.png"));
		rightSideSwing[1] = ImageIO.read(new File("MalumRightSideSwing2.png"));
		leftFrontSwing[0] = ImageIO.read(new File("MalumLeftFrontSwing1.png"));
		leftFrontSwing[1] = ImageIO.read(new File("MalumLeftFrontSwing2.png"));
		rightFrontSwing[0] = ImageIO.read(new File("MalumRightFrontSwing1.png"));
		rightFrontSwing[1] = ImageIO.read(new File("MalumRightFrontSwing2.png"));
		
		current = standingImages[0];
	}
	
	public Boss(int x, int y, Player player) throws IOException {
		super(x, y, 256, 256, 100, -2, Color.red, 2);
		this.player = player;
		fireballs = new ArrayList<pointSource>();
		standingImages = new Image[4];
		skull = ImageIO.read(new File("skull.png"));
		standingImages[0] = ImageIO.read(new File("Malum.png"));
		standingImages[1] = ImageIO.read(new File("MalumBack.png"));
		standingImages[2] = ImageIO.read(new File("MalumRightSide.png"));
		standingImages[3] = ImageIO.read(new File("MalumLeftSide.png"));
		
		leftWalk = new Image[3];
		rightWalk = new Image[3];
		backWalk = new Image[3];
		frontWalk = new Image[2];
		
		frontWalk[0] = ImageIO.read(new File("MalumFrontWalk1.png"));
		frontWalk[1] = ImageIO.read(new File("MalumFrontWalk2.png"));
		backWalk[0] = ImageIO.read(new File("MalumBackWalk1.png"));
		backWalk[1] = ImageIO.read(new File("MalumBackWalk2.png"));
		rightWalk[0] = ImageIO.read(new File("MalumRightWalk1.png"));
		rightWalk[1] = ImageIO.read(new File("MalumRightWalk2.png"));
		rightWalk[2] = ImageIO.read(new File("MalumRightWalk3.png"));
		leftWalk[0] = ImageIO.read(new File("MalumLeftWalk1.png"));
		leftWalk[1] = ImageIO.read(new File("MalumLeftWalk2.png"));
		leftWalk[2] = ImageIO.read(new File("MalumLeftWalk3.png"));
		
		leftSwing = new Image[2];
		rightSwing = new Image[2];
		
		rightSwing[0] = ImageIO.read(new File("MalumRightSwing1.png"));
		rightSwing[1] = ImageIO.read(new File("MalumRightSwing2.png"));
		leftSwing[0] = ImageIO.read(new File("MalumLeftSwing1.png"));
		leftSwing[1] = ImageIO.read(new File("MalumLeftSwing2.png"));
		
		leftSideSwing = new Image[2]; 
		rightSideSwing = new Image[2]; 
		leftFrontSwing = new Image[2]; 
		rightFrontSwing = new Image[2];
		
		leftSideSwing[0] = ImageIO.read(new File("MalumLeftSideSwing1.png"));
		leftSideSwing[1] = ImageIO.read(new File("MalumLeftSideSwing2.png"));
		rightSideSwing[0] = ImageIO.read(new File("MalumRightSideSwing1.png"));
		rightSideSwing[1] = ImageIO.read(new File("MalumRightSideSwing2.png"));
		leftFrontSwing[0] = ImageIO.read(new File("MalumLeftFrontSwing1.png"));
		leftFrontSwing[1] = ImageIO.read(new File("MalumLeftFrontSwing2.png"));
		rightFrontSwing[0] = ImageIO.read(new File("MalumRightFrontSwing1.png"));
		rightFrontSwing[1] = ImageIO.read(new File("MalumRightFrontSwing2.png"));
		
		current = standingImages[0];
	}

	public void bossAI() {
	//	System.out.println(left);
	//phase 0 = targetting the player
	//phase 1 = attacking player
	//phase 2 = moving towards the fireball spot
	//phase 3 = fireballing
	//phase 4 = stun
		if (phase == 0) { //PHASE 0
			
			target();
			
			classTimer++;
			if (phase == 0 && classTimer >= 401) {
				classTimer = 0;
				phase = 2;
			}
		}
		else if (phase == 1 && classTimer < 61) { //PHASE 1
			classTimer++;
			attack();
			if ((player.getDirection() == 1 && player.getCenter().getX() < center.getX()) || (player.getDirection() == -1 && player.getCenter().getX() > center.getX()) || player.getDirection() == direction * -1) {
				if (isAttacking && !player.isUsingSword()) { //sends the boss into a stun position
					phase = 4;
					classTimer = 0;
					player.setShieldHealth(player.getShieldHealth() - 12);
				}
			}
		}
		
		if (phase == 1 && classTimer >= 61) {
			int random = (int)(Math.random() * 100);
			
			classTimer = 0;
			if (random % 2 == 0)
				phase = 0;
			else
				phase = 2;
		}
		
		if (phase == 2) //PHASE 2
			moveTo(375, 0);
		if (phase == 3) { //PHASE 3
			classTimer++;
			if (player.getYCoord() < 200) {
				phase = 0;
				classTimer = 0;
				melee = true;
				atMiddle = false;
			}
			if (phase == 3 && classTimer % 10 == 0)
				fireball();
			else if (phase == 3 && classTimer >= 50) {
				classTimer = 0;
				int random = (int)(Math.random() * 100);
				
				if (random % 2 == 0)
					phase = 0;
				else
					phase = 3;
				melee = true;
				atMiddle = false;
			}
		}
		if (phase == 4) { //STUN
			current = standingImages[0];
			int speed = 3;
			if (classTimer < 10) { //pushes the boss back || handles the knockback for the boss
				if (player.getDirection() == 2 && direction == -2) {
					y -= speed;
					center.setLocation(center.getX(), center.getY() - speed);
				}
				if (player.getDirection() == -2 && direction == 2) {
					y += speed;
					center.setLocation(center.getX(), center.getY() + speed);
				}
				if (player.getDirection() == 1 && direction == -1) {
					x += speed;
					center.setLocation(center.getX() + speed, center.getY());
				}
				if (player.getDirection() == -1 && direction == 1) {
					x += speed;
					//System.out.println("Happen");
					center.setLocation(center.getX() - speed, center.getY());
				}
			}
				
			classTimer++;
		}
		if (phase == 4 && classTimer >= 200) {
			classTimer = 0;
			int random = (int)(Math.random() * 100);
			
			if (random % 2 == 0)
				phase = 0;
			else
				phase = 2;
		}

	}
	
	public void fireball() {
		int xDir = 0;
		int rX = -20;
		int rY = 0;
		for (int i = 0; i < 6; i++) {
			//System.out.println(rX);
			rX += (int)(Math.random() * 5 + 3);
			//rX = (int)(Math.random() * 15);
			rY = (int)(Math.random() * 20 + 5);
			fireballs.add(new pointSource((int)center.getX(), (int)center.getY(), 25, rX, rY, xDir));
		}
	} 
	
	public void attack() {
		//direction = determineDirection();
		//System.out.println(direction);
		
		if (direction == 2) {
			swingFront();
			//current = standingImages[1];
		}	
		else if (direction == -2) {
			swingBack();
			//current = standingImages[0];
		} 
		else if (direction == 1) {
			swingRight();
			//current = standingImages[2];
		}
			
		else if (direction == -1) {
			swingLeft();
			//current = standingImages[3];
		}
			
		
	}
	
	public void target() { //targets the player and has the boss move closer
		//2 = North
		//1 = East
		//-2 = South
		//-1 = West
		int speed = 3;
		int distance = (int)calculate(player);
		isAttacking = false;
		
		if ((player.getCenter().getX() <= 35 && player.getCenter().getY() >= 550 && !collided && distance <= 132)) {
			targ = false;
			melee = true;
			phase = 1;
			classTimer = 0;
		}
		else if ((player.getCenter().getX() >= 685 && player.getCenter().getY() >= 550 && !collided && distance <= 132)) {
			targ = false;
			melee = true;
			phase = 1;
			classTimer = 0;
		}
		else if ((player.getCenter().getX() <= 35 && player.getCenter().getY() <= 60)  && !collided && distance <= 142) {
			targ = false;
			melee = true;
			phase = 1;
			classTimer = 0;
		}
		else if ((player.getCenter().getX() >= 685 && player.getCenter().getY() <= 60)  && !collided && distance <= 144) {
			targ = false;
			melee = true;
			phase = 1;
			classTimer = 0;
		}
		else if (distance >= 110) {
			if (center.getX() - player.getCenter().getX() < - 10) {
				x += speed;
				center.setLocation(center.getX() + speed, center.getY());
				animateRight();
			}
			else if (center.getX() - player.getCenter().getX() > 10){
				x -= speed;
				center.setLocation(center.getX() - speed, center.getY());
				animateLeft();
			}
			else {
				left = 0;
				right = 0;
			}
				
			
			if (center.getY() - player.getCenter().getY() < -10) {
				y += speed;
				center.setLocation(center.getX(), center.getY() + speed);
				animateBack();
			}
			else if (center.getY() - player.getCenter().getY() > 10){
				y -= speed;
				center.setLocation(center.getX(), center.getY() - speed);
				animateFront();
			}
			else {
				front = 0;
				back = 0;
			}

		}
		else {
			targ = false;
			melee = true;
			phase = 1;
			classTimer = 0;
				
		}
	}
	
	public void swingLeft() {
		timer++;
		direction = -1;
		
		if (timer >= 20) {
			left++;
			timer = 0;
		}
		if (left == 1) {
			isAttacking = true;
		}
			
		if (left == 2) {
			left = 0;
			isAttacking = false;
		}
	
		current = leftSideSwing[left];
	}
	
	public void swingRight() {
		
		timer++;
		direction = 1;
		if (timer >= 20) {
			right++;
			timer = 0;
		}
		if (right == 1) {
			isAttacking = true;
		}
			
		if (right == 2) {
			right = 0;
			isAttacking = false;
		}


		current = rightSideSwing[right];
	}
	
	public void swingFront() {
		timer++;
		direction = 2;
		if (timer >= 20) {
			front++;
			timer = 0;
		}
		if (front == 1) {
			isAttacking = true;
		}
			
		if (front == 2) {
			front = 0;
			isAttacking = false;
		}
			
		current = leftFrontSwing[front];
	} 
	
	public void swingBack() {
		timer++;
		direction = -2;
		if (timer >= 20) {
			back++;
			timer = 0;
		}
		if (back == 1) {
			isAttacking = true;
		}
			
		if (back == 2) {
			back = 0;
			current = standingImages[0];
			isAttacking = false;
		}

		current = leftSwing[back];
		
	}
	
	public void animateLeft() {
		timer++;
		direction = -1;
		if (timer >= 15) {
			left++;
			timer = 0;
		}
		if (left == 3)
			left = 0;
		
		current = leftWalk[left];
	}
	
	public void animateRight() {
		//System.out.println("Animate Right=============================");
		timer++;
		direction = 1;
		if (timer >= 15) {
			right++;
			timer = 0;
		}
		if (right == 3)
			right = 0;
		
		current = rightWalk[right];
		
		//System.out.println(timer);
	}
	
	public void animateBack() {
		timer++;
		direction = -2;
		if (timer >= 15) {
			back++;
			timer = 0;
		}
		if (back == 2)
			back = 0;
		
		current = backWalk[back];
		
		//System.out.println(timer);
	}
	
	public void animateFront() {
		timer++;
		direction = 2;
		if (timer >= 15) {
			front++;
			timer = 0;
		}
		if (front == 2)
			front = 0;
		
		current = frontWalk[front];
		
		//System.out.println(timer);
	}

	
	
	public void checkForOutofBounds() {
		for (int i = 0; i < fireballs.size(); i++) {
			if (fireballs.get(i).getParticles() != null && fireballs.get(i).getParticles()[0] != null) {
				if (!fireballs.get(i).isCollidable() && fireballs.get(i).getParticles()[0].getC().getAlpha() < 30) {
					fireballs.remove(i);
					return;
				}
					
				if (fireballs.get(i).getX() < 0 || fireballs.get(i).getX() > 750) {
					fireballs.remove(i);
					return;
				}
				if (fireballs.get(i).getY() < 0 || fireballs.get(i).getY() > 610) {
					fireballs.remove(i);
					return;
				
			}
		}
				
		}
	}
	
	public void moveTo(int x, int y) {
		
		left = 0;
		right = 0;
		front = 0;
		back = 0;
		Point goTo = calcSlope(x, y);
		int speed = 5;
		int distance = (int) calculate(x, y);
		isAttacking = false;
		
		if (center.getX() - x < -2) {
			this.x += speed;
			center.setLocation(center.getX() + speed, center.getY());
			animateRight();
		}
		else if (center.getX() - x > 2){
			this.x -= speed;
			center.setLocation(center.getX() - speed, center.getY());
			animateLeft();
		}
			
		
		if (this.y - y < -2) {
			this.y += speed;
			center.setLocation(center.getX(), center.getY() + speed);
			animateBack();
		}
		else if (this.y - y > 2){
			this.y -= speed;
			center.setLocation(center.getX(), center.getY() - speed);
			animateFront();
		}
		
		if (distance < 127 + speed) {
			direction = -2;
			atMiddle = true;
			current = standingImages[0];
			phase = 3;
		}
			
	}
	
	public int getPhase() {
		return phase;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}

	public Image[] getStandingImages() {
		return standingImages;
	}

	public void setStandingImages(Image[] standingImages) {
		this.standingImages = standingImages;
	}

	public Player getPlayer() {
		return player;
	}

	public Image[] getLeftWalk() {
		return leftWalk;
	}

	public Image[] getRightWalk() {
		return rightWalk;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setLeftWalk(Image[] leftWalk) {
		this.leftWalk = leftWalk;
	}

	public void setRightWalk(Image[] rightWalk) {
		this.rightWalk = rightWalk;
	}

	public int getLeft() {
		return left;
	}
	
	public void setLeft(int left) {
		this.left = left;
	}

	public ArrayList<pointSource> getFireballs() {
		return fireballs;
	}

	public void setFireballs(ArrayList<pointSource> fireballs) {
		this.fireballs = fireballs;
	}

	public int getCap() {
		return cap;
	}

	public void setCap(int cap) {
		this.cap = cap;
	}

	public Image getSkull() {
		return skull;
	}

	public void setSkull(Image skull) {
		this.skull = skull;
	}
	
	
	
	
}
