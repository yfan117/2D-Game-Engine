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
    private boolean inMenu;
    private MusicPlayer musicPlayer;

    public MainMenu(Display d)
    {
        try {
            musicPlayer = new MusicPlayer(Game.root + "/resources/music/PerditionsLight.wav");
        }catch(Exception e){e.printStackTrace();}

        layout = new BorderLayout();
        this.setLayout(layout);

        inMenu = true;
        display = d;
        img = new ImageIcon(Game.root + "/resources/images/MainMenu.png").getImage();

        startButtonImage = new ImageIcon(Game.root + "/resources/images/StartButton.png");
        startButton = new JButton();
        startButton.setIcon(startButtonImage);
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.setBorderPainted(false);
        startButton.addActionListener(e -> {inMenu = false; display.switchJPanels(); musicPlayer.stop();});
        add(startButton, BorderLayout.SOUTH);

        musicPlayer.play();
    }

    public boolean isInMenu()
    {
        return inMenu;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }
}