package Diablo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import Diablo.items.Item;
public class MouseControl implements MouseListener, MouseMotionListener {
	private Game game;
	private Movement movement;
	Entity player;
	//new
	int itemClickedNumber = -1;
    int backpackClickedNumber = -1;
    
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
		
				/*new stuff
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

				//end of new stuff
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
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	//new 
	   private void swapInvToInv(int i, int j)
	    {
	        Item temp = game.getEntityList().get(0).inventory.getInventoryItem(i);
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
	        Item temp = game.getEntityList().get(0).inventory.getBackpackItem(i);
	        game.getEntityList().get(0).inventory.setBackpackItem(i, game.getEntityList().get(0).inventory.getBackpackItem(j));
	        game.getEntityList().get(0).inventory.setBackpackItem(j, temp);
	    }

	    private void swapInvToBack(int i, int j)
	    {
	        Item temp = game.getEntityList().get(0).inventory.getInventoryItem(i);
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
