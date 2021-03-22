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

		if(game.responsing==true) {
			if(input=='1') {
				game.responsing=false;
				game.dialogueObj.manager.chooseResponse(1);
				System.out.println("Choose 1");
			}
			if(input=='2') {
				game.responsing=false;
				game.dialogueObj.manager.chooseResponse(2);
				System.out.println("Choose 2");
			}
		}
		if(game.dialogue==true && game.responsing==false) {
			if(input=='n') { //continue dialogue
				game.continueDialogue=true;
			}
		}

		else
		{
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			{
				game.display.switchJPanels(2);
			}

			if (input == 'm')
			{

				game.getEntityList().get(0).isMelee = !(game.getEntityList().get(0).isMelee);
				System.out.println(game.getEntityList().get(0).isMelee);
			}

			if (input == 'a')
			{

				game.getEntityList().get(0).moveLeft = true;
				//Renderer.cameraX-=10;
			}

			if (input == 'w')
			{

				game.getEntityList().get(0).moveUp = true;
				//Renderer.cameraY-=10;
			}

			if (input == 's')
			{

				game.getEntityList().get(0).moveDown = true;
				//Renderer.cameraY+=10;
			}

			if (input == 'd')
			{

				game.getEntityList().get(0).moveRight = true;
				//Renderer.cameraX+=10;
			}

			if (input == 'z')
			{

				zoomRate += 100;
			}

			if (input == 'x')
			{

				zoomRate -= 100;
			}

			if (input == 't')
			{
				if (game.getEntityList().get(0).inventory.getBackpackOpen())
					game.getEntityList().get(0).inventory.setBackpackOpen(false);
				else
					game.getEntityList().get(0).inventory.setBackpackOpen(true);
			}

			game.getEntityList().get(0).newClick = true;

			game.getEntityList().get(0).north = false;
			game.getEntityList().get(0).south = false;
			game.getEntityList().get(0).west = false;
			game.getEntityList().get(0).east = false;


			game.getEntityList().get(0).directionCheck = true;

			game.getEntityList().get(0).target = game.getEntityList().get(0);

		}
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
