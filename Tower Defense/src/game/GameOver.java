/**
 * This class will bring game over sign in
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

public class GameOver extends GameObject {
	private Control control;
	private State state;

	public GameOver (State state,Control control)
	{
		isVisible = true;
		isExpired = false;
		this.control = control;
		this.state = state;
	}
	@Override
	public void update(double timeElapsed) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(control.getImage("game_over.png"), 0, 0, null);

	}

}
