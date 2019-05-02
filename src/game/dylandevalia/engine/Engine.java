package game.dylandevalia.engine;

import game.dylandevalia.engine.gui.Window;

import javax.swing.*;

public class Engine {
	
	private static Window window;
	
	public static void run(String windowTitle) {
//		window = new Window(windowTitle);
		SwingUtilities.invokeLater(() -> window = new Window(windowTitle));
	}
	
	public static void changeWindowTitle(String newWindowTitle) {
		window.setTitle(newWindowTitle);
	}
}
