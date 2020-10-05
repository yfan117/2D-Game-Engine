package Diablo;

import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends Game {

	private JFrame frame;
	private MainMenu mainMenu = new MainMenu(this);
	static Draw draw = new Draw(root + "/resources/images/" , windowX, windowY, list, projectile);
	private int currentPanel = 0;

	public Display() {

		frame = new JFrame("Perdition's Light");
		//Draw draw = new Draw();
		frame.setSize(windowX, windowY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		//frame.setResizable(false);

		frame.getContentPane().add(mainMenu);

		Control control = new Control();
		draw.addMouseListener(control);


		frame.setVisible(true);
	}

	public void switchJPanels(int i) {

		frame.getContentPane().removeAll();
		switch(i) {
			case 0:
				frame.getContentPane().add(mainMenu);
				currentPanel = 0;
				break;
			case 1:
				frame.getContentPane().add(draw);
				currentPanel = 1;
				break;
		}
		frame.revalidate();
	}

	public int getCurrentPanel() {
		return currentPanel;
	}


	public MainMenu getMainMenu() {
		return mainMenu;

	}

	public static void getDirection(Character genericChar) {

		//Character genericChar = list.get(i);
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
				getDirection(list.get(i));
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
