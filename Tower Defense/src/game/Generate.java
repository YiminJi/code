/**
 * This class set the generation of the enemy
 * how fast and how many
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

public class Generate extends GameObject
{
	private double timeToNextGeneration = 1.0;
	private Control control;
	private State state;
	private int counter = 0;

	/**
	 * Contractor here the visible has being set to false
	 * because we don't need to see, this class only generate new enemy
	 * @param state
	 * @param control
	 */
	public Generate(State state, Control control) {
		isVisible = false;
		isExpired = false;
		this.control = control;
		this.state = state;
	}

	/**
	 * this is how we generate the new enemy
	 * if the enemy reach to certain number 
	 * we will generate enemy in different way.
	 */
	public void update(double timeElapsed) {
		timeToNextGeneration -= timeElapsed;
		
		if (timeToNextGeneration <= 0) 
		{
			counter ++;
			state.addGameObject(new Snail(state, control));
			if(counter % 10 > 5) {
				timeToNextGeneration += 1.0;
			}else {
				timeToNextGeneration += 2.0;
			}
		}
		
	}

	@Override
	public void draw(Graphics g) {}

}
