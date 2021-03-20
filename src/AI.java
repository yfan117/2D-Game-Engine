package Diablo;

import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

public class AI {
	
	int idleRange = 500;
	Random rand = new Random();
	String state = "idle";
	
	Entity npc;
	
	long moveBeginTime;
	long moveEndTime;
	
	boolean moveReady = true;
	boolean resetScheduled = false;
	Game game;

	
	public AI(final Entity npc, Game game)
	{
		this.npc = npc;
		this.game = game;
		
		
	}
	
	public void update()
	{
		if(state == "idle")
		{
		
			if(moveEndTime <= Game.gameTime)
			{
			
				if(npc.type == "enemy")
				{
					npc.clickedX = npc.respondX + (rand.nextInt(idleRange*2)-idleRange);
					npc.clickedY = npc.respondY + (rand.nextInt(idleRange*2)-idleRange);
				}
				
				if(npc.type == "friend")
				{
					npc.clickedX = game.getEntityList().get(0).x;
					npc.clickedY = game.getEntityList().get(0).y;
				}
				
				
				
				
				int x = npc.clickedX;
				int y = npc.clickedY;
				
				if(npc.move.isObstacles(npc.clickedX , npc.clickedY) == false)
				//if((x <= 0 ) || ( y <= 0) || (game.obsMap[x + y * game.mapWidth] == 1))
				{
					
	
					//npc.clickedX = npc.list.get(0).x - (int)(Math.random()*100);
					//npc.clickedY = npc.list.get(0).y - (int)(Math.random()*100);
					//clickedX = list.get(0).x;
					//clickedY = list.get(0).y;
	
					
					//npc.target = npc.list.get(0);
					
					//npc.target = npc;
	
					//System.out.println(Math.sqrt(Math.pow(npc.clickedX - 105, 2)+Math.pow(npc.clickedY - 95, 2)));
					//System.out.println("here");
	
					npc.clickedX =Math.round(npc.clickedX/5)*5;
					npc.clickedY =Math.round(npc.clickedY/5)*5;
					
					npc.move.nodeIndex = 1;
					npc.move.checkPoint = new ArrayList<Node>();
					npc.move.usedGrid   = new ArrayList<Node>();
					npc.move.checkPoint.add(new Node(npc.x, npc.y));
					npc.move.pathFind();
					npc.hasPath = true;
					npc.state = "run";
					npc.newClick = true;
					npc.newCheckPoint = true;
				}
				moveEndTime = Game.gameTime + 30;
				
				
				
			}
			
	
			
	
			
		}
	}

}
