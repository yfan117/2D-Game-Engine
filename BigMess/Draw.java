package Diablo;
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
	Image temp;
	// private Player player;
	//int genericChar.picRank;

	int windowX;
	int windowY;

	
	
	static ArrayList <Character> list;
	static ArrayList<Character> projectile;
	
	public Draw(String repository, int windowX, int windowY, ArrayList<Character> list, ArrayList<Character> projectile) {
		
		this.list = list;
		this.projectile = projectile;

		this.windowX = windowX;
		this.windowY = windowY;
		  backGround = new ImageIcon(repository +"backGround.jpg").getImage();
		  character = new ImageIcon(repository +"character.png").getImage();
		  arrowIMG = new ImageIcon(repository +"arrow2.png").getImage();
		  bowIMG = new ImageIcon(repository +"bow.png").getImage();


		
	}




	public void updateValue() {
		
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

			super.paintComponent(g);
	
			g.drawImage(backGround, 					//name of the image draw 
						0, 0, 							//x, y on the panel to draw from
						windowX, windowY, 				//x, y on the panel to draw to
						list.get(0).x - windowX/2, list.get(0).y - windowY/2 , 	//x, y on the image to get from
						list.get(0).x + windowX/2, list.get(0).y + windowY/2 , 	//x, y on the image to get to
						null);
		
		
			g.drawImage(character, 
						windowX/2 - 50, windowY/2 -50, 
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
			
			g.drawImage(bowIMG, windowX/2 -50, windowY/2 -50, null);
			
			for(int i = 1; i< projectile.size(); i++)
			{
				
				g.drawImage(arrowIMG, 
						windowX/2 - list.get(0).x+ projectile.get(i).x, windowY/2- list.get(0).y+ projectile.get(i).y, 
						windowX/2 - list.get(0).x + projectile.get(i).x + 32, windowY/2- list.get(0).y + projectile.get(i).y +32, 
						0, 0, 
						64, 64, 
						null);
				
			}

		}
	
	
}