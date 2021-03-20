package src;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.Timer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
//import org.json.*;
//import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MapBuilder extends JFrame {
	private static JTextField filename = new JTextField(); //sets file name
	public File f;
	static JSONArray arr1 = new JSONArray();
	private JTextField dir1 = new JTextField();
	public boolean foundNode = false;
	
	static int screenW = 1200;
	static int screenH = 1000;
	static int pixelW = 500;
	static int pixelH = 250;
	static int mapW = 25000;
	static int mapH = 25000;
	static Dimension screen = new Dimension(960,1000); //Viewport size
	
	static List<List<Nodes>> nodes = new ArrayList<List<Nodes>>(); //holds layer 1-3
	static List<Nodes> layer12 = new ArrayList<Nodes>(); //Hold nodes of layer 1
	static List<Nodes> layer22 = new ArrayList<Nodes>(); //Hold nodes of layer 2
	static List<Nodes> layer32 = new ArrayList<Nodes>(); //Hold nodes of layer 3
	
    private JMenuBar jMenuBar;//Menu bar
    private JMenu jMenu;
    private JMenuItem newMenuItem;//new file
    private JMenuItem openMenuItem;//open file
    private JMenuItem saveMenuItem;//save
    private JMenuItem closeMenuItem;//exit
    
    private static  JMenu layer;//layers
    private List<JMenuItem> layerList = new ArrayList<JMenuItem>();
    //private HashMap<JMenuItem, Integer> layerList;
    private static int layerListSize = 2;
    private JMenuItem layer1;//switch to layer 1
    private JMenuItem layer2;//switch to layer 2
    private JMenuItem layer3;//switch to layer 3
    private JMenu addLayer;
    private static JMenuItem addLayerVar;
    
    private JMenu moveMap;
    private JMenuItem up;
    private JMenuItem down;
    private JMenuItem left;
    private JMenuItem right;
    
    private JSONArray ja = new JSONArray();
    private JSONObject jo = new JSONObject();
    
    static String dir = System.getProperty("user.dir");
    JFrame jf = new JFrame();

    JPanel container = new JPanel();
    Browser broswer = new Browser();
    public int[] gameGrid;
    String mapName;
	public static int totalLayers = 0;
    static Viewport viewport;
    
    @SuppressWarnings("deprecation")
	public MapBuilder()
    {     	
    	//    	layerList.add(layer1);
//    	layerList.add(layer2);
//    	layerList.add(layer3);
//    	System.out.println(" YO TO OYO " + layerListSize);
    	jMenuBar = new JMenuBar();
    	jf.setTitle("Map Editor"); //sets file title
    	jf.setSize(screenW, screenH);//sets screen size
    	jf.setLocationRelativeTo(null);
        jf.setVisible(true);
   
       //set map name
        jMenu = new JMenu("File");//Shows as File in the menu bar
        layer = new JMenu("Layer");//shows as Layer in menu bar
        newMenuItem = new JMenuItem(new AbstractAction("Export as JSON") {//exports the file to a JSON file
        	@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
        		JSONObject mapDetails = new JSONObject();
        		JSONObject mapObject = new JSONObject();
        		mapDetails.put("Map", "map title 1");//currently temporary, will change so you can choose this name
        		mapDetails.put("map_Width", screenW);//Outputs map width
        		mapDetails.put("map_Height", screenH);//Outputs map Height
        		mapDetails.put("tile_Width", pixelW);//outputs the width of floor tiles
        		mapDetails.put("tile_Height", pixelH);//outputs height of floor tiles
        		mapDetails.put("layerSize", totalLayers);
        		mapObject.put("Map", mapDetails);
        		JSONObject layerDetails = new JSONObject();
        		JSONObject layerObject = new JSONObject();
        		JSONArray layerList = new JSONArray();
        		
        		//Below outputs information for each node into the JSON
        		for(int i = 0; i < Viewport.allLayers.size(); i++) {//layer
//        			layerDetails.put("layer", i);
        			layerObject = new JSONObject();
        			layerDetails = new JSONObject();
        			layerDetails.put("node_num", 1);
        			layerDetails.put("layer", i);  
        			layerDetails.put("layerW", Viewport.layerWH.get(i).get(0));
        			layerDetails.put("layerH", Viewport.layerWH.get(i).get(1));
        			layerDetails.put("image_index", -1);
        			layerDetails.put("x", Viewport.layerWH.get(i).get(0));
        			layerDetails.put("offset_x", 0);
        			layerDetails.put("y",  Viewport.layerWH.get(i).get(1));
        			layerObject.put("Tile", layerDetails);
        			layerList.add(layerObject);
        			for(int j = 0; j < Viewport.allLayers.get(i).size(); j++) {//node
	        			if(Viewport.allLayers.get(i).get(j).getimageListIndex() !=-1) {
		        			layerObject = new JSONObject();
		        			layerDetails = new JSONObject();
		        			layerDetails.put("node_num", j);
		        			layerDetails.put("layer", i);  
		        			layerDetails.put("layerW", Viewport.layerWH.get(i).get(0));
		        			layerDetails.put("layerH", Viewport.layerWH.get(i).get(1));
		        			layerDetails.put("image_index", Viewport.allLayers.get(i).get(j).getimageListIndex());
		        			layerDetails.put("x", Viewport.allLayers.get(i).get(j).getX());
		        			layerDetails.put("offset_x", Viewport.allLayers.get(i).get(j).getOffsetX());
		        			layerDetails.put("y",  Viewport.allLayers.get(i).get(j).getY());
		        			layerObject.put("Tile", layerDetails);
		        			layerList.add(layerObject);
		        			System.out.println(i + " " + j + " " +Viewport.layerWH.get(i).get(0)//i j w h imgindex x y
		        			+ " " + Viewport.layerWH.get(i).get(1) + " " + Viewport.allLayers.get(i).get(j).getimageListIndex()
		        			+ " " +  Viewport.allLayers.get(i).get(j).getX() + " " +  Viewport.allLayers.get(i).get(j).getY());
//		        			layerList.put(layerObject);
	        			}
        			}
//        			layerObject.put("Layer", layerDetails);
        		}
        		
        		JSONObject spriteDetails = new JSONObject();
        		JSONObject spriteOBJ = new JSONObject();
        		JSONArray spriteList = new JSONArray();       		
        		//below outputs the information about the images used to create the map
        		for(int i = 0; i < Browser.imageIndexSize; i++) {
        			spriteOBJ = new JSONObject();
        			spriteDetails = new JSONObject();
           			spriteDetails.put("Picture", (Browser.folders.toString() + "\\" + Browser.files.get(i)).toString());
        			spriteDetails.put("index", i);
        			spriteDetails.put("width", (Browser.imgList.get(i).getWidth(null)));
        			spriteDetails.put("height",(Browser.imgList.get(i).getHeight(null)));
        			spriteOBJ.put("Sprite", spriteDetails);
        			spriteList.add(spriteOBJ);
//        			spriteList.put(spriteOBJ);
        		}
        		 	spriteOBJ.put("Sprite", spriteDetails);
        		JSONObject jo1 = new JSONObject();
        		JSONArray ja1 = new JSONArray();
        		jo1.put("mapObject", mapObject);
        		jo1.put("layerList", layerList);
        		jo1.put("spriteList", spriteList);
        		try(FileWriter file = new FileWriter("test2.json")){//temporary; will change so user could create their own name
        			file.write(jo1.toString());
        			file.flush();
        		} catch (IOException e1) {
        			e1.printStackTrace();
        		}
        		System.out.println("SAVED JSON");
        	}
        });
        
       layerMenu();
        
        openMenuItem = new JMenuItem(new AbstractAction("Import JSON") {//imports a json file into editor *currently doesn't work*
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser c = new JFileChooser();
				// Demonstrate "Open" dialog:
				int rVal = c.showOpenDialog(MapBuilder.this);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					filename.setText(c.getSelectedFile().getName());
					//dir1.setText(c.getCurrentDirectory().toString());
					f = c.getSelectedFile();
//					System.out.println(f.getPath());
//					System.out.println(filename.getText());
					
					try {
						parseJSON(f.toString());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if (rVal == JFileChooser.CANCEL_OPTION) {
					filename.setText("You pressed cancel");
					dir1.setText("");
				}
			}		
        });
        
        saveMenuItem = new JMenuItem(new AbstractAction("Save as Image") {//Saves map as image *mostly doesnt work*
			@Override
			public void actionPerformed(ActionEvent e) {
				Viewport.setSaved(true);
				// TODO Auto-generated method stub
				String name = "yo";
				String type = ".jpg";
				BufferedImage image = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);
				Graphics2D g2 = image.createGraphics();
				paint(g2);
				try{
					ImageIO.write(image, type, new File(name+"."+type));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			});
        closeMenuItem = new JMenuItem("Close");
        
        jMenu.add(newMenuItem);
        jMenu.add(openMenuItem);
        jMenu.add(saveMenuItem);
        jMenu.add(closeMenuItem);
        
        
        //Don't really need "Move the Map" since we can move with arrow keys now
        moveMap = new JMenu("Move the Map");
        left = new JMenuItem(new AbstractAction("Right") {
        	public void actionPerformed(ActionEvent e) {
        		Viewport.incOSX(pixelW);
        	}
        });
        up = new JMenuItem(new AbstractAction("Down") {
        	public void actionPerformed(ActionEvent e) {
        		Viewport.incOSY(pixelH);
        	}
        });
        right = new JMenuItem(new AbstractAction("Left") {
        	public void actionPerformed(ActionEvent e) {
        		Viewport.incOSX(-pixelW);
        	}
        });
        down = new JMenuItem(new AbstractAction("Up") {
        	public void actionPerformed(ActionEvent e) {
        		Viewport.incOSY(-pixelH);
        	}
        });
        moveMap.add(down);
        moveMap.add(left);
        moveMap.add(right);
        moveMap.add(up);
        
        jMenuBar.add(jMenu);
        jMenuBar.add(layer);
        jMenuBar.add(moveMap);
        //jMenuBar.add(addLayer);
        jf.setJMenuBar(jMenuBar);
        
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        container.add(viewport);//pane
        container.add(broswer);
        
        jf.addKeyListener(new KeyListener());
        jf.add(container);
        jf.setResizable(false);
        jf.setIgnoreRepaint(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Viewport.populateEverything();
    }
    private int askUserH() {
    	String input;     // To hold String input.
        int    height;    // To hold length.

        // Prompt user to input length.
        input = JOptionPane.showInputDialog("Enter Height");

        // Convert the String input to an int.
        height = Integer.parseInt(input);
        
        return height;
	}
    private int askUserW() {
    	String input;     // To hold String input.
    	int    width;     // To hold width.
    	
    	input = JOptionPane.showInputDialog("Enter Width");
    	
        // Convert the String input to an int.
        width = Integer.parseInt(input);
        
        return width;
    }
    public int layerNum = 0;
    public void layerMenu() {
    	 JMenuItem newLayerJItem;
        layer1 = new JMenuItem(new AbstractAction("Layer 0") {//changes to layer 0, usually floor tyles
        	public void actionPerformed(ActionEvent e) {
        		Viewport.setLayer(0);
        	}
        });
        layer2 = new JMenuItem(new AbstractAction("Layer 1") {//changes to layer 1
        	public void actionPerformed(ActionEvent e) {
        		Viewport.setLayer(1);
        	}
        });
        layer3 = new JMenuItem(new AbstractAction("Layer 2") {//changes to layer 2
        	public void actionPerformed(ActionEvent e) {
        		Viewport.setLayer(2);
        	}
        });

    	//layerListSize = layer.

        addLayerVar = new JMenuItem("New Layer");
       
        addLayerVar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				int w = askUserW();
				int h = askUserH();
				JMenuItem item = new JMenuItem("Layer " + layerListSize + " " + w + "x" + h);
				Viewport.addLayer(w,h);
				layerListSize++;
				item.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub

		        		
						Viewport.setLayer(layerListSize);
					}
					
				});
				layer.add(item);
			}
        	
        });
        layer.add(addLayerVar);
        layer.add(layer1);
        layer.add(layer2);
        layer.add(layer3);
       // addLayer.add(addLayerVar);
//        jMenuBar.add(addLayerVar);
//        layerList.add(layer1);
//        for(int i = 0; i < layerList.size(); i++) {
//        	layerNum++;
//    		JMenuItem newLayer = layerList.get(i);
//    		newLayer.addActionListener(new ActionListener() {
//    			public void actionPerformed(ActionEvent ev) {
//    				Viewport.setLayer(layerNum);
//    			}
//    		});
//    		layer.add(newLayer);
//    	}
//        layer1 =  new JMenuItem(new AbstractAction("Layer 0") {//changes to layer 0, usually floor tyles
//        	public void actionPerformed(ActionEvent e) {
//        		
//        	}
//        });
//        addLayerVar = new JMenuItem(new AbstractAction("Add Layer") {
//        	public void actionPerformed(ActionEvent e) {
//        		//List<Nodes> newLayer = new ArrayList<Nodes>(); //Hold nodes of layer 1
//        		JMenuItem newLayerJItem = MapBuilder.newLayerJItem;
//        		layerListSize++;
//        		layerList.add(newLayerJItem);
//        		int l = askUserL();
//        		int w = askUserW();
//        		Viewport.addLayer(l,w);
//        		addLayer.add(layerList.get(layerList.size()));
//        	}
//        });
//        
//        addLayer.add(layerList.get(layerList.size()-1));
    }
	public static void main(String[] args)
    {
//    	after user input for default pixel width, hight, 
//    	screen width, screen hight
//    	for(int y = 0; y < screenH; y += pixelH){
//    		for(int x = 0; x < screenW; x += pixelW){
//    			nodes.add(new Nodes(x, y));
//    		}
//    	}
    	
//    	System.out.println(nodes.get(testNode) + " " + nodes.get(testNode).getX() +
//    			" " + nodes.get(testNode).getY());
    	
    	nodes.add(layer12);
    	nodes.add(layer22);
    	nodes.add(layer32);
//    	System.out.println(nodes.get(7));
    	createNodes();//initizalized the nodes
//    	setOffset(); //Idk what this does tbh
    	setStaggerX();
    	 int col = (MapBuilder.mapW)/pixelW;
    	 int row = MapBuilder.mapH/(pixelH / 2);
//    	 System.out.println(col + " " + row);
    	//changeX();
//    	showNodes(); //Shows the nodes
    	viewport = new Viewport(nodes);
    	MapBuilder mb = new MapBuilder();
//    	System.out.println(Viewport.allLayers.get(0).size() + "LAYER SIZE YO");
    }
    public static void setOffset() {
    	for (int i = 0; i <nodes.size(); i++) {
    		for (int j = 0; j < nodes.get(i).size(); j++) {
    			if(nodes.get(i).get(j).getX() >= 1000) {
    				String numberString = "" + nodes.get(i).get(j).getX();
    				char firstLetterchar = numberString.charAt(0);
    				int firstDigit = Integer.parseInt("" + firstLetterchar);
    				nodes.get(i).get(j).setOffset(firstDigit);
    				//System.out.println(firstDigit);
    			}
    		}
    	}
    }
    public static void changeX() {
		for(int i = 0; i < nodes.size(); i++) {//for each layer, create nodes at x,y)
			for(int j = 0; j < nodes.get(i).size(); j++){
				nodes.get(i).get(j).setX(nodes.get(i).get(j).getOffsetX());
			}
		}
	}
    public static void showNodes() {
    	for(int i = 0; i < nodes.size(); i++) {//for each layer, create nodes at x,y)
			for(int j = 0; j < nodes.get(i).size(); j++) {
				System.out.println(j + " " + nodes.get(i).get(j).getX() + ". " + nodes.get(i).get(j).getY());
			}
		}
    }
    
	public static void createNodes() {
		for(int i = 0; i < nodes.size(); i++) {//for each layer, create nodes at x,y)
//			for(int y = 0; y <= mapH; y += pixelH/2){//for staggered tiles
			for(int y = 0; y <= mapH; y += pixelH){
	    		for(int x = 0; x <= mapW; x += pixelW){
	    			System.out.println("(" + x + ", " + y + ")");
	    			//int newX = calcStagger(i,x,y);
	    			//System.out.println("x: " + x + "; + newX: " + newX);
//	    			if((y/(pixelH/2)) % 2 == 0) {//for staggered tiles
	    			//	System.out.println("is even");
	    				nodes.get(i).add(new Nodes(x, y, true, i));
	    				
//	    			}
//	    			else {//for staggered tiles
//	    				System.out.println("is NOT even");
//	    				nodes.get(i).add(new Nodes(x, y, false));
//	    			}
	    		//	nodes.get(size).setSize(size);
	    		//	size = size + 1;
	    			
	    		}
	    	}
			totalLayers++;
		}
	}
	public static void setStaggerX() {
		for(int i = 0; i < nodes.size(); i++) {//for each layer, create nodes at x,y)
			for(int j = 0; j < nodes.get(i).size(); j++){
				int x = nodes.get(i).get(j).getX();
				int y = nodes.get(i).get(j).getY();
				int newX = calcStagger(i,j,x,y);
				nodes.get(i).get(j).setOffsetX(newX);
			}
		}
	}
	public static int calcStagger(int i, int j, int x, int y) {
			int num = j;//Which node
//			System.out.println(j);
			int dig = num % 10;//node number % 10
//			System.out.println(dig);
			int one; 
			one = dig;
//			System.out.println(one);
			dig = num % 100; //node number % 100
//			System.out.println(dig);
			int mod = (dig - one) / 10; 
//			System.out.println(mod);
			int offset = nodes.get(i).get(j).getOffset();
//			if(j % 2 == 0) {
////				if(x % 1000 ==0 && moved == false) {
////					System.out.println("moved yo");
////					moved = true;
////					return x;
////				}
////				if(moved == true) {
////					return x+50;
////				}
//				System.out.println("Yo 1");
//				return (x + (MapBuilder.pixelW/2)) - ((MapBuilder.pixelW/2)*(offset));
//			} 
//			else {
//				
//				System.out.println("Yo 2");
//				return x + ((MapBuilder.pixelW/2) * offset);
//			}
			if(nodes.get(i).get(j).getIsEvenRow() == true) {
				return (nodes.get(i).get(j).getX() + (pixelW/2));
			}
			else return (nodes.get(i).get(j).getX());
			
			
	}
	public static int calcOS(int x) {
		String numberString = "" + x ;
		char firstLetterchar = numberString.charAt(0);
		int firstDigit = Integer.parseInt("" + firstLetterchar);
		return firstDigit;
		//System.out.println(firstDigit);
	}
	public static void parseJSON(String string) throws ParseException, FileNotFoundException, IOException {
		System.out.println(string);
		Object obj = new JSONParser().parse((new FileReader(string))); 
		JSONObject jo = (JSONObject) obj; 
		String joString = jo.get("layerList").toString();
		//  System.out.println((jo));
		//jo = (JSONObject) jo.keySet();
		JSONArray obj1 = (JSONArray) new JSONParser().parse(joString);
		JSONArray tempobj = obj1;
		JSONObject parseThis;
		Object obj2 = new JSONParser();
		JSONArray array = new JSONArray();
		for(int i = 0; i < (obj1).size(); i++) {
			parseThis = (JSONObject) new JSONParser().parse((obj1.get(i)).toString());
			joString = ((Map) parseThis).values().toString();
			//   System.out.println(joString);
			//   arr1.add(parseThis);
			obj2 = new JSONParser().parse(joString);
			((Collection) array).add(obj2);
			//   System.out.println(array.get(i) + "TSET ");
			
			//  System.out.println(parseThis.keySet());
		}
		for(int i = 0; i < (array).size(); i++) {
			Iterator<Object> iterator = ((JSONArray) array.get(i)).iterator();
			parseThis = (JSONObject) new JSONParser().parse((iterator.next().toString()));
			((Collection) arr1).add(parseThis);
			int x =  ((Long)((HashMap) arr1.get(i)).get("x")).intValue();
			int y = ((Long)((HashMap) arr1.get(i)).get("y")).intValue();
			int imageInd = ((Long)((HashMap) arr1.get(i)).get("image_index")).intValue();
			final int layerCount = ((Long)((HashMap) arr1.get(i)).get("layer")).intValue();
			int nodeIndex = ((Long)((HashMap) arr1.get(i)).get("node_num")).intValue();	
			int tempW = ((Long)((HashMap) arr1.get(i)).get("layerW")).intValue();
			int tempH = ((Long)((HashMap) arr1.get(i)).get("layerH")).intValue();
			Image tempImg;
			if(imageInd == -1) {
				 tempImg = null;
			}
			else tempImg =  Browser.imgList.get(imageInd);
			//System.out.println(((HashMap) arr1.get(i)).keySet());
			System.out.println("(x,y,image_index, layer): " + x + ", " + y + ", " + imageInd + ", " + layerCount);
			if(layerCount > Viewport.allLayers.size()-1) {
				System.out.println(layerCount + "not a layer yet");
//				List<Nodes> newLayer = new ArrayList<Nodes>();
				List<Integer>layerSize = new ArrayList<Integer>();

				layerSize.add(tempW);
				layerSize.add(tempH);
				System.out.println(tempW + " tempH: " + tempH);
				
//				Viewport.layerWH.put(layerCount, layerSize);
				//Viewport.allLayers.add(newLayer);
				layerListSize++;
						
				JMenuItem item = new JMenuItem("Layer " + layerListSize + " " + tempW  + "x" 
												+ tempH);
				Viewport.addLayer(tempW
						         ,tempH);
				item.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
							
						
						Viewport.setLayer(layerListSize);
					}
					
				});
				layer.add(item);
//		       	layer.add(addLayerVar);
				System.out.println("layerCount = " + layerCount + "; nodeIndex = " + nodeIndex);
				int width;
				int height;
				if(imageInd == -1) {
					width = 1;
					height = 1;
				}
				else {
					width = Browser.imgList.get(imageInd).getWidth(null);
					height= Browser.imgList.get(imageInd).getHeight(null);
				}
				System.out.println("Width: " + width + ", Height: " + height);
				Viewport.allLayers.get(layerCount).add(nodeIndex, new Nodes(x, y, true, layerCount));
				Viewport.allLayers.get(layerCount).get(nodeIndex).setImage(tempImg);
				Viewport.allLayers.get(layerCount).get(nodeIndex).setImageListIndex(imageInd);
//				System.out.println("width: " + width + "height: " + height);
				Viewport.allLayers.get(layerCount).get(nodeIndex).setWidth(width);
				Viewport.allLayers.get(layerCount).get(nodeIndex).setHeight(height);
//				nodes.get(layerCount).get(nodeIndex).setHeight(tempImg.getHeight(null));
			}
			else if(imageInd == -1) {
			}
			else {	//nodes.get(layerCount).
			System.out.println("layerCount = " + layerCount + "; nodeIndex = " + nodeIndex);
				
				int width = Browser.imgList.get(imageInd).getWidth(null);
				int height= Browser.imgList.get(imageInd).getHeight(null);
				System.out.println("Width: " + width + ", Height: " + height);
//				System.out.println(Viewport.allLayers.get(layerCount));
				Viewport.allLayers.get(layerCount).add(nodeIndex, new Nodes(x, y, true, layerCount));
				Viewport.allLayers.get(layerCount).get(nodeIndex).setImage(tempImg);
				Viewport.allLayers.get(layerCount).get(nodeIndex).setImageListIndex(imageInd);
//				System.out.println("width: " + width + "height: " + height);
				Viewport.allLayers.get(layerCount).get(nodeIndex).setWidth(width);
				Viewport.allLayers.get(layerCount).get(nodeIndex).setHeight(height);
//				nodes.get(layerCount).get(nodeIndex).setHeight(tempImg.getHeight(null));
			}
//				nodes.get(layerCount).get(nodes.size()).
		}
	}
//	public boolean getNode(int targetX, int targetY, int pixelW) {
//		int i = 0;
//		while(foundNode == false) {
//			i++;
////			if(size > 1) { //Only works for viewport
////				System.out.println(nodesVP.size());
////				System.out.println("Target X: " + targetX + "; X: " + nodesVP.get(Viewport.getLayer()).get(i).getX()
////						+ " Offset X: " +  nodesVP.get(Viewport.getLayer()).get(i).getOffsetX()
////						+ " Y: " + nodesVP.get(Viewport.getLayer()).get(i).getY() + " targetY: " + targetY);
//				if((nodes.get(Viewport.getLayer()).get(i).getX() == targetX && nodesVP.get(Viewport.getLayer()).get(i).getY() == targetY && pixelW != Browser.pixelW)){
//					//iterates through every node and checks if the target x and y are equal the viewport nodes at index (i)
//					//if(pixelW == 60) { targetBrowser = nodes.get(i); inc = i; return foundNode = true;} //System.out.println(nodes.get(i));} //Browser
////					int num = i; //calculates offset of tiles
////					int dig = num % 10;
////					int one;
////					one = dig;
////					dig = num % 100;
//					System.out.println(nodesVP.get(Viewport.getLayer()).get(i).getOffsetX() + " x TEST");
//					System.out.println((nodesVP.get(Viewport.getLayer()).get(i).getX()+(MapBuilder.pixelW/2)) + ": MOD STATEMENT");
//					System.out.println(i + "=i; (" + nodesVP.get(Viewport.getLayer()).get(i).getX() + ", " //Displays information about X the calculated x value
//										 + nodesVP.get(Viewport.getLayer()).get(i).getY() + ")" //displays calculated y value
//									     + " (" + targetX + ", " + targetY + ") " // displays target the target x and y
//										 + pixelW + "=pixelW " + MapBuilder.pixelW + "=mapBuilder " + "Size = " + size
//										 + ", Offset X= " + nodesVP.get(Viewport.getLayer()).get(i).getOffsetX());
//					targetViewport = nodesVP.get(Viewport.getLayer()).get(i);
//					inc = i; foundNode = true;
//				}
//			}
////		}
//		return foundNode;
//}
}




