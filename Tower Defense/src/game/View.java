/**
 * This class will initiate the GUI window
 * 
 * 
 * assignment #9
 * 
 * Class CS1420
 * @author  ---Yimin Jiang---
 * @version November 15, 2022
 */

package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JPanel
{
	private Control control;
	private State state;
	
	/**
	 * initiate the JPanel here
	 * @param control
	 * @param state
	 */
	public View(Control control, State state)
	{
		this.control = control;
		this.state = state;
		
		JFrame frame = new JFrame("Tower Defense");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setMinimumSize(new Dimension(800,600));
		this.setPreferredSize(new Dimension(800,600));
		this.setMaximumSize(new Dimension(800,600));
		
		frame.setContentPane(this);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	
	public void paint(Graphics g)
	{
		for (GameObject go : state.getFrameObjects())
            if (go.isVisible() && !go.isExpired())
                go.draw(g);
	}
}
