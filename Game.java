public class Game {
	

	
	static int windowX = 1280;
	static int windowY = 720;
	
	static int centerX = windowX /2 ;
	static int centerY = windowY /2 ;
	
	static int x = 0;
	static int y = 0;
	static int fps = 30;
	
	static int clickedX ;
	static int clickedY ;
	static boolean newClick = false;
	static boolean directionCheck = false;
	
	static double slopeX;
	static double slopeY;
	
	static int moveSpeed = 20;
	static int moveCounter = 0;
	
	static boolean north;
	static boolean south;
	static boolean west;
	static boolean east;

	
	
	static Display display = new Display();
	
	public static void main(String[] args) {
		
		
		

		
		
		
		
		gameLoop();
		
		
		
	}
	
	public static void gameLoop() {
		int timer = 1000 / fps;
		while(true)
		{
		
		try {
			Thread.sleep(timer);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(clickedX + "," + clickedY);
		update();
		display.update(x, y);
		

		}
	}
	
	static double maxSlope = 1;
	
	public static void update() 
	{
		
	
		if(newClick == true)
		{
			slopeX = Math.abs(Math.round((double)(clickedX - centerX)/(clickedY - centerY)));
			slopeY = Math.abs(Math.round((double)(clickedY - centerY)/(clickedX - centerX)));
			
			//System.out.println(slopeX +" "+slopeY +" " +maxSlope);
			if(slopeX > 3) {
				slopeX = 3;
			}
			if(slopeY > 3) {
				slopeY = 3;
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
		
		if(clickedX < centerX) {
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
	
	



