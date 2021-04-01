package Diablo;



import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Diablo.Items.*;

public class Entity{

	 int x = 0;
	 int y = 0;

	 int clickedX ;//destination
	 int clickedY ;//destination
	 boolean newClick = false;
	 boolean directionCheck = false;

	 int moveSpeed = 0;
	 int moveCounter = 0;

	 boolean north;
	 boolean south;
	 boolean west;
	 boolean east;

	 double slopeX;
	 double slopeY;
	 double maxSlope = 1;

	 int preX;
	 int preY;

	 int picCounter = 0;
	 int timeCounter = 0;

	 int picRank = 6;

	 int range;

	 String type = "";

	 boolean visible = false;
	 boolean collision = false;
	 boolean active = false;
	 
	 boolean isMelee = false;
	 
	 boolean tookDamage = false;
	 
	 boolean hasDoneDmage = false;
	 
	 boolean hasPath = false;
	 
	 boolean newCheckPoint = true;
	 
	 double moveAngle;

	 int hp;
	 
	 int damage;
	 
	 int hitBox;

	 Entity target;
	 
	 Movement move;
	 
	 AI ai;

	 Game game;

	 int oil;
	 int insanity;
	 
	 int respondX = 0;
	 int respondY = 0;
	/* 
	static boolean moveLeft = false;
	static boolean moveRight = false;
	static boolean moveUp = false;
	static boolean moveDown = false;
	*/
	 boolean moveLeft = false;
	 boolean moveRight = false;
	 boolean moveUp = false;
	 boolean moveDown = false;
	 
	 boolean keyMove = false;

	private boolean actionable=false;

	public Inventory inventory;
	int mana;
	 
	 String state;
	 
	int layerY;
	/*
	int[] imageData;
	int picX;
	int picY;
	int spriteWidth;
	int numOfFrame;
	*/
	//int frameTiming;
	
	Animation idle;
	Animation run;
	Animation attack;
	int attackFrame;

	MusicPlayer runningStone;
	MusicPlayer runningDirt;
	
	String characterName;

	/*
	 * Friendly NPC variables
	 */
	private Image npcPortrait;
	private String[] dialogue;
	private int currentDialogue;
	private int [] collisionBox;
	private int width;
	private int height;
	private Dialogue d=null;
	private Diablo.Items.Item[] itemsList = new Diablo.Items.Item[4];
	private ArrayList<String> questlog= new ArrayList<String>();
	private String npcName;
	
	public String getName() {return npcName;}
	
	public void doAction(){
		if(type.equals("friendly")) {
			if(game.dialogue==true) {
				game.dialogue=true;//terminate dialogue
			}
			else {
				game.createDialogue(this);//startup dialogue
			}
		}
		if(type.equals("enemy")) {
			//attack();
		}
		if(type.equals("chest"))
		{
			game.display.getRendererObject().drawChest(this.inventory.getRows(), this.inventory.getCols(), this.inventory);
		}
	}

	public boolean actionable() {
		/*
		 * checks if entity is actionable
		 * for hovering mouse function
		 */
		if(actionable==true)
			return true;
		else
			return false;

	}


	public boolean isEntity(int x, int y) {
		//checks if x and y is within entity's collision box
		boolean result = Movement.isVisible(x,
                y, 
                0,
                0,
                this.x,
               this.y,
               300,
                300);
		if(result==true)
		{
			return true;
		}
		return false;
	}

	//getters
	public Image getNpcPortrait() {return npcPortrait;}
	public Dialogue getDialogue() {return d;}
	public int[] getCollisionBox() {return collisionBox;}
	
	Animation animationInUse;
	public void updateAnimationData(Animation current)
	{
		/*
		imageData = current.imageData;
		picX = current.picWidth;
		picY = current.picHeight;
		spriteWidth = current.spriteWidth;
		numOfFrame = current.numOfFrame;
		*/
		animationInUse = current;

	}
	public void addDialogue(Image NpcPortrait,Dialogue dialogue,String npcName,int [] collisionBox)
	{
		this.npcPortrait=NpcPortrait;
		this.d=dialogue;
		currentDialogue=0;
		this.npcName=npcName;
		 width=collisionBox[0];
			height=collisionBox[1];
			this.collisionBox=collisionBox;
	}
	int destinationX;
	int destinationY;
	public Entity(String type, String name, int[] location, int hp, int hitBox, Game game, int oil, int mana) throws IOException {
		this.game = game;

		this.inventory = new Diablo.Inventory();

		 x = location[0];
         y = location[1];

         this.type = type;
         characterName = name;
         
         this.idle = makeNewAnimation(name+"@idle", Game.models);
        // makeNewAnimation(name);
         
        if(type.contentEquals("player"))
         {
			 try{
				 runningStone = new MusicPlayer(Game.root + "/resources/music/runningStone.WAV");
				 runningDirt = new MusicPlayer(Game.root + "/resources/music/runningDirt.WAV");
				 runningStone.start();
				 runningDirt.start();
			 }catch(Exception ex){ex.printStackTrace();}

        	visible = true;
        	enableMovement();
        	enableAttack();
         }
        else
        {
          
         respondX = x;
         respondY = y;
         ai = new AI(this, game);
        }
        
        target = this;
        
     inventory = new Inventory();
     
  //friendly NPC Variables
  if(type.equals("friendly")) {
   actionable=true;
  }
  if(type.equals("enemy")) {
   actionable=true;
  }
  
  //width= 0;
 // height=0;
  
        this.hp = hp;
        this.hitBox = hitBox;
   moveSpeed = 5;
   
   state = "idle";
   
  
 }
 
 public Animation makeNewAnimation(String name, ArrayList<Animation> list) throws NumberFormatException, IOException
 {
  FileReader reader = new FileReader(Game.root + "/resources/text/" + name + ".txt");
   BufferedReader bufferedReader = new BufferedReader(reader);
   
 
   for(int i = 0; i < list.size(); i++)
   {

   if(list.get(i).name.contentEquals(name))
   {
    System.out.println("animation already exist for -- " + name);
    return list.get(i);
   }
   }
   
  if(name.contains("@idle") == true)
  {
   this.layerY = Integer.parseInt(bufferedReader.readLine());
  }
  if(name.contains("@attack") == true)
  {
   this.attackFrame = Integer.parseInt(bufferedReader.readLine());
  }
  
 
  list.add(new Animation(name,
       Renderer.getImageData(name),
            Integer.parseInt(bufferedReader.readLine()),
            Integer.parseInt(bufferedReader.readLine()),
            Integer.parseInt(bufferedReader.readLine()),
            Integer.parseInt(bufferedReader.readLine())));
     
      System.out.println("new animation added for -- " + name);
 
      
   return list.get(list.size() -1);
 }
 
 public void enableAttack() throws NumberFormatException, IOException
 {
 
  this.attack = makeNewAnimation(characterName + "@attack", Game.models);
 
 }

 public Entity(Game game, String name, int destinationX, int destinationY, int hitBox) throws IOException {

		this.x = game.getEntityList().get(0).x - 70;
        this.y = game.getEntityList().get(0).y - 50;

        if(x < 0)
        {
        	x = 0;
        }
        if(y < 0)
        {
        	y = 0;
        }
        
        //move = new Movement(this, game);

  clickedX = destinationX ;
  clickedY = destinationY ;
  
  characterName = name;
  newClick = true;

        if(name == "arrow")
        {
         type = "projectile";

         visible = true;
         enableMovement();
         newCheckPoint = true;
        }
        if(name == "melee")
        {
         type = "melee";
         visible = true;
        
  
        }
        
        animationInUse = run;
        this.hitBox = hitBox;
//        FileReader reader = new FileReader(Game.root + "/resources/text/" + name + ".txt");
//

//		 BufferedReader bufferedReader = new BufferedReader(reader);
//
//		 moveSpeed = Integer.parseInt(bufferedReader.readLine());
        
        moveSpeed = 10;

   damage = 100;

 }

	public Entity(Game game, String type, int x, int y) throws IOException {
		this.game = game;
		this.x = x;
		this.y = y;
		this.type = type;
		this.inventory = new Diablo.Inventory("Chest", 4, 8);

		this.hp = 1;
		this.hitBox = 80;
		this.target = this;
		this.visible = true;
		this.actionable = true;

		int[] collisionBox = {50, 100};

		this.collisionBox = collisionBox;
		this.width = collisionBox[0];
		this.height = collisionBox[1];

		this.idle = new Animation("chest", Renderer.getImageData("chest"),50,100,50,0);

		this.state = "idle";

		for(int i = 0; i < 32; i++)
		{
			inventory.setBackpackItem(i, new SpeedPotion(game));
		}
	}
 

 public Entity(Game game, int[] imageData, int x, int y, int layerY, int picX, int picY) throws IOException {
  this.game = game;
  this.x = x;
        this.y = y;
        this.layerY = layerY;

        
        animationInUse = new Animation("house",
						        		 imageData, 
						        		 picX,
						        		 picY,
						        		 picX,
							        	 1);
        
 
	}
 
 public Entity(String name, int[] imageData, int x, int y, int layerY, int picX, int picY) throws IOException {
     this.characterName = name;
     this.x = x;
     this.y = y;
     this.layerY = layerY;


     animationInUse = new Animation(name,
              imageData, 
              picX,
              picY,
              picX,
              1);



 }

	public void enableMovement() throws NumberFormatException, IOException
	{
		 
		move = new Movement(this, game);
		
		 if(type == "projectile")
		 {
			
			 this.run = makeNewAnimation(characterName, Game.projectileAnimation);
//			 run = new Animation("arrow",
//					 			 projectileArray, 
//					        	 140,
//					        	 90,
//					        	 140,
//					        	 1);
			 
		 }
		 else
		 {
		     this.run = makeNewAnimation(characterName + "@run", Game.models);
		 }

		
	}
	
	public ArrayList<Entity> getEntityList()
	{
		return game.getEntityList();
	}

	public void setDialogue(Dialogue d) {
		this.d=d;

	}

	public void addItem(int i, Diablo.Items.Item it)
	{
		this.itemsList [i] = it;
	}

	public Diablo.Items.Item getItem(int i)
	{
		return itemsList[i];
	}

	public void removeItem(int i)
	{
		this.itemsList[i] = null;
	}
	public void addQuest(String string) {
		questlog.add(string);
		System.out.println("quest added");
	}
	public ArrayList<String> getQuestlog(){
		return questlog;
	}

	public void setHP(int i)
	{
		this.hp = i;
	}

	public int getHP()
	{
		return this.hp;
	}

	public void setMana(int i)
	{
		this.mana = i;
	}

	public int getMana()
	{
		return this.mana;
	}

	public void setMovespeed(double i)
	{
		moveSpeed = (int) Math.round(5 * i);
		if (moveSpeed <= 0)
			moveSpeed = 1;
	}
	public void setPortrait(Image portrait) {
		this.npcPortrait= portrait;
	}
	
}
