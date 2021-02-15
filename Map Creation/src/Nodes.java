import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Nodes {
	private int x;
	private int y;
	//private int size;
	private boolean foundNode;
	private Image image;
	private Dimension maxSize = new Dimension();
	//private int maxSizeW;
	//private int maxSizeH;
	//public Nodes(int screenW, int screenH, int pixelW, int pixelH){
	//		
	//}
	public Nodes(int x, int y, Dimension maxSize){
		this.x = x;
		this.y = y;
		this.maxSize = maxSize;
	}
	
	public void nodeSelect() {
		
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Dimension getMaxSize() {
		return maxSize;
	}
	public int maxWidth() {
		return (int) maxSize.getWidth();
	}
	public int maxHeight() {
		return (int) maxSize.getHeight();
	}
	public void setImage(Image image) {
		this.image = image;
		
	}
	public Image getImage() {
		return this.image;
	}
	//public void setSize(int size) {
//		size = size + 1;
	//}
//	public int size() {
//		return size;
//	}
		
	//	for(int i = 0; i <= this.size(); i = i + 1) {
			
		//}
//	}
/*

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		int clikcedX = e.getX();
		int clikcedY = e.getY();
		System.out.println("TEST YO");
	//	for(int i = 0; i <= MapBuilder.nodes.size(); i++) {
	//		System.out.println(this.x + ", " + this.y);
	//	}
	}*/
}
