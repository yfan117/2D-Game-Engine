import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class Display extends Game {

 static int picRank = 6;
 
 public Player player;

 Draw draw;

 public Display(Player _player) {

  player = _player;
  draw = new Draw(windowX, windowY, player);
   
  JFrame frame = new JFrame();
  frame.setSize(windowX, windowY);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setLocationRelativeTo(null);
  frame.setResizable(false);

  frame.getContentPane().add(draw);

  Control control = new Control();
  draw.addMouseListener(control);

  frame.setVisible(true);
 }

 public static void getDirection() {

  int differenceX = Math.abs(centerX-clickedX);
  int differenceY = Math.abs(centerY-clickedY);

  if((differenceX < 75) && (centerY < clickedY)) {

   south = true;
  }
  else if((differenceX < 75) && (centerY > clickedY)) {

   north = true;
  }
  else if((differenceY < 75) && (centerX < clickedX)) {

   east = true;
  }
  else if((differenceY < 75) && (centerX > clickedX)) {

   west = true;
  }
  else if((differenceX >= 75) && (centerY < clickedY) && (centerX < clickedX)) {

   south = true;
   east = true;
  }
  else if((differenceX >= 75) && (centerY < clickedY) && (centerX > clickedX)) {

   south = true;
   west = true;
  }
  else if((differenceX >=  75) && (centerY > clickedY)&& (centerX < clickedX)) {

   north = true;
   east = true;
  }
  else if((differenceX >=  75) && (centerY > clickedY)&& (centerX > clickedX)) {

   north = true;
   west = true;
  }

  /*
   * save this for testing purposes
  System.out.println();
  System.out.println("north: "+north);
  System.out.println("south: "+south);
  System.out.println("west: "+west);
  System.out.println("east: "+east);
  */
  if((north == true) && (west == true)) {
   picRank = 3;
  }
  else if((north == true) && (east == true)) {
   picRank = 1;
  }
  else if((south == true) && (west == true)) {
   picRank = 5;
  }
  else if((south == true) && (east == true)) {
   picRank = 7;
  }
  else if(north == true) {
   picRank = 2;
  }
  else if(south == true) {
   picRank = 6;
  }
  else if(west == true) {
   picRank = 4;
  }
  else if(east == true) {
   picRank = 0;
  }
  directionCheck = false;
 }

 public void update(int x, int y) {

  if(directionCheck == true) {
   getDirection();
  }
  draw.updateValue(x, y, picRank);
  //picRank = draw.getPicRank();
 }
}
