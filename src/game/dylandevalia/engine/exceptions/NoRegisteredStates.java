package game.dylandevalia.engine.exceptions;

public class NoRegisteredStates extends RuntimeException {
	
	public NoRegisteredStates() {
		super("There are no states registered. At least one registered state is required");
	}
}
