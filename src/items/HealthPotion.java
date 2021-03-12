package Diablo.items;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class HealthPotion extends Item
{
    private final int cost = 50;
    private final String name = "Health potion";
    private BufferedImage image;
    private Diablo.Game g;
    private boolean disposable = true;
    private boolean stackable = true;
    private int numberInStack = 1;

    public HealthPotion(Diablo.Game g, int n)
    {
        this.g = g;
        this.numberInStack = n;
        try
        {
            image = ImageIO.read(new File(Diablo.Game.root + "/resources/images/items/healthpotion.png"));
        }catch(Exception ex){ex.printStackTrace();}
    }

    public void useItem()
    {
        g.getEntityList().get(0).setHP(g.getEntityList().get(0).getHP() + 50);
    }

    public BufferedImage getImage(){return image;}

    public boolean isDisposable(){return disposable;}
    public boolean isStackable(){return stackable;}

    public int getNumberInStack(){return numberInStack;}
    public void setNumberInStack(int i){numberInStack = i;}

    public String getName(){return name;}
}