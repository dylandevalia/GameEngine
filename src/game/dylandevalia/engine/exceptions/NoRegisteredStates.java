package game.dylandevalia.engine.exceptions;

public class NoRegisteredStates extends RuntimeException {
	
	public NoRegisteredStates() {
		super("There are no states registered. At least one registered state is required");
	}
	
	public NoRegisteredStates(String message) {
		super(message);
	}
	
	public NoRegisteredStates(String message, Throwable err) {
		super(message, err);
	}
	
	public NoRegisteredStates(Throwable err) {
		super(err);
	}
}
