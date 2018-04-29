package game.dylandevalia.engine.game.entities;

import game.dylandevalia.engine.gui.Window;
import game.dylandevalia.engine.utility.Vector2D;
import java.awt.Graphics2D;

public class BaseEntity {
	
	/**
	 * The position vector of the entity - Use this in {@link #update()} Corresponds to the top-left
	 * corner of the object
	 */
	protected Vector2D pos = new Vector2D();
	/**
	 * The position to draw to - Use this in {@link #draw(Graphics2D, double)} Corresponds to the
	 * top-left corner of the object
	 */
	protected Vector2D drawPos = new Vector2D();
	/**
	 * The dimensions of the object
	 */
	protected int width, height;
	/**
	 * If the object is on the screen
	 */
	protected boolean onScreen;
	/**
	 * The last position of the object, updated before {@link #update()}. Used for interpolation
	 * with {@link #pos} and saved to {@link #drawPos} at the beginning of {@link #draw(Graphics2D,
	 * double)} in {@link #calculateDrawPos(double)}
	 */
	private Vector2D lastPos = new Vector2D();
	
	protected BaseEntity(int x, int y, int width, int height) {
		this.pos.set(x, y);
		this.width = width;
		this.height = height;
	}
	
	protected void update() {
		this.lastPos = this.pos;
	}
	
	protected void draw(Graphics2D g, double interpolate) {
		calculateDrawPos(interpolate);
		isOnScreen();
	}
	
	public Vector2D getPos() {
		return pos;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	private void calculateDrawPos(double interpolate) {
		if (pos == lastPos) {
			drawPos = pos;
		}
		drawPos.x = ((pos.x - lastPos.x) * interpolate + lastPos.x);
		drawPos.y = ((pos.y - lastPos.y) * interpolate + lastPos.y);
	}
	
	private void isOnScreen() {
		onScreen =
			!(drawPos.x + width < 0 || drawPos.x > Window.WIDTH
				|| drawPos.y + height < 0 || drawPos.y > Window.HEIGHT);
	}
}
