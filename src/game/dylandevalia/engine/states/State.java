package game.dylandevalia.engine.states;

import game.dylandevalia.engine.utility.Bundle;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Extend this class to create a state
 */
public class State implements IState {
	
	@Override
	public void onCreate(Bundle bundle) {
	}
	
	@Override
	public void onEnable(Bundle bundle) {
	}
	
	@Override
	public void onDisable(Bundle bundle) {
	}
	
	@Override
	public void onDestroy(Bundle bundle) {
	}
	
	@Override
	public void update() {
	}
	
	@Override
	public void draw(Graphics2D g, double interpolate) {
	}
	
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
