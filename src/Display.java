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
		frame.setSize(game.windowX + 16, game.windowY + 39);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
	
		MouseControl mouseControl = new MouseControl(this.game);
		render.addMouseListener(mouseControl);
		
		render.addMouseMotionListener(mouseControl);
		
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
		pause=new PauseScreen(game, this);
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
				if(game.map.isMapMusicPlaying())
					game.map.pauseMapMusic();
				currentPanel = 0;
				break;
			case 1: //game state
				cards.show(visiblePanel, "game");
				try
				{
					if (!game.map.isMapMusicPlaying())
						game.map.startMapMusic();
				}catch(Exception ex){game.map.startMapMusic();}
				if(mainmenu.isMusicPlaying())
					mainmenu.pauseMusic();
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

	public static void getDirection(Entity current) {

		if(current.move != null)
		{
		if((current.destinationX != 0)&&(current.destinationY != 0))
		{
			current.move.getDirection(current.destinationX, current.destinationY);
		}
		
		if(current.type == "projectile")
		{
			//System.out.println(current.clickedX +" " + current.clickedY);
			current.move.getDirection(current.clickedX, current.clickedY);
//			System.out.println("here");
//			System.out.println(current.moveAngle);
		}
		current.picRank = (int) Math.round(current.moveAngle / 22.5);
		
		if(current.picRank == 16)
		{
			current.picRank = 15;
		}
		
		if(current.picRank <= 0)
		{
			current.picRank = 0;
		}
//		if(current.type == "projectile")
//		{
//			System.out.println(current.picRank);
//			//System.out.println(current.moveAngle);
//
//		}
		}
		
		if(current.type == "projectile")
		{
			if(current.collision == true)
			{
				current.picRank = 16;
				current.move = null;
			}
		}
	
	
	}

	
		public void update() {
	
			for(int i = 0; i< game.getEntityList().size(); i++) 
			{
				//if(list.get(i).directionCheck == true)
				//if(game.getEntityList().get(i).newClick == true) 
				{
					//System.out.println(i);
					getDirection(game.getEntityList().get(i));
				}
			}
			for(int i = 0; i< game.getProjectileList().size(); i++) 
			{
				//if(list.get(i).directionCheck == true)
				//if(game.getEntityList().get(i).newClick == true) 
				{
					//System.out.println(i);
					getDirection(game.getProjectileList().get(i));
				}
			}
		
				render.updateValue();
		
		
			
		}
		
		public Renderer getRendererObject()
		{
			return render;
		}
	}

