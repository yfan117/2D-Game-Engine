import javax.swing.ImageIcon;

public class Map extends Game{
	//static ImageIcon icon;

	public Map(String mapName) {

		ImageIcon icon = new ImageIcon(Game.root + "/resources/backGround/"+mapName+".jpg");

		mapDimension = new int[] {icon.getImage().getWidth(null), icon.getImage().getHeight(null)};


		//read from txt file about map information.  EX: respawn location

		respawnLcation = new int[] {0, 0};


	}





}
