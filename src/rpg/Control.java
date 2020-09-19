import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;
public class Control extends Game implements MouseListener{
//	boolean mouseHeld = false;
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		// would be cool to have it so while mouse is held, the character moves with mouse
		if(SwingUtilities.isLeftMouseButton(e)) {
			if(outBound(e.getX(), e.getY()) == false)
			{
			
				clickedX = e.getX() + x;
				clickedY = e.getY() + y;
				System.out.println(x);
				System.out.println(y);
				newClick = true;
			
				north = false;
				south = false;
				west = false;
				east = false;
			
			
				directionCheck = true;
		
			}
		}
		if (SwingUtilities.isRightMouseButton(e)) {
			Display.draw.addArrow(new Arrow(Game.windowX/2, Game.windowY/2, e.getX(), e.getY()));
			System.out.println(e.getX()+ ", " + e.getY());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//mouseHeld = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
