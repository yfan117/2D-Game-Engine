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
	static int windowX = 1920;
	static int windowY = 1080;

	 int preX;
	 int preY;

	 int picCounter = 0;
	 int timeCounter = 0;

	 int picRank = 6;

	 int rangeCounter = 0;
	 int range;

	 String type;

	 boolean visible = false;
	 boolean collision = false;
	 boolean active = true;//temp

	 int hp;
	 //Character collider;
	 private int whichEn;


	public Character(String name, int[] location, int hp) throws IOException {

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
        	this.hp = hp;

        }

		FileReader reader = new FileReader(root + "/resources/text/" + name + ".txt");

		 BufferedReader bufferedReader = new BufferedReader(reader);

		//moveSpeed = Integer.parseInt(bufferedReader.readLine());

		 moveSpeed = 20;

	}

	public Character(String name, int originX, int originY, int destinationX, int destinationY) throws IOException {

		this.x = originX;
        this.y = originY;

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

		FileReader reader = new FileReader(root + "/resources/text/" + name + ".txt");

		 BufferedReader bufferedReader = new BufferedReader(reader);

		 moveSpeed = Integer.parseInt(bufferedReader.readLine());

		 range = Integer.parseInt(bufferedReader.readLine());


	}


	public void update(int placeInList)
	{


		if((type == "enemy")&& ((list.get(0).x > (x + 600))||(list.get(0).x < (x - 600))
				||(list.get(0).y > (y + 600))||(list.get(0).y < (y - 600))))
		{
			north = false;
			south = false;
			west = false;
			east = false;
			directionCheck = true;

			clickedX = list.get(0).x - (int)(Math.random()*100);
			clickedY = list.get(0).y - (int)(Math.random()*100);



			newClick = true;


		}


		isVisible();
		isCollision(clickedX, clickedY, placeInList);



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
						updateX(placeInList);


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
						updateY(placeInList);


				}
				else if (slopeX < slopeY){

					maxSlope = slopeY;

					for(int j = 0; j <= slopeY; j++) {

						updateY(placeInList);



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
						updateX(placeInList);



				}
				else if(slopeX == slopeY)
				{

					for(int j = 0; j <= maxSlope; j++)
					{

						updateX(placeInList);
						updateY(placeInList);


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


			if((clickedX == x)&&(clickedY == y))
			{

			//	active = false;
				newClick = false;
				maxSlope = 1;

			}


		}
	}


		public void updateX(int placeInList) {


				if(clickedX < x){

					isCollision(x-1, y, placeInList);

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

					isCollision(x+1, y, placeInList);

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

			public void updateY(int placeInList) {

				if(clickedY < y) {

					isCollision(x, y-1, placeInList);

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

					isCollision(x, y+1, placeInList);

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

			public void isCollision(int x, int y, int placeInList)
			{
				if(type !="player")
				{
					for(int i = 1; i < list.size(); i++)
					{
						if((this.visible == true)&&(list.get(i).visible == true))
						{
							if((i != placeInList)||(type == "projectile"))
							{
								if(((x > list.get(i).x ) &&(x < list.get(i).x + 100))
						        		&&
						        		((y > list.get(i).y) &&(y < list.get(i).y + 100)))
						        {


										if(type == "projectile")
										{
											projectile.get(placeInList).collision = true;

											//this.collider = list.get(i);
											setHP(list.get(i));
											//System.out.println(whichEn);
											//System.out.println(placeInList);
											break;
										}
										else
										{
											list.get(placeInList).collision = true;
											//this.collider = list.get(i);
											//collider = list.get(placeInList);
											//whichEn = placeInList;
											//collision = true;
										}


						        }
								else
								{
									if(type == "projectile")
									{
										//if(collision == true)
											//System.out.println(projectile.get(placeInList).collision);

										projectile.get(placeInList).collision = false;
										//break;
									}
									else
									{
										//list.get(i).collision = false;
										list.get(placeInList).collision = false;
									}


								}
							}

						}}}
					}
			/*
			public void hit(int hitAmount) {
			//	if (type == "enemy") {
					//list.get(whichEn).hp = this.hp - hitAmount;
				//	this.hp = this.hp + hitAmount;
					list.get(whichEn).setHP(this.hp - hitAmount);
					//System.out.println(this.hp);
				//	System.out.println(hp);

			//		if(hp == 0) {
			//			die();
				//	}
			//	}
		//	}
		//	public void die() {
			//	list.remove(whichEn);

			}
			*/
			public void setHP(Character temp) {
				temp.hp = temp.hp - 2;
				//System.out.println("set" + this.hp);
			}
			public int getHP() {
				return this.hp;
			}
		//	public String getType() {
		//		return this.type;
		//	}

}
