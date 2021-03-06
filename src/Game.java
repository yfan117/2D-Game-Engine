package Diablo;

import java.awt.Dimension;
import java.io.IOException;
import java.net.SocketException;
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

	//GameState enum
	public enum GameState {
		MAINMENU_STATE,
		MAINGAME_STATE,
		PAUSE_STATE,
		DEAD_STATE
	}
	public static GameState gameState;

	//static int windowX = 1920;
	//static int windowY = 1080;
	
	static int windowX = 1280;
	static int windowY = 720;
	
	//static int windowX = 1920/2;
	//static int windowY = 1080;

	static int centerX = windowX /2 ;
	static int centerY = windowY /2 ;


	static int[] mapDimension = new int[2];
	//static int timer = 1000 / fps;

	static Display display;
	static Map map;

	private ArrayList<Entity> list = new ArrayList<Entity>();
	public ArrayList<Entity> getEntityList(){return list;}
	
	private ArrayList<Entity> projectileList= new ArrayList<Entity>();
	public ArrayList<Entity> getProjectileList(){return projectileList;}
	
	private MouseControl mouse;
	public Diablo.MouseControl getMouseControl(){return mouse;}
	
	private KeyboardControl keyboard = new KeyboardControl(this);
	public Diablo.KeyboardControl getKeyboardControl(){return keyboard;}
	
	private ArrayList<Entity> obstacle = new ArrayList<Entity>();
	public ArrayList<Entity> getObstacles(){return obstacle;}
	
	private ArrayList<Node> obstacleLocation = new ArrayList<Node>();
	public ArrayList<Node> getObstacleLocation(){return obstacleLocation;}
	
	int mapWidth = 5000;
	int[] obsMap = new int[mapWidth * mapWidth];
	
	static Timer dataTimer = new Timer();
	static Timer renderTimer = new Timer();
	static Timer timeTimer = new Timer();
	static Timer receiveTimer = new Timer();
	static Timer sendTimer = new Timer();
	static int gameTime = 0;
	
	Sender sender;
	Receiver receiver;
	Thread senderThread;
	 public Game() throws IOException
	 {
		 //long time = System.currentTimeMillis();
		 //System.out.println(time);

		

		 list.add(new Entity("player", "wizard", new int[]{10, 10}, 100, 80, this, 100, 0));
		 //list.add(new Entity("enemy", new int[]{500, 500}, 100, 80, this, 100, 0));
		 /*
		 list.add(new Entity("npc", "lucy", new int[]{1700, 1000}, 100, 80, this, 100, 0));
		 list.add(new Entity("npc", "lucy", new int[]{1500, 1200}, 100, 80, this, 100, 0));
	
		 
		 list.get(1).moveAngle = 220;
		 list.get(2).moveAngle = 330;
		 */
		 //list.add(new Entity("player2", new int[]{200, 200}, 100, 80, this, 100, 0));
		 //list.add(new Entity("enemy", new int[]{10000, 10000}, 100, 80, this, 100, 0));
		 
		 //obstacle.add(new Entity(this, "tavern", 500, 500));
		 //obstacle.add(new Entity(this, "house", 300, 300));
		 //list.add(new Entity("enemy", new int[]{-50, 0}, 100, 80));

		 //list.get(0).move.isLineOfSight();

		 
		 
		 try {
			 map = new Map("map1", this);
		 } catch (IOException e1) {
			 // TODO Auto-generated catch block
			 System.out.println("map file not found");
		 }
		 display = new Display(this);
		 //gameLoop();

		 //System.out.println(System.currentTimeMillis());

		 int fps = 30;
		 int refreshTime = 1000/fps;
		
		//DataThread dataUpdate = new DataThread(this);
		//DisplayThread displayUpdate = new DisplayThread(this);
		 
		 //Sending sender = new Sending(this, 10000);
		 //Receiving receiver = new Receiving(this, 20000);
		 
		 //senderThread = new Thread(sender);
		 //senderThread.start();
		 
		 //Thread thread2 = new Thread(receiver);
		 //thread2.start();
		  
		//Thread dataThread = new Thread(dataUpdate);
		
		//dataThread.start();
		dataTimer.scheduleAtFixedRate(dataUpdate, 0, 30);
		//new Thread(displayUpdate).start();
		
		//displayThread.start();
		
		//new Thread(dataUpdate).start();
		renderTimer.scheduleAtFixedRate(frameUpdate, 0, 1000/30);
		timeTimer.scheduleAtFixedRate(timeCounter, 0, 10);
		 mouse = new MouseControl(this);
		 
		 
		 receiver = new Receiver(this, 20000);
		 receiveTimer.scheduleAtFixedRate(receive, 0, 1);
		 
		 sender = new Sender(this, 10000);
		 sendTimer.scheduleAtFixedRate(send, 0, 1);
		 
		 /*
		 while(true)
		 {
			
			 display.update();
		 }
		 */
	
	 }
	
	
	 
	  private TimerTask send = new TimerTask()
	  {
		  
		public void run()
		{
			try {
				sender.sending();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	  };
	  
	  private TimerTask receive = new TimerTask()
	  {
		  
		public void run()
		{
			 try {
					receiver.receiving();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			/*
			 if(list.get(2).move.isObstacles(list.get(2).clickedX , list.get(2).clickedY) == false)
					//if((x <= 0 ) || ( y <= 0) || (game.obsMap[x + y * game.mapWidth] == 1))
					{
						list.get(2).north = false;
						list.get(2).south = false;
						list.get(2).west = false;
						list.get(2).east = false;
						list.get(2).directionCheck = true;
		
						//list.get(2).clickedX = list.get(2).list.get(0).x - (int)(Math.random()*100);
						//list.get(2).clickedY = list.get(2).list.get(0).y - (int)(Math.random()*100);
						//clickedX = list.get(0).x;
						//clickedY = list.get(0).y;
		
		
		
						list.get(2).newClick = true;
						
						//list.get(2).target = list.get(2).list.get(0);
						
						//list.get(2).target = list.get(2);
		
						//System.out.println(Math.sqrt(Math.pow(list.get(2).clickedX - 105, 2)+Math.pow(list.get(2).clickedY - 95, 2)));
						//System.out.println("here");
		
						list.get(2).clickedX =Math.round(list.get(2).clickedX/5)*5;
						list.get(2).clickedY =Math.round(list.get(2).clickedY/5)*5;
						
						list.get(2).move.nodeIndex = 1;
						list.get(2).move.checkPoint = new ArrayList<Node>();
						list.get(2).move.usedGrid   = new ArrayList<Node>();
						list.get(2).move.checkPoint.add(new Node(list.get(2).x, list.get(2).y));
						list.get(2).move.pathFind();
						list.get(2).hasPath = true;
					}
					*/
		}
	  };
	 
	  private TimerTask frameUpdate = new TimerTask()
	  {
		  
		public void run()
		{

			//System.out.println("repainting");
			display.update();
			//display.getRendererObject().repaintt();
			
		}
	  };

	public static void main(String[] args) throws IOException {
		new Game();
		
	}

	public void changeGameState(int i) {
		gameState = GameState.values()[i];
	}
	
	boolean cycleDone = true;
	
	 private TimerTask dataUpdate = new TimerTask()
	  {
		public void run()
		{
			
			gameLoop();
			
			
			
		}
	  };

	 private TimerTask timeCounter = new TimerTask()
	  {
		public void run()
		{
			gameTime++;
		}
	  };

	public void gameLoop() {

		list.get(0).move.keyBoardUpdate(list.get(0));

				for(int i = 0; i < list.size(); i++)
				{
					if(list.get(i).hasPath == true)
					{
						
						list.get(i).move.update(list.get(i));
					}
				}
				
				for(int i = 1; i < list.size(); i++)
				{
					
						//System.out.println("ai");
						list.get(i).ai.update();
			
			
				}
				//System.out.println(projectile.size());

				for(int i = 0; i < projectileList.size(); i++)
				{

					projectileList.get(i).move.update(projectileList.get(i));

					//System.out.println(projectile.get(i).collision);
				}

				//display.update();
				
				//list.get(0).takeDamage(list.get(0), 1);

				for(int i = 0; i < projectileList.size(); i++)
				{

					if((projectileList.get(i).visible == false)||(projectileList.get(i).active == false)) {
						projectileList.remove(i);
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
				
	
			
			
	}
	
	



}

class Sending implements Runnable
{
	Game game;
	Sender sender;
	public Sending(Game game, int portNumber)
	{
		this.game = game;
		sender = new Sender(game, portNumber);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//while(true)
		{
			System.out.println("stuck");
			try {
				sender.sending();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}

class Receiving implements Runnable
{
	Game game;
	Receiver receiver;
	public Receiving(Game game, int portNumber) throws SocketException
	{
		this.game = game;
		receiver = new Receiver(game, portNumber);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//while(true)
		{
			try {
				receiver.receiving();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
}





class DisplayThread implements Runnable
{
	Game game;
	Timer timer = new Timer();
	int preTime = 0;
	int fps = 25;
	int waitTime = 1000/fps;
	public DisplayThread(Game game)
	{
		this.game = game;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			
			//timer.scheduleAtFixedRate(frameUpdate, 0, waitTime);
			
			if(Game.gameTime >= preTime + waitTime)
			{
				preTime = Game.gameTime;
				//System.out.println("repainting");
				game.display.update();
			}
			//System.out.println();
		}
		
	}
	
	/*
	  private TimerTask frameUpdate = new TimerTask()
	  {
		  
		public void run()
		{

			System.out.println("repainting");
			game.display.update();
			//display.getRendererObject().repaintt();
			
		}
	  };
	  */
	
}