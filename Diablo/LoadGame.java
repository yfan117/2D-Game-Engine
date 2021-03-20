package Diablo;

import java.io.*;
import java.nio.Buffer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoadGame
{

    private int x = 10;
    private int y = 10; //player position
    private int hp;
    private Game game;
    private File saveFile;
    private File currentSave;
    private Path path;
    private List<String> savesList;

    public LoadGame(Game game)
    { //construct a Load game instance
        this.game = game;
        saveFile = new File(game.root + "/resources/text/savedGame.txt");

        getSavesList();
    }

    public int[] loadGame(String name)
    { //acess the load file
        Scanner scanner = null;
        int saveData[] = new int[3];
        try
        {
            scanner = new Scanner(new File(game.root + "/resources/text/saves/" + name + ".txt"));
            saveData[0] = scanner.nextInt();
            saveData[1] = scanner.nextInt();
            saveData[2] = scanner.nextInt();
            scanner.close();

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return saveData;
    }

    public void saveGame(String name)
    {
        currentSave = new File(game.root + "/resources/text/saves/" + name + ".txt");

        x = (game.getEntityList().get(0).x / 10) * 10;
        y = (game.getEntityList().get(0).y / 10) * 10;
        hp = game.getEntityList().get(0).hp;
        if (currentSave.exists())
        {
            currentSave.delete();
        }
        outputToFile(name);
    }

    public void saveNewGame(String name)
    {
        currentSave = new File(game.root + "/resources/text/saves/" + name + ".txt");

        x = 10;
        y = 10;
        hp = 100;
        if (currentSave.exists())
        {
            currentSave.delete();
        }
        outputToFile(name);
    }

    private void outputToFile(String name)
    {
        try
        {
            currentSave.createNewFile();
            FileWriter fw = new FileWriter(game.root + "/resources/text/saves/" + name + ".txt");
	           /* Save file Structure:
		             x position
		           	 y position
		           	 health
				*/
            fw.write(x + System.lineSeparator());
            fw.write(y + System.lineSeparator());
            fw.write(hp + System.lineSeparator());

            fw.close();
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void append(String name)
    {
        try
        {
            FileWriter fw = new FileWriter(saveFile, true);
            fw.write(name + "\n");
            fw.close();
        }catch(Exception ex){ex.printStackTrace();}
    }

    public List getSavesList()
    {
        savesList = new ArrayList<String>();

        try
        {
            BufferedReader br = new BufferedReader(new FileReader(saveFile));
            String s;
            s = br.readLine();

            while(s != null)
            {
                savesList.add(s);
                s = br.readLine();
            }
        }catch(Exception ex){ex.printStackTrace();}

        return savesList;
    }

    public void saveLastLoaded(String name)
    {
        try
        {
            File f = new File(game.root + "/resources/text/lastLoaded.txt");
            f.createNewFile();
            FileWriter fw = new FileWriter(f, false);
            fw.write(name);
            fw.close();
        }catch(Exception ex){ex.printStackTrace();}
    }

    public String lastLoaded()
    {
        String s = "";
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(game.root + "/resources/text/lastLoaded.txt"));
            s = br.readLine();
        }catch(Exception ex){ex.printStackTrace();}
        return s;
    }
}
