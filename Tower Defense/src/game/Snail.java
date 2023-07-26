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


import java.awt.Graphics;
import java.awt.Point;

import path.Path;

public class Snail extends Enemy
{
	

    /**
     * constructor for snail enemy
     * 
     */
    public Snail(State state, Control control) 
    {
    	super(state, control);
    	this.Enemyimage = control.getImage("snail.png");
    	
    	this.diffPercentage = 0.0000002;
    }



}
