package Diablo;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DialogueUI {
	/*
	 * This class is used to draw dialogue UI and text for a given
	 * DialogueUI string. The string represents the entire 
	 * dialogue, the class functions parse and print out
	 * the text within a given output range
	 */
	private String dialogue=null;
	public Game game;
	private Entity entity;
	private String repo= Game.root+"\\resources\\images\\";
	private Image portrait;
	private Graphics g;
	private Dialogue d;
	private String[] dialogues;
	public boolean NPC=true;
	public DialogueManager manager;
	private String [] responses;
	
	private int boxHeight, boxWidth, boxY, boxX;
	private int portraitHeight, portraitWidth, portraitX , portraitY;
	private Image dialoguebox;
	/*
	 * constructor takes in an entity
	 * to render an instance of dialogue
	 */
	public DialogueUI(Entity entity){ 
		this.game=entity.game;
		this.entity=entity;
		this.portrait=entity.getNpcPortrait();
		this.d=entity.getDialogue();
		dialogue=d.getDialogue();
		this.manager=new DialogueManager(entity,this);
		game.dialogue=true;
		boxHeight=(int)game.windowY/4;
		boxWidth= (int)game.windowX/3;
		boxY=(int)game.windowY-(game.windowY/3);
		boxX=(int)game.windowX-(game.windowX/2)-(boxWidth/2);
	//	portraitHeight=game.windowY/2;
		portraitHeight=(int) ((int)boxHeight*.8);
		portraitWidth=maintainRatio(portrait,portraitHeight);
		portraitY=game.windowY-((int)game.windowY/2);
		portraitX=(int) (game.windowX-portraitWidth-boxWidth-(game.windowX-boxWidth-(game.windowX-boxWidth)/2)+(boxWidth*.1));
		try {
			System.out.println(repo+"DialogueBox.png");
			dialoguebox=ImageIO.read(new File(repo+"DialogueBox.png"));
		} catch (IOException e) {
			System.out.println(repo+"DialogueBox.png");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * Functions to draw dialogue
	 */
	public void render(Graphics g) {
		if(NPC==true) {
			this.g=g;
			paintDialogueBox(dialoguebox);
			paintNPCDialogue();
		}
		else {
			this.g=g;
			//paintDialogueBox(g);
			paintDialogueBox(dialoguebox);
			paintResponses(dialogues,g);
		}
	}

	private void paintDialogueBox(Graphics g) {
	/*
	 * default draws a generic, transparent black
	 * dialogue box with rounded edges
	 */
//		g.setFont(new Font("TimesRoman",Font.PLAIN,20));
//		this.g=g;
//		int alpha = 127; // 50% transparent
//		Color myColor = new Color(0, 0, 0, alpha);//transparent black
//		g.setColor(Color.BLACK); 
//     	g.drawRoundRect(boxX,boxY, boxWidth, boxHeight,50,25);
//		g.setColor(myColor);
//	    g.fillRoundRect(boxX,boxY, boxWidth, boxHeight,50,25);
//	    g.setColor(Color.LIGHT_GRAY);
		g.drawImage(dialoguebox,boxX-150,boxY-55,boxWidth+150,boxHeight+55,game.display.getRender());
	}
	
	private void paintDialogueBox(Image image) {
	/*
	 * Function to format and paint a custom image
	 *  dialogue box and entity portrait
	 */
		g.setFont(new Font("TimesRoman",Font.PLAIN,20));
		g.drawImage(image,boxX-150,boxY-55,boxWidth+150,boxHeight+55,game.display.getRender());
	
	}
	
	private void paintNPCDialogue() { //will be changed to paintDialogue
		
		paintEntityPortrait(boxWidth);
		g.setColor(new Color(101,67,33));
		if(NPC==true) {
			 printText(boxX,boxY-15,boxWidth,boxHeight, dialogue,g.getFont(),g); 
		}
		if(NPC==false) {
			
		}
		
	   
	}
	
	private void paintEntityPortrait(int boxWidth) {
		/*
		 * paints player portrait relative to the window size and text-box width
		 */
		if(NPC==true) {
			//draws portrait on the right
			g.drawImage(portrait,boxX-120,boxY-20,portraitWidth,portraitHeight,game.display.getRender());
		}
		if(NPC==false) {
			//draw image on the left
		}
	}
	
	private void paintResponses(String[] dialogues, Graphics g) {
		/*
		 * format and paint user resposne
		 */
		int responseWidth;
		int responseHeight;
		g.setFont(new Font("TimesRoman",Font.PLAIN,20));
		this.g=g;
	  //  paintEntityPortrait(boxWidth);
	   // for(int i =0; i <responses.length; i++) {
	    	// printText(boxX,boxY,boxWidth,boxHeight, dialogue,g.getFont(),g);
	//    }
	    printText(boxX,boxY,boxWidth,boxHeight, dialogue,g.getFont(),g);
	}
	
	private void printText(int textboxX,int textboxY, int textboxWidth,int textboxHeight,String dialogue,Font font,Graphics g)
	{
	/*
	 * draw text within a given "text box" range
	 * PARAMETERS:textboxX and textBoxY:upper left-hand corner, String dialogue:output text. 
	 * font determines the font and size of the drawn text
	 */
		FontMetrics m= g.getFontMetrics();
		int lineWidth = textboxWidth-2*m.getHeight();
		String s=dialogue;
		int x= textboxX+(int)(textboxHeight*.1);
		int y= textboxY+(int)(textboxWidth*.1); //initial text position
	/*
	 * Parses String into array of words, 
	 * prints words until the text-box margins
	 */
		String[] words = s.split(" ");
		String currentLine="";
		int i;
		if(game.continueDialogue==true) {//exit if no remaining dialogue
			if(s.trim().length()==0) {
				game.dialogue=false;
				game.continueDialogue=false;
				return;
			}
		}
		int max=textboxY+textboxHeight-2*(int)(textboxHeight*.1);//max length
		for( i =0; i < words.length && y <= max; ){
			if(m.stringWidth(currentLine+words[i])<=lineWidth) { //if room, add word
				currentLine+=" "+ words[i];
				g.drawString(currentLine,x,y);
				i++;
			}
			else {
				g.drawString(currentLine, x,y);
				y+=m.getHeight();//new line coordinate
				currentLine=words[i]; 
				i++;
			}
		} 
	/*
	 * wait for user to continue dialogue
	 */
		if(game.continueDialogue==true) {
			String string= "";
			if (i < words.length) { //check for remaining dialogue
				string=currentLine +" ";
				while(i<words.length) {
				 string += words[i]+ " ";
				 i++;
				}
				game.continueDialogue=false;
				setDialogue(string); //store remaining dialogue
			 }
			else {//no more dialogue remains, exit
				System.out.println("no more dialogue to print");
				if(NPC==true) {
				game.dialogue=false;
				game.continueDialogue=false;
				manager=new DialogueManager(entity,this);
				manager.manageDialogue(d);
				}
			}	
		}
	}
	
	/*
	 * helper functions
	 */
	public void setDialogue(String string) { 
		this.dialogue=string;
	}
	public void setDialogue(Dialogue d) { 
		this.d=d;
		this.dialogue=d.getDialogue();
		this.entity.setDialogue(d);
	}
	
	public String getDialogue(){return dialogue;}
	
	public void stageResponses(Dialogue d) {
		this.d=d;
		game.dialogue=true;
		Dialogue[] responses=d.getResponses();
		NPC=false;
		game.responsing=true;
		dialogue=responses[0].getDialogue()+" "+responses[1].getDialogue();
	}
	
	public static int maintainRatio(Image image, int nHeight){
		/*
		 * This function takes in an image and a new proposed width
		 * Returns a height value that maintains the original image aspect ratio
		 */
		int nWidth=0;
		int height=image.getHeight(null);
		int width=image.getWidth(null);
		nWidth=(nHeight*width)/height;
		return nWidth;
	}
	

	
}
