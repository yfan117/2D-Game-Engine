package rpg;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;
import java.awt.MouseInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends Game{


	static Draw draw = new Draw(windowX, windowY);
	//static Draw arrow = new Draw(windowX,windowY);
	//static Graphics gfx = draw.getGraphics();
	public Display(){
		
		JFrame frame = new JFrame();
		//Draw draw = new Draw();
		frame.setSize(windowX, windowY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		
		
		frame.getContentPane().add(draw);
		//frame.getContentPane().add(arrow);
		
	
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
				//shoot();
				draw.addArrow(new Arrow(Game.centerX,Game.centerY, e.getX(), e.getY()));//spaceship.getX(),spaceShip.getY(),e.getX(), e.getY()
			}
		});
		frame.setVisible(true);
		

		
	}
/*	public void shoot() { proabably don't need this
		
		if(draw.arrows.size() > 0) {
			int j = draw.arrows.size() -1;
			System.out.println("shoot");
			System.out.println(draw.arrows.size());
			//Arrow.drawLine(x, y, 500, 500);
			//Arrow.updatePos();
			//draw.arrows.get(j).setX(x+50);
			//update(50,50);
		}
	}*/
	
	public void update(int x, int y) {
		
		draw.updateValue(x, y);
	}

		
	}

class Draw extends JPanel{
	
	static Image backGround;
	static Image spaceShip;
	static Image arrowIMG;
	
	static int x = 0;
	static int y = 0;
	public int newMouseX;
	public int newMouseY;
	public int newX;
	public int newY;
	
	static int windowX;
	static int windowY;
	
	static int centerX = windowX /2 ;
	static int centerY = windowY /2 ;
	static int test1;//=(windowX/2-78/2);
	static List<Arrow> arrows = new ArrayList<Arrow>();
	public void addArrow(Arrow arrow) { 
		arrows.add(arrow);
		
		
		this.repaint();
	}
	public Draw(int windowX, int windowY) {
		
		this.windowX = windowX;
		this.windowY = windowY;
		
		ImageIcon icon = new ImageIcon("backGround/backGround.jpg");
		backGround = icon.getImage();
		spaceShip = new ImageIcon("backGround/spaceShip.png").getImage();
		arrowIMG = new ImageIcon("backGround/arrow2.png").getImage();
		
	}
	
	public void updateValue(int x, int y) {
		this.x = x;
		this.y = y;
	//	updateArrows();
		//System.out.println(x +" "+y);
		
		repaint();
	}

	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//System.out.println("here");				
			g.drawImage(backGround, 0, 0, windowX, windowY, x, y, x + windowX, y + windowY, null);
			g.drawImage(spaceShip, windowX/2-78/2, windowY/2-78/2, null);
			Graphics2D g2d = (Graphics2D) g;
		List<Arrow> arrow = Sprite.getArrows();
		if (arrows.size() > 0) {
			for(Arrow a : arrows) {
			//System.out.println("Drawing Arrow");
			//System.out.println(MouseInfo.getPointerInfo().getLocation().x);
			//newMouseX = MouseInfo.getPointerInfo().getLocation().x;
			//newMouseY = MouseInfo.getPointerInfo().getLocation().y;
			//rate = (newMouseX - newMouseY) / (newMouseY - y);
			//g.drawImage(arrowIMG, test1, test1, null);
			//a.setX(test1);
			//a.setY(test1);
		//	newX = a.getX();
		//	//System.out.println(newX);
			//System.out.println("boop");
		//	System.out.println(arrows.size());
			//System.out.println(a.getMouseX());
			//System.out.println(arrows.size());
			//g.drawLine(x, y, newMouseX, newMouseY);
			//g.drawImage(arrowIMG, a.setX(a.getY() + 1), a.setY(a.getX() + 1), null);
			//a.setX(a.getX() - 1);
			//a.setY(a.getY() - 1);
		//	newX = a.getX();
		//	newY = a.getY();
			g.drawImage(arrowIMG, a.getX(), a.getY(), null);
			updateArrows();
			repaint();
			}
		}
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
					arrow.setX(centerX);
					arrow.setY(centerY);
					arrows.remove(i);
					
				}
			}
		}
	public void CheckCollisions() {
		//Rectangle playerBounds = player.getBounds();
		//for (Enemy enemy : enemies){
		//Rectangle enemyBounds = enemy.getBounds();
		//if(playerBounds.intersects(enemyBounds)){
		//enemy.setVisible(false);
		//}
		//}
		//List<Arrows> ar = Sprite.getArrows();
		//for (Arrow a : ar){
		//Rectangle arrowBounds = a.getBounds
		//for (Enemy enemy : enemies){
		//Rectangle enemyBounds = enemy.getBounds();
		//if(arrowBounds.intersects(enemyBounds)){
		//enemy.setVisible(false);
		//make it so when an enemy visible 
		
	}
}
	
	



