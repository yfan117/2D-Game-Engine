<<<<<<< HEAD
<<<<<<<< HEAD:src/Control.java
package Diablo;
========

package BigMess;
>>>>>>>> c763570e1ee493dd1a252837001c30f5c3251ba1:BigMess/Control.java
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
			
			int orginX = list.get(0).x;
			int orginY = list.get(0).y;
			
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
				 
				projectile.add(new Character("arrow", orginX, orginY, destinationX, destinationY));
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
=======
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;
public class Control extends Game implements MouseListener{
// boolean mouseHeld = false;
 @Override
 public void mouseClicked(MouseEvent e) {
  // TODO Auto-generated method stub
  
 }

 @Override
 public void mousePressed(MouseEvent e) {
  // TODO Auto-generated method stub
  // would be cool to have it so while mouse is held, the character moves with mouse
  if(SwingUtilities.isLeftMouseButton(e)) {
   if(outBound(e.getX(), e.getY()) == false) {
    clickedX = e.getX() + x;
    clickedY = e.getY() + y;
    System.out.println(x);
    System.out.println(y);
    newClick = true;
   
    north = false;
    south = false;
    west = false;
    east = false;
   
    directionCheck = true;
   }
  }
  
  if (SwingUtilities.isRightMouseButton(e)) {
   Display.draw.addArrow(new Arrow(Game.windowX/2, Game.windowY/2, e.getX(), e.getY()));
   System.out.println(e.getX()+ ", " + e.getY());
  }
 }

 @Override
 public void mouseReleased(MouseEvent e) {
  // TODO Auto-generated method stub
  //mouseHeld = false;
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
>>>>>>> c763570e1ee493dd1a252837001c30f5c3251ba1
