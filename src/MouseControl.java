package Diablo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;
public class MouseControl implements MouseListener, MouseMotionListener {
	private Game game;
	private Movement movement;
	Entity player;
	public MouseControl(Game game)
	{
		this.game = game;
		player = game.getEntityList().get(0);
		movement = new Movement(player, game);
	}
	//public MouseControl(Movement movement){this.movement = movement;}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(SwingUtilities.isLeftMouseButton(e)) {
			//System.out.println("left click");
			//if(outBound(e.getX(), e.getY(), player) == false)
			{
				//player.clickedX = e.getX() + player.x;
				//player.clickedY = e.getY() + player.y;

				int eX = e.getX();
				int eY = e.getY();
		
				player.collision = false;
				System.out.println("clicked " +eX + " " + eY);
				
					
				if(eX < Game.centerX ) {
					player.clickedX = Renderer.cameraX- (Game.centerX - eX);
				}
				else if(eX > Game.centerX ) {
					player.clickedX = Renderer.cameraX + (eX - Game.centerX);
				}

				if(eY < Game.centerY ) {
					player.clickedY = Renderer.cameraY- (Game.centerY - eY);
				}
				else if(eY > Game.centerY ) {
					player.clickedY = Renderer.cameraY  + (eY - Game.centerY);
				}
				 
				
				//int offsetX = Renderer.resolutionX / Game.windowX;
				//int offsetY = Renderer.resolutionY / Game.windowY;
				/*
				int offsetX = Game.windowX / eX * Renderer.resolutionX;
				int offsetY = Game.windowY / eY * Renderer.resolutionY;
				
				
				if(eX < Game.centerX ) {
					player.clickedX = Renderer.cameraX - (Game.centerX- eX) * offsetX;
				}
				else if(eX > Game.centerX ) {
					player.clickedX = Renderer.cameraX + (offsetX - Renderer.resolutionX/2);
				}

				if(eY < Game.centerY ) {
					player.clickedY = Renderer.cameraY - (Game.centerX - eY) * offsetX;
				}
				else if(eY > Game.centerY ) {
					player.clickedY = Renderer.cameraY + (eY - Game.centerY) * offsetX;
				}
				*/
				player.clickedX =Math.round(player.clickedX/5)*5;
				player.clickedY =Math.round(player.clickedY/5)*5;
				
				System.out.println("player coordinates: " +player.clickedX+" "+player.clickedY);
		
				/*
				 * check for entity 
				 * starting from index 1
				 */
				for(int i =1; i < game.getEntityList().size();i++)
				{	
					Entity entity= game.getEntityList().get(i);//check for click collision
					if(entity.isEntity(player.clickedX,player.clickedY)) {
						System.out.println("entity here");
						entity.doAction();
					}
				}
				
				
				if(movement.isObstacles(player.clickedX, player.clickedY) == false)
				{
					player.newClick = true;

					player.north = false;
					player.south = false;
					player.west = false;
					player.east = false;


					player.directionCheck = true;

					player.target = player;

					//System.out.println(Math.sqrt(Math.pow(player.clickedX - 105, 2)+Math.pow(player.clickedY - 95, 2)));
					//System.out.println(Math.sqrt(Math.pow(player.clickedX - 95, 2)+Math.pow(player.clickedY - 95, 2)));



					player.move.nodeIndex = 1;
					player.move.checkPoint = new ArrayList<Node>();
					player.move.usedGrid   = new ArrayList<Node>();
					player.move.checkPoint.add(new Node(player.x, player.y));
					player.move.pathFind();
					player.hasPath = true;
				}


			}
		}

		if (SwingUtilities.isRightMouseButton(e))
		{

			int eX = e.getX();
			int eY = e.getY();

			int destinationX = eX;
			int destinationY = eY;

			int orginX = player.x;
			int orginY = player.y;



			if(player.isMelee == false)
			{

				if(eX < Game.centerX ) {
					destinationX = player.x - (Game.centerX - eX)*3;
				}
				else if(eX > Game.centerX ) {
					destinationX = player.x + (eX - Game.centerX)*3;
					//orginX = orginX + 10;
				}

				if(eY < Game.centerY ) {
					destinationY = player.y - (Game.centerY - eY)*3;
				}
				else if(eY > Game.centerY ) {
					destinationY = player.y + (eY - Game.centerY)*3;
					//orginY = orginY + 10;
				}




			try {
					game.getProjectileList().add(new Entity("arrow", destinationX, destinationY, 10));
				/*
					projectile.get(projectile.size()-1).move.nodeIndex = 1;
					projectile.get(projectile.size()-1).move.checkPoint = new ArrayList<Node>();
					projectile.get(projectile.size()-1).move.usedGrid   = new ArrayList<Node>();
					projectile.get(projectile.size()-1).move.checkPoint.add(new Node(player.x, player.y));
					projectile.get(projectile.size()-1).move.pathFind();
				*/

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			 }
			else
			{
				Entity temp;
				if(eX < Game.centerX ) {
					destinationX = player.x - (Game.centerX - eX);
				}
				else if(eX > Game.centerX ) {
					destinationX = player.x + (eX - Game.centerX);
					//orginX = orginX + 10;
				}

				if(eY < Game.centerY ) {
					destinationY = player.y - (Game.centerY - eY);
				}
				else if(eY > Game.centerY ) {
					destinationY = player.y + (eY - Game.centerY);
					//orginY = orginY + 10;
				}


				try {
					temp = new Entity("melee", 0, 0, 0);
					//System.out.println(temp.isCollision(destinationX, destinationY, temp));
					player.target = game.getEntityList().get(temp.move.isCollision(destinationX, destinationY, temp));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				player.clickedX =Math.round(player.target.x/5)*5;
				player.clickedY =Math.round(player.target.y/5)*5;

				player.move.nodeIndex = 1;
				player.move.checkPoint = new ArrayList<Node>();
				player.move.usedGrid   = new ArrayList<Node>();
				player.move.checkPoint.add(new Node(player.x, player.y));
				player.move.pathFind();

				//player.clickedX = player.target.x;
				//player.clickedY = player.target.y;


				player.newClick = true;

				player.north = false;
				player.south = false;
				player.west = false;
				player.east = false;

				player.directionCheck = true;
				//display.update();
				
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * implements MouseMotionListener Functions
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		/*
		 * get eX and eY in terms of 'world-coordinates'
		 */
		int eX=e.getX();
		int eY=e.getY();
		if(eX < Game.centerX ) {
			eX = Renderer.cameraX- (Game.centerX - eX);
		}
		else if(eX > Game.centerX ) {
			eX = Renderer.cameraX + (eX - Game.centerX);
		}

		if(eY < Game.centerY ) {
			eY = Renderer.cameraY- (Game.centerY - eY);
		}
		else if(eY > Game.centerY ) {
			eY = Renderer.cameraY  + (eY - Game.centerY);
		}
		eX =Math.round(eX/5)*5;
		eY=Math.round(eY/5)*5;

		/*
		 * Check if mouse hovering over an action
		 */
		for(int i =1; i < game.getEntityList().size();i++){	
			Entity entity= game.getEntityList().get(i);//check for entity collision
			if(entity.isEntity(eX,eY) && entity.actionable()==true ) {
				if(game.dialogue==true){
					game.hovering=false;
					return;
				}
				else {
					game.hovering=true;
					return;
				}
			}	
			else {
				game.hovering=false;
			}
		}
	}
	

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

}
