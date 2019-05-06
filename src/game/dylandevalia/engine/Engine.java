package game.dylandevalia.engine;

import game.dylandevalia.engine.exceptions.EngineAlreadyRunning;
import game.dylandevalia.engine.exceptions.NoRegisteredStates;
import game.dylandevalia.engine.gui.Window;
import game.dylandevalia.engine.states.State;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Engine {
	
	private static Window window;
	private static Map<String, Class<? extends State>> registeredStates = new HashMap<>();
	
	private static boolean isRunning = false;
	
	
	/* Run the application */
	
	public static void runFullscreen(String title) {
		checksBeforeRun();
		
		SwingUtilities.invokeLater(() -> window = new Window(title, registeredStates));
		isRunning = true;
	}
	
	public static void runWindowed(Dimension dimensions, String title) {
		checksBeforeRun();
		
		SwingUtilities.invokeLater(() -> window = new Window(dimensions, title, registeredStates));
		isRunning = true;
	}
	
	public static void runWindowedResizeable(Dimension dimensions, String title) {
		checksBeforeRun();
		
		SwingUtilities.invokeLater(() -> window = new Window(dimensions, true, title, registeredStates));
		isRunning = true;
	}
	
	private static void checksBeforeRun() {
		if (registeredStates.isEmpty()) {
			throw new NoRegisteredStates();
		}
		
		if (isRunning) {
			throw new EngineAlreadyRunning();
		}
	}
	
	
	/* Getters and Setter */
	
	public static void registerState(String name, Class<? extends State> stateClass) {
		registeredStates.put(name, stateClass);
	}
	
	public static void registerStates(String[] names, Class<? extends State>[] stateClasses) {
		for (int i = 0; i < Math.min(names.length, stateClasses.length); i++) {
			registeredStates.put(names[i], stateClasses[i]);
		}
	}
	
	public static void registerStates(Map<String, Class<? extends State>> states) {
		registeredStates.putAll(states);
	}
	
	public static void changeWindowTitle(String newWindowTitle) {
		window.setTitle(newWindowTitle);
	}
}
