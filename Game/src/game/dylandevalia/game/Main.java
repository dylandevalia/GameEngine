package game.dylandevalia.game;

import game.dylandevalia.engine.Engine;
import game.dylandevalia.engine.utility.Log;
import game.dylandevalia.game.states.Pause;
import game.dylandevalia.game.states.Play;
import game.dylandevalia.game.states.Start;

import java.awt.*;

public class Main {
	
	public static void main(String[] args) {
		Log.SET_INFO();
		
		Engine.registerState("start", Start.class);
		Engine.registerState("pause", Pause.class);
		Engine.registerState("play", Play.class);
		
		Engine.runWindowedResizeable(new Dimension(720, 720), "start", "Game");
	}
}
