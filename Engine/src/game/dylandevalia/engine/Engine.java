package game.dylandevalia.engine;

import game.dylandevalia.engine.exceptions.EngineAlreadyRunning;
import game.dylandevalia.engine.exceptions.NoRegisteredStates;
import game.dylandevalia.engine.exceptions.StateDoesNotExist;
import game.dylandevalia.engine.gui.Window;
import game.dylandevalia.engine.states.IState;
import game.dylandevalia.engine.states.StateManager;
import game.dylandevalia.engine.utility.Bundle;
import game.dylandevalia.engine.utility.ICallback;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * The main class used to interact with the game game.dylandevalia.engine
 */
public class Engine {
	
	/** Reference to the JFrame window */
	private static Window window;
	
	/** Map of all the states the game.dylandevalia.engine will know about */
	private static Map<String, Class<? extends IState>> registeredStates = new HashMap<>();
	
	/** True if an instance of the game.dylandevalia.engine is already running */
	private static boolean isRunning = false;
	
	
	//<editor-fold desc="Methods to run the application">
	
	/**
	 * Starts the game.dylandevalia.engine in fullscreen
	 *
	 * @param startingState The state that the game.dylandevalia.engine will start on (must already be registered)
	 * @param title         The title of the window
	 */
	public static void runFullscreen(String startingState, String title) {
		String startingStateUpper = startingState.toUpperCase();
		checksBeforeRun(startingStateUpper);
		
		SwingUtilities.invokeLater(() -> {
			window = new Window();
			window.constructor(title, registeredStates, startingStateUpper);
			window.setVisible(true);
		});
		isRunning = true;
	}
	
	/**
	 * Starts the game.dylandevalia.engine in a window that is not resizeable
	 *
	 * @param dimensions    The size of the window in pixels
	 * @param startingState The state that the game.dylandevalia.engine will start on (must already be registered)
	 * @param title         The title of the window
	 */
	public static void runWindowed(Dimension dimensions, String startingState, String title) {
		String startingStateUpper = startingState.toUpperCase();
		checksBeforeRun(startingStateUpper);
		
		SwingUtilities.invokeLater(() -> {
			window = new Window(false);
			window.constructor(title, registeredStates, startingStateUpper);
			window.setWindowSize(dimensions);
			window.setVisible(true);
		});
		isRunning = true;
	}
	
	/**
	 * Starts the game.dylandevalia.engine in a window that is resizeable
	 *
	 * @param dimensions    The size of the window in pixels
	 * @param startingState The state that the game.dylandevalia.engine will start on (must already be registered)
	 * @param title         The title of the window
	 */
	public static void runWindowedResizeable(Dimension dimensions, String startingState, String title) {
		String startingStateUpper = startingState.toUpperCase();
		checksBeforeRun(startingStateUpper);
		
		SwingUtilities.invokeLater(() -> {
			window = new Window(true);
			window.constructor(title, registeredStates, startingStateUpper);
			window.setWindowSize(dimensions);
			window.setVisible(true);
		});
		isRunning = true;
	}
	
	/**
	 * Runs some validation before running the game.dylandevalia.engine. Checks if:
	 * <ul>
	 * <li>There are no registered states</li>
	 * <li>The starting state is registered</li>
	 * <li>There is already an instance of the game.dylandevalia.engine running</li>
	 * </ul>
	 * If any of the above are true, throws the relevant exception
	 *
	 * @param startingState The state that the game.dylandevalia.engine will start on
	 */
	private static void checksBeforeRun(String startingState) {
		if (registeredStates.isEmpty()) {
			throw new NoRegisteredStates();
		}
		
		if (registeredStates.get(startingState) == null) {
			throw new StateDoesNotExist(startingState);
		}
		
		if (isRunning) {
			throw new EngineAlreadyRunning();
		}
	}
	
	//</editor-fold>
	
	/**
	 * Registers the state with the name provided
	 *
	 * @param name       The name used for the state (case insensitive)
	 * @param stateClass The class of the state
	 */
	public static void registerState(String name, Class<? extends IState> stateClass) {
		registeredStates.put(name.toUpperCase(), stateClass);
	}
	
	/**
	 * Changes the title of the window
	 *
	 * @param newWindowTitle The new title the window should be called
	 */
	public static void changeWindowTitle(String newWindowTitle) {
		window.setTitle(newWindowTitle);
	}
	
	/**
	 * Gets the current width and height of the window
	 *
	 * @return The current width and height of the window in pixels
	 */
	public static Dimension getWindowDimentions() {
		return new Dimension(window.getWidth(), window.getHeight());
	}
	
	/**
	 * Gets the current width of the window
	 *
	 * @return The current width of the window in pixels
	 */
	public static int getWindowWidth() {
		return window.getWidth();
	}
	
	/**
	 * Gets the current height of the window
	 *
	 * @return The current height of the window in pixels
	 */
	public static int getWindowHeight() {
		return window.getHeight();
	}
	
	
	//<editor-fold desc="StateManager methods">
	
	/**
	 * Gets a reference to the state manager
	 *
	 * @return A reference to the state manager
	 */
	private static StateManager getStateManager() {
		return window.getFramework().getStateManager();
	}
	
	/**
	 * Creates the given state asynchronously, calls its {@link IState#onCreate(Bundle)} method with the given bundle
	 * and runs the callback method when completed
	 *
	 * @param state    The state to load into memory
	 * @param bundle   The bundle of data to sent to the state's initialisation method
	 * @param callback The callback method that will be called when the loading is complete
	 */
	public static void loadStateAsync(String state, Bundle bundle, ICallback callback) {
		getStateManager().loadStateAsync(state, bundle, callback);
	}
	
	/**
	 * Creates the given state asynchronously, calls its {@link IState#onCreate(Bundle)} method and runs the callback
	 * method when completed
	 *
	 * @param state    The state to load into memory
	 * @param callback The callback method that will be called when the loading is complete
	 */
	public static void loadStateAsync(String state, ICallback callback) {
		getStateManager().loadStateAsync(state, callback);
	}
	
	/**
	 * Creates the state and calls the state's {@link IState#onCreate(Bundle)} method
	 *
	 * @param state  The states to be onCreate
	 * @param bundle The bundle of the data to send to the initialising state
	 */
	public static void loadState(String state, Bundle bundle) {
		getStateManager().loadState(state, bundle);
	}
	
	/**
	 * Creates the state and calls the state's {@link IState#onCreate(Bundle)} method
	 *
	 * @param state The state to onCreate
	 */
	public static void loadState(String state) {
		getStateManager().loadState(state);
	}
	
	/**
	 * Stops the currently active state by running its {@link IState#onDisable(Bundle)} method before setting the given
	 * state as the active state. Then running the new state's {@link IState#onEnable(Bundle)} method, and passes in the
	 * given bundle
	 *
	 * @param state  The state to set as active
	 * @param bundle The bundle of the data to send to the state
	 */
	public static void setState(String state, Bundle bundle) {
		getStateManager().setState(state, bundle);
	}
	
	/**
	 * Stops the currently active state by running its {@link IState#onDisable(Bundle)} method before setting the given
	 * state as the active state. Then running the new state's {@link IState#onEnable(Bundle)} method
	 *
	 * @param state The state to set as active
	 */
	public static void setState(String state) {
		getStateManager().setState(state);
	}
	
	/**
	 * Runs the state's {@link IState#onDestroy(Bundle)} method then unloads the state from memory
	 *
	 * @param state  The state to be unloaded
	 * @param bundle The bundle of data to send to the state
	 */
	public static void unloadState(String state, Bundle bundle) {
		getStateManager().unloadState(state, bundle);
	}
	
	/**
	 * Runs the state's {@link IState#onDestroy(Bundle)} method with no bundle then unloads the state from memory
	 *
	 * @param state The state to be unloaded
	 */
	public static void unloadState(String state) {
		getStateManager().unloadState(state);
	}
	
	/**
	 * Checks to see if the given states is loaded
	 *
	 * @param state The state to check if it's loaded
	 * @return True if the state is loaded
	 */
	public static boolean isLoaded(String state) {
		return getStateManager().isLoaded(state);
	}
	
	//</editor-fold>
	
	public static void quit() {
		window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
	}
}
