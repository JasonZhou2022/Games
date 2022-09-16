
//import java.awt.Color;
//import java.awt.Graphics;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ColorPanel extends JPanel{ //colorpanel that does all the graphics for the game and extends the JPanel class

	//declaring variables for future use
	Particle[] confetti;
	Player player;
	Boss boss; pointSource stun;
	boolean left = false;
	boolean right = false;
	boolean up = false;
	boolean down = false;
	boolean spawnStun = false;
	Image bg;
	boolean start = true;
	boolean play = false;
	boolean gameOver = false;
	boolean youWin = false;
	boolean attacking = false;
	boolean quit = false;
	boolean controls = false;
	boolean thankYou = false;
	
	public ColorPanel() throws IOException { //constructor
		//sets the background
		this.setBounds(0, 0, 750, 750);
		this.setBackground(Color.blue);

		try { //initializes the background image
			bg = ImageIO.read(new File("background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//adds listeners
		addKeyListener(new Controls());
		setFocusable(true);

		//declares and initializes the player and boss
		player = new Player(375, 475);
		boss = new Boss(250, 0, player);

		//assigns an array of size 1000 of type Particle to the variable confetti
		confetti = new Particle[1000];
		for (int i = 0; i < confetti.length; i++) { //fills the array with particles that have random collors
			confetti[i] = new Particle();
			confetti[i].setC(new Color((int)(Math.random() * 255 + 1),(int)(Math.random() * 255 + 1),(int)(Math.random() * 255 + 1)));
		}
		
		//adds mouse listeners to detect clicks
		addMouseListener(new Mouse());
		setFocusable(true);
	}

	public void startScreen(Graphics g) { //creates the start screen for the game
		this.setBackground(Color.blue);
		g.setColor(Color.red); //sets the color to red
		Font font = new Font("Monospaced", Font.PLAIN, 50); //creates a font object for the string
		g.setFont(font); //sets the font
		//TITLE
		g.drawString("Virtus's Adventure", 100, 100); //draws a string
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
	
	public void thankYou(Graphics g) { //creates the thank you for playing screen
		this.setBackground(Color.blue);
		g.setColor(Color.red); //sets the color to red
		Font font = new Font("Monospaced", Font.BOLD, 50); //creates a font object for the string
		g.setFont(font); //sets the font
		//TITLE
		g.drawString("Thanks For Playing", 100, 325); //draws a string
		
		//WHITE QUIT BUTTON
		font = new Font("Monospaced", Font.PLAIN, 50); //new font
		g.setFont(font);
		g.setColor(Color.white); //sets the color to white
		g.fillRect(175, 375, 400, 100); //creates a box for the quit button
		g.setColor(Color.black); //sets the color black
		g.drawString("Quit", 310, 440); //draws a string
	}
	
	public void controlsScreen(Graphics g) { //creates the screen
		//WHITE BBACK BUTTON
		Font font = new Font("Monospaced", Font.BOLD, 50); //creates a font object for the string
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString("Controls", 250, 100); //draws a string
		font = new Font("Monospaced", Font.BOLD, 25); //creates a font object for the string
		g.setFont(font);
		g.drawString("W = Move Up", 150, 150); //draws a string
		g.drawString("A = Move Left", 150, 200); //draws a string
		g.drawString("S = Move Down", 150, 250); //draws a string
		g.drawString("D = Move Right", 150, 300); //draws a string
		g.drawString("N = Swing Sword", 150, 350); //draws a string
		g.drawString("M = Toggle Sword/Shield", 150, 400); //draws a string
		font = new Font("Monospaced", Font.BOLD, 15); //creates a font object for the string
		g.setFont(font);
		g.drawString("NOTE: You Can Stun The Boss By Blocking His Attack", 150, 430); //draws a string
		g.drawString("(A Stun Is Indicated By White Particles Emitted From The Boss)", 150, 440); //draws a string
		
		g.drawString("NOTE: You Will Receive A Slight Stun from Boss Attacks", 150, 460); //draws a string
		g.drawString("NOTE: You Will Be Unable To Use Your Shield", 150, 480); //draws a string
		g.drawString("If Its Health Drops Below 20%", 150, 490); //draws a string
		g.setColor(Color.white); //sets the color to white
		//g.fillRect(175, 525, 400, 100); //creates a box for the quit button
		g.fillRect(175, 525, 400, 100); //creates a box for the quit button
		g.setColor(Color.black); //sets the color black
		font = new Font("Monospaced", Font.BOLD, 50); //creates a font object for the string
		g.setFont(font);
		g.drawString("BACK", 310, 590); //draws a string
	}
	
	public void paintComponent(Graphics g) { //draws the graphics onto the screen
		super.paintComponent(g); //calls super
		if (quit) //if the quit variable is true, then exit the window
			System.exit(0);
		if (player.getHealth() <= 0) { //if player health is zero, game over
			play = false;
			gameOver = true;
		}
		else if (boss.getHealth() <= 0) { //if the boss health is zero, win 
			play = false;
			youWin = true;
		}
		if (start) { //if start is true, run the start screen
			startScreen(g);
		}
		else if (controls) // else if controls is true, run the controls screen
			controlsScreen(g);
		else if (thankYou) //if thankyou is true, then run the thank you screen
			thankYou(g);
		else if (play){ //if play is true, run the game

			if (player.getShieldHealth() <= 12 && !player.isUsingSword()) //if there shield has no health, forcibly switch them back to the sword
			{ //switches the player's sprite from shield to sword
				player.setUsingSword(true);
				if (!up && !down && !left && !right) { //if the directional variables are all false
					if (player.getDirection() == 2) //if NORTH
						player.setCurrent(player.getStandingImages()[1]);//set to the appropriate directional sprite
					else if (player.getDirection() == -2) //if SOUTH
						player.setCurrent(player.getStandingImages()[0]);//set to the appropriate directional sprite
					else if (player.getDirection() == 1) //if EAST
						player.setCurrent(player.getStandingImages()[3]);//set to the appropriate directional sprite
					else if (player.getDirection() == -1) //if WEST
						player.setCurrent(player.getStandingImages()[2]); //set to the appropriate directional sprite
				}
			}
			
			g.drawImage(bg, 0, 0, null); //draw the background
			g.setColor(new Color(183, 132, 0)); //light brown
			g.fillRect(0, 615, 750, 140); //draw a rect
			g.setColor(Color.red); //set color to red
			g.fill3DRect(50, 20, boss.getHealth() * 2, 30, true);
			g.setColor(Color.blue); //set color to blue
			g.drawImage(boss.getSkull(), 0, 0, null); //draw a skull
			Font font = new Font("Monospaced", Font.PLAIN, 10); //make a new font
			g.setColor(Color.white); //set to white
			g.drawString("Shield Health", 375, 645); //draw a string
			g.setColor(Color.blue); //set color to blue
			g.fillRect(355, 650, player.getShieldHealth() * 2, 30); //draw the player shield health bar
			g.setColor(Color.black);
			//making the boxes to indicate the percentage values of the shield health bar
			g.drawRect(355, 650, 12, 30);
			g.drawRect(355, 650, 24, 30);
			g.drawRect(355, 650, 36, 30);
			g.drawRect(355, 650, 48, 30);
			g.drawRect(355, 650, 60, 30);
			g.drawRect(355, 650, 72, 30);
			g.drawRect(355, 650, 84, 30);
			g.drawRect(355, 650, 96, 30);
			g.drawRect(355, 650, 108, 30);
			g.drawRect(355, 650, 120, 30);

			if (player.isCollided()) { //if collided, then prevent the player from moving
				
				//System.out.println("thread");
				up = false;
				down = false;
				left = false;
				right = false;
				attacking = false;
				player.setAttacking(false);
				if (player.getDirection() == 2) //NORTH
					player.setCurrent(player.getStandingImages()[1]); //set it to the appropriate directional image
				else if (player.getDirection() == -2) //SOUTH
					player.setCurrent(player.getStandingImages()[0]);//set it to the appropriate directional image
				else if (player.getDirection() == -1) //WEST
					player.setCurrent(player.getStandingImages()[2]);//set it to the appropriate directional image
				else if (player.getDirection() == 11) //EAST
					player.setCurrent(player.getStandingImages()[3]);//set it to the appropriate directional image
			}
				
			if (attacking) { //if attacking
				player.attack(this); //attack
				//reset all movement switches
				up = false;
				down = false;
				left = false;
				right = false;
			} else { //allow the player to move
				if (up) { //if up, then move up

					player.setY(player.getYCoord() - 5);
					player.setDirection(2);
					if (player.isUsingSword() && !left && !right) //if using sword, show animation moving up with a sword
						player.animateFront();
					else if (!player.isUsingSword() && !left && !right)
						player.animateFrontShield(); //if using shield, show animation moving up with a shield
				}
					
				else if (down) { //if down, then move down
					player.setY(player.getYCoord() + 5);
					player.setDirection(-2);
					if (player.isUsingSword() && !left && !right) //if using sword, show animation moving down with a sword
						player.animateBack();
					else if (!player.isUsingSword() && !left && !right)
						player.animateBackShield(); //if using shield, show animation moving down with a shield
				}
					
				if (right) { //if right, then move right
					player.setX(player.getXCoord() + 5);
					player.setDirection(1);
					if (player.isUsingSword())  //if using sword, show animation moving right with a sword
						player.animateRight();
					else
						player.animateRightShield(); //if using shield, show animation moving right with a shield
				}
				else if (left) { //if left, then move left
					player.setX(player.getXCoord() - 5);
					player.setDirection(-1);
					if (player.isUsingSword()) //if using sword, show animation moving left with a sword
						player.animateLeft();
					else
						player.animateLeftShield(); //if using shield, show animation moving left with a shield
				}
			}
			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//drawing the boss and the player
			g.drawImage(boss.getCurrent(), boss.getXCoord(), boss.getYCoord(), null);
			g.drawImage(player.getCurrent(), player.getXCoord(), player.getYCoord(), null);			
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//FIREBALL COLLISION DAMAGE
			  for (int i = 0; i < boss.getFireballs().size(); i++) { //iterates through the fireball arrraylist
				if (player.checkShield(boss.getFireballs().get(i)) && player.checkForSourceCollision(boss.getFireballs().get(i))) { //if fireball has collided with player
					player.setHealth(player.getHealth() - 1);	//decrease the health of the player
				} 
					
				else if (player.checkForSourceCollision(boss.getFireballs().get(i)) && !player.checkShield(boss.getFireballs().get(i))) { //if the player has the shield out
					
					player.setShieldHealth(player.getShieldHealth() - 2); //reduce shield health
				}
					
			}
			  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			player.displayHealth(g); //show health bar
			player.displayTools(g); //show sword and shield
			
			player.checkForParticleCollision(Particle.allParticles); //check for particle collisions
			
			//STUN SYSTEM
			if (boss.getPhase() == 4) { //if stunned
				 if (!spawnStun) { //if not true
					 stun = new pointSource((int)boss.getCenter().getX(), (int)boss.getCenter().getY(), 100, 0, 0, -2); //make a new stun particle array
					 for (int i = 0; i < stun.getParticles().length; i++) //iterate through this new array
						 stun.getParticles()[i].setC(Color.white); //set the color to white
					 spawnStun = true; //switch to true
				 }
				 
				 
				  for (int i = 0; i < stun.getParticles().length; i++) { //for all the particles in stun
						if (stun.getParticles()[i] != null) { //as long as they are not null
							stun.getParticles()[i].move();//move the particles
							g.setColor(stun.getParticles()[i].getC()); //set their colors to the appropriate colors
							g.fillRect(stun.getParticles()[i].getX(), stun.getParticles()[i].getY(), 4, 4); //draw the particles
							if (stun.getParticles()[i].getC().getAlpha() >= 21) //if their alpha value is greater than 21, assign a new color with lower transparency
								stun.getParticles()[i].setC(new Color(stun.getParticles()[i].getC().getRed(), stun.getParticles()[i].getC().getGreen(), stun.getParticles()[i].getC().getBlue(), stun.getParticles()[i].getC().getAlpha() - 20));
						}
						
					}
				 

			}
			else { //else set to false and null, respectively
				spawnStun = false;
				stun = null;
			}
				
			for (int i = 0; i < boss.getFireballs().size(); i++) { //iterate through the fireballs array list
				if (boss.getFireballs().get(i).getParticles() != null) { //if they are not null
					for (int j = 0; j < boss.getFireballs().get(i).getParticles().length; j++) { //iterates through the particles for each fireball
						if (boss.getFireballs().get(i).getParticles()[j] != null) { //if they are not null
							boss.getFireballs().get(i).getParticles()[j].move(); //move
							g.setColor(boss.getFireballs().get(i).getParticles()[j].getC()); //set to their color
							g.fillRect(boss.getFireballs().get(i).getParticles()[j].getX(), boss.getFireballs().get(i).getParticles()[j].getY(), 4, 4); //draw
							
							g.setColor(boss.getFireballs().get(i).getParticles()[j].getC()); //set to their color
							g.fillRect(boss.getFireballs().get(i).getParticles()[j].getX(), boss.getFireballs().get(i).getParticles()[j].getY(), 4, 4); //draw
						}
						
					}
				}
				
				
					
				
				if (boss.getFireballs().get(i).getParticles() != null) { //if the fireballs are not null, then have the fireballs travel
					g.setColor(Color.cyan);
					boss.getFireballs().get(i).travel();
				}
				
			}
			
			checkForOutofBounds(); //if they go outside the playing area, snap them back into it
			boss.bossAI(); //run boss AI
			if (boss.getPhase() != 4) //if the phase is not four
				if (player.isUsingSword()) //if the player is using sword
					player.checkForCollider(boss); //check for boss collisions
			
			if (!player.isUsingSword()) { //if the player is not using 
				player.setAttacking(false);
				attacking = false;
			}
			
			if (player.isUsingSword())
				player.checkForDamage(boss);
			
			boss.checkForCollider(player);
			boss.checkForDamage(player);
			boss.checkForOutofBounds();
			
			Particle.checkForOutofBounds();
			//pointSource.checkForOutofBounds();
			player.regenShield();
			boss.setCenter(boss.recalcCenter());
			player.setCenter(player.recalcCenter());
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		}
		else if(gameOver) { //GAME OVER SCREEN
			gameOver(g);
		}
		else {
			win(g);
			for (int i = 0; i < confetti.length; i++) {
				confetti[i].move();
				g.setColor(confetti[i].getC());
				g.fillRect(confetti[i].getX(), confetti[i].getY(), 4, 4);
			}
		}
		
	}

	public void win(Graphics g) { //creates the win screen
		this.setBackground(Color.yellow); //yellow background
		Font font = new Font("Monospaced", Font.PLAIN, 100); //creates a font for the string
		g.setFont(font); //sets the font
		g.drawString("YOU WIN", 130, 300); //draws the string
		//e.getX() <= 525 && e.getX() >= 175 && e.getY() >= 525 && e.getY() <= 625
		//WHITE QUIT BUTTON
		g.setColor(Color.white); //sets the color to white
		g.fillRect(175, 525, 400, 100); //creates the box for the quite button
		font = new Font("Monospaced", Font.PLAIN, 50); //new font
		g.setFont(font); //sets new font
		g.setColor(Color.black); //sets the color to black
		g.drawString("Next", 310, 590); //draws the string
	}
	
	public void gameOver(Graphics g) { //creates the game over screen
		this.setBackground(Color.black); //black background
		g.setColor(Color.red); //sets the color to red
		Font font = new Font("Impact", Font.ITALIC, 100); //creates font for string  
		g.setFont(font); //sets font
		g.drawImage(boss.getSkull(), 70, 225, null); //draw a skull
		g.drawImage(boss.getSkull(), 580, 225, null); //draw a skull
		g.drawString("GAME OVER", 130, 300); //draw string
		//WHITE QUIT BUTTON
		g.setColor(Color.white); //sets the color to white
		g.fillRect(275, 525, 200, 100); //creates the box for the button
		font = new Font("Monospaced", Font.PLAIN, 50); //new font
		g.setFont(font); //sets new font
		g.setColor(Color.black); //sets color to black
		g.drawString("Next", 310, 590); //draws the string
	}
	
	public Particle[] getConfetti() {
		return confetti;
	}

	public void setConfetti(Particle[] confetti) {
		this.confetti = confetti;
	}

	public void checkForOutofBounds() { //keeps the player and the boss within the playable area
		//if player leaves the boundary, set its x or y coord to the max or min possible
		if (player.getXCoord() < 0)
			player.setX(0);
		if (player.getXCoord() + player.getWIDTH() > 750)
			player.setX(749 - player.getWIDTH());
		if (player.getYCoord() < 0)
			player.setY(0);
		if (player.getYCoord() + player.getHEIGHT() > 615) {
			player.setY(620 - player.getHEIGHT());
		}

		//if boss leaves the boundary, set its x or y coord to the max or min possible
		if (boss.getXCoord() < 0)
			boss.setX(0);
		if (boss.getXCoord() + boss.getWIDTH() > 750)
			boss.setX(749 - boss.getWIDTH());
		if (boss.getYCoord() < 0)
			boss.setY(0);
		if (boss.getYCoord() + boss.getHEIGHT() > 620) {
			boss.setY(620 - boss.getHEIGHT());
		}

	}

	public class Controls implements KeyListener {
		// 2 = North
		// 1 = East
		// -2 = South
		// -1 = West
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if (e.getKeyCode() == KeyEvent.VK_N && player.isUsingSword()) { //key (N) for attacking
				if (!player.isAttacking() && !attacking) { //if the player is not attacking
					player.setAttacking(true); //set the attacking variables to true
					attacking = true;
					player.setTimer(0); // resetting the timer
					repaint();
				}
			} else if (!attacking){
				if (e.getKeyCode() == KeyEvent.VK_W && !player.isCollided()) { //if W is pressed
					up = true; //flip a switch
					player.setDirection(2); // NORTH
					repaint();
				}
				if (e.getKeyCode() == KeyEvent.VK_S && !player.isCollided()) { //if S is pressed
					down = true;//flip a switch
					player.setDirection(-2); // SOUTH
					repaint();
				}
				if (e.getKeyCode() == KeyEvent.VK_D && !player.isCollided()) { //if D is pressed
					right = true;//flip a switch
					player.setDirection(1); // EAST
					repaint();
				}
				if (e.getKeyCode() == KeyEvent.VK_A && !player.isCollided()) { //if A is pressed
					left = true;//flip a switch
					player.setDirection(-1); // WEST
					repaint();
				}
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			if (!attacking) {
				if (e.getKeyCode() == KeyEvent.VK_W && !player.isCollided()) { //if W is released
					up = false; //flip a switch
					player.setTimer(0); // resetting the timer
					if (player.isUsingSword())
						player.setCurrent(player.getStandingImages()[1]);
					else
						player.setCurrent(player.getStandingShieldImages()[1]);
					// player.imageStabilizer();
					player.setCenter(player.recalcCenter());
					repaint();
				}
				if (e.getKeyCode() == KeyEvent.VK_S && !player.isCollided()) { //if S is released
					down = false;//flip a switch
					player.setTimer(0); // resetting the timer
					if (player.isUsingSword())
						player.setCurrent(player.getStandingImages()[0]); //set the current image to the player looking down
					else
						player.setCurrent(player.getStandingShieldImages()[0]); //set the current image to the player looking down with a shield
					// player.imageStabilizer();
					player.setCenter(player.recalcCenter()); //recalculates the player's center
					repaint();
				}
				if (e.getKeyCode() == KeyEvent.VK_D && !player.isCollided()) { //if D is released
					right = false;//flip a switch
					player.setTimer(0); // resetting the timer
					if (player.isUsingSword())
						player.setCurrent(player.getStandingImages()[3]); //set the current image to player facing right
					else
						player.setCurrent(player.getStandingShieldImages()[2]); //set the current image to player right with a shield
					player.imageStabilizer(); //recenters the image
					repaint();
				}
				if (e.getKeyCode() == KeyEvent.VK_A && !player.isCollided()) { //if A is released
					left = false;//flip a switch
					player.setTimer(0); // resetting the timer
					if (player.isUsingSword()) //if using the sword
						player.setCurrent(player.getStandingImages()[2]); //set the current image to player facing left
					else
						player.setCurrent(player.getStandingShieldImages()[3]); //set the current image to player facing left with a shield
					player.imageStabilizer(); //recenters the image
					repaint();
				}
				if (e.getKeyCode() == KeyEvent.VK_M) { //if M is released
					if (!player.isCollided()) {
						player.setUsingSword(!player.isUsingSword());
						player.setAttacking(false);//flip a switch
						attacking = false;//flip a switch
						if (player.getShieldHealth() <= 12) //if there shield has no health, forcibly switch them back to the sword
							player.setUsingSword(true);//flip a switch
						if (!player.isUsingSword()) { //if the player is using a shield
							if (player.getDirection() == 2) //if looking NORTH
								player.setCurrent(player.getStandingShieldImages()[1]); //set the current image to the player looking up with a shield
							else if (player.getDirection() == -2) //if looking SOUTH
								player.setCurrent(player.getStandingShieldImages()[0]); //set the current image to the player looking down with a shield
							else if (player.getDirection() == -1) //if looking WEST
								player.setCurrent(player.getStandingShieldImages()[3]); //set the current image to the player looking left with a shield
							else if (player.getDirection() == 1) //if looking EAST
								player.setCurrent(player.getStandingShieldImages()[2]); //set the current image to the player looking right with a shield
						} else { //else the player is using a sword
							if (player.getDirection() == 2) //if looking NORTH
								player.setCurrent(player.getStandingImages()[1]); //set the current image to the player looking up with a sword
							else if (player.getDirection() == -2) //if looking SOUTH
								player.setCurrent(player.getStandingImages()[0]); //set the current image to the player looking down with a sword
							else if (player.getDirection() == 1) //if looking EAST
								player.setCurrent(player.getStandingImages()[3]); //set the current image to the player looking right with a sword
							else if (player.getDirection() == -1) //if looking WEST
								player.setCurrent(player.getStandingImages()[2]); //set the current image to the player looking left with a sword
						}
					}
					

				}
			}
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}
	}

	
	public Player getPlayer() {
		return player;
	}

	public Boss getBoss() {
		return boss;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setBoss(Boss boss) {
		this.boss = boss;
	}

	public class Mouse implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			if (start) {
				if (e.getX() <= 525 && e.getX() >= 175 && e.getY() >= 225 && e.getY() <= 325) {
					start = false;
					play = true;
				}
				
				if (e.getX() <= 525 && e.getX() >= 175 && e.getY() >= 375 && e.getY() <= 475) {
					start = false;
					play = false;
					quit = false;
					controls = true;
				}
				
				if (e.getX() <= 525 && e.getX() >= 175 && e.getY() >= 525 && e.getY() <= 625) {
					start = false;
					play = false;
					quit = true;
				}
			}
			
			if (gameOver) {
				if (e.getX() <= 525 && e.getX() >= 175 && e.getY() >= 525 && e.getY() <= 625) {
					start = false;
					play = false;
					thankYou = true;
				}
			}
			
			if (controls && e.getX() <= 525 && e.getX() >= 175 && e.getY() >= 525 && e.getY() <= 625) {
				controls = false;
				start = true;
			}
			
			if(youWin) {
				if (e.getX() <= 525 && e.getX() >= 175 && e.getY() >= 525 && e.getY() <= 625) {
					play = false;
					start = false;
					quit = false;
					thankYou = true;
				}
			}
			
			if(thankYou) {
				if (e.getX() <= 525 && e.getX() >= 175 && e.getY() >= 375 && e.getY() <= 475) {
					play = false;
					start = false;
					quit = true;
				}
			}
			
		}
	}


	public boolean isAttacking() {
		return attacking;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}
	
	

	
	
}

	
