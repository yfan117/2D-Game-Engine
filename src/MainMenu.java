package Diablo;

import java.awt.*;
import javax.swing.*;

public class MainMenu extends JPanel
{
    private BorderLayout layout;
    private JPanel bottomPanel;
    private Image img;
    private ImageIcon continueButtonImage;
    private ImageIcon newGameButtonImage;
    private ImageIcon loadGameButtonImage;
    private ImageIcon settingsButtonImage;
    private JButton continueButton;
    private JButton newGameButton;
    private JButton loadGameButton;
    private JButton settingsButton;
    private Display display;
    private MusicPlayer musicPlayer;
    private Game game;

    public MainMenu(Display d, Game game)
    {
        try {
            musicPlayer = new MusicPlayer(Game.root + "/resources/music/PerditionsLight.wav");
        }catch(Exception e){e.printStackTrace();}

        layout = new BorderLayout();
        this.setLayout(layout);

        bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);

        this.game = game;
        display = d;
        img = new ImageIcon(Game.root + "/resources/images/MainMenu.png").getImage();

        continueButtonImage = new ImageIcon(Game.root + "/resources/images/ContinueButton.png");
        newGameButtonImage = new ImageIcon(Game.root + "/resources/images/NewGameButton.png");
        loadGameButtonImage = new ImageIcon(Game.root + "/resources/images/LoadGameButton.png");
        settingsButtonImage = new ImageIcon(Game.root + "/resources/images/SettingsButton.png");

        continueButton = new JButton();
        newGameButton = new JButton();
        loadGameButton = new JButton();
        settingsButton = new JButton();

        continueButton.setIcon(continueButtonImage);
        newGameButton.setIcon(newGameButtonImage);
        loadGameButton.setIcon(loadGameButtonImage);
        settingsButton.setIcon(settingsButtonImage);

        continueButton.setOpaque(false);
        continueButton.setContentAreaFilled(false);
        continueButton.setFocusPainted(false);
        continueButton.setBorderPainted(false);

        newGameButton.setOpaque(false);
        newGameButton.setContentAreaFilled(false);
        newGameButton.setFocusPainted(false);
        newGameButton.setBorderPainted(false);

        loadGameButton.setOpaque(false);
        loadGameButton.setContentAreaFilled(false);
        loadGameButton.setFocusPainted(false);
        loadGameButton.setBorderPainted(false);

        settingsButton.setOpaque(false);
        settingsButton.setContentAreaFilled(false);
        settingsButton.setFocusPainted(false);
        settingsButton.setBorderPainted(false);

        continueButton.addActionListener(e -> { musicPlayer.stopSong(); display.switchJPanels(1);});
        newGameButton.addActionListener(e -> {System.out.println("New Game");});
        loadGameButton.addActionListener(e -> {System.out.println("Load Game");});
        settingsButton.addActionListener(e -> {System.out.println("Settings");});

        bottomPanel.add(continueButton);
        bottomPanel.add(newGameButton);
        bottomPanel.add(loadGameButton);
        bottomPanel.add(settingsButton);

        add(bottomPanel, BorderLayout.SOUTH);

        musicPlayer.start();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }

    public void startMusic() {
        try {
            musicPlayer = new MusicPlayer(Game.root + "/resources/music/PerditionsLight.wav");
        }catch(Exception e){e.printStackTrace();}

        musicPlayer.play();
    }
}