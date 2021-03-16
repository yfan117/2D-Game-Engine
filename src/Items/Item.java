package Diablo.Items;
import java.awt.image.BufferedImage;

public abstract class Item
{
    BufferedImage image;
    public void useItem(){}
    public boolean isDisposable(){return true;}
    public boolean isStackable(){return false;}
    public int getNumberInStack(){return 0;}
    public void setNumberInStack(int i){}
    public String getName(){return "";}
    public BufferedImage getImage(){return image;}
}