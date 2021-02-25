package Diablo;

import Items.Item;

import java.awt.image.BufferedImage;

public class Inventory
{
    private int capacity;
    private int rows;
    private int cols;
    private boolean isInvOpen = false;
    private Item[] items;

    public Inventory(int capacity, int rows, int cols)
    {
        this.capacity = capacity;
        this.rows = rows;
        this.cols = cols;
        this.items = new Item[rows * cols];
    }

    public int getRows(){return rows;}
    public int getCols(){return cols;}
    public int getCapacity(){return capacity;}
    public Item[] getItems(){return items;}
    public void addItem(Item item, int i){items[i] = item;}
    public void removeItem(int i){items[i] = null;}
    public boolean getInvOpen(){return isInvOpen;}
    public void setInvOpen(boolean b){isInvOpen = b;}
    public BufferedImage getItemImage(int i){return items[i].getImage();}
}
