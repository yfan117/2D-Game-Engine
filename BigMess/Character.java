package Diablo;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Character extends Game{

	
	 int x = 0;
	 int y = 0;

	
	 int clickedX ;
	 int clickedY ;
	 boolean newClick = false;
	 boolean directionCheck = false;
	

	
	 int moveSpeed = 0;
	 int moveCounter = 0;
	
	 boolean north;
	 boolean south;
	 boolean west;
	 boolean east;


	 int centerX;
	 int centerY;


	 double slopeX;
	 double slopeY;
	 double maxSlope = 1;
	
	
	 int windowX = 1280;
	 int windowY = 720;
	
	
	 int preX;
	 int preY;
	
	 int picCounter = 0;
	 int timeCounter = 0;
	
	 int picRank = 6;
	 
	 int rangeCounter = 0;
	 int range;
	 
	 String type;
	
	public Character(String name, int[] location) throws IOException {
		
		 this.x = location[0];
         this.y = location[1];
         
         preX = x;
         preY = y;
		
        if(name == "player")
         {
        	type = "player";
        	//isPlayer = true;
			this.centerX = x + windowX/2;
			this.centerY = y + windowY/2;
         }
        
        if(name == "enemy")
        {
        	type = "enemy";
        }
       
		FileReader reader = new FileReader(repository +name +".txt");
	
		 BufferedReader bufferedReader = new BufferedReader(reader);
		
		 moveSpeed = Integer.parseInt(bufferedReader.readLine());

	}
	
	public Character(String name, int originX, int originY, int destinationX, int destinationY) throws IOException {
		
		this.x = originX;
        this.y = originY;
        
        preX = x;
        preY = y;
		
        centerX = x;
		centerY = y;
		
		clickedX = destinationX;
		clickedY = destinationY;
        
		newClick = true;
		
        if(name == "arrow")
        {
        	type = "projectile";
        }
      
		FileReader reader = new FileReader(repository +name +".txt");
	
		 BufferedReader bufferedReader = new BufferedReader(reader);
		
		 moveSpeed = Integer.parseInt(bufferedReader.readLine());
		 
		 range = Integer.parseInt(bufferedReader.readLine());

		 
	}
	
	
	
	
	public void update(Character character) 
	{
		
		
		if((type == "enemy")&& ((character.x > (x + 300))||(character.x < (x - 300))
				||(character.y > (y + 300))||(character.y < (y - 300))))
		{
			north = false;
			south = false;
			west = false;
			east = false;
			directionCheck = true;
			centerX = x;
			centerY = y;
			
			clickedX = character.x - 100;
			clickedY = character.y - 100;
	
			
			newClick = true;
		}
		
		
		
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
						
							
								if(clickedX == centerX) 
								{
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
	
	
	public void updateX() {
	
		
		if(clickedX < centerX){
			centerX --;
			x --;
			moveCounter++;
			
			if(type == "projectile")
			{
				clickedX--;
				rangeCounter++;
			}
			
			
		}
		else if(clickedX > centerX) {
			centerX ++;
			x ++;
			moveCounter++;

			if(type == "projectile")
			{
				clickedX++;
				rangeCounter++;
			}
	
		}

			
	}
	
	public void updateY() {
		
		if(clickedY < centerY) {
			centerY --;
			y --;
			moveCounter++;
			
			if(type == "projectile")
			{
				clickedY--;
				rangeCounter++;
			}

		}
		else if(clickedY > centerY) {
			centerY ++;
			y ++;
			moveCounter++;
			
			if(type == "projectile")
			{
				clickedY++;
				rangeCounter++;
			}
		}
	
	}
	

}
