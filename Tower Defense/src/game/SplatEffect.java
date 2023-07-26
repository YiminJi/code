/**
 * This class is a sub class of Effect class. It will load the splat image
 * whenever car enemies will be defeated.
 * 
 * assignment #9
 * 
 * Class CS1420
 * @author  ---Yimin Jiang---
 * @version November 15, 2022
 */


package game;


import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class SplatEffect extends GameObject 
{
	private BufferedImage deadImage;
	private State state;
	private Control control;
	private Point position;
	private double x, y;
	private int frameCounter;

	public SplatEffect(State s, Point p, Control c) {
		control = c;
		state = s;
		position = p;
		this.x = p.x;
		this.y = p.y;
		isVisible = true;
		isExpired = false;
		deadImage = c.getImage("splat.png");
		this.frameCounter = 0;
	}
	

	public void update(double timeElapsed)
	{
		frameCounter++;
		
		// Control how long the puddle bullet will stay showing up on screen.
		if(frameCounter == 10)
			isExpired = true;
	}

	@Override
	public void draw(Graphics g) {
		//g.drawImage(control.getImage("beer.png"),x ,y ,null);
		g.drawImage(deadImage, (int)x, (int)y, null);

	}

}
