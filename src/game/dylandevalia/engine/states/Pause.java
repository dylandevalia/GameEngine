package game.dylandevalia.engine.states;

import game.dylandevalia.engine.gui.ColorMaterial;
import game.dylandevalia.engine.gui.Window;
import game.dylandevalia.engine.states.StateManager.GameState;
import game.dylandevalia.engine.utility.Bundle;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class Pause extends AbstractState {
	
	@Override
	public void initialise(Bundle bundle) {
	
	}
	
	@Override
	public void update() {
	
	}
	
	@Override
	public void draw(Graphics2D g2d, double interpolate) {
		g2d.setColor(ColorMaterial.red);
		g2d.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		stateManager.setState(GameState.PLAY);
	}
}
