package src;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.Timer;
import org.json.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MapBuilder extends JFrame
{
	 private static JTextField filename = new JTextField(); //sets file name
	 static JSONArray arr1 = new JSONArray();
	private JTextField dir = new JTextField();
	static int screenW = 1200;
	static int screenH = 1000;
	static int pixelW = 100;
	static int pixelH = 50;
	static int mapW = 5000;
	static int mapH = 5000;
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
    
    private JMenu layer;//layers
    private JMenuItem layer1;//switch to layer 1
    private JMenuItem layer2;//switch to layer 2
    private JMenuItem layer3;//switch to layer 3
    
    private JMenu moveMap;
    private JMenuItem up;
    private JMenuItem down;
    private JMenuItem left;
    private JMenuItem right;
    
    private JSONArray ja = new JSONArray();
    private JSONObject jo = new JSONObject();
    JFrame jf = new JFrame();

    JPanel container = new JPanel();
    Browser broswer = new Browser();
    public int[] gameGrid;
    String mapName;
    static Viewport viewport;
    
    public MapBuilder()
    {     	
    	jMenuBar = new JMenuBar();
    	jf.setTitle("Map Editor"); //sets file title
    	jf.setSize(screenW, screenH);//sets screen size
    	jf.setLocationRelativeTo(null);
        jf.setVisible(true);
   
       //set map name
        jMenu = new JMenu("File");//Shows as File in the menu bar
        layer = new JMenu("Layer");//shows as Layer in menu bar
        newMenuItem = new JMenuItem(new AbstractAction("Export as JSON") {//exports the file to a JSON file
        	public void actionPerformed(ActionEvent e) {
        		JSONObject mapDetails = new JSONObject();
        		JSONObject mapObject = new JSONObject();
        		mapDetails.put("Map", "map title 1");//currently temporary, will change so you can choose this name
        		mapDetails.put("map_Width", screenW);//Outputs map width
        		mapDetails.put("map_Height", screenH);//Outputs map Height
        		mapDetails.put("tile_Width", pixelW);//outputs the width of floor tiles
        		mapDetails.put("tile_Height", pixelH);//outputs height of floor tiles
        		mapObject.put("Map", mapDetails);
        		JSONObject layerDetails = new JSONObject();
        		JSONObject layerObject = new JSONObject();
        		JSONArray layerList = new JSONArray();
        		layerDetails.put("layer", "1");
        		//Below outputs information for each node into the JSON
        		for(int i = 0; i < Viewport.allLayers.get(Viewport.getLayer()).size(); i++) {
        			if(Viewport.allLayers.get(Viewport.getLayer()).get(i).getimageListIndex() !=-1) {
	        			layerObject = new JSONObject();
	        			layerDetails = new JSONObject();
	        			layerDetails.put("node_num", i);
	        			layerDetails.put("image_index", Viewport.allLayers.get(Viewport.getLayer()).get(i).getimageListIndex());
	        			layerDetails.put("x", Viewport.allLayers.get(Viewport.getLayer()).get(i).getX());
	        			layerDetails.put("y",  Viewport.allLayers.get(Viewport.getLayer()).get(i).getY());
	        			layerObject.put("Tile", layerDetails);
	        			layerList.put(layerObject);
        			}
        		}        		
        		layerObject.put("Layer", layerDetails);
        		
        		JSONObject spriteList = new JSONObject();
        		JSONObject spriteDetails = new JSONObject();
        		JSONObject spriteOBJ = new JSONObject();
        		//below outputs the information about the images used to create the map
        		for(int i = 0; i < Browser.imageIndexSize; i++) {
        			spriteDetails = new JSONObject();
        			spriteOBJ = new JSONObject();
        			spriteDetails.put("Picture", Browser.imgList.get(i));
        			spriteDetails.put("index", i);
        			spriteDetails.put("Dimensions", "100x50");
        			spriteOBJ.put("Sprite", spriteDetails);
        			spriteList.put("sprite " + i, spriteOBJ);
        		}
        		 
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
        
        
        openMenuItem = new JMenuItem(new AbstractAction("Import JSON") {//imports a json file into editor *currently doesn't work*
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser c = new JFileChooser();
				// Demonstrate "Open" dialog:
				int rVal = c.showOpenDialog(MapBuilder.this);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					filename.setText(c.getSelectedFile().getName());
					dir.setText(c.getCurrentDirectory().toString());
					System.out.println(filename.getText());
					
					try {
						parseJSON(filename.getText());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if (rVal == JFileChooser.CANCEL_OPTION) {
					filename.setText("You pressed cancel");
					dir.setText("");
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
        layer.add(layer1);
        layer.add(layer2);
        layer.add(layer3);
        
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
    	createNodes();//initizalized the nodes
    	setOffset(); //Idk what this does tbh
//    	showNodes(); //Shows the nodes
    	viewport = new Viewport(nodes);
    	MapBuilder mb = new MapBuilder();
    }
    public static void setOffset() {
    	for (int i = 0; i <nodes.size(); i++) {
    		for (int j = 0; j < nodes.get(i).size(); j++) {
    			if(nodes.get(i).get(j).getX() >= 1000) {
    				String numberString = "" + nodes.get(i).get(j).getX();
    				char firstLetterchar = numberString.charAt(0);
    				int firstDigit = Integer.parseInt("" + firstLetterchar);
    				nodes.get(i).get(j).setOffset(firstDigit);
    				System.out.println(firstDigit);
    			}
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
			for(int y = 0; y < mapW; y += pixelH/2){
	    		for(int x = 0; x < mapH; x += pixelW){
//	    			System.out.println("(" + x + ", " + y + ")");
	    			nodes.get(i).add(new Nodes(x, y));
	    		//	nodes.get(size).setSize(size);
	    		//	size = size + 1;
	    			
	    		}
	    	}
		}
	}
	public static void parseJSON(String string) throws ParseException {
		System.out.println(string);
		Object obj = new JSONParser().parse("C:/MapCreation/" + string); 
		  JSONObject jo = (JSONObject) obj; 
		  String joString = jo.get("layerList").toString();
		//  System.out.println((jo));
		  //jo = (JSONObject) jo.keySet();
		  JSONArray obj1 = (JSONArray) new JSONParser().parse(joString);
		  JSONArray tempobj = obj1;
		  JSONObject parseThis;
		  Object obj2 = new JSONParser();
		  JSONArray array = new JSONArray();
		  for(int i = 0; i < ((Map) obj1).size(); i++) {
		   parseThis = (JSONObject) new JSONParser().parse((obj1.get(i)).toString());
		   joString = ((Map) parseThis).values().toString();
		//   System.out.println(joString);
		//   arr1.add(parseThis);
		   obj2 = new JSONParser().parse(joString);
		   ((Collection) array).add(obj2);
		//   System.out.println(array.get(i) + "TSET ");
		   
		 //  System.out.println(parseThis.keySet());
		  }
		  for(int i = 0; i < ((Map) array).size(); i++) {
			  Iterator<Object> iterator = ((JSONArray) array.get(i)).iterator();
			  parseThis = (JSONObject) new JSONParser().parse((iterator.next().toString()));
			  ((Collection) arr1).add(parseThis);
			  //System.out.println(((HashMap) arr1.get(i)).keySet());
			  System.out.println("(x,y,image_index): " + ((HashMap) arr1.get(i)).get("x") + ", " + ((HashMap) arr1.get(i)).get("y") + ", " + ((HashMap) arr1.get(i)).get("image_index"));
		  }
	}
}