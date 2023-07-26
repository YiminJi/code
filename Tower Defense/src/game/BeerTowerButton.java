package game;

import java.awt.Color;
import java.awt.Graphics;

public class BeerTowerButton extends GameObject implements Clickable 
{
	private State state;
	private Control control;
	private int LocX, LocY;
	private int x , y;
	
	public BeerTowerButton(State s, Control c) {
		isVisible = true;
		isExpired = false;
		control = c;
		state = s;
	}

	@Override
	public boolean consumeClick() {
		if (state.getMoney() < BeerTower.COST)
			return false;
		
		if(x >= 715 && x <=738 && y >= 102 && y <=132){
			System.out.println("clicked");
			state.startFrame();
			state.addToMoney(-BeerTower.COST);
			state.addGameObject(new BeerTower(state, control));
			state.finishFrame();
			return true;
			
		}
		return false;
	}

	@Override
	public void update(double timeElapsed) {
		x = control.getMouseX();
		y = control.getMouseY();

		
	}

	@Override
	public void draw(Graphics g) {
		LocX = 680;
		LocY = 80;
		g.setColor(Color.black);
		g.fillRoundRect(LocX, LocY, 40, 40, 10, 10 );
		g.setColor(Color.gray);
		g.fillRoundRect(LocX + 10, LocY + 10, 35, 35, 10, 10);
		g.drawImage(control.getImage("beer.png"),LocX + 20,LocY + 15,null);
		
	}

}
