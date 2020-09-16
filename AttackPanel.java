import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TimerTask;

import javax.swing.JPanel;
import javax.swing.Timer;

public class AttackPanel extends JPanel{ //change angle to arrow
	//int timer = 1000/30;
	int x;
	int y;
	int arrowSpeed;
	int newMouseX;
	int newMouseY;
	int rate;
	int direction = 1;
	private List<Arrow> arrows = new ArrayList<Arrow>();
	public void addArrow(Arrow arrow) { 
		arrows.add(arrow);
		//animation();
		
		this.repaint();
	}
/*	public void animation() {
		
		while (true) {
			try {
				Thread.sleep(1000/30);
              //  @Override
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}}
          
            public void update() {
            	System.out.println("test");
                      Arrow test = arrows.get(0);
					test.setX(test.getX() + direction);
                    if (test.getX() + test.getImageW() > getWidth()) {
                        test.setX(50- getWidth());
                        direction *= -1;
                    } else if (test.getX() < 0) {
                        test.setX(0);
                        direction *= -1;
                    }
                    repaint();
                }	*/
            
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		List<Arrow> arrow = Sprite.getArrow();
		for(Arrow a : arrows) {
			//System.out.println("Drawing Arrow");
			//System.out.println(MouseInfo.getPointerInfo().getLocation().x);
			newMouseX = MouseInfo.getPointerInfo().getLocation().x;
			newMouseY = MouseInfo.getPointerInfo().getLocation().y;
			rate = (newMouseX - newMouseY) / (newMouseY - y);
			g2d.drawImage(a.getImage(), x, y, null);
			System.out.println(a.getX() + ", " + a.getY());
			System.out.println(a.getMouseX());
			System.out.println(arrows.size());
			g.drawLine(x, y, newMouseX, newMouseY);
			//update();
		//	t.start();
/*			arrowSpeed = a.getArrowSpeed();
				for (int i = x; i < newMouseX; i= i +arrowSpeed) {//x2-x1 i should be 
					System.out.println(i);
					x += arrowSpeed;
					y +=arrowSpeed;
				//	for (int j = y; j < newMouseY; j = j +arrowSpeed) {//y2 - y1
				//	y = j;
					//}
					repaint();
				}*/
			}
		}

	
	
}
