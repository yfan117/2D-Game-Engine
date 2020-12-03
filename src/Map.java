
package Diablo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.ImageIcon;

public class Map extends Game{


	static int enemyNum;
	int[] respawnLcation = new int[2];


	public Map(String mapName) throws IOException  {

		ImageIcon icon = new ImageIcon(root + "/resources/images/" + mapName + ".jpg");
	

		mapDimension[0] = icon.getImage().getWidth(null);
		mapDimension[1] = icon.getImage().getHeight(null);


		//BufferedReader bufferedReader = new BufferedReader(new FileReader(root + "/resources/text/" + mapName +".txt"));
	
		File map = new File(root + "/resources/text/" + mapName +".txt");
		Scanner scan = new Scanner(map);
		
		while(scan.hasNext())
		{
			//System.out.println(scan.next());
			//if(scan.next().charAt(0) == '>')
			{
				//System.out.println("here");
				//System.out.println(scan.next());
				Game.obstacle.add(new Entity(scan.next(), Integer.parseInt(scan.next()), Integer.parseInt(scan.next())));
			}
		}
			
		

		//respawnLcation[0] = Integer.parseInt(bufferedReader.readLine());
		//respawnLcation[1] = Integer.parseInt(bufferedReader.readLine());



		enemyNum = 0;

	}

	public int[] getLocation() {

		return respawnLcation;
	}


}

