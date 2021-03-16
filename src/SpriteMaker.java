package Diablo;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class SpriteMaker {
	
	public static void main(String[] args) throws IOException
	{
		int picX = 300;
		int picY = 300;
		int numDirection = 16;
		int numPicPer = 111;
		
		int width = picX * numPicPer;
		int height = picY * numDirection;
		int[] rgbMatrix = new int[width * height];
		
		String root = Paths.get(System.getProperty("user.dir")).getParent()+"/Portfolio/resources/images/wizard/Direction";
		
		String newRoot;
		String imageDir;
		
		for(int i = 1; i <= numDirection ; i++)
		{
			newRoot = root + i +"/";
			
			for(int a = 0; a < numPicPer ; a++)
			{
				if(a < 10)
				{
					imageDir = newRoot + "000" +a +".png";
				}
				else if(a<100)
				{
					imageDir = newRoot + "00" +a +".png";
				}
				else
				{
					imageDir = newRoot + "0" +a +".png";
				}
					BufferedImage image = ImageIO.read(new File(imageDir));		
					
					for(int y = 0; y < picY; y++)
					{
						for(int x = 0; x < picX; x++)
						{
							int color = image.getRGB(x, y);
							
							if((color != 0)&&(color != 65793))
							{
								rgbMatrix[ a * picX + x + ((i - 1)*picY + y) * width] = color;
							}
						}
					}
			}
		}
		
		BufferedImage finalImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		int[] temp = ((DataBufferInt)finalImage.getRaster().getDataBuffer()).getData();
		
		for(int i = 0; i < width * height; i++)
		{
			temp[i] = rgbMatrix[i];
		}
		
		FileWriter outputFile = new FileWriter("sprite.txt");
		for(int i = 0; i < width * height; i++)
		{
			outputFile.write(temp[i] +" ");
		}
		ImageIO.write(finalImage, "png", new File("C:\\Users\\Fan\\eclipse-workspace/Portfolio/resources/images/wizard.png"));
	}
}
