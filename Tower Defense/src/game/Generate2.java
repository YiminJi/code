package game;

import java.awt.Graphics;

public class Generate2 extends GameObject {
	private State state;
	private Control control;
	private double timeToNextGeneration = 5.0;
	private int groupSize = 1;
	private int burstSize = 1;

	public Generate2(State state, Control control) {
		isVisible = false;
		isExpired = false;
		this.state = state;
		this.control = control;
	}

	@Override
	public void update(double timeElapsed) {
		timeToNextGeneration -= timeElapsed;
		if (timeToNextGeneration <= 0) {
			state.addGameObject(new EnemySCargo(state, control));
			burstSize--;
			if (burstSize <= 0) {
				timeToNextGeneration = 7.5;
				groupSize++;
				burstSize = groupSize;
			} else
				timeToNextGeneration = 0.33;
		}
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
	}

}
