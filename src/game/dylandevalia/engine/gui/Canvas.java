package game.dylandevalia.engine.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import javax.swing.JPanel;

public abstract class Canvas extends JPanel implements KeyListener, MouseListener {
	
	/**
	 * A hashmap of all the keyCodes and whether they are currently held down
	 */
	private static HashMap<Integer, Boolean> keyboardStates = new HashMap<>();
	
	public Canvas() {
		setDoubleBuffered(true);
		setFocusable(true);
		setBackground(Color.BLACK);
		
		addKeyListener(this);
		addMouseListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setBackground(Color.GREEN);
		// Calls method overridden by child
		draw(g2d);
	}
	
	public abstract void draw(Graphics2D g);
	
	/* ----------------- */
	/* Keyboard listener */
	/* ----------------- */
	
	/**
	 * Checks if a given key is being held down Returns false on NullPointerException as key hasn't
	 * been pressed yet so there's no value for it in the map
	 *
	 * @param key The keycode of the key to check
	 * @return Whether the given key is held down
	 */
	public static boolean getKeyState(int key) {
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
		keyboardStates.put(e.getKeyCode(), false);
		keyReleasedFramework(e);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	public abstract void keyPressedFramework(KeyEvent e);
	
	public abstract void keyReleasedFramework(KeyEvent e);
	
	/* -------------- */
	/* Mouse listener */
	/* -------------- */
	
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
	 * Returns if the mouse button given is being pressed down 1 - Left click 2 - Middle click
	 * (scroll wheel) 3 - Right click
	 *
	 * @param button Value of the button to check
	 * @return Boolean if the mouse button is held down
	 */
	public static boolean mouseButtonState(int button) {
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
}
