import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends Game{


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
				
			
				slopeX = Math.abs(Math.round((double)(clickedX - centerX)/(clickedY - centerY)));
				slopeY = Math.abs(Math.round((double)(clickedY - centerY)/(clickedX - centerX)));
				
				//System.out.println(slopeX +" " +slopeY);

			}
		});
		frame.setVisible(true);
		

		
	}
	
	public void update(int x, int y) {
		
		draw.updateValue(x, y);
	}

		
	}

class Draw extends JPanel{
	
	static Image backGround;
	static Image spaceShip;
	
	static int x = 0;
	static int y = 0;

	static int windowX;
	static int windowY;
	
	static int centerX = windowX /2 ;
	static int centerY = windowY /2 ;
	
	public Draw(int windowX, int windowY) {
		
		this.windowX = windowX;
		this.windowY = windowY;
		
		ImageIcon icon = new ImageIcon("backGround/backGround.jpg");
		backGround = icon.getImage();
		spaceShip = new ImageIcon("backGround/spaceShip.png").getImage();
		
	}
	
	public void updateValue(int x, int y) {
		this.x = x;
		this.y = y;
		
		//System.out.println(x +" "+y);
		
		repaint();
	}
	
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		//System.out.println("here");
	
			g.drawImage(backGround, 0, 0, windowX, windowY, x, y, x + windowX, y + windowY, null);
			

			g.drawImage(spaceShip, windowX/2-78/2, windowY/2-74/2, null);
			

		
		}
	
}

	
	



