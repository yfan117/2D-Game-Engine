package Diablo;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Character extends Game{


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


	 //int windowX = 1280;
	 //int windowY = 720;
	// int windowX = 1920;
	 //int windowY = 1080;

	 int preX;
	 int preY;

	 int picCounter = 0;
	 int timeCounter = 0;

	 int picRank = 6;

	 //int rangeCounter = 0;
	 int range;

	 String type;

	 boolean visible = false;
	 boolean collision = false;
	 boolean active = true;
	 
	 boolean isMelee = true;
	 
	 boolean tookDamage = false;
	 
	 boolean hasDoneDmage = false;

	 int hp;
	 
	 int damage;
	 
	 int hitBox;

	 Character target;

	public Character(String name, int[] location, int hp, int hitBox) throws IOException {

		 x = location[0];
         y = location[1];

         preX = x;
         preY = y;

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

        	isVisible();
   
        }
        
        target = this;
        this.hp = hp;
        this.hitBox = hitBox;
        FileReader reader = new FileReader(root + "/resources/text/" + name + ".txt");
	

		 BufferedReader bufferedReader = new BufferedReader(reader);

		//moveSpeed = Integer.parseInt(bufferedReader.readLine());

		 moveSpeed = 20;

	}

	public Character(String name, int destinationX, int destinationY, int hitBox) throws IOException {

		this.x = list.get(0).x;
        this.y = list.get(0).y;

        preX = x;
        preY = y;



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


	public void update(Character current)
	{

		hasDoneDmage = false;
		if((type == "enemy")&& ((list.get(0).x > (x + 200))||(list.get(0).x < (x - 200))
				||(list.get(0).y > (y + 200))||(list.get(0).y < (y - 200))))
		{
			north = false;
			south = false;
			west = false;
			east = false;
			directionCheck = true;

			clickedX = list.get(0).x - (int)(Math.random()*100);
			clickedY = list.get(0).y - (int)(Math.random()*100);
			//clickedX = list.get(0).x;
			//clickedY = list.get(0).y;



			newClick = true;
			
			target = list.get(0);


		}


		isVisible();
	
		isCollision(clickedX, clickedY, current);

		
		

		//&&(collision = false)


		if(newClick == true)
		{




			slopeX = Math.abs(Math.round((double)(clickedX - x)/(clickedY - y)));
			slopeY = Math.abs(Math.round((double)(clickedY - y)/(clickedX - x)));


			if(slopeX > 4) {
				slopeX = 4;
			}
			if(slopeY > 4) {
				slopeY = 4;
			}

			for(moveCounter = 0; moveCounter< moveSpeed; moveCounter++)
			{

				//if(type == "projectile")
				//System.out.println(projectile.get(placeInList).collision);

				if(slopeX > slopeY)
				{

					maxSlope = slopeX;
					for(int j = 0; j <= slopeX; j++)
					{
						updateX(current);


								if(clickedX == x)
								{
									break;
								}



						if(moveCounter == moveSpeed) {
							break;

						}



					}
					if(collision == true)
					{
						newClick = false;
						break;
					}
					else
						updateY(current);


				}
				else if (slopeX < slopeY){

					maxSlope = slopeY;

					for(int j = 0; j <= slopeY; j++) {

						updateY(current);



							if(clickedY == y) {
								break;
							}



						if(moveCounter == moveSpeed) {
							break;

						}


					}
					if(collision == true)
					{
						newClick = false;
						break;
					}
					else
						updateX(current);



				}
				else if(slopeX == slopeY)
				{

					for(int j = 0; j <= maxSlope; j++)
					{

						updateX(current);
						updateY(current);


						if(moveCounter == moveSpeed)
						{
							break;

						}

						if(collision == true)
						{
							newClick = false;
							break;
						}

					}

				}

				//if((type == "projectile")&&(collision == true))
					//System.out.println("x:" +x +" y:" +y +"   collision:"+collision +"  visible:"+visible +" active:" +active +" newClick:" +newClick +"\n");

			}
			//takeDamage(list.get(0), 100);
			System.out.println(list.get(0).hp);
			if((clickedX == x)&&(clickedY == y))
			{

			//	active = false;
				newClick = false;
				maxSlope = 1;
				
			
				//if((current.type == "player")&&(target != this))
				if(this.target != this)
				{
					//System.out.println("here");
					if(target.hp >0)
					{
						if((isInRange(this, this.target) == true)&&(hasDoneDmage == false))
						{
							takeDamage(target, 10);
							this.target = this;
						}
					}
					
				
				}
				
				
			}


		}
	}


		public void updateX(Character current) {


				if(clickedX < x){

					isCollision(x-1, y, current);

					//System.out.println(collision);
					if(collision == false)
					{
						x --;
						moveCounter++;




					}
					else
					{

						clickedX = x;
						clickedY = y;

					}




				}
				else if(x < clickedX) {

					isCollision(x+1, y, current);

					if(collision == false)
					{
						x ++;
						moveCounter++;



					}
					else
					{

						clickedX = x;
						clickedY = y;

					}

				}


			}

			public void updateY(Character current) {

				if(clickedY < y) {

					isCollision(x, y-1, current);

					if(collision == false)
					{
						y --;
						moveCounter++;


					}
					else
					{

						clickedX = x;
						clickedY = y;

					}

				}
				else if(clickedY > y) {

					isCollision(x, y+1, current);

					if(collision == false)
					{
						y ++;
						moveCounter++;


					}
					else
					{

						clickedX = x;
						clickedY = y;

					}

			}
		}


			public void isVisible() {
				if(((x >= list.get(0).x - windowX/2) &&(x <= list.get(0).x + windowX/2))
		        		&&
		        		((y >= list.get(0).y - windowY/2) &&(y <= list.get(0).y + windowY/2)))
		        	{

		        		visible = true;
		        	}
				else
					    visible = false;

			}

			public int isCollision(int x, int y, Character current)
			{
				int result = 0;
				if(type !="player")
				{
					for(int i = 1; i < list.size(); i++)
					{
						if((this.visible == true)&&(list.get(i).visible == true))
						{
							if((list.get(i) != current)||(current.type == "projectile"))
							{
								//System.out.println("here");
								if(((x + current.hitBox > list.get(i).x) &&(x < list.get(i).x + list.get(i).hitBox))
						        		&&
						        		((y + current.hitBox > list.get(i).y) &&(y < list.get(i).y + list.get(i).hitBox)))
						        {


										if(current.type == "projectile")
										{
											current.collision = true;

											//this.collider = list.get(i);
											takeDamage(list.get(i), current.damage);
											//System.out.println(current.type);
											//System.out.println(placeInList);
											//result = true;
											break;
										}
										if(current.type == "melee")
										{
											current.collision = true;

											//this.collider = list.get(i);
											//setHP(list.get(i));
											result = i;
											//System.out.println(current.type);
											//System.out.println(placeInList);
											break;
										}
										else
										{
											current.collision = true;
											//this.collider = list.get(i);
											//collider = list.get(placeInList);
											//whichEn = placeInList;
											//collision = true;
										}
						        }

										//
								else
								{
									if(current.type == "projectile")
									{
										//if(collision == true)
											//System.out.println(projectile.get(placeInList).collision);

										current.collision = false;
										//break;
									}
									else
									{
										//list.get(i).collision = false;
										current.collision = false;
									}
									
									//result = false;


								}
							}

						}}}
				
				return result;
					}

			public void takeDamage(Character target, int damage) {
				target.hp = target.hp - damage;
				target.tookDamage = true;
				hasDoneDmage = true;
				//System.out.println("set" + damage);
			}
			
			public boolean isInRange(Character self, Character target)
			{
				int range = 20;
				boolean result = false;
			
					
				if((self.x < (target.x + range))&&(self.x > (target.x - range))
					||(self.y < (target.y + range))&&(self.y > (target.y - range)))
					{
						result = true;
					}
					
					return result;
			}
			
			
		
}
