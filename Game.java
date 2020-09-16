
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel{
	
	static Image backGround;
	static Image spaceShip;
	
	static int windowX = 1000;
	static int windowY = 700;
	
	static int centerX = windowX /2 ;
	static int centerY = windowY /2 ;
	
	static int x = 0;
	static int y = 0;
	static int fps = 30;
	
	static int clickedX = -99;
	static int clickedY = -99;
	
	static Game test = new Game();
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setSize(windowX, windowY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		
		
		frame.add(test);
		ImageIcon icon = new ImageIcon("backGround/backGround.jpg");
		backGround = icon.getImage();
		spaceShip = new ImageIcon("backGround/spaceShip.png").getImage();
		
		//repaint();
		frame.setVisible(true);
		test.addMouseListener(new MouseAdapter() {// provides empty implementation of all
            // MouseListener`s methods, allowing us to
            // override only those which interests us
			@Override //I override only one method for presentation
				public void mousePressed(MouseEvent e) {
				
				clickedX = e.getX() + x;
				clickedY = e.getY() + y;
				
				//System.out.println(clickedX + "," + clickedY);
				//test.update(clickedX, clickedY);
			}
		});
		
		
		test.gameLoop();
		
		
		
	}
	
	public void gameLoop() {
		int timer = 1000 / fps;
		while(true)
		{
		
		try {
			Thread.sleep(timer);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(clickedX + "," + clickedY);
		test.update();
		repaint();
		
		}
	}
	
	public void update() {
		
		//while((clickedX != centerX) || (clickedY != centerY)) {
		if((clickedX != -99)||(clickedY != -99))
		{
			if(clickedX < centerX) {
				centerX--;
				x--;
				
			}
			else if(clickedX > centerX) {
				centerX++;
				x++;
				
			}
			
			if(clickedY < centerY) {
				centerY--;
				y--;
			
			}
			else if(clickedY > centerY) {
				centerY++;
				y++;
				
			}
		}
		
		if((clickedX == centerX)&&(clickedY == centerY)) {
			clickedX = -99;
			clickedY = -99;
			
		}
		
			
			
			
			
		//}
		
		
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
			
			g.drawImage(backGround, 0, 0, windowX, windowY, x, y, x + windowX, y + windowY, null);
			
			//g.drawRect(clickedX, clickedY, 500, 500);
			g.drawImage(spaceShip, windowX/2-78/2, windowY/2-74/2, null);
			
			
			//g.drawLine(centerX, centerY, clickX, ClickY);
			
			//System.out.println(x +" " +y);
		
		}
		//g.drawImage(backGround, 0, 0, null);
		
		//
		
	}
	
	



