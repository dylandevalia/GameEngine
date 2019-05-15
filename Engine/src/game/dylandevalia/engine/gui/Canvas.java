package game.dylandevalia.engine.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

/**
 * The canvas which the game.dylandevalia.engine draws to. Extends {@link JPanel} to utilise the Swing GUI. Also
 * implements {@link KeyListener} and {@link MouseListener} interfaces to get keyboard and mouse information
 */
abstract class Canvas extends JPanel implements KeyListener, MouseListener {
	
	/**
	 * A hash map of all the keyCodes and whether they are currently held down
	 */
	private static HashMap<Integer, Boolean> keyboardStates = new HashMap<>();
	
	/**
	 * Creates the canvas as a double buffered canvas. Also adds this object as keyboard and mouse listeners
	 */
	Canvas() {
		setDoubleBuffered(true);
		setFocusable(true);
		setBackground(Color.BLACK);
		
		addKeyListener(this);
		addMouseListener(this);
	}
	
	/**
	 * Used to draw to the canvas. This method is called by the system but then runs {@link #draw(Graphics2D)} which
	 * passes on the draw call to the rest of the game.dylandevalia.engine
	 *
	 * @param g The graphics object used to draw to the canvas
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setBackground(Color.GREEN);
		// Calls method overridden by child
		draw(g2d);
	}
	
	/**
	 * Method used to draw to the canvas
	 *
	 * @param g The graphics object used to draw to the canvas
	 */
	public abstract void draw(Graphics2D g);
	
	
	//<editor-fold desc="Keyboard listeners">
	
	/**
	 * Checks if a given key is being held down Returns false on NullPointerException as key hasn't been pressed yet so
	 * there's no value for it in the map
	 *
	 * @param key The key code of the key to check
	 * @return Whether the given key is held down
	 */
	public boolean getKeyState(int key) {
		try {
			return keyboardStates.get(key);
		} catch (NullPointerException e) {
			return false;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keyboardStates.put(e.getKeyCode(), true);
		keyPressedFramework(e);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		keyboardStates.remove(e.getKeyCode());
		keyReleasedFramework(e);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	public abstract void keyPressedFramework(KeyEvent e);
	
	public abstract void keyReleasedFramework(KeyEvent e);
	
	//</editor-fold>
	
	//<editor-fold desc="Mouse listener">
	
	/**
	 * Array of if the respective mouse button is currently being held down
	 */
	private static boolean[] mouseStates = new boolean[3];
	
	/**
	 * Updates the array {@link #mouseStates} when the mouse has been pressed/released
	 *
	 * @param e      The mouse event
	 * @param status True if the mouse was pressed, false if released
	 */
	private void mouseKeyStatus(MouseEvent e, boolean status) {
		switch (e.getButton()) {
			case MouseEvent.BUTTON1:
				mouseStates[0] = status;
				break;
			case MouseEvent.BUTTON2:
				mouseStates[1] = status;
				break;
			case MouseEvent.BUTTON3:
				mouseStates[2] = status;
				break;
		}
	}
	
	/**
	 * Returns if the mouse button given is being pressed down 1 - Left click 2 - Middle click (scroll wheel) 3 - Right
	 * click
	 *
	 * @param button Value of the button to check
	 * @return Boolean if the mouse button is held down
	 */
	public boolean getMouseButtonState(int button) {
		if (button > mouseStates.length) {
			return false;
		}
		return mouseStates[button - 1];
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		mouseKeyStatus(e, true);
		mousePressedFramework(e);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		mouseKeyStatus(e, false);
		mouseReleasedFramework(e);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	public abstract void mousePressedFramework(MouseEvent e);
	
	public abstract void mouseReleasedFramework(MouseEvent e);
	
	//</editor-fold>
}
