import java.awt.Image;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class Draw extends JPanel{
  
 public Player player;
 
 Image backGround;

 int x = 0;
 int y = 0;
 int picRank;

 int windowX;
 int windowY;

 int picCounter = 0;
 int timeCounter = 0;

 public Draw(int windowX, int windowY, Player _player) {
   
  player = _player;
   
  this.windowX = windowX;
  this.windowY = windowY;

  backGround = new ImageIcon(Game.root + "/resources/backGround/backGround.jpg").getImage();
 }
 /*
 public int getPicRank() {
  return this.picRank;
 }
 */
 public void updateValue(int x, int y, int picRank) {
   
  this.picRank = picRank;

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
  }
}