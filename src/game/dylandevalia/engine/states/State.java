package game.dylandevalia.engine.states;

import game.dylandevalia.engine.utility.Bundle;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Extend this class to create a state
 */
public class State implements IState {
	
	/**
	 * State event method called when the state is first loaded
	 *
	 * @param bundle Bundle of data which is passed to the method
	 */
	@Override
	public void onCreate(Bundle bundle) {
	}
	
	/**
	 * State event method called when the state is set as the active state
	 *
	 * @param bundle Bundle of data which is passed to the method
	 */
	@Override
	public void onEnable(Bundle bundle) {
	}
	
	/**
	 * State event method called when the state is unset as the active state
	 *
	 * @param bundle Bundle of data which is passed to the method
	 */
	@Override
	public void onDisable(Bundle bundle) {
	}
	
	/**
	 * State event method called before the state is unloaded from memory
	 *
	 * @param bundle Bundle of data which is passed to the method
	 */
	@Override
	public void onDestroy(Bundle bundle) {
	}
	
	/**
	 * Engine event method called every update frame {@link game.dylandevalia.engine.gui.Framework#GAME_HERTZ}
	 */
	@Override
	public void update() {
	}
	
	/**
	 * Engine event method called every draw frame {@link game.dylandevalia.engine.gui.Framework#TARGET_FPS}
	 *
	 * @param g           The graphics object used to draw to the canvas
	 * @param interpolate Delta-time value used to keep objects appearing smoothly
	 */
	@Override
	public void draw(Graphics2D g, double interpolate) {
	}
	
	/**
	 * Engine event method called when a keyboard key has been pressed down
	 *
	 * @param e The event information
	 */
	@Override
	public void keyPressed(KeyEvent e) {
	}
	
	/**
	 * Engine event method called when a keyboard key has been released
	 *
	 * @param e The event information
	 */
	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	/**
	 * Engine event method called when a mouse button has been pressed down
	 *
	 * @param e The event information
	 */
	@Override
	public void mousePressed(MouseEvent e) {
	}
	
	/**
	 * Eneing event method called when a mouse button have been released
	 *
	 * @param e The event information
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
