import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class Draw extends JPanel{
	
	Image backGround;
	Image character;
	Image arrowIMG;
	Image bowIMG;
	// private Player player;
	//int genericChar.picRank;

	int windowX;
	int windowY;
	static List<Arrow> arrows = new ArrayList<Arrow>();
	 public void addArrow(Arrow arrow) { 
		  arrows.add(arrow);
		  this.repaint();
		 }
	
	
	
	public Draw(int windowX, int windowY) {
		//player = _player;
		this.windowX = windowX;
		this.windowY = windowY;
		  backGround = new ImageIcon("resources/backGround/backGround.jpg").getImage();
		  character = new ImageIcon("resources/character.png").getImage();
		  arrowIMG = new ImageIcon("resources/arrow2.png").getImage();
		  bowIMG = new ImageIcon("resources/bow.png").getImage();
		
	//	ImageIcon icon = new ImageIcon("backGround/backGround.jpg");
	//	backGround = icon.getImage();
	//	//System.out.println(backGround.getWidth(null));
	//	character = new ImageIcon("backGround/character.png").getImage();
		
	}


	static ArrayList <Character> list;
	
	public void updateValue(ArrayList <Character> list) {
		
		

		this.list = list;
		
		for(int i = 0; i< list.size(); i++) {
			
		if((list.get(i).x != list.get(i).preX) || (list.get(i).y != list.get(i).preY)) 
		{
			
			list.get(i).timeCounter ++;
			
			if(list.get(i).timeCounter == 5)
			{
				list.get(i).timeCounter = 0;
				list.get(i).picCounter ++;
				
				if(list.get(i).picCounter == 4) 
				{
					list.get(i).picCounter = 0;
				}
			}
		
		}
		else {
			list.get(i).picCounter = 0;
		}
		
			list.get(i).preX = list.get(i).x;
			list.get(i).preY = list.get(i).y;
		}
	
		
		
		repaint();
	}
	
	protected void paintComponent(Graphics g) {
		//System.out.println("DRAWING");
		super.paintComponent(g);
		//System.out.println(list.get(0).x );
	/*	
			g.drawImage(backGround, 					//name of the image draw 
						0, 0, 							//x, y on the panel to draw from
						windowX, windowY, 				//x, y on the panel to draw to
						list.get(0).x - windowX/2, list.get(0).y - windowY/2 , 	//x, y on the image to get from
						list.get(0).x + windowX/2, list.get(0).y + windowY/2 , 	//x, y on the image to get to
						null);
			
			
			g.drawImage(character, 
						windowX/2 - 50, windowY/2 - 50, 
						windowX/2 + 50, windowY/2 + 50, 
						list.get(0).picCounter*17, list.get(0).picRank * 17, 
						17*(list.get(0).picCounter+1), list.get(0).picRank * 17 + 17, 
						null);
			/*
			
		
			g.drawImage(character, 
					list.get(0).x- 50, list.get(0).y - 50, 
					list.get(0).x + 50, list.get(0).y + 50, 
					list.get(0).picCounter*17, list.get(0).picRank * 17, 
					17*(list.get(0).picCounter+1), list.get(0).picRank * 17 + 17, 
					null);
	*/		
			g.drawImage(backGround, 					//name of the image draw 
						0, 0, 							//x, y on the panel to draw from
						windowX, windowY, 				//x, y on the panel to draw to
						list.get(0).x - windowX/2, list.get(0).y - windowY/2 , 	//x, y on the image to get from
						list.get(0).x + windowX/2, list.get(0).y + windowY/2 , 	//x, y on the image to get to
						null);
		
		
			g.drawImage(character, 
						windowX/2 - 50, windowY/2 - 50, 
						windowX/2 + 50, windowY/2 + 50, 
						list.get(0).picCounter*17, list.get(0).picRank * 17, 
						17*(list.get(0).picCounter+1), list.get(0).picRank * 17 + 17, 
						null);			
			for(int i = 1; i< list.size(); i++)
			{
				g.drawImage(character, 
							windowX/2 - list.get(0).x+ list.get(i).x, windowY/2- list.get(0).y+ list.get(i).y, 
							windowX/2 - list.get(0).x + list.get(i).x + 100, windowY/2- list.get(0).y + list.get(i).y +100, 
							list.get(i).picCounter*17, list.get(i).picRank * 17, 
							17*(list.get(i).picCounter+1), list.get(i).picRank * 17 + 17, 
							null);

			}
			repaint();
			List<Arrow> arrow = Sprite.getArrows();
			if (arrows.size() > 0) {
				   for(Arrow a : arrows) {
					   g.drawImage(arrowIMG,Game.windowX/2, Game.windowY/2, null);
					   g.drawLine(Game.windowX/2, Game.windowY/2, a.getMouseX(), a.getMouseY());
					   updateArrows();
					   repaint();
				   }
			}
			g.drawImage(bowIMG, windowX/2 - 50, windowY/2 - 50, null);
			
		}
	public void updateArrows() {
		for (int i = 0; i < arrows.size(); i++) {
			Arrow arrow = arrows.get(i);
			if (arrow.isVisible()){
				arrow.move();
			}
			else {
				System.out.println("remove");
			    arrow.setExists(false);
			    // arrow.setX(Game.centerX);
			    //arrow.setY(Game.centerY);
			    arrows.remove(i);
			    System.out.println(arrows.size());
			   }
		}
	}
	
	
}