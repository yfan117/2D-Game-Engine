package src;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {
	public void keyPressed(KeyEvent e) {
		char ch = e.getKeyChar();
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			Viewport.incOSY(MapBuilder.pixelH);
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			Viewport.incOSY(-MapBuilder.pixelH);
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Viewport.incOSX(MapBuilder.pixelW);
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			Viewport.incOSX(-MapBuilder.pixelW);
		}
	}
}
