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
	Image explosion;
	
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
		  backGround = new ImageIcon(repository +"bigMap2.png").getImage();
		  explosion = new ImageIcon(repository +"explosion.png").getImage();
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
	
	//static int zoom = 0;
	
	protected void paintComponent(Graphics g) {

			super.paintComponent(g);
			/* 
			 * save this
			 *
			g.drawImage(backGround, 					//name of the image draw 
						0 - zoom, 0 -zoom, 							//x, y on the panel to draw from
						windowX + zoom, windowY + zoom, 				//x, y on the panel to draw to
						list.get(0).x - windowX/2, list.get(0).y - windowY/2 , 	//x, y on the image to get from
						list.get(0).x + windowX/2, list.get(0).y + windowY/2 , 	//x, y on the image to get to
						null);
	
			/*i
			 * f(list.get(0).x % 1000 == 0) {
			 
				tempX  = list.get(0).x - list.get(0).moveSpeed;
			}
			if(list.get(0).y % 1000 == 0) {
				tempY  = list.get(0).y - list.get(0).moveSpeed;
			}
			*/
			
			//infinity map is incomplete
			int tempX = 0;
			int tempY =0;
		
			if(list.get(0).x % 1000 == 0) {
				tempX  = list.get(0).x - list.get(0).moveSpeed;
			}
			if(list.get(0).y % 1000 == 0) {
				tempY  = list.get(0).y - list.get(0).moveSpeed;
			}
			
			
			for(int xOffset = tempX; xOffset < list.get(0).x + windowX; xOffset = xOffset + 690)
			{
				for(int yOffset = tempY; yOffset < list.get(0).y + windowY; yOffset = yOffset + 670)
				{
					g.drawImage(backGround, 					
								xOffset - list.get(0).x, yOffset - list.get(0).y, 							
								xOffset + 690 - list.get(0).x, yOffset + 670 - list.get(0).y, 			
								0, 0,
								690, 670, 
								null);
				}
				
				
			}
			
			
			//System.out.println(list.get(0).x +" " +list.get(0).y);
			//System.out.println(list.get(0).clickedX +" " +list.get(0).clickedY +"\n");
	
		
			
			
			
			for(int i = 1; i< list.size(); i++)
			{
				
				g.drawImage(character, 
							windowX/2 - list.get(0).x+ list.get(i).x, windowY/2- list.get(0).y+ list.get(i).y, 
							windowX/2 - list.get(0).x + list.get(i).x + 80, windowY/2- list.get(0).y + list.get(i).y + 80, 
							list.get(i).picCounter*17, list.get(i).picRank * 17, 
							17*(list.get(i).picCounter+1), list.get(i).picRank * 17 + 17, 
							null);

			}
			
			//g.drawImage(bowIMG, windowX/2 -50, windowY/2 -50, null);
			
			for(int i = 0; i< projectile.size(); i++)
			{
				//System.out.println(projectile.get(i).collision);
				if(projectile.get(i).collision == true)
				{
					temp = explosion;
					projectile.get(i).active = false;
					//projectile.get(i).newClick = false;
					//System.out.println("explosion");
				}
				else
				{
					temp = arrowIMG;
				}
				g.drawImage(temp, 
						windowX/2 - list.get(0).x+ projectile.get(i).x, windowY/2- list.get(0).y+ projectile.get(i).y, 
						windowX/2 - list.get(0).x + projectile.get(i).x + 32, windowY/2- list.get(0).y + projectile.get(i).y +32, 
						0, 0, 
						64, 64, 
						null);
				
			}
		
			
			g.drawImage(character, 
					windowX/2 - 40, windowY/2 - 40, 
					windowX/2 + 40, windowY/2 + 40, 
					list.get(0).picCounter*17, list.get(0).picRank * 17, 
					17*(list.get(0).picCounter+1), list.get(0).picRank * 17 + 17, 
					null);	

		}
	
	
}