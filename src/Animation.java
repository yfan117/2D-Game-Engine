package Diablo;

public class Animation {
	
	int[] imageData;
	int spritWidth;
	int picWidth;
	int picHeight;
	int numOfFrame;
	
	String name;
	
	int startX;
	int startY;
	//int cycleRate;
	
	public Animation(String name, int[] imageData, int picWidth, int picHeight, int spritWidth, int numOfFrame)
	{
		this.name = name;
		this.imageData = imageData;
		this.picWidth = picWidth;
		this.picHeight = picHeight;
		this.spritWidth = spritWidth;
		this.numOfFrame = numOfFrame;
	}
	
	public Animation(int[] imageData)
	{
		this.imageData = imageData;

	}
	
	public void addStartPoint(int startX, int startY)
	{
		this.startX = startX;
		this.startY = startY;
	}
}
