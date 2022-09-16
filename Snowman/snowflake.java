//Jason Zhou
// 1/20/2021
//Period 7
//AP CS
//Mr Mouradov

public class snowflake {//class for the snowflakes that can appear in the image
	//establishing class variables that relate to the x and y coordinates of the snowflake object
	int x;
	int y;
	
	public snowflake() //basic constructor that sets a random x and y coorindate for the snowflake
	{
		x = (int)(Math.random() * 600);
		y = -(int)(Math.random() * 600);
	}
	
	public int getX() {return x;} //gets the x coordinate of the snowflake
	public int getY() {return y;} //gets the y coordinate of the snowflake
	
	public void setX(int x) {this.x = x;} //sets a new x value for the x coordinate of the snowflake
	public void setY(int y) {this.y = y;} //sets a new y value for the y coordinate of the snowflake
}
