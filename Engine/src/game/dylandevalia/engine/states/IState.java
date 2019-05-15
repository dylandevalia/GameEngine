package game.dylandevalia.engine.states;

import game.dylandevalia.engine.gui.Framework;
import game.dylandevalia.engine.utility.Bundle;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Interface that all states use
 */
public interface IState {
	
	/**
	 * State event method called when the state is first loaded
	 *
	 * @param bundle Bundle of data which is passed to the method
	 */
	void onCreate(Bundle bundle);
	
	/**
	 * State event method called when the state is set as the active state
	 *
	 * @param bundle Bundle of data which is passed to the method
	 */
	void onEnable(Bundle bundle);
	
	/**
	 * State event method called when the state is unset as the active state
	 *
	 * @param bundle Bundle of data which is passed to the method
	 */
	void onDisable(Bundle bundle);
	
	/**
	 * State event method called before the state is unloaded from memory
	 *
	 * @param bundle Bundle of data which is passed to the method
	 */
	void onDestroy(Bundle bundle);
	
	/**
	 * Engine event method called every update frame {@link Framework#GAME_HERTZ}
	 */
	void update();
	
	/**
	 * Engine event method called every draw frame {@link Framework#TARGET_FPS}
	 *
	 * @param g           The graphics object used to draw to the canvas
	 * @param interpolate Delta-time value used to keep objects appearing smoothly
	 */
	void draw(Graphics2D g, double interpolate);
	
	/**
	 * Engine event method called when a keyboard key has been pressed down
	 *
	 * @param e The event information
	 */
	void keyPressed(KeyEvent e);
	
	/**
	 * Engine event method called when a keyboard key has been released
	 *
	 * @param e The event information
	 */
	void keyReleased(KeyEvent e);
	
	/**
	 * Engine event method called when a mouse button has been pressed down
	 *
	 * @param e The event information
	 */
	void mousePressed(MouseEvent e);
	
	/**
	 * Eneing event method called when a mouse button have been released
	 *
	 * @param e The event information
	 */
	void mouseReleased(MouseEvent e);
}
