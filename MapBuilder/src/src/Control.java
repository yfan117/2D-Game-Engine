package src;
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
	private List<List<Nodes>> nodesVP = new ArrayList<List<Nodes>>();
	public static Nodes targetBrowser;
	public static Nodes targetViewport;
	public boolean keyPress = false;
	private int inc = 0;
	private int tempX;
	private	int tempY;
	private int size;
	public Control(int pixelW, int pixelH, List<List<Nodes>> nodesVP, int size){
		this.pixelW = pixelW;
		this.pixelH = pixelH;
		this.setMaxSize(maxSize);
//		this.maxSize = maxSize;
		this.nodesVP = nodesVP;
		this.size = size;
	}
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		int clickedX = e.getX();//;-Viewport.getOSX();
		int clickedY = e .getY();//-Viewport.getOSY();

		if (pixelW == Viewport.pixelW) { //only for viewport
			//nodesVP = Viewport.allLayers;
			size = Viewport.allLayers.size();
			clickedX = clickedX-Viewport.getOSX();
			clickedY = clickedY-Viewport.getOSY();
			if(pixelW == Browser.pixelW && pixelH == Browser.pixelH) {
				tempX = ((((clickedX + Browser.pixelW)/Browser.pixelW) * Browser.pixelW) - this.pixelW); //Changes the X distance between tiles
				tempY = ((((clickedY + ((Browser.pixelH/2)-1)) / (Browser.pixelH/2)) * (Browser.pixelH/2))-this.pixelH);//Changes the distance between
				
			}
			else {
//				tempX = ((((clickedX + Viewport.getLayerWidth())/Viewport.getLayerWidth()) * Viewport.getLayerWidth()) - this.pixelW); //Changes the X distance between tiles
//				tempY = ((((clickedY + ((Viewport.getLayerHeight(Viewport.getLayer())/2)-1)) / (Viewport.getLayerHeight(Viewport.getLayer())/2)) * (Viewport.getLayerHeight(Viewport.getLayer())/2))-this.pixelH);//Changes the distance between
				tempX = Viewport.getLayerWidth(Viewport.getLayer())*(Math.round(clickedX/Viewport.getLayerWidth(Viewport.getLayer())));
				tempY = Viewport.getLayerHeight(Viewport.getLayer())*(Math.round(clickedY/Viewport.getLayerHeight(Viewport.getLayer())));
			}
			System.out.println("Temp X: " + tempX + " " + "Temp Y: "+ tempY);
			getNode(tempX,tempY,pixelW); //finds the node that was clicked in the viewport, sets this as targetViewport
			
			if(targetBrowser != null) { //checks if there is a node selected in the browser
				keyPress = true;
				Viewport.setNode(targetViewport, targetBrowser.getImage(), inc); //sets the node in Viewport to targetViewport and have the targetBrowser image
				targetViewport.setImageListIndex(targetBrowser.getimageListIndex());
				
				if(targetBrowser.getimageListIndex() >= 0) { //allows to delete multiple tiles instead of just one
//					System.out.println("TARGET BROWSER " + targetBrowser.getimageListIndex());
					Viewport.setDim(nodesVP.get(Viewport.getLayer()).get(inc).getImage().getWidth(null),
									nodesVP.get(Viewport.getLayer()).get(inc).getImage().getHeight(null),
									inc);
				}
				nodesVP.get(Viewport.getLayer()).get(inc).setImageListIndex(targetBrowser.getimageListIndex());
//				System.out.println(nodesVP.get(Viewport.getLayer()).get(inc).getWidth() + ", " + nodesVP.get(Viewport.getLayer()).get(inc).getHeght());
			}
			else { //if targetBrowser is null, then replace that node with nothing, essentially an eraser
				nodesVP.get(Viewport.getLayer()).get(inc).setImageListIndex(-1);
				nodesVP.get(Viewport.getLayer()).get(inc).reset();
			}
		}
		else {//cliked browser
			tempX = Math.round(clickedX/60 * pixelW);//(((clickedX + 5) /10) * 10);
			tempY = Math.round(clickedY/30 * pixelH);//(((clickedY + 5) /10) * 10);
			getNode(tempX,tempY,pixelW); //finds the node that was clicked
			keyPress = true;
		}
//		System.out.println(targetBrowser.getImage());
		//System.out.println(targetViewport.getImage());
		foundNode = false;
		keyPress = false;
	}
	
	public boolean getNode(int targetX, int targetY, int pixelW) {
				int i = 0;
				while(foundNode == false) {
					i++;
					if(size > 1) { //Only works for viewport
//						System.out.println(nodesVP.size());
//						System.out.println("Target X: " + targetX + "; X: " + nodesVP.get(Viewport.getLayer()).get(i).getX()
//								+ " Offset X: " +  nodesVP.get(Viewport.getLayer()).get(i).getOffsetX()
//								+ " Y: " + nodesVP.get(Viewport.getLayer()).get(i).getY() + " targetY: " + targetY);
						if((nodesVP.get(Viewport.getLayer()).get(i).getX() == targetX && nodesVP.get(Viewport.getLayer()).get(i).getY() == targetY && pixelW != Browser.pixelW)){
							//iterates through every node and checks if the target x and y are equal the viewport nodes at index (i)
							//if(pixelW == 60) { targetBrowser = nodes.get(i); inc = i; return foundNode = true;} //System.out.println(nodes.get(i));} //Browser
//	    					int num = i; //calculates offset of tiles
//	    					int dig = num % 10;
//	    					int one;
//	    					one = dig;
//	    					dig = num % 100;
							System.out.println(nodesVP.get(Viewport.getLayer()).get(i).getOffsetX() + " x TEST");
							System.out.println((nodesVP.get(Viewport.getLayer()).get(i).getX()+(MapBuilder.pixelW/2)) + ": MOD STATEMENT");
							System.out.println(i + "=i; (" + nodesVP.get(Viewport.getLayer()).get(i).getX() + ", " //Displays information about X the calculated x value
												 + nodesVP.get(Viewport.getLayer()).get(i).getY() + ")" //displays calculated y value
											     + " (" + targetX + ", " + targetY + ") " // displays target the target x and y
												 + pixelW + "=pixelW " + MapBuilder.pixelW + "=mapBuilder " + "Size = " + size
												 + ", Offset X= " + nodesVP.get(Viewport.getLayer()).get(i).getOffsetX());
							targetViewport = nodesVP.get(Viewport.getLayer()).get(i);
							inc = i; foundNode = true;
						}
					}
					else { //clicked on browser
//						System.out.println(nodesVP.get(0).get(i) + "YO");
						if((nodesVP.get(0).get(i-1).getX() == targetX && nodesVP.get(0).get(i-1).getY() == targetY && pixelW == Browser.pixelW)) {
							System.out.println(targetX + "");
							 targetBrowser = nodesVP.get(0).get(i-1); inc = i-1; foundNode = true; //System.out.println(nodes.get(i));} //Browser
						}
					}
				}
				return foundNode;
	}
	
	public void setMaxSize(Dimension maxSize) {
		this.maxSize = maxSize;
	} 
}
