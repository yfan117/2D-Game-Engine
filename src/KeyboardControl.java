package Diablo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardControl extends Game implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("here");
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) 
		{
			System.out.println("here");
		}
		if(e.getKeyCode() == KeyEvent.VK_Q) 
		{
			
			list.get(0).isMelee = !(list.get(0).isMelee);
			System.out.println(list.get(0).isMelee);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
