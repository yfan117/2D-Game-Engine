<<<<<<< HEAD
<<<<<<<< HEAD:src/Draw.java
package Diablo;


import java.awt.Color;
========

package BigMess;
>>>>>>>> c763570e1ee493dd1a252837001c30f5c3251ba1:BigMess/Draw.java
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
				g.setColor(Color.BLACK);
				g.drawRect(windowX/2 - list.get(0).x+ list.get(i).x-5, windowY/2- list.get(0).y+ list.get(i).y+-20, 100, 5);
				g.setColor(Color.RED);
				g.fillRect(windowX/2 - list.get(0).x+ list.get(i).x-5, windowY/2- list.get(0).y+ list.get(i).y+-20, list.get(i).getHP(), 5);
				if (list.get(i).getHP() <= 0){
					list.remove(i);
				}
			}
			
			//g.drawImage(bowIMG, windowX/2 -50, windowY/2 -50, null);
			
			for(int i = 0; i< projectile.size(); i++)
			{
				//System.out.println(projectile.get(i).collision);
				if(projectile.get(i).collision == true)
				{
					//Character.collider.hit(2);
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
		
			
			g.drawImage(character, 
					windowX/2 - 40, windowY/2 - 40, 
					windowX/2 + 40, windowY/2 + 40, 
					list.get(0).picCounter*17, list.get(0).picRank * 17, 
					17*(list.get(0).picCounter+1), list.get(0).picRank * 17 + 17, 
					null);	
			g.setColor(Color.BLACK);
			g.drawRect(25, 650, 200, 20);
			g.setColor(Color.RED);
			g.fillRect(25, 650, 200, 20);

		}
	
<<<<<<<< HEAD:src/Draw.java
}
========
	
>>>>>>>> c763570e1ee493dd1a252837001c30f5c3251ba1:BigMess/Draw.java
=======
import java.awt.Image;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class Draw extends JPanel{
 
 private Player player;
 Image backGround;
 Image character;
 Image arrowIMG;
 Image bowIMG;
 int x = 0;
 int y = 0;
 int picRank;

 int windowX;
 int windowY;
 
 int picCounter = 0;
 int timeCounter = 0;
 
 static List<Arrow> arrows = new ArrayList<Arrow>();
 
 public void addArrow(Arrow arrow) { 
  arrows.add(arrow);
  this.repaint();
 }
 
 public Draw(int windowX, int windowY, Player _player) {
  player = _player;
   
  this.windowX = windowX;
  this.windowY = windowY;
  
  backGround = new ImageIcon(Game.root + "/resources/backGround/backGround.jpg").getImage();
  character = new ImageIcon(Game.root + "/resources/character.png").getImage();
  arrowIMG = new ImageIcon(Game.root + "/resources/arrow2.png").getImage();
  bowIMG = new ImageIcon(Game.root + "/resources/bow.png").getImage();
 }
 
 /*
 public int getPicRank() {
  return this.picRank;
 }
 */
 
 public void updateValue(int x, int y, int picRank) {
  this.picRank = picRank;
  int mouseXTest;
  int mouseYTest;
  
  if((this.x != x) || (this.y != y)) {
   
   timeCounter ++;
   
   if(timeCounter == 5) {
    timeCounter = 0;
    picCounter ++;
    
    if(picCounter == 4) {
     picCounter = 0;
    }
   }
   /*
    * save this for testing purpose
   if((this.x > x) && ( this.y == y)) {
    this.picRank = 4;
    picCounter = 0;
   }
   else if((this.x < x) && ( this.y == y)) {
    this.picRank = 0;
    picCounter = 0;
   }
   else if((this.x == x) && ( this.y < y)) {
    this.picRank = 6;
    picCounter = 0;
   }
   else if((this.x == x) && ( this.y > y)) {
    this.picRank = 2;
    picCounter = 0;
   }
   */
  }
  else {
   picCounter = 0;
  }

  this.x = x;
  this.y = y;
  
  repaint();
 }
 
 protected void paintComponent(Graphics g) {
  
  super.paintComponent(g);
 
   g.drawImage(backGround,      //name of the image draw 
      0, 0,        //x, y on the panel to draw from
      windowX, windowY,     //x, y on the panel to draw to
      x - windowX/2, y - windowY/2 ,  //x, y on the image to get from
      x + windowX/2, y + windowY/2 ,  //x, y on the image to get to
      null);
   
   g.drawImage(player.getImage(), 
      windowX/2 - 50, windowY/2 - 50, 
      windowX/2 + 50, windowY/2 + 50, 
      picCounter*17, picRank * 17, 
      17*(picCounter+1), picRank * 17 + 17, 
      null);
   
   List<Arrow> arrow = player.getArrows();
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
>>>>>>> c763570e1ee493dd1a252837001c30f5c3251ba1
