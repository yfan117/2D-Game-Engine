<<<<<<< HEAD
<<<<<<<< HEAD:src/Game.java
package Diablo;

========

package BigMess;
>>>>>>>> c763570e1ee493dd1a252837001c30f5c3251ba1:BigMess/Game.java
import java.awt.Dimension;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;





public class Game {
	static final public Path root = Paths.get(System.getProperty("user.dir")).getParent();



	//static String repository = "backGround/";
	static int windowX = 1280;
	static int windowY = 720;

	//static int windowX = 1920;
	//static int windowY = 1080;

	static int centerX = windowX /2 ;
	static int centerY = windowY /2 ;


	static int fps = 30;



	static int[] mapDimension = new int[2];
	static int timer = 1000 / fps;

	static Display display;
	static Map map;
	static ArrayList<Character> list = new ArrayList<Character>();
	static ArrayList<Character> projectile= new ArrayList<Character>();



	public static void main(String[] args) throws IOException {



		try {
			map = new Map("backGround");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}




		list.add(new Character("player", map.getLocation(), 100));
		list.add(new Character("enemy", new int[]{500, 500},100));
		list.add(new Character("enemy", new int[]{200, 100}, 100));


		display = new Display();
		gameLoop();





	}

	public static void gameLoop() {


			while(true)
			{

				//System.out.println("here");
				for(int i = 0; i < list.size(); i++)
				{

					list.get(i).update(i);

					if(i != 0)
						System.out.println(list.get(i).hp);
				}
				System.out.println();

				for(int i = 0; i < projectile.size(); i++)
				{
					projectile.get(i).update(i);




				}



				display.update();

				for(int i = 0; i < projectile.size(); i++)
				{

					if((projectile.get(i).visible == false)||(projectile.get(i).active == false)) {
						projectile.remove(i);
					}



				}
				//System.out.println(projectile.size());

				try {
					Thread.sleep(timer);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}




			}
	}
	/*
	 * save this
	public boolean outBound(int eX, int eY, Character genericChar) {
		  boolean answer = false;

		  int xAxis = windowX / 2;
		  int yAxis = windowY / 2;

		  int x = genericChar.x;
		  int y = genericChar.y;

		  if(eX < xAxis)
		  {
		   if((x - (xAxis - eX)) <= 0)
		    answer = true;
		  }
		  else if(eX > xAxis)
		  {
		   if((x + (eX - xAxis)) >= mapDimension[0])
		    answer = true;
		  }

		  if(eY < yAxis)
		  {
		   if((y - (yAxis - eY)) <= 0)
		    answer = true;
		  }
		  else if(eY > yAxis)
		  {
		   if((y + (eY - yAxis)) >= mapDimension[1])
		    answer = true;
		  }

		  return answer;
		 }

	*/

	}
=======
import java.nio.file.Path;
import java.nio.file.Paths;

public class Game {
 
 static final public Path root = Paths.get(System.getProperty("user.dir")).getParent();
 
 static double maxSlope = 1;
 
 static int windowX = 1280;
 static int windowY = 720;
 
 static int centerX = windowX /2 ;
 static int centerY = windowY /2 ;
 
 static int x = 0;
 static int y = 0;
 static int fps = 30;
 
 static int clickedX ;
 static int clickedY ;
 static boolean newClick = false;
 static boolean directionCheck = false;
 
 static double slopeX;
 static double slopeY;
 
 static int moveSpeed = 20;
 static int moveCounter = 0;
 
 static boolean north;
 static boolean south;
 static boolean west;
 static boolean east;

 static int[] respawnLcation;
 static int[] mapDimension;
 
 static Display display;
 static Map map = new Map("backGround");
 
 static Player player;
 
 public static void main(String[] args) {
  player = new Player();
  display = new Display(player);
  
  x = respawnLcation[0];
  y = respawnLcation[1];
  
  gameLoop();
 }
 
 public boolean outBound(int eX, int eY) {
  boolean answer = false;
 
  int xAxis = windowX / 2;
  int yAxis = windowY / 2;
  
  if(eX < xAxis)
  {
   if((x - (xAxis - eX)) <= 0)
    answer = true;
  }
  else if(eX > xAxis)
  {
   if((x + (eX - xAxis)) >= mapDimension[0])
    answer = true;
  }
  
  if(eY < yAxis)
  {
   if((y - (yAxis - eY)) <= 0)
    answer = true;
  }
  else if(eY > yAxis)
  {
   if((y + (eY - yAxis)) >= mapDimension[1])
    answer = true;
  }
  
  return answer;
 }
 
 public static void gameLoop() {
  int timer = 1000 / fps;
  while(true) {
  try {
   Thread.sleep(timer);
  } catch (InterruptedException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }

  update();
  display.update(x, y);
  }
 }
 
 public static void update() {
  if(newClick == true){
   slopeX = Math.abs(Math.round((double)(clickedX - centerX)/(clickedY - centerY)));
   slopeY = Math.abs(Math.round((double)(clickedY - centerY)/(clickedX - centerX)));

   if(slopeX > 3) {
    slopeX = 3;
   }
   if(slopeY > 3) {
    slopeY = 3;
   }
   
   for(moveCounter = 0; moveCounter< moveSpeed; moveCounter++) {
    if(slopeX > slopeY){
     maxSlope = slopeX;
     for(int j = 0; j <= slopeX; j++){
      updateX();
      
      if(clickedX == centerX) {
       break;
      }
     
      if(moveCounter == moveSpeed) {
       break;
      }
     }
     updateY();
    }
    else if (slopeX < slopeY){
     maxSlope = slopeY;
     
     for(int j = 0; j <= slopeY; j++) {
 
      updateY();
      
      if(clickedY == centerY) {
       break;
      }
      
      if(moveCounter == moveSpeed) {
       break;
      }
     }
     updateX();
    }
    else if(slopeX == slopeY) {
     for(int j = 0; j <= maxSlope; j++) {
      updateX();
      updateY();
   
      if(moveCounter == moveSpeed) {
       break;
      }
     }
    }
   }

   if((clickedX == centerX)&&(clickedY == centerY)) {
    newClick = false;
    maxSlope = 1;
   }
  }
 }
 
 public static void updateX() {
  if(clickedX < centerX){
   centerX --;
   x --;
   moveCounter++;
  }
  else if(clickedX > centerX) {
   centerX ++;
   x ++;
   moveCounter++;
  }
 }
 
 public static void updateY() {
  if(clickedY < centerY) {
   centerY --;
   y --;
   moveCounter++;
  }
  else if(clickedY > centerY) {
   centerY ++;
   y ++;
   moveCounter++;
  }
 }
 
 public void CheckCollisions() {
  //Rectangle playerBounds = player.getBounds();
  //for (Enemy enemy : enemies){
  //Rectangle enemyBounds = enemy.getBounds();
  //if(playerBounds.intersects(enemyBounds)){
  //enemy.setVisible(false);
  //}
  //}
  //List<Arrows> ar = Sprite.getArrows();
  //for (Arrow a : ar){
  //Rectangle arrowBounds = a.getBounds
  //for (Enemy enemy : enemies){
  //Rectangle enemyBounds = enemy.getBounds();
  //if(arrowBounds.intersects(enemyBounds)){
  //enemy.setHP(enemy.getHP - 1);
  //enemy.setVisible(false);
  //make it so when an enemy visible 
 }
}
>>>>>>> c763570e1ee493dd1a252837001c30f5c3251ba1
