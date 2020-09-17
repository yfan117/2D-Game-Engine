package rpg;

import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

public class Sprite {
	protected int x;
	protected int y;
	 protected boolean visible;
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
	public static List<Arrow> getArrows() {//move to char make non static
        return arrows;
    }
	/*public void fire() {
		arrows.add(new Arrow(50, 50, x, y));
		System.out.println("Fire");
	}*/
}
