package Diablo;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display{

	private JFrame frame;
	private MainMenu mainmenu;
	private JPanel visiblePanel;
	private PauseScreen pause;
	private CardLayout cards= new CardLayout();
	private Renderer render;
	private Game game;
	private int currentPanel;

	public Display(Game game){

		this.game = game;

		render = new Renderer(game.root + "/resources/images/" , game.windowX, game.windowY, game.getEntityList(), game.getProjectileList(), this);
		frame = new JFrame();
		frame.setSize(game.windowX + 16, game.windowY + 39);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		render.addMouseListener(new MouseControl(this.game));
		frame.addKeyListener(new KeyboardControl(this.game));
		frame.setResizable(false);
		/*
		 * Code to create "visible layers", which displays
		 * and switches game states
		 */
		visiblePanel=new JPanel();
		visiblePanel.setLayout(cards);
		mainmenu= new MainMenu(this, game);
		visiblePanel.add(mainmenu,"menu");
		visiblePanel.add(render,"game");
		pause=new PauseScreen(this);
		visiblePanel.add(pause,"pause");
		frame.add(visiblePanel);
		cards.show(visiblePanel, "menu");
		frame.setFocusable(true);
		frame.setVisible(true);
	}

	public Game getGame(){return game;}

	public void switchJPanels(int i ) {
		switch(i) {
			case 0://menu state
				cards.show(visiblePanel, "menu");
				mainmenu.startMusic();
				currentPanel = 0;
				break;
			case 1: //game state
				cards.show(visiblePanel, "game");
				game.map.playMapMusic();
				currentPanel = 1;
				break;
			case 2: //pause state
				cards.show(visiblePanel,"pause");
				currentPanel = 2;
				break;
			case 3://inventory
				cards.show(visiblePanel, "inventory");
				currentPanel = 3;
				break;
		}
	}

	public static void getDirection(Entity genericChar) {

			//Entity genericChar = list.get(i);
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
		   genericChar.picRank = 7;
		  }
		  else if((genericChar.north == true) && (genericChar.east == true)) {
		   genericChar.picRank = 1;
		  }
		  else if((genericChar.south == true) && (genericChar.west == true)) {
		   genericChar.picRank = 5;
		  }
		  else if((genericChar.south == true) && (genericChar.east == true)) {
		   genericChar.picRank = 3;
		  }
		  else if(genericChar.north == true) {
		   genericChar.picRank = 0;
		  }
		  else if(genericChar.south == true) {
		   genericChar.picRank = 4;
		  }
		  else if(genericChar.west == true) {
		   genericChar.picRank = 6;
		  }
		  else if(genericChar.east == true) {
		   genericChar.picRank = 2;
		  }
		  genericChar.directionCheck = false;
	}


	public void update() {

		for(int i = 0; i< game.getEntityList().size(); i++) {
			//if(list.get(i).directionCheck == true)
			if(game.getEntityList().get(i).newClick == true) {
				getDirection(game.getEntityList().get(i));
			}
		}
		render.updateValue();
	}
	
	}
