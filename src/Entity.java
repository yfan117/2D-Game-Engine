package Diablo;



import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
	
	public void updateAnimationData(Animation current)
	{
		imageData = current.imageData;
		picX = current.picWidth;
		picY = current.picHeight;
		spriteWidth = current.spritWidth;
		numOfFrame = current.numOfFrame;
	}
	
	public Entity(String type, String name, int[] location, int hp, int hitBox, Game game, int oil, int insanity) throws IOException {
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
        else
        {
        	
        	
        	respondX = x;
        	respondY = y;

        	//move.isVisible();
        	ai = new AI(this, game);
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
		 FileReader reader = new FileReader(Game.root + "/resources/text/" + characterName + "@run.txt");
		 BufferedReader bufferedReader = new BufferedReader(reader);

	     run = new Animation(Renderer.getImageData(characterName + "@run"), 
				        	 Integer.parseInt(bufferedReader.readLine()),
				        	 Integer.parseInt(bufferedReader.readLine()),
				        	 Integer.parseInt(bufferedReader.readLine()),
				        	 Integer.parseInt(bufferedReader.readLine()));	
		move = new Movement(this, game);
	}
	
	public ArrayList<Entity> getEntityList()
	{
		return game.getEntityList();
	}

	
}
