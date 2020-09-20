package Diablo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends Game{


	
	//static int genericChar.picRank = 6;
	
	static Draw draw = new Draw(windowX, windowY, list);

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
	//static Character genericChar;
	//Character genericChar
	public static void getDirection(Character genericChar) {
	
		  int differenceX = Math.abs(genericChar.centerX-genericChar.clickedX);
		  int differenceY = Math.abs(genericChar.centerY-genericChar.clickedY);
		  
		  int acceptableDifference = 50;
		  
		  if((differenceX < acceptableDifference) && (genericChar.centerY < genericChar.clickedY)) {
		   genericChar.south = true;
		  }
		  else if((differenceX < acceptableDifference) && (genericChar.centerY > genericChar.clickedY)) {
		   genericChar.north = true;
		  }
		  else if((differenceY < acceptableDifference) && (genericChar.centerX < genericChar.clickedX)) {
		   genericChar.east = true;
		  }
		  else if((differenceY < acceptableDifference) && (genericChar.centerX > genericChar.clickedX)) {
		   genericChar.west = true;
		  }
		  else if((differenceX >= acceptableDifference) && (genericChar.centerY < genericChar.clickedY) && (genericChar.centerX < genericChar.clickedX)) {
		   genericChar.south = true;
		   genericChar.east = true;
		  }
		  else if((differenceX >= acceptableDifference) && (genericChar.centerY < genericChar.clickedY) && (genericChar.centerX > genericChar.clickedX)) {
		   genericChar.south = true;
		   genericChar.west = true;
		  }
		  else if((differenceX >=  acceptableDifference) && (genericChar.centerY > genericChar.clickedY)&& (genericChar.centerX < genericChar.clickedX)) {
		   genericChar.north = true;
		   genericChar.east = true;
		  }
		  else if((differenceX >=  acceptableDifference) && (genericChar.centerY > genericChar.clickedY)&& (genericChar.centerX > genericChar.clickedX)) {
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

		//this.list = list;
	
		//this.genericChar = list.get(0);
		for(int i = 0; i< list.size(); i++) {
		if(list.get(i).directionCheck == true) {
			getDirection(list.get(i));
		}
		}
		draw.updateValue();
		
		/*
		 * 	this.genericChar = list.get(0);
		
		if(genericChar.directionCheck == true) {
			getDirection();
		}
		draw.updateValue(list);
		
		for(int i = 0; i< list.size(); i++) {
			if(list.get(i).directionCheck == true) {
				getDirection(list.get(i));
			}
		draw.updateValue(list);
		}
		 */
		
	
	}

		
	}

class Draw extends JPanel{
	
	Image backGround;
	Image character;
	

	//int genericChar.picRank;

	int windowX;
	int windowY;

	
	static ArrayList <Character> list;
	
	public Draw(int windowX, int windowY, ArrayList <Character> list) {
		
		this.windowX = windowX;
		this.windowY = windowY;
		this.list = list;
		
		ImageIcon icon = new ImageIcon("backGround/backGround.jpg");
		backGround = icon.getImage();
		//System.out.println(backGround.getWidth(null));
		character = new ImageIcon("backGround/character.png").getImage();
		
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
			
			
			 
		
		}
	
}

	
	



