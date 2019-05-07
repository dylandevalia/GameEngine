package game.dylandevalia.engine.states;

import game.dylandevalia.engine.Engine;

import java.awt.*;

public class State extends AdvancedState {
	
	private Graphics2D g;
	private double interpolate;
	
	@Override
	public void draw(Graphics2D g, double interpolate) {
		this.g = g;
		this.interpolate = interpolate;
		draw();
	}
	
	public void draw() {
		//drawBackground(RED);
	}
	
	public void fill(Color c) {
		g.setColor(c);
	}
	
	public void drawBackground() {
		g.fillRect(0, 0, Engine.getWindowWidth(), Engine.getWindowHeight());
	}
}
