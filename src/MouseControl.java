package Diablo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;
public class MouseControl implements MouseListener {
	private Game game;
	private Movement movement;

	public MouseControl(Game game)
	{
		this.game = game;
		movement = new Movement(game.getEntityList().get(0), game);
	}
	//public MouseControl(Movement movement){this.movement = movement;}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

		if(SwingUtilities.isLeftMouseButton(e)) {

			//System.out.println("left click");

			//if(outBound(e.getX(), e.getY(), game.getEntityList().get(0)) == false)
			{
				//game.getEntityList().get(0).clickedX = e.getX() + game.getEntityList().get(0).x;
				//game.getEntityList().get(0).clickedY = e.getY() + game.getEntityList().get(0).y;

				int eX = e.getX();
				int eY = e.getY();

				game.getEntityList().get(0).collision = false;
				//System.out.println("here");

				if(eX < Game.centerX ) {
					game.getEntityList().get(0).clickedX = game.getEntityList().get(0).x - (Game.centerX - eX);
				}
				else if(eX > Game.centerX ) {
					game.getEntityList().get(0).clickedX = game.getEntityList().get(0).x + (eX - Game.centerX);
				}

				if(eY < Game.centerY ) {
					game.getEntityList().get(0).clickedY = game.getEntityList().get(0).y - (Game.centerY - eY);
				}
				else if(eY > Game.centerY ) {
					game.getEntityList().get(0).clickedY = game.getEntityList().get(0).y + (eY - Game.centerY);
				}

				game.getEntityList().get(0).clickedX =Math.round(game.getEntityList().get(0).clickedX/5)*5;
				game.getEntityList().get(0).clickedY =Math.round(game.getEntityList().get(0).clickedY/5)*5;
				if(movement.isObstacle(game.getEntityList().get(0).clickedX, game.getEntityList().get(0).clickedY) == false)
				{
					game.getEntityList().get(0).newClick = true;

					game.getEntityList().get(0).north = false;
					game.getEntityList().get(0).south = false;
					game.getEntityList().get(0).west = false;
					game.getEntityList().get(0).east = false;


					game.getEntityList().get(0).directionCheck = true;

					game.getEntityList().get(0).target = game.getEntityList().get(0);

					//System.out.println(Math.sqrt(Math.pow(game.getEntityList().get(0).clickedX - 105, 2)+Math.pow(game.getEntityList().get(0).clickedY - 95, 2)));
					//System.out.println(Math.sqrt(Math.pow(game.getEntityList().get(0).clickedX - 95, 2)+Math.pow(game.getEntityList().get(0).clickedY - 95, 2)));



					game.getEntityList().get(0).move.nodeIndex = 1;
					game.getEntityList().get(0).move.checkPoint = new ArrayList<Node>();
					game.getEntityList().get(0).move.usedGrid   = new ArrayList<Node>();
					game.getEntityList().get(0).move.checkPoint.add(new Node(game.getEntityList().get(0).x, game.getEntityList().get(0).y));
					game.getEntityList().get(0).move.pathFind();
					game.getEntityList().get(0).hasPath = true;
				}


			}
		}

		if (SwingUtilities.isRightMouseButton(e))
		{

			int eX = e.getX();
			int eY = e.getY();

			int destinationX = eX;
			int destinationY = eY;

			int orginX = game.getEntityList().get(0).x;
			int orginY = game.getEntityList().get(0).y;



			if(game.getEntityList().get(0).isMelee == false)
			{

				if(eX < Game.centerX ) {
					destinationX = game.getEntityList().get(0).x - (Game.centerX - eX)*3;
				}
				else if(eX > Game.centerX ) {
					destinationX = game.getEntityList().get(0).x + (eX - Game.centerX)*3;
					//orginX = orginX + 10;
				}

				if(eY < Game.centerY ) {
					destinationY = game.getEntityList().get(0).y - (Game.centerY - eY)*3;
				}
				else if(eY > Game.centerY ) {
					destinationY = game.getEntityList().get(0).y + (eY - Game.centerY)*3;
					//orginY = orginY + 10;
				}




			try {
					game.getProjectileList().add(new Entity("arrow", destinationX, destinationY, 10));
				/*
					projectile.get(projectile.size()-1).move.nodeIndex = 1;
					projectile.get(projectile.size()-1).move.checkPoint = new ArrayList<Node>();
					projectile.get(projectile.size()-1).move.usedGrid   = new ArrayList<Node>();
					projectile.get(projectile.size()-1).move.checkPoint.add(new Node(game.getEntityList().get(0).x, game.getEntityList().get(0).y));
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
				if(eX < Game.centerX ) {
					destinationX = game.getEntityList().get(0).x - (Game.centerX - eX);
				}
				else if(eX > Game.centerX ) {
					destinationX = game.getEntityList().get(0).x + (eX - Game.centerX);
					//orginX = orginX + 10;
				}

				if(eY < Game.centerY ) {
					destinationY = game.getEntityList().get(0).y - (Game.centerY - eY);
				}
				else if(eY > Game.centerY ) {
					destinationY = game.getEntityList().get(0).y + (eY - Game.centerY);
					//orginY = orginY + 10;
				}


				try {
					temp = new Entity("melee", 0, 0, 0);
					//System.out.println(temp.isCollision(destinationX, destinationY, temp));
					game.getEntityList().get(0).target = game.getEntityList().get(temp.move.isCollision(destinationX, destinationY, temp));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				game.getEntityList().get(0).clickedX =Math.round(game.getEntityList().get(0).target.x/5)*5;
				game.getEntityList().get(0).clickedY =Math.round(game.getEntityList().get(0).target.y/5)*5;

				game.getEntityList().get(0).move.nodeIndex = 1;
				game.getEntityList().get(0).move.checkPoint = new ArrayList<Node>();
				game.getEntityList().get(0).move.usedGrid   = new ArrayList<Node>();
				game.getEntityList().get(0).move.checkPoint.add(new Node(game.getEntityList().get(0).x, game.getEntityList().get(0).y));
				game.getEntityList().get(0).move.pathFind();

				//game.getEntityList().get(0).clickedX = game.getEntityList().get(0).target.x;
				//game.getEntityList().get(0).clickedY = game.getEntityList().get(0).target.y;


				game.getEntityList().get(0).newClick = true;

				game.getEntityList().get(0).north = false;
				game.getEntityList().get(0).south = false;
				game.getEntityList().get(0).west = false;
				game.getEntityList().get(0).east = false;

				game.getEntityList().get(0).directionCheck = true;
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
