package game.dylandevalia.engine.exceptions;

public class StateDoesNotExist extends RuntimeException {
	
	public StateDoesNotExist(String state) {
		super("The state given '" + state + "' does not exist");
	}
}
