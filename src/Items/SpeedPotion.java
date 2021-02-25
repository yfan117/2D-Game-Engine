package Items;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class SpeedPotion extends Item
{
    private final int cost = 50;
    private final String name = "Speed potion";
    private BufferedImage image;
    private Diablo.Game g;

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
    }

    public BufferedImage getImage(){return image;}

    public boolean isDisposable(){return true;}
}
