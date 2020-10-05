package Diablo;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainMenu extends JPanel
{
    private BorderLayout layout;
    private Image img;
    private ImageIcon startButtonImage;
    private JButton startButton;
    private Display display;
    private MusicPlayer musicPlayer;

    public MainMenu(Display d)
    {
        try {
            musicPlayer = new MusicPlayer(Game.root + "/resources/music/PerditionsLight.wav");
        }catch(Exception e){e.printStackTrace();}

        layout = new BorderLayout();
        this.setLayout(layout);

        display = d;
        img = new ImageIcon(Game.root + "/resources/images/MainMenu.png").getImage();

        startButtonImage = new ImageIcon(Game.root + "/resources/images/StartButton.png");
        startButton = new JButton();
        startButton.setIcon(startButtonImage);
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.setBorderPainted(false);
        startButton.addActionListener(e -> {Game.changeGameState(1); display.switchJPanels(1); musicPlayer.stop();});
        add(startButton, BorderLayout.SOUTH);

        musicPlayer.play();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }
}