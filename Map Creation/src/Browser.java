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
	private int pixelW = 60;
	private int pixelH = 60;
	final File folders = new File("C:\\Map Creation\\resources");
	private List files = new ArrayList();
	private List<Image> imgList = new ArrayList<Image>();
	static List<Nodes> nodes = new ArrayList<Nodes>();
	int x = 0;
	int y = 0;
	int drawCount = 0;
	private Control control = new Control(60,60, nodes, maxSize);
	private static Dimension maxSize = new Dimension(240, MapBuilder.screenH);
	
	public Browser() {
		super();
		System.out.println("LOADING BROSWER...");
		addMouseListener(control);
		setMaximumSize(maxSize);
		createNodes();
		System.out.println(folders);
		filesInFolder(folders);
		for(int i = 0; i <files.size(); i++) {
			imgList.add(new ImageIcon(folders.getName() + "/" +  files.get(i)).getImage());
			System.out.println(folders.getName() + files.get(i));
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
			for(int y = 0; y <= maxSize.getHeight(); y = y + 60) {
				g.drawRect(x, y, 60, 60);
			}
		}
		//draws each image in /resources
		for(int i = 0; i < files.size(); i++) { 
			g.drawImage(imgList.get(i), x, y, 60, 60, null);
			System.out.println(x);
			x = x + 60;
			if(x > maxSize.getWidth()) {
				y = y + 60;
			}
			y = y;
			nodes.get(i).setImage(imgList.get(i));
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
    			nodes.add(new Nodes(x, y,maxSize));
    		//	nodes.get(size).setSize(size);
    		//	size = size + 1;
    			
    		}
    	}
	}
}
