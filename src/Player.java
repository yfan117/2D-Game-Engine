import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.List;

public class Player extends Sprite {
  private int hitpoints;
  private int weapon;//Index to array of weapons
  private Image image;
  private boolean canAttack;
  private boolean canMove;
  private static List<Arrow> arrows;
  
  public Player() {
    super(0, 0);
    this.exists = true;
    this.hitpoints = 10;
    this.weapon = 0;
    this.image = new ImageIcon(Game.root + "/resources/character.png").getImage();
    this.canAttack = true;
    this.canMove = true;
  }
  
  public Player(int n) {//Start the player with a weapon
    super(0, 0);
    this.weapon = n;
  }
  
  public Image getImage() {
    return this.image;
  }
  
  public boolean canMove() {
    return this.canMove;
  }
  
  public List<Arrow> getArrows() {//move to char make non static
        return arrows;
  }
}