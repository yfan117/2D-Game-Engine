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
		
		char input = e.getKeyChar();
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) 
		{
			System.out.println("here");
			
		}
		if(input == 'm') 
		{
			
			list.get(0).isMelee = !(list.get(0).isMelee);
			System.out.println(list.get(0).isMelee);
		}
		
		if(input == 'a') 
		{
			
			list.get(0).moveLeft = true;
	
		}
		
		if(input == 'w') 
		{
			
			list.get(0).moveUp = true;
		}
		
		if(input == 's') 
		{

			list.get(0).moveDown = true;
		}
		
		if(input == 'd') 
		{
			
			list.get(0).moveRight = true;
		}
		
		
		list.get(0).newClick = true;
		
		list.get(0).north = false;
		list.get(0).south = false;
		list.get(0).west = false;
		list.get(0).east = false;
		
		
		list.get(0).directionCheck = true;
		
		list.get(0).target = list.get(0);

		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	char input = e.getKeyChar();
			
			
			if(input == 'a') 
			{
				
				list.get(0).moveLeft = false;
			}
			
			if(input == 'w') 
			{
				
				list.get(0).moveUp = false;
			}
			
			if(input == 's') 
			{
	
				list.get(0).moveDown = false;
			}
			
			if(input == 'd') 
			{
				
				list.get(0).moveRight = false;
			}
	
				
	
	}
	

}
