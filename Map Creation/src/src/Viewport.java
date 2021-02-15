package src;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Viewport extends JPanel
{
	static int pixelW = MapBuilder.pixelW;
	static int pixelH = MapBuilder.pixelH;
//	private int size = 0;
	private static Dimension maxSize = new Dimension(MapBuilder.screenW-240, MapBuilder.screenH);
	static List<List<Nodes>> allLayers = new ArrayList<List<Nodes>>();
	static List<Nodes> layer1 = new ArrayList<Nodes>();
	static List<Nodes> layer2 = new ArrayList<Nodes>();
	static List<Nodes> layer3 = new ArrayList<Nodes>();
	public static int offsetX = 0;
	public static int offsetY = 0;
//	private Control control = new Control(pixelW ,pixelH , allLayers, maxSize, allLayers.size());
	public static int testNode = 25;
	public static Nodes currentNode = null;
//	public int drawCount = 0;
	private static int increment = 0;
	private static int col = (MapBuilder.mapW-200)/pixelW;
	private static int row = MapBuilder.mapH/(pixelH / 2);
	public int currRow = 0;
	private List files = new ArrayList();
	private static List<Image> imgList = new ArrayList<Image>();
	final File folders = new File("C:\\Map Creation\\resources");
	public Image grid = new ImageIcon("C:\\Map Creation\\resource\\1_29OYsR6EJl9y9og64HvYBA.png").getImage();
	private static int imageIndex;
	private static int[] gameGrid;
	private static int currentLayer = 0;
	public static boolean saved = false;
    public Viewport(List<List<Nodes>> nodes)
    {
    	super();
    	System.out.println("LOADING VIEWPORT. . .");
    	this.allLayers = nodes;
    	Control control = new Control(pixelW ,pixelH , allLayers, allLayers.size());
    	addMouseListener(control);
    	//setContentPane(null);
    	//createNodes();
    	gameGrid = new int[layer1.size()];
    	super.repaint();
    	setMaximumSize(maxSize);
    	System.out.println(col + "Col, " + row + "Row.");
		System.out.println(folders + " viewport");
		filesInFolder(folders);
		for(int i = 0; i <files.size(); i++) {
			imgList.add(new ImageIcon(folders.getName() + "/" +  files.get(i)).getImage());
			System.out.println(folders.getName() + files.get(i));
			//imgList.set(i, (Image) files.get(i));
		}
		System.out.println(imgList.size());
//		populateEverything();
    //	repaint();
//    	setSize(500,500);
//    	setVisible(true);
    	update();
    	System.out.println("FINSHED LOADING VIEWPORT");
    }   
    
    public void update() {
    	final Timer timer = new Timer();
    	timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if(saved == true) {
					saveImage("yo", ".png");
				}
				repaint();
			}
    		
    	},0,100);
		
    }
    public void paintComponent(Graphics g)    
    {  
    	currRow = 0;
    	super.paintComponent(g);
    	Graphics2D g2d = (Graphics2D)g;	
    	int x = 0;
    	int y = 0;
    	for (x = -MapBuilder.screenW; x <= MapBuilder.screenW*2; x += pixelW  ) {  //DRAWS GRID
    		for(y = -MapBuilder.screenH; y <= MapBuilder.screenH*2; y += pixelH/2 ) {//DRAWS GRID
    			//kinda shitty grid
    			g2d.drawRect(x, y, pixelW, pixelH);
    		}
    	}
    	if(currentNode != null) {
    		for(int j = 0; j < allLayers.size(); j ++) {
    			for(int i = 0; i < allLayers.get(j).size(); i++) {
    				int num = i;//Calculates isometric offset
    				int dig = num % 10;
    				int one;
    				one = dig;
    				dig = num % 100;
    				
    				if(allLayers.get(j).get(i).getWidth() != 0) {//draws tiles
    					if(allLayers.get(j).get(i).getWidth() / allLayers.get(j).get(i).getHeght() == 2) {
    						g.drawImage(allLayers.get(j).get(i).getImage(), 
    								allLayers.get(j).get(i).getXMod((dig-one)/10) + offsetX,
    								allLayers.get(j).get(i).getY()-pixelH/2 + offsetY,
    								pixelW,   pixelH,
    								null);
    					}
    					else { //draws tiles
    						g.drawImage(allLayers.get(j).get(i).getImage(), 
    								allLayers.get(j).get(i).getXMod((dig-one)/10) + offsetX,
    								allLayers.get(j).get(i).getY()-(allLayers.get(j).get(i).getWidth()/2) + offsetY,
    								allLayers.get(j).get(i).getWidth(),   allLayers.get(j).get(i).getHeght(),
    								null);
    					}
    				}
    			}
    		}
    	}
//    	drawCount = 2;    	
    }
    
	public static Dimension getMaxSize() { return maxSize; }
	public void setMaxSize(Dimension maxSize) { this.maxSize = maxSize;	} 
	
	public void createNodes() {
		for(int i = 0; i < allLayers.size(); i++) {//for each layer, create nodes at x,y)
			for(int y = 0; y < maxSize.getHeight(); y += pixelH/2){
	    		for(int x = 0; x < maxSize.getWidth(); x += pixelW){
	    			allLayers.get(i).add(new Nodes(x, y));
	    		}
	    	}
		}
	}
	public static void setNode(Nodes node , Image image, int inc){//Image image, int x, int y, int dx, int dy) {
		currentNode = node;
		currentNode.setImage(image);
		allLayers.get(currentLayer).get(inc).setImage(image);
	}
	public static void setDim(int width, int height, int inc) {
		allLayers.get(currentLayer).get(inc).setWidth(width);
		allLayers.get(currentLayer).get(inc).setHeight(height);
	}
	
	public void filesInFolder(final File folder) {
		int i = 0;
		for (final File fileEntry : folder.listFiles()){
			if (fileEntry.isDirectory()) {
				i = i + 1;
			}
			else {
				files.add(fileEntry.getName());				
			}
		}
	}
	public static int[] generate() {
		for(int i = 0; i < layer1.size(); i++) {
			imageIndex = layer1.get(i).getimageListIndex();
			gameGrid[i] = imageIndex;
		}
		return gameGrid;
	}
	public void saveImage(String name,String type) {
		setSaved(false);
		BufferedImage image = new BufferedImage(MapBuilder.mapW, MapBuilder.mapW, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = image.createGraphics();
		paint(g2);
		
		try{
			ImageIO.write(image, "png", new File("C:\\Map Creation\\image.png"));
			System.out.println("SAVED IMAGE");
		} catch (Exception e) {
			e.printStackTrace();
			}		
		}
		
	public static void setSaved(boolean statement) { saved = statement; }
	public static void showgameGrid() {
		System.out.print("{"); 
		
		for(int i = 0; i < layer1.size(); i++) {
			System.out.print(gameGrid[i] + ", ");
		}
		
		System.out.println("}");
	}
	public static void setLayer(int layer) { currentLayer = layer; }
	public static int getLayer() { return currentLayer; }
	
	public static void incOSX(int x) { offsetX = offsetX - x;	}
	public static int getOSX() { return offsetX;	}
	
	public static void incOSY(int y) { offsetY = offsetY - y; }
	public static int getOSY() { return offsetY; }
	
    public static void populateEverything() {
    	for (int i = 0; i <allLayers.size(); i++) {
    		for (int j = 0; j < allLayers.get(i).size(); j++) {
    			allLayers.get(i).get(j).setImageListIndex(3);
    			allLayers.get(i).get(j).setImage(imgList.get(3));
    		}
    	}
    }
}