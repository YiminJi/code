package game;

import java.awt.Graphics;
import java.awt.Point;

public class BeerTower extends GameObject implements Clickable
{
	public static final int COST = 100;
	
	private State state;
	private Control control;
	private int x , y;
	private boolean isMoving;
	private int frameCounter;
	private Point position;
	
	private double cooldownTime = 0.5;
	
	public BeerTower(State state, Control control)
	{
		isVisible = true;
		isExpired = false;
		isMoving = true;
		
		this.state = state;
		this.control = control;
		
		frameCounter = 0;
		
	}

	@Override
	public boolean consumeClick() {
		if(isMoving) {
			if (x < 0 || x >= 600 || y < 0 || y >= 600)
			{
				isExpired = true;
				state.addToMoney(TowerBuying.COST / 2);
				return true;
			}
			isMoving = false;
			return true;
		}
		return false;
	}

	@Override
	public void update(double timeElapsed) {
		frameCounter++;// Whenever update method get called, frameCounter will plus 1
		position = new Point(x,y);
		if (isMoving) {
			x = control.getMouseX();
			y = control.getMouseY();
		}else {
			cooldownTime -= timeElapsed;
			if (cooldownTime <= 0)
			{
			Enemy e = state.getClosetEnemy(position);
			// If the distance < 50, fire some salt at it.
			// and reset the cooldown to 1 second.
			}
		}
		position = new Point(x,y);

		// Control how long the salt crystal bullet will stay showing up on screen.
		if (frameCounter % 50 != 0)
			return;
//		
		Enemy nearbyEnemy = state.getClosetEnemy(position);

		if(nearbyEnemy == null)
			return;
		
		int deltaX = nearbyEnemy.getLocation().x - x;
		int deltaY = nearbyEnemy.getLocation().y - y;

		// The shooting range for the beer tower is 150 pixels.
		if(nearbyEnemy.getLocation().distance(position) <= 150)
		{
			FlyingBeer e = new FlyingBeer(state, new Point(position),control,  deltaX, deltaY);
			state.addGameObject(e);
//			System.out.println(e);
		}
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(control.getImage("beer.png"),x ,y ,null);
		
	}

}
