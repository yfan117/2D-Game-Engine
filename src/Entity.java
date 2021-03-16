package Diablo;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Diablo.Items.Item;

public class Entity
{
    int x = 0;
    int y = 0;

    int clickedX;//destination
    int clickedY;//destination
    boolean newClick = false;
    boolean directionCheck = false;

    int moveSpeed = 0;
    int moveCounter = 0;

    boolean north;
    boolean south;
    boolean west;
    boolean east;

    double slopeX;
    double slopeY;
    double maxSlope = 1;

    int preX;
    int preY;

    int picCounter = 0;
    int timeCounter = 0;

    int picRank = 6;

    int range;

    String type = "";

    boolean visible = false;
    boolean collision = false;
    boolean active = true;

    boolean isMelee = false;

    boolean tookDamage = false;

    boolean hasDoneDmage = false;

    boolean hasPath = false;

    boolean newCheckPoint = true;

    double moveAngle;

    int hp;

    int damage;

    int hitBox;

    Entity target;

    Movement move;

    AI ai;

    Game game;

    int oil;
    int insanity;

    int respondX = 0;
    int respondY = 0;

    boolean moveLeft = false;
    boolean moveRight = false;
    boolean moveUp = false;
    boolean moveDown = false;

    boolean keyMove = false;

    private boolean actionable = false;

    public Inventory inventory;
    int mana;

    String state;

    int layerY;
    int[] imageData;
    int picX;
    int picY;
    int spriteWidth;
    int numOfFrame;

    Animation idle;
    Animation run;

    String characterName;

    MusicPlayer runningStone;
    MusicPlayer runningDirt;

    /*
     * Friendly NPC variables
     */
    private Image npcPortrait;
    private String[] dialogue;
    private int currentDialogue;
    private int[] collisionBox;
    private int width;
    private int height;
    private Dialogue d = null;
    private Diablo.Items.Item[] itemsList = new Diablo.Items.Item[4];
    private ArrayList<Objective> questlog = new ArrayList<Objective>();
    /*
     * Friendly NPC constructor
     */

    public void doAction()
    {
        if (type.equals("friendly"))
        {
            if (game.dialogue == true)
            {
                game.dialogue = false;//terminate dialogue
            } else
            {
                game.createDialogue(this);//startup dialogue
            }
        }
        if (type.equals("enemy"))
        {
            //attack();
        }
    }

    public boolean actionable()
    {
        /*
         * checks if entity is actionable
         * for hovering mouse function
         */
        if (actionable == true)
            return true;
        else
            return false;

    }

    public boolean isEntity(int x, int y)
    {
        //checks if x and y is within entity's collision box
        if (x < this.x + width / 2 && x > this.x - width / 2 && y < this.y + height / 2 && y > this.y - height / 2)
        {
            return true;
        }
        return false;
    }

    //getters
    public Image getNpcPortrait()
    {
        return npcPortrait;
    }

    public Dialogue getDialogue()
    {
        return d;
    }

    public int[] getCollisionBox()
    {
        return collisionBox;
    }

    public void updateAnimationData(Animation current)
    {
        imageData = current.imageData;
        picX = current.picWidth;
        picY = current.picHeight;
        spriteWidth = current.spritWidth;
        numOfFrame = current.numOfFrame;
    }

    public void addDialogue(Image NpcPortrait, Dialogue dialogue, int[] collisionBox)
    {
        this.npcPortrait = NpcPortrait;
        this.d = dialogue;
        currentDialogue = 0;
        this.collisionBox = collisionBox;
    }

    public Entity(String type, String name, int[] location, int hp, int hitBox, Game game, int oil, int mana) throws IOException
    {
        this.game = game;

        this.inventory = new Diablo.Inventory();

        x = location[0];
        y = location[1];

        this.type = type;
        characterName = name;
        if (type == "player")
        {
            try{
                runningStone = new MusicPlayer(Game.root + "/resources/music/runningStone.WAV");
                runningDirt = new MusicPlayer(Game.root + "/resources/music/runningDirt.WAV");
                runningStone.start();
                runningDirt.start();
            }catch(Exception ex){ex.printStackTrace();}
            visible = true;
            enableMovement();
        }
        else
        {
            respondX = x;
            respondY = y;
            ai = new AI(this, game);
        }

        target = this;

        inventory = new Inventory();

        //friendly NPC Variables
        if (type.equals("friendly"))
        {
            actionable = true;
        }
        if (type.equals("enemy"))
        {
            actionable = true;
        }

        width = 0;
        height = 0;

        this.hp = hp;
        this.hitBox = hitBox;

        FileReader reader = new FileReader(Game.root + "/resources/text/" + name + ".txt");
        BufferedReader bufferedReader = new BufferedReader(reader);

        layerY = Integer.parseInt(bufferedReader.readLine());

        idle = new Animation(Renderer.getImageData(name),
                Integer.parseInt(bufferedReader.readLine()),
                Integer.parseInt(bufferedReader.readLine()),
                Integer.parseInt(bufferedReader.readLine()),
                Integer.parseInt(bufferedReader.readLine()));

        moveSpeed = 5;

        state = "idle";
    }

    public Entity(Game game, String name, int x, int y) throws IOException
    {
        this.game = game;
        this.x = x;
        this.y = y;
    }

    public Entity(String name, int destinationX, int destinationY, int hitBox) throws IOException
    {
        this.x = game.getEntityList().get(0).x;
        this.y = game.getEntityList().get(0).y;

        preX = x;
        preY = y;

        move = new Movement(this, game);

        clickedX = destinationX;
        clickedY = destinationY;

        newClick = true;

        if (name == "arrow")
        {
            type = "projectile";
            visible = true;
        }
        if (name == "melee")
        {
            type = "melee";
            visible = true;
        }

        this.hitBox = hitBox;
        FileReader reader = new FileReader(Game.root + "/resources/text/" + name + ".txt");

        BufferedReader bufferedReader = new BufferedReader(reader);

        moveSpeed = Integer.parseInt(bufferedReader.readLine());

        damage = Integer.parseInt(bufferedReader.readLine());

    }


    public Entity(Game game, int[] imageData, int x, int y, int layerY, int picX, int picY) throws IOException
    {
        this.game = game;
        this.x = x;
        this.y = y;
        this.layerY = layerY;
        this.imageData = imageData;
        this.picX = picX;
        this.picY = picY;
        spriteWidth = picX;
    }

    public void enableMovement() throws NumberFormatException, IOException
    {
        FileReader reader = new FileReader(Game.root + "/resources/text/" + characterName + "@run.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);

        run = new Animation(Renderer.getImageData(characterName + "@run"),
                Integer.parseInt(bufferedReader.readLine()),
                Integer.parseInt(bufferedReader.readLine()),
                Integer.parseInt(bufferedReader.readLine()),
                Integer.parseInt(bufferedReader.readLine()));
        move = new Movement(this, game);
    }

    public ArrayList<Entity> getEntityList()
    {
        return game.getEntityList();
    }

    public void setDialogue(Dialogue d) { this.d = d; }

    public void addItem(int i, Diablo.Items.Item it)
    {
        this.itemsList[i] = it;
    }

    public Diablo.Items.Item getItem(int i)
    {
        return itemsList[i];
    }

    public void removeItem(int i)
    {
        this.itemsList[i] = null;
    }

    public void addObjective(Objective quest)
    {
        questlog.add(quest);
    }

    public ArrayList<Objective> getQuestlog() { return questlog; }

    public void setHP(int i)
    {
        this.hp = i;
    }

    public int getHP()
    {
        return this.hp;
    }

    public void setMana(int i)
    {
        this.mana = i;
    }

    public int getMana()
    {
        return this.mana;
    }

    public void setMovespeed(double i)
    {
        moveSpeed = (int) Math.round(5 * i);
        if (moveSpeed <= 0)
            moveSpeed = 1;
    }
}
