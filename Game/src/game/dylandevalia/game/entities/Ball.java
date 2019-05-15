package game.dylandevalia.game.entities;

import game.dylandevalia.engine.Engine;
import game.dylandevalia.engine.utility.ColorMaterial;
import game.dylandevalia.engine.utility.Vector2D;

import java.awt.*;

public class Ball extends BaseEntity {
	
	private Vector2D vel = new Vector2D(1, 1).setMag(10);
	
	public Ball() {
		super(Engine.getWindowWidth() / 2, Engine.getWindowHeight() / 2, 50, 50);
	}
	
	@Override
	public void update() {
		super.update();
		pos.add(vel);
		
		if (pos.x < 0 || pos.x > Engine.getWindowWidth() - width) {
			vel.x *= -1;
		}
		if (pos.y < 0 || pos.y > Engine.getWindowHeight() - height) {
			vel.y *= -1;
		}
	}
	
	@Override
	public void draw(Graphics2D g, double interpolate) {
		super.draw(g, interpolate);
		g.setColor(ColorMaterial.BLUE_GREY[0]);
		g.fillOval(((int) drawPos.x), ((int) drawPos.y), width, height);
	}
}