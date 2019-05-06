package game.dylandevalia.engine.exceptions;

public class EngineAlreadyRunning extends RuntimeException {
	
	public EngineAlreadyRunning() {
		super("There is an instance already running");
	}
	
	public EngineAlreadyRunning(String message) {
		super(message);
	}
	
	public EngineAlreadyRunning(String message, Throwable err) {
		super(message, err);
	}
	
	public EngineAlreadyRunning(Throwable err) {
		super(err);
	}
}
