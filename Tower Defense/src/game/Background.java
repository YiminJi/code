/**
 * This class will bring background picture in.
 * 
 * 
 * assignment #9
 * 
 * Class CS1420
 * @author  ---Yimin Jiang---
 * @version November 15, 2022
 */

package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Background extends GameObject
{
	private Control control;
	private State state;

	/**
	 * set background to draw
	 */
	public Background (State state,Control control)
	{
		isVisible = true;
		isExpired = false;
		this.control = control;
		this.state = state;
	}
	
	/**
	 * background do not move
	 */
	public void update(double timeElapsed)
	{
		
	}
	
	/**
	 * get the picture for the background
	 */
	public void draw(Graphics g) 
	{	
		g.drawImage(control.getImage("path_2.jpg"), 0, 0, null);
		
	}
}
