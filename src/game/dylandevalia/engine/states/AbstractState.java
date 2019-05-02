package game.dylandevalia.engine.states;

import game.dylandevalia.engine.utility.Bundle;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

abstract class AbstractState {
	
	StateManager stateManager;
	
	void initialise(StateManager stateManager, Bundle bundle) {
		this.stateManager = stateManager;
		initialise(bundle);
	}
	
	public abstract void initialise(Bundle bundle);
	
	public abstract void onSet(Bundle bundle);
	
	public abstract void update();
	
	public abstract void draw(Graphics2D g, double interpolate);
	
	public abstract void keyPressed(KeyEvent e);
	
	public abstract void keyReleased(KeyEvent e);
	
	public abstract void mousePressed(MouseEvent e);
	
	public abstract void mouseReleased(MouseEvent e);
}
