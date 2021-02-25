package Diablo;
import Items.Item;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.SwingUtilities;
public class MouseControl implements MouseListener {
	private Game game;
	private Movement movement;
	boolean[] invBools = new boolean[4];
	//boolean[] backpackBools = new boolean[game.getEntityList().get(0).inventory.getCapacity()];
	boolean[] backpackBools = new boolean[12];
	Entity player;
	public MouseControl(Game game)
	{
		Arrays.fill(invBools, Boolean.FALSE);
		Arrays.fill(backpackBools, Boolean.FALSE);
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

				//Player clicked inside
				//the items inventory
				if(eX > (game.windowX / 2) - 100 && eX < (game.windowX / 2) + 100 && eY > game.windowY - 50)
				{
					System.out.println("Items");
					System.out.println("clicked " +eX + " " + eY);
					if(eX > (game.windowX / 2) - 100 && eX < (game.windowX / 2) - 50 && eY > game.windowY - 50)//Player has clicked inside the item 1 slot
					{
						invBools[0] = true;
					}
					if(eX > (game.windowX / 2) - 50 && eX < (game.windowX / 2) && eY > game.windowY - 50)//Player has clicked inside the item 2 slot
					{
						invBools[1] = true;
					}
					if(eX > (game.windowX / 2) && eX < (game.windowX / 2) + 50 && eY > game.windowY - 50)//Player has clicked inside the item 3 slot
					{
						invBools[2] = true;
					}
					if(eX > (game.windowX / 2) + 50 && eX < (game.windowX / 2) + 100 && eY > game.windowY - 50)//Player has clicked inside the item 4 slot
					{
						invBools[3] = true;
					}
					return;
				}

				//Player clicked inside spell slots
				if(eX > (game.windowX / 2) - 74 && eX < (game.windowX / 2) + 74 && eY > game.windowY - 87 && eY < game.windowY - 50)
				{
					for(int i = 0; i < 4; i++)
					{
						if(eX > ((game.windowX / 2) - (74 - (i * 37))) && eX < ((game.windowX / 2) - (74 - ((i+1) * 37))) && eY > game.windowY - 87 && eY < game.windowY - 50)
						{
							if(i == 0)//Spell slot 0
							{
								System.out.println("0");
							}
							if(i == 1)//Spell slot 1
							{
								System.out.println("1");
							}
							if(i == 2)//Spell slot 2
							{
								System.out.println("2");
							}
							if(i == 3)//Spell slot 3
							{
								System.out.println("3");
							}
						}
					}
					return;
				}

				//Player clicked inside backpack while it is open
				if(checkBackpack(eX, eY))
					return;

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
		int eX = e.getX();
		int eY = e.getY();
		int check = 0;

		//Check backpack
		checkBackpack(eX, eY);

		for(int i = 0; i < backpackBools.length; i++)
		{
			if(backpackBools[i])
				check++;
		}
		for(int j = 0; j < invBools.length; j++)
		{
			if(invBools[j])
				check++;
		}
		System.out.println(check);
		if(check == 0)//player clicked an item
		{

		}
		if (check == 1)//Player dragged an item onto the ground
		{


		}
		if(check == 2)//player is swapping two items
		{

		}

		if(invBools[0] == true)
		{
			if(eX > (game.windowX / 2) - 100 && eX < (game.windowX / 2) - 50 && eY > game.windowY - 90)//Player has clicked inside the item 1 slot
			{
				try{
					game.getEntityList().get(0).getItem(0).useItem();
					if(game.getEntityList().get(0).getItem(0).isDisposable())
						game.getEntityList().get(0).removeItem(0);
				}catch(Exception ex){}
			}
			if(eX > (game.windowX / 2) - 50 && eX < (game.windowX / 2) && eY > game.windowY - 90)//Player has clicked inside the item 2 slot
			{
				swapInvToInv(0, 1);
			}
			if(eX > (game.windowX / 2) && eX < (game.windowX / 2) + 50 && eY > game.windowY - 90)//Player has clicked inside the item 3 slot
			{
				swapInvToInv(0, 2);
			}
			if(eX > (game.windowX / 2) + 50 && eX < (game.windowX / 2) + 100 && eY > game.windowY - 90)//Player has clicked inside the item 4 slot
			{
				swapInvToInv(0, 3);
			}
			invBools[0] = false;
		}
		if(invBools[1] == true)
		{
			if(eX > (game.windowX / 2) - 100 && eX < (game.windowX / 2) - 50 && eY > game.windowY - 90)//Player has clicked inside the item 1 slot
			{
				swapInvToInv(1, 0);
			}
			if(eX > (game.windowX / 2) - 50 && eX < (game.windowX / 2) && eY > game.windowY - 90)//Player has clicked inside the item 2 slot
			{
				try{
					game.getEntityList().get(0).getItem(1).useItem();
					if(game.getEntityList().get(0).getItem(1).isDisposable())
						game.getEntityList().get(0).removeItem(1);
				}catch(Exception ex){}
			}
			if(eX > (game.windowX / 2) && eX < (game.windowX / 2) + 50 && eY > game.windowY - 90)//Player has clicked inside the item 3 slot
			{
				swapInvToInv(1, 2);
			}
			if(eX > (game.windowX / 2) + 50 && eX < (game.windowX / 2) + 100 && eY > game.windowY - 90)//Player has clicked inside the item 4 slot
			{
				swapInvToInv(1, 3);
			}
			invBools[1] = false;
		}
		if(invBools[2] == true)
		{
			if(eX > (game.windowX / 2) - 100 && eX < (game.windowX / 2) - 50 && eY > game.windowY - 90)//Player has clicked inside the item 1 slot
			{
				swapInvToInv(2, 0);
			}
			if(eX > (game.windowX / 2) - 50 && eX < (game.windowX / 2) && eY > game.windowY - 90)//Player has clicked inside the item 2 slot
			{
				swapInvToInv(2, 1);
			}
			if(eX > (game.windowX / 2) && eX < (game.windowX / 2) + 50 && eY > game.windowY - 90)//Player has clicked inside the item 3 slot
			{
				try{
					game.getEntityList().get(0).getItem(2).useItem();
					if(game.getEntityList().get(0).getItem(2).isDisposable())
						game.getEntityList().get(0).removeItem(2);
				}catch(Exception ex){}
			}
			if(eX > (game.windowX / 2) + 50 && eX < (game.windowX / 2) + 100 && eY > game.windowY - 90)//Player has clicked inside the item 4 slot
			{
				swapInvToInv(2, 3);
			}
			invBools[2] = false;
		}
		if(invBools[3] == true)
		{
			if(eX > (game.windowX / 2) - 100 && eX < (game.windowX / 2) - 50 && eY > game.windowY - 90)//Player has clicked inside the item 1 slot
			{
				swapInvToInv(3, 0);
			}
			if(eX > (game.windowX / 2) - 50 && eX < (game.windowX / 2) && eY > game.windowY - 90)//Player has clicked inside the item 2 slot
			{
				swapInvToInv(3, 1);
			}
			if(eX > (game.windowX / 2) && eX < (game.windowX / 2) + 50 && eY > game.windowY - 90)//Player has clicked inside the item 3 slot
			{
				swapInvToInv(3, 2);
			}
			if(eX > (game.windowX / 2) + 50 && eX < (game.windowX / 2) + 100 && eY > game.windowY - 90)//Player has clicked inside the item 4 slot
			{
				try{
					game.getEntityList().get(0).getItem(3).useItem();
					if(game.getEntityList().get(0).getItem(3).isDisposable())
						game.getEntityList().get(0).removeItem(3);
				}catch(Exception ex){}
			}
			invBools[3] = false;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	private void swapInvToInv(int i, int j)
	{
		Item temp = game.getEntityList().get(0).getItem(i);
		game.getEntityList().get(0).addItem(i, game.getEntityList().get(0).getItem(j));
		game.getEntityList().get(0).addItem(j, temp);
	}

	private boolean checkBackpack(int eX, int eY)
	{
		boolean b = false;
		if(game.getEntityList().get(0).inventory.getInvOpen())
		{
			if( eX > ((Diablo.Game.windowX) - game.getEntityList().get(0).inventory.getCols() * 50) && eY > ((Diablo.Game.windowY / 2) - ((game.getEntityList().get(0).inventory.getRows() / 2) * 50)) && eY < ((Diablo.Game.windowY / 2) + ((game.getEntityList().get(0).inventory.getRows() / 2) * 50)))
			{
				for(int i = 0; i < game.getEntityList().get(0).inventory.getRows(); i++)
				{
					for(int j = 0; j < game.getEntityList().get(0).inventory.getCols(); j++)
					{
						int col = ((Diablo.Game.windowX) - (game.getEntityList().get(0).inventory.getCols() * 50) + (50 * j));
						int row = ((Diablo.Game.windowY / 2) - ((game.getEntityList().get(0).inventory.getRows() / 2) *  50) + (50 * i));
						if( eX > col && eX < col + 50 && eY > row && eY < row + 50)
						{
							for(int y = 1; y <= game.getEntityList().get(0).inventory.getRows(); y++)
							{
								for(int x = 0; x <= game.getEntityList().get(0).inventory.getCols(); x++)
								{
									if(j == (game.getEntityList().get(0).inventory.getRows() - y) && i == (game.getEntityList().get(0).inventory.getCols() - x))
									{
										//i*3+j to access elements in array
										backpackBools[(i*game.getEntityList().get(0).inventory.getCols()) + j] = !backpackBools[(i*game.getEntityList().get(0).inventory.getCols()) + j];
									}
								}
							}
						}
					}
				}
				b = true;
			}
		}
		return b;
	}
}
