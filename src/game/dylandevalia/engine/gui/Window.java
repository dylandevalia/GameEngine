package game.dylandevalia.engine.gui;

import game.dylandevalia.engine.states.IState;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Window extends JFrame {
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	
	private boolean fullscreen = false;
	
	private Framework framework;
	
	
	//<editor-fold desc="Constructors">
	
	/**
	 * Constructs the window at fullscreen with given title
	 */
	public Window() {
		fullscreen = true;
		setUndecorated(true);
		setExtendedState(MAXIMIZED_BOTH);
	}
	
	/**
	 * Constructs the window with the given dimensions and title
	 *
	 * @param resizeable Allows the window to be resizeable
	 */
	public Window(boolean resizeable) {
		setLocationRelativeTo(null);
		setResizable(resizeable);
	}
	
	/**
	 * General code needed to start a JFrame window on startup
	 *
	 * @param title The title of the window
	 */
	public void constructor(String title, Map<String, Class<? extends IState>> registeredStates, String startingState) {
		setTitle(title);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		framework = new Framework(registeredStates, startingState);
		setContentPane(framework);
	}
	//</editor-fold>
	
	
	//<editor-fold desc="JFrame overrides">
	
	/**
	 * Sets the size of the application window
	 *
	 * @param width  The width of the window in pixels
	 * @param height The height of the window in pixels
	 */
	public void setWindowSize(int width, int height) {
		if (fullscreen) {
			return;
		}
		
		// TODO: Fix this issue of screen being the wrong size
		// Added tmp fix by hard-coding values
		pack();
		Insets in = new Insets(28, 0, 0, 0); // getInsets();
		Dimension d = new Dimension(width + in.left + in.right, height + in.top + in.bottom);
		super.setMinimumSize(d);
		super.setPreferredSize(d);
		super.setMaximumSize(d);
		super.setSize(d);
		// super.setPreferredSize(new Dimension(
		// 	width /*+ insets.left + insets.right*/,
		// 	height /*+ insets.top + insets.bottom*/ /*+ 28*/
		// ));
		pack();
	}
	
	/**
	 * Sets the size of the application window
	 *
	 * @param dimension The width and height of the window in pixels
	 */
	public void setWindowSize(Dimension dimension) {
		setWindowSize(dimension.width, dimension.height);
	}
	//</editor-fold>
	
	
	public Framework getFramework() {
		return framework;
	}
}
