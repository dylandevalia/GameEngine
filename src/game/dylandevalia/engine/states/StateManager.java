package game.dylandevalia.engine.states;

import game.dylandevalia.engine.utility.Bundle;
import game.dylandevalia.engine.utility.ICallback;
import game.dylandevalia.engine.utility.Log;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Controls the creation, initialising, activating/swapping and destroying of states. Passes
 * functions onto the currently active state. IState objects are implemented from the 'IState'
 * interface {@link IState}
 */
public class StateManager {
	
	/** Static to give all states a new id in array */
	private static int stateIndexCounter = 0;
	
	/** Array of loaded states */
	private final IState[] loadedStates = new IState[GameState.values().length];
	
	/** The currently active state */
	private IState currentState;
	
	/**
	 * Creates the given state asynchronously, calls its initialisation method and runs the callback
	 * method when completed
	 *
	 * @param state    The state to load into memory
	 * @param bundle   The bundle of data to sent to the state's initialisation method
	 * @param callback The callback method that will be called when the loading is complete
	 */
	public void loadStateAsync(final GameState state, final Bundle bundle, ICallback callback) {
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
	 * Creates the given state asynchronously, calls its initialisation method and runs the callback
	 * method when completed
	 *
	 * @param state    The state to load into memory
	 * @param callback The callback method that will be called when the loading is complete
	 */
	public void loadStateAsync(GameState state, ICallback callback) {
		loadStateAsync(state, null, callback);
	}
	
	/**
	 * Creates the state in the array and calls the state's initialise function
	 *
	 * @param state  The states to be initialise
	 * @param bundle The bundle of the data to send to the initialising state
	 */
	public void loadState(GameState state, Bundle bundle) {
		try {
			int index = state.getIndex();
			loadedStates[index] = (IState) state.getObj().newInstance();
			loadedStates[index].initialise(this, bundle);
			Log.info("STATE MANAGER", "Loaded and initialised " + state.name());
		} catch (Exception e) {
			Log.error(
				"IState manager",
				"Error trying to create new instance of " + state.name(),
				e
			);
		}
	}
	
	/**
	 * Creates the stat in the array and calls the state's initialise function
	 *
	 * @param state The state to initialise
	 */
	public void loadState(GameState state) {
		loadState(state, null);
	}
	
	/**
	 * Sets the given state as the active state
	 *
	 * @param state  The state to set as active
	 * @param bundle The bundle of the data to send to the state
	 */
	public void setState(GameState state, Bundle bundle) {
		if (loadedStates[state.getIndex()] == null) {
			Log.error("IState manager", state.name() + " not loaded!");
			return;
		}
		currentState = loadedStates[state.getIndex()];
		currentState.onSet(bundle);
		Log.info("STATE MANAGER", state.name() + " set");
	}
	
	/**
	 * Sets the given state as the active state
	 *
	 * @param state The state to set as active
	 */
	public void setState(GameState state) {
		setState(state, null);
	}
	
	/**
	 * Unloads the state from memory
	 *
	 * @param state The state to be deleted
	 */
	public void unloadState(GameState state) {
		loadedStates[state.getIndex()] = null;
		Log.info("STATE MANAGER", state.name() + " unloaded");
	}
	
	/**
	 * Checks to see if the given states is loaded
	 *
	 * @param state The state to check if it's loaded
	 * @return Boolean if the state is loaded
	 */
	public boolean isLoaded(GameState state) {
		return loadedStates[state.getIndex()] != null;
	}
	
	/*              Passers             */
	/* Calls the currently active state */
	
	public void reinitialise(Bundle bundle) {
		currentState.initialise(this, bundle);
	}
	
	public void reinitialise() {
		reinitialise(null);
	}
	
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
	
	/**
	 * Enum used to store states. Takes the state class in constructor and generates its own id from
	 * the static {@link #stateIndexCounter} to be used in the array of states {@link
	 * #loadedStates}
	 * <p>
	 * Add states here
	 *
	 * @see #stateIndexCounter
	 * @see IState
	 */
	public enum GameState {
		START(Start.class), PLAY(Play.class), PAUSE(Pause.class);
		
		private int index;
		private Class obj;
		
		GameState(Class obj) {
			this.index = stateIndexCounter++;
			this.obj = obj;
		}
		
		int getIndex() {
			return index;
		}
		
		Class getObj() {
			return obj;
		}
	}
}
