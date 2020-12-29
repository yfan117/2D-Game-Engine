package Diablo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardControl implements KeyListener {
	private Game game;

	public KeyboardControl(Game game){this.game = game;}

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
			
			game.getEntityList().get(0).isMelee = !(game.getEntityList().get(0).isMelee);
			System.out.println(game.getEntityList().get(0).isMelee);
		}
		
		if(input == 'a') 
		{
			
			game.getEntityList().get(0).moveLeft = true;
	
		}
		
		if(input == 'w') 
		{
			
			game.getEntityList().get(0).moveUp = true;
		}
		
		if(input == 's') 
		{

			game.getEntityList().get(0).moveDown = true;
		}
		
		if(input == 'd') 
		{
			
			game.getEntityList().get(0).moveRight = true;
		}
		
		if(input == 'z') 
		{
				
			zoomRate += 100;
		}
			
		if(input == 'x') 
		{
				
			zoomRate -=100;
		}
		game.getEntityList().get(0).newClick = true;
		
		game.getEntityList().get(0).north = false;
		game.getEntityList().get(0).south = false;
		game.getEntityList().get(0).west = false;
		game.getEntityList().get(0).east = false;
		
		
		game.getEntityList().get(0).directionCheck = true;
		
		game.getEntityList().get(0).target = game.getEntityList().get(0);

		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	char input = e.getKeyChar();
			
			
			if(input == 'a') 
			{
				
				game.getEntityList().get(0).moveLeft = false;
			}
			
			if(input == 'w') 
			{
				
				game.getEntityList().get(0).moveUp = false;
			}
			
			if(input == 's') 
			{
	
				game.getEntityList().get(0).moveDown = false;
			}
			
			if(input == 'd') 
			{
				
				game.getEntityList().get(0).moveRight = false;
			}
	}
}
