package game.dylandevalia.game.states;

import game.dylandevalia.engine.Engine;
import game.dylandevalia.engine.states.State;
import game.dylandevalia.engine.utility.Bundle;
import game.dylandevalia.engine.utility.ColorMaterial;
import game.dylandevalia.engine.utility.Utility;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Play extends State {
	
	@Override
	public void onCreate(Bundle bundle) {
		// Wait 5 seconds to test asynchronous loading
		Utility.sleep(5000);
	}
	
	@Override
	public void onEnable(Bundle bundle) {
		Engine.changeWindowTitle("Play");
	}
	
	@Override
	public void draw(Graphics2D g2d, double interpolate) {
		g2d.setColor(ColorMaterial.green);
		g2d.fillRect(0, 0, Engine.getWindowWidth(), Engine.getWindowHeight());
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		Engine.setState("pause");
	}
}
