import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Player extends Collider{ //player class that stores the images for the player and extends the collider class

	//establishing variables
	Image[] standingImages; Image[] leftImages; Image[] frontImages; Image[] backImages; Image[] rightSwing; Image[] leftSwing; Image[] frontSwing;
	Image[] backSwing; int left = 0; int right = 0; int front = 0; int back = 0; Image[] rightImages;
	int rightAttack = 0; int leftAttack = 0; int frontAttack = 0; int backAttack; int direction = -2; int shieldRegenTimer = 0;
	Image[] hearts; Image sword; Image shield; boolean usingSword = true; boolean attacking = false; int shieldHealth = 60;
	Image[] standingShieldImages; Image[] frontShieldImages; Image[] backShieldImages; Image[] rightShieldImages; Image[] leftShieldImages;
	
	//2 = North
	//1 = East
	//-2 = South
	//-1 = West
	public Player() throws IOException { //constructor for the player class
		super();
		x = 375;
		y = 375;
		//reading iamges into variables and arrays
		hearts = new Image[4];
		sword = ImageIO.read(new File("Sword.png"));
		shield = ImageIO.read(new File("Shield.png"));
		//assigning images to arrays
		hearts[0] = ImageIO.read(new File("Heart.png"));
		hearts[1] = ImageIO.read(new File("75Heart.png"));
		hearts[2] = ImageIO.read(new File("50Heart.png"));
		hearts[3] = ImageIO.read(new File("25Heart.png"));
		
		//initializing arrays
		leftImages = new Image[5];
		rightImages = new Image[5];
		standingImages = new Image[4];
		frontImages = new Image[2];
		backImages = new Image[2];
		//reading iamges into variables and arrays
		standingImages[0] = ImageIO.read(new File("Virtus.png"));
		standingImages[1] = ImageIO.read(new File("StandingFront.png"));
		standingImages[2] = ImageIO.read(new File("StandingLeft.png"));
		standingImages[3] = ImageIO.read(new File("StandingRight.png"));
		//assigning images to arrays	
		leftImages[0] = ImageIO.read(new File("VirtusMoveLeft.png"));
		leftImages[1] = ImageIO.read(new File("VirtusMoveLeft1.png"));
		leftImages[2] = ImageIO.read(new File("VirtusMoveLeft2.png"));
		leftImages[3] = ImageIO.read(new File("VirtusMoveLeft3.png"));
		leftImages[4] = ImageIO.read(new File("VirtusMoveLeft4.png"));
		//assigning images to arrays
		rightImages[0] =  ImageIO.read(new File("VirtusMoveRight.png"));
		rightImages[1] =  ImageIO.read(new File("VirtusMoveRight1.png"));
		rightImages[2] =  ImageIO.read(new File("VirtusMoveRight2.png"));
		rightImages[3] =  ImageIO.read(new File("VirtusMoveRight3.png"));
		rightImages[4] =  ImageIO.read(new File("VirtusMoveRight4.png"));
		//assigning images to arrays
		frontImages[0] = ImageIO.read(new File("FrontWalk1.png"));
		frontImages[1] = ImageIO.read(new File("FrontWalk2.png"));
		//assigning images to arrays
		backImages[0] = ImageIO.read(new File("BackWalk1.png"));
		backImages[1] = ImageIO.read(new File("BackWalk2.png"));
		
		//initializing arrays
		rightSwing = new Image[5];
		leftSwing = new Image[5];
		frontSwing = new Image[5];
		backSwing = new Image[5];
		//assigning images to arrays
		backSwing[0] = ImageIO.read(new File("BackAttack1.png"));
		backSwing[1] = ImageIO.read(new File("BackAttack2.png"));
		backSwing[2] = ImageIO.read(new File("BackAttack3.png"));
		backSwing[3] = ImageIO.read(new File("BackAttack4.png"));
		backSwing[4] = ImageIO.read(new File("BackAttack5.png"));
		//assigning images to arrays	
		frontSwing[0] = ImageIO.read(new File("FrontAttack1.png"));
		frontSwing[1] = ImageIO.read(new File("FrontAttack2.png"));
		frontSwing[2] = ImageIO.read(new File("FrontAttack3.png"));
		frontSwing[3] = ImageIO.read(new File("FrontAttack4.png"));
		frontSwing[4] = ImageIO.read(new File("FrontAttack5.png"));
		//assigning images to arrays
		leftSwing[0] = ImageIO.read(new File("playerLeftSwing.png"));
		leftSwing[1] = ImageIO.read(new File("playerLeftSwing1.png"));
		leftSwing[2] = ImageIO.read(new File("playerLeftSwing2.png"));
		leftSwing[3] = ImageIO.read(new File("playerLeftSwing3.png"));
		leftSwing[4] = ImageIO.read(new File("playerLeftSwing4.png"));
		//assigning images to arrays
		rightSwing[0] = ImageIO.read(new File("playerRightSwing.png"));
		rightSwing[1] = ImageIO.read(new File("playerRightSwing1.png"));
		rightSwing[2] = ImageIO.read(new File("playerRightSwing2.png"));
		rightSwing[3] = ImageIO.read(new File("playerRightSwing3.png"));
		rightSwing[4] = ImageIO.read(new File("playerRightSwing4.png"));
		
		//initializing arrays
		standingShieldImages = new Image[4];
		frontShieldImages = new Image[2];
		backShieldImages = new Image[2];
		rightShieldImages = new Image[4];
		leftShieldImages = new Image[4];
		
		//assigning images to arrays
		leftShieldImages[0] = ImageIO.read(new File("MoveShieldLeft1.png"));
		leftShieldImages[1] = ImageIO.read(new File("MoveShieldLeft2.png"));
		leftShieldImages[2] = ImageIO.read(new File("MoveShieldLeft3.png"));
		leftShieldImages[3] = ImageIO.read(new File("MoveShieldLeft4.png"));
		rightShieldImages[0] = ImageIO.read(new File("MoveShieldRight1.png"));
		rightShieldImages[1] = ImageIO.read(new File("MoveShieldRight2.png"));
		rightShieldImages[2] = ImageIO.read(new File("MoveShieldRight3.png"));
		rightShieldImages[3] = ImageIO.read(new File("MoveShieldRight4.png"));
		backShieldImages[0] = ImageIO.read(new File("BackShieldWalk1.png"));
		backShieldImages[1] = ImageIO.read(new File("BackShieldWalk2.png"));
		frontShieldImages[0] = ImageIO.read(new File("FrontShieldWalk1.png"));
		frontShieldImages[1] = ImageIO.read(new File("FrontShieldWalk2.png"));
		standingShieldImages[0] = ImageIO.read(new File("VirtusBackShield.png"));
		standingShieldImages[1] = ImageIO.read(new File("VirtusFrontShield.png"));
		standingShieldImages[2] = ImageIO.read(new File("VirtusRightShield.png"));
		standingShieldImages[3] = ImageIO.read(new File("VirtusLeftShield.png"));
		//sets the current image to the standing image
		current = standingImages[0];
	}
	
	
	public Player(int x, int y) throws IOException { //second constructor that gets a custom position for the player
		super(x, y, 56, 96, 20, -2, new Color(0,0,0,0), 5);
		//reading iamges into variables and arrays
		hearts = new Image[4];
		//assigning images to arrays
		sword = ImageIO.read(new File("Sword.png"));
		shield = ImageIO.read(new File("Shield.png"));
		//assigning images to arrays
		hearts[0] = ImageIO.read(new File("Heart.png"));
		hearts[1] = ImageIO.read(new File("75Heart.png"));
		hearts[2] = ImageIO.read(new File("50Heart.png"));
		hearts[3] = ImageIO.read(new File("25Heart.png"));
	
		//initializing arrays
		leftImages = new Image[5];
		rightImages = new Image[5];
		standingImages = new Image[4];
		frontImages = new Image[2];
		backImages = new Image[2];
		//assigning images to arrays
		standingImages[0] = ImageIO.read(new File("Virtus.png"));
		standingImages[1] = ImageIO.read(new File("StandingFront.png"));
		standingImages[2] = ImageIO.read(new File("StandingLeft.png"));
		standingImages[3] = ImageIO.read(new File("StandingRight.png"));
		//assigning images to arrays
		leftImages[0] = ImageIO.read(new File("VirtusMoveLeft.png"));
		leftImages[1] = ImageIO.read(new File("VirtusMoveLeft1.png"));
		leftImages[2] = ImageIO.read(new File("VirtusMoveLeft2.png"));
		leftImages[3] = ImageIO.read(new File("VirtusMoveLeft3.png"));
		leftImages[4] = ImageIO.read(new File("VirtusMoveLeft4.png"));
		//assigning images to arrays
		rightImages[0] =  ImageIO.read(new File("VirtusMoveRight.png"));
		rightImages[1] =  ImageIO.read(new File("VirtusMoveRight1.png"));
		rightImages[2] =  ImageIO.read(new File("VirtusMoveRight2.png"));
		rightImages[3] =  ImageIO.read(new File("VirtusMoveRight3.png"));
		rightImages[4] =  ImageIO.read(new File("VirtusMoveRight4.png"));
		//assigning images to arrays
		frontImages[0] = ImageIO.read(new File("FrontWalk1.png"));
		frontImages[1] = ImageIO.read(new File("FrontWalk2.png"));
		//assigning images to arrays
		backImages[0] = ImageIO.read(new File("BackWalk1.png"));
		backImages[1] = ImageIO.read(new File("BackWalk2.png"));
		//initializing arrays
		rightSwing = new Image[5];
		leftSwing = new Image[5];
		frontSwing = new Image[5];
		backSwing = new Image[5];
		//assigning images to arrays
		backSwing[0] = ImageIO.read(new File("BackAttack1.png"));
		backSwing[1] = ImageIO.read(new File("BackAttack2.png"));
		backSwing[2] = ImageIO.read(new File("BackAttack3.png"));
		backSwing[3] = ImageIO.read(new File("BackAttack4.png"));
		backSwing[4] = ImageIO.read(new File("BackAttack5.png"));
		//assigning images to arrays
		frontSwing[0] = ImageIO.read(new File("FrontAttack1.png"));
		frontSwing[1] = ImageIO.read(new File("FrontAttack2.png"));
		frontSwing[2] = ImageIO.read(new File("FrontAttack3.png"));
		frontSwing[3] = ImageIO.read(new File("FrontAttack4.png"));
		frontSwing[4] = ImageIO.read(new File("FrontAttack5.png"));
		
		leftSwing[0] = ImageIO.read(new File("playerLeftSwing.png"));
		leftSwing[1] = ImageIO.read(new File("playerLeftSwing1.png"));
		leftSwing[2] = ImageIO.read(new File("playerLeftSwing2.png"));
		leftSwing[3] = ImageIO.read(new File("playerLeftSwing3.png"));
		leftSwing[4] = ImageIO.read(new File("playerLeftSwing4.png"));
		//assigning images to arrays
		rightSwing[0] = ImageIO.read(new File("playerRightSwing.png"));
		rightSwing[1] = ImageIO.read(new File("playerRightSwing1.png"));
		rightSwing[2] = ImageIO.read(new File("playerRightSwing2.png"));
		rightSwing[3] = ImageIO.read(new File("playerRightSwing3.png"));
		rightSwing[4] = ImageIO.read(new File("playerRightSwing4.png"));
		//initializing arrays
		standingShieldImages = new Image[4];
		frontShieldImages = new Image[2];
		backShieldImages = new Image[2];
		rightShieldImages = new Image[4];
		leftShieldImages = new Image[4];
		//assigning images to arrays
		leftShieldImages[0] = ImageIO.read(new File("MoveShieldLeft1.png"));
		leftShieldImages[1] = ImageIO.read(new File("MoveShieldLeft2.png"));
		leftShieldImages[2] = ImageIO.read(new File("MoveShieldLeft3.png"));
		leftShieldImages[3] = ImageIO.read(new File("MoveShieldLeft4.png"));
		rightShieldImages[0] = ImageIO.read(new File("MoveShieldRight1.png"));
		rightShieldImages[1] = ImageIO.read(new File("MoveShieldRight2.png"));
		rightShieldImages[2] = ImageIO.read(new File("MoveShieldRight3.png"));
		rightShieldImages[3] = ImageIO.read(new File("MoveShieldRight4.png"));
		backShieldImages[0] = ImageIO.read(new File("BackShieldWalk1.png"));
		backShieldImages[1] = ImageIO.read(new File("BackShieldWalk2.png"));
		frontShieldImages[0] = ImageIO.read(new File("FrontShieldWalk1.png"));
		frontShieldImages[1] = ImageIO.read(new File("FrontShieldWalk2.png"));
		standingShieldImages[0] = ImageIO.read(new File("VirtusBackShield.png"));
		standingShieldImages[1] = ImageIO.read(new File("VirtusFrontShield.png"));
		standingShieldImages[2] = ImageIO.read(new File("VirtusRightShield.png"));
		standingShieldImages[3] = ImageIO.read(new File("VirtusLeftShield.png"));
		//sets the current image to the standing image
		current = standingImages[0];
	}
	
	public void regenShield() { //regenerates the shield health
		shieldRegenTimer++; //increment timer
		if (shieldRegenTimer >= 50) { //if equal to or greater than 50
			shieldRegenTimer = 0; //reset
			if (shieldHealth < 60) //if less shield health is less than 60, increase by one
				shieldHealth++;
			//essentially, the health of the shield regenerates by one point every 50 ticks
		}
	}
	
	public boolean checkShield(pointSource source) { //checks for the shield and if it facing the approprate way to block a collision
		//only run if-statements if the sword is NOT being used, in other words the player must be using the shield
		//return true, means that there will be a collision
		//return false, means there will not be a collision
		if (shieldHealth <= 12) //if less than or equal to 12, return true
			return true;
		
		if (!usingSword) {
			if (direction == 2 && source.getAngle() < 135 && source.getAngle() > 45) //if facing north and the particle is at an angle between 45 and 135 degrees
				return false; //return false, meaning that there will not be a collision
			else if (direction == 1 && source.getAngle() >= 120 && source.getAngle() <= 180) //if facing east and the particle is at an angle between 135 and 180 degrees
				return false; //return false, meaning that there will not be a collision
			else if (direction == -1 && source.getAngle() <= 60 && source.getAngle() >= 0) //if facing north and the particle is at an angle between 45 and 0 degrees
				return false;//return false, meaning that there will not be a collision
		} 
		return true; //return true, meaning that there will be a collision
	}
	
	public void displayHealth(Graphics g) { //displays the health bar for the player onto the screen
		int xH = 0; int yH = 640; //coordinates for positioning the images
		
		int fullHearts = health / 4; //gets the amount of full hearts the player has
		int remainder = health % 4; //gets the amount of fractional hearts the player has
		
		for (int i = 0; i < fullHearts; i++) { //iterates the logic inside the loop for each full heart
			g.drawImage(hearts[0], xH, yH, null); //draws a full heart onto the screen
			xH += 69; //changes the posiiton
		}

		if (remainder == 1) //if there is a remainder of one
			g.drawImage(hearts[3], xH, yH, null); //draw a quarter of a heart
		else if (remainder == 2) //if there is a remainder of two
			g.drawImage(hearts[2], xH, yH, null); //draw half of a heart
		else if (remainder == 3) //if there is a remainder of three
			g.drawImage(hearts[1], xH, yH, null); //draw 3/4s of a heart
			
	}
	
	public void displayTools(Graphics g) { //displays the tools that the player can use
		int xI = 490; int yI = 600; int width = 120; int height = 100; //defines variables for the positions of the images
		g.drawImage(sword, xI, yI, null); //draws the sword
		g.drawImage(shield, xI + 120, yI, null); //draw the shield
		
		g.setColor(Color.red); //sets the color to red
		BasicStroke stroke = new BasicStroke(5); //creates a BasicStroke object to change the thickness of the pen
		Graphics2D g2 = (Graphics2D)g; // casts the graphics to a grpahics2d object
		g2.setStroke(stroke); //sets a new stroke thickness
		if (usingSword) //if using the sword
			g.drawRect(xI, yI + 15, width, height); //draw a rectangle that selects the sword on the screen
		else //if using the shield
			g.drawRect(xI + 120, yI + 15, width, height); //draw a rectangle that selects the shield on the screen
		
		if (this.getShieldHealth() <= 12) { //if the shield health is less than or equal 12, then let the player know that their shield health has dropped below 20%
			g.setFont(new Font("Impact", Font.BOLD, 14)); //sets the fond
			g.setColor(Color.white);//sets the color to black
			g.drawString("Your Shield Has Dropped Below 20%", xI, yI - 10); //makes a text that tells the player that their shield has dropped below 20%
		}
	}
	public void animateLeftShield() { //animates the shield animation when the player is running to the left
		timer++; //increments the timer
		if (timer >= 10) { //if timer reaches 10
			left++; //increment by one
			timer = 0; //reset
		}
		if (left == 4) //if left reaches 4
			left = 0; //reset
		//assigns an image in leftShieldimages based on the index to current
		current = leftShieldImages[left];
	}
	public void animateLeft() { //animates the running sword animation for the player
		timer++; //incrementing timer
		if (timer >= 10) { //if timer equals 10
			left++; //increase 'left' by one
			timer = 0; //reset timer
		}
		if (left == 4) //if left is 4
			left = 0; //reset
		
		current = leftImages[left]; //set the current image to an
		//image at index 'left' of an array
	}
	public void animateRightShield() { //animates the shield animation when the player is running to the right
		timer++; //increments the timer
		if (timer >= 10) { //if timer reaches 10
			right++;//increment by one
			timer = 0;//reset
		}
		if (right == 4) //if right reaches 4
			right = 0; //reset
		//assigns an image in rightShieldimages based on the index to current
		current = rightShieldImages[right];
	}
	
	public void animateRight() { //animates the running sword animation for the player
		timer++; //incrementing timer
		if (timer >= 10) { //if timer equals 10
			right++;
			timer = 0;
		}
		if (right == 4)  //if right is 4, reset
			right = 0;
		//assigns an image in rightShieldimages based on the index to current
		current = rightImages[right];
	}
	
	public void animateFrontShield() { //animates the shield animation when the player is running upward
		timer++; //increments the timer
		if (timer >= 10) { //if timer reaches 10
			front++;  //increment by one
			timer = 0; //reset
		}
		if (front == 2)//if front reaches 2, reset
			front = 0;
		//assigns an image in frontShieldimages based on the index to current
		current = frontShieldImages[front];
	}
	
	public void animateFront() { //animates the running sword animation for the player
			
		timer++; //increment by one
		if (timer >= 10) { //if timer reaches 10, reset timer and increment front by one
			front++;
			timer = 0;
		}
		if (front == 2) //if front reaches two, thne reset it
			front = 0;
		//assigns an image in rightShieldimages based on the index to current
		current = frontImages[front];
	}
	
	public void animateBackShield() { //animates the shield animation when the player is running down
		timer++; //increment
		if (timer >= 10) { //if timer reaches 10
			back++; //increment by one
			timer = 0; //reset
		}
		if (back == 2) //if back reaches two, reset
			back = 0;
		//assigns an image in rightShieldimages based on the index to current
		current = backShieldImages[back];
	}
	
	public void animateBack() { //animates the running sword animation for the player
		timer++; //increment
		if (timer >= 10) {//if timer reaches 10
			back++;//increment by one
			timer = 0; //reset
		}
		if (back == 2)//if back reaches two, reset
			back = 0;
		//assigns an image in rightShieldimages based on the index to current
		current = backImages[back];
	}
	
	public void attack(ColorPanel panel) { //allows the player to attack
		//2 = North
		//1 = East
		//-2 = South
		//-1 = West
		isAttacking = true; //sets to true
		timer++; //increment
		if (direction == 1) { //EAST
			if (timer >= 5) { //if it reaches five
				rightAttack++; //increment by one
				timer = 0; //reset
			}
			
			if (rightAttack == 5) { //if  reset variables and set the current image to its proper standing direction
				attacking = false; 
				rightAttack = 0;
				current = standingImages[3];
				isAttacking = false;
				panel.setAttacking(false);
				return;
			}
			//assigns a new image to the current
			current = rightSwing[rightAttack];
			imageStabilizer();
		}
		else if (direction == -1) { //WEST
			if (timer >= 5) { //if it reaches five, increment variable and reset timer
				leftAttack++;
				timer = 0;
			}
				
			if (leftAttack == 5) { //if  reset variables and set the current image to its proper standing direction
				attacking = false;
				leftAttack = 0;
				current = standingImages[2];
				isAttacking = false;
				panel.setAttacking(false);
				return;
			}
			//assigns a new image to the current
			current = leftSwing[leftAttack];
			imageStabilizer();
		}
		else if (direction == 2) { //NORTH
			if (timer >= 5) {//if it reaches five, increment variable and reset timer
				frontAttack++;
				timer = 0;
			}
				
			if (frontAttack == 5) { //if  reset variables and set the current image to its proper standing direction
				attacking = false;
				frontAttack = 0;
				current = standingImages[1];
				isAttacking = false;
				panel.setAttacking(false);
				return;
			}
			//assigns a new image to the current
			current = frontSwing[frontAttack];
			imageStabilizer();
		}
		else if (direction == -2) { //SOUTH
			if (timer >= 5) { //if it reaches five, increment variable and reset timer
				backAttack++;
				timer = 0;
			}
				
			if (backAttack == 5) { //if  reset variables and set the current image to its proper standing direction
				attacking = false;
				backAttack = 0;
				current = standingImages[0];
				isAttacking = false;
				panel.setAttacking(false);
				return;
			}
				//assigns a new image to the current
			current = backSwing[backAttack];
			imageStabilizer();
		}
		
	}

	public int getRight() { //gets the 'right' variable
		return right;
	}

	public Image[] getRightImages() { //gets the rightImages array
		return rightImages;
	}

	public Image[] getHearts() { //gets the hearts array
		return hearts;
	}

	public void setRight(int right) { //sets the right variable
		this.right = right;
	}

	public void setRightImages(Image[] rightImages) { //sets the right images to an other array
		this.rightImages = rightImages;
	}

	public void setHearts(Image[] hearts) { //sets the hearts array to an other array
		this.hearts = hearts;
	}

	public Image[] getStandingImages() { //gets the standing images
		return standingImages;
	}

	public void setStandingImages(Image[] image) { //sets the standing images
		this.standingImages = image;
	}

	public Image[] getLeftImages() { //gets the left images
		return leftImages;
	}

	public Image getCurrent() { //gets the current image
		return current;
	}

	public void setLeftImages(Image[] rightImages) { //sets the left images
		this.leftImages = rightImages;
	}

	public void setCurrent(Image current) { //sets the current
		this.current = current;
	}

	public int getLeft() { //gets the left variable
		return left;
	}

	public void setLeft(int left) { //sets the left variable
		this.left = left;
	}

	public Image getSword() { //gets the sword image
		return sword;
	}

	public Image getShield() { //gets the shield image
		return shield;
	}

	public boolean isUsingSword() { //get if the player is using the sword
		return usingSword;
	}

	public void setSword(Image sword) { //sets the sword image
		this.sword = sword;
	}

	public void setShield(Image shield) { //sets the shield image
		this.shield = shield;
	}

	public void setUsingSword(boolean usingSword) { //sets the boolean value of whether the player is using the sword or not
		this.usingSword = usingSword;
	}

	public Image[] getFrontImages() { //gets the front images
		return frontImages;
	}

	public Image[] getBackImages() { //gets the back images
		return backImages;
	}

	public Image[] getRightSwing() { //gets the right swing images
		return rightSwing;
	}

	public int getFront() { //gets the front variable
		return front;
	}

	public int getBack() { //gets the back variables
		return back;
	}

	public int getRightAttack() { //gets the right attack variable
		return rightAttack;
	}

	public boolean isAttacking() { //gets the isAttacking variable
		return attacking;
	}

	public void setFrontImages(Image[] frontImages) { //gets the front image array
		this.frontImages = frontImages;
	}

	public void setBackImages(Image[] backImages) { //sets the back images
		this.backImages = backImages;
	}

	public void setRightSwing(Image[] rightSwing) { //sets the right swing images
		this.rightSwing = rightSwing;
	}

	public void setFront(int front) { //sets the front variable
		this.front = front;
	}

	public void setBack(int back) { //sets the back variable
		this.back = back;
	}

	public void setRightAttack(int rightAttack) { //sets the right attack variable
		this.rightAttack = rightAttack;
	}

	public void setAttacking(boolean attacking) { //sets the attacking variable
		this.attacking = attacking;
	}

	public Image[] getLeftSwing() { //gets the left swing array
		return leftSwing;
	}

	public int getDirection() { //gets the direction
		return direction;
	}

	public void setLeftSwing(Image[] leftSwing) { //sets the left swing variable
		this.leftSwing = leftSwing;
	}

	public void setDirection(int direction) { //sets the direction
		this.direction = direction;
	}


	public Image[] getStandingShieldImages() { //gets the standing shield images
		return standingShieldImages;
	}


	public void setStandingShieldImages(Image[] standingShieldImages) { //sets the standing shield image array
		this.standingShieldImages = standingShieldImages;
	}


	public Image[] getFrontShieldImages() { //gets the front shiield image array
		return frontShieldImages;
	}


	public void setFrontShieldImages(Image[] frontShieldImages) { //sets the front shield image arrays
		this.frontShieldImages = frontShieldImages;
	}


	public Image[] getBackShieldImages() { //gets the back shield image array
		return backShieldImages;
	}


	public void setBackShieldImages(Image[] backShieldImages) { //sets the back shield image array
		this.backShieldImages = backShieldImages;
	}


	public Image[] getRightShieldImages() { //gets the right shield image array
		return rightShieldImages;
	}


	public void setRightShieldImages(Image[] rightShieldImages) { //sets the right shield image array
		this.rightShieldImages = rightShieldImages;
	}


	public Image[] getLeftShieldImages() { //gets the left shield image array
		return leftShieldImages;
	}


	public void setLeftShieldImages(Image[] leftShieldImages) {  //sets the left shield image array
		this.leftShieldImages = leftShieldImages;
	}


	public int getShieldHealth() { //gets the shield health
		return shieldHealth;
	}


	public void setShieldHealth(int shieldHealth) { //sets the shield health
		this.shieldHealth = shieldHealth;
	}
	
	
	
	
	
}
