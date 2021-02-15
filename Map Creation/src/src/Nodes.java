package src;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;

public class Nodes {
	private int x;
	private int y;
	//private int size;
	private boolean foundNode;
	private Image image;
	private Dimension maxSize = new Dimension();
	private int imageListIndex; //index of images in the image folder
	private int width;
	private int height;
	private boolean isShown;
	private boolean moved;
	private int offset=0;
	public Nodes(int x, int y){
		this.x = x;
		this.y = y;
		this.maxSize = maxSize;
		imageListIndex = -1; //initialize default imageIndex
		this.moved = false;
	}
	
	public void nodeSelect() {
		
	}
	public int getimageListIndex() {
		return imageListIndex;
	}
	public void setImageListIndex(int imageListIndex) {
		this.imageListIndex = imageListIndex;
	}

	public void incX(int inc) {
		//return ((x * (100/2)) - (y*100/2));
		x = x + inc;
	}
	public int getXMod(int mod) {
		//return ((x * (100/2)) - (y*100/2));
		if(mod == 0 || mod == 2 || mod == 4 || mod == 6 || mod == 8 || mod == 10 || mod == 100) {
//			if(x % 1000 ==0 && moved == false) {
//				System.out.println("moved yo");
//				moved = true;
//				return x;
//			}
//			if(moved == true) {
//				return x+50;
//			}
			return (x + (MapBuilder.pixelW/2) - ((MapBuilder.pixelW/2)*(offset)));
		} 
		else return x + (MapBuilder.pixelW/2)*offset;
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	public Dimension getMaxSize() { return maxSize; }
	
	public int maxWidth() { return (int) maxSize.getWidth(); }
	public int maxHeight() { return (int) maxSize.getHeight(); }
	
	public void setImage(Image image) { this.image = image; }
	public Image getImage() { return this.image; }
	
	public void setWidth(int width) { this.width = width; }
	public void setHeight(int height) { this.height = height; }
	
	public int getWidth() { return width; }
	public int getHeght() { return height; }
	
	public boolean getIsShown() { return isShown; }
	public void setIsShown(boolean a) { isShown = a; }
	
	public boolean getMoved() { return moved; }
	public void reset() { moved = false; }
	
	public void setOffset(int offset) { this.offset = offset; }
	public int getOffset() { return offset; }
}
