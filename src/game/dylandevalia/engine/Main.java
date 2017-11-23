package game.dylandevalia.engine;

import game.dylandevalia.engine.gui.Window;
import javax.swing.SwingUtilities;

public class Main {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Window();
			}
		});
	}
}
