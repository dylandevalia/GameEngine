package game.dylandevalia.engine.states;

import game.dylandevalia.engine.game.entities.BaseEntity;
import game.dylandevalia.engine.gui.ColorMaterial;
import game.dylandevalia.engine.gui.Window;
import game.dylandevalia.engine.states.StateManager.GameState;
import game.dylandevalia.engine.utility.Bundle;
import game.dylandevalia.engine.utility.Log;
import game.dylandevalia.engine.utility.Vector2D;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class Start extends AbstractState {
	
	private int ready = 0;
	private Ball ball = new Ball();
	
	@Override
	public void initialise(Bundle bundle) {
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
			stateManager.loadStateAsync(GameState.PLAY, this::setReady);
			stateManager.loadStateAsync(GameState.PAUSE, this::setReady);
		} else if (ready == 2 && e.getButton() == MouseEvent.BUTTON1) {
			stateManager.setState(GameState.PLAY);
			stateManager.unloadState(GameState.START);
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
	
	private class Ball extends BaseEntity {
		
		Vector2D vel = new Vector2D(1, 1).setMag(10);
		
		Ball() {
			super(Window.WIDTH / 2, Window.HEIGHT / 2, 50, 50);
		}
		
		@Override
		protected void update() {
			super.update();
			pos.add(vel);
			
			if (pos.x < 0 || pos.x > Window.WIDTH - width) {
				vel.x *= -1;
			}
			if (pos.y < 0 || pos.y > Window.HEIGHT - height) {
				vel.y *= -1;
			}
		}
		
		@Override
		protected void draw(Graphics2D g, double interpolate) {
			super.draw(g, interpolate);
			g.setColor(ColorMaterial.BLUE_GREY[0]);
			g.fillOval(((int) drawPos.x), ((int) drawPos.y), width, height);
		}
	}
}
