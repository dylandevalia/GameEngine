package game.dylandevalia.game.states;

import game.dylandevalia.engine.Engine;
import game.dylandevalia.engine.gui.ColorMaterial;
import game.dylandevalia.engine.gui.Window;
import game.dylandevalia.engine.states.State;
import game.dylandevalia.engine.utility.Bundle;
import game.dylandevalia.engine.utility.Log;
import game.dylandevalia.game.entities.Ball;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Start extends State {
	
	private int ready = 0;
	private Ball ball = new Ball();
	
	@Override
	public void onCreate(Bundle bundle) {
		Log.SET_DEBUG();
	}
	
	@Override
	public void update() {
		ball.update();
	}
	
	@Override
	public void draw(Graphics2D g, double interpolate) {
		g.setColor(ColorMaterial.blue);
		g.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
		
		g.setColor(Color.RED);
		g.fillRect(0, 0, 1, 20);
		g.fillRect(0, 0, 20, 1);
		g.fillRect(Window.WIDTH - 1, Window.HEIGHT - 20, 1, 20);
		g.fillRect(Window.WIDTH - 20, Window.HEIGHT - 1, 20, 1);
		
		
		ball.draw(g, interpolate);
		
		if (ready == 0) {
			drawCenteredString(g, "NOT LOADED - RIGHT CLICK");
		} else if (ready == 2) {
			drawCenteredString(g, "LOADED - LEFT CLICK");
		} else {
			drawCenteredString(g, "LOADING...");
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// Load play and pause states
		if (ready == 0 && e.getButton() == MouseEvent.BUTTON3) {
			Engine.loadStateAsync("play", this::setReady);
			Engine.loadStateAsync("pause", this::setReady);
		} else if (ready == 2 && e.getButton() == MouseEvent.BUTTON1) {
			Engine.setState("play");
			Engine.unloadState("start");
		}
	}
	
	private void setReady() {
		ready++;
		Log.debug("Start", "ready = " + ready);
	}
	
	/**
	 * Draw a String centered in the middle of a Rectangle
	 *
	 * @param g The Graphics instance
	 */
	private void drawCenteredString(Graphics2D g, String message) {
		Font font = new Font("TimesRoman", Font.PLAIN, 72);
		
		int width = g.getFontMetrics(font).stringWidth(message);
		int height = g.getFontMetrics(font).getHeight();
		
		// Determine the X coordinate for the text
		int x = (Window.WIDTH / 2) - (width / 2);
		
		// Determine the Y coordinate for the text
		int y = (Window.HEIGHT / 2) + (height / 2);
		
		// Set the font
		g.setFont(font);
		// Draw the String
		g.drawString(message, x, y);
	}
}
