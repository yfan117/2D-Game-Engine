package Diablo;


import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

class Renderer extends JPanel{


	int[] backGroundBuffer;

	
	int resolutionX = Game.windowX;
	int resolutionY = Game.windowY;
	
	//static int resolutionX = 1920;
	//static int resolutionY = 1080;
	

	BufferedImage image ;

	
	ArrayList<int[]> test = new ArrayList<>();

	int mapWidth = Game.numTileX;
	int mapHeight = Game.numTileY;
	
	//int mapWidth = 200*50;
	//int mapHeight = 200 * 50;



	BufferedImage frameBuffer1 = new BufferedImage(Game.windowX, Game.windowY, BufferedImage.TYPE_INT_RGB);
	
	
    int[] fbData1 = ((DataBufferInt)frameBuffer1.getRaster().getDataBuffer()).getData();
    
    
    
    Animation[] worldBuffer = new Animation[mapWidth * mapHeight];
    

 
	int windowX;
	int windowY;

	//int picOrder = 0;
	//int picTimer = 0;
	
	private ArrayList <Entity> list;
	private ArrayList<Entity> projectile;
	private Display display;
	private Game game;
	private Graphics g;

	LayerThread layerThread;
	
	ArrayList<int[]> images = new ArrayList();
	
	int[] mapTile;
			
	public Renderer(Game game, String repository, int windowX, int windowY, ArrayList<Entity> list, ArrayList<Entity> projectile, Display display) {
		
		this.game = game;
		this.list = list;
		this.projectile = projectile;

		this.windowX = windowX;
		this.windowY = windowY;
		
		//this.setLocation(0 + (Game.windowX - 1280)/2, 0);
		//this.setSize(1280, 720);
		
		try {
			System.out.println(repository);

			 image = ImageIO.read(new File(repository +"brick.png"));
			 mapTile = getImageData("brick");
			
			
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			cameraX = list.get(0).x;
			cameraY = list.get(0).y;
			
			this.display = display;
			this.game = display.getGame();
			
			populateArray();
			
			layerThread = new LayerThread(this, game);
			
			new Thread(layerThread).start();
			
			initObsOrder();
			layerOrder();
			//tempToFrame();
		
			//layerFlag = true;
	
	
		
	}
	
	
	public void initObsOrder()
	{
		
		Entity temp;
	
		int leastIndex;
		
		ArrayList<Entity> obs = game.getObstacles();

		for(int a = 0; a < obs.size(); a++)
		{
			leastIndex = a;
			for(int i = a; i < obs.size(); i++)
			{
				if(obs.get(leastIndex).layerY + obs.get(leastIndex).y > obs.get(i).layerY + obs.get(i).y)
				{
					temp = obs.get(i);
					obs.set(i, obs.get(leastIndex));
					obs.set(leastIndex, temp);
					
				}
			}
		}

	}
	static int cameraX;
	static int cameraY;
	
	ArrayList<Entity> entityInFB = new ArrayList<>(); 
	boolean layerDone = false;
	boolean layerFlag = true;


	public boolean isInFrame(Entity current)
	{
		
		//if((current.layerY + current.y > cameraY + resolutionY)&&(current.layerY + current.y < cameraY + resolutionY))
			
			
			
		return false;
	}
//	public void layerOrder()
//	{
//		
//				entityInFB = new ArrayList<>();
//				Entity current;
//
//				for(int i = 0; i< game.getObstacles().size(); i++)
//				{
//					current = game.getObstacles().get(i);
//					
//					
//					if(current.y > cameraY + resolutionY)
//					{
//						break;
//					}
//					
//					entityInFB.add(current);
//					
//					
//				}
//				
//				
//				for(int i = 0; i< game.getEntityList().size(); i++)
//				{
//					current = game.getEntityList().get(i);
//					//if(current.visible == true)
//					{
//					for(int a = 0; a< entityInFB.size(); a++)
//					{
//						//System.out.println(entityInFB.size());
//						/*
//						if(current.y > cameraY + resolutionY)
//						{
//							break;
//						}
//						*/
//						
//						if(a == 0)
//						{
//							if(current.layerY + current.y < entityInFB.get(a).layerY + entityInFB.get(a).y)
//							{
//								entityInFB.add(0, current);
//								break;
//							}
//
//							
//						}
//						
//						else if(a == entityInFB.size() - 1)
//						{
//							if(current.layerY + current.y >= entityInFB.get(a).layerY + entityInFB.get(a).y)
//							{
//								entityInFB.add(current);
//							}
//							else
//							{
//								entityInFB.add(entityInFB.size() - 1, current);
//							}
//							
//							break;
//						}
//						
//						else if((current.layerY + current.y >= entityInFB.get(a).layerY + entityInFB.get(a).y)
//								&& (current.layerY + current.y <= entityInFB.get(a + 1).layerY + entityInFB.get(a + 1).y))
//							{
//								//System.out.println("here");
//								entityInFB.add(a + 1, current);
//								break;
//							}
//						
//						
//						
//					
//						
//						
//					}
//				
//				}
//				}
//				
//		
//		}
			
	static int frameWidth = 0;
	static int frameHeight = 0;
	public void layerOrder()
	{
		
		cameraControlX = list.get(0).x;
		cameraControlY = list.get(0).y;
		
		cameraX = cameraControlX - resolutionX/2;
		cameraY = cameraControlY - resolutionY/2;
	
		//set camera cord to be player cord
		/*
		cameraX = list.get(0).x - resolutionX/2;
		cameraY = list.get(0).y - resolutionY/2;
		
		*/
		if(cameraX < 0)
		{
			frameWidth = Game.windowX + cameraX;
			cameraX = 0;

		}
		else
		{
			frameWidth = Game.windowX;
		}
		
		if(cameraY < 0)
		{
			frameHeight = Game.windowY + cameraY;
			cameraY = 0;

		}
		else
		{
			frameHeight = Game.windowY;
		}
				entityInFB = new ArrayList<>();
				Entity current;

				for(int i = 0; i< game.getObstacles().size(); i++)
				{
					current = game.getObstacles().get(i);
					
					
					if(Movement.isVisible(current) == true )
					{
						entityInFB.add(current);
					}
					
					if(current.y > cameraY + resolutionY)
					{
						break;
					}
					
					
					
				}
	
				for(int i = 0; i< game.getEntityList().size(); i++)
				{
					current = game.getEntityList().get(i);
					//if(current.visible == true)
					{
					for(int a = 0; a< entityInFB.size(); a++)
					{
						//System.out.println(entityInFB.size());
						/*
						if(current.y > cameraY + resolutionY)
						{
							break;
						}
						*/
						
						if(a == 0)
						{
							if(current.layerY + current.y < entityInFB.get(a).layerY + entityInFB.get(a).y)
							{
								entityInFB.add(0, current);
								break;
							}

							
						}
						
						else if(a == entityInFB.size() - 1)
						{
							if(current.layerY + current.y >= entityInFB.get(a).layerY + entityInFB.get(a).y)
							{
								entityInFB.add(current);
							}
							else
							{
								entityInFB.add(entityInFB.size() - 1, current);
							}
							
							break;
						}
						
						else if((current.layerY + current.y >= entityInFB.get(a).layerY + entityInFB.get(a).y)
								&& (current.layerY + current.y <= entityInFB.get(a + 1).layerY + entityInFB.get(a + 1).y))
							{
								//System.out.println("here");
								entityInFB.add(a + 1, current);
								break;
							}
						
						
						
					
						
						
					}
					if(entityInFB.size() == 0)
					{
						
						entityInFB.add(current);
					}
				
				}
				}
				
		
		}
		
	static int cameraControlX;
	static int cameraControlY;
	public void tempToFrame()
	{
		cameraControlX = list.get(0).x;
		cameraControlY = list.get(0).y;
		
		cameraX = cameraControlX - resolutionX/2;
		cameraY = cameraControlY - resolutionY/2;
	
		//set camera cord to be player cord
		/*
		cameraX = list.get(0).x - resolutionX/2;
		cameraY = list.get(0).y - resolutionY/2;
		
		*/
		if(cameraX < 0)
		{
			cameraX = 0;

		}
		
		if(cameraY < 0)
		{
			cameraY = 0;

		}
		//System.out.println(cameraX +" " +cameraY);
		int fbStartX = resolutionX/2 - cameraControlX;
		int fbStartY = resolutionY/2 - cameraControlY;
		
		if(fbStartX < 0)
		{
			fbStartX = 0;

		}
		
		if(fbStartY < 0)
		{
			fbStartY = 0;

		}
		
		if((fbStartX != 0) || (fbStartY != 0)||(fbStartX <= resolutionX) || (fbStartY <= resolutionY))
		{
			resetFrame(fbData1);
		}

		//get map data	
		
		int tileX = cameraX / 1000;
		int tileY = cameraY / 500;
	
		int offsetX = 0;
		int offsetY = 0;
		
		
		for(int b = fbStartY; b <= Game.windowY; b = b + 500 + offsetY)
		{
			tileX = cameraX / 1000;
			if(tileX > mapWidth -1)
			{
				tileX = mapWidth -1;
				break;
			}
			if(tileY > mapHeight -1)
			{
				tileY = mapHeight -1;
				break;
			}
			
			offsetX = worldBuffer[tileX + tileY * mapWidth].startX - cameraX;
			offsetY = worldBuffer[tileX + tileY * mapWidth].startY - cameraY;
			
			if(offsetX > 0)
			{
				offsetX = 0;
			}
			
			if(offsetY > 0)
			{
				offsetY = 0;
			}
	
			for(int a = fbStartX; a <= Game.windowX; a = a + 1000 + offsetX) 
			{

				if(tileX > mapWidth -1)
				{
					tileX = mapWidth -1;
					break;
				}
				/*
				if(tileY > mapHeight -1)
				{
					tileY = mapHeight -1;
					break;
				}
				*/
				//System.out.println(a);
				offsetX = worldBuffer[tileX + tileY * mapWidth].startX - cameraX;
				offsetY = worldBuffer[tileX + tileY * mapWidth].startY - cameraY;
				
				if(offsetX > 0)
				{
					offsetX = 0;
				}
				
				if(offsetY > 0)
				{
					offsetY = 0;
				}
				
				
				for(int y = 0; y < (500 + offsetY); y++)
				{
					for(int x = 0; x < (1000 + offsetX); x++)
					{
						
						if((a + x  >= Game.windowX)||(b + y >= Game.windowY))
						{
							break;
						}
						fbData1[a + x + (b + y) * Game.windowX] = worldBuffer[tileX + tileY * 10].imageData[x - offsetX + (y - offsetY) * 1000];
	
					}
					
			
				}
			
				tileX++;
				
			}
			
			tileY++;

		}

		Entity current;

		
			//System.out.println("stuck");
				
				for(int i = 0; i< entityInFB.size(); i++) 
				{
			
					current = entityInFB.get(i);
					
					for(int y = 0; y < current.animationInUse.picHeight; y++)
					{
						for(int x = 0; x < current.animationInUse.picWidth; x++)
						{
							int colorCode = 0;
							if(current.type != "")
							{
								//System.out.println("here");
								//System.out.println(current.picX * current.picCounter + x + ((current.picY * current.picRank + y) * current.spriteWidth));

								//colorCode =  current.imageData[current.picX * current.picCounter + x + ((current.picY * current.picRank + y) * current.spriteWidth)];

								try {
									colorCode =  current.animationInUse.imageData[current.animationInUse.picWidth * current.picCounter + x + ((current.animationInUse.picHeight * current.picRank + y) * current.animationInUse.spriteWidth)];
								}
								catch(ArrayIndexOutOfBoundsException e)
								{
									System.out.println(current.state +"!!!!!!!!!! UNEXPLAINED ERROR !!!!!!!!!!");
									System.out.println(current.animationInUse.picWidth * current.picCounter + x + ((current.animationInUse.picHeight * current.picRank + y) * current.animationInUse.spriteWidth));
									break;
								}
								
								

							}
							else
							{
								colorCode =  current.animationInUse.imageData[x + y * current.animationInUse.spriteWidth];

							}
							
							if( colorCode != 0)
							{
								if((resolutionX/2 + x +(current.x - cameraControlX)) >= resolutionX)
								{
						
									break;
								}

								if(resolutionY/2 + y+(current.y - cameraControlY) >= resolutionY)
								{
						
									break;
								}
								
								
								if(((resolutionX/2 + x +(current.x  - cameraControlX)) <= 0) 
										|| 
									(resolutionY/2 + y+(current.y - cameraControlY) <= 0))
								{
								
								}
								else
								{
									//System.out.println("3");
									if(current.type != "player") 
									{
										//System.out.println(current.type);
										//System.out.println(current.type);
										fbData1[resolutionX/2 + x + (current.x - cameraControlX) + (resolutionY/2 + y +(current.y - cameraControlY )) * resolutionX] = colorCode;
									}
									else if(current.type == "player")
									{
										fbData1[resolutionX/2 + x - current.animationInUse.picWidth/2 + (current.x - cameraControlX) + (resolutionY/2 + y +(current.y - cameraControlY - current.animationInUse.picHeight/2)) * resolutionX] = colorCode;

									}
									else
									{
										fbData1[resolutionX/2 + x +(current.x - cameraControlX) + (resolutionY/2 + y+(current.y - cameraControlY)) * resolutionX] = colorCode;
									}
								}
							
					
						
							
							}
						}
					
					}
				}
				
				for(int i = 0; i < game.getProjectileList().size(); i++) 
				{
					current = game.getProjectileList().get(i);
					
					if(current.active == true)
					{
			
					for(int y = 0; y < current.animationInUse.picHeight; y++)
					{
						for(int x = 0; x < current.animationInUse.picWidth; x++)
						{
							int colorCode = 0;
						
								colorCode =  current.animationInUse.imageData[x + (y + current.picRank * current.animationInUse.picHeight) * current.animationInUse.spriteWidth];

								//System.out.println("here");
							
							if( colorCode != 0)
							{
								if((resolutionX/2 + x +(current.x - cameraControlX)) >= resolutionX)
								{
									//System.out.println("here1");
									break;
								}

								if(resolutionY/2 + y+(current.y - cameraControlY) >= resolutionY)
								{
									//System.out.println("here2");
									break;
								}
								
								
								if(((resolutionX/2 + x +(current.x  - cameraControlX)) <= 0) 
										|| 
									(resolutionY/2 + y+(current.y - cameraControlY) <= 0))
								{
									//System.out.println("here3");
								}
								else
								{
										
										fbData1[resolutionX/2 + x +(current.x - cameraControlX) + (resolutionY/2 + y+(current.y - cameraControlY)) * resolutionX] = colorCode;
									
								}
							
					
						
							
							}
						}
					}
					}
				}
				
		
	}

	public void resetFrame(int[] tempBuffer)
	{
		for(int y = 0; y < resolutionY; y++)
		{
			for(int x = 0; x < resolutionX; x++)
			{
		
				tempBuffer[x + y * resolutionX] = 0;
				
			}
		}
	}
	

    public void populateArray()
    {
  
    	//Animation tile = new Animation(mapTile);
    
    	//load each image into ArrayList as int[],  Each element in worldBuffer contain one Animation obj, which has reference to it's int[].
    	for(int y = 0; y < mapHeight; y++)
		{
    		for(int x = 0; x < mapWidth; x++)
    		{
    			worldBuffer[x + y *mapWidth] = new Animation(mapTile);
        		worldBuffer[x + y *mapWidth].addStartPoint(1000*x, 500*y);
    		}
    		
		}
   
    }

  	public static int[] getImageData(String imageName) throws IOException
	{
  	
  		BufferedImage image = ImageIO.read(new File(Game.root +"/resources/images/"+ imageName + ".png"));
  		
    	int width = image.getWidth();
    	int height = image.getHeight();
		int[] imageData = new int[width * height];
		int firstColor = image.getRGB(0, 0);
		System.out.println("first color is -- " +firstColor);
		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				if((firstColor != image.getRGB(x, y)) && (131586 != image.getRGB(x, y)))
				{
					/*
					if(imageName.contains("archer"))
					{
						if(image.getRGB(x, y)!=0)
						{
							System.out.println(image.getRGB(x, y));
						}
					}*/
						
					imageData[x + y * width] = image.getRGB(x, y);
				}
					//imageData[x + y * width] = image.getRGB(x, y);
			}
		}
		
		return imageData;
	}

	public void updateValue() {
		//System.out.println(" ");


		Entity current;
		for(int i = 0; i< list.size(); i++) 
		{
			
		//if((list.get(i).x != list.get(i).preX) || (list.get(i).y != list.get(i).preY)) 
			current = list.get(i);
			if(current.state == "run") 
			{
			
				if(current.animationInUse != current.run)
				{
					current.updateAnimationData(current.run);
					//System.out.println("state changed");
				}
			
				if(list.get(i).picCounter < list.get(i).animationInUse.numOfFrame) 
				{
					list.get(i).picCounter++;
				}
				else
				{
					list.get(i).picCounter = 0;
				}
				
			
			}
			else if(current.state == "idle")
			{
				if(current.animationInUse != current.idle)
				{
					current.updateAnimationData(current.idle);
					//System.out.println("state changed");
					//list.get(i).picCounter = 77;
					list.get(i).picCounter = 0;
				}
					
				
				if(list.get(i).picCounter < list.get(i).animationInUse.numOfFrame) 
				{
					list.get(i).picCounter++;
				}
				else
				{
					list.get(i).picCounter = 0;
				}
			}
			else if(current.state == "attack")
			{
				if(current.animationInUse != current.attack)
				{
					current.updateAnimationData(current.attack);
					//System.out.println("state changed");
					list.get(i).picCounter = 0;
				}
					
				
				if(list.get(i).picCounter < list.get(i).animationInUse.numOfFrame-1) 
				{
					list.get(i).picCounter++;
				}
				else
				{
					list.get(i).state = "idle";
				}
				
				if(list.get(i).picCounter == list.get(i).attackFrame)
				{
					System.out.println("shooting");
					
					for(int a = 0; a< game.getProjectileList().size(); a++)
					{
						game.getProjectileList().get(game.getProjectileList().size()-1).active = true;
//					try {
//						game.getProjectileList().add(new Entity(game, "arrow", list.get(i).destinationX, list.get(i).destinationY, 80));
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					list.get(i).destinationX = 0;
					list.get(i).destinationY = 0;
					}
				}
				
			}

		
		}
		//System.out.println("1");
		//showObs();

		tempToFrame();
		layerOrder();
		//System.out.println("2");
		repaint();
		//System.out.println("3");
	}

	boolean renderReady = true;
	
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);


		//KeyboardControl.zoomRate++;
		this.g=g;
		g.drawImage(frameBuffer1,
				0 - KeyboardControl.zoomRate,
				0 - KeyboardControl.zoomRate,
				Game.windowX + KeyboardControl.zoomRate*2,
				Game.windowY + KeyboardControl.zoomRate*2,
				null);

		/*
		 * Check if hovering
		 */
		if(game.hovering==true)
			this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		else
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

		if(game.dialogue==true) {
			game.dialogueObj.render(g);
		}

		/*
		 * Player & Inventory UI
		 */
		g.setFont(new Font("TimesRoman", Font.BOLD, 16));
		//KeyboardControl.zoomRate++;

		g.setColor(Color.RED);
		g.fillRect(25, 10, list.get(0).hp * 2, 20);
		g.setColor(Color.BLUE);
		g.fillRect(25, 40, list.get(0).mana, 20);

		//Inventory slots
		if(game.getEntityList().get(0).inventory.getInventoryOpen())
		{
			g.setColor(Color.darkGray);
			g.fillRect(((Diablo.Game.windowX / 2) - (game.getEntityList().get(0).inventory.getInventoryCols() / 2) * 50), ((Diablo.Game.windowY) - (game.getEntityList().get(0).inventory.getInventoryRows() * 50)), game.getEntityList().get(0).inventory.getInventoryCols() * 50, game.getEntityList().get(0).inventory.getInventoryRows() * 50);
			g.setColor(Color.LIGHT_GRAY);
			for (int i = 0; i < game.getEntityList().get(0).inventory.getInventoryCols(); i++)
			{
				for (int j = 0; j < game.getEntityList().get(0).inventory.getInventoryRows(); j++)
				{
					g.drawRect(((((Diablo.Game.windowX / 2) - (game.getEntityList().get(0).inventory.getInventoryCols() / 2) * 50)) + (i * 50)), (((Diablo.Game.windowY) - (game.getEntityList().get(0).inventory.getInventoryRows() * 50)) + 1) + (j * 50), 48, 48);
				}
			}

			//Inventory items
			int counter = -1;
			for (int i = 0; i < game.getEntityList().get(0).inventory.getInventoryRows(); i++)
			{
				for (int j = 0; j < game.getEntityList().get(0).inventory.getInventoryCols(); j++)
				{
					try
					{
						counter++;
						BufferedImage img = game.getEntityList().get(0).inventory.getInventoryItemImage(counter);
						g.drawImage(img, ((((Diablo.Game.windowX / 2) - (game.getEntityList().get(0).inventory.getInventoryCols() / 2) * 50) + 9) + (j * 50)), (((Diablo.Game.windowY) - (game.getEntityList().get(0).inventory.getInventoryRows() * 50)) + 9) + (i * 50), 32, 32, null);
						if(game.getEntityList().get(0).inventory.getInventoryItem(counter).isStackable())
						{
							String s = String.valueOf(game.getEntityList().get(0).inventory.getInventoryItem(counter).getNumberInStack());
							g.drawString(s, ((((Diablo.Game.windowX / 2) - (game.getEntityList().get(0).inventory.getInventoryCols() / 2) * 50) + 9) + (j * 50)), (((Diablo.Game.windowY) - (game.getEntityList().get(0).inventory.getInventoryRows() * 50)) + 9) + (i * 50) + 30);
						}
					}catch(Exception ex){}
				}
			}
		}

		//Backpack slots
		if(game.getEntityList().get(0).inventory.getBackpackOpen())
		{
			g.setColor(Color.darkGray);
			g.fillRect(((Diablo.Game.windowX) - game.getEntityList().get(0).inventory.getCols() * 50), ((Diablo.Game.windowY / 2) - ((game.getEntityList().get(0).inventory.getRows() / 2) * 50)), game.getEntityList().get(0).inventory.getCols() * 50, game.getEntityList().get(0).inventory.getRows() * 50);
			g.setColor(Color.LIGHT_GRAY);
			for (int i = 0; i < game.getEntityList().get(0).inventory.getCols(); i++)
			{
				for (int j = 0; j < game.getEntityList().get(0).inventory.getRows(); j++)
				{
					g.drawRect(((((Diablo.Game.windowX) - game.getEntityList().get(0).inventory.getCols() * 50)) + (i * 50)), (((Diablo.Game.windowY / 2) - ((game.getEntityList().get(0).inventory.getRows() / 2) * 50)) + 1) + (j * 50), 48, 48);
				}
			}

			//Backpack items
			int counter = -1;
			for (int i = 0; i < game.getEntityList().get(0).inventory.getRows(); i++)
			{
				for (int j = 0; j < game.getEntityList().get(0).inventory.getCols(); j++)
				{
					try
					{
						counter++;
						BufferedImage img = game.getEntityList().get(0).inventory.getBackpackItemImage(counter);
						g.drawImage(img, ((((Diablo.Game.windowX) - game.getEntityList().get(0).inventory.getCols() * 50) + 9) + (j * 50)), (((Diablo.Game.windowY / 2) - ((game.getEntityList().get(0).inventory.getRows() / 2) * 50)) + 9) + (i * 50), 32, 32, null);
						if(game.getEntityList().get(0).inventory.getBackpackItem(counter).isStackable())
						{
							String s = String.valueOf(game.getEntityList().get(0).inventory.getBackpackItem(counter).getNumberInStack());
							g.drawString(s, ((((Diablo.Game.windowX) - game.getEntityList().get(0).inventory.getCols() * 50) + 9) + (j * 50)), (((Diablo.Game.windowY / 2) - ((game.getEntityList().get(0).inventory.getRows() / 2) * 50)) + 9) + (i * 50) + 30);
						}
					}catch(Exception ex){}
				}
			}
		}

		///////////////////////////ADD LATER
		//Spell slots
//			g.setColor(Color.DARK_GRAY);
//			g.fillRect(((Diablo.Game.windowX/2)-74), (Diablo.Game.windowY - 87), 148, 37);
//			g.setColor(Color.blue);
//			for(int i = 0; i < 4; i++)
//			{
//				g.drawRect(((Diablo.Game.windowX/2)-74)+(i*37), Diablo.Game.windowY - 86, 35, 35);
//			}
//			g.setColor(Color.CYAN);
//			for(int i = 0; i < 4; i++)
//			{
//				g.drawRect(((Diablo.Game.windowX/2)-73)+(i*37), Diablo.Game.windowY - 85, 33, 33);
//			}
//			g.setColor(Color.blue);
//			for(int i = 0; i < 4; i++)
//			{
//				g.drawRect(((Diablo.Game.windowX/2)-72)+(i*37), Diablo.Game.windowY - 84, 31, 31);
//			}

	}
	//new function
	private static BufferedImage imageToBufferedImage(Image image) {

		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = bufferedImage.createGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();

		return bufferedImage;

	}

	public static Image makeColorTransparent(BufferedImage im, final Color color) {
		ImageFilter filter = new RGBImageFilter() {

			// the color we are looking for... Alpha bits are set to opaque
			public int markerRGB = color.getRGB() | 0xFF000000;

			public final int filterRGB(int x, int y, int rgb) {
				if ((rgb | 0xFF000000) == markerRGB) {
					// Mark the alpha bits as zero - transparent
					return 0x00FFFFFF & rgb;
				} else {
					// nothing to do
					return rgb;
				}
			}
		};

		ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
		return Toolkit.getDefaultToolkit().createImage(ip);
	}

	
}

class LayerThread implements Runnable 
{
	Renderer renderer;
	Game game;
	static boolean go = true;

	
	
	public LayerThread(Renderer renderer, Game game)
	{
		this.renderer = renderer;
		this.game = game;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
			{
			
			if(go == true)
				{
					
					//renderer.layerOrder();
				
				}	
			}
		
	}
	
}
