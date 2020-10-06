package MapBuilder;

import javax.swing.*;

public class MapBuilder extends JFrame
{
    private JMenuBar jMenuBar;
    private JMenu jMenu;
    private JMenuItem newMenuItem;
    private JMenuItem openMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem closeMenuItem;

    public MapBuilder()
    {
        jMenuBar = new JMenuBar();
        jMenu = new JMenu("File");
        newMenuItem = new JMenuItem("New");
        openMenuItem = new JMenuItem("Open");
        saveMenuItem = new JMenuItem("Save");
        closeMenuItem = new JMenuItem("Close");

        jMenu.add(newMenuItem);
        jMenu.add(openMenuItem);
        jMenu.add(saveMenuItem);
        jMenu.add(closeMenuItem);
        jMenuBar.add(jMenu);

        setJMenuBar(jMenuBar);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 1000);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        MapBuilder mb = new MapBuilder();
    }
}