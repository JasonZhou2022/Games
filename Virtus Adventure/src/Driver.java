import java.io.IOException;

import javax.swing.*;
//import javax.awt.*;

public class Driver {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		
		//myFrame frame = new myFrame();
		JFrame frame = new JFrame();
		frame.setSize(750, 750);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		
		
		 ColorPanel panel = new ColorPanel();
		
		 
		 
		//frame.add(startScreen);
		frame.getContentPane().add(panel);
		//frame.getContentPane.add(panel);
		frame.setVisible(true);
		int x = 5;
		frame.setTitle("Virtus's Adventure");
		frame.setResizable(false);
		frame.setVisible(true);

		while (x == 5) {

			frame.repaint();
			Thread.sleep(10);
		}
		 
		
	}
	

}
