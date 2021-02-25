package Items;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class HealthPotion extends Item
{
    private final int cost = 50;
    private final String name = "Health potion";
    private BufferedImage image;
    private Diablo.Game g;

    public HealthPotion(Diablo.Game g)
    {
        this.g = g;
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

    public boolean isDisposable(){return true;}
}
