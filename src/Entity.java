package Diablo;



import java.awt.Image;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Diablo.items.Item;

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

	 String type;

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
	 private Item[] itemsList = new Item[4];
	 private ArrayList<Objective> questlog= new ArrayList<Objective>();
	  /*
	  * Friendly NPC constructor
	  */
	 public Entity(String type, int[] location, int hp,int mana, int hitBox, Game game, int oil, int insanity, 
				Image NpcPortrait,Dialogue dialogue, int[] collisionBox)throws IOException
			 { 
     
     	this.game = game;

		 x = location[0];
         y = location[1];
         
         preX = x;
         preY = y;


         this.type = type;
         characterName = name;
        if(type == "player")

         {
        	
        	//isPlayer = true;
			//this.centerX = x + windowX/2;
			//this.centerY = y + windowY/2;
        	visible = true;
        	//move = new Movement(this, game);
        	enableMovement();
         }

        if(name == "enemy")
        {

        	type = "enemy";

        	
        	int respondX = x;
        	int respondY = y;


        	move.isVisible();
        	ai = new AI(this);

        }
        
        target = this;
        this.hp = hp;
        this.hitBox = hitBox;
         FileReader reader = new FileReader(Game.root + "/resources/text/" + name + ".txt");
		 BufferedReader bufferedReader = new BufferedReader(reader);


		 layerY = Integer.parseInt(bufferedReader.readLine());
		 /*
		 picX = Integer.parseInt(bufferedReader.readLine());
		 picY = Integer.parseInt(bufferedReader.readLine());
		 spriteWidth = Integer.parseInt(bufferedReader.readLine());
		 spriteFrame = Integer.parseInt(bufferedReader.readLine());
		 frameTiming = Integer.parseInt(bufferedReader.readLine());
		 */
		 //System.out.println(name);
	     idle = new Animation(Renderer.getImageData(name), 
				        	  Integer.parseInt(bufferedReader.readLine()),
				        	  Integer.parseInt(bufferedReader.readLine()),
				        	  Integer.parseInt(bufferedReader.readLine()),
				        	  Integer.parseInt(bufferedReader.readLine()));
		 
		// System.out.printf("%d %d %d \n", layerY, picX, picY);

		 moveSpeed = 5;
		 
		 state = "idle";
		 
				this.game = game;
				this.oil = oil;
				this.insanity = insanity;
				
				 x = location[0];
				 y = location[1];
				 preX = x;
				 preY = y;
				 move = new Movement(this, game);
		    	int respondX = x;
		    	int respondY = y;
		    	move.isVisible();
		    	ai = new AI(this);
			    target = this;
		        this.hp = hp;
		        this.hitBox = hitBox;
		        moveSpeed = 5;
		        
		        this.mana = mana;
		        inventory = new Inventory();
		        //friendly NPC Variables
				this.type=type;
		 		this.npcPortrait=NpcPortrait;
		 		this.d=dialogue;
		 		currentDialogue=0;
		 		this.collisionBox=collisionBox;
		 		if(type.equals("friendly")) {
		 			actionable=true;
		 		}
		 		if(type.equals("enemy")) {
		 			actionable=true;
		 		}
		 		width= collisionBox[0];
		 		height=collisionBox[1];
			 }

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
		 
	 
	/*
	* Player and enemy constructor
	*/
	public Entity(String name, int[] location, int hp, int hitBox, Game game, int oil, int insanity) throws IOException {
		//new stuff
		this.mana = mana;
        inventory = new Inventory();
        //
        

	 String state;
	
	Animation idle;
	Animation run;
	
	String characterName;
	
	public void updateAnimationData(Animation current)
	{
		imageData = current.imageData;
		picX = current.picWidth;
		picY = current.picHeight;
		spriteWidth = current.spritWidth;
		numOfFrame = current.numOfFrame;
	}

	public Entity(String name, int destinationX, int destinationY, int hitBox) throws IOException {

		this.x = game.getEntityList().get(0).x;
        this.y = game.getEntityList().get(0).y;

        preX = x;
        preY = y;
        
        move = new Movement(this, game);

		clickedX = destinationX ;
		clickedY = destinationY ;

		newClick = true;

        if(name == "arrow")
        {
        	type = "projectile";
        	visible = true;
        
		
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

		 damage = Integer.parseInt(bufferedReader.readLine());

	}
	
	public Entity(Game game, String name, int x, int y) throws IOException {
		this.game = game;
		this.x = x;
        this.y = y;
/*
        System.out.println("here");
        FileReader reader = new FileReader(Game.root + "/resources/text/" + name + ".txt");

		 BufferedReader bufferedReader = new BufferedReader(reader);

		 int x1 = Integer.parseInt(bufferedReader.readLine());

		 int y1 = Integer.parseInt(bufferedReader.readLine());
		 
		 int x2 = Integer.parseInt(bufferedReader.readLine());

		 int y2 = Integer.parseInt(bufferedReader.readLine());
		 
		 //System.out.println(name+" "+ x+" "+ y);
		 //System.out.println(x1+" "+ y1+" "+ x2 +" " +y2);
		 
		 for(int a = x1 + x; a < x2 + x; a = a + 5)
		 {
			 for(int b = y1 + y; b < y2 +y; b = b + 5)
			 {
				// game.getObstacleLocation().add(new Node(a, b));
			 }
		 }
		 */
	}
	
	public void enableMovement() throws NumberFormatException, IOException
	{
		 FileReader reader = new FileReader(Game.root + "/resources/text/" + characterName + "@run.txt");
		 BufferedReader bufferedReader = new BufferedReader(reader);

	     run = new Animation(Renderer.getImageData(characterName + "@run"), 
				        	 Integer.parseInt(bufferedReader.readLine()),
				        	 Integer.parseInt(bufferedReader.readLine()),
				        	 Integer.parseInt(bufferedReader.readLine()),
				        	 Integer.parseInt(bufferedReader.readLine()));	
		move = new Movement(this, game);
	}
	
	public void setDialogue(Dialogue d) {
		this.d=d;
		
	}

	public ArrayList<Entity> getEntityList(){return game.getEntityList();}
	
    public void addItem(int i, Item it)
    {
        this.itemsList [i] = it;
    }

    public Item getItem(int i)
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
