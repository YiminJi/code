/**
 * This class will create enemy objects
 * 
 * 
 * assignment #9
 * 
 * Class CS1420
 * @author  ---Yimin Jiang---
 * @version November 15, 2022
 */


package game;

public class EnemySCargo extends Enemy {

	public EnemySCargo(State state, Control control) {
		super(state, control);
		this.Enemyimage = control.getImage("s-cargo.png");
		this.diffPercentage = 0.004; 
	}

}
