import java.io.IOException;

public class Driver {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//JFrame setup
		TheFrame frame = new TheFrame();

		//variables for spinning wheel
		int timer = 0;
		double degree = -1;
		double increment = 1;
		
		//variables for messages
		int index = ((int)frame.getPanel().getStartDegree()) / 30;
		if (index == 12)
			index--;
		
		boolean buffer = false;
		double start = 0;
		
		frame.setVisible(true);
		
		while(true) {
			if (frame.getPanel().getSpin())
				timer++;
			
			if (timer != 0) {
				//makes the wheel spin
				if (degree <= 360 && frame.getPanel().getSpin())
					degree += increment;
				else {
					degree = 0;
				}
				
				if ((int)degree % 30 == 0 && !buffer) {
					index++;
					buffer = true;
					start = degree;
				}
				else if (buffer)
					if (Math.abs(degree - start) >= 1) {
						
						buffer = false;
					}
				
				
				if (index >= 12)
					index = 0;
				frame.getPanel().setDegree(degree);
				
				//slowing rotation of wheel
				if (timer % 1000 == 0 && timer < 10000)
					increment -= 0.1;
				else if (timer % 1000 == 0)
					increment -= 0.01;
				
				
				
				if (increment <= 0) //keep increment at 0 once it hits 0
					increment = 0;
				
				
				if (increment == 0)  {
					frame.getPanel().setMessageIndex(index);
				}
			}
			
			
				
				//
			
				
			
			frame.getPanel().repaint();
			frame.setVisible(true);
		}
	}

}
