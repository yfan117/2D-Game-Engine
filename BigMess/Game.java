package Diablo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
	

	
	static int windowX = 1280;
	static int windowY = 720;
	
	static int centerX = windowX /2 ;
	static int centerY = windowY /2 ;
	

	static int fps = 30;
	

	
	static int[] mapDimension = new int[2];
	static int timer = 1000 / fps;
	
	//static Character genericChar;
	
	static Display display = new Display();
	static Map map;
	static ArrayList<Character> list = new ArrayList<Character>();
	
	
	
	
	public static void main(String[] args) throws IOException {
		
	
		
		try {
			map = new Map("backGround");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		
		
		list.add(new Character("player", map.getLocation()));
		list.add(new Character("enemy", new int[]{100, 100}));
		list.add(new Character("enemy", new int[]{200, 100}));
		
		//list.add(new Character("enemy", new int[]{200, 200}));
		
		//System.out.println(list.get(0).x);
		//System.out.println(list.get(1).x);
		
		gameLoop();
		
		
		
		
		
	}
	
	public static void gameLoop() {
		
		
			while(true)
			{
				for(int i = 0; i < list.size(); i++) {
					//genericChar = list.get(i);
					
					list.get(i).update(list.get(0));
					
					
				
		
				}
				try {
					Thread.sleep(timer);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				display.update(list);
		}
	}
	
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
	
	
	
	
	

	
	
		
	

	
		
	}