package Items;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ManaPotion extends Item
{
    private final int cost = 50;
    private final String name = "Mana potion";
    private BufferedImage image;
    private Diablo.Game g;

    public ManaPotion(Diablo.Game g)
    {
        this.g = g;
        try
        {
            image = ImageIO.read(new File(Diablo.Game.root + "/resources/images/items/manapotion.png"));
        }catch(Exception ex){ex.printStackTrace();}
    }

    public void useItem()
    {
        g.getEntityList().get(0).setMana(g.getEntityList().get(0).getMana() + 50);
    }

    public BufferedImage getImage(){return image;}

    public boolean isDisposable(){return true;}
}
