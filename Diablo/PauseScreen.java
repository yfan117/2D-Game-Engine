package Diablo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class PauseScreen extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private Display display;
	private MusicPlayer musicPlayer;
	private LoadGame loadGame;

	public PauseScreen(Game game, Display display){
		//need to create new music player, maybe having one list of musicplayers in the game class for all others to access is better
		loadGame = game.getLoadFile();
		 
		this.display=display;
		this.panel= new JPanel();
		this.setLayout(new BorderLayout());
		
		JPanel buttons= new JPanel();
		buttons.setLayout(new FlowLayout());
		
		//buttons
		JButton button = new JButton("Continue" );
		button.setBackground(Color.BLACK);
		button.setForeground(Color.WHITE);
		JButton button2= new JButton("Main Menu");
		button2.setBackground(Color.BLACK);
		button2.setForeground(Color.WHITE);
		JButton saveButton= new JButton("Save game");
		saveButton.setBackground(Color.BLACK);
		saveButton.setForeground(Color.WHITE);
		button.addActionListener(e -> {display.switchJPanels(1); } );//continue
		button2.addActionListener(e -> {display.switchJPanels(0);} );//menu
		saveButton.addActionListener(e -> {
			System.out.println("Save game");
			loadGame.saveGame(game.getLoadName());
		});
		//button panel
		buttons.add(button);
		buttons.add(button2);
		buttons.add(saveButton);
		buttons.setBackground(Color.BLACK);
		
		//Screen Title
		JLabel paused= new JLabel("Paused");
		paused.setFont(new Font("Helvetica", Font.BOLD|Font.ITALIC,35));
		paused.setForeground(Color.WHITE);
		
		//Putting the components together
		GridBagConstraints gbc = new GridBagConstraints();
		panel.setLayout(new GridBagLayout());
		gbc.insets = new Insets(10,0,10,0);//object spacing ints:( top, left, bottom, right )
		gbc.gridx=1;
		gbc.gridy=0;
		panel.add(paused,gbc);
		gbc.gridx=1;
		gbc.gridy=1;
		panel.add(buttons,gbc);

		//final panel settings
		panel.setBackground(Color.BLACK);
		this.add(panel,BorderLayout.CENTER);
		
	}
	public JPanel getPanel(){
		return panel;
	}	

}
