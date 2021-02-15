package src;

import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
 
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;

public class Export {
	private List<Nodes> bwNodes = new ArrayList<Nodes>();
	private List<Nodes> vpNodes = new ArrayList<Nodes>();
	private int imageIndex;
	private int[] gameGrid = new int[vpNodes.size()];
	public Export(List<Nodes> bwNodes, List<Nodes> vpNodes) {
		this.bwNodes = bwNodes;
		this.vpNodes = vpNodes;
	}
	
	public void generate(List<Nodes> vpNodes) {
		for(int i = 0; i < vpNodes.size(); i++) {
			imageIndex = vpNodes.get(i).getimageListIndex();
			//if(imageIndex == -1) {
			//	
			//}
			gameGrid[i] = imageIndex;
		}
	}
	public void showgameGrid() {
		System.out.print("{");
		
		for(int i = 0; i < vpNodes.size(); i++) {
			System.out.print(gameGrid[i] + ", ");
		}
		
		System.out.println("}");
	}
}

