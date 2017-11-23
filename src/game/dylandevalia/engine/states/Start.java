package game.dylandevalia.engine.states;

import game.dylandevalia.engine.gui.ColorMaterial;
import game.dylandevalia.engine.gui.Window;
import game.dylandevalia.engine.states.StateManager.GameState;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Start implements State {
	
	private StateManager stateManager;
	
	@Override
	public void initialise(StateManager stateManager) {
		this.stateManager = stateManager;
		
	}
	
	@Override
	public void update() {
	
	}
	
	@Override
	public void draw(Graphics2D g2d, double interpolate) {
		g2d.setColor(ColorMaterial.blue);
		g2d.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
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
		// Load play and pause states
		stateManager.loadState(GameState.PLAY);
		stateManager.loadState(GameState.PAUSE);
		
		stateManager.setState(GameState.PLAY);
		stateManager.unloadState(GameState.START);
	}
}
