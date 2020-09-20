package Diablo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Character {
	
	static int x = 0;
	static int y = 0;
	static int fps = 30;
	
	static int clickedX ;
	static int clickedY ;
	static boolean newClick = false;
	static boolean directionCheck = false;
	

	
	static int moveSpeed = 0;
	static int moveCounter = 0;
	
	static boolean north;
	static boolean south;
	static boolean west;
	static boolean east;


	static int centerX;
	static int centerY;
	//static int[] respawnLcation;

	static double slopeX;
	static double slopeY;
	static double maxSlope = 1;
	
	
	static int windowX = 1280;
	static int windowY = 720;
	
	boolean isPlayer = false;
	
	static int preX;
	static int preY;
	
	static int picCounter = 0;
	static int timeCounter = 0;
	
	static int picRank = 6;
	
	public Character(String name, int[] location) throws IOException {
		
		 this.x = location[0];
         this.y = location[1];
         
         preX = x;
         preY = y;
		
        if(name == "player")
         {
        	isPlayer = true;
			this.centerX = x + windowX/2;
			this.centerY = y + windowY/2;
         }
		
		FileReader reader = new FileReader("backGround/"+name +".txt");
	
		 BufferedReader bufferedReader = new BufferedReader(reader);
		
		 moveSpeed = Integer.parseInt(bufferedReader.readLine());
	
        
         

		
	}
	static int a;
	public Character(int a) {
		this.a = a;
	}
	
	
	
	
	
	public void update() 
	{
		
	
		if(newClick == true)
		{
			
			slopeX = Math.abs(Math.round((double)(clickedX - centerX)/(clickedY - centerY)));
			slopeY = Math.abs(Math.round((double)(clickedY - centerY)/(clickedX - centerX)));
			

			if(slopeX > 4) {
				slopeX = 4;
			}
			if(slopeY > 4) {
				slopeY = 4;
			}
			
			for(moveCounter = 0; moveCounter< moveSpeed; moveCounter++) 
			{
				
				if(slopeX > slopeY)
				{
					
					maxSlope = slopeX;
					for(int j = 0; j <= slopeX; j++)
					{
						updateX();
						
						
						if(clickedX == centerX) {
							break;
						}
						
						if(moveCounter == moveSpeed) {
							break;
							
						}
					
					}
					
					updateY();
					
				}
				else if (slopeX < slopeY){
					
					maxSlope = slopeY;
					
					for(int j = 0; j <= slopeY; j++) {
	
						updateY();
						
						if(clickedY == centerY) {
							break;
						}
						
						if(moveCounter == moveSpeed) {
							break;
							
						}
					}
	
					updateX();
					
					
				}
				else if(slopeX == slopeY) 
				{
					
					for(int j = 0; j <= maxSlope; j++) 
					{
						
						updateX();
						updateY();
			
						
						if(moveCounter == moveSpeed) {
							break;
							
						}
						
					}
					
				}
	
			
			}

			if((clickedX == centerX)&&(clickedY == centerY)) {
	
				newClick = false;
				maxSlope = 1;
				
				
			}
		}
	}
	
	public static void updateX() {
		//System.out.println(clickedX +" " +centerX +" " +x);
		if(clickedX < centerX){
			centerX --;
			x --;
			moveCounter++;
			
			
			
		}
		else if(clickedX > centerX) {
			centerX ++;
			x ++;
			moveCounter++;

	
		}

			
	}
	
	public static void updateY() {
		
		if(clickedY < centerY) {
			centerY --;
			y --;
			moveCounter++;

		}
		else if(clickedY > centerY) {
			centerY ++;
			y ++;
			moveCounter++;

		}
	
	}
	

}
