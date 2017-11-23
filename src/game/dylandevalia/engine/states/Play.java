package game.dylandevalia.engine.states;

import game.dylandevalia.engine.gui.ColorMaterial;
import game.dylandevalia.engine.gui.Window;
import game.dylandevalia.engine.states.StateManager.GameState;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Play implements State {
	
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
		g2d.setColor(ColorMaterial.green);
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
		stateManager.setState(GameState.PAUSE);
	}
}
