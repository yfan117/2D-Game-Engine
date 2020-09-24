import javax.swing.ImageIcon;

public class Map extends Game {

 public Map(String mapName) {
  ImageIcon icon = new ImageIcon(Game.root + "/resources/backGround/"+mapName+".jpg");
  
  mapDimension = new int[] {icon.getImage().getWidth(null), icon.getImage().getHeight(null)};
  
  respawnLcation = new int[] {0, 0};
 }
}
