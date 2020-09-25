<<<<<<< HEAD
<<<<<<<< HEAD:src/Display.java
package Diablo;
========

package BigMess;
>>>>>>>> c763570e1ee493dd1a252837001c30f5c3251ba1:BigMess/Display.java
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends Game{


	static Draw draw = new Draw(root + "/resources/images/" , windowX, windowY, list, projectile);

	public Display(){

		JFrame frame = new JFrame();
		//Draw draw = new Draw();
		frame.setSize(windowX, windowY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		//frame.setResizable(false);



		frame.getContentPane().add(draw);


		Control control = new Control();
		draw.addMouseListener(control);


		frame.setVisible(true);



	}

	public static void getDirection(int i) {

			Character genericChar = list.get(i);
		  int differenceX = Math.abs(genericChar.x-genericChar.clickedX);
		  int differenceY = Math.abs(genericChar.y-genericChar.clickedY);

		  int acceptableDifference = 75;

		  if((differenceX < acceptableDifference) && (genericChar.y < genericChar.clickedY)) {
		   genericChar.south = true;
		  }
		  else if((differenceX < acceptableDifference) && (genericChar.y > genericChar.clickedY)) {
		   genericChar.north = true;
		  }
		  else if((differenceY < acceptableDifference) && (genericChar.x < genericChar.clickedX)) {
		   genericChar.east = true;
		  }
		  else if((differenceY < acceptableDifference) && (genericChar.x > genericChar.clickedX)) {
		   genericChar.west = true;
		  }
		  else if((differenceX >= acceptableDifference) && (genericChar.y < genericChar.clickedY) && (genericChar.x < genericChar.clickedX)) {
		   genericChar.south = true;
		   genericChar.east = true;
		  }
		  else if((differenceX >= acceptableDifference) && (genericChar.y < genericChar.clickedY) && (genericChar.x > genericChar.clickedX)) {
		   genericChar.south = true;
		   genericChar.west = true;
		  }
		  else if((differenceX >=  acceptableDifference) && (genericChar.y > genericChar.clickedY)&& (genericChar.x < genericChar.clickedX)) {
		   genericChar.north = true;
		   genericChar.east = true;
		  }
		  else if((differenceX >=  acceptableDifference) && (genericChar.y > genericChar.clickedY)&& (genericChar.x > genericChar.clickedX)) {
		   genericChar.north = true;
		   genericChar.west = true;
		  }
		  /*
		   * save this for testing purposes
		  System.out.println();
		  System.out.println("genericChar.north: "+genericChar.north);
		  System.out.println("genericChar.south: "+genericChar.south);
		  System.out.println("genericChar.west: "+genericChar.west);
		  System.out.println("genericChar.east: "+genericChar.east);
		  */
		  if((genericChar.north == true) && (genericChar.west == true)) {
		   genericChar.picRank = 3;
		  }
		  else if((genericChar.north == true) && (genericChar.east == true)) {
		   genericChar.picRank = 1;
		  }
		  else if((genericChar.south == true) && (genericChar.west == true)) {
		   genericChar.picRank = 5;
		  }
		  else if((genericChar.south == true) && (genericChar.east == true)) {
		   genericChar.picRank = 7;
		  }
		  else if(genericChar.north == true) {
		   genericChar.picRank = 2;
		  }
		  else if(genericChar.south == true) {
		   genericChar.picRank = 6;
		  }
		  else if(genericChar.west == true) {
		   genericChar.picRank = 4;
		  }
		  else if(genericChar.east == true) {
		   genericChar.picRank = 0;
		  }
		  genericChar.directionCheck = false;
	}


	public void update() {

		for(int i = 0; i< list.size(); i++) {
			//if(list.get(i).directionCheck == true)
			if(list.get(i).newClick == true) {
				getDirection(i);
			}
		}
		draw.updateValue();




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
=======
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends Game {
 
 static int picRank = 6;
 
 private Player player;
 
 static public Draw draw;

 public Display(Player _player) {
   
  player = _player;
  draw = new Draw(windowX, windowY, player);
  
  JFrame frame = new JFrame();
  frame.setSize(windowX, windowY);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setLocationRelativeTo(null);
  frame.setResizable(false);
  
  frame.getContentPane().add(draw);
  
  Control control = new Control();
  draw.addMouseListener(control);
  
 
  frame.setVisible(true);
 }
 
 public static void getDirection() {
  int differenceX = Math.abs(centerX-clickedX);
  int differenceY = Math.abs(centerY-clickedY);
  
  if((differenceX < 75) && (centerY < clickedY)) {
   south = true;
  }
  else if((differenceX < 75) && (centerY > clickedY)) {
   north = true;
  }
  else if((differenceY < 75) && (centerX < clickedX)) {
   east = true;
  }
  else if((differenceY < 75) && (centerX > clickedX)) {
   west = true;
  }
  else if((differenceX >= 75) && (centerY < clickedY) && (centerX < clickedX)) {
   south = true;
   east = true;
  }
  else if((differenceX >= 75) && (centerY < clickedY) && (centerX > clickedX)) {
   south = true;
   west = true;
  }
  else if((differenceX >=  75) && (centerY > clickedY)&& (centerX < clickedX)) {
   north = true;
   east = true;
  }
  else if((differenceX >=  75) && (centerY > clickedY)&& (centerX > clickedX)) {
   north = true;
   west = true;
  }
  /*
   * save this for testing purposes 
  System.out.println();
  System.out.println("north: "+north);
  System.out.println("south: "+south);
  System.out.println("west: "+west);
  System.out.println("east: "+east);
  */
  if((north == true) && (west == true)) {
   picRank = 3;
  }
  else if((north == true) && (east == true)) {
   picRank = 1;
  }
  else if((south == true) && (west == true)) {
   picRank = 5;
  }
  else if((south == true) && (east == true)) {
   picRank = 7;
  }
  else if(north == true) {
   picRank = 2;
  }
  else if(south == true) {
   picRank = 6;
  }
  else if(west == true) {
   picRank = 4;
  }
  else if(east == true) {
   picRank = 0;
  }
  directionCheck = false;
 }

 public void update(int x, int y) {
  if(directionCheck == true) {
   getDirection();
  }
  draw.updateValue(x, y, picRank);
 }
}
>>>>>>> c763570e1ee493dd1a252837001c30f5c3251ba1
