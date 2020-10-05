package Diablo;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.SwingUtilities;

public class Control extends Game implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(SwingUtilities.isLeftMouseButton(e)) {
			
			//System.out.println("left click");
			
			//if(outBound(e.getX(), e.getY(), list.get(0)) == false)
				
			//list.get(0).clickedX = e.getX() + list.get(0).x;
			//list.get(0).clickedY = e.getY() + list.get(0).y;
	
			int eX = e.getX();
			int eY = e.getY();
				
			list.get(0).collision = false;
			//System.out.println("here");
				
			if(eX < centerX ) {
				list.get(0).clickedX = list.get(0).x - (centerX - eX);
			}
			else if(eX > centerX ) {
				list.get(0).clickedX = list.get(0).x + (eX - centerX);
			}
				
			if(eY < centerY ) {
				list.get(0).clickedY = list.get(0).y - (centerY - eY);
			}
			else if(eY > centerY ) {
				list.get(0).clickedY = list.get(0).y + (eY - centerY);
			}
				
			list.get(0).newClick = true;
				
			list.get(0).north = false;
			list.get(0).south = false;
			list.get(0).west = false;
			list.get(0).east = false;

			list.get(0).directionCheck = true;
				
			list.get(0).target = list.get(0);
		}
		
		if (SwingUtilities.isRightMouseButton(e)) {
			int eX = e.getX();
			int eY = e.getY();
			
			int destinationX = eX;
			int destinationY = eY;
			
			int orginX = list.get(0).x;
			int orginY = list.get(0).y;
			
			if(list.get(0).isMelee == false) {
				if(eX < centerX ) {
					destinationX = list.get(0).x - (centerX - eX)*3;	
				}
				else if(eX > centerX ) {
					destinationX = list.get(0).x + (eX - centerX)*3;
					//orginX = orginX + 10;
				}
				if(eY < centerY ) {
					destinationY = list.get(0).y - (centerY - eY)*3;
				}
				else if(eY > centerY ) {
					destinationY = list.get(0).y + (eY - centerY)*3;
					//orginY = orginY + 10;
				}
				try {
					projectile.add(new Character("arrow", destinationX, destinationY, 10));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 }
			else {
				Character temp;
				if(eX < centerX ) {
					destinationX = list.get(0).x - (centerX - eX);	
				}
				else if(eX > centerX ) {
					destinationX = list.get(0).x + (eX - centerX);
					//orginX = orginX + 10;
				}
				if(eY < centerY ) {
					destinationY = list.get(0).y - (centerY - eY);
				}
				else if(eY > centerY ) {
					destinationY = list.get(0).y + (eY - centerY);
					//orginY = orginY + 10;
				}
				try {
					temp = new Character("melee", 0, 0, 0);
					//System.out.println(temp.isCollision(destinationX, destinationY, temp));
					list.get(0).target = list.get(temp.isCollision(destinationX, destinationY, temp));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				list.get(0).clickedX = list.get(0).target.x;
				list.get(0).clickedY = list.get(0).target.y;

				list.get(0).newClick = true;
				
				list.get(0).north = false;
				list.get(0).south = false;
				list.get(0).west = false;
				list.get(0).east = false;
			
				list.get(0).directionCheck = true;
				display.update();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}
