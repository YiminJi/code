/**
 * This class will create a menu bar
 * 
 * 
 * assignment #9
 * 
 * Class CS1420
 * @author  ---Yimin Jiang---
 * @version November 15, 2022
 */
package game;

import java.awt.Color;
import java.awt.Graphics;

public class Menu extends GameObject
{
	private State state;
	private Control control;
	
	public Menu(State state, Control control) 
	{
		isVisible = true;
		isExpired = false;
		
		this.state = state;
		this.control = control;
	}
	@Override
	public void update(double timeElapsed) {
		
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(600, 0, 200, 600);
		g.setColor(Color.black);
		g.setFont(g.getFont().deriveFont(20.0f));
		g.drawString("Hello", 680,40);
		g.setColor(Color.black);
		
		// set score area
		g.setFont(g.getFont().deriveFont(15.0f));
		g.drawString("lives: ", 600,520);
		g.setColor(Color.black);
		g.setFont(g.getFont().deriveFont(15.0f));
		g.drawString("score: ", 600,550);
		g.setColor(Color.black);
		g.setFont(g.getFont().deriveFont(15.0f));
		g.drawString("money: ", 600,580);
		
		//set the corresponding 
		g.setColor(Color.black);
		g.setFont(g.getFont().deriveFont(15.0f));
		g.drawString(" " + state.getLives(), 640,523);
		g.setColor(Color.black);
		g.setFont(g.getFont().deriveFont(15.0f));
		g.drawString(" " + state.getScore(), 650,553);
		g.setColor(Color.black);
		g.setFont(g.getFont().deriveFont(15.0f));
		g.drawString(" " + state.getMoney(), 650,583);
	}


}
