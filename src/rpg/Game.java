package rpg;
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
	
	static double slopeX;
	static double slopeY;
	
	static int moveSpeed = 10;
	static int moveCounter = 0;
	
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
	
	public static void update() 
	{
		
	
		if(newClick == true)
		{
			for(moveCounter = 0; moveCounter< moveSpeed; moveCounter++) 
			{
				
				if(slopeX > slopeY)
				{
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
					
					for(int j = 0; j <= slopeY+1; j++) 
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
	
	



