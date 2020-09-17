package rpg;

import java.awt.Graphics;
import java.awt.Image;

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
		//slope();
		//double a=Math.atan2(rise,run);
		//y = (int) (y + ARROW_SPEED*Math.cos(a));
		//y = (int) (y + ARROW_SPEED*Math.cos(a));
	//	if(mouseX>BOARD_WIDTH)
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
		if (x <= 0 || x >= 1280 || y <= 0 || y >= 720){
				visible = false;
		}
				for(moveCounter = 0; moveCounter< ARROW_SPEED; moveCounter++) 
				{
					if(slopeXArrow >slopeYArrow)
					{
						for(int j = 0; j <=slopeXArrow; j++)
						{
							updateX();
							if(clickedXArrow ==Game.centerX) {
								break;
							}
							if(moveCounter == ARROW_SPEED) {
								break;
							}
						}
						updateY();
					}
					else if (slopeXArrow <slopeYArrow){
						for(int j = 0; j <=slopeYArrow; j++) {
							updateY();
							if(clickedYArrow ==Game.windowY) {
								break;
							}
							if(moveCounter == ARROW_SPEED) {
								break;
							}
						}
						updateX();
					}
					else if(slopeXArrow ==slopeYArrow) 
					{
						for(int j = 0; j <=slopeYArrow+1; j++) 
						{	
							updateX();
							updateY();
							if(moveCounter == ARROW_SPEED) {
								break;
							}
						}
					}
				}
				if((clickedXArrow ==Game.centerX)&&(clickedYArrow ==Game.windowY)) {
					newClickArrow = false;
				}
			}
	public void updateX() {
		
		if(clickedXArrow < Game.centerX) {
			//Game.centerX --;
		//	System.out.println(x + "x--");
			//x --;
			xDisX = 600-mouseX;// : x distance from player to mouseX
			yDisX = Game.windowY-mouseY;// = y distance from plyare to mouseY
			hypotX = Math.sqrt(((xDisX)*(xDisX)) + ((yDisX)*(yDisX)));// = hypotneus
			rateX = Math.cos(xDisX / hypotX);
		//	System.out.println(rateX);
		//	System.out.println(hypotX);
		//	System.out.println(xDisX);
		//	System.out.println(yDisX);
			x = (int) (x + rateX);
		//	x++;
			System.out.println(x);
			moveCounter++;
			
		}
		else if(clickedXArrow > Game.centerX) {
			xDisX = mouseX-600;// : x distance from player to mouseX
			yDisX = Game.windowY-mouseY;// = y distance from plyare to mouseY
			hypotX = Math.sqrt(((xDisX)*(xDisX)) + ((yDisX)*(yDisX)));// = hypotneus
			rateX = Math.cos(xDisX / hypotX);
		//	System.out.println(rateX);
			//System.out.println(hypotX);
			//System.out.println(xDisX);
			//System.out.println(yDisX);
			x = (int) (x + rateX);
			moveCounter++;
		//	System.out.println(x);
		}
	}
	
	public void updateY() {
			//Game.windowY --;
			xDisY = mouseX-600;// : x distance from player to mouseX
			yDisY = Game.windowY-mouseY;// = y distance from plyaer to mouseY
			hypotY = Math.sqrt(((xDisY)*(xDisY)) + ((yDisY)*(yDisY)));//=hypotneus
			rateY = Math.sin(yDisY / hypotY);
			y = y-1;
			//y = (int) (y + rateY);
			moveCounter++;
			//System.out.println(y);
			//System.out.println(rateY);
	}
	
	public void slope() {
		clickedXArrow = mouseX;
		
		clickedYArrow = mouseY;
		slopeXArrow = rateX;
		slopeYArrow = rateY;

	}
}

