package Diablo;
import java.awt.Dimension;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;





public class Game {
	//static final public Path root = Paths.get(System.getProperty("user.dir")).getParent();



	static String repository = "backGround/";
	//static int windowX = 1280;
	//static int windowY = 720;
	
	static int windowX = 1920;
	static int windowY = 1080;
	
	static int centerX = windowX /2 ;
	static int centerY = windowY /2 ;
	

	static int fps = 30;
	

	
	static int[] mapDimension = new int[2];
	static int timer = 1000 / fps;
	
	static Display display;
	static Map map;
	static ArrayList<Character> list = new ArrayList<Character>();
	static ArrayList<Character> projectile= new ArrayList<Character>();
	
	
	
	public static void main(String[] args) throws IOException {
		

		
		try {
			map = new Map("backGround");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		
		
		list.add(new Character("player", map.getLocation()));
		//list.add(new Character("enemy", new int[]{100, 100}));
		//list.add(new Character("enemy", new int[]{200, 100}));
		

		display = new Display();
		gameLoop();
		
		
		
		
		
	}
	
	public static void gameLoop() {
		
		
			while(true)
			{
				for(int i = 0; i < list.size(); i++) 
				{
					//genericChar = list.get(i);
					
					list.get(i).update();
	
		
				}
				
			
				for(int i = 0; i < projectile.size(); i++) 
				{
					//genericChar = list.get(i);
					projectile.get(i).update();
					/*
					if((projectile.get(i).type == "projectile") && (projectile.get(i).rangeCounter >= projectile.get(i).range))
					{
						projectile.remove(i);
					}
					*/
					if((projectile.get(i).clickedX == projectile.get(i).x)&&(projectile.get(i).clickedY == projectile.get(i).y))
						projectile.remove(i);
					
		
				}
				
				
				
				display.update();
				//System.out.println(projectile.size());
				
				try {
					Thread.sleep(timer);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
			}
	}
	/*
	public boolean outBound(int eX, int eY, Character genericChar) {
		  boolean answer = false;
		 
		  int xAxis = windowX / 2;
		  int yAxis = windowY / 2;
		  
		  int x = genericChar.x;
		  int y = genericChar.y;
		  
		  if(eX < xAxis)
		  {
		   if((x - (xAxis - eX)) <= 0)
		    answer = true;
		  }
		  else if(eX > xAxis)
		  {
		   if((x + (eX - xAxis)) >= mapDimension[0])
		    answer = true;
		  }
		  
		  if(eY < yAxis)
		  {
		   if((y - (yAxis - eY)) <= 0)
		    answer = true;
		  }
		  else if(eY > yAxis)
		  {
		   if((y + (eY - yAxis)) >= mapDimension[1])
		    answer = true;
		  }
		  
		  return answer;
		 }
	
	*/
	
	}
