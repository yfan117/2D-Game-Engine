package Items;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class SpeedPotion extends Item
{
    private final int cost = 50;
    private final String name = "Speed potion";
    private BufferedImage image;
    private Diablo.Game g;
    private boolean disposable = true;
    private boolean stackable = false;

    public SpeedPotion(Diablo.Game g)
    {
        this.g = g;
        try
        {
            image = ImageIO.read(new File(Diablo.Game.root + "/resources/images/items/speedpotion.png"));
        }catch(Exception ex){ex.printStackTrace();}
    }

    public void useItem()
    {
        g.getEntityList().get(0).setMovespeed(2.0);

        //Set the player's speed back to 1.0 after 5000ms
        new Timer().schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                g.getEntityList().get(0).setMovespeed(1.0);
                g.getEntityList().get(0).inventory.addItem(new HealthPotion(g, 5));
                cancel();
            }
        }, 5000);
    }

    public BufferedImage getImage(){return image;}

    public boolean isDisposable(){return disposable;}
    public boolean isStackable(){return stackable;}

    public String getName(){return name;}
}
