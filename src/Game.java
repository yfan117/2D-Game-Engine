package Diablo;

import java.awt.Dimension;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;





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
	static int timer = 1000 / fps;

	static Display display;
	static Map map;
	
	static ArrayList<Entity> list = new ArrayList<Entity>();
	static ArrayList<Entity> projectile= new ArrayList<Entity>();
	static MouseControl mouse = new MouseControl();
	static KeyboardControl keyboard = new KeyboardControl();



	public static void main(String[] args) throws IOException {
		System.out.println(root);


		try {
			map = new Map("backGround");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}




		list.add(new Entity("player", map.getLocation(), 100, 80));
		//list.add(new Entity("enemy", new int[]{0, 0},100, 80));
		//list.add(new Entity("enemy", new int[]{100, 0}, 100, 80));

		//list.get(0).move.isLineOfSight();
		
		display = new Display();
		
		
		gameLoop();





	}

	public static void gameLoop() {


			while(true)
			{
				//list.get(0).move.pathFind();
				
				//System.out.println("here");
				for(int i = 0; i < list.size(); i++)
				{
					
					{
						list.get(i).move.update(list.get(i));
					}
					
					//System.out.println(list.get(i).hp );

				}
				System.out.println(projectile.size());

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
					break;
				}

				try {
					Thread.sleep(timer);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}




			}
	}
}