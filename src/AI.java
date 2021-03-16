package Diablo;

import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

public class AI
{
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
        if (state == "idle")
        {
            if (moveEndTime <= Game.gameTime)
            {
                if (npc.type == "enemy")
                {
                    npc.clickedX = npc.respondX + (rand.nextInt(idleRange * 2) - idleRange);
                    npc.clickedY = npc.respondY + (rand.nextInt(idleRange * 2) - idleRange);
                }

                if (npc.type == "friend")
                {
                    npc.clickedX = game.getEntityList().get(0).x;
                    npc.clickedY = game.getEntityList().get(0).y;
                }

                int x = npc.clickedX;
                int y = npc.clickedY;

                if (npc.move.isObstacles(npc.clickedX, npc.clickedY) == false)
                {
                    npc.north = false;
                    npc.south = false;
                    npc.west = false;
                    npc.east = false;
                    npc.directionCheck = true;

                    npc.newClick = true;

                    npc.clickedX = Math.round(npc.clickedX / 5) * 5;
                    npc.clickedY = Math.round(npc.clickedY / 5) * 5;

                    npc.move.nodeIndex = 1;
                    npc.move.checkPoint = new ArrayList<Node>();
                    npc.move.usedGrid = new ArrayList<Node>();
                    npc.move.checkPoint.add(new Node(npc.x, npc.y));
                    npc.move.pathFind();
                    npc.hasPath = true;
                }
                moveEndTime = Game.gameTime + 30;
            }
        }
    }
}
