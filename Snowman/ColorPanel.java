//Jason Zhou
// 1/20/2021
//Period 7
//AP CS
//Mr Mouradov

//importing libraries
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class ColorPanel extends JPanel {//this class extends the JPanel class and creates all the images for window

	// do not need a default and all parameter constructor
	//declaring global variables for later use
	private Color backgroundColor;
	private Color skyColor;
	private Color stickColor;
	private int numberOfSnowballs;
	private int snowballWidth;
	private int snowballHeight;
	private int snowballX;
	private int snowballY;
	
	//x and y coordinate arrays for the scarf
	int[] xCoords;
	int[] yCoords;
	
	//making variables for the positions of the first and last point of the scarf, and the y value of the first point of the scarf
	int scarfX;
	int scarfEndX; 
	int scarfY;
	
	//x and y coordinate arrays for the ground
	int[] groundX;
	int[] groundY;

	//creates the variables for the scarf to create it
	boolean scarfMade = false;
	int snow;
	int night;
	twig[] sticks;
	int crazyHair;
	snowflake[] snowflakes = new snowflake[100];

	public ColorPanel(int numberOfSnowballs, int x, int y, int snow, int crazyHair, int night) //constructor that sets up the values for variables in order to create the image
	{
		//sets new values for the class variables
		this.snow = snow;
		this.crazyHair = crazyHair;
		this.night = night;
		this.numberOfSnowballs = numberOfSnowballs;
		sticks = new twig[20]; //points this variable to a new array of twigs
		
		skyColor = new Color(76, 206, 255);
		stickColor = new Color(122, 84, 99);
		for (int i = 0; i < snowflakes.length; i++)
			snowflakes[i] = new snowflake();
		if (night == 1) //if night is 1, then set the background to a dark color for the sky
			this.setBackground(new Color(10, 30, 57));
		else //if night is not 1, then set the background to the color used for day
			this.setBackground(skyColor);
		

		//sets values for the variables to make the snowballs for the snowman later
		snowballWidth = x / 4;
		snowballHeight = x / 4;
		snowballX = (int) (x * 0.654);
		snowballY = (int) (y * 0.437);
		
		//provides these variables with new objects to store the coordinates for the ground
		groundX = new int[x];
		groundY = new int[groundX.length];

		for (int i = 0; i < x; i++) { //iterates the same amount of times as there are pixels for the width of the screen
			groundX[i] = i; //stores i inside the x coordinate array
			groundY[i] = (int) (-(y / 30) * Math.sin(((2 * Math.PI) / x) * i) + y * 3 / 5); //runs a sine function to calculate the y level of the ground
		}

		// sets the last two points for the corners of the window
		// bottom right corner
		groundX[x - 2] = x;
		groundY[x - 2] = y;

		// bottom left corner
		groundX[x - 1] = 0;
		groundY[x - 1] = y;
		

		for (int i = 0; i < sticks.length; i++) { //iterates through the sticks array
			sticks[i] = new twig(x * 2 / 5, y * 2 / 3, 50, groundY); //assigning new stick objects to each index in the array
		}
		
		//setting scarf variables to make the scarf later
		scarfX = (snowballX - snowballHeight / 10) - snowballWidth / 10;
		scarfEndX = snowballX + snowballWidth;
		scarfY = (snowballY + snowballHeight / 10) + snowballHeight;

		
		
		if (this.getHeight() < 245) // if the y scale of the window goes under a certain amount, re-adjust the
			// scaling
			scarfY = (snowballY + snowballHeight / 10) + 3 * snowballHeight / 5;
		else if (this.getHeight() < 405)// if the y scale of the window goes under a certain amount, re-adjust the
			// scaling
			scarfY = (snowballY + snowballHeight / 10) + 4 * snowballHeight / 5;
		//creates the x coordinates for the polygon for the scarf
		xCoords = new int[] { scarfX, scarfEndX, scarfEndX * 21 / 20, scarfEndX * 43 / 40, scarfEndX * 21 / 20,
				scarfEndX * 41 / 40, scarfX };
		//creates the y coordinates for the polygon for the scarf
		yCoords = new int[] { scarfY, scarfY * 19 / 20, scarfY * 21 / 20, scarfY * 6 / 5, scarfY * 7 / 5,
				scarfY * 11 / 10, scarfY * 11 / 10 };

	}

	public void paintComponent(Graphics g) { //paints things onto the window
		super.paintComponent(g); //builds off of the paintComponent method from the parent class, JPanel
		g.setColor(backgroundColor); //sets the background

		
		//declaring local variables for future reference
		int moonX = this.getWidth() / 8;
		int moonY = this.getHeight() / 8;
		int moonArcWidth = this.getWidth() / 6;
		int moonArcHeight = this.getHeight() / 6;
		int moonStartingAngle = 50;
		int moonAngleExtension = 200;

		// resets the variables for the arc, so that the hat does not constantly move
		int arcY = snowballY + snowballHeight / 10;
		int arcX = snowballX - snowballHeight / 10;
		int arcWidth = snowballWidth;
		int arcHeight = snowballHeight;
		int decreasingAngle = 160;
		int increasingAngle = -20;
		
		
		
		// g.fill3DRect(300, 50, 100, 200, true); //makes a rectangle
		// resets the variables so nothing funky happens
		snowballWidth = this.getWidth() / 4;
		snowballHeight = this.getWidth() / 4;
		snowballX = (int) (this.getWidth() * 0.654);
		snowballY = (int) (this.getHeight() * 0.437);

		g.setColor(Color.white); //sets the color to white

		for (int i = 0; i < numberOfSnowballs; i++) { //repeats as many times as there are snowballs
			g.fillOval(snowballX, snowballY, snowballWidth, snowballHeight); // draws an 'oval' AKA the snowball
			if (i != numberOfSnowballs - 1) { //if the last snowball has not been made, then set the snowball variables to make the next snowball
				snowballX += (snowballWidth / 8);
				snowballY /= 1.5;
				snowballWidth = (snowballWidth / 4) * 3;
				snowballHeight = (snowballHeight / 4) * 3;
			}

		}

		// drawing the ground
		g.setColor(Color.white); //sets the color to white
		g.fillPolygon(groundX, groundY, groundX.length);
		g.setColor(Color.black); //sets the color to black
		g.drawPolygon(groundX, groundY, groundX.length);

		

		// draws the hat or crazy hair depending on user input
		g.setColor(Color.blue); //changes the color to blue
		int repeat = this.getHeight() / 20; //changes the variable based on the height of the window


		if (crazyHair != 1) //if crazy hair is not equal to one, then draw a hat
			for (int i = 0; i < repeat; i++)
				g.drawArc(arcX++, arcY--, arcWidth, arcHeight, increasingAngle++, decreasingAngle--);
		else { //else draw crazy hair by changing the color to red and changing the positions of the arc
			g.setColor(Color.red); //sets the color to red
			arcY -= 7; //changes the position the arcs to be 7 up
			if (numberOfSnowballs == 2) //if the snowman is 2 snowballs tall, then move the position of the arc 12 pixels to the right
			{
				arcX += 3 * this.getWidth() / 125;
				arcY -= this.getHeight() / 250;
			}
				
			else //else move the position of arc 12 pixel to the right
			{
				arcX += this.getWidth() / 50 ; //changes the position the arcs to 1 / 50th of the width down
				arcY += this.getWidth() / 250; //moves the hair a couple pixels down
			}
				
			for (int i = 0; i < repeat; i++) //repeatedly draws arcs to make the hair
				g.drawArc(arcX--, arcY--, arcWidth, arcHeight, increasingAngle++, decreasingAngle--); //draws arcs with different positions and lengths
			g.setColor(Color.white);
		}

		//sets the variables for making the arc
		arcY = snowballY + snowballHeight / 10;
		arcX = snowballX - snowballHeight / 10;
		arcX += repeat;
		arcY -= repeat;

		// System.out.println(scarfX);
		g.setColor(Color.cyan);
		g.fillPolygon(xCoords, yCoords, 7);

		g.setColor(Color.red);

		// changes the color to black
		g.setColor(Color.black);
		g.fillOval(snowballX * 18 / 16, snowballY + snowballHeight / 5, snowballWidth / 9, snowballHeight / 7); // draws the eye
		g.fillOval(snowballX * 21 / 20, snowballY + snowballHeight / 5, snowballWidth / 15, snowballHeight / 7); // draws the left eye

		// changes the color to orange
		g.setColor(Color.orange);
		
		// draws the carrot nose by setting up coordinates for points of the triangle that makes the carrot
		g.fillPolygon(new int[] { snowballX * 15 / 14, snowballX * 14 / 15, snowballX * 15 / 14 },
				new int[] { snowballY + snowballHeight / 2, snowballY * 7 / 5, snowballY + snowballHeight / 3 }, 3);

		g.setColor(stickColor); //changes the color to be the color of a stick
		
		if (!scarfMade) { //if the scarf has not been made, then make it once and switch the scarfMade variable to true
			//sets the proper numbers for the scarf
			scarfX = arcX - snowballWidth / 10;
			scarfEndX = snowballX + snowballWidth;
			scarfY = arcY + snowballHeight;
			scarfMade = true;
			if (numberOfSnowballs == 3) //if the snowman is three snowballs tall, then move the scarf a little to the left
				scarfX -= 10;

			//creates the array of x coordinates and y coordinates for the scarf to use later
			xCoords = new int[] { scarfX, scarfEndX, scarfEndX * 21 / 20, scarfEndX * 43 / 40, scarfEndX * 21 / 20,
					scarfEndX * 41 / 40, scarfX };
			yCoords = new int[] { scarfY, scarfY * 19 / 20, scarfY * 21 / 20, scarfY * 6 / 5, scarfY * 7 / 5,
					scarfY * 11 / 10, scarfY * 11 / 10 };
		}

		// drawing the moon

		if (night == 1) { //if it is night then draw a moon, set the sky to a night color and then set the color back to white
			g.setColor(new Color(255, 255, 210)); //color of the moon
			//creates a for loop to repeatedly draw arcs
			for (int i = 0; i < 20; i++)
				//draws an arc that gets smaller and slightly changes position every iteration
				g.drawArc(moonX++, moonY++, moonArcWidth, moonArcHeight, moonStartingAngle++, moonAngleExtension -= 3);
			g.setColor(Color.white); //sets the color back to white
		}
		
		drawTwig(g); //draws and animates the twig
		animateScarf(xCoords, yCoords); //animates the scarf
		g.setColor(Color.white); //sets the color back to white
		if (snow == 1) //if the user wanted to snow, then draw and animate snowflakes
			drawSnowflake(g);
	}

	public void drawTwig(Graphics g) { //draws and animates the twig
		//sets the color to brown, for the stick
		g.setColor(stickColor);
		twig.setCount(twig.getCount() + 1); //increases the static counter for the twig class
		//iterates through the 'sticks' array
		for (int i = 0; i < sticks.length; i++) {
			//draws the first segment of the stick
			g.drawLine(sticks[i].getX(), sticks[i].getY(), sticks[i].getMidX(), sticks[i].getMidY());
			//draws the second segment of the stick
			g.drawLine(sticks[i].getMidX(), sticks[i].getMidY(), sticks[i].getEndX(), sticks[i].getEndY());

			//if the static counter for the twig class is under 40, move the twigs to the right
			if (sticks[i].getCount() < 40) {
				sticks[i].setEndX(sticks[i].getEndX() + 1);
				if (twig.getCount() % 2 == 0) //if the counter is even, move the twi's y coordinate up one
					sticks[i].setEndY(sticks[i].getEndY() + 1);

			} else {//if the static counter is above 40, move the twigs to the left
				sticks[i].setEndX(sticks[i].getEndX() - 1);
				if (twig.getCount() % 2 == 0) //if the counter is an even number, move the twig's y coordinate down one pixel
					sticks[i].setEndY(sticks[i].getEndY() - 1);
			}
		}


	}

	public void animateScarf(int[] xCoords, int[] yCoords) {//animates the scarf for the snowman
		
		if (twig.getCount() < 40) {//if the static counter for the twig class is below 40, move the fourth point of the scarf right one and up one
			//then move the 5th point of the scarf right one and up one
			xCoords[3] += 1;
			yCoords[3] -= 1;
			xCoords[4] += 1;
			yCoords[4] -= 1;
		} else { //if the static counter for the twig class is above 40, move the fourth point of the scarf left one and down one
			//then move the 5th point of the scarf left one and down one
			xCoords[3] -= 1;
			yCoords[3] += 1;
			xCoords[4] -= 1;
			yCoords[4] += 1;
		}

	}

	public void drawSnowflake(Graphics g) {//draws and animates the snowflakes for the picture
		
		//iterates through the snowflakes array
		for (int i = 0; i < snowflakes.length; i++) {
			//draws the horizontal line
			g.drawLine(snowflakes[i].getX() - 3, snowflakes[i].getY(), snowflakes[i].getX() + 3, snowflakes[i].getY()); // horizontal
										
			//draws the vertical line
			g.drawLine(snowflakes[i].getX(), snowflakes[i].getY() - 3, snowflakes[i].getX(), snowflakes[i].getY() + 3); // vertical
			
			// top left to bottom right, diagonal
			g.drawLine(snowflakes[i].getX() - 3, snowflakes[i].getY() - 3, snowflakes[i].getX() + 3,
					snowflakes[i].getY() + 3); 
			// top right to bottom left, diagonal
			g.drawLine(snowflakes[i].getX() + 3, snowflakes[i].getY() - 3, snowflakes[i].getX() - 3,
					snowflakes[i].getY() + 3); 
			//moves the y position of the snowflake down
			snowflakes[i].setY(snowflakes[i].getY() + 3);
			//moves the snowflake in either the left or right direction, random
			snowflakes[i].setX(snowflakes[i].getX() + (int) (Math.random() * 5 - 2));

			if (snowflakes[i].getY() > this.getHeight()) //if the y position of the snowflake is greater than the height, reset it to above the screen
				snowflakes[i].setY(snowflakes[i].getY() - this.getHeight() - 10);

			if (snowflakes[i].getX() > this.getWidth()) //if the snowflake's x coordinate goes over the width, then reset it to the left of the screen
				snowflakes[i].setX(snowflakes[i].getX() - this.getWidth() - 5);
		}


	}

}
