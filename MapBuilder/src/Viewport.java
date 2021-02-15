import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class Viewport extends JPanel
{
	static int pixelW = 100;
	static int pixelH = 100;
//	private int size = 0;
	private static Dimension maxSize = new Dimension(960, MapBuilder.screenH);
	static List<Nodes> nodes = new ArrayList<Nodes>();
	private Control control = new Control(100,100, nodes, maxSize);
	public static int testNode = 25;
    public Viewport()
    {
    	super();
    	System.out.println("LOADING VIEWPORT. . .");	
    	addMouseListener(control);
    	createNodes();
    	super.repaint();
    	setMaximumSize(maxSize);
    //	repaint();
//    	setSize(500,500);
//    	setVisible(true);
    	System.out.println("FINSHED LOADING VIEWPORT");
    }   
    public void paintComponent(Graphics g)    
    {  
    	super.paintComponent(g);
    	//System.out.println("test");
    	for (int x = 0; x <= MapBuilder.screenW; x += pixelW  ) {
    		for(int y = 0; y <= MapBuilder.screenH; y += pixelH ) { 
    			g.drawRect(x, y, pixelW, pixelH);
    			//System.out.println("Test");
    		}
    	}
    	g.drawOval(nodes.get(testNode).getX() - 5, nodes.get(testNode).getY() - 5, 10, 10);
    }
	public static Dimension getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(Dimension maxSize) {
		this.maxSize = maxSize;
	} 
	public void createNodes() {
		for(int y = 0; y < maxSize.getHeight(); y += pixelH){
    		for(int x = 0; x < maxSize.getWidth(); x += pixelW){
    			nodes.add(new Nodes(x, y, maxSize));
    		//	nodes.get(size).setSize(size);
    		//	size = size + 1;
    			
    		}
    	}
	}
}