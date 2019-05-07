package game.dylandevalia.engine.exceptions;

/**
 * Exception called when a state is trying to be called
 * that does not exist
 */
public class StateDoesNotExist extends RuntimeException {
	
	public StateDoesNotExist(String state) {
		super("The state given '" + state + "' does not exist");
	}
}
