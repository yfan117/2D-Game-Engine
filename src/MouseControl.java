package Diablo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;
public class MouseControl extends Game implements MouseListener {

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
				
				list.get(0).clickedX =Math.round(list.get(0).clickedX/5)*5;
				list.get(0).clickedY =Math.round(list.get(0).clickedY/5)*5;
				if(Movement.isObstacle(list.get(0).clickedX, list.get(0).clickedY) == false)
				{
					list.get(0).newClick = true;
					
					list.get(0).north = false;
					list.get(0).south = false;
					list.get(0).west = false;
					list.get(0).east = false;
					
					
					list.get(0).directionCheck = true;
					
					list.get(0).target = list.get(0);

					//System.out.println(Math.sqrt(Math.pow(list.get(0).clickedX - 105, 2)+Math.pow(list.get(0).clickedY - 95, 2)));
					//System.out.println(Math.sqrt(Math.pow(list.get(0).clickedX - 95, 2)+Math.pow(list.get(0).clickedY - 95, 2)));

					
					
					list.get(0).move.nodeIndex = 1;
					list.get(0).move.checkPoint = new ArrayList<Node>();
					list.get(0).move.usedGrid   = new ArrayList<Node>();
					list.get(0).move.checkPoint.add(new Node(list.get(0).x, list.get(0).y));
					list.get(0).move.pathFind();
					list.get(0).hasPath = true;
				}
				
		
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
			
			
			
			if(list.get(0).isMelee == false)
			{
	
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
					projectile.add(new Entity("arrow", destinationX, destinationY, 10));
				/*
					projectile.get(projectile.size()-1).move.nodeIndex = 1;
					projectile.get(projectile.size()-1).move.checkPoint = new ArrayList<Node>();
					projectile.get(projectile.size()-1).move.usedGrid   = new ArrayList<Node>();
					projectile.get(projectile.size()-1).move.checkPoint.add(new Node(list.get(0).x, list.get(0).y));
					projectile.get(projectile.size()-1).move.pathFind();
				*/	 
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
			 }
			else
			{
				Entity temp;
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
					temp = new Entity("melee", 0, 0, 0);
					//System.out.println(temp.isCollision(destinationX, destinationY, temp));
					list.get(0).target = list.get(temp.move.isCollision(destinationX, destinationY, temp));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				list.get(0).clickedX =Math.round(list.get(0).target.x/5)*5;
				list.get(0).clickedY =Math.round(list.get(0).target.y/5)*5;
				
				list.get(0).move.nodeIndex = 1;
				list.get(0).move.checkPoint = new ArrayList<Node>();
				list.get(0).move.usedGrid   = new ArrayList<Node>();
				list.get(0).move.checkPoint.add(new Node(list.get(0).x, list.get(0).y));
				list.get(0).move.pathFind();
			
				//list.get(0).clickedX = list.get(0).target.x;
				//list.get(0).clickedY = list.get(0).target.y;
				

				list.get(0).newClick = true;
				
				list.get(0).north = false;
				list.get(0).south = false;
				list.get(0).west = false;
				list.get(0).east = false;
			
				list.get(0).directionCheck = true;
				//display.update();
				
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
