package Diablo;

import java.awt.Dimension;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;





public class Game {

	//static final public Path root = Paths.get(System.getProperty("user.dir")).getParent();
	
	//this is only works for Fan
	static final public String root = Paths.get(System.getProperty("user.dir")).getParent()+"/Portfolio";


	
	//static String repository = "backGround/";
	static int windowX = 1280;
	static int windowY = 720;

	//static int windowX = 1920;
	//static int windowY = 1080;

	static int centerX = windowX /2 ;
	static int centerY = windowY /2 ;


	static int fps = 30;



	static int[] mapDimension = new int[2];
	//static int timer = 1000 / fps;

	static Display display;
	static Map map;

	
	static ArrayList<Entity> list = new ArrayList<Entity>();
	static ArrayList<Entity> projectile= new ArrayList<Entity>();
	static MouseControl mouse = new MouseControl();
	static KeyboardControl keyboard = new KeyboardControl();
	static ArrayList<Entity> obstacle = new ArrayList<Entity>();
	static ArrayList<Node> obstacleLocation = new ArrayList<Node>();
	static Timer timer = new Timer();
	static int gameTime = 0;
	
	 static TimerTask task = new TimerTask()
	  {
 		public void run()
 		{
 			//System.out.println("here");
 			gameLoop();
 		}
	  };
	  
	 static TimerTask timeCounter = new TimerTask()
	  {
 		public void run()
 		{
 			//System.out.println("here");
 			gameTime++;
 		}
	  };


	public static void main(String[] args) throws IOException {
		//long time = System.currentTimeMillis();
		//System.out.println(time);
		
		

		try {
			map = new Map("backGround");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}



		
		list.add(new Entity("player", new int[]{0, 0}, 100, 80));
		//list.add(new Entity("enemy", new int[]{0, 0},100, 80));
		//list.add(new Entity("enemy", new int[]{-50, 0}, 100, 80));

		//list.get(0).move.isLineOfSight();
		
		display = new Display();
		
		
		//gameLoop();
		
		//System.out.println(System.currentTimeMillis());

		   
		      
		     
		    	
		    		
		    int refreshTime = 1000/fps;
		    timer.scheduleAtFixedRate(task, 0, refreshTime);
		    timer.scheduleAtFixedRate(timeCounter, 0, 100);



	}

	 
	public static void gameLoop() {


	
			Movement.keyBoardUpdate(list.get(0));
	
		
		
			//while(true)
			{
				//list.get(0).move.pathFind();
			
				
				//list.get(0).move.update(list.get(0));
				//System.out.println(list.get(0).move.checkPoint.size());
				for(int i = 0; i < list.size(); i++)
				{
				
					if(list.get(i).hasPath == true)
					{
						if(i != 0)
						{
							list.get(i).ai.update();
						}
						list.get(i).move.update(list.get(i));
					}

				}
				//System.out.println(projectile.size());

				for(int i = 0; i < projectile.size(); i++)
				{
					
					projectile.get(i).move.update(projectile.get(i));

					//System.out.println(projectile.get(i).collision);
				}



				display.update();
				
				//list.get(0).takeDamage(list.get(0), 1);

				for(int i = 0; i < projectile.size(); i++)
				{

					if((projectile.get(i).visible == false)||(projectile.get(i).active == false)) {
						projectile.remove(i);
					}
			

				}
				
			
				for(int i = 0; i < list.size(); i++)
				{

					if (list.get(i).hp <= 0)
					{
						list.get(i).visible = false;
					}
				}
			
				//System.out.println(projectile.size());
				
				if(list.get(0).hp <= 0)
				{
					//break;
				}

				/*
				try {
					Thread.sleep(timer);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				 */


			}
	}
}