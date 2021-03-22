package Diablo;

import java.awt.*;
import java.io.File;
import java.util.List;
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
    private String newGameString;
    private String loadName;
    private LoadGame loadGame;
    private File lastLoaded;

    public MainMenu(Display d, Game game)
    {
        lastLoaded = new File(game.root + "/resources/text/lastLoaded.txt");

        layout = new BorderLayout();
        this.setLayout(layout);

        loadGame = game.getLoadFile();

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

        continueButton.addActionListener(e -> {
            if(lastLoaded.exists())
            {
                game.setLoadName(loadGame.lastLoaded());
                game.populateEntityList();

                musicPlayer.stopSong();
                display.switchJPanels(1);
            }
            else
            {
                JOptionPane.showMessageDialog(this, "No save files exist");
            }
        });
        newGameButton.addActionListener(e -> {
            newGameString = JOptionPane.showInputDialog(this, "Enter save name");

            if(newGameString != null)
            {
                loadGame.saveLastLoaded(newGameString);
                loadGame.append(newGameString);
                loadGame.saveNewGame(newGameString);

                loadName = newGameString;
                game.setLoadName(loadName);
                game.populateEntityList();

                musicPlayer.stopSong();
                display.switchJPanels(1);
            }
        });
        loadGameButton.addActionListener(e -> {
            List<String> savesList = loadGame.getSavesList();
            String[] savesArray = new String[savesList.size()];
            savesArray = savesList.toArray(savesArray);

            try
            {
                loadName = (String) JOptionPane.showInputDialog(this, "", "All saves", JOptionPane.QUESTION_MESSAGE, null, savesArray, savesArray[0]);

                if (loadName != null)
                {
                    loadGame.saveLastLoaded(loadName);
                    game.setLoadName(loadName);
                    game.populateEntityList();

                    musicPlayer.stopSong();
                    display.switchJPanels(1);
                }
            }catch(Exception ex)
            {
                JOptionPane.showMessageDialog(this, "No save files exist");
            }
        });
        settingsButton.addActionListener(e -> {System.out.println("Settings");});

        bottomPanel.add(continueButton);
        bottomPanel.add(newGameButton);
        bottomPanel.add(loadGameButton);
        bottomPanel.add(settingsButton);

        add(bottomPanel, BorderLayout.SOUTH);

        startMusic();
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

        musicPlayer.start();
    }

    public void pauseMusic(){musicPlayer.pause();}
    public void playMusic(){musicPlayer.play();}
    public boolean isMusicPlaying(){return musicPlayer.isRunning();}
}