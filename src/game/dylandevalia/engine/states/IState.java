package game.dylandevalia.engine.states;

import game.dylandevalia.engine.utility.Bundle;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Interface used to create a state for the state manager
 */
public interface IState {
	
	void initialise(StateManager stateManager, Bundle bundle);
	
	void onSet(Bundle bundle);
	
	void update();
	
	void draw(Graphics2D g, double interpolate);
	
	void keyPressed(KeyEvent e);
	
	void keyReleased(KeyEvent e);
	
	void mousePressed(MouseEvent e);
	
	void mouseReleased(MouseEvent e);
}