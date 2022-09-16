//Jason Zhou
// 1/20/2021
//Period 7
//AP CS
//Mr Mouradov

//importing libraries
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.Scanner;

public class Driver { //driver class for the color panel

	public static void main(String[] args) throws InterruptedException {//main function that runs the main logic
		// TODO Auto-generated method stub
		
		//creating variables for future use
		JFrame frame = new JFrame(); //creates the frame the program will use
		//makes choices for how many snowballs there should be
		Integer[] choices = new Integer[] {2,3};
		//smakes choices for the dimensions the user can use
		Integer[] dimensions = new Integer[] {500, 600, 700, 800};
		//gives a yes or no option to users 0 = No, 1 = Yes
		Integer[] yOrN = new Integer[] {0,1};
		//sets up the ColorPanel class pointer for future use
		ColorPanel myPicture = null;
		
		
		
		//creates an Integer 
		Integer redo = null;
		do
		{
			//asks the user for the size of the frame and stores it into the 'Frame' variable 
			Integer Frame = (Integer) JOptionPane.showInputDialog(frame, "What Would You Like the Dimensions to be(From 500 to 800, N x N)?", "Input", JOptionPane.QUESTION_MESSAGE, null, dimensions, dimensions[0]);
			
			Integer snowballCount = (Integer) JOptionPane.showInputDialog(frame, "How many Snowballs Tall Would You Like the Snowman to be?", "Input", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
			
			//System.out.println("Would You Like It To Snow(1 = Yes, Anything Else = No): ");
			Integer snow = (Integer) JOptionPane.showInputDialog(frame, "Would You Like It To Snow(1 = Yes, 0 = No)?", "Input", JOptionPane.QUESTION_MESSAGE, null, yOrN, yOrN[1]);
			//int snow = Integer.parseInt(input2);
			
			//System.out.println();
			Integer crazyHair = (Integer) JOptionPane.showInputDialog(frame, "Would You Like Your Snowman To Have Crazy Hair(1 = Yes, 0 = No)?", "Input", JOptionPane.QUESTION_MESSAGE, null, yOrN, yOrN[0]);
			
			Integer night = (Integer) JOptionPane.showInputDialog(frame, "Would You Like it to be Night(1 = Yes, 0 = No)?", "Input", JOptionPane.QUESTION_MESSAGE, null, yOrN, yOrN[0]);
			
			frame.setSize(Frame, Frame); //sets the size
			
			//sets the window to not resizable
			frame.setResizable(false); //decides whether the window is resizable
			//sets the program to close after the window closes
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			
			if (redo != null) //if redo is not equal to null, then remove the current panel
				frame.getContentPane().remove(myPicture);
			
			//makes a new ColorPanel object to display on the frame
			myPicture = new ColorPanel(snowballCount, Frame, Frame, snow, crazyHair, night);
			
			frame.getContentPane().add(myPicture); //adds myPicture to the frame
			frame.setVisible(true); //sets the visibility(whether you can see it or not)
			
			for (int i = 0; i < 320; i++) //repeats the animated picture for 400 iterations
			{
				if (twig.getCount() >= 80) //if the static counter for the twig class is above 80, then reset it to 0
					twig.setCount(0);
				frame.repaint(); //repaint the frame to update the animation
				Thread.sleep(20); //delay it for smooth animation that the user can see
			}
			//asks whether the user wants to go again and stores it inside the 'redo' variable
			redo = (Integer) JOptionPane.showInputDialog(frame, "Would You Like to Do Play This Again(1 = Yes, 0 = No)?", "Input", JOptionPane.QUESTION_MESSAGE, null, yOrN, yOrN[1]);
		}while(redo == 1); //if redo is equal to one, then run everything inside the do-while loop again
	}

}
