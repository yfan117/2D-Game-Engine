import java.awt.*; 
import javax.swing.*; 
import javax.swing.JFrame; 
import java.awt.geom.Line2D; 
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Attack {
	static int fps = 30;
	static int x = 0;
	static int y = 0;
	static int clickedX = -99;
	static int clickedY = -99;

	static int windowX = 1000;
	static int windowY = 700;
	static int centerX = windowX /2 ;
	static int centerY = windowY /2 ;
	//static Attack test = new Attack();
	static AttackPanel panel = new AttackPanel();
	
    public static void main(String[] args) {
	    JFrame window = new JFrame("Attack");
	    //window.getContentPane().addMouseListener(new ClickListener());
	    
	    window.setContentPane(panel);
	    
	    panel.addMouseListener(new ClickListener(panel));//adds ClickListener to panel
	    window.setSize(1000,700);
	    window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    window.setVisible(true);
/*	    window.addMouseListener(new MouseAdapter() {
	    	@Override
		public void mousePressed(MouseEvent e) {
		
		clickedX = e.getX() + x;
		clickedY = e.getY() + y;
		
		//System.out.println(clickedX + "," + clickedY);
		//test.update(clickedX, clickedY);
		}
});
	   // panel.gameLoop();
    }*/
    }
    public static int[] mouseClick() {
    	int[] arr = new int[2];
    	PointerInfo ab = MouseInfo.getPointerInfo(); //describes the location of where the mouse is
		Point attack = ab.getLocation(); //gets the location of mouse
		//add if statement for left click or rightclick is active to attack
		int mouseX = (int) attack.getX(); //put in if statement
		int mouseY = (int) attack.getY(); //put in if statement
		arr[0] = mouseX;
		arr[1] = mouseY;
		return arr;
		//get (x,y) position of character
		//(enemyY-charY)/(enemyX-charX)
		//create method to create a line
    	}

}