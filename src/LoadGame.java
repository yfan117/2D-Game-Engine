package Diablo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class LoadGame {
	
	private int x=10;
	private int y=10; //player position
	private int hp;
	private Game game;
	private File saveFile;
	private Path path;
	
	public LoadGame(Game game) { //construct a Load game instance
		
		this.game = game;
		saveFile= new File(game.root+ "/resources/text/savedGame.txt");
		
	}
	
	public int[] loadGame() { //acess the load file
		
		Scanner scanner = null;
		try {
			scanner = new Scanner(saveFile);
			y=scanner.nextInt();
			x=scanner.nextInt();
			hp=scanner.nextInt();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		scanner.close();
		int saveData[] = {x,y,hp}; // x position, y position, health
		return saveData;
	}
	
	
	public void saveGame(Game game) {
		x=game.getEntityList().get(0).x;
		y=game.getEntityList().get(0).y;
		if(saveFile.exists())
		{
		saveFile.delete();
		}
		outputToFile();
		
	}
	
	 private void outputToFile()
	    {
	        try
	        {
	        	 System.out.println(saveFile);
	        	saveFile.createNewFile();
	           FileWriter fw = new FileWriter(game.root+ "/resources/text/savedGame.txt");
	           /* Save file Structure:
		             x position
		           	 y position 
		           	 health
				*/
	           fw.write(x + System.lineSeparator());
	           fw.write(y + System.lineSeparator());
	           fw.write(hp + System.lineSeparator());
	                
	           fw.close();
	        }catch(Exception ex){ex.printStackTrace();}
	    }
	
	
	
	
	

}
