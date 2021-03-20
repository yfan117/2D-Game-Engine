package src;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {
	public void keyPressed(KeyEvent e) {
		char ch = e.getKeyChar();
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			Viewport.incOSY(50);
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			Viewport.incOSY(-50);
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Viewport.incOSX(100);
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			Viewport.incOSX(-100);
		}
		if(e.getKeyCode() == KeyEvent.VK_EQUALS) {
			System.out.println("zoom");
			Viewport.incZoom(-.5);
			Viewport.incZoomC(1);
		}
		if(e.getKeyCode() == KeyEvent.VK_SUBTRACT) {
			System.out.println("zoom");
			Viewport.incZoom(.5);
			Viewport.incZoomC(1);
		}
	}
}
