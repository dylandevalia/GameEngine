package game.dylandevalia.engine.states;

import game.dylandevalia.engine.exceptions.StateDoesNotExist;
import game.dylandevalia.engine.utility.Bundle;
import game.dylandevalia.engine.utility.ICallback;
import game.dylandevalia.engine.utility.Log;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Controls the creation, initialising, activating/swapping and destroying of states. Passes functions onto the
 * currently active state. IState objects are implemented from the 'IState' interface {@link IState}
 */
public class StateManager {
	
	/** The list of all states that the system knows about */
	private Map<String, Class<? extends IState>> registeredStates = new HashMap<>();
	
	/** Array of loaded states */
	private Map<String, IState> loadedStates = new HashMap<>();
	
	/** The currently active state */
	private IState currentState;
	
	/**
	 * Constructor which takes a map of all the states the the system should know about and the state that should start.
	 * It then loads and starts the starting state
	 *
	 * @param states        The list of all states the this manager is in control of
	 * @param startingState The state which should be loaded up first
	 */
	public StateManager(Map<String, Class<? extends IState>> states, String startingState) {
		registeredStates.putAll(states);
		
		loadState(startingState);
		setState(startingState);
	}
	
	/**
	 * Creates the given state asynchronously, calls its {@link IState#onCreate(Bundle)} method with the given bundle
	 * and runs the callback method when completed
	 *
	 * @param state    The state to load into memory
	 * @param bundle   The bundle of data to sent to the state's initialisation method
	 * @param callback The callback method that will be called when the loading is complete
	 */
	public void loadStateAsync(String state, Bundle bundle, ICallback callback) {
		Thread thread = new Thread(() -> loadState(state, bundle));
		new Thread(() -> {
			thread.start();
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			callback.callback();
		}).start();
	}
	
	/**
	 * Creates the given state asynchronously, calls its {@link IState#onCreate(Bundle)} method and runs the callback
	 * method when completed
	 *
	 * @param state    The state to load into memory
	 * @param callback The callback method that will be called when the loading is complete
	 */
	public void loadStateAsync(String state, ICallback callback) {
		loadStateAsync(state, null, callback);
	}
	
	/**
	 * Creates the state and calls the state's {@link IState#onCreate(Bundle)} method
	 *
	 * @param state  The states to be onCreate
	 * @param bundle The bundle of the data to send to the initialising state
	 */
	public void loadState(String state, Bundle bundle) {
		state = state.toUpperCase();
		if (registeredStates.get(state) == null) {
			throw new StateDoesNotExist(state);
		}
		
		try {
			loadedStates.put(state, registeredStates.get(state).newInstance());
			loadedStates.get(state).onCreate(bundle);
		} catch (Exception e) {
			Log.error("STATE MANAGER", "Error trying to create new instance of '" + state + "'", e);
		}
	}
	
	/**
	 * Creates the state and calls the state's {@link IState#onCreate(Bundle)} method
	 *
	 * @param state The state to onCreate
	 */
	public void loadState(String state) {
		loadState(state, null);
	}
	
	/**
	 * Stops the currently active state by running its {@link IState#onDisable(Bundle)} method before setting the given
	 * state as the active state. Then running the new state's {@link IState#onEnable(Bundle)} method, and passes in the
	 * given bundle
	 *
	 * @param state  The state to set as active
	 * @param bundle The bundle of the data to send to the state
	 */
	public void setState(String state, Bundle bundle) {
		state = state.toUpperCase();
		if (loadedStates.get(state) == null) {
			Log.error("STATE MANAGER", state + " not loaded!");
			return;
		}
		
		if (currentState != null) {
			currentState.onDisable(bundle);
		}
		currentState = loadedStates.get(state);
		currentState.onEnable(bundle);
		Log.info("STATE MANAGER", state + " set");
	}
	
	/**
	 * Stops the currently active state by running its {@link IState#onDisable(Bundle)} method before setting the given
	 * state as the active state. Then running the new state's {@link IState#onEnable(Bundle)} method
	 *
	 * @param state The state to set as active
	 */
	public void setState(String state) {
		setState(state, null);
	}
	
	/**
	 * Runs the state's {@link IState#onDestroy(Bundle)} method then unloads the state from memory
	 *
	 * @param state  The state to be unloaded
	 * @param bundle The bundle of data to send to the state
	 */
	public void unloadState(String state, Bundle bundle) {
		state = state.toUpperCase();
		if (loadedStates.get(state) == null) {
			Log.error("STATE MANAGER", "Cannot unload state '" + state + "' as it was not loaded");
			return;
		}
		loadedStates.get(state).onDestroy(bundle);
		loadedStates.remove(state);
		Log.info("STATE MANAGER", state + " unloaded");
	}
	
	/**
	 * Runs the state's {@link IState#onDestroy(Bundle)} method with no bundle then unloads the state from memory
	 *
	 * @param state The state to be unloaded
	 */
	public void unloadState(String state) {
		unloadState(state, null);
	}
	
	/**
	 * Checks to see if the given states is loaded
	 *
	 * @param state The state to check if it's loaded
	 * @return True if the state is loaded
	 */
	public boolean isLoaded(String state) {
		return loadedStates.get(state) != null;
	}
	
	
	//<editor-fold desc="Callback method on currentState">
	public void update() {
		currentState.update();
	}
	
	public void draw(Graphics2D g, double interpolate) {
		currentState.draw(g, interpolate);
	}
	
	public void keyPressed(KeyEvent e) {
		currentState.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e) {
		currentState.keyReleased(e);
	}
	
	public void mousePressed(MouseEvent e) {
		currentState.mousePressed(e);
	}
	
	public void mouseReleased(MouseEvent e) {
		currentState.mouseReleased(e);
	}
	//</editor-fold>
}
