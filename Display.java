import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends Game{

	
	
	static int picRank = 6;
	
	static Draw draw = new Draw(windowX, windowY);

	public Display(){
		
		JFrame frame = new JFrame();
		//Draw draw = new Draw();
		frame.setSize(windowX, windowY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		
		
		frame.getContentPane().add(draw);
		
	
		Control control = new Control();
		draw.addMouseListener(control);
		
	
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
		
		directionCheck = false;
		
	}
	
	
	public void update(int x, int y) {
		
		if(directionCheck == true) {
			getDirection();
		}
		draw.updateValue(x, y, picRank);
		//picRank = draw.getPicRank();
		
		
	
		
	
	}

		
	}

class Draw extends JPanel{
	
	Image backGround;
	Image character;
	
	int x = 0;
	int y = 0;
	int picRank;

	int windowX;
	int windowY;

	
	int picCounter = 0;
	int timeCounter = 0;
	
	public Draw(int windowX, int windowY) {
		
		this.windowX = windowX;
		this.windowY = windowY;
		
		
		ImageIcon icon = new ImageIcon("backGround/backGround.jpg");
		backGround = icon.getImage();
		character = new ImageIcon("backGround/character.png").getImage();
		
	}
	/*
	public int getPicRank() {
		return this.picRank;
	}
	*/
	public void updateValue(int x, int y, int picRank) {
		this.picRank = picRank;
		
		
		
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
		
		//System.out.println(x +" "+y);
	
		
		
		repaint();
	}
	
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		//System.out.println("here");
	
			g.drawImage(backGround, 0, 0, windowX, windowY, x, y, x + windowX, y + windowY, null);
			
			g.drawImage(character, windowX/2 - 50, windowY/2 - 50, windowX/2 + 50, windowY/2 + 50, picCounter*17, picRank * 17, 17*(picCounter+1), picRank * 17 + 17, null);

			//g.drawImage(spaceShip, windowX/2-78/2, windowY/2-74/2, null);
			

		
		}
	
}

	
	



