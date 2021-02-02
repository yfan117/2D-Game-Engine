package Diablo;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
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
	
	int widthMultiplier = 10; 
	int heightMultiplier = 10; 
	
	int mapWidth = 5000;
	int mapHeight = 5000;
	
	//int mapWidth = 200*50;
	//int mapHeight = 200 * 50;

	BufferedImage frameBuffer1 = new BufferedImage(resolutionX, resolutionY, BufferedImage.TYPE_INT_RGB);
	BufferedImage frameBuffer2 = new BufferedImage(resolutionX, resolutionY, BufferedImage.TYPE_INT_RGB);
	
	
    int[] fbData1 = ((DataBufferInt)frameBuffer1.getRaster().getDataBuffer()).getData();
    int[] fbData2 = ((DataBufferInt)frameBuffer2.getRaster().getDataBuffer()).getData();
    
    
    
    int[] worldBuffer = new int[mapWidth * mapHeight];
    

 
	int windowX;
	int windowY;

	int picOrder = 0;
	int picTimer = 0;
	
	private ArrayList <Entity> list;
	private ArrayList<Entity> projectile;
	private Display display;
	private Game game;

	LayerThread layerThread;
			
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

			 image = ImageIO.read(new File(repository +"bigMap3.png"));
		
			
			
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
			tempToFrame();
		
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
		/*
		for(int a = 0; a < game.getObstacles().size(); a++)
		{
			System.out.println(game.getObstacles().get(a).layerY + game.getObstacles().get(a).y);
		}
		
		try {
			Thread.sleep(500000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
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
	public void layerOrder()
	{
		
				entityInFB = new ArrayList<>();
				Entity current;

				for(int i = 0; i< game.getObstacles().size(); i++)
				{
					current = game.getObstacles().get(i);
					
					
					if(current.y > cameraY + resolutionY)
					{
						break;
					}
					
					entityInFB.add(current);
					
					
				}
				
				
				for(int i = 0; i< game.getEntityList().size(); i++)
				{
					current = game.getEntityList().get(i);
					
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
								System.out.println("here");
								entityInFB.add(a + 1, current);
								break;
							}
						
						
						
					
						
						
					}
				
				}
				
				
				
				/*
			
				for(int a = 0; a < entityInFB.size(); a++)
				{
					System.out.println(entityInFB.get(a).layerY + entityInFB.get(a).y);
				}
				
				try {
					Thread.sleep(500000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 */
			//System.out.println("stuck");
			//	Entity current;
				
		}
			
		
	
	public void tempToFrame()
	{
		
		
	
		//set camera cord to be player cord
		cameraX = list.get(0).x;
		cameraY = list.get(0).y;
		
		
		
		int fbStartX = resolutionX/2 - cameraX;
		int fbStartY = resolutionY/2 - cameraY;
		
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
		for(int y = fbStartY; y < resolutionY; y++)
		{
			for(int x = fbStartX; x < resolutionX; x++)
			{
		
				//if((cameraX + x + ((cameraY + y) * mapWidth) < worldBuffer.length))
				if(((cameraX + x) < mapWidth) && ((cameraY + y) < mapHeight))
				{
					fbData1[x + y * resolutionX] = worldBuffer[cameraX + x + ((cameraY + y) * mapWidth)];
					//System.out.println(worldBuffer[cameraX + x + ((cameraY + y) * mapWidth)]);
				}
						
				
				
			}

		}
		
		int playerX = list.get(0).x;
		int playerY = list.get(0).y;

		//get entity data
		Entity current;
		{
		
			//System.out.println("stuck");
				
				for(int i = 0; i< entityInFB.size(); i++) 
				{
					current = entityInFB.get(i);
					
					for(int y = 0; y < current.picY; y++)
					{
						for(int x = 0; x < current.picX; x++)
						{
							int colorCode = 0;
							if(current.type != "")
							{
								colorCode =  current.imageData[current.picX * current.picCounter + x + ((current.picY * current.picRank + y) * current.spriteWidth)];

							}
							else
							{
								colorCode =  current.imageData[x + y * current.spriteWidth];

							}
							
							if( colorCode != 0)
							{
								if((resolutionX/2 + x +(current.x - cameraX)) >= resolutionX)
								{
									break;
								}

								if(resolutionY/2 + y+(current.y - cameraY) >= resolutionY)
								{
									break;
								}
								
								
								if(((resolutionX/2 + x +(current.x  - cameraX)) <= 0) 
										|| 
									(resolutionY/2 + y+(current.y - cameraY) <= 0))
								{
								
								}
								else
								{
									fbData1[resolutionX/2 + x +(current.x - cameraX) + (resolutionY/2 + y+(current.y - cameraY)) * resolutionX] = colorCode;
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
	
	public void showObs()
	{
		
		for(int i = 0; i < worldBuffer.length; i++)
    	{
			int colorCode = game.obsMap[i];
			
			if(colorCode == 1)
			{
				//System.out.println(i);
				worldBuffer[i] = Color.GREEN.getRGB();
			}
				
    	}
    	
		
		
	}

    public void populateArray()
    {
    	System.out.println("here");
    	
    	int width = image.getWidth();
    	int height = image.getHeight();
    
    	for(int y2 = 0; y2 < heightMultiplier * height; y2 = y2 + height)
		{
			for(int x2 = 0; x2 < widthMultiplier *width; x2 = x2 + width)
			{
				
				for(int y = 0; y < height; y++)
				{
					for(int x = 0; x < width; x++)
					{
	
						worldBuffer[x + x2 + ((y2 + y) * mapWidth)] = image.getRGB(x,y);
						//System.out.println(image.getRGB(x,y));
						
					}
				}
		    	
				
			}
		}
    	

		
    }
    
  	public static int[] getImageData(String imageName) throws IOException
	{
  		BufferedImage image = ImageIO.read(new File(Game.root +"/resources/images/"+ imageName +".png"));
  		
    	int width = image.getWidth();
    	int height = image.getHeight();
		int[] imageData = new int[width * height];
		
		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				imageData[x + y * width] = image.getRGB(x, y);
			}
		}
		
		return imageData;
	}

	public void updateValue() {
		System.out.print("");
		renderReady = false;
		this.layerFlag = true;
		for(int i = 0; i< list.size(); i++) {
			
		if((list.get(i).x != list.get(i).preX) || (list.get(i).y != list.get(i).preY)) 
		{
			
			list.get(i).timeCounter ++;
			
			if(list.get(i).timeCounter == 2)
			{
				list.get(i).timeCounter = 0;
				list.get(i).picCounter ++;
				
				if(list.get(i).picCounter == 19) 
				{
					list.get(i).picCounter = 0;
				}
			}
		
		}
		else {
			list.get(i).picCounter = 0;
		}
		
		
			list.get(i).preX = list.get(i).x;
			list.get(i).preY = list.get(i).y;
		
		}
		//System.out.println("1");
		//showObs();
		LayerThread.go = true;
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
	
			layerDone = false;
		
				g.drawImage(frameBuffer1,
						0 - KeyboardControl.zoomRate,
						0 - KeyboardControl.zoomRate,
						Game.windowX + KeyboardControl.zoomRate*2,
						Game.windowY + KeyboardControl.zoomRate*2,
						null);
	
			//System.out.println("repainted");
				renderReady = true;
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
