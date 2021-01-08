
package Diablo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.ImageIcon;

public class Map{
	Game game;

	public Map(Game game){this.game = game;}

	private int enemyNum;
	int[] respawnLcation = new int[2];


	public Map(String mapName, Game game) throws IOException  {
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

