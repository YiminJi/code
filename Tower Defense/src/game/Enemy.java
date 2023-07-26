/**
 * The enemy class is going to set all the features(such as enemy image, 
 * enemy moving speed-percentage, etc) that enemies need. And will make 
 * enemy subclass simpler.
 * 
 * assignment #9
 * 
 * Class CS1420
 * @author  ---Yimin Jiang---
 * @version November 15, 2022
 */


package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Scanner;

import path.Path;

public class Enemy extends GameObject
{
	protected Control control;
	protected State state;
	protected Path path;
	protected double percentage;
	protected BufferedImage Enemyimage;
	
	protected double diffPercentage;
	
	public Enemy(State state, Control control)
	{
		percentage = 0;
		isVisible = true;
		isExpired = false;
		this.control = control;
		this.state = state;

	}

	@Override
	public void update(double timeElapsed) {
		double velocity = 1.0/10 + diffPercentage;
		percentage += velocity * timeElapsed;
		
		if(percentage > 0.99) 
		{
			isExpired = true;
			//state.addGameObject(new Snail(state, control));
			state.liveDecrease(1);
		}
		
	}

	@Override
	public void draw(Graphics g) {
		Path path = control.getpath();
		Point loc = path.convertToCoordinates(percentage);
		g.drawImage(Enemyimage, loc.x , loc.y, null);
		
	}

	public Point getLocation()
	{
		Path p = control.getpath();
		Point loc = p.convertToCoordinates(percentage);
		return loc;
	}
	
	/**
	 * Get the image that loads from different enemies sub class.
	 * 
	 * @return a BufferedImage object, image that loads from different enemies sub class
	 */
	public BufferedImage getImages()
	{
		return Enemyimage;
	}

}
