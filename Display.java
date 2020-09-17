import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends Game{

	static boolean north;
	static boolean south;
	static boolean west;
	static boolean east;
	
	static int picRank;
	
	static Draw draw = new Draw(windowX, windowY);

	public Display(){
		
		JFrame frame = new JFrame();
		//Draw draw = new Draw();
		frame.setSize(windowX, windowY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		
		
		frame.getContentPane().add(draw);
		
	
		//control.mouseClicked();
		
		//repaint();
		draw.addMouseListener(new MouseAdapter() {
			//@Override 
				public void mousePressed(MouseEvent e) {
				
				clickedX = e.getX() + x;
			
				clickedY = e.getY() + y;
				
				newClick = true;
				
				north = false;
				south = false;
				west = false;
				east = false;
			
				slopeX = Math.abs(Math.round((double)(clickedX - centerX)/(clickedY - centerY)));
				slopeY = Math.abs(Math.round((double)(clickedY - centerY)/(clickedX - centerX)));
				
				//System.out.println(slopeX +" " +slopeY);
				getDirection();

			}
		});
		frame.setVisible(true);
		

		
	}
	
	public static void getDirection() {
		
		int differenceX = Math.abs(centerX-clickedX);
		int differenceY = Math.abs(centerY-clickedY);
		
		if((differenceX < 75) && (centerY < clickedY)) {
			
			south = true;
		}
		else if((differenceX < 75) && (centerY > clickedY)) {
			
			north = true;
		}
		else if((differenceY < 75) && (centerX < clickedX)) {
			
			east = true;
		}
		else if((differenceY < 75) && (centerX > clickedX)) {
			
			west = true;
		}
		else if((differenceX >= 75) && (centerY < clickedY) && (centerX < clickedX)) {
			
			south = true;
			east = true;
		}
		else if((differenceX >= 75) && (centerY < clickedY) && (centerX > clickedX)) {
			
			
			south = true;
			west = true;
		}
		else if((differenceX >=  75) && (centerY > clickedY)&& (centerX < clickedX)) {
			
			north = true;
			east = true;
		}
		else if((differenceX >=  75) && (centerY > clickedY)&& (centerX > clickedX)) {
			
			north = true;
			west = true;
			
		}
	
		/*
		System.out.println();
		System.out.println("north: "+north);
		System.out.println("south: "+south);
		System.out.println("west: "+west);
		System.out.println("east: "+east);
		*/
		if((north == true) && (west == true)) {
			picRank = 3;
		}
		else if((north == true) && (east == true)) {
			picRank = 1;
		}
		else if((south == true) && (west == true)) {
			picRank = 5;
		}
		else if((south == true) && (east == true)) {
			picRank = 7;
		}
		else if(north == true) {
			
			picRank = 2;
		}
		else if(south == true) {
			
			picRank = 6;
		}
		else if(west == true) {
			picRank = 4;
		}
		else if(east == true) {
			picRank = 0;
		}
		
	}
	
	public void update(int x, int y) {
		
		draw.updateValue(x, y, picRank);
		
		
		
	
		
	
	}

		
	}

class Draw extends JPanel{
	
	static Image backGround;
	static Image spaceShip;
	
	static int x = 0;
	static int y = 0;
	static int picRank;

	static int windowX;
	static int windowY;
	
	static int centerX = windowX /2 ;
	static int centerY = windowY /2 ;
	
	static int picCounter = 0;
	static int timeCounter = 0;
	
	public Draw(int windowX, int windowY) {
		
		this.windowX = windowX;
		this.windowY = windowY;
		
		
		ImageIcon icon = new ImageIcon("backGround/backGround.jpg");
		backGround = icon.getImage();
		spaceShip = new ImageIcon("backGround/character.png").getImage();
		
	}
	
	public void updateValue(int x, int y, int picRank) {
		
		if((this.x != x) || (this.y != y)) {
			
			timeCounter ++;
			
			if(timeCounter == 5) {
				timeCounter = 0;
				picCounter ++;
				
				if(picCounter == 3) {
					picCounter = 0;
				}
			}
		}
		
		this.x = x;
		this.y = y;
		this.picRank = picRank;
		//System.out.println(x +" "+y);
	
		
		
		repaint();
	}
	
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		//System.out.println("here");
	
			g.drawImage(backGround, 0, 0, windowX, windowY, x, y, x + windowX, y + windowY, null);
			
			g.drawImage(spaceShip, windowX/2 - 50, windowY/2 - 50, windowX/2 + 50, windowY/2 + 50, picCounter*17, picRank * 17, 17*(picCounter+1), picRank * 17 + 17, null);

			//g.drawImage(spaceShip, windowX/2-78/2, windowY/2-74/2, null);
			

		
		}
	
}

	
	



