package game;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class FlyingBeer  extends GameObject
{
	private BufferedImage towerImage;
	private State state;
	private Control control;
	private Point position;
	private double x, y;
	private int frameCounter;
	private double vectorLength, vX, vY;
	
	public FlyingBeer(State s, Point p, Control c,  int vX, int vY) 
	{
		control = c;
		state = s;
		position = p;
		this.x = p.x;
		this.y = p.y;
		isVisible = true;
		isExpired = false;
		vectorLength = Math.sqrt(vX * vX + vY * vY);
		this.towerImage = c.getImage("puddle.png");
		this.vX = vX / vectorLength;
		this.vY = vY / vectorLength;
		this.frameCounter = 0;
	}

	
	/**
	 * This method will do several things below:
	 * 		-If enemies are close enough, then fire and send the bullet to the enemies.
	 * 		-If the bullets hit the enemies, then show up the effects.
	 * 			-If the enemy is snail, then show up splat effect.
	 * 			-If the enemy is car, then show up car crash effect.
	 */
	public void update(double timeElapsed) {
		position.x += (vX * 0.0001); // Change FlyingSalt Speed
		position.y += (vY * 0.0001);
		frameCounter++;

		// Control how long the puddle bullet will stay showing up on screen.
		if (frameCounter == 100)
			this.isExpired = true;

		Enemy nearbyEnemy = state.getClosetEnemy(position);
		if (nearbyEnemy == null)
			return;

		Point nearbyEnemyPoint = new Point(nearbyEnemy.getLocation().x - 25, nearbyEnemy.getLocation().y - 25);

		if (nearbyEnemy.getLocation().distance(position) <= 30) 
		{
			// No matter the enemy is defeated or not, bullets show up
			state.addGameObject(this);
			if (this.getLocation().distance(nearbyEnemyPoint) <= 200) 
			{
					// Check if the enemy is snail or not.
					if (nearbyEnemy.getImages().equals(control.getImage("snail.png"))) {
						state.addGameObject(new SplatEffect(state, nearbyEnemyPoint,control));
						nearbyEnemy.isExpired = true;
						
						// Once one snail enemy is defeated, player get 10 credits
						state.adjustScore(10);
						state.addToMoney(5);
					} else if(nearbyEnemy.getImages().equals(control.getImage("s-cargo.png"))){
						state.addGameObject(new SplatEffect(state, nearbyEnemyPoint,control));
						nearbyEnemy.isExpired = true;
						
						// Once one car enemy is defeated, player get 20 credits
						state.adjustScore(20);
						state.addToMoney(10);
					}
				// Avoid add credits multiple times.
					nearbyEnemy.isExpired = true;
			}
		}
		
	}
	
	public Point getLocation()
	{
		return new Point ((int) x, (int) y);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(towerImage, position.x, position.y, null);
		
	}

}
