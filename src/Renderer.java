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
	int[] entityBuffer; 
	
	Image arrowIMG;
	Image bowIMG;
	Image temp;
	Image explosion;
	Image damageEntity;
	Image darkness;
	Image weapon;
	Image death;
	Image fire;
	
	int resolutionX = 1920;
	int resolutionY = 1080;
	
	int mapWidth = 6900;
	int mapHeight = 6700;
	
	BufferedImage frameBuffer = new BufferedImage(resolutionX, resolutionY, BufferedImage.TYPE_INT_ARGB);
    int[] fbData = ((DataBufferInt)frameBuffer.getRaster().getDataBuffer()).getData();
    int[] worldBuffer = new int[690 * 670 * 100];
    

	
	Icon test;

	int windowX;
	int windowY;

	int picOrder = 0;
	int picTimer = 0;
	
	private ArrayList <Entity> list;
	private ArrayList<Entity> projectile;
	private Display display;
	private Game game;
	
	
	BufferedImage image ;

	public Renderer(String repository, int windowX, int windowY, ArrayList<Entity> list, ArrayList<Entity> projectile, Display display) {
		
		this.list = list;
		this.projectile = projectile;

		this.windowX = windowX;
		this.windowY = windowY;
		
		
		try {
			
			 image = ImageIO.read(new File(repository +"bigMap2.png"));
			//backGroundBuffer = getImageArray(image);
			
			//entityBuffer = ((DataBufferInt)ImageIO.read(new File(repository +"character.png")).getRaster().getDataBuffer()).getData();
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		populateWorld();
		tempToFrame();

		this.display = display;
		this.game = display.getGame();
	}

	public void tempToFrame()
	{

		int playerX = list.get(0).x;
		int playerY = list.get(0).y;
		
		
		int temp = resolutionX/2 - playerX;
		int temp2 = resolutionY/2 - playerY;
		
		if(temp < 0)
		{
			temp = 0;

		}
		
		if(temp2 < 0)
		{
			temp2 = 0;

		}
		
		if((temp2 != 0) || (temp != 0))
		{
			resetFrame();
		}

		for(int y = temp2; y < resolutionY; y++)
		{
			for(int x = temp; x < resolutionX; x++)
			{
		
				fbData[x + y * resolutionX] = worldBuffer[playerX + x + ((playerY + y) * mapWidth)];
				
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

    public void populateWorld()
    {
    	
    	int width = image.getWidth();
    	int height = image.getHeight();
    
    	for(int y2 = 0; y2 < 10* height; y2 = y2 + height)
		{
			for(int x2 = 0; x2 < 10 *width; x2 = x2 + width)
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
    	
    
    
		
    }

	public void updateValue() {
		
		for(int i = 0; i< list.size(); i++) {
			
		if((list.get(i).x != list.get(i).preX) || (list.get(i).y != list.get(i).preY)) 
		{
			
			list.get(i).timeCounter ++;
			
			if(list.get(i).timeCounter == 5)
			{
				list.get(i).timeCounter = 0;
				list.get(i).picCounter ++;
				
				if(list.get(i).picCounter == 4) 
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
		repaint();
	}
	
	//static int zoom = 0;
	
	protected void paintComponent(Graphics g) {

			super.paintComponent(g);
			//KeyboardControl.zoomRate++;
			g.drawImage(frameBuffer,
						0 - KeyboardControl.zoomRate,
						0 - KeyboardControl.zoomRate,
						Game.windowX + KeyboardControl.zoomRate,
						Game.windowY + KeyboardControl.zoomRate,
						null);
	
			
		}
	
}
