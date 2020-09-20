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
	
		
		if(outBound(e.getX(), e.getY(), list.get(0)) == false) 
		{
			//System.out.println(e.getX() +" " +e.getY());
			//System.out.println(list.get(0).x+" " +list.get(0).y);
			//slopeX = Math.abs(Math.round((double)(clickedX - centerX)/(clickedY - centerY)));
			//slopeY = Math.abs(Math.round((double)(clickedY - centerY)/(clickedX - centerX)));
			list.get(0).clickedX = e.getX() + list.get(0).x;
			list.get(0).clickedY = e.getY() + list.get(0).y;
			//System.out.println(list.get(0).clickedX+" " +list.get(0).clickedY);
			
			list.get(0).newClick = true;
			
			list.get(0).north = false;
			list.get(0).south = false;
			list.get(0).west = false;
			list.get(0).east = false;
			
			
			list.get(0).directionCheck = true;
		
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
