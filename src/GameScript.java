package Diablo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.imageio.ImageIO;

import Diablo.Items.HealthPotion;
import Diablo.Items.Item;

public class GameScript extends Game{

	
	public GameScript() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException
	{
		Game game = new Game();
		
		game.root= Paths.get(System.getProperty("user.dir")).getParent() + "\\Portfolio";
		
		Map.currentMap = "map3";
    	try
        {
    		game.map = new Map("map1", game);
        } catch (IOException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    	game.loadFile = new LoadGame(game); //constructs loading/saving object

        String repo = Game.root + "/resources/images/";
        File saveFile = new File(game.root + "/resources/text/savedGame.txt");

        /*
         * Creating Dialogue
         */
        Dialogue d3 = new Dialogue("Did you encounter Sean?");
        Dialogue d2 = new Dialogue("I require you to please encounter Sean.  I will reward you for your efforts", d3);
        Objective encounterSeanDialogue = new DialogueObjective(new Dialogue("Please ecounter Sean", d3),"encounter sean", game);
        Dialogue[] responses = {new Dialogue("yes", encounterSeanDialogue), new Dialogue("no")};
        Dialogue d1 = new Dialogue("Hello Traveler, I am the Tavern girl.  It is nice to meet you.  You have a trusthworthy face, will you help me for a reward?  I require assistance with a mission, would you like to hear more?", responses);

        //	 Item reward= new SeansItem();
        Item reward= new HealthPotion(game, 10);
        Dialogue EncounteredSeanDialogue = new Dialogue("Oh, the Tavern Girl sent you, here is proof you met me");
        Objective EncounteredSean = new DialogueObjective(EncounteredSeanDialogue,"encounter sean", game);
        Objective EncounterSean = new QuestObjective(game,"encounter sean", EncounteredSean,reward);
        Dialogue sean1 = new Dialogue("Hello, I am Sean", EncounterSean);
        int[] collisionBox = {50, 100};

        
        if(Map.currentMap == "map3")
        {
        	game.getEntityList().add(new Entity("player", "archer", new int[]{3300, 1500}, 100, 80, game, 100, 50));
//        	list.add(new Entity("friend", "knight", new int[]{10000, 900}, 100, 80, this, 100, 50));
//            list.add(new Entity("friend", "tavernGirl", new int[]{10000, 900}, 100, 80, this, 100, 50));
//            list.get(list.size()-1).picRank = 13;
//            list.add(new Entity("friend", "wizard", new int[]{9500, 1500}, 100, 80, this, 100, 50));
//            list.get(list.size()-1).picRank = 3;
//            
//            
//            list.get(1).addDialogue(ImageIO.read(new File(repo + "tavernGirl.png")),d1,"Lucy",collisionBox);
//            list.get(2).addDialogue(ImageIO.read(new File(repo + "playerportrait.png")),sean1,"Sean",collisionBox);
//            list.get(0).setPortrait(ImageIO.read(new File(repo + "playerportrait.png")));
           	File file = new File(game.root + "\\resources\\maps\\map3\\enemyList.txt");
//        	FileReader read = new FileReader(file);
//        	BufferedReader br = new BufferedReader(read);
        	Scanner scan = new Scanner(file);
        	
        	while(scan.hasNext())
        	{
        		game.getEntityList().add(new Entity("enemy", "wizard", new int[]{scan.nextInt()-250, scan.nextInt()-250}, 100, 80, game, 100, 50));
        		game.getEntityList().get(game.getEntityList().size()-1).enableMovement();
        		game.getEntityList().get(game.getEntityList().size()-1).picRank = scan.nextInt();
        	
        	}
        }
        
//        else if(Map.currentMap == "map2")
//        {
//        	list.add(new Entity("player", "archer", new int[]{3900, 2100}, 100, 80, this, 100, 50));
//        	File file = new File(root + "\\resources\\maps\\map2\\enemyList.txt");
////        	FileReader read = new FileReader(file);
////        	BufferedReader br = new BufferedReader(read);
//        	Scanner scan = new Scanner(file);
//        	
//        	while(scan.hasNext())
//        	{
//        		list.add(new Entity("enemy", "knight", new int[]{scan.nextInt()-250, scan.nextInt()-250}, 100, 80, this, 100, 50));
//        		list.get(list.size()-1).enableMovement();
//        		list.get(list.size()-1).picRank = scan.nextInt();
//        	
//        	}
//        	
//        	
//        
//        }
//        else if(Map.currentMap == "map4")
//        {
//        	list.add(new Entity("player", "archer", new int[]{3900, 2100}, 100, 80, this, 100, 50));
//        	list.add(new Entity("friend", "tavernGirl", new int[]{2090, 2205}, 100, 80, this, 100, 50));
//            list.get(list.size()-1).picRank = 14;
//            list.get(1).addDialogue(ImageIO.read(new File(repo + "tavernGirl.png")),d1,"Lucy",collisionBox);
//            //list.get(2).addDialogue(ImageIO.read(new File(repo + "playerportrait.png")),sean1,"Sean",collisionBox);
//            list.get(0).setPortrait(ImageIO.read(new File(repo + "playerportrait.png")));
////        	File file = new File(root + "\\resources\\maps\\map2\\enemyList.txt");
//////        	FileReader read = new FileReader(file);
//////        	BufferedReader br = new BufferedReader(read);
////        	Scanner scan = new Scanner(file);
////        	
////        	while(scan.hasNext())
////        	{
////        		list.add(new Entity("enemy", "knight", new int[]{scan.nextInt()-250, scan.nextInt()-250}, 100, 80, this, 100, 50));
////        		list.get(list.size()-1).picRank = scan.nextInt();
////        		list.get(list.size()-1).enableMovement();
////        	}
////        	
//        	
//        
//        }
//        else if(Map.currentMap == "map1")
//        {
//        	list.add(new Entity("player", "archer", new int[]{1840, 920}, 100, 80, this, 100, 50));
//   
//        	File file = new File(root + "\\resources\\maps\\map1\\enemyList.txt");
////        	FileReader read = new FileReader(file);
////        	BufferedReader br = new BufferedReader(read);
//        	Scanner scan = new Scanner(file);
//        	
//        	while(scan.hasNext())
//        	{
//        		list.add(new Entity("enemy", "zombie", new int[]{scan.nextInt()-250, scan.nextInt()-250}, 100, 80, this, 100, 50));
//        		list.get(list.size()-1).picRank = scan.nextInt();
//        		list.get(list.size()-1).enableMovement();
//        	}
//        	
//        	
//        
//        }
//        

        
        
        //list.add(new Entity("enemy", "knight", new int[]{3300, 1200}, 100, 80, this, 100, 50));
        //list.add(new Entity("enemy", "wall", new int[]{100, 100}, 100, 80, this, 100, 50));
       //list.add(new Entity("friendly", "lucy", new int[]{100, 100}, 100, 80, this, 100, 50));
//        list.add(new Entity("friendly", "lucy", new int[]{300, 300}, 100, 100, 80, this, 100, 0, ImageIO.read(new File(repo + "tavernGirl.png")), new Dialogue("Hello, good day", new Dialogue("Hello again")), collisionBox));
//        list.add(new Entity("friendly", "lucy", new int[]{600, 300}, 100, 100, 80, this, 100, 0, ImageIO.read(new File(repo + "player.png")), new Dialogue("Greetings"), collisionBox));
//        list.add(new Entity("friendly", "lucy", new int[]{1200, 300}, 100, 100, 80, this, 100, 0, ImageIO.read(new File(repo + "player.png")), d1, collisionBox));

        game.display = new Display(game);

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
        game.dataTimer.scheduleAtFixedRate(game.dataUpdate, 0, 30);
      
        //new Thread(displayUpdate).start();

        //displayThread.start();

        //new Thread(dataUpdate).start();
        //renderTimer.scheduleAtFixedRate(frameUpdate, 0, 1000 / 30);
        game.timeTimer.scheduleAtFixedRate(game.timeCounter, 0, 1);
        game.mouse = new MouseControl(game);

//        receiver = new Receiver(this, 20000);
//        receiveTimer.scheduleAtFixedRate(receive, 0, 1);
//
//        sender = new Sender(this, 10000);
//        sendTimer.scheduleAtFixedRate(send, 0, 1);
//		 
		 int timeSegment = 0;
		 int waitTime = 0;
		 while(true)
		 {
			 timeSegment = game.gameTime + 30;
			 game.display.update();
			 
			 waitTime = timeSegment - game.gameTime;
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
				 //System.out.println("time slice exceeded");
			 }
			 
		 }
		 
	}


}
