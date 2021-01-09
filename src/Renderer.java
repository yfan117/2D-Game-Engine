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
	BufferedImage character;
	BufferedImage tavern;
	
	int widthMultiplier = 10; 
	int heightMultiplier = 10; 
	
	int mapWidth = 5000;
	int mapHeight = 5000;
	
	//int mapWidth = 200*50;
	//int mapHeight = 200 * 50;

	BufferedImage frameBuffer = new BufferedImage(resolutionX, resolutionY, BufferedImage.TYPE_INT_RGB);
	
    int[] fbData = ((DataBufferInt)frameBuffer.getRaster().getDataBuffer()).getData();
    int[] worldBuffer = new int[mapWidth * mapHeight];
    
  
    //int[] characerBuffer = new int[3608 * 1056];
    int[] characerBuffer;
    int[] tavernBuffer;
 
	int windowX;
	int windowY;

	int picOrder = 0;
	int picTimer = 0;
	
	private ArrayList <Entity> list;
	private ArrayList<Entity> projectile;
	private Display display;
	private Game game;
	
	

	public Renderer(String repository, int windowX, int windowY, ArrayList<Entity> list, ArrayList<Entity> projectile, Display display) {
		
		this.list = list;
		this.projectile = projectile;

		this.windowX = windowX;
		this.windowY = windowY;
		
		//this.setLocation(0 + (Game.windowX - 1280)/2, 0);
		//this.setSize(1280, 720);
		
		try {
			System.out.println(repository);

			 image = ImageIO.read(new File(repository +"bigMap3.png"));
			 //image = ImageIO.read(new File(repository +"dirt.png"));
			 character = ImageIO.read(new File(repository + "sprite.png"));
			 tavern = ImageIO.read(new File(repository + "tavern.png"));
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fbData = ((DataBufferInt)frameBuffer.getRaster().getDataBuffer()).getData();
		worldBuffer = new int[mapWidth * mapHeight];
		characerBuffer = new int[character.getWidth() * character.getHeight()];
		tavernBuffer = new int[1145 * 1000];
		    
		populateArray();
		//tempToFrame();

		cameraX = list.get(0).x;
		cameraY = list.get(0).y;
		
		this.display = display;
		this.game = display.getGame();
	}

	static int cameraX;
	static int cameraY;

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
			resetFrame();
		}

		//get map data
		for(int y = fbStartY; y < resolutionY; y++)
		{
			for(int x = fbStartX; x < resolutionX; x++)
			{
		
				//if((cameraX + x + ((cameraY + y) * mapWidth) < worldBuffer.length))
				if(((cameraX + x) < mapWidth) && ((cameraY + y) < mapHeight))
				{
					fbData[x + y * resolutionX] = worldBuffer[cameraX + x + ((cameraY + y) * mapWidth)];
				}
						
				
				
			}
		}
		
		int playerX = list.get(0).x;
		int playerY = list.get(0).y;

		//get entity data
		Entity current;
		int perPicX = 200;
		int perPicY = 200;
		for(int i = 0; i< game.getEntityList().size(); i++) 
		{
			current = game.getEntityList().get(i);
			
			for(int y = 0; y < perPicY; y++)
			{
				for(int x = 0; x < perPicX; x++)
				{
					
					int colorCode =  characerBuffer[perPicX * current.picCounter + x + ((y + perPicY * current.picRank) * character.getWidth())];
					
					if( colorCode != -16777216)
					{
						if((resolutionX/2 + x +(current.x - perPicX/2 - cameraX)) >= resolutionX)
						{
							break;
						}

						if(resolutionY/2 + y+(current.y - perPicY/2 - cameraY) >= resolutionY)
						{
							break;
						}
						
						
						if(((resolutionX/2 + x +(current.x - perPicX/2 - cameraX)) <= 0) 
								|| 
							(resolutionY/2 + y+(current.y - perPicY/2 - cameraY) <= 0))
						{
						
						}
						else
						{
							fbData[resolutionX/2 + x +(current.x - perPicX/2 - cameraX) + (resolutionY/2 + y+(current.y - perPicY/2 - cameraY)) * resolutionX] = colorCode;
						}
						
					
	
					}
			
				
					
				}
			}
		}
		
		for(int i = 0; i< game.getObstacles().size(); i++) 
		{
			current = game.getObstacles().get(i);
			//System.out.println(i);
			int width = tavern.getWidth();
			int height = tavern.getHeight();
			
			for(int y = 0; y < height; y++)
			{
				for(int x = 0; x < width; x++)
				{
				
					int colorCode =  tavernBuffer[x + y * tavern.getWidth()];
					//System.out.println(colorCode);
					if(( colorCode != -15658735) && ( colorCode != -16777216))
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
							fbData[resolutionX/2 + x +(current.x - cameraX) + (resolutionY/2 + y+(current.y - cameraY)) * resolutionX] = colorCode;
						}
						
					
	
					}
			
				
					
				}
			}
		}
		
	
	}

	public void resetFrame()
	{
		for(int y = 0; y < resolutionY; y++)
		{
			for(int x = 0; x < resolutionX; x++)
			{
		
				fbData[x + y * resolutionX] = 0;
				
			}
		}
	}
	
	public void showObs()
	{
		
		for(int i = 500 + 500 *5000; i < worldBuffer.length; i++)
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
						
					}
				}
		    	
				
			}
		}
    	
    	
    	//System.out.println(character.getRGB(0,0));
    	for(int y = 0; y < character.getHeight(); y++)
		{
			for(int x = 0; x < character.getWidth(); x++)
			{
					characerBuffer[x + y * character.getWidth()] = character.getRGB(x,y);
	
				
			}
		}
    	
    	for(int y = 0; y < tavern.getHeight(); y++)
		{
			for(int x = 0; x < tavern.getWidth(); x++)
			{
				tavernBuffer[x + y * tavern.getWidth()] = tavern.getRGB(x,y);
	
				
			}
		}
    	
    
    
		
    }

	public void updateValue() {
		
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

		tempToFrame();
		//showObs();
		repaint();
	}
	
	//static int zoom = 0;
	
	protected void paintComponent(Graphics g) {

			super.paintComponent(g);
			//KeyboardControl.zoomRate++;
			
			g.drawImage(frameBuffer,
						0 - KeyboardControl.zoomRate,
						0 - KeyboardControl.zoomRate,
						Game.windowX + KeyboardControl.zoomRate*2,
						Game.windowY + KeyboardControl.zoomRate*2,
						null);
			
		
		
			
		}
	
}
