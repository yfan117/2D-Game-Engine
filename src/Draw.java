package Diablo;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class Draw extends JPanel{
	
	Image backGround;
	Image Entity;
	Image arrowIMG;
	Image bowIMG;
	Image temp;
	Image explosion;
	Image damageEntity;
	Image darkness;
	Image weapon;
	Image death;
	
	// private Player player;
	//int genericChar.picRank;

	int windowX;
	int windowY;

	
	
	static ArrayList <Entity> list;
	static ArrayList<Entity> projectile;
	
	public Draw(String repository, int windowX, int windowY, ArrayList<Entity> list, ArrayList<Entity> projectile) {
		
		this.list = list;
		this.projectile = projectile;

		this.windowX = windowX;
		this.windowY = windowY;
		  backGround = new ImageIcon(repository +"bigMap2.png").getImage();
		  explosion = new ImageIcon(repository +"explosion.png").getImage();
		  Entity = new ImageIcon(repository +"character.png").getImage();
		  damageEntity = new ImageIcon(repository +"damageEntity.png").getImage();
		  arrowIMG = new ImageIcon(repository +"arrow2.png").getImage();
		  darkness = new ImageIcon(repository +"darkness.png").getImage();
		  weapon = new ImageIcon(repository +"weapon.png").getImage();
		  death = new ImageIcon(repository +"death.png").getImage();


		
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
				if(list.get(i).visible == true)
				{
					if(list.get(i).tookDamage == true)
					{
						temp = damageEntity;
					}
					else
					{
						temp = Entity;
					}
					
					g.drawImage(temp, 
								windowX/2 - list.get(0).x+ list.get(i).x, windowY/2- list.get(0).y+ list.get(i).y, 
								windowX/2 - list.get(0).x + list.get(i).x + 80, windowY/2- list.get(0).y + list.get(i).y + 80, 
								list.get(i).picCounter*17, list.get(i).picRank * 17, 
								17*(list.get(i).picCounter+1), list.get(i).picRank * 17 + 17, 
								null);
					
					g.setColor(Color.BLACK);
					g.drawRect(windowX/2 - list.get(0).x+ list.get(i).x-5, windowY/2- list.get(0).y+ list.get(i).y+-20, 100, 5);
					g.setColor(Color.RED);
					g.fillRect(windowX/2 - list.get(0).x+ list.get(i).x-5, windowY/2- list.get(0).y+ list.get(i).y+-20, list.get(i).hp, 5);
				
					list.get(i).tookDamage = false;
				}
			}
			
			//g.drawImage(bowIMG, windowX/2 -50, windowY/2 -50, null);
			
			for(int i = 0; i< projectile.size(); i++)
			{
				//System.out.println(projectile.get(i).collision);
				if(projectile.get(i).collision == true)
				{
					//Entity.collider.hit(2);
					temp = explosion;
					projectile.get(i).active = false;
				//	System.out.println("hit");
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
		
			/*
			g.drawImage(darkness, 
						0, 0, 
						windowX, windowY,  
						null);
			*/
			
			if(list.get(0).tookDamage == true)
			{
				temp = damageEntity;
			}
			else
			{
				temp = Entity;
			}
			list.get(0).tookDamage = false;
			g.drawImage(temp, 
					windowX/2 - 40, windowY/2 - 40, 
					windowX/2 + 40, windowY/2 + 40, 
					list.get(0).picCounter*17, list.get(0).picRank * 17, 
					17*(list.get(0).picCounter+1), list.get(0).picRank * 17 + 17, 
					null);
			
			/*
			g.drawImage(weapon, 
					windowX/2 - 40, windowY/2 - 40, 
					windowX/2 + 40, windowY/2 + 40, 
					0, 0, 
					0, 17, 
					null);
			*/
			int hp = list.get(0).hp;
			if(list.get(0).hp <= 0)
			{
				hp = 0;
				
			}
			g.setColor(Color.BLACK);
			g.drawRect(25,windowY - 100, 200, 20);
			g.setColor(Color.RED);
			g.fillRect(25, windowY - 100, hp * 2, 20);
			
			
	
					
			g.setColor(Color.BLACK);
			g.drawRect(windowX/2 - list.get(0).x+100,windowY/2- list.get(0).y+ 100, 200, 200);
			g.setColor(Color.BLACK);
			g.fillRect(windowX/2 - list.get(0).x+100,windowY/2- list.get(0).y+ 100, 200, 200);
			
			if(hp == 0)
			{
				g.drawImage(death, 
							0, 0, 
							windowX, windowY,  
							null);
			}
			
			for(int i = 0; i < list.get(0).move.checkPoint.size(); i++)
			{
				
				
				if(i + 1 == list.get(0).move.checkPoint.size())
				{
					break;
				}
				g.drawLine( windowX/2 - list.get(0).x + list.get(0).move.checkPoint.get(i).x, 
							windowY/2 - list.get(0).y + list.get(0).move.checkPoint.get(i).y, 
							windowX/2 - list.get(0).x + list.get(0).move.checkPoint.get(i + 1).x, 
							windowY/2 - list.get(0).y + list.get(0).move.checkPoint.get(i + 1).y);
			}
			
			
			/*
			g.setColor(Color.BLACK);
			g.drawLine( windowX/2 - list.get(0).x + list.get(0).move.tempX, 
					windowY/2 - list.get(0).y + list.get(0).move.tempY, 
					windowX/2 - list.get(0).x + list.get(0).move.tempX + 50, 
					windowY/2 - list.get(0).y + list.get(0).move.tempY);
			
			*/
		}
	
}
