package Diablo;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Dialogue {
	/*
	 * This class is used to draw dialogue UI and text for a given
	 * Dialogue string. The string represents the entire 
	 * dialogue, the class functions parse and print out
	 * the text within a given output range
	 */
	private String dialogue=null;
	private Game game;
	private Entity entity;
	private String repo= Game.root+"\\resources\\images\\";
	private Image portrait;
	private Graphics g;
	
	/*
	 * constructor takes in an entity
	 * to render an instance of dialogue
	 */
	public Dialogue(Entity entity){ 
	
		this.game=entity.game;
		this.entity=entity;
		this.portrait=entity.getNpcPortrait();
		this.dialogue=entity.getDialogue();
		
	}
	
	public void setDialogue(String string) {
		dialogue=string;
	}
	
	public String getDialogue(){
		return dialogue;
	}
	
	/*
	 * Functions to draw dialogue
	 */
	public void render(Graphics g) {
		
		paintDialogueBox(dialogue,g);
	}
	
	private void paintDialogueBox(String dialogue,Graphics g) {
		/*
		 * Function to format and paint a generic
		 *  dialogue box and entity portrait
		 */
		int boxHeight=(int)game.windowY/4;
		int boxWidth= (int)game.windowX/3;
		int boxY=(int)game.windowY-(game.windowY/3);
		int boxX=(int)game.windowX-(game.windowX/2)-(boxWidth/2);
		int portraitHeight=game.windowY/2;
		int portraitWidth=maintainRatio(portrait,portraitHeight);
		int portraitY=game.windowY-((int)game.windowY/2);
		int portraitX=(int) (game.windowX-portraitWidth-boxWidth-(game.windowX-boxWidth-(game.windowX-boxWidth)/2)+(boxWidth*.1));
		int alpha = 127; // 50% transparent
		Color myColor = new Color(0, 0, 0, alpha);//transparent black
		
		/*
		 * draws a generic, transparent black
		 * dialogue box with rounded edges
		 */
		g.setColor(Color.BLACK); 
     	g.drawRoundRect(boxX,boxY, boxWidth, boxHeight,50,25);
		g.setColor(myColor);
	    g.fillRoundRect(boxX,boxY, boxWidth, boxHeight,50,25);
	    g.setColor(Color.LIGHT_GRAY);
	    g.drawImage(portrait,portraitX,portraitY,portraitWidth,portraitHeight,game.display.getRender());
	 
	    /*
	     * call function to print text within the dialogue-box
	     */
	    g.setFont(new Font("TimesRoman",Font.PLAIN,20));
	    printText(boxX,boxY,boxWidth,boxHeight, dialogue,g.getFont(),g); 
	}
	
	private void printText(int textboxX,int textboxY, int textboxWidth,int textboxHeight,String dialogue,Font font,Graphics g)
	{
		/*
		 * Use this function to draw text within a given "text box" range
		 * PARAMETERS:textboxX and textBoxY:upper left-hand corner, String dialogue:output text. 
		 * font determines the font and size of the drawn text
		 */
		FontMetrics m= g.getFontMetrics();
		int lineWidth = textboxWidth-2*m.getHeight();
		String s=dialogue;
		int x= textboxX+(int)(textboxHeight*.1);
		int y= textboxY+(int)(textboxWidth*.1); //initial text position
		/*
		 * Parse String into array of words, 
		 * output words until the text-box margins
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
		
		if(game.continueDialogue==true) {
			/*
			 * Continue dialogue event was triggered
			 */
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
				game.dialogue=false;
				game.continueDialogue=false;
				System.out.println("end");
			}
				
		}
	
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
