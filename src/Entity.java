package Diablo;



import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import Diablo.Items.*;

import javax.imageio.ImageIO;

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
	 boolean active = true;
	 
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
	int[] imageData;
	int picX;
	int picY;
	int spriteWidth;
	int numOfFrame;
	//int frameTiming;
	
	Animation idle;
	Animation run;
	
	String characterName;

	MusicPlayer runningStone;
	MusicPlayer runningDirt;

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
	private ArrayList<Objective> questlog= new ArrayList<Objective>();
	/*
	 * Friendly NPC constructor
	 */
	

	public void doAction(){
		if(type.equals("friendly")) {
			if(game.dialogue==true) {
				game.dialogue=false;//terminate dialogue
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

		if(x<this.x+width/2 && x >this.x-width/2 && y <this.y+height/2 && y>this.y-height/2)
		{
			return true;
		}
		return false;
	}

	//getters
	public Image getNpcPortrait() {return npcPortrait;}
	public Dialogue getDialogue() {return d;}
	public int[] getCollisionBox() {return collisionBox;}
	
	public void updateAnimationData(Animation current)
	{
		imageData = current.imageData;
		picX = current.picWidth;
		picY = current.picHeight;
		spriteWidth = current.spritWidth;
		numOfFrame = current.numOfFrame;
	}
	public void addDialogue(Image NpcPortrait,Dialogue dialogue, int[] collisionBox)
	{
		this.npcPortrait=NpcPortrait;
		this.d=dialogue;
		currentDialogue=0;
		this.collisionBox=collisionBox;
	}
	public Entity(String type, String name, int[] location, int hp, int hitBox, Game game, int mana) throws IOException {
		this.game = game;

		this.inventory = new Diablo.Inventory();

		 x = location[0];
         y = location[1];

         this.type = type;
         characterName = name;
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
		
		width= 0;
		height=0;
		
        this.hp = hp;
        this.hitBox = hitBox;
        
         FileReader reader = new FileReader(Game.root + "/resources/text/" + name + ".txt");
		 BufferedReader bufferedReader = new BufferedReader(reader);

		 layerY = Integer.parseInt(bufferedReader.readLine());
	
		 
	     this.idle = makeNewAnimation(name);
		// System.out.printf("%d %d %d \n", layerY, picX, picY);

		 moveSpeed = 5;
		 
		 state = "idle";
	}
	
	public Animation makeNewAnimation(String name) throws NumberFormatException, IOException
	{
		FileReader reader = new FileReader(Game.root + "/resources/text/" + name + ".txt");
		 BufferedReader bufferedReader = new BufferedReader(reader);
		 
		 //boolean isExist = false;
		 for(int i = 0; i < Game.models.size(); i++)
		 {
			//System.out.println(Game.models.get(i).name);
			if(Game.models.get(i).name.contentEquals(name))
			{
				//isExist = true;
				System.out.println("animation already exist for -- " + name);
				return Game.models.get(i);
			}
		 }
		 
		 //if(isExist == false)
		 {
			 Game.models.add(new Animation(name,
					 	      Renderer.getImageData(name),
				        	  Integer.parseInt(bufferedReader.readLine()),
				        	  Integer.parseInt(bufferedReader.readLine()),
				        	  Integer.parseInt(bufferedReader.readLine()),
				        	  Integer.parseInt(bufferedReader.readLine())));
			 
			 System.out.println("new animation added for -- " + name);
		 }
	     
		 return Game.models.get(Game.models.size() -1);
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

	public Entity(Game game, String name, int destinationX, int destinationY, int hitBox) throws IOException {

		this.x = game.getEntityList().get(0).x;
        this.y = game.getEntityList().get(0).y;

        preX = x;
        preY = y;
        
        //move = new Movement(this, game);

		clickedX = destinationX ;
		clickedY = destinationY ;

		newClick = true;

        if(name == "arrow")
        {
        	type = "projectile";
        	visible = true;
        	enableMovement();
		
        }
        if(name == "melee")
        {
        	type = "melee";
        	visible = true;
        
		
        }
        
        this.hitBox = hitBox;
        FileReader reader = new FileReader(Game.root + "/resources/text/" + name + ".txt");

		 BufferedReader bufferedReader = new BufferedReader(reader);

		 moveSpeed = Integer.parseInt(bufferedReader.readLine());

		 damage = 100;

	}
	

	public Entity(Game game, int[] imageData, int x, int y, int layerY, int picX, int picY) throws IOException {
		this.game = game;
		this.x = x;
        this.y = y;
        this.layerY = layerY;
        this.imageData = imageData;
        this.picX = picX;
        this.picY = picY;
        spriteWidth = picX;
	}
	
	public void enableMovement() throws NumberFormatException, IOException
	{
		 
		move = new Movement(this, game);
		
		 if(type == "projectile")
		 {
			 run = new Animation("arrow",
					 Renderer.getImageData("arrow2"), 
		        	 64,
		        	 64,
		        	 64,
		        	 0);
			 
		 }
		 else
		 {
		     this.run = makeNewAnimation(characterName + "@run");
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
	public void addObjective(Objective quest) {
		questlog.add(quest);
	}
	public ArrayList<Objective> getQuestlog(){
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
}
