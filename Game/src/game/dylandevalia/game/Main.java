package game.dylandevalia.game;

import game.dylandevalia.engine.Engine;
import game.dylandevalia.game.states.Start;

import java.awt.*;

public class Main {
	
	public static void main(String[] args) {
		
		Engine.registerState("Start", Start.class);
		Engine.runWindowed(new Dimension(1280, 720), "Game");
		
		if (false) {
			Engine.runWindowedResizeable(null, "");
			Engine.runFullscreen("");
		}
	}
}
