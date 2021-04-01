package Diablo;
import Diablo.Items.HealthPotion;
import Diablo.Items.Item;

import javax.imageio.ImageIO;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;


public class Game
{
	static final public String root = Paths.get(System.getProperty("user.dir")).getParent() + "\\Portfolio";

    public DialogueUI dialogueObj = null; //no dialogue instances yet
    boolean saved = false;
    boolean dialogue = false;
    boolean continueDialogue = false;
    boolean hovering = false;
    boolean responsing = false;
    private LoadGame loadFile;
    private String loadName;

    public void setLoadName(String s){loadName = s;}
    public String getLoadName(){return loadName;}

    public LoadGame getLoadFile() {return loadFile;}

    public void createDialogue(Entity entity) { dialogueObj = new DialogueUI(entity); }

    public String getDialogue()
    {
        return dialogueObj.getDialogue();
    }
    static int windowX = 1280;
    static int windowY = 720;
    
    //static int windowX = 1920;
    //static int windowY = 1080;

    static int centerX = windowX / 2;
    static int centerY = windowY / 2;

    static int[] mapDimension = new int[2];

    static Display display;
    static Map map;

    public static ArrayList<Entity> list = new ArrayList<Entity>();

    public ArrayList<Entity> getEntityList() {return list;}

    private ArrayList<Entity> projectileList = new ArrayList<Entity>();

    public ArrayList<Entity> getProjectileList() { return projectileList; }

    private MouseControl mouse;

    public Diablo.MouseControl getMouseControl() { return mouse; }

    private KeyboardControl keyboard = new KeyboardControl(this);

    public Diablo.KeyboardControl getKeyboardControl() { return keyboard; }

    static ArrayList<Entity> obstacle = new ArrayList<Entity>();

    public ArrayList<Entity> getObstacles() { return obstacle; }

    private ArrayList<Node> obstacleLocation = new ArrayList<Node>();

    public ArrayList<Node> getObstacleLocation() { return obstacleLocation; }

    static ArrayList<Animation> models = new ArrayList<Animation>();
    
    static ArrayList<Animation> projectileAnimation = new ArrayList<Animation>();
    
    public static List<Animation> uniqueTiles = new ArrayList<Animation>();
    
    static int numTileX = 40;
    static int numTileY = 40;
    
    static int mapWidth = 500*numTileX;
    
    //boolean[] obsMap = new boolean[1000 * numTileX * 500 * numTileY];
    boolean[] obsMap = new boolean[500 * numTileX * 250 * numTileY];

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
    	try
        {
            map = new Map("map1", this);
        } catch (IOException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        loadFile = new LoadGame(this); //constructs loading/saving object

        String repo = Game.root + "/resources/images/";
        File saveFile = new File(root + "/resources/text/savedGame.txt");

        /*
         * Creating Dialogue
         */
        Dialogue d3 = new Dialogue("Did you encounter Sean?");
        Dialogue d2 = new Dialogue("I require you to please encounter Sean.  I will reward you for your efforts", d3);
        Objective encounterSeanDialogue = new DialogueObjective(new Dialogue("Please ecounter Sean", d3),"encounter sean", this);
        Dialogue[] responses = {new Dialogue("yes", encounterSeanDialogue), new Dialogue("no")};
        Dialogue d1 = new Dialogue("Hello Traveler, I am the Tavern girl.  It is nice to meet you.  You have a trusthworthy face, will you help me for a reward?  I require assistance with a mission, would you like to hear more?", responses);

        //	 Item reward= new SeansItem();
        Item reward= new HealthPotion(this, 10);
        Dialogue EncounteredSeanDialogue = new Dialogue("Oh, the Tavern Girl sent you, here is proof you met me");
        Objective EncounteredSean = new DialogueObjective(EncounteredSeanDialogue,"encounter sean", this);
        Objective EncounterSean = new QuestObjective(this,"encounter sean", EncounteredSean,reward);
        Dialogue sean1 = new Dialogue("Hello, I am Sean", EncounterSean);
        int[] collisionBox = {50, 100};

        list.add(new Entity("player", "archer", new int[]{3300, 1500}, 100, 80, this, 100, 50));
        list.add(new Entity("friend", "tavernGirl", new int[]{10000, 900}, 100, 80, this, 100, 50));
        list.get(list.size()-1).picRank = 13;
        list.add(new Entity("friend", "wizard", new int[]{9500, 1500}, 100, 80, this, 100, 50));
        list.get(list.size()-1).picRank = 3;
        
        list.get(1).addDialogue(ImageIO.read(new File(repo + "tavernGirl.png")),d1,"Lucy",collisionBox);
        list.get(2).addDialogue(ImageIO.read(new File(repo + "playerportrait.png")),sean1,"Sean",collisionBox);
        list.get(0).setPortrait(ImageIO.read(new File(repo + "playerportrait.png")));
        
        
        //list.add(new Entity("enemy", "knight", new int[]{3300, 1200}, 100, 80, this, 100, 50));
        //list.add(new Entity("enemy", "wall", new int[]{100, 100}, 100, 80, this, 100, 50));
       //list.add(new Entity("friendly", "lucy", new int[]{100, 100}, 100, 80, this, 100, 50));
//        list.add(new Entity("friendly", "lucy", new int[]{300, 300}, 100, 100, 80, this, 100, 0, ImageIO.read(new File(repo + "tavernGirl.png")), new Dialogue("Hello, good day", new Dialogue("Hello again")), collisionBox));
//        list.add(new Entity("friendly", "lucy", new int[]{600, 300}, 100, 100, 80, this, 100, 0, ImageIO.read(new File(repo + "player.png")), new Dialogue("Greetings"), collisionBox));
//        list.add(new Entity("friendly", "lucy", new int[]{1200, 300}, 100, 100, 80, this, 100, 0, ImageIO.read(new File(repo + "player.png")), d1, collisionBox));

        display = new Display(this);

        int fps = 30;
        int refreshTime = 1000 / fps;

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
        //renderTimer.scheduleAtFixedRate(frameUpdate, 0, 1000 / 30);
        timeTimer.scheduleAtFixedRate(timeCounter, 0, 1);
        mouse = new MouseControl(this);

        receiver = new Receiver(this, 20000);
        receiveTimer.scheduleAtFixedRate(receive, 0, 1);

        sender = new Sender(this, 10000);
        sendTimer.scheduleAtFixedRate(send, 0, 1);
		 
		 int timeSegment = 0;
		 int waitTime = 0;
		 while(true)
		 {
			 timeSegment = gameTime + 30;
			 display.update();
			 
			 waitTime = timeSegment - gameTime;
			 //System.out.println(waitTime);
			 
			 if(waitTime > 0)
			 {
				 try {
						Thread.sleep(waitTime);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 }
			 else
			 {
				 System.out.println("time slice exceeded");
			 }
			 
		 }
		 
    
    }


    private TimerTask send = new TimerTask()
    {

        public void run()
        {
            try
            {
                sender.sending();
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    };

    private TimerTask receive = new TimerTask()
    {

        public void run()
        {
            try
            {
                receiver.receiving();
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

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

    public static void main(String[] args) throws IOException { new Game(); }

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

    public void gameLoop()
    {
    	//System.out.println("current x is: " +list.get(0).x +"  current y is: " +list.get(0).y);
        //list.get(0).move.keyBoardUpdate(list.get(0));

    	/*
    	 Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
         for ( Thread t : threadSet){
             System.out.println("Thread :"+t+":"+"state:"+t.getState());
         }
         System.out.println();
         */
         
        for (int i = 0; i < list.size(); i++)
        {
            if ((list.get(i).hasPath == true) && (list.get(i).move != null))
            {

                list.get(i).move.update(list.get(i));
            }
        }

        for (int i = 1; i < list.size(); i++)
        {

        	//if((list.get(i).move != null)&&(list.get(i).active == true))
        	if((list.get(i).move != null))
        	{
        		list.get(i).ai.update();
        	}
 
        }
        //System.out.println(projectileList.size());

        for (int i = 0; i < projectileList.size(); i++)
        {
        	if(projectileList.get(i).active == true)
        		projectileList.get(i).move.update(projectileList.get(i));

            //System.out.println(projectile.get(i).collision);
        }

        //display.update();

        //list.get(0).takeDamage(list.get(0), 1);

        for (int i = 0; i < projectileList.size(); i++)
        {

            //if ((projectileList.get(i).visible == false) || (projectileList.get(i).active == false))
        	//if ((projectileList.get(i).collision == true)||(Movement.isVisible(projectileList.get(i)) == false))
        	if (projectileList.get(i).collision == true)
            {
                projectileList.remove(i);
            }
        }

        for (int i = 0; i < list.size(); i++)
        {

            if (list.get(i).hp <= 0)
            {
                list.get(i).visible = false;
                System.out.println("died");
            }
        }

        //System.out.println(projectile.size());

        if (list.get(0).hp <= 0)
        {
            //break;
        }

      

    }

    public void populateEntityList()
    {
        try
        {
            int[] loadData = loadFile.loadGame(loadName); //load the game file
            list.get(0).x = loadData[0];
            list.get(0).y = loadData[1];
            list.get(0).hp = loadData[2];

            //new stuff
            //temporary for testing
            list.get(0).inventory.setInventoryItem(0, new Diablo.Items.ManaPotion(this, 10));
            list.get(0).inventory.setInventoryItem(1, new Diablo.Items.ManaPotion(this, 1));
            list.get(0).inventory.setInventoryItem(2, new Diablo.Items.ManaPotion(this, 1));
            list.get(0).inventory.setInventoryItem(3, new Diablo.Items.SpeedPotion(this));
            list.get(0).inventory.setBackpackItem(0, new Diablo.Items.ManaPotion(this, 1));
            list.get(0).inventory.setBackpackItem(1, new Diablo.Items.ManaPotion(this, 1));
            list.get(0).inventory.setBackpackItem(2, new Diablo.Items.ManaPotion(this, 1));
            list.get(0).inventory.setBackpackItem(3, new Diablo.Items.ManaPotion(this, 1));
            list.get(0).inventory.setBackpackItem(4, new Diablo.Items.ManaPotion(this, 1));
            list.get(0).inventory.setBackpackItem(5, new Diablo.Items.ManaPotion(this, 1));
            list.get(0).inventory.setBackpackItem(6, new Diablo.Items.ManaPotion(this, 1));
            list.get(0).inventory.setBackpackItem(7, new Diablo.Items.ManaPotion(this, 1));
            list.get(0).inventory.setBackpackItem(8, new Diablo.Items.ManaPotion(this, 1));
            list.get(0).inventory.setBackpackItem(9, new Diablo.Items.ManaPotion(this, 1));
            list.get(0).inventory.setBackpackItem(10, new Diablo.Items.ManaPotion(this, 1));
            list.get(0).inventory.setBackpackItem(11, new Diablo.Items.ManaPotion(this, 1));
        }catch(Exception ex){ex.printStackTrace();}
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
    public void run()
    {
        // TODO Auto-generated method stub
        //while(true)
        {
            System.out.println("stuck");
            try
            {
                sender.sending();
            } catch (IOException e)
            {
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
    public void run()
    {
        // TODO Auto-generated method stub
        //while(true)
        {
            try
            {
                receiver.receiving();
            } catch (IOException e)
            {
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
    int waitTime = 1000 / fps;

    public DisplayThread(Game game)
    {
        this.game = game;
    }

    @Override
    public void run()
    {
        // TODO Auto-generated method stub
        while (true)
        {

            //timer.scheduleAtFixedRate(frameUpdate, 0, waitTime);

            if (Game.gameTime >= preTime + waitTime)
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