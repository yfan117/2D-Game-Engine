package Diablo;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;

import Diablo.Items.Item;

public class Inventory
{
    private int backpackCapacity;
    private int rows;
    private int cols;
    private boolean isBackpackOpen = false;
    private Item[] backpackItems;
    private boolean[] backpackBools;

    private int inventoryCapacity;
    private int inventoryRows;
    private int inventoryCols;
    private Item[] inventoryItems;
    private boolean isInventoryOpen = true;
    private boolean[] inventoryBools;

    private BufferedReader br;

    public Inventory()
    {
        try
        {
            br = new BufferedReader(new FileReader(Game.root + "/resources/text/inventory.txt"));

            String line = br.readLine();
            line = line.replaceAll("[^0-9]", "");
            this.rows = Integer.parseInt(line);
            line = br.readLine();
            line = line.replaceAll("[^0-9]", "");
            this.cols = Integer.parseInt(line);
            line = br.readLine();
            line = line.replaceAll("[^0-9]", "");
            this.inventoryRows = Integer.parseInt(line);
            line = br.readLine();
            line = line.replaceAll("[^0-9]", "");
            this.inventoryCols = Integer.parseInt(line);
        }catch(Exception ex){ex.printStackTrace();}

        this.backpackCapacity = rows * cols;
        this.inventoryCapacity = inventoryRows * inventoryCols;

        this.backpackItems = new Item[backpackCapacity];
        this.inventoryItems = new Item[inventoryCapacity];
        this.backpackBools = new boolean[backpackCapacity];
        this.inventoryBools = new boolean[inventoryCapacity];
    }

    public int getRows(){return rows;}
    public int getCols(){return cols;}
    public int getBackpackCapacity(){return backpackCapacity;}
    public Item[] getBackpackItems(){return backpackItems;}
    public Item getBackpackItem(int i){return backpackItems[i];}
    private boolean addBackpackItem(Item item)
    {
        if(item.isStackable())
        {
            for (int j = 0; j < backpackCapacity; j++)
            {
                try
                {
                    if (backpackItems[j].getName() == item.getName())
                    {
                        backpackItems[j].setNumberInStack(backpackItems[j].getNumberInStack() + item.getNumberInStack());
                        return true;
                    }
                }catch(Exception ex){}
            }
        }
        for(int i = 0; i < backpackCapacity; i++)
        {
            if(backpackItems[i] == null)
            {
                backpackItems[i] = item;
                return true;
            }
        }
        return false;
    }
    public void setBackpackItem(int i, Item item){backpackItems[i] = item;}
    public void removeBackpackItem(int i){backpackItems[i] = null;}
    public boolean getBackpackOpen(){return isBackpackOpen;}
    public void setBackpackOpen(boolean b){isBackpackOpen = b;}
    public BufferedImage getBackpackItemImage(int i){return backpackItems[i].getImage();}
    public boolean[] getBackpackBools(){return backpackBools;}
    public boolean getBackpackBool(int i){return backpackBools[i];}
    public void setBackpackBool(int i, boolean b){backpackBools[i] = b;}

    public int getInventoryRows(){return inventoryRows;}
    public int getInventoryCols(){return inventoryCols;}
    public int getInventoryCapacity(){return inventoryCapacity;}
    public Item[] getInventoryItems(){return inventoryItems;}
    public Item getInventoryItem(int i){return inventoryItems[i];}
    private boolean addInventoryItem(Item item)
    {
        if(item.isStackable())
        {
            for (int j = 0; j < inventoryCapacity; j++)
            {
                try
                {
                    if (inventoryItems[j].getName() == item.getName())
                    {
                        inventoryItems[j].setNumberInStack(inventoryItems[j].getNumberInStack() + item.getNumberInStack());
                        return true;
                    }
                }catch(Exception ex){}
            }
        }
        for(int i = 0; i < inventoryCapacity; i++)
        {
            if(inventoryItems[i] == null)
            {
                inventoryItems[i] = item;
                return true;
            }
        }
        return false;
    }
    public void setInventoryItem(int i, Item item){inventoryItems[i] = item;}
    public void removeInventoryItem(int i){inventoryItems[i] = null;}
    public boolean getInventoryOpen(){return isInventoryOpen;}
    public void setInventoryOpen(boolean b){isInventoryOpen = b;}
    public BufferedImage getInventoryItemImage(int i){return inventoryItems[i].getImage();}
    public boolean[] getInventoryBools(){return inventoryBools;}
    public boolean getInventoryBool(int i){return inventoryBools[i];}
    public void setInventoryBool(int i, boolean b){inventoryBools[i] = b;}

    public void addItem(Item item)
    {
        if(addInventoryItem(item))
            return;
        if(addBackpackItem(item))
            return;
        System.out.println("There's no room!");
    }

    public void resetAllBools()
    {
        for(int i = 0; i < backpackCapacity; i++)
            setBackpackBool(i, false);
        for(int i = 0; i < inventoryCapacity; i++)
            setInventoryBool(i, false);
    }
}