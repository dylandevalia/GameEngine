package game.dylandevalia.engine.exceptions;

public class EngineAlreadyRunning extends RuntimeException {
	
	public EngineAlreadyRunning() {
		super("There is an instance already running");
	}
}
