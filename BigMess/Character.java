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
			//this.centerX = x + windowX/2;
			//this.centerY = y + windowY/2;
         }
        
        if(name == "enemy")
        {
        	type = "enemy";
        }
       
		FileReader reader = new FileReader(repository +name +".txt");
	
		 BufferedReader bufferedReader = new BufferedReader(reader);
		
		// moveSpeed = Integer.parseInt(bufferedReader.readLine());
		 
		 moveSpeed = 20;

	}
	
	public Character(String name, int originX, int originY, int destinationX, int destinationY) throws IOException {
		
		this.x = originX;
        this.y = originY;
        
        preX = x;
        preY = y;
		
 
		
		clickedX = destinationX ;
		clickedY = destinationY ;
        
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
	
	
	
	
	public void update() 
	{
		
		
		if((type == "enemy")&& ((list.get(0).x > (x + 300))||(list.get(0).x < (x - 300))
				||(list.get(0).y > (y + 300))||(list.get(0).y < (y - 300))))
		{
			north = false;
			south = false;
			west = false;
			east = false;
			directionCheck = true;
			
			
			clickedX = list.get(0).x - 100;
			clickedY = list.get(0).y - 100;
			
			
			
			newClick = true;
		}
		
		
		
		if(newClick == true)
		{
		
			slopeX = Math.abs(Math.round((double)(clickedX - x)/(clickedY - y)));
			slopeY = Math.abs(Math.round((double)(clickedY - y)/(clickedX - x)));
			

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
						
							
								if(clickedX == x) 
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
						
					
							
							if(clickedY == y) {
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
			

			if((clickedX == x)&&(clickedY == y)) {
	
				newClick = false;
				maxSlope = 1;
			}
				
			
		}
	}
	
		public void updateX() {
			
				
				if(clickedX < x){

					x --;
					moveCounter++;
					
				}
				else if(x < clickedX) {

					x ++;
					moveCounter++;
					
		
				}
		
					
			}
			
			public void updateY() {
				
				if(clickedY < y) {

					y --;
					moveCounter++;
		
		
				}
				else if(clickedY > y) {

					y ++;
					moveCounter++;
		
		
				}
			
			}
	
	

}
