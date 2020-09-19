import java.awt.Image;
import javax.swing.ImageIcon;

public class Player {
  private int hitpoints;
  private int weapon;//Index to array of weapons
  private Image image;
  private boolean canAttack;
  private boolean canMove;
  
  public Player() {
    this.hitpoints = 10;
    this.weapon = 0;
    this.image = new ImageIcon(Game.root + "/resources/backGround/character.png").getImage();
    this.canAttack = true;
    this.canMove = true;
  }
  
  public Player(int n) {//Start the player with a weapon
    super();
    this.weapon = n;
  }
  
  public Image getImage() {
    return this.image;
  }
  
  public boolean canMove() {
    return this.canMove;
  }
}