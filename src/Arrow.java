import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Arrow extends Sprite {
 
 static Image arrow;
 private final double ARROW_SPEED= 1;
 private final double BOARD_WIDTH= 1280;
 
 private int mouseX;
 private int mouseY;
 private static int clickedXArrow;
 private int clickedYArrow;
 private int startX = getX();
 private int startY = getY();
 private double slopeXArrow;
 private double slopeYArrow;
 private double slope;
 private int angle;
 private int yDisX;
 private int xDisX;
 private double hypotX;
 private double rateX;
 private int yDisY;
 private int xDisY;
 private double hypotY;
 private double rateY;
 //private Timer timer;
 //private boolean exists = false;
 static boolean newClickArrow = true;
 //static int ARROW_SPEED = 10;
 static int moveCounter = 0;
 
 public Arrow(int x, int y, int mouseX, int mouseY) {
   
  super(x, y);
  this.mouseX = mouseX;
  this.mouseY = mouseY;
  initArrow();
  slope();
 }
 
 public int getMouseX() {
   return this.mouseX;
 }
 
 public int getMouseY() {
   return this.mouseY;
 }
 
 public int getX() {
   return this.x;
 }
 
 public int getY() {
   return this.y;
 }
 
 private void initArrow() {
   System.out.println("initArrow");
   loadImage("imgs/arrow2.png");
   // drawImage();
   // getImageDimensions();
 }
 
 public double getArrowSpeed() {
   return ARROW_SPEED;
 }
 
 public int setX(int newX) {
   this.x = newX;
   return newX;
 }
 
 public int setY(int newY) {
   this.y = newY;
   return newY;
 }
 
 public void updatePos(int mouseX, int mouseY, Graphics g) {
   g.drawImage(this.image, (startY - mouseY) / (startX - mouseX), 5, null); 
   System.out.println("test");
 }
 
 public void drawLine(int x, int y, int x1, int y2) {
   //g.drawLine(x, y, x1, y2);
 }
 
 public void move() {
   // System.out.println(mouseX);
   // System.out.println(mouseY);
   //timer.start();
   //slope();
   //double a=Math.atan2(rise,run);
   //y = (int) (y + ARROW_SPEED*Math.cos(a));
   //y = (int) (y + ARROW_SPEED*Math.cos(a));
   // if(mouseX>BOARD_WIDTH)
   //System.out.println(mouseX);
   //System.out.println(mouseY);
   //x = (int) ((650 - mouseX) / ARROW_SPEED);
   //y = (int) ((650 - mouseY) / ARROW_SPEED);
   /*if(mouseX >= Game.windowX/2-78/2) {
    x = (int) (x + ARROW_SPEED);
    System.out.println(x);
    }
    else if(mouseX < Game.windowX/2-78/2) {
    x = (int) (x - ARROW_SPEED);
    //System.out.println("bugged");
    }
    
    //if (mouseY > Game.windowY)
    this.y-=ARROW_SPEED;*/
   //System.out.println(x);
   //if(x>mouseY)
   
   //timer = new Timer(1000, new ActionListener() {
   
   //@Override
   // public void actionPerformed(ActionEvent evt) {
   //  System.out.println("timer update");  
   if (this.x <= 0 || this.x >= 1280 || this.y <= 0 || this.y >= 720){
     this.visible = false;
     //timer.stop();
   }
   //int timer = 200;
   //while (exists = true) {
   // try {
   //  Thread.sleep(timer);
   // }catch(InterruptedException e) {
   //  e.printStackTrace();
   // }
   
   for(moveCounter = 0; moveCounter< ARROW_SPEED; moveCounter++) {
     if(slopeXArrow >slopeYArrow) {
       for(int j = 0; j <=slopeXArrow; j++) {
         updateX();
         if(clickedXArrow ==Game.centerX) {
           break;
         }
         
         if(moveCounter == ARROW_SPEED) {
           break;
         }
         
         if (x <= 0 || x >= 1280 || y <= 0 || y >= 720) {
           visible = false;
         }
         updateY();
       }
     }
     else if (slopeXArrow <slopeYArrow) {
       for(int j = 0; j <=slopeYArrow; j++) {
         updateY();
         if(clickedYArrow ==Game.windowY) {
           break;
         }
         
         if(moveCounter == ARROW_SPEED) {
           break;
         }
         
         if (x <= 0 || x >= 1280 || y <= 0 || y >= 720){
           visible = false;}
       }
       updateX();
     }
     else if(slopeXArrow ==slopeYArrow) {
       for(int j = 0; j <=slopeYArrow+1; j++) { 
         updateX();
         updateY();
         if(moveCounter == ARROW_SPEED) {
           break;
         }
         
         if (x <= 0 || x >= 1280 || y <= 0 || y >= 720){
           visible = false;
         }
       }
     }
   }
   if((clickedXArrow ==Game.centerX)&&(clickedYArrow ==Game.windowY)) {
     newClickArrow = false;
     if (x <= 0 || x >= 1280 || y <= 0 || y >= 720){
       visible = false;
     }
   }
 }
 //}
 
 // });
 
 //  }
 public void updateX() {
   
   if(clickedXArrow < Game.windowX/2) {
     //Game.centerX --;
     // System.out.println(x + "x--");
     //x --;
     xDisX = 600-mouseX;// : x distance from player to mouseX
     yDisX = Game.windowY-mouseY;// = y distance from plyare to mouseY
     hypotX = Math.sqrt(((xDisX)*(xDisX)) + ((yDisX)*(yDisX)));// = hypotneus
     rateX = Math.cos(xDisX / hypotX)*10;
     // System.out.println(rateX);
     // System.out.println(hypotX);
     // System.out.println(xDisX);
     // System.out.println(yDisX);
     x = (int) (x + rateX);
     x--;
     // for(int i = 0; i < rateX; i++) {
     //  x++;
     //  } 
     // System.out.println(x + "left");
     moveCounter++;
   }
   else if(clickedXArrow > Game.windowX/2) {
     xDisX = mouseX-600;// : x distance from player to mouseX
     yDisX = Game.windowY-mouseY;// = y distance from plyare to mouseY
     hypotX = Math.sqrt(((xDisX)*(xDisX)) + ((yDisX)*(yDisX)));// = hypotneus
     rateX = (Math.cos(xDisX / hypotX))*10;
     //System.out.println(rateX);
     //System.out.println(hypotX);
     //System.out.println(xDisX);
     //System.out.println(yDisX);
     //x = (int) (x + rateX);
     x++;
     //for(int i = 0; i < rateX; i++) {
     //  x++;
     //  } 
     moveCounter++;
     // System.out.println(x + "right");
   }
 }
 
 public void updateY() {
   //Game.windowY --;
   if (clickedYArrow > Game.windowY/2) {
     xDisY = mouseX-600;// : x distance from player to mouseX
     yDisY = Game.windowY-mouseY;// = y distance from plyaer to mouseY
     hypotY = Math.sqrt(((xDisY)*(xDisY)) + ((yDisY)*(yDisY)));//=hypotneus
     rateY = (Math.sin(yDisY / hypotY))*10;
     
     // for(int j = 0; j < rateY; j++) {
     //  y++;
     // }
     y++;
     //y = (int) (y + rateY);
     moveCounter++;
     // System.out.println(y);
     //System.out.println(rateY);
   }else if (clickedYArrow <Game.windowY/2){
     xDisY = mouseX-600;// : x distance from player to mouseX
     yDisY = Game.windowY-mouseY;// = y distance from plyaer to mouseY
     hypotY = Math.sqrt(((xDisY)*(xDisY)) + ((yDisY)*(yDisY)));//=hypotneus
     rateY = (Math.sin(yDisY / hypotY))*10;
     
     //  for(int j = 0; j < rateY; j++) {
     //   y++;
     //  }
     y--;
     //y = (int) (y + rateY);
     moveCounter++;
     //  System.out.println(y);
   }
 }
 
 public void udpatePos() {
   for(int i = 0; i < rateX; i++) {
     x++;
   } 
 }
 
 public void slope() {
   clickedXArrow = mouseX;
   
   clickedYArrow = mouseY;
   slopeXArrow = rateX;
   slopeYArrow = rateY;
 }
}

