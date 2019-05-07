package game.dylandevalia.engine.exceptions;

/**
 * Exception called if there is already an instance of the
 * engine running and another instance is trying to be created
 */
public class EngineAlreadyRunning extends RuntimeException {
	
	public EngineAlreadyRunning() {
		super("There is an instance already running");
	}
}
