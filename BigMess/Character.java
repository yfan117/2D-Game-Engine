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
	 
	 boolean visible = false;
	 boolean collision = false;
	
	public Character(String name, int[] location) throws IOException {
		
		 x = location[0];
         y = location[1];
         
         preX = x;
         preY = y;
		
        if(name == "player")
         {
        	type = "player";
        	//isPlayer = true;
			//this.centerX = x + windowX/2;
			//this.centerY = y + windowY/2;
        	visible = true;
         }
        
        if(name == "enemy")
        {
        	type = "enemy";
        	
        	setVisible();
        	
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
	
	public void setVisible() {
		if(((x >= list.get(0).x - windowX/2) &&(x <= list.get(0).x + windowX/2))
        		&&
        		((y >= list.get(0).y - windowY/2) &&(y <= list.get(0).y + windowY/2)))
        	{
			
        		visible = true;
        	} 
		else
			    visible = false;
		
	}
	
	public void isCollision(int x, int y, int placeInList) 
	{
		
		for(int i = 0; i < list.size(); i++) 
		{
			if((visible == true)&&(list.get(i).visible == true))
			{
				if(i != placeInList)
				{
					if(((x > list.get(i).x ) &&(x <= list.get(i).x + 100))
			        		&&
			        		((y > list.get(i).y) &&(y <= list.get(i).y + 100)))
			        {
					
							
							
							
							if(type == "projectile")
							{
								projectile.get(placeInList).collision = true;
							}
							else
							{
								list.get(i).collision = true;
								//collision = true;
							}
						
							
			        }
					else 
					{
						if(type == "projectile")
						{
							//System.out.println(projectile.get(placeInList).collision);
							projectile.get(placeInList).collision = false;
						}
						else
						{
							//list.get(i).collision = false;
							collision = false;
						}
						
	
					}
				}
				
			}	
		}
	}
	
	
	public void update(int placeInList) 
	{
		
		
		if((type == "enemy")&& ((list.get(0).x > (x + 300))||(list.get(0).x < (x - 300))
				||(list.get(0).y > (y + 300))||(list.get(0).y < (y - 300))))
		{
			north = false;
			south = false;
			west = false;
			east = false;
			directionCheck = true;
			
			//System.out.println((int)(Math.random()*100));
			clickedX = list.get(0).x - (int)(Math.random()*200);
			clickedY = list.get(0).y - (int)(Math.random()*200);
			
			
			
			newClick = true;
			
			
		}
		
		
		setVisible();
		isCollision(clickedX, clickedY, placeInList);

		
		
		//&&(collision = false)
		
	
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
			
				//if(type == "projectile")
				//System.out.println(projectile.get(placeInList).collision);

				if(slopeX > slopeY)
				{
					
					maxSlope = slopeX;
					for(int j = 0; j <= slopeX; j++)
					{
						updateX(placeInList);
						
							
								if(clickedX == x) 
								{
									break;
								}
							
							
						
						if(moveCounter == moveSpeed) {
							break;
							
						}
						
						
					
					}
					if(collision == true)
					{
						break;
					}
					else
						updateY(placeInList);
					
				
				}
				else if (slopeX < slopeY){
					
					maxSlope = slopeY;
					
					for(int j = 0; j <= slopeY; j++) {
	
						updateY(placeInList);
						
					
							
							if(clickedY == y) {
								break;
							}
						
					
						
						if(moveCounter == moveSpeed) {
							break;
			
						}
						
						
					}
					if(collision == true)
					{
						break;
					}
					else
						updateX(placeInList);
					
					
					
				}
				else if(slopeX == slopeY) 
				{
					
					for(int j = 0; j <= maxSlope; j++) 
					{
						
						updateX(placeInList);
						updateY(placeInList);
			
						
						if(moveCounter == moveSpeed) 
						{
							break;
	
						}
						
						if(collision == true)
						{
							break;
						}
						
					}
					
				}
				
				//if((type == "enemy")&&(collision == true))	
					//System.out.println("x: " +x +" y: " +y +"   collision: "+collision +"  visible: "+visible +"\n");
			
			}
			

			if(((clickedX == x)&&(clickedY == y))||(collision == true)) {
	
				newClick = false;
				maxSlope = 1;
				/*
				isCollision(x, y, placeInList);
				if(collision == true)
				{
					list.get(placeInList).x = list.get(placeInList).x - 5;
					clickedX = x - 100;
					list.get(placeInList).y = list.get(placeInList).y - 5;
					clickedX = y - 100;
				}
				
				//x = x - 50;
				if(collision == true)
				{
					for(int a = 0; a < 1000; a = a + 100) 
					{
						for(int b = 100; b < 1000; b = b + 100) 
						{
							isCollision(x+a, y+b, placeInList);
							if(collision == false) 
							{
								clickedX = x+a;
								clickedY = y+b;
								newClick = true;
								
							}
							
							if(newClick == true)
							{
								break;
							}
						}
						
						if(newClick == true)
						{
							break;
						}
					}
				}*/
			}
				
			
		}
	}
	
	
		public void updateX(int placeInList) {
			
				
				if(clickedX < x){

					isCollision(x-1, y, placeInList);
					
					//System.out.println(collision);
					if(collision == false)
					{
						x --;
						moveCounter++;
						
					
					
						
					}
					else
					{
						if(type == "projectile") {
							projectile.get(placeInList).collision = true;
						}
						clickedX = x;
					}
						
				
				
					
				}
				else if(x < clickedX) {
					
					isCollision(x+1, y, placeInList);
					
					if(collision == false)
					{
						x ++;
						moveCounter++;
						

						
					}
					else 
					{
						if(type == "projectile") {
							projectile.get(placeInList).collision = true;
						}
						clickedX = x;
					}
					
				}
		
					
			}
			
			public void updateY(int placeInList) {
				
				if(clickedY < y) {
					isCollision(x, y-1, placeInList);
					if(collision == false)
					{
						y --;
						moveCounter++;
						
						
					}
					else
					{
						if(type == "projectile") {
							projectile.get(placeInList).collision = true;
						}
						clickedY = y;
					}
		
				}
				else if(clickedY > y) {

					isCollision(x, y+1, placeInList);

					if(collision == false)
					{
						y ++;
						moveCounter++;
						
						
					}
					else
					{
						if(type == "projectile") {
							projectile.get(placeInList).collision = true;
						}
						clickedY = y;
					}
			
			}
			}
	
	

}
