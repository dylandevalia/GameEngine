package game.dylandevalia.engine.states;

import game.dylandevalia.engine.utility.Bundle;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface IState {
	
	void onCreate(Bundle bundle);
	
	void onEnable(Bundle bundle);
	
	void onDisable(Bundle bundle);
	
	void onDestroy(Bundle bundle);
	
	void update();
	
	void draw(Graphics2D g, double interpolate);
	
	void keyPressed(KeyEvent e);
	
	void keyReleased(KeyEvent e);
	
	void mousePressed(MouseEvent e);
	
	void mouseReleased(MouseEvent e);
}
