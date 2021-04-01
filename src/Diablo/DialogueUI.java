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
	private int[] responseX= new int [2]; //x,y
	private int[] responseY=new int [2]; //x,y
	private int[] rheight=new int [2];
	private int[] rwidth=new int [2];
	private int boxHeight, boxWidth, boxY, boxX;
	private int portraitHeight, portraitWidth, portraitX , portraitY;
	private Image dialoguebox;
	private int tX, tY, tWidth, tHeight;
	private FontMetrics m;
	
	private int continueX;
	private int continueY;
	private int continueWidth;
	private int continueHeight;
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
		boxHeight=(int)game.windowY/3;
		boxWidth= (int)game.windowX/2;
		boxY=(int)(game.windowY-(game.windowY/3)-game.windowY*.1);
		boxX=(int)game.windowX-(game.windowX/2)-(boxWidth/2);
	//	portraitHeight=game.windowY/2;
		portraitHeight=(int)(boxWidth/4);
		portraitWidth=(int)(boxWidth/4);
		portraitY=(int)(boxY+2*(boxHeight*.08));
		portraitX=(int)(boxX+(boxWidth*.05));
		
		tX=portraitX+boxWidth/4;
		tY=portraitY;
		tWidth=boxWidth-portraitWidth-2*(boxWidth/10);
		tHeight=portraitHeight;
		
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
		g.drawImage(dialoguebox,boxX,boxY,boxWidth,boxHeight,game.display.getRendererObject());
	}
	
	private void paintDialogueBox(Image image) {
	/*
	 * Function to format and paint a custom image
	 *  dialogue box and entity portrait
	 */
		g.setFont(new Font("TimesRoman",Font.PLAIN,20));
		g.drawImage(image,boxX,boxY,boxWidth,boxHeight,game.display.getRendererObject());
	
	}
	
	private void paintNPCDialogue() { //will be changed to paintDialogue
		
		paintEntityPortrait(boxWidth);
		g.setColor(new Color(101,67,33));
		if(NPC==true) {
			//int y =printText(tX,tY,tWidth,tHeight,entity.getName(),g.getFont(),g);
			 printText(tX,tY,tWidth,tHeight, dialogue,g.getFont(),g); 
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
			g.drawImage(portrait,portraitX,portraitY,portraitWidth,portraitHeight,game.display.getRendererObject());
		}
		if(NPC==false) {
			//draw image on the left

			g.drawImage(game.getEntityList().get(0).getNpcPortrait(),portraitX+(portraitWidth*2)+2*boxWidth/10,portraitY,portraitWidth,portraitHeight,game.display.getRendererObject());
		}
	}
	
	private void paintResponses(String[] dialogues, Graphics g) {
		/*
		 * format and paint user responses
		 */
		int responseWidth;
		int responseHeight;
//		g.setFont(new Font("TimesRoman",Font.PLAIN,20));
		this.g=g;
	  //  paintEntityPortrait(boxWidth);
	   // for(int i =0; i <responses.length; i++) {
	    	// printText(boxX,boxY,boxWidth,boxHeight, dialogue,g.getFont(),g);
	//    }
		Dialogue [] responses=null;
		responses=d.getResponses();
		dialogue= responses[0].getDialogue();
		int y= printText(portraitX,tY,tWidth,tHeight,"Select your response:",g.getFont(),g);
		g.setColor(Color.BLUE);
		responseY[0]=y;
		y= printText(portraitX,y,tWidth,tHeight, dialogue,g.getFont(),g);
	   	rwidth[0]=tWidth;
	   	rheight[0]=y-responseY[0];
	   	responseX[0]=portraitX;
	  
	   dialogue= responses[1].getDialogue();
	   
	   g.setColor(Color.RED);
	   rheight[1] =printText(portraitX,y,tWidth,tHeight,dialogue,g.getFont(),g)-y;
	   responseY[1]=y;
	   rwidth[1]=tWidth;
	   responseX[1]=portraitX;
	   
	   g.setColor(new Color(101,67,33));
	   paintEntityPortrait(tWidth);
	}
	
	private int printText(int textboxX,int textboxY, int textboxWidth,int textboxHeight,String dialogue,Font font,Graphics g)
	{
	/*
	 * draw text within a given "text box" range
	 * PARAMETERS:textboxX and textBoxY:upper left-hand corner, String dialogue:output text. 
	 * font determines the font and size of the drawn text
	 */
		m= g.getFontMetrics();
		int lineWidth = textboxWidth-2*m.getHeight();
		String s=dialogue;
		int x= textboxX+(int)(textboxHeight*.1);
		int y= textboxY+(int)(textboxWidth*.1);//initial text position
		if(NPC==true) {
			g.drawString(entity.getName(), x, y);
			y+=m.getHeight();
		}
	/*
	 * Parses String into array of words, 
	 * prints words until the text-box margins
	 */
		String[] words = s.split(" ");
		String currentLine="";
		int i=0;
		if(game.continueDialogue==true) {//exit if no remaining dialogue
			if(s.trim().length()==0) {
				game.dialogue=false;
				game.continueDialogue=false;
				return y;
			}
		}
		int max=textboxY+textboxHeight-2*(int)(textboxHeight*.1);//max length
	
		while(  i < words.length && y <= max){
			if(m.stringWidth(currentLine+words[i])<=lineWidth) { //if room, add word
				currentLine+=" "+ words[i];
				g.drawString(currentLine,x,y);
				i++;
				if(NPC==true &&i==words.length) {
					y+=m.getHeight();
					continueHeight=y-textboxY;
				}
			}
			else {
				g.drawString(currentLine, x,y);
				y+=m.getHeight();//new line coordinate
				currentLine=words[i]; 
				i++;
			}
		
		} 
		if(NPC==true) {
		g.setColor(Color.blue);
		g.drawString("continue", x+(textboxWidth/2), y);
		continueX=x+(textboxWidth/2);
		continueY=y;
		continueWidth=textboxX+textboxWidth-x;
		 g.setColor(new Color(101,67,33));
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
		return y;
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
	
	public boolean checkHovering(int x, int y) {
		// TODO Auto-generated method stub
		if(NPC==false) {
			
			//check response areas
			for(int i =0; i<responseX.length;i++) {
				if(x<responseX[i]+rwidth[i] && x >responseX[i] && y <responseY[i]+rheight[i] && y >responseY[i])
				{
					return true;
				}
				
			}
			return false;
		}
		return false;
	}
	
	public void checkResponse(int x, int y) {
		// TODO Auto-generated method stub
		if(NPC==false) {
		
			//check response areas
			for(int i =0; i<responseX.length;i++) {
//				System.out.println("checking clicked ");
//				System.out.println(responseX[i]+" "+responseY[i]);
//				System.out.println(rwidth[i]+" "+rheight[i]);
				if(x<responseX[i]+rwidth[i] && x >responseX[i] && y <responseY[i]+rheight[i] && y >responseY[i])
				{
					game.responsing=false;
					i++;
					game.dialogueObj.manager.chooseRespone(i);
					System.out.println("Choose "+ i);
					return;
				}
			
			}
			
		}
		else {
			if(x<continueX+continueWidth && x >continueX && y <continueY+continueHeight && y >continueY)
			game.continueDialogue=true;
		}
		
	}
	

	
}
