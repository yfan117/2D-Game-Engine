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

		if(input == '1')
		{
			try{
				game.getEntityList().get(0).getItem(0).useItem();
				if(game.getEntityList().get(0).getItem(0).isDisposable())
					game.getEntityList().get(0).removeItem(0);
			}catch(Exception ex){}
		}

		if(input == '2')
		{
			try{
				game.getEntityList().get(0).getItem(1).useItem();
				if(game.getEntityList().get(0).getItem(1).isDisposable())
					game.getEntityList().get(0).removeItem(1);
			}catch(Exception ex){}
		}

		if(input == '3')
		{
			try{
				game.getEntityList().get(0).getItem(2).useItem();
				if(game.getEntityList().get(0).getItem(2).isDisposable())
					game.getEntityList().get(0).removeItem(2);
			}catch(Exception ex){}
		}

		if(input == '4')
		{
			try{
				game.getEntityList().get(0).getItem(3).useItem();
				if(game.getEntityList().get(0).getItem(3).isDisposable())
					game.getEntityList().get(0).removeItem(3);
			}catch(Exception ex){}
		}
		
		if(input == 'a')
		{
			
			game.getEntityList().get(0).moveLeft = true;
			//Renderer.cameraX-=10;
		}
		
		if(input == 'w') 
		{
			
			game.getEntityList().get(0).moveUp = true;
			//Renderer.cameraY-=10;
		}
		
		if(input == 's') 
		{

			game.getEntityList().get(0).moveDown = true;
			//Renderer.cameraY+=10;
		}
		
		if(input == 'd') 
		{
			
			game.getEntityList().get(0).moveRight = true;
			//Renderer.cameraX+=10;
		}
		
		if(input == 'z') 
		{
			
			zoomRate += 100;
		}
		
		if(input == 'x') 
		{
			
			zoomRate -=100;
		}

		if(input == 't')
		{
			if(game.getEntityList().get(0).inventory.getInvOpen())
				game.getEntityList().get(0).inventory.setInvOpen(false);
			else
				game.getEntityList().get(0).inventory.setInvOpen(true);
		}
		
		game.getEntityList().get(0).newClick = true;
		
		game.getEntityList().get(0).north = false;
		game.getEntityList().get(0).south = false;
		game.getEntityList().get(0).west = false;
		game.getEntityList().get(0).east = false;
		
		
		game.getEntityList().get(0).directionCheck = true;
		
		game.getEntityList().get(0).target = game.getEntityList().get(0);

		
	}

	static int zoomRate = 0;
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
