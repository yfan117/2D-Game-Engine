package Diablo;

import java.awt.Dimension;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Game {

	static final public Path root = Paths.get(System.getProperty("user.dir")).getParent();

	//GameState enum
	public static enum GameState {
		MAINMENU_STATE,
		MAINGAME_STATE,
		PAUSE_STATE,
		DEAD_STATE
	}
	public static GameState gameState;
	public static void changeGameState(int i) {
		gameState = GameState.values()[i];
	}

	//Time until night/day switch, and helper methods
	static private int timeUntilNight = 18000;//10 minutes
	static private int minutes;
	static private int seconds;
	public static String timeToClockString() {
		minutes = timeUntilNight / 1800;
		seconds = (timeUntilNight % 1800) / 30;
		return(Integer.toString(minutes) + ":" + new DecimalFormat("00").format(seconds));
	}
	public static int getTimeUntilNight() {
		return timeUntilNight;
	}


	//itNight boolean, and helper methods
	static private boolean isNight = false;
	public static void setNight(boolean b) {
		isNight = b;
	}
	public static boolean getNight() {
		return isNight;
	}

	//this is only works for Fan
	//static final public String root = Paths.get(System.getProperty("user.dir")).getParent()+"/Portfolio";

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
		//gameState = new GameState();
		changeGameState(0);

		//System.out.println(root);

		try {
			map = new Map("backGround");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		list.add(new Character("player", map.getLocation(), 100, 80, 100, 0));
		list.add(new Character("enemy", new int[]{500, 500},100, 80, 10, 0));
		list.add(new Character("enemy", new int[]{200, 100}, 100, 80, 10, 0));

		display = new Display();
		gameLoop();
	}

	public static void gameLoop() {

			while(true) {
				switch(gameState) {
					case MAINMENU_STATE:
						if(display.getCurrentPanel() != 0)
							display.switchJPanels(0);
						System.out.print("");//This is needed so the loop has something to execute, if we remove this the game freezes when exiting the main menu**************************BUG
						break;
					case MAINGAME_STATE:
						timeUntilNight--;
						if (timeUntilNight == 0) {
							if (isNight)
								isNight = false;
							else
								isNight = true;

							timeUntilNight = 18000;
							display.draw.changeToNight(root + "/resources/images/");
						}

						//System.out.println("here");
						for (int i = 0; i < list.size(); i++) {
							list.get(i).update(list.get(i));
							//System.out.println(list.get(i).hp );
						}
						//System.out.println();

						for (int i = 0; i < projectile.size(); i++) {
							projectile.get(i).update(projectile.get(i));

							//System.out.println(projectile.get(i).collision);
						}

						display.update();

						//list.get(0).takeDamage(list.get(0), 1);

						for (int i = 0; i < projectile.size(); i++) {
							if ((projectile.get(i).visible == false) || (projectile.get(i).active == false)) {
								projectile.remove(i);
							}
						}

						for (int i = 0; i < list.size(); i++) {
							if (list.get(i).hp <= 0) {
								list.get(i).visible = false;
							}
						}

						//System.out.println(projectile.size());

						if (list.get(0).hp <= 0) {
							changeGameState(3);
						}

						try {
							Thread.sleep(timer);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case PAUSE_STATE:
						break;
					case DEAD_STATE:
						break;
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
