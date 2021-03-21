package Diablo;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

public class MouseControl implements MouseListener, MouseMotionListener {
	private Game game;
	private Movement movement;
	Entity player;
	int itemClickedNumber = -1;
	int backpackClickedNumber = -1;

	public MouseControl(Game game)
	{
		this.game = game;
		player = game.getEntityList().get(0);
		movement = new Movement(player, game);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	boolean isPressed = false;
	//int
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub





		if(SwingUtilities.isLeftMouseButton(e))
		{

			int eX = e.getX();
			int eY = e.getY();


			player.collision = false;
			//System.out.println("clicked " +eX + " " + eY);


			if(eX < Game.centerX ) {
				player.clickedX = Renderer.cameraControlX- (Game.centerX - eX);
			}
			else if(eX > Game.centerX ) {
				player.clickedX = Renderer.cameraControlX + (eX - Game.centerX);
			}

			if(eY < Game.centerY ) {
				player.clickedY = Renderer.cameraControlY- (Game.centerY - eY);
			}
			else if(eY > Game.centerY ) {
				player.clickedY = Renderer.cameraControlY  + (eY - Game.centerY);
			}

			
			player.clickedX =Math.round(player.clickedX/5)*5;
			player.clickedY =Math.round(player.clickedY/5)*5;

			if(movement.isObstacles(player.clickedX, player.clickedY) == false)
			{
				player.hasPath = true;
				player.newClick = false;
				player.newCheckPoint = false;


				player.target = player;

				//System.out.println(Math.sqrt(Math.pow(player.clickedX - 105, 2)+Math.pow(player.clickedY - 95, 2)));
				//System.out.println(Math.sqrt(Math.pow(player.clickedX - 95, 2)+Math.pow(player.clickedY - 95, 2)));


				player.move.nodeIndex = 1;
				player.move.checkPoint = new ArrayList<Node>();
				player.move.usedGrid   = new ArrayList<Node>();
				player.move.checkPoint.add(new Node(player.x, player.y));
				player.move.pathFind();
				player.state = "run";
				player.newClick = true;
				player.newCheckPoint = true;

				player.state = "run";
				//player.picCounter = 0;

				/*
				try {
					game.sender.sending();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				*/
			}


			if((eX > 0) && (eY >0))
			{
				isPressed = true;
				player.collision = false;
			}


			System.out.println("clicked " +eX + " " + eY);

			for(int i =1; i < game.getEntityList().size();i++)
			{
				Entity entity= game.getEntityList().get(i);//check for click collision
				if(entity.isEntity(player.clickedX,player.clickedY)) {
					System.out.println("entity here");
					entity.doAction();
				}
			}

			//				//Player clicked inside spell slots
//				if(eX > (game.windowX / 2) - 74 && eX < (game.windowX / 2) + 74 && eY > game.windowY - 87 && eY < game.windowY - 50)
//				{
//					for(int i = 0; i < 4; i++)
//					{
//						if(eX > ((game.windowX / 2) - (74 - (i * 37))) && eX < ((game.windowX / 2) - (74 - ((i+1) * 37))) && eY > game.windowY - 87 && eY < game.windowY - 50)
//						{
//							if(i == 0)//Spell slot 0
//							{
//								System.out.println("0");
//							}
//							if(i == 1)//Spell slot 1
//							{
//								System.out.println("1");
//							}
//							if(i == 2)//Spell slot 2
//							{
//								System.out.println("2");
//							}
//							if(i == 3)//Spell slot 3
//							{
//								System.out.println("3");
//							}
//						}
//					}
//					return;
//				}

			//Player clicked inside the inventory while it is open
			if (checkInventory(eX, eY))
				return;

			//Player clicked inside backpack while it is open
			if (checkBackpack(eX, eY))
				return;


		}




		if (SwingUtilities.isRightMouseButton(e))
		{

			int eX = e.getX();
			int eY = e.getY();

			int destinationX = 0;
			int destinationY = 0;

			int orginX = player.x;
			int orginY = player.y;



			if(player.isMelee == false)
			{

				if(eX < Game.centerX ) {
					destinationX = Renderer.cameraControlX- (Game.centerX - eX);
				}
				else if(eX > Game.centerX ) {
					destinationX = Renderer.cameraControlX + (eX - Game.centerX);
				}

				if(eY < Game.centerY ) {
					destinationY = Renderer.cameraControlY- (Game.centerY - eY);
				}
				else if(eY > Game.centerY ) {
					destinationY = Renderer.cameraControlY  + (eY - Game.centerY);
				}

				
				destinationX =Math.round(destinationX/5)*5;
				destinationY =Math.round(destinationY/5)*5;
				
				System.out.println(destinationX +" " +destinationX);

			try {
					game.getProjectileList().add(new Entity(game, "arrow", destinationX, destinationY, 80));
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
					destinationX = Renderer.cameraControlX- (Game.centerX - eX);
				}
				else if(eX > Game.centerX ) {
					destinationX = Renderer.cameraControlX + (eX - Game.centerX);
				}

				if(eY < Game.centerY ) {
					destinationY = Renderer.cameraControlY- (Game.centerY - eY);
				}
				else if(eY > Game.centerY ) {
					destinationY = Renderer.cameraControlY  + (eY - Game.centerY);
				}

				
				destinationX =Math.round(destinationX/5)*5;
				destinationY =Math.round(destinationY/5)*5;

				try {
					temp = new Entity(game, "melee", 0, 0, 0);
					//System.out.println(temp.isCollision(destinationX, destinationY, temp));
					player.target = game.getEntityList().get(temp.move.isCollision(destinationX, destinationY, temp));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}



			}
		}



	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		isPressed = false;

		int eX = e.getX();
		int eY = e.getY();
		int check = 0;

		//Check backpack
		checkBackpack(eX, eY);

		//Check inventory
		checkInventory(eX, eY);

		for (int i = 0; i < game.getEntityList().get(0).inventory.getBackpackCapacity(); i++)
		{
			if (game.getEntityList().get(0).inventory.getBackpackBool(i))
				check++;
		}

		for (int j = 0; j < game.getEntityList().get(0).inventory.getInventoryCapacity(); j++)
		{
			if (game.getEntityList().get(0).inventory.getInventoryBool(j))
				check++;
		}

		if (check == 0)//player clicked an item
		{
			try
			{
				if (itemClickedNumber != -1)
				{
					game.getEntityList().get(0).inventory.getInventoryItem(itemClickedNumber).useItem();
					if (game.getEntityList().get(0).inventory.getInventoryItem(itemClickedNumber).isStackable())
					{
						game.getEntityList().get(0).inventory.getInventoryItem(itemClickedNumber).setNumberInStack(game.getEntityList().get(0).inventory.getInventoryItem(itemClickedNumber).getNumberInStack() - 1);
						if (game.getEntityList().get(0).inventory.getInventoryItem(itemClickedNumber).getNumberInStack() <= 0)
						{
							game.getEntityList().get(0).inventory.setInventoryItem(itemClickedNumber, null);
						}
					} else
					{
						game.getEntityList().get(0).inventory.setInventoryItem(itemClickedNumber, null);
					}
					itemClickedNumber = -1;
				}
			}catch(Exception ex){}
		}

		if (check == 1)//Player dragged an item onto the ground
		{
			if (itemClickedNumber != -1)
			{
				game.getEntityList().get(0).inventory.setInventoryItem(itemClickedNumber, null);
				itemClickedNumber = -1;
			}
			if (backpackClickedNumber != -1)
			{
				game.getEntityList().get(0).inventory.setBackpackItem(backpackClickedNumber, null);
				backpackClickedNumber = -1;
			}
		}

		if (check == 2)//player is swapping two items
		{
			int a = game.getEntityList().get(0).inventory.getInventoryCapacity();
			int b = game.getEntityList().get(0).inventory.getBackpackCapacity();
			int c = a + b;
			boolean[] temp = new boolean[c];

			for (int i = 0; i < a; i++)
			{
				temp[i] = game.getEntityList().get(0).inventory.getInventoryBool(i);
			}
			for (int i = a; i < c; i++)
			{
				temp[i] = game.getEntityList().get(0).inventory.getBackpackBool(i - a);
			}

			int x = 0;
			int y = 0;
			int count = 0;

			for (int i = 0; i < c; i++)
			{
				if (temp[i] && count == 1)
				{
					y = i;
					count++;
				}
				if (temp[i] && count == 0)
				{
					x = i;
					count++;
				}
			}

			if (y < a)//Inv to inv swap
			{
				swapInvToInv(x, y);
			}
			if (x >= a)//Backpack to backpack swap
			{
				swapBackToBack(x - a, y - a);
			}
			if (x < a && y >= a)//Inv to backpack swap
			{
				swapInvToBack(x, y - a);
			}
		}

		game.getEntityList().get(0).inventory.resetAllBools();
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
			eX = Renderer.cameraControlX- (Game.centerX - eX);
		}
		else if(eX > Game.centerX ) {
			eX = Renderer.cameraControlX + (eX - Game.centerX);
		}

		if(eY < Game.centerY ) {
			eY = Renderer.cameraControlY- (Game.centerY - eY);
		}
		else if(eY > Game.centerY ) {
			eY = Renderer.cameraControlY  + (eY - Game.centerY);
		}
		eX =Math.round(eX/5)*5;
		eY=Math.round(eY/5)*5;

		/*new
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
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	
			int eX = e.getX();
			int eY = e.getY();


			if (SwingUtilities.isLeftMouseButton(e))
			{
			player.collision = false;
			//System.out.println("clicked " +eX + " " + eY);


			if(eX < Game.centerX ) {
				player.clickedX = Renderer.cameraControlX- (Game.centerX - eX);
			}
			else if(eX > Game.centerX ) {
				player.clickedX = Renderer.cameraControlX + (eX - Game.centerX);
			}

			if(eY < Game.centerY ) {
				player.clickedY = Renderer.cameraControlY- (Game.centerY - eY);
			}
			else if(eY > Game.centerY ) {
				player.clickedY = Renderer.cameraControlY  + (eY - Game.centerY);
			}

			
			player.clickedX =Math.round(player.clickedX/5)*5;
			player.clickedY =Math.round(player.clickedY/5)*5;

			if(movement.isObstacles(player.clickedX, player.clickedY) == false)
			{
				player.hasPath = true;

				player.target = player;

				//System.out.println(Math.sqrt(Math.pow(player.clickedX - 105, 2)+Math.pow(player.clickedY - 95, 2)));
				//System.out.println(Math.sqrt(Math.pow(player.clickedX - 95, 2)+Math.pow(player.clickedY - 95, 2)));


				player.move.nodeIndex = 1;
				player.move.checkPoint = new ArrayList<Node>();
				player.move.usedGrid   = new ArrayList<Node>();
				player.move.checkPoint.add(new Node(player.x, player.y));
				player.move.pathFind();
				player.state = "run";
				player.newClick = true;
				player.newCheckPoint = true;

				
				//player.picCounter = 0;

				/*
				try {
					game.sender.sending();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				*/
			}
			}

		
	}
	private void swapInvToInv(int i, int j)
	{
		Diablo.Items.Item temp = game.getEntityList().get(0).inventory.getInventoryItem(i);
//	        if(game.getEntityList().get(0).inventory.getInventoryItem(i).getName() == game.getEntityList().get(0).inventory.getInventoryItem(j).getName() && (game.getEntityList().get(0).inventory.getInventoryItem(i).isStackable()))
//	        {
//	            game.getEntityList().get(0).inventory.getInventoryItem(i).setNumberInStack(game.getEntityList().get(0).inventory.getInventoryItem(i).getNumberInStack() + game.getEntityList().get(0).inventory.getInventoryItem(j).getNumberInStack());
//	            game.getEntityList().get(0).inventory.setInventoryItem(j, null);
//	            return;
//	        }
		game.getEntityList().get(0).inventory.setInventoryItem(i, game.getEntityList().get(0).inventory.getInventoryItem(j));
		game.getEntityList().get(0).inventory.setInventoryItem(j, temp);
	}

	private void swapBackToBack(int i, int j)
	{
		Diablo.Items.Item temp = game.getEntityList().get(0).inventory.getBackpackItem(i);
		game.getEntityList().get(0).inventory.setBackpackItem(i, game.getEntityList().get(0).inventory.getBackpackItem(j));
		game.getEntityList().get(0).inventory.setBackpackItem(j, temp);
	}

	private void swapInvToBack(int i, int j)
	{
		Diablo.Items.Item temp = game.getEntityList().get(0).inventory.getInventoryItem(i);
		game.getEntityList().get(0).inventory.setInventoryItem(i, game.getEntityList().get(0).inventory.getBackpackItem(j));
		game.getEntityList().get(0).inventory.setBackpackItem(j, temp);
	}

	private boolean checkBackpack(int eX, int eY)
	{
		boolean b = false;
		if (game.getEntityList().get(0).inventory.getBackpackOpen())
		{
			if (eX > ((Diablo.Game.windowX) - game.getEntityList().get(0).inventory.getCols() * 50) && eY > ((Diablo.Game.windowY / 2) - ((game.getEntityList().get(0).inventory.getRows() / 2) * 50)) && eY < ((Diablo.Game.windowY / 2) + ((game.getEntityList().get(0).inventory.getRows() / 2) * 50)))
			{
				for (int i = 0; i < game.getEntityList().get(0).inventory.getRows(); i++)
				{
					for (int j = 0; j < game.getEntityList().get(0).inventory.getCols(); j++)
					{
						int col = ((Diablo.Game.windowX) - (game.getEntityList().get(0).inventory.getCols() * 50) + (50 * j));
						int row = ((Diablo.Game.windowY / 2) - ((game.getEntityList().get(0).inventory.getRows() / 2) * 50) + (50 * i));
						if (eX > col && eX < col + 50 && eY > row && eY < row + 50)
						{
							for (int y = 0; y <= game.getEntityList().get(0).inventory.getRows(); y++)
							{
								for (int x = 0; x <= game.getEntityList().get(0).inventory.getCols(); x++)
								{
									if (i == (game.getEntityList().get(0).inventory.getRows() - y) && j == (game.getEntityList().get(0).inventory.getCols() - x))
									{
										backpackClickedNumber = i * game.getEntityList().get(0).inventory.getCols() + j;
										game.getEntityList().get(0).inventory.setBackpackBool((i * game.getEntityList().get(0).inventory.getCols()) + j, !(game.getEntityList().get(0).inventory.getBackpackBool(i * game.getEntityList().get(0).inventory.getCols() + j)));
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
	private boolean checkInventory(int eX, int eY)
	{
		boolean b = false;
		if (game.getEntityList().get(0).inventory.getInventoryOpen())
		{
			if (eX > ((Diablo.Game.windowX / 2) - ((game.getEntityList().get(0).inventory.getInventoryCols() / 2) * 50)) && eX < ((Diablo.Game.windowX / 2) + ((game.getEntityList().get(0).inventory.getInventoryCols() / 2) * 50)) && eY > ((Diablo.Game.windowY) - (game.getEntityList().get(0).inventory.getInventoryRows() * 50)))
			{
				for (int i = 0; i < game.getEntityList().get(0).inventory.getInventoryRows(); i++)
				{
					for (int j = 0; j < game.getEntityList().get(0).inventory.getInventoryCols(); j++)
					{
						int col = ((Diablo.Game.windowX / 2) - ((game.getEntityList().get(0).inventory.getInventoryCols() * 50) / 2) + (50 * j));
						int row = ((Diablo.Game.windowY) - (game.getEntityList().get(0).inventory.getInventoryRows() * 50) + (50 * i));

						if (eX > col && eX < col + 50 && eY > row && eY < row + 50)
						{
							for (int y = 0; y <= game.getEntityList().get(0).inventory.getInventoryRows(); y++)
							{
								for (int x = 0; x <= game.getEntityList().get(0).inventory.getInventoryCols(); x++)
								{
									if (i == (game.getEntityList().get(0).inventory.getInventoryRows() - y) && j == (game.getEntityList().get(0).inventory.getInventoryCols() - x))
									{
										itemClickedNumber = i * game.getEntityList().get(0).inventory.getInventoryCols() + j;
										game.getEntityList().get(0).inventory.setInventoryBool((i * game.getEntityList().get(0).inventory.getInventoryCols()) + j, !(game.getEntityList().get(0).inventory.getInventoryBool(i * game.getEntityList().get(0).inventory.getInventoryCols() + j)));
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
