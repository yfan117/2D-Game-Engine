package Diablo;

public class Animation {
	
	int[] imageData;
	int spritWidth;
	int picWidth;
	int picHeight;
	int numOfFrame;
	//int cycleRate;
	
	public Animation(int[] imageData, int picWidth, int picHeight, int spritWidth, int numOfFrame)
	{
		this.imageData = imageData;
		this.picWidth = picWidth;
		this.picHeight = picHeight;
		this.spritWidth = spritWidth;
		this.numOfFrame = numOfFrame;
	}

}
