/**
 * This class will keep track each objects state.
 * 
 * 
 * assignment #9
 * 
 * Class CS1420
 * @author  ---Yimin Jiang---
 * @version November 15, 2022
 */
package game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class State 
{
	List<GameObject> nextFrameGameObjects;
	List<GameObject> currentFrameGameObjects;
	
	private int lives, money, score;
	
	private long lastFrameStartTime;
	private double elaspsedTime;
	private double secondsSinceGameStart;
	Enemy closest;
	
	
	public State()
	{
		lives = 100;
		money = 500;
		score = 0;
		lastFrameStartTime = System.currentTimeMillis();
		elaspsedTime = 0.0;
		secondsSinceGameStart = 0.0;
		
		//initialize the time here
		
		
		currentFrameGameObjects = new ArrayList<GameObject>();
	}
	
	public Enemy getClosetEnemy(Point p)
	{
		for(GameObject a : currentFrameGameObjects)
		{
			if(a instanceof Enemy)
			{
				Enemy e = (Enemy) a;
				if(closest == null)
					closest = e;
				else if(e.getLocation().distance(p) < closest.getLocation().distance(p))
					closest = e;
			}
		}
		return closest;
		
	}
	
	public double getElaspsedTime() {
		return elaspsedTime;
	}
	
	public long getLastFrameStartTime() {
		return lastFrameStartTime;
	}
	
	/**
	 * this method will return the number of score
	 * @return
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * this method will return the number of lives
	 * @return
	 */
	public int getLives() {
		return lives;
	}
	
	/**
	 * this method will return the number of money
	 * @return
	 */
	public int getMoney() {
		return money;
	}
	
	/**
	 * this method initialize the game 
	 */
	public void startFrame()
	{
		//lastFrameStartTime = System.currentTimeMillis();
		
		nextFrameGameObjects = new ArrayList<GameObject>();
		nextFrameGameObjects.addAll(currentFrameGameObjects); 
		
		//Calculate the elapsed time here

		long currenTime = System.currentTimeMillis();
		elaspsedTime = (currenTime - lastFrameStartTime)/1000.0;
		//System.out.println(elaspsedTime);
		secondsSinceGameStart += elaspsedTime;
		lastFrameStartTime = currenTime;
		
	}
	
	/**
	 * this method allow us to get all the game object in the list
	 * @return
	 */
	public List<GameObject> getFrameObjects()
	{
		return currentFrameGameObjects;
	}
	
	/**
	 * this method allow us add game object to the list
	 * @param go
	 */
	public void addGameObject (GameObject go)
	{
		nextFrameGameObjects.add(go);
	}
	
	/**
	 * in this method we will remove all the expired game object in the list that we will not draw again
	 */
	public void finishFrame()
	{
		// remove the expired object from the list
		for(GameObject x : currentFrameGameObjects) 
		{
			if(x.isExpired == true) {
				nextFrameGameObjects.remove(x);
			}
		}
		
		
		currentFrameGameObjects = nextFrameGameObjects;
		nextFrameGameObjects = new ArrayList<GameObject>();
	}
	
	public void liveDecrease(int amount) {
		lives -= amount;
	}

	public double getSecondsSinceGameStart() {
		return secondsSinceGameStart;
	}

	public void adjustScore(int amount) {
		score += amount;

		if(score < 0)
			score = 0;
	}

	public void addToMoney(int money) {
		this.money += money;
		
	}
}
