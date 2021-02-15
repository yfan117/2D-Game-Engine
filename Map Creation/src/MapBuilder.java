package src;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class MapBuilder extends JFrame
{
	static int screenW = 1200;
	static int screenH = 1000;
	static int pixelW = 100;
	static int pixelH = 100;
	static List<Nodes> nodes = new ArrayList<Nodes>();
	
    private JMenuBar jMenuBar;
    private JMenu jMenu;
    private JMenuItem newMenuItem;
    private JMenuItem openMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem closeMenuItem;

    public MapBuilder()
    {   
    	JFrame jf = new JFrame();
    	Viewport viewport = new Viewport();
    	JButton up = new JButton();
    	jMenuBar = new JMenuBar();
    	//Control control = new Control();
    	JPanel container = new JPanel();
    	Browser broswer = new Browser();
    	
    	
    	jf.setTitle("Map Editor"); 
    	jf.setSize(1200, 1000);
    	jf.setLocationRelativeTo(null);
        jf.setVisible(true);
   
       
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
        jf.setJMenuBar(jMenuBar);
    	
//    	viewport.addMouseListener(control);
 //       broswer.addMouseListener(control);
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        container.add(viewport);
        container.add(broswer);
        //container.add();
        jf.add(container);
        jf.setResizable(false);
        jf.setIgnoreRepaint(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args)
    {

    	//after user input for default pixel width, hight, 
    	//screen width, screen hight
//    	for(int y = 0; y < screenH; y += pixelH){
//    		for(int x = 0; x < screenW; x += pixelW){
//    			nodes.add(new Nodes(x, y));
//    		}
//    	}
    	
//    	System.out.println(nodes.get(testNode) + " " + nodes.get(testNode).getX() +
//    			" " + nodes.get(testNode).getY());
    	
 //   	System.out.println(nodes.size());
    	MapBuilder mb = new MapBuilder();
        
        
    }
}