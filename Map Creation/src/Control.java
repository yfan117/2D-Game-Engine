import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
public class Control implements MouseListener{
	private int pixelW;
	private int pixelH;
	private Dimension maxSize;
	private boolean foundNode;
	private List<Nodes> nodes = new ArrayList<Nodes>();
	public static Nodes targetBrowser;
	public static Nodes targetViewport;
	
	public Control(int pixelW, int pixelH, List nodes, Dimension maxSize){
		this.pixelW = pixelW;
		this.pixelH = pixelH;
		this.setMaxSize(maxSize);
		this.maxSize = maxSize;
		this.nodes = nodes;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		int clickedX = e.getX();
		int clickedY = e .getY();
		int tempX;
		int tempY;
		//System.out.println(nodes.size());
		if (this.pixelW == 100) {
			tempX = (((clickedX + 99) /100) * 100)-this.pixelW;
			tempY = (((clickedY + 99) /100) * 100)-this.pixelH;
			getNode(tempX,tempY,pixelW);
			if(targetBrowser != null) {
				targetViewport.setImage(targetBrowser.getImage());
			}
		}
		else {
			tempX = Math.round(clickedX/60 * pixelW);//(((clickedX + 5) /10) * 10);
			tempY = Math.round(clickedY/60 * pixelH);//(((clickedY + 5) /10) * 10);
			getNode(tempX,tempY,pixelW);
		}
		System.out.println(targetBrowser.getImage());
		System.out.println(targetViewport.getImage());
//		System.out.println(target.getX());
//		System.out.println(target.getY());
		foundNode = false;
		//System.out.println(tempX + ", " + tempY);
		//System.out.println(clickedX + ", " + clickedY);
	}
	public void getNode(int targetX, int targetY, int pixelW) {
			for (int i = 0; i <= nodes.size(); i++) {
				if(foundNode == false) {
					if(nodes.get(i).getX() == targetX && nodes.get(i).getY() == targetY)
					{
						if(pixelW == 60) targetBrowser = nodes.get(i);
						else targetViewport = nodes.get(i);
						foundNode = true;
						
					}
				}
			}
	//	}
	}
	public void paintComponent(Graphics g) {
		//System.out.println(targetBrowser.getImage());
		//Viewport.getComponentGraphics(g);
		
		g.drawImage(targetBrowser.getImage(), targetViewport.getX(), targetViewport.getY(), targetViewport.getX() + 100, targetViewport.getY() + 100, null);
	}
	public void setMaxSize(Dimension maxSize) {
		this.maxSize = maxSize;
	} 
}
