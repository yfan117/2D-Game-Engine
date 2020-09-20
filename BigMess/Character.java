import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Character {
	
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
	
	 boolean isPlayer = false;
	
	 int preX;
	 int preY;
	
	 int picCounter = 0;
	 int timeCounter = 0;
	
	 int picRank = 6;
	
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
	
	
	public void update(Character character) 
	{
		//System.out.println("here");
		/*
		 * && ((character.x > (x + 1500))||(character.x < (x + 1500))||
				(character.y > (y + 1500))||(character.y < (y + 1500))))
		 */
		
		
		if((isPlayer == false)&& ((character.x > (x + 300))||(character.x < (x - 300))||
				(character.y > (y + 300))||(character.y < (y - 300))))
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
	
			
			//System.out.println(clickedX +" " +clickedY);
			
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
	
	
	public void updateX() {
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
	
	public void updateY() {
		
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
