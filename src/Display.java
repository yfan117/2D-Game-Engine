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

		render = new Renderer(game, game.root + "/resources/images/" , game.windowX, game.windowY, game.getEntityList(), game.getProjectileList(), this);
		frame = new JFrame();
		frame.setSize(game.windowX, game.windowY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		render.addMouseListener(new MouseControl(this.game));
		frame.addKeyListener(new KeyboardControl(this.game));
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
		  
		  /*
		   *
		   * save this for testing purposes
		  System.out.println();
		  System.out.println("genericChar.north: "+genericChar.north);
		  System.out.println("genericChar.south: "+genericChar.south);
		  System.out.println("genericChar.west: "+genericChar.west);
		  System.out.println("genericChar.east: "+genericChar.east);
		  */
		int angleDiff = 15;
		  if((genericChar.moveAngle > 90 + angleDiff) && (genericChar.moveAngle < 180 - angleDiff)) {
		   genericChar.picRank = 7;
		  }
		  else if((genericChar.moveAngle > 0 + angleDiff) && (genericChar.moveAngle < 90 - angleDiff)) {
		   genericChar.picRank = 1;
		  }
		  else if((genericChar.moveAngle > 180 + angleDiff) && (genericChar.moveAngle < 270 - angleDiff)) {
		   genericChar.picRank = 5;
		  }
		  else if((genericChar.moveAngle > 270 + angleDiff) && (genericChar.moveAngle < 360 - angleDiff)) {
		   genericChar.picRank = 3;
		  }
		  else if((genericChar.moveAngle > 90 - angleDiff) && (genericChar.moveAngle < 90 + angleDiff)) {
		   genericChar.picRank = 0;
		  }
		  else if((genericChar.moveAngle > 270 - angleDiff) && (genericChar.moveAngle < 270 + angleDiff)) {
		   genericChar.picRank = 4;
		  }
		  else if((genericChar.moveAngle > 180 - angleDiff) && (genericChar.moveAngle < 180 + angleDiff)) {
		   genericChar.picRank = 6;
		  }
		  else if((genericChar.moveAngle < 0 + angleDiff) || (genericChar.moveAngle > 360 - angleDiff)) {
		   genericChar.picRank = 2;
		  }
		  //genericChar.directionCheck = false;
	}

	
		public void update() {
	
			for(int i = 0; i< game.getEntityList().size(); i++) {
				//if(list.get(i).directionCheck == true)
				if(game.getEntityList().get(i).newCheckPoint == true) {
					//getDirection(game.getEntityList().get(i));
				}
			}
			
			if(render.renderReady == true)
			{
				render.updateValue();
			}
			else
			{
				//System.out.println("not ready");
			}
			
		
			
		}
		
		public Renderer getRendererObject()
		{
			return render;
		}
	
	}



	
