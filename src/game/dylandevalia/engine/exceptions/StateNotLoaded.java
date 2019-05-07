package game.dylandevalia.engine.exceptions;

public class StateNotLoaded extends RuntimeException {
	
	public StateNotLoaded(String state) {
		super("The state '" + state + "' is not loaded");
	}
}
