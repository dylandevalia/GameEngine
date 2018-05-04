package game.dylandevalia.engine.states;

import game.dylandevalia.engine.gui.ColorMaterial;
import game.dylandevalia.engine.gui.Window;
import game.dylandevalia.engine.states.StateManager.GameState;
import game.dylandevalia.engine.utility.Bundle;
import game.dylandevalia.engine.utility.Utility;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class Play extends AbstractState {
	
	@Override
	public void initialise(Bundle bundle) {
		// Wait 5 seconds to test asynchronous loading
		Utility.sleep(5000);
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
	public void mouseReleased(MouseEvent e) {
		stateManager.setState(GameState.PAUSE);
	}
}
