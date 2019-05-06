package game.dylandevalia.engine;

import game.dylandevalia.engine.exceptions.EngineAlreadyRunning;
import game.dylandevalia.engine.gui.Window;

import javax.swing.*;
import java.awt.*;

public class Engine {
	
	private static Window window;
	private static boolean isRunning = false;
	
	/* Run the application */
	
	public static void runFullscreen(String title) {
		if (isRunning) {
			throw new EngineAlreadyRunning();
		}
		SwingUtilities.invokeLater(() -> window = new Window(title));
		isRunning = true;
	}
	
	public static void runWindowed(Dimension dimensions, String title) {
		if (isRunning) {
			throw new EngineAlreadyRunning();
		}
		SwingUtilities.invokeLater(() -> window = new Window(dimensions, title));
		isRunning = true;
	}
	
	public static void runWindowedResizeable(Dimension dimensions, String title) {
		if (isRunning) {
			throw new EngineAlreadyRunning();
		}
		SwingUtilities.invokeLater(() -> window = new Window(dimensions, true, title));
		isRunning = true;
	}
	
	
	/* Getters and Setter */
	
	public static void changeWindowTitle(String newWindowTitle) {
		window.setTitle(newWindowTitle);
	}
}
