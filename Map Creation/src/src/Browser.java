package src;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Browser extends JPanel {
	static int pixelW = 60; //Floor tile width
	static int pixelH = 30; //Floor tile height
	static final File folders = new File("C:\\Map Creation\\test1");//Gets folder with tiles for game
	private List files = new ArrayList();
	static List<Image> imgList = new ArrayList<Image>();
	static List<List<Nodes>> nodesB = new ArrayList<List<Nodes>>();
	static List<Nodes> nodes = new ArrayList<Nodes>();
	int x = 0;
	int y = 0;
	int drawCount = 0;
	private static Dimension maxSize = new Dimension(240, MapBuilder.screenH); //Changes browser width & height
	static int imageIndexSize;
	
	public Browser() {
		super();
		System.out.println("LOADING BROSWER...");
		Control control = new Control(60,30, nodesB, nodesB.size());
		addMouseListener(control);;//adds clickability to browser *probably not a good way to do this xd*
		setMaximumSize(maxSize);
		nodesB.add(nodes);
		createNodes(); //creates nodes for images
		System.out.println(folders);
		filesInFolder(folders); //gets the images in the folder
		
		for(int i = 0; i <files.size(); i++) { //Creates a list of images called imgList and sets the images from folder 
			imgList.add(new ImageIcon(folders.getName() + "/" +  files.get(i)).getImage());
			System.out.println(folders.getName() + files.get(i));
			imageIndexSize++;
			//imgList.set(i, (Image) files.get(i));
		}
		if (drawCount== 0 ) {
		repaint();
	}
		System.out.println("FINISHED LOADING BROSWER");
	}
	
	public void filesInFolder(final File folder) {
		int i = 0;
		for (final File fileEntry : folder.listFiles()){
			if (fileEntry.isDirectory()) {
				i = i + 1;
				//System.out.println(files.get(i) + "YO");
			}
			else {
				files.add(fileEntry.getName());
				System.out.println(fileEntry.getName());
				
			}
		}
	}
	public void paintComponent(Graphics g) {
		drawCount++;
		super.paintComponent(g);
		System.out.println(files.size());
		//draws border
		g.drawRect(0, 0, 300, MapBuilder.screenH);
		//draws grid for each image
		g.setColor(Color.LIGHT_GRAY);
		for(int x = 0; x <= maxSize.getWidth(); x = x + 60) {
			for(int y = 0; y <= maxSize.getHeight(); y = y + 30) {
				g.drawRect(x, y, 60, 30);
			}
		}
		//draws each image that is in folder
		for(int i = 0; i < files.size(); i++) { 
			g.drawImage(imgList.get(i), x, y, 60, 30, null);
			System.out.println(files.get(i)+ " " + x + ", " + y);
			x = x + 60;
			if(x >= maxSize.getWidth()) {
				y = y + 30;
				x = 0;
			}
			y = y;
			nodesB.get(0).get(i).setImage(imgList.get(i)); //sets the image of node(i) to the corresponding image in imgList 
			nodesB.get(0).get(i).setImageListIndex(i);
		}
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
    			nodesB.get(0).add(new Nodes(x, y));
    		//	nodes.get(size).setSize(size);
    		//	size = size + 1;
    			
    		}
    	}
	}
	
}
