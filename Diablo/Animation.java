package Diablo;

public class Animation {
	
	int[] imageData;
	int spriteWidth;
	int picWidth;
	int picHeight;
	int numOfFrame;
	
	String name;
	
	int startX;
	int startY;
	//int cycleRate;
	
	public Animation(String name, int[] imageData, int picWidth, int picHeight, int spriteWidth, int numOfFrame)
	{
		this.name = name;
		this.imageData = imageData;
		this.picWidth = picWidth;
		this.picHeight = picHeight;
		this.spriteWidth = spritWidth;
		this.numOfFrame = numOfFrame;
	}
	
	public Animation(int[] imageData)
	{
		this.imageData = imageData;

	}
	public Animation(String name, int[] imageData, int picWidth, int picHeight, int spritWidth, int startX, int startY) {
		this.name = name;
		this.imageData = imageData;
		this.picWidth = picWidth;
		this.picHeight = picHeight;
		this.spriteWidth = spritWidth;
		this.startX = startX;
		this.startY = startY;
	}

	public void addStartPoint(int startX, int startY)
	{
		this.startX = startX;
		this.startY = startY;
	}
}