<<<<<<< HEAD
<<<<<<<< HEAD:src/Map.java
package Diablo;
========

package BigMess;
>>>>>>>> c763570e1ee493dd1a252837001c30f5c3251ba1:BigMess/Map.java
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
=======
import javax.swing.ImageIcon;

public class Map extends Game {

 public Map(String mapName) {
  ImageIcon icon = new ImageIcon(Game.root + "/resources/backGround/"+mapName+".jpg");
  
  mapDimension = new int[] {icon.getImage().getWidth(null), icon.getImage().getHeight(null)};
  
  respawnLcation = new int[] {0, 0};
 }
}
>>>>>>> c763570e1ee493dd1a252837001c30f5c3251ba1
