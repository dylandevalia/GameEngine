package game.dylandevalia.game.states;

import game.dylandevalia.engine.states.State;
import game.dylandevalia.engine.utility.Bundle;
import game.dylandevalia.game.entities.Ball;

import java.awt.*;

public class Start extends State {
	
	Ball ball;
	
	@Override
	public void initialise(Bundle bundle) {
		ball = new Ball();
	}
	
	@Override
	public void update() {
		ball.update();
	}
	
	@Override
	public void draw(Graphics2D g, double interpolate) {
		ball.draw(g, interpolate);
	}
}
