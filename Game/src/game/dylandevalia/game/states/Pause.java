package game.dylandevalia.game.states;

import game.dylandevalia.engine.Engine;
import game.dylandevalia.engine.gui.Window;
import game.dylandevalia.engine.states.State;
import game.dylandevalia.engine.utility.Bundle;
import game.dylandevalia.engine.utility.ColorMaterial;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Pause extends State {
	
	@Override
	public void onEnable(Bundle bundle) {
		Engine.changeWindowTitle("Paused");
	}
	
	public void draw(Graphics2D g2d, double interpolate) {
		g2d.setColor(ColorMaterial.red);
		g2d.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
	}
	
	public void mouseReleased(MouseEvent e) {
		Engine.setState("play");
	}
}
