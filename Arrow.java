import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Arrow extends Sprite {

	 static BufferedImage arrow;
	 
	 private final int BOARD_WIDTH = 500;
	 private final int ARROW_SPEED = 2;
	 private int mouseX;
	 private int mouseY;

    public Arrow(int x, int y, int mouseX, int mouseY) {
		super(x, y);
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		initArrow();
	}

	private void initArrow() {
        System.out.println("initArrow");
        loadImage("imgs/arrow2.png");
       // drawImage();
        getImageDimensions();
    }

//in character create a list of arrows
 //   public List<Arrow> getArrows() {
 //       return arrows;
  //  }
	public int getArrowSpeed() {
		return ARROW_SPEED;
	}
    public void move() {
        
        x += ARROW_SPEED;
        
        if (x > BOARD_WIDTH) {
            visible = false;
        }
    }
    public int getMouseX() {
    	return mouseX;
    }
    public int getMouseY() {
    	return mouseY;
    }
    protected void getImageDimensions() { // add this to a sprite class

        width = image.getWidth(null);
        height = image.getHeight(null);
    }
    public int getImageW() {
    	return width;
    }
	public int getImageH() {
		return height;
	}
	public int setX(int newX) {
		this.x = newX;
		return newX;
	}
	public void draw(Graphics g) {
		//g.drawImage(arrow, 0, 0,null);
		g.drawLine(x, y, mouseX, mouseY);
	}
	
}
