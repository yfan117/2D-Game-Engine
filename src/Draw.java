import java.awt.Image;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class Draw extends JPanel{
 
 private Player player;
 Image backGround;
 Image character;
 Image arrowIMG;
 Image bowIMG;
 int x = 0;
 int y = 0;
 int picRank;

 int windowX;
 int windowY;
 
 int picCounter = 0;
 int timeCounter = 0;
 
 static List<Arrow> arrows = new ArrayList<Arrow>();
 
 public void addArrow(Arrow arrow) { 
  arrows.add(arrow);
  this.repaint();
 }
 
 public Draw(int windowX, int windowY, Player _player) {
  player = _player;
   
  this.windowX = windowX;
  this.windowY = windowY;
  
  backGround = new ImageIcon(Game.root + "/resources/backGround/backGround.jpg").getImage();
  character = new ImageIcon(Game.root + "/resources/character.png").getImage();
  arrowIMG = new ImageIcon(Game.root + "/resources/arrow2.png").getImage();
  bowIMG = new ImageIcon(Game.root + "/resources/bow.png").getImage();
 }
 
 /*
 public int getPicRank() {
  return this.picRank;
 }
 */
 
 public void updateValue(int x, int y, int picRank) {
  this.picRank = picRank;
  int mouseXTest;
  int mouseYTest;
  
  if((this.x != x) || (this.y != y)) {
   
   timeCounter ++;
   
   if(timeCounter == 5) {
    timeCounter = 0;
    picCounter ++;
    
    if(picCounter == 4) {
     picCounter = 0;
    }
   }
   /*
    * save this for testing purpose
   if((this.x > x) && ( this.y == y)) {
    this.picRank = 4;
    picCounter = 0;
   }
   else if((this.x < x) && ( this.y == y)) {
    this.picRank = 0;
    picCounter = 0;
   }
   else if((this.x == x) && ( this.y < y)) {
    this.picRank = 6;
    picCounter = 0;
   }
   else if((this.x == x) && ( this.y > y)) {
    this.picRank = 2;
    picCounter = 0;
   }
   */
  }
  else {
   picCounter = 0;
  }

  this.x = x;
  this.y = y;
  
  repaint();
 }
 
 protected void paintComponent(Graphics g) {
  
  super.paintComponent(g);
 
   g.drawImage(backGround,      //name of the image draw 
      0, 0,        //x, y on the panel to draw from
      windowX, windowY,     //x, y on the panel to draw to
      x - windowX/2, y - windowY/2 ,  //x, y on the image to get from
      x + windowX/2, y + windowY/2 ,  //x, y on the image to get to
      null);
   
   g.drawImage(player.getImage(), 
      windowX/2 - 50, windowY/2 - 50, 
      windowX/2 + 50, windowY/2 + 50, 
      picCounter*17, picRank * 17, 
      17*(picCounter+1), picRank * 17 + 17, 
      null);
   
   List<Arrow> arrow = player.getArrows();
   if (arrows.size() > 0) {
    for(Arrow a : arrows) {
     g.drawImage(arrowIMG,Game.windowX/2, Game.windowY/2, null);
     g.drawLine(Game.windowX/2, Game.windowY/2, a.getMouseX(), a.getMouseY());
     updateArrows();
     repaint();
    }
   }
   g.drawImage(bowIMG, windowX/2 - 50, windowY/2 - 50, null);
 }
 
 public void updateArrows() {
  for (int i = 0; i < arrows.size(); i++) {
   Arrow arrow = arrows.get(i);
   if (arrow.isVisible()){
    arrow.move();
   }
   else {
    System.out.println("remove");
    arrow.setExists(false);
   // arrow.setX(Game.centerX);
    //arrow.setY(Game.centerY);
    arrows.remove(i);
    System.out.println(arrows.size());
   }
  }
 }
}