package game.dylandevalia.engine.states;

import game.dylandevalia.engine.utility.Bundle;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class AbstractState implements IState {
	
	protected StateManager stateManager;
	
	@Override
	public void initialise(StateManager stateManager, Bundle bundle) {
		this.stateManager = stateManager;
		initialise(bundle);
	}
	
	public abstract void initialise(Bundle bundle);
	
	@Override
	public void onSet(Bundle bundle) {
	
	}
	
	@Override
	public abstract void update();
	
	@Override
	public abstract void draw(Graphics2D g, double interpolate);
	
	@Override
	public void keyPressed(KeyEvent e) {
	
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
	
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
	
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
	
	}
}
