package Diablo;

import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

public class AI {
	
	int idleRange = 300;
	Random rand = new Random();
	String state = "idle";
	
	Entity npc;
	
	long moveBeginTime;
	long moveEndTime;
	
	boolean moveReady = true;
	boolean resetScheduled = false;

	
	public AI(final Entity npc)
	{
		this.npc = npc;
		
		
	}
	
	public void update()
	{
		if(state == "idle")
		{
		
			if(moveEndTime <= Game.gameTime)
			{
				npc.clickedX = npc.respondX + rand.nextInt((idleRange*2)-idleRange);
				npc.clickedY = npc.respondY + rand.nextInt((idleRange*2)-idleRange);
				
				npc.north = false;
				npc.south = false;
				npc.west = false;
				npc.east = false;
				npc.directionCheck = true;

				//npc.clickedX = npc.list.get(0).x - (int)(Math.random()*100);
				//npc.clickedY = npc.list.get(0).y - (int)(Math.random()*100);
				//clickedX = list.get(0).x;
				//clickedY = list.get(0).y;



				npc.newClick = true;
				
				//npc.target = npc.list.get(0);
				
				//npc.target = npc;

				//System.out.println(Math.sqrt(Math.pow(npc.clickedX - 105, 2)+Math.pow(npc.clickedY - 95, 2)));
				//System.out.println(Math.sqrt(Math.pow(npc.clickedX - 95, 2)+Math.pow(npc.clickedY - 95, 2)));

				npc.clickedX =Math.round(npc.clickedX/5)*5;
				npc.clickedY =Math.round(npc.clickedY/5)*5;
				
				npc.move.nodeIndex = 1;
				npc.move.checkPoint = new ArrayList<Node>();
				npc.move.usedGrid   = new ArrayList<Node>();
				npc.move.checkPoint.add(new Node(npc.x, npc.y));
				npc.move.pathFind();
				
				moveEndTime = Game.gameTime + 10;
				
				
				
			}
			
	
			
	
			
		}
	}

}
