package game.dylandevalia.engine.exceptions;

/**
 * Exception called when the game.dylandevalia.engine is run without any states
 * registered
 */
public class NoRegisteredStates extends RuntimeException {
	
	public NoRegisteredStates() {
		super("There are no states registered. At least one registered state is required");
	}
}
