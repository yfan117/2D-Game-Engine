
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
	String mapName;
	public MusicPlayer musicPlayer;
	BufferedReader br;
	String currLine;

	public Map(Game game){this.game = game;}

	private int enemyNum;
	int[] respawnLcation = new int[2];


	public Map(String mapName, Game game) throws IOException  {
		this.mapName = mapName;

		try
		{
			br = new BufferedReader(new FileReader(Game.root + "/resources/text/music.txt"));
			currLine = br.readLine();

			while(currLine != null)
			{
				if(currLine.startsWith("/"))
				{
					if(currLine.substring(1).equals(mapName))
					{
						currLine = br.readLine();
						musicPlayer = new MusicPlayer(Game.root + "/resources/music/" + mapName + ".WAV");
					}
				}
				currLine = br.readLine();
			}
		}catch(Exception ex){ex.printStackTrace();}
		
		BufferedImage image = ImageIO.read(new File(Game.root + "/resources/images/tavern_Obs.png"));
		
		for(int y = 0; y < image.getHeight(); y++)
		{
			for(int x = 0; x < image.getWidth(); x++)
			{
				int colorCode = image.getRGB(x, y);
				
				if( colorCode != 0)
				{
					//System.out.println(colorCode);
					game.obsMap[500 + x + (500 + y) * 5000] = 1;

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

	public void playMapMusic()
	{
		musicPlayer.play();
	}
}

