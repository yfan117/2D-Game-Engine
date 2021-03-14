
package Diablo;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Map{
	Game game;

	public Map(Game game){this.game = game;}

	private int enemyNum;
	int[] respawnLcation = new int[2];


	public Map(String mapName, Game game) throws IOException  {
		
		BufferedImage image = ImageIO.read(new File(Game.root + "\\resources\\images\\tavern_Obs.png"));
		
		

				String obsName = scan.next();
				int obsLocationX = Integer.parseInt(scan.next());
				int obsLocationY = Integer.parseInt(scan.next());
			
			
				innerScan = new Scanner(new File(Game.root + "/resources/text/" + obsName  +".txt"));
				BufferedImage image;
						
				while(innerScan.hasNext())
				{
					String obsPart = innerScan.next();
					int deltaX = Integer.parseInt(innerScan.next());
					int deltaY = Integer.parseInt(innerScan.next());
					int layerY = Integer.parseInt(innerScan.next());
					int picX = Integer.parseInt(innerScan.next());
					int picY = Integer.parseInt(innerScan.next());

					System.out.println(obsPart);
					game.getObstacles().add(new Entity( game, 							//main object
														Renderer.getImageData(obsPart),	//array of ints containing pixel data of picture
														obsLocationX + deltaX, 			//location x on the map
														obsLocationY + deltaY, 			//location y on the map
														layerY,
														picX,
														picY));						//y coordinate used to determine print order
				}
						
				image = ImageIO.read(new File(Game.root + "/resources/images/" +obsName +"Floor.png"));
				
				obsLocationX = obsLocationX - 0;
				obsLocationY = obsLocationY - 0;
				for(int y = 0; y < image.getHeight(); y++)
				{
					for(int x = 0; x < image.getWidth(); x++)
					{
						int colorCode = image.getRGB(x, y);
						
						if( colorCode != 0)
						{
							//System.out.println(colorCode);
							game.obsMap[obsLocationX + x + (obsLocationY + y) * Game.mapWidth] = true;

						}
					}

				}
			}
		}
		
		/*
		this.game = game;

		ImageIcon icon = new ImageIcon(Game.root + "/resources/images/" + mapName + ".png");


		Game.mapDimension[0] = icon.getImage().getWidth(null);
		Game.mapDimension[1] = icon.getImage().getHeight(null);


		//BufferedReader bufferedReader = new BufferedReader(new FileReader(root + "/resources/text/" + mapName +".txt"));
	
		File map = new File(Game.root + "/resources/text/" + mapName +".txt");
		Scanner scan = new Scanner(map);
		
		while(scan.hasNext())
		{
			//System.out.println(scan.next());
			//if(scan.next().charAt(0) == '>')
			{
				//System.out.println("here");
				//System.out.println(scan.next());
				game.getObstacles().add(new Entity(game, scan.next(), Integer.parseInt(scan.next()), Integer.parseInt(scan.next())));
			}
		}

	*/
			
		//respawnLcation[0] = Integer.parseInt(bufferedReader.readLine());
		//respawnLcation[1] = Integer.parseInt(bufferedReader.readLine());

		enemyNum = 0;

	}

	public int[] getLocation() {

		return respawnLcation;
	}


}

