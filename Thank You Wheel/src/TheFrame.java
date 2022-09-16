import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JFrame;

public class TheFrame extends JFrame implements MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ColorPanel panel;
	
	public TheFrame() throws IOException {
		setSize(830, 850); // size of window
		setResizable(false); // can not change window size
		setTitle("Thank You Wheel"); // title of window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //binds window close with ending program
		getContentPane().setBackground(Color.cyan); // changing the default color to black
		setLocationRelativeTo(null); // puts window in the middle of the screen
		
		panel = new ColorPanel();
		panel.addMouseListener(this);
		add(panel);
		
		setVisible(true);
	}
	
	public ColorPanel getPanel() {
		return panel;
	}
	
	public void setPanel(ColorPanel p) {
		panel = p;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
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
		//e.translatePoint(400, 400);
		
		//-50, 300, 100, 50
		if(e.getX() > 350 && e.getX() < 450 && e.getY() < 750 && e.getY() > 700) {
			panel.setSpin(true);
		}
			
		
	}

}
