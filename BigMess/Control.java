package BigMess;
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
			{
				
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
		
			}
		}
		
		if (SwingUtilities.isRightMouseButton(e)) 
		{	
			int eX = e.getX();
			int eY = e.getY();
			
			int destinationX = eX;
			int destinationY = eY;
			
			if(eX < centerX ) {
				destinationX = list.get(0).x - (centerX - eX)*3;
			}
			else if(eX > centerX ) {
				destinationX = list.get(0).x + (eX - centerX)*3;
			}
			
			if(eY < centerY ) {
				destinationY = list.get(0).y - (centerY - eY)*3;
			}
			else if(eY > centerY ) {
				destinationY = list.get(0).y + (eY - centerY)*3;
			}
			
			
			   try {
				 
				projectile.add(new Character("arrow", list.get(0).x, list.get(0).y, destinationX, destinationY));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
