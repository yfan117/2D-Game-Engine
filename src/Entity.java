package Diablo;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Entity extends Game{


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

	 int hp;
	 
	 int damage;
	 
	 int hitBox;

	 Entity target;
	 
	 Movement move;
	 
	 AI ai;
	 
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

	public Entity(String name, int[] location, int hp, int hitBox) throws IOException {

		
		 x = location[0];
         y = location[1];

         
         preX = x;
         preY = y;
         move = new Movement(this);
        if(name == "player")
         {
        	type = "player";
        	//isPlayer = true;
			//this.centerX = x + windowX/2;
			//this.centerY = y + windowY/2;
        	visible = true;
     
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
        FileReader reader = new FileReader(root + "/resources/text/" + name + ".txt");
	

		 BufferedReader bufferedReader = new BufferedReader(reader);

		//moveSpeed = Integer.parseInt(bufferedReader.readLine());

		 moveSpeed = 5;
		 
		
	}

	public Entity(String name, int destinationX, int destinationY, int hitBox) throws IOException {

		this.x = list.get(0).x;
        this.y = list.get(0).y;

        preX = x;
        preY = y;
        
        move = new Movement(this);

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
        FileReader reader = new FileReader(root + "/resources/text/" + name + ".txt");

		 BufferedReader bufferedReader = new BufferedReader(reader);

		 moveSpeed = Integer.parseInt(bufferedReader.readLine());

		 damage = Integer.parseInt(bufferedReader.readLine());

	}
	
	public Entity(String name, int x, int y) throws IOException {

		this.x = x;
        this.y = y;


        FileReader reader = new FileReader(root + "/resources/text/" + name + ".txt");

		 BufferedReader bufferedReader = new BufferedReader(reader);

		 int x1 = Integer.parseInt(bufferedReader.readLine());

		 int y1 = Integer.parseInt(bufferedReader.readLine());
		 
		 int x2 = Integer.parseInt(bufferedReader.readLine());

		 int y2 = Integer.parseInt(bufferedReader.readLine());
		 
		 System.out.println(name+" "+ x+" "+ y);
		 System.out.println(x1+" "+ y1+" "+ x2 +" " +y2);
		 
		 for(int a = x1 + x; a < x2 + x; a = a + 5)
		 {
			 for(int b = y1 + y; b < y2 +y; b = b + 5)
			 {
				 Game.obstacleLocation.add(new Node(a, b)); 
			 }
		 }
		 
		

	}
	
	public void enableMovement()
	{
		move = new Movement(this);
	}


			
		
}
