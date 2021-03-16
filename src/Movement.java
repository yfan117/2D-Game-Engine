package Diablo;

import java.io.IOException;
import java.util.ArrayList;

public class Movement
{
    int obsXY = 100;
    int obsSize = 0;

    Entity current;
    Game game;

    ArrayList<Node> checkPoint;
    ArrayList<Node> usedGrid;

    public Movement(Entity current, Game game)
    {
        this.current = current;
        this.game = game;

        checkPoint = new ArrayList<Node>();
        checkPoint.add(new Node(current.x, current.y));

        usedGrid = new ArrayList<Node>();
    }

    int nodeIndex = 0;

    public void keyBoardUpdate(Entity current)
    {
        if (current.moveRight == true)
        {
            if (isObstacles(current.x + 10, current.y) == false)
            {
                current.x = current.x + 10;
            }
        }

        if (current.moveLeft == true)
        {

            if (isObstacles(current.x - 10, current.y) == false)
            {
                current.x = current.x - 10;
            }
        }

        if (current.moveUp == true)
        {
            if (isObstacles(current.x, current.y - 10) == false)
            {
                current.y = current.y - 10;
            }
        }

        if (current.moveDown == true)
        {
            if (isObstacles(current.x, current.y + 10) == false)
            {
                current.y = current.y + 10;
            }
        }
    }

    int obsX;
    int obsY;

    public void update(Entity current)
    {
        current.hasDoneDmage = false;

        isVisible();

        isCollision(current.clickedX, current.clickedY, current);

        if ((current.newClick == true) && (checkPoint.size() > 1))
        {
            if (current.type != "projectile")
            {
                current.clickedX = checkPoint.get(1).x;
                current.clickedY = checkPoint.get(1).y;
            }

            if (current.newCheckPoint == true)
            {
                double deltaX = (double) (current.clickedX - current.x);
                double deltaY = -(double) (current.clickedY - current.y);
                current.slopeY = deltaY / deltaX;
                current.moveAngle = Math.toDegrees(Math.atan2(current.slopeY, nodeIndex));

                if ((deltaX < 0) && (deltaY > 0))
                {
                    current.moveAngle = 180 + current.moveAngle;
                }
                else if ((deltaX < 0) && (deltaY < 0))
                {
                    current.moveAngle = 180 + current.moveAngle;

                }
                else if ((deltaX > 0) && (deltaY < 0))
                {
                    current.moveAngle = 360 + current.moveAngle;
                }

                current.newCheckPoint = false;
            }

            current.slopeX = Math.abs(Math.round((double) (current.clickedX - current.x) / (current.clickedY - current.y)));
            current.slopeY = Math.abs(Math.round((double) (current.clickedY - current.y) / (current.clickedX - current.x)));

            if (current.slopeX > 4)
            {
                current.slopeX = 4;
            }
            if (current.slopeY > 4)
            {
                current.slopeY = 4;
            }

            for (current.moveCounter = 0; current.moveCounter < current.moveSpeed; current.moveCounter++)
            {
                if (current.slopeX > current.slopeY)
                {
                    current.maxSlope = current.slopeX;
                    for (int j = 0; j <= current.slopeX; j++)
                    {
                        updateX(current);

                        if (current.clickedX == current.x)
                        {
                            break;
                        }

                        if (current.moveCounter == current.moveSpeed)
                        {
                            break;

                        }
                    }

                    if (current.collision == true)
                    {
                        current.newClick = false;
                        break;
                    }
                    else
                        updateY(current);

                }
                else if (current.slopeX < current.slopeY)
                {
                    current.maxSlope = current.slopeY;

                    for (int j = 0; j <= current.slopeY; j++)
                    {
                        updateY(current);

                        if (current.clickedY == current.y)
                        {
                            break;
                        }

                        if (current.moveCounter == current.moveSpeed)
                        {
                            break;

                        }
                    }

                    if (current.collision == true)
                    {
                        current.newClick = false;
                        break;
                    }
                    else
                        updateX(current);

                }
                else if (current.slopeX == current.slopeY)
                {
                    for (int j = 0; j <= current.maxSlope; j++)
                    {
                        updateX(current);
                        updateY(current);

                        if (current.moveCounter == current.moveSpeed)
                        {
                            break;
                        }

                        if (current.collision == true)
                        {
                            current.newClick = false;
                            break;
                        }
                    }
                }
            }
            if ((current.clickedX == current.x) && (current.clickedY == current.y))
            {
                //if(current.type == "player")
                {
                    checkPoint.remove(1);
                    current.newCheckPoint = true;
                }

                current.maxSlope = 1;

                if (checkPoint.size() == 1)
                {
                    current.newCheckPoint = false;
                    current.state = "idle";
                    if(current.type == "player")
                    {
                        current.runningStone.pause();
                        current.runningDirt.pause();
                    }
                    current.picCounter = 0;
                    current.newClick = false;
                    current.hasPath = false;
                }

                if (this.current.target != this.current)
                {
                    if (current.target.hp > 0)
                    {
                        if ((isInRange(this.current, this.current.target) == true) && (current.hasDoneDmage == false))
                        {
                            takeDamage(current.target, 10);
                            this.current.target = this.current;
                        }
                    }
                }
            }
        }
    }

    public void updateX(Entity current)
    {

        if (current.clickedX < current.x)
        {
            if (current.collision == false)
            {
                current.x -= 5;
                current.moveCounter++;
            }
            else
            {
                current.clickedX = obsX;
                current.clickedY = obsY;
            }
        }
        else if (current.x < current.clickedX)
        {
            if (current.collision == false)
            {
                current.x += 5;
                current.moveCounter++;
            }
            else
            {
                current.clickedX = obsX;
                current.clickedY = obsY;
            }
        }
    }

    public void updateY(Entity current)
    {
        if (current.clickedY < current.y)
        {
            if (current.collision == false)
            {
                current.y -= 5;
                current.moveCounter++;
            }
            else
            {
                current.clickedX = obsX;
                current.clickedY = obsY;
            }
        }
        else if (current.clickedY > current.y)
        {
            if (current.collision == false)
            {
                current.y += 5;
                current.moveCounter++;
            }
            else
            {
                current.clickedX = obsX;
                current.clickedY = obsY;
            }
        }
    }


    public void isVisible()
    {
        if (((current.x >= game.getEntityList().get(0).x - Game.windowX / 2) && (current.x <= current.getEntityList().get(0).x + Game.windowX / 2))
                &&
                ((current.y >= current.getEntityList().get(0).y - Game.windowY / 2) && (current.y <= current.getEntityList().get(0).y + Game.windowY / 2)))
        {
            current.visible = true;
        }
        else
            current.visible = false;
    }

    public int isCollision(int x, int y, Entity current)
    {
        int result = 0;
        if (current.type != "player")
        {
            for (int i = 1; i < current.getEntityList().size(); i++)
            {
                if ((this.current.visible == true) && (current.getEntityList().get(i).visible == true))
                {
                    if ((current.getEntityList().get(i) != current) || (current.type == "projectile"))
                    {
                        if (((x + current.hitBox > current.getEntityList().get(i).x) && (x < current.getEntityList().get(i).x + current.getEntityList().get(i).hitBox))
                                &&
                                ((y + current.hitBox > current.getEntityList().get(i).y) && (y < current.getEntityList().get(i).y + current.getEntityList().get(i).hitBox)))
                        {
                            if (current.type == "projectile")
                            {
                                current.collision = true;

                                takeDamage(current.getEntityList().get(i), current.damage);
                                break;
                            }
                            if (current.type == "melee")
                            {
                                current.collision = true;

                                result = i;

                                break;
                            }
                            else
                            {
                                current.collision = true;
                            }
                        }

                        else
                        {
                            if (current.type == "projectile")
                            {
                                current.collision = false;
                            }
                            else
                            {
                                current.collision = false;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public void takeDamage(Entity target, int damage)
    {
        target.hp = target.hp - damage;
        target.tookDamage = true;
        current.hasDoneDmage = true;
    }

    public boolean isInRange(Entity self, Entity target)
    {
        int range = 20;
        boolean result = false;

        if ((self.x < (target.x + range)) && (self.x > (target.x - range))
                || (self.y < (target.y + range)) && (self.y > (target.y - range)))
        {
            result = true;
        }

        return result;
    }

    /*--------------------------------------------------------------------------------
     * path finding
     */

    public int updateTempX(int originX, int originY, int targetX)
    {
        if ((targetX < originX))
        {
            originX -= 5;

            isObstacles(originX, originY);
        }
        else if ((originX < targetX))
        {
            originX += 5;

            isObstacles(originX, originY);
        }

        return originX;
    }

    public int updateTempY(int originX, int originY, int targetY)
    {
        if ((originY > targetY))
        {
            originY -= 5;

            isObstacles(originX, originY);
        }
        else if ((originY < targetY))
        {
            originY += 5;

            isObstacles(originX, originY);
        }

        return originY;
    }

    boolean isObs = false;

    public boolean isObstacles(int x, int y)
    {
        if ((x <= 0) || (y <= 0) || (game.obsMap[x + y * game.mapWidth] == true))
        {
            System.out.println("obs detected");
            isObs = true;
            return true;
        }

        isObs = false;
        return false;
    }


    public double getSlope(int target1, int target2, int origin1, int origin2)
    {
        return Math.abs((double) (target1 - origin1) / (target2 - origin2));
    }

    int tempX;
    int tempY;
    boolean firstScan = false;

    public boolean isLineOfSight(int originX, int originY, int targetX, int targetY)
    {
        int slopeX = (int) Math.abs(Math.round(getSlope(originX, originY, targetX, targetY)));
        int slopeY = (int) Math.abs(Math.round(getSlope(originY, originX, targetY, targetX)));

        while ((originX != targetX) || (originY != targetY))
        {
            if (slopeX > slopeY)
            {
                for (int i = 0; i < slopeX; i++)
                {
                    originX = updateTempX(originX, originY, targetX);

                    if ((originX == targetX) || (originY == targetY))
                    {
                        break;
                    }

                    if (isObs == true)
                    {
                        break;
                    }
                }

                if ((originX == targetX) && (originY == targetY))
                {
                    break;
                }
                originY = updateTempY(originX, originY, targetY);

                if (isObs == true)
                {
                    break;
                }

            } else if (slopeY > slopeX)
            {
                for (int i = 0; i < slopeY; i++)
                {
                    originY = updateTempY(originX, originY, targetY);

                    if ((originX == targetX) || (originY == targetY))
                    {
                        break;
                    }

                    if (isObs == true)
                    {
                        break;
                    }
                }

                if ((originX == targetX) && (originY == targetY))
                {
                    break;
                }
                originX = updateTempX(originX, originY, targetX);

                if (isObs == true)
                {
                    break;
                }

            }
            else
            {
                originX = updateTempX(originX, originY, targetX);

                if ((originX == targetX) && (originY == targetY))
                {
                    break;
                }

                if (isObs == true)
                {
                    break;
                }
                originY = updateTempY(originX, originY, targetY);

                if ((originX == targetX) && (originY == targetY))
                {
                    break;
                }

                if (isObs == true)
                {
                    break;
                }
            }
        }

        if (isObs == true)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public void pathFind()
    {
        firstScan = true;
        tempX = checkPoint.get(checkPoint.size() - 1).x;
        tempY = checkPoint.get(checkPoint.size() - 1).y;

        int shortestX = 0;
        int shortestY = 0;
        isLineOfSight(tempX, tempY, current.clickedX, current.clickedY);

        boolean condition = false;

        if ((tempX != current.clickedX) || (tempY != current.clickedY))
        {
            condition = true;
        }

        while (condition == true)
        {
            ArrayList<Node> grid = new ArrayList<Node>();

            establishGrid(-5, -5, grid);
            establishGrid(0, -5, grid);
            establishGrid(5, -5, grid);

            establishGrid(-5, 0, grid);
            establishGrid(5, 0, grid);

            establishGrid(-5, +5, grid);
            establishGrid(0, +5, grid);
            establishGrid(5, +5, grid);

            if (grid.size() == 0)
            {
                System.out.println("ERROR, unknown error bypassed");
                break;
            }
            shortestX = grid.get(0).x;
            shortestY = grid.get(0).y;
            int smallestIndex = 0;

            for (int i = 0; i < grid.size(); i++)
            {
                if (i + 1 == grid.size())
                {
                    break;
                }

                if (grid.get(smallestIndex).distance >= grid.get(i + 1).distance)
                {
                    shortestX = grid.get(i + 1).x;
                    shortestY = grid.get(i + 1).y;
                    smallestIndex = i + 1;
                }

            }
            usedGrid.add(new Node(shortestX, shortestY));

            int possibleX = tempX;
            int possibleY = tempY;
            if (isLineOfSight(checkPoint.get(checkPoint.size() - 1).x, checkPoint.get(checkPoint.size() - 1).y, shortestX, shortestY) == false)
            {
                checkPoint.add(new Node(possibleX, possibleY));
            }

            tempX = shortestX;
            tempY = shortestY;

            if ((tempX == current.clickedX) && (tempY == current.clickedY))
            {
                break;
            }
        }

        checkPoint.add(new Node(current.clickedX, current.clickedY));

        for (int i = 0; i < checkPoint.size(); i++)
        {
            //System.out.println("checkX:" +checkPoint.get(i).x +"  checkY" +checkPoint.get(i).y +"  clickedX" +current.clickedX+"  clickedY" +current.clickedY);
        }

        for (int i = 0; i < checkPoint.size(); i++)
        {
            for (int a = checkPoint.size() - 1; a > i + 1; a--)
            {
                if (isLineOfSight(checkPoint.get(i).x, checkPoint.get(i).y, checkPoint.get(a).x, checkPoint.get(a).y) == true)
                {
                    int numRemove = a - i - 1;
                    for (int b = 0; b < numRemove; b++)
                    {
                        checkPoint.remove(i + 1);
                    }

                    break;
                }
            }
        }
    }

    public void establishGrid(int deltaX, int deltaY, ArrayList<Node> grid)
    {
        if (isObstacles(tempX + deltaX, tempY + deltaY) == false)
        {
            if (checkPreviousGrid(tempX + deltaX, tempY + deltaY) == false)
            {
                grid.add(new Node(tempX + deltaX, tempY + deltaY, getDistance(current.clickedX, current.clickedY, tempX + deltaX, tempY + deltaY)));
            }
        }
    }

    public boolean checkPreviousGrid(int x, int y)
    {
        boolean result = false;
        for (int i = 0; i < usedGrid.size(); i++)
        {
            if ((usedGrid.get(i).x == x) && (usedGrid.get(i).y == y))
            {
                return true;
            }
        }

        return result;
    }

    public double getDistance(int x1, int y1, int x2, int y2)
    {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}


