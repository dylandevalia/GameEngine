package game.dylandevalia.engine.gui;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	
	private boolean fullscreen = false;
	
	/**
	 * Constructs the window at fullscreen with given title
	 *
	 * @param title The title of the window
	 */
	public Window(String title) {
		fullscreen = true;
		setUndecorated(true);
		setExtendedState(MAXIMIZED_BOTH);
		
		constructor(title);
	}
	
	/**
	 * Constructs the window with the given dimensions and title
	 *
	 * @param dimensions The size of the window
	 * @param resizeable Allows the window to be resizeable
	 * @param title      The title of the window
	 */
	public Window(Dimension dimensions, boolean resizeable, String title) {
		setSize(dimensions);
		setLocationRelativeTo(null);
		setResizable(resizeable);
		
		constructor(title);
	}
	
	/**
	 * Constructs the window with the given dimensions and title but does not allow for resizing
	 *
	 * @param dimensions The size of the window
	 * @param title      The title of the window
	 */
	public Window(Dimension dimensions, String title) {
		this(dimensions, false, title);
	}
	
	/**
	 * General code needed to start a JFrame window on startup
	 *
	 * @param title The title of the window
	 */
	private void constructor(String title) {
		setTitle(title);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setContentPane(new Framework());
		setVisible(true);
	}
	
	/**
	 * Sets the size of the application window
	 *
	 * @param width  The width of the window in pixels
	 * @param height The height of the window in pixels
	 */
	@Override
	public void setSize(int width, int height) {
		if (fullscreen) {
			return;
		}
		
		// TODO: Fix this issue of screen being the wrong size
		// Added tmp fix by hard-coding values
		pack();
		Insets insets = getInsets();
		super.setSize(
			width /*+ insets.left + insets.right*/,
			height /*+ insets.top + insets.bottom*/ + 28
		);
	}
	
	/**
	 * Sets the size of the application window
	 *
	 * @param dimension The width and height of the window in pixels
	 */
	@Override
	public void setSize(Dimension dimension) {
		setSize(dimension.width, dimension.height);
	}
}
