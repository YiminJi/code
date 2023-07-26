/**
 * This is the super class for this tower defense game 
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

abstract public class GameObject 
{
	protected boolean isVisible;
	protected boolean isExpired;
	
	public boolean isVisible()
	{
		return isVisible;
	}
	
	public boolean isExpired()
	{
		return isExpired;
	}
	
	abstract public void update(double timeElapsed);
	abstract public void draw(Graphics g);

//	protected abstract boolean isInside(int x, int y);
//
//	protected abstract void move(int deltaX, int deltaY);
}
