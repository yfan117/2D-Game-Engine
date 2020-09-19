import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.ImageIcon;

public class Sprite {
	protected int x;
	protected int y;
    protected int width;
    protected int height;
    protected boolean visible;
    protected boolean exists = false;
    protected Image image;
	private static List<Arrow> arrows;//mov to char
	public Sprite(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		visible = true;
	}
	protected void loadImage(String imageName) {
		ImageIcon ii = new ImageIcon(imageName);
		image = ii.getImage();
	}
	public Image getImage() {
		return image;
	}
	protected void getImgDim() {
		width = image.getWidth(null);
		height = image.getHeight(null);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}    
	public boolean isExist() {
		return exists;
	}
	public void setExists(Boolean exist) {
		this.exists = exists;
	}    
	public static List<Arrow> getArrows() {//move to char make non static
        return arrows;
    }
	public Rectangle getBounds() {
		return new Rectangle(x,y,width,height);
	}
	/*public void fire() {
		arrows.add(new Arrow(50, 50, x, y));
		System.out.println("Fire");
	}*/
}
