
package Diablo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;

public class Map extends Game{


	static int enemyNum;
	int[] respawnLcation = new int[2];


	public Map(String mapName) throws IOException  {

		ImageIcon icon = new ImageIcon(root + "/resources/images/" + mapName + ".jpg");
	

		mapDimension[0] = icon.getImage().getWidth(null);
		mapDimension[1] = icon.getImage().getHeight(null);


		BufferedReader bufferedReader = new BufferedReader(new FileReader(root + "/resources/text/" + mapName +".txt"));
	



		respawnLcation[0] = Integer.parseInt(bufferedReader.readLine());
		respawnLcation[1] = Integer.parseInt(bufferedReader.readLine());


		enemyNum = 0;

	}

	public int[] getLocation() {

		return respawnLcation;
	}


}

