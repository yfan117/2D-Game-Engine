
package Diablo;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class Map{
	Game game;
	String mapName;
	public MusicPlayer musicPlayer;
	BufferedReader br;
	String currLine;
	static JSONArray arr1 = new JSONArray();
	static JSONArray arr2 = new JSONArray();
	static JSONArray map = new JSONArray();
	public static List<Integer[]> imageListArr = new ArrayList<Integer[]>();
	public static List imageFiles = new ArrayList();
	final static File folders = new File(Game.root + "/resources/maps/map3");
	public static List<Animation> aniList = new ArrayList<Animation>();
	public Map(Game game){this.game = game;}

	private int enemyNum;
	int[] respawnLcation = new int[2];


	public Map(String mapName, Game game) throws IOException  {
		System.out.println("map");
		try {
			parseJSON();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.mapName = mapName;

		try
		{
			br = new BufferedReader(new FileReader(Game.root + "/resources/text/music.txt"));
			currLine = br.readLine();

			while(currLine != null)
			{
				if(currLine.startsWith("/"))
				{
					if(currLine.substring(1).equals(mapName))
					{
						currLine = br.readLine();
						musicPlayer = new MusicPlayer(Game.root + "/resources/music/" + mapName + ".WAV");
					}
				}
				currLine = br.readLine();
			}
		}catch(Exception ex){ex.printStackTrace();}
		
	BufferedReader bufferedReader = new BufferedReader(new FileReader(Game.root + "/resources/text/" + mapName +".txt"));
		
		File map = new File(Game.root + "/resources/text/" + mapName +".txt");
		Scanner scan = new Scanner(map);
		Scanner innerScan;
		
		while(scan.hasNext())
			{
				String obsName = scan.next();
				int obsLocationX = Integer.parseInt(scan.next());
				int obsLocationY = Integer.parseInt(scan.next());
			
			
				innerScan = new Scanner(new File(Game.root + "/resources/text/" + obsName  +".txt"));
				BufferedImage image;
						
				while(innerScan.hasNext())
				{
					String obsPart = innerScan.next();
					int deltaX = Integer.parseInt(innerScan.next());
					int deltaY = Integer.parseInt(innerScan.next());
					int layerY = Integer.parseInt(innerScan.next());
					int picX = Integer.parseInt(innerScan.next());
					int picY = Integer.parseInt(innerScan.next());

					System.out.println(obsPart);
					game.getObstacles().add(new Entity( game, 							//main object
														Renderer.getImageData(obsPart),	//array of ints containing pixel data of picture
														obsLocationX + deltaX, 			//location x on the map
														obsLocationY + deltaY, 			//location y on the map
														layerY,
														picX,
														picY));						//y coordinate used to determine print order
				}
						
				image = ImageIO.read(new File(Game.root + "/resources/images/" +obsName +"Floor.png"));
				
				obsLocationX = obsLocationX - 0;
				obsLocationY = obsLocationY - 0;
				for(int y = 0; y < image.getHeight(); y++)
				{
					for(int x = 0; x < image.getWidth(); x++)
					{
						int colorCode = image.getRGB(x, y);
						
						if( colorCode != 0)
						{
							//System.out.println(colorCode);
							game.obsMap[obsLocationX + x + (obsLocationY + y) * Game.mapWidth] = true;

						}
					}
				}
				
				
			}


	}


	public int[] getLocation() {

		return respawnLcation;
	}
	public void startMapMusic()
	{
		try
		{
			musicPlayer = new MusicPlayer(Game.root + "/resources/music/" + mapName + ".WAV");
		}catch(Exception ex){ex.printStackTrace();}
		musicPlayer.start();
	}
	public void playMapMusic(){musicPlayer.play();}
	public void pauseMapMusic(){musicPlayer.pause();}
	public boolean isMapMusicPlaying(){return musicPlayer.isRunning();}
	public void setMap(JSONArray map) {
		this.map = map;
	}
	public static JSONArray getMap() {
		return arr1;//map 
	}	
	public void parseJSON() throws IOException, ParseException {
		filesInFolder(folders);
		Object obj = new JSONParser().parse(new FileReader(Game.root + "/resources/maps/" + "map31" + ".json")); 
		JSONObject jo = (JSONObject) obj; 
		String layerList = jo.get("nodeList").toString();
		
		//System.out.println((jo.keySet()));
		//jo = (JSONObject) jo.keySet();
		JSONArray layerArr = (JSONArray) new JSONParser().parse(layerList);
		JSONArray tempobj = layerArr;
		JSONObject parseThis;
		Object obj2 = new JSONParser();
		JSONArray array = new JSONArray();
		JSONArray array2 = new JSONArray();
		for(int i = 0; i < layerArr.size(); i++) {
			parseThis = (JSONObject) new JSONParser().parse((layerArr.get(i)).toString());
			layerList = parseThis.values().toString();
			obj2 = new JSONParser().parse(layerList);
			array.add(obj2);
		}
		obj2=null;
		obj = null;
		Arrays.fill(Renderer.empty, 0);
		Renderer.emptyAni = new Animation(Renderer.empty);
		Renderer.createEmptyWB();
		for(int i = 0; i < array.size(); i++) {
			Iterator<JSONObject> iterator = ((JSONArray) array.get(i)).iterator();
				parseThis = (JSONObject) new JSONParser().parse((iterator.next().toString()));
				if(((Long)parseThis.get("layer")).intValue() == 0) {
					Renderer.populateArray(parseThis);
				}
				else {
					makeUnique(parseThis);
				}			
		}
		
		String spriteList = jo.get("spriteList").toString();
		JSONArray spriteArr = (JSONArray) new JSONParser().parse(spriteList);
		JSONArray tempobj2 = spriteArr;
		for(int i = 0; i < spriteArr.size(); i++) {
			parseThis = (JSONObject) new JSONParser().parse((spriteArr.get(i)).toString());
			spriteList = parseThis.values().toString();
			obj2 = new JSONParser().parse(spriteList);
			array2.add(obj2);
		}
		for(int i = 0; i < array2.size(); i++) {
			Iterator<JSONObject> iterator = ((JSONArray) array2.get(i)).iterator();
			parseThis = (JSONObject) new JSONParser().parse((iterator.next().toString()));
			arr2.add(parseThis);
		}
		parseThis = null;
		array2 = null;
		array = null;
	}
	 public void makeUnique(JSONObject node) throws IOException {
		 int index = ((Long)node.get("image_index")).intValue();
		 if(index == -1) {
			 return;
		 }
		 String indexStr = node.get("picture").toString();
		 System.out.println(indexStr);
		 Image  image = ImageIO.read(new File(folders + "\\" + indexStr));
		 int x = ((Long) node.get("x")).intValue();
		 int y = ((Long) node.get("y")).intValue();
		 int width = image.getWidth(null);
		 int height = image.getHeight(null);
		 
//		 Entity temp = new Entity(indexStr, Map.imageToArray(index), x, y, 1, width, height);
		 for(int i = 0; i < Game.obstacle.size(); i++) {
			 if((Game.obstacle.get(i)).characterName.equals(indexStr)) {
					//reference	
				 Game.obstacle.add(new Entity(indexStr, Game.obstacle.get(i).animationInUse.imageData, x, y + 380, y, width, height));
						return;
					}
				}
		 //create nwe
		 Game.obstacle.add(new Entity(indexStr, imageToArray(indexStr, ((Long)node.get("layer")).intValue()), x, y + 380, y, width, height));
//			 temp=null;
			 return;
	 }
	 
	 
	 
	 public static void filesInFolder(final File folder) {
		 int i = 0;
		 for (final File fileEntry : folder.listFiles()){
			 if (fileEntry.isDirectory()) {
				 i = i + 1;
			 }
			 else {
				 imageFiles.add(fileEntry.getName());    
			 }
		 }
	 }
	 
	 public static int[] imageToArray(String name, int layer) throws IOException {
		  BufferedImage image;
//		  int width;
//		  int height;
//		  for(int i = 0; i < spriteList.size(); i++) {
//			 
//			  //System.out.println("spriteList size: " + spriteList.size() + "; i: " + i + "; width: " + width + "; height: " + height);
//			  if(i != 90) { //remove later, there was a 8mb borken picture for some reason
				  image = ImageIO.read(new File(folders + "\\" + name));
//				  System.out.println(i);
//				  width = ((Long) ((HashMap) spriteList.get(i)).get("width")).intValue();
//				  height = ((Long)((HashMap) spriteList.get(i)).get("height")).intValue();
				  int[] arr = new int[image.getWidth() * image.getHeight()];
				  for (int y = 0; y < image.getHeight(); y++) {
					  for(int x = 0; x < image.getWidth(); x++) {
						  //System.out.println("x: " + x + "; y: " + y);
						  
						  int colorCode = image.getRGB(x, y);
//						  System.out.println(colorCode + " " + index);
						
						  if(layer == 0) { 
							  int delColor = 16777215;
							  if(colorCode != delColor) {
								  arr[x + (y * image.getWidth())] = colorCode;
							  }
						  }
						  
						  else {
//						  System.out.println("here " +  image.getRGB(10,10) + ", " + colorCode);
//							  System.out.println(colorCode);
//							  if(colorCode != 0 || colorCode != image.getRGB(0,0) 
//									  || colorCode != 16777215 || colorCode != 65793 || colorCode != 922377
//									  || colorCode != 658184) {
								  arr[x + y * image.getWidth()] = colorCode;
//							  }
						  }
						  
					  }
				  }
				  return arr;
//				  aniList.add(new Animation(arr));
//				  arr=null;
	 }
			  
//			  else{
//				  
//				  //   System.out.println(imageListArr.size());
//				  //   for(int y = 0; y <)
//				  //   imageListArr.add(1);
//			  }
//		  }
//	 }

}

