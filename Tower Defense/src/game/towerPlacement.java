/**
 * This class will create a salt tower in the menu bar.
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

public class towerPlacement  extends GameObject implements Clickable {

	private State state;
	private Control control;
	private int LocX, LocY;
	private int x , y;
	
	public towerPlacement(State s, Control c) {
		isVisible = true;
		isExpired = false;
		control = c;
		state = s;
	}

	@Override
	public boolean consumeClick() {
		//check boundary
		if (state.getMoney() < TowerBuying.COST)
			return false;
		
		if(x >= 637 && x <=654 && y >= 96 && y <=144){
			System.out.println("clicked");
			state.startFrame();
			state.addToMoney(-TowerBuying.COST);
			state.addGameObject(new TowerBuying(state, control));
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
		LocX = 610;
		LocY = 80;
		g.setColor(Color.black);
		g.fillRoundRect(LocX, LocY, 40, 40, 10, 10 );
		g.setColor(Color.gray);
		g.fillRoundRect(LocX + 10, LocY + 10, 35, 35, 10, 10);
		g.drawImage(control.getImage("salt.png"),LocX + 10,LocY + 10,null);
		
	}

}
