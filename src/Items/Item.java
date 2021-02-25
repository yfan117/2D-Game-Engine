package Items;

import java.awt.image.BufferedImage;

public abstract class Item
{
    BufferedImage image;
    public void useItem(){}
    public boolean isDisposable(){return true;}
    public BufferedImage getImage(){return image;}
}
