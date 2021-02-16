package Diablo;

import java.io.IOException;
import java.util.ArrayList;

public class Movement{
	
	int obsXY = 100;
	int obsSize = 0;
	
	Entity current;
	Game game;

	ArrayList<Node> checkPoint;
	ArrayList<Node> usedGrid;
	
	public Movement(Entity current, Game game )
	{
		this.current = current;
		this.game = game;
		
		checkPoint = new ArrayList<Node>();
		checkPoint.add(new Node(current.x, current.y));
		
		usedGrid   = new ArrayList<Node>();
		//checkPoint.add(new Node(current.x, current.y));
	}
	

	
	int nodeIndex = 0;
	
	
	public void keyBoardUpdate(Entity current)
	{
		if(current.moveRight == true)
		{
			if(isObstacles(current.x+10, current.y) == false)
			{
				//list.get(0).hasPath = true;
				//list.get(0).move.checkPoint.add(new Node(list.get(0).x+10, list.get(0).y));
				current.x = current.x + 10;
			}
		}
		
		if(current.moveLeft == true)
		{
			
			if(isObstacles(current.x-10, current.y) == false)
			{
				//current.hasPath = true;
				//current.move.checkPoint.add(new Node(current.x+10, current.y));
				current.x = current.x - 10;
			}
		}
		
		if(current.moveUp == true)
		{
			if(isObstacles(current.x, current.y - 10) == false)
			{
				//current.hasPath = true;
				//current.move.checkPoint.add(new Node(current.x+10, current.y));
				current.y = current.y - 10;
			}
		}
		
		if(current.moveDown == true)
		{
			if(isObstacles(current.x, current.y+10) == false)
			{
				//current.hasPath = true;
				//current.move.checkPoint.add(new Node(current.x+10, current.y));
				current.y = current.y + 10;
			}
		}
		
	}

	public void update(Entity current)
	{

		current.hasDoneDmage = false;
		
		
		isVisible();
	
		isCollision(current.clickedX, current.clickedY, current);
		
		

		if((current.newClick == true)&&(checkPoint.size()>1))
		{
			//System.out.println("here");
			//if((current.type != "projectile")&&(current.type != "player"))
			if(current.type != "projectile")
			{
				
					current.clickedX = checkPoint.get(1).x;
					current.clickedY = checkPoint.get(1).y;
				
				
			}
		
			
			//current.clickedX = 100;
			//current.clickedX = 100;



			current.slopeX = Math.abs(Math.round((double)(current.clickedX - current.x)/(current.clickedY - current.y)));
			current.slopeY = Math.abs(Math.round((double)(current.clickedY - current.y)/(current.clickedX - current.x)));


			if(current.slopeX > 4) {
				current.slopeX = 4;
			}
			if(current.slopeY > 4) {
				current.slopeY = 4;
			}

			for(current.moveCounter = 0; current.moveCounter< current.moveSpeed; current.moveCounter++)
			{

				//if(type == "projectile")
				//System.out.println(projectile.get(placeInList).collision);

				if(current.slopeX > current.slopeY)
				{

					current.maxSlope = current.slopeX;
					for(int j = 0; j <= current.slopeX; j++)
					{
						updateX(current);


								if(current.clickedX == current.x)
								{
									break;
								}



						if(current.moveCounter == current.moveSpeed) {
							break;

						}



					}
					if(current.collision == true)
					{
						current.newClick = false;
						break;
					}
					else
						updateY(current);


				}
				else if (current.slopeX < current.slopeY){

					current.maxSlope = current.slopeY;

					for(int j = 0; j <= current.slopeY; j++) {

						updateY(current);



							if(current.clickedY == current.y) {
								break;
							}



						if(current.moveCounter == current.moveSpeed) {
							break;

						}


					}
					if(current.collision == true)
					{
						current.newClick = false;
						break;
					}
					else
						updateX(current);



				}
				else if(current.slopeX == current.slopeY)
				{

					for(int j = 0; j <= current.maxSlope; j++)
					{

						updateX(current);
						updateY(current);


						if(current.moveCounter == current.moveSpeed)
						{
							break;

						}

						if(current.collision == true)
						{
							current.newClick = false;
							break;
						}

					}

				}

				//if((type == "projectile")&&(collision == true))
					//System.out.println("x:" +x +" y:" +y +"   collision:"+collision +"  visible:"+visible +" active:" +active +" newClick:" +newClick +"\n");

			}
			//takeDamage(list.get(0), 100);
			//System.out.println(list.get(0).hp);
			if((current.clickedX == current.x)&&(current.clickedY == current.y))
			{

			//	active = false;
				if(current.type == "player")
				{
					checkPoint.remove(1);
				}
				
		
				//current.newClick = false;
				current.maxSlope = 1;
				
				if(checkPoint.size() == 1)
				{
					current.newClick = false;
					current.hasPath = false;
				}
				
				//checkPoint.replaceXY(current.x, current.y);
		
			
				//if((current.type == "player")&&(target != this))
				if(this.current.target != this.current)
				{
					//System.out.println("here");
					if(current.target.hp >0)
					{
						if((isInRange(this.current, this.current.target) == true)&&(current.hasDoneDmage == false))
						{
							takeDamage(current.target, 10);
							this.current.target = this.current;
						}
					}
					
				
				}
				
				
			}


		}
	}


		public void updateX(Entity current) {


				if(current.clickedX < current.x){

					isCollision(current.x-5, current.y, current);

					//System.out.println(collision);
					if(current.collision == false)
					{
						current.x -=5;
						current.moveCounter++;




					}
					else
					{

						current.clickedX = current.x;
						current.clickedY = current.y;

					}




				}
				else if(current.x < current.clickedX) {

					isCollision(current.x+1, current.y, current);

					if(current.collision == false)
					{
						current.x +=5;
						current.moveCounter++;



					}
					else
					{

						current.clickedX = current.x;
						current.clickedY = current.y;

					}

				}


			}

			public void updateY(Entity current) {

				if(current.clickedY < current.y) {

					isCollision(current.x, current.y-1, current);

					if(current.collision == false)
					{
						current.y -=5;
						current.moveCounter++;


					}
					else
					{

						current.clickedX = current.x;
						current.clickedY = current.y;

					}

				}
				else if(current.clickedY > current.y) {

					isCollision(current.x, current.y+1, current);

					if(current.collision == false)
					{
						current.y +=5;
						current.moveCounter++;


					}
					else
					{

						current.clickedX = current.x;
						current.clickedY = current.y;

					}

			}
		}
		


			public void isVisible() {
				if(((current.x >= game.getEntityList().get(0).x - Game.windowX/2) &&(current.x <= current.getEntityList().get(0).x + Game.windowX/2))
		        		&&
		        		((current.y >= current.getEntityList().get(0).y - Game.windowY/2) &&(current.y <= current.getEntityList().get(0).y + Game.windowY/2)))
		        	{

					current.visible = true;
		        	}
				else
					current.visible = false;

			}

			public int isCollision(int x, int y, Entity current)
			{
				int result = 0;
				if(current.type !="player")
				{
					for(int i = 1; i < current.getEntityList().size(); i++)
					{
						if((this.current.visible == true)&&(current.getEntityList().get(i).visible == true))
						{
							if((current.getEntityList().get(i) != current)||(current.type == "projectile"))
							{
								//System.out.println("here");
								if(((x + current.hitBox > current.getEntityList().get(i).x) &&(x < current.getEntityList().get(i).x + current.getEntityList().get(i).hitBox))
						        		&&
						        		((y + current.hitBox > current.getEntityList().get(i).y) &&(y < current.getEntityList().get(i).y + current.getEntityList().get(i).hitBox)))
						        {


										if(current.type == "projectile")
										{
											current.collision = true;

											//this.collider = list.get(i);
											takeDamage(current.getEntityList().get(i), current.damage);
											//System.out.println(current.type);
											//System.out.println(placeInList);
											//result = true;
											break;
										}
										if(current.type == "melee")
										{
											current.collision = true;

											//this.collider = list.get(i);
											//setHP(list.get(i));
											result = i;
											//System.out.println(current.type);
											//System.out.println(placeInList);
											break;
										}
										else
										{
											current.collision = true;
											//this.collider = list.get(i);
											//collider = list.get(placeInList);
											//whichEn = placeInList;
											//collision = true;
										}
						        }

										//
								else
								{
									if(current.type == "projectile")
									{
										//if(collision == true)
											//System.out.println(projectile.get(placeInList).collision);

										current.collision = false;
										//break;
									}
									else
									{
										//list.get(i).collision = false;
										current.collision = false;
									}
									
									//result = false;


								}
							}

						}}}
				
				return result;
					}

			public void takeDamage(Entity target, int damage) {
				target.hp = target.hp - damage;
				target.tookDamage = true;
				current.hasDoneDmage = true;
				//System.out.println("set" + damage);
			}
			
			public boolean isInRange(Entity self, Entity target)
			{
				int range = 20;
				boolean result = false;
			
					
				if((self.x < (target.x + range))&&(self.x > (target.x - range))
					||(self.y < (target.y + range))&&(self.y > (target.y - range)))
					{
						result = true;
					}
					
					return result;
			}
			
			
			
			/*--------------------------------------------------------------------------------
			 * path finding
			 */
			/*
		
	
			*/

			public int updateTempX(int originX, int originY, int targetX) 
			{

				
				//if((targetX < originX)&&( isObstacles(originX-5, originY)== false))
				if((targetX < originX))
				{

			
					
					
						originX -=5;

						isObstacles(originX, originY);
	


				}
				//else if((originX < targetX)&&( isObstacles(originX+5, originY)== false)) 
				else if((originX < targetX)) 
				{

			
						originX +=5;

						isObstacles(originX, originY);
					
				

				}
				
				return originX;


			}

			public int updateTempY(int originX, int originY, int targetY) 
			{
			
			
				//if((targetY < originY)&&( isObstacles(originX, originY-5)== false))
				if((originY > targetY))
				{

				
						originY -=5;

						isObstacles(originX, originY);
	


				}
				//else if((originY < targetY) &&( isObstacles(originX, originY+5)== false))
				else if((originY < targetY))
				{

			
					originY +=5;

					isObstacles(originX, originY);
				
				
				}
				
				
					return originY;

				}
			boolean isObs = false;
			public boolean isObstacles(int x, int y)
			{
				
				/*
					for(int i = 0; i < game.getObstacleLocation().size(); i ++)
				{
					if(x == game.getObstacleLocation().get(i).x)
					{
						if(y == game.getObstacleLocation().get(i).y)
						{
							isObs = true;
							return true;
						}
					}
				}
				*/
				//System.out.println("here");
				
				
				if((x <= 0 ) || ( y <= 0) || (game.obsMap[x + y * game.mapWidth] == 1))
				{
					System.out.println("obs detected");
					isObs = true;
					return true;
				}
				
				
				
				isObs = false;
				return false;
			}
			
			
			
			public double getSlope(int target1, int target2, int origin1, int origin2)
			{
				
				return Math.abs((double)(target1 - origin1)/(target2 - origin2));
				
			
			}
			int tempX;
			int tempY;
			boolean firstScan = false;
			public boolean isLineOfSight(int originX, int originY, int targetX, int targetY)
			{
				//Node lastNode = checkPoint.get(checkPoint.size()-1);
				
				int slopeX = (int)Math.abs(Math.round(getSlope(originX, originY, targetX, targetY)));
				int slopeY = (int)Math.abs(Math.round(getSlope(originY, originX, targetY, targetX)));
				
			
				while(( originX != targetX ) || (originY != targetY ))
				{
					
					//
					if(slopeX > slopeY)
						{
							for(int i = 0; i< slopeX; i++)
							{
								originX = updateTempX(originX, originY, targetX);
								
								if(( originX == targetX ) || (originY == targetY ))
								{
									//System.out.println("here");
									break;
								}
								
								if(isObs == true)
								{
									break;
								}
							}
							
								if(( originX == targetX ) && (originY == targetY ))
								{
									break;
								}
								originY = updateTempY(originX, originY, targetY);
								
								if(isObs == true)
								{
									break;
								}
								
						}
					
					else if(slopeY > slopeX)
					{
						for(int i = 0; i< slopeY; i++)
						{
							originY = updateTempY(originX, originY, targetY);
							
							if(( originX == targetX ) || (originY == targetY ))
							{
								break;
							}
							
							if(isObs == true)
							{
								break;
							}
						}
						
						
							if(( originX == targetX ) && (originY == targetY ))
							{
								break;
							}
							originX = updateTempX(originX, originY, targetX);
							
							if(isObs == true)
							{
								break;
							}
		
					}
					
					else
					{
						originX = updateTempX(originX, originY, targetX);
						
						if(( originX == targetX ) && (originY == targetY ))
						{
							break;
						}
						
						if(isObs == true)
						{
							break;
						}
						originY = updateTempY(originX, originY, targetY);
						
						if(( originX == targetX ) && (originY == targetY ))
						{
							break;
						}
						
						if(isObs == true)
						{
							break;
						}
					}
					
				}
			
				
					
					if(isObs == true)
					{
						//System.out.println("here");
						//tempX = originX;
						//tempY = originY;
						return false;
					}
					else
					{
						
						return true;
					}
				
			
			}
			public void pathFind()
			{
				firstScan = true;
				tempX = checkPoint.get(checkPoint.size()-1).x;
				tempY = checkPoint.get(checkPoint.size()-1).y;
				
				int shortestX = 0;
				int shortestY = 0;
				isLineOfSight(tempX, tempY, current.clickedX, current.clickedY);
				//System.out.println(isObs);
				
				boolean condition = false;
				//System.out.println("tempXY: "+tempX +" "+tempY +" current click"+current.clickedX+" "+current.clickedY);
				if((tempX != current.clickedX)||(tempY != current.clickedY))
				{
					condition = true;
				}
					
				while(condition == true)
				{
					//System.out.println("tempXY: "+tempX +" "+tempY +" current click"+current.clickedX+" "+current.clickedY);
					
					ArrayList<Node> grid = new ArrayList<Node>();
			
					establishGrid(-5, -5, grid);
					establishGrid( 0, -5, grid);
					establishGrid( 5, -5, grid);
					
					establishGrid(-5,  0, grid);
					establishGrid( 5,  0, grid);
					
					establishGrid(-5, +5, grid);
					establishGrid( 0, +5, grid);
					establishGrid( 5, +5, grid);
					/*
					try {
						shortestX = grid.get(0).x;
						shortestY = grid.get(0).y;
					}
					catch(IndexOutOfBoundsException e)
					{
						
					}
					*/
					
					if(grid.size() == 0)
					{
						System.out.println("ERROR, unknown error by passed");
						break;
					}
					shortestX = grid.get(0).x;
					shortestY = grid.get(0).y;
					int smallestIndex = 0;
					
					for(int i = 0; i < grid.size(); i++) 
					{
						//System.out.println("x is:"+grid.get(i).x +"  y is:"+grid.get(i).y +"  distance is:"+grid.get(i).distance +" current click "+current.clickedX+" "+current.clickedY);
						
						if(i + 1 == grid.size()) 
						{
							break;
						}
						
						if(grid.get(smallestIndex).distance >= grid.get(i + 1).distance)
						{
							shortestX = grid.get(i + 1).x;
							shortestY = grid.get(i + 1).y;
							smallestIndex = i + 1;
						}
					
					}
					//System.out.println("shortest distance is "+grid.get(smallestIndex).distance);
					usedGrid.add(new Node(shortestX, shortestY));
					
					int possibleX = tempX;
					int possibleY = tempY;
					//System.out.println(shortestX +" "+shortestY);
					if(isLineOfSight(checkPoint.get(checkPoint.size()-1).x, checkPoint.get(checkPoint.size()-1).y, shortestX, shortestY) == false)
					{
						//System.out.println("from "+checkPoint.get(checkPoint.size()-1).x +" "+ checkPoint.get(checkPoint.size()-1).y +" to:" + shortestX+" "+ shortestY +" LOS is false");
						//System.out.println(checkPoint.get(checkPoint.size()-1).x + " "+checkPoint.get(checkPoint.size()-1).y);
						checkPoint.add(new Node(possibleX, possibleY));
						
						
					}
				
					
					
					tempX = shortestX;
					tempY = shortestY;
				
					
					if((tempX == current.clickedX)&&(tempY == current.clickedY))
					{
						break;
					}
					
				}
				
				//checkPoint.add(new Node(tempX, tempY));
				
				checkPoint.add(new Node(current.clickedX, current.clickedY));
				
				for(int i = 0; i < checkPoint.size(); i++) 
				{
					//System.out.println("checkX:" +checkPoint.get(i).x +"  checkY" +checkPoint.get(i).y +"  clickedX" +current.clickedX+"  clickedY" +current.clickedY);
				}
				//System.out.println(checkPoint.size());
				
				
				for(int i = 0; i < checkPoint.size(); i++)
				{
					for(int a = checkPoint.size()-1; a > i+1; a--)
					{
						if(isLineOfSight(checkPoint.get(i).x, checkPoint.get(i).y, checkPoint.get(a).x, checkPoint.get(a).y) == true)
						{
							//System.out.println(i +" "+a);
							//System.out.println("checkpoint size " +checkPoint.size());

							int numRemove = a-i-1;
							for(int b = 0; b < numRemove; b++)
							{
								//System.out.println(checkPoint.size());
								checkPoint.remove(i+1);
						
							}
							//System.out.println("checkpoint size " +checkPoint.size());

							break;
						}
						
					}
					
				}
				
			}
			
			public void establishGrid(int deltaX, int deltaY, ArrayList<Node> grid)
			{
				if(isObstacles(tempX + deltaX, tempY + deltaY) == false)
				{
					if(checkPreviousGrid(tempX + deltaX, tempY + deltaY) == false)
					{
							grid.add(new Node(tempX + deltaX, tempY + deltaY, getDistance(current.clickedX, current.clickedY, tempX + deltaX, tempY + deltaY)));
							
					}

				}
		
			}
			
			public boolean checkPreviousGrid(int x, int y)
			{
				boolean result = false;
				for(int i = 0; i < usedGrid.size(); i++)
				{
					if((usedGrid.get(i).x == x)&&(usedGrid.get(i).y == y))
					{
						return true;
					}
	
				}
				//usedGrid.add(new Node(x, y));
				
				return result;
			}
			
			public double getDistance(int x1, int y1, int x2, int y2)
			{
				
				//System.out.println(Math.sqrt(Math.pow(x1 - x2, 2)+Math.pow(y1- y2, 2)));
				return Math.sqrt(Math.pow(x1 - x2, 2)+Math.pow(y1- y2, 2));
				//return ((x1 - x2)*(x1 - x2)+(y1- y2)*(y1- y2))^(1/2);
			}
		
			/*
			public static boolean isObstacle(int cx, int cy)
			{
				for(int i = 0; i < game.getObstacleLocation().size(); i ++)
				{
					if ((game.getObstacleLocation().get(i).x == cx) && (Game.obstacleLocation.get(i).y == cy))
					{
						return true;
					}
				}
				
				return false;
			}
		*/
}


