import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class Control extends Game implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

		
		
		if(outBound(e.getX(), e.getY()) == false)
		{
			
			clickedX = e.getX() + x;
			clickedY = e.getY() + y;
			
			newClick = true;
			
			north = false;
			south = false;
			west = false;
			east = false;
			
			
			directionCheck = true;
		
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
