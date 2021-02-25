package Diablo;

import Items.HealthPotion;
import Items.ManaPotion;
import Items.SpeedPotion;

import java.awt.Dimension;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Game
{

    //static final public Path root = Paths.get(System.getProperty("user.dir")).getParent();

    //this is only works for Fan
    static final public String root = Paths.get(System.getProperty("user.dir")).getParent() + "/RPG";

    //GameState enum
    public enum GameState
    {
        MAINMENU_STATE,
        MAINGAME_STATE,
        PAUSE_STATE,
        DEAD_STATE
    }

    public static GameState gameState;

    static int windowX = 1280;
    static int windowY = 720;

    //static int windowX = 1920;
    //static int windowY = 1080;

    static int centerX = windowX / 2;
    static int centerY = windowY / 2;

    static int fps = 30;

    static int[] mapDimension = new int[2];
    //static int timer = 1000 / fps;

    static Display display;
    static Map map;

    private ArrayList<Entity> list = new ArrayList<Entity>();

    public ArrayList<Entity> getEntityList()
    {
        return list;
    }

    private ArrayList<Entity> projectileList = new ArrayList<Entity>();

    public ArrayList<Entity> getProjectileList()
    {
        return projectileList;
    }

    private MouseControl mouse;

    public Diablo.MouseControl getMouseControl()
    {
        return mouse;
    }

    private KeyboardControl keyboard = new KeyboardControl(this);

    public Diablo.KeyboardControl getKeyboardControl()
    {
        return keyboard;
    }

    private ArrayList<Entity> obstacle = new ArrayList<Entity>();

    public ArrayList<Entity> getObstacles()
    {
        return obstacle;
    }

    private ArrayList<Node> obstacleLocation = new ArrayList<Node>();

    public ArrayList<Node> getObstacleLocation()
    {
        return obstacleLocation;
    }

    int mapWidth = 5000;
    int[] obsMap = new int[mapWidth * mapWidth];

    static Timer timer = new Timer();
    static int gameTime = 0;


    public Game() throws IOException
    {
        //long time = System.currentTimeMillis();
        //System.out.println(time);

        try
        {
            map = new Map("backGround", this);
        } catch (IOException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        list.add(new Entity("player", new int[]{500, 500}, 100, 80, this, 100, 0, 100));
        list.add(new Entity("enemy", new int[]{300, 300}, 100, 80, this, 100, 0, 100));

        obstacle.add(new Entity(this, "tavern", 500, 500));
        //list.add(new Entity("enemy", new int[]{-50, 0}, 100, 80));

        //list.get(0).move.isLineOfSight();

        display = new Display(this);

        //gameLoop();

        //System.out.println(System.currentTimeMillis());

        int refreshTime = 1000 / fps;
        timer.scheduleAtFixedRate(dataUpdate, 0, 30);
        timer.scheduleAtFixedRate(frameUpdate, 0, refreshTime);
        timer.scheduleAtFixedRate(timeCounter, 0, 100);
        mouse = new MouseControl(this);

        //temporary for testing
        list.get(0).addItem(0, new HealthPotion(this));
        list.get(0).addItem(1, new HealthPotion(this));
        list.get(0).addItem(2, new ManaPotion(this));
        list.get(0).addItem(3, new SpeedPotion(this));
        list.get(0).inventory.addItem(new HealthPotion(this), 0);
        list.get(0).inventory.addItem(new HealthPotion(this), 1);
        list.get(0).inventory.addItem(new HealthPotion(this), 2);
        list.get(0).inventory.addItem(new ManaPotion(this), 3);
        list.get(0).inventory.addItem(new ManaPotion(this), 4);
        list.get(0).inventory.addItem(new ManaPotion(this), 5);
        list.get(0).inventory.addItem(new SpeedPotion(this), 6);
        list.get(0).inventory.addItem(new SpeedPotion(this), 7);
        list.get(0).inventory.addItem(new SpeedPotion(this), 8);
        list.get(0).inventory.addItem(new HealthPotion(this), 9);
        list.get(0).inventory.addItem(new ManaPotion(this), 10);
        list.get(0).inventory.addItem(new SpeedPotion(this), 11);
    }

    public static void main(String[] args) throws IOException
    {
        new Game();
    }

    public void changeGameState(int i)
    {
        gameState = GameState.values()[i];
    }

    private TimerTask dataUpdate = new TimerTask()
    {
        public void run()
        {
            //System.out.println("here");
            gameLoop();
        }
    };

    private TimerTask frameUpdate = new TimerTask()
    {
        public void run()
        {
            //System.out.println("here");
            display.update();
        }
    };

    private TimerTask timeCounter = new TimerTask()
    {
        public void run()
        {
            //System.out.println("here");
            gameTime++;
        }
    };

    public void gameLoop()
    {

        list.get(0).move.keyBoardUpdate(list.get(0));

        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).hasPath == true)
            {
                if (i != 0)
                {
                    list.get(i).ai.update();
                }
                list.get(i).move.update(list.get(i));
            }
        }
        //System.out.println(projectile.size());

        for (int i = 0; i < projectileList.size(); i++)
        {

            projectileList.get(i).move.update(projectileList.get(i));

            //System.out.println(projectile.get(i).collision);
        }

        //display.update();

        //list.get(0).takeDamage(list.get(0), 1);

        for (int i = 0; i < projectileList.size(); i++)
        {

            if ((projectileList.get(i).visible == false) || (projectileList.get(i).active == false))
            {
                projectileList.remove(i);
            }
        }

        for (int i = 0; i < list.size(); i++)
        {

            if (list.get(i).hp <= 0)
            {
                list.get(i).visible = false;
            }
        }

        //System.out.println(projectile.size());

        if (list.get(0).hp <= 0)
        {
            //break;
        }
    }

}