import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class ClickListener extends MouseAdapter {

	private AttackPanel panel;
	
	
	public ClickListener(AttackPanel panel) {
		super();
		this.panel = panel;
	}


	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getX() + ", " + e.getY());
		panel.addArrow(new Arrow(50,50, e.getX(), e.getY()));
			//get (x,y) posit

		}
	public int getMouseX(MouseEvent e) {
	return e.getX();
	}
	public int getMouseY(MouseEvent e) {
		System.out.println(e.getY());
		return e.getY();
	}
	
}
