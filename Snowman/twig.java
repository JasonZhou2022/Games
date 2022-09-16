//Jason Zhou
// 1/20/2021
//Period 7
//AP CS
//Mr Mouradov

public class twig { //class for the twigs that appear in the image
	//establishing variables for the coordinates of the stick
	//starting point of the line
	private int x;
	private int y;
	//mid endpoint of the first segment, "middle" of the twig
	private int midX;
	private int midY;
	//end of the twig, end of the second segment
	private int endX;
	private int endY;
	//establishes a static counter for the class to be used later on
	private static int count;
	
	public twig(int xLimit, int yLimit, int length, int[] groundY) //constructor that sets the position of the twigs
	{
		//sets the x and y for the bottom of the stick
		x = (int)(Math.round((Math.random() * xLimit))); 
		y= (int)Math.round((yLimit + Math.random() * 50 - 25) - yLimit / 7.0);
		if (y < groundY[x]) //if the y is less than the y coordinate of the ground, then set the y equal to y of the ground
			y = groundY[x];
		
		//calculate the middle point of the stick
		midX = (int)(Math.round((x + Math.random() * xLimit / 25.0 - xLimit / 40.0)));
		midY = (int)(Math.round(y - Math.random() * yLimit / 8.0));
		
		//calculates where the end of the stick is
		endX = (int)(Math.round(midX + Math.random() * xLimit / 10.0 - xLimit / 20.0));
		endY = (int)(Math.round(midY - Math.random() * yLimit / 8.0));
	}
	
	//getters and setters
	public int getX() {return x;} //gets the x value linked to the bottom of the stick
	public int getMidX() {return midX;} //gets the mid X of the stick
	public int getEndX() {return endX;} //gets the x at the end of the stick
	
	public int getY() {return y;} //gets the x value linked to the bottom of the stick
	public int getMidY() {return midY;}//gets the mid Y of the stick
	public int getEndY() {return endY;}//gets the y at the end of the stick
	
	public void setX(int newX) {x = newX;} //sets a new x value for the x that is linked to the bottom of the x
	public void setMidX(int newMidX) {midX = newMidX;} //sets a new x value for the middle x of the stick
	public void setEndX(int newEndX) {endX = newEndX;} //sets a new x value for the last x coordinate of the stick
	
	public void setY(int newY) {y = newY;}//sets a new y value for the y that is linked to the bottom of the y
	public void setMidY(int newMidY) {midY = newMidY;} //sets a new y value for the middle x of the stick
	public void setEndY(int newEndY) {endY = newEndY;}//sets a new y value for the last y coordinate of the stick
	
	//gets the static count for the class
	static int getCount() {return count;}
	//sets the static count for the class
	static void setCount(int c) {count = c;}
	
	
}
