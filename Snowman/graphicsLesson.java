//Jason Zhou
// 1/20/2021
//Period 7
//AP CS
//Mr Mouradov

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class graphicsLesson {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		JFrame frame = new JFrame();
		frame.setSize(600, 600); //sets the size
		frame.setVisible(true); //sets the visibility(whether you can see it or not)
		frame.setResizable(false); //decides whether the window is resizable
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//String input = JOptionPane.showInputDialog("What Is Your Name?");
		//Integer[] choices = {1,2,3};
		//makes a drop down option bar
		//Integer input2 = (Integer) JOptionPane.showInputDialog(frame, "Some other number", "Give me a number", JOptionPane.QUESTION_MESSAGE, null, choices, choices[2]);
		
		Pictures myPicture = new Pictures(Color.magenta);
		frame.getContentPane().add(myPicture);
		
		for (int i = 0; i < 200; i++)
		{
			frame.repaint();
			Thread.sleep(20);
		}
	}

}
