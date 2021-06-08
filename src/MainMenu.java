

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MainMenu {

    private final int FRAME_WIDTH = 1200;
    private final int FRAME_HEIGHT= 700;
    private static JFrame frame;
    private static JPanel imagePanel;
    private static JPanel menuPanel;
    private static JLabel imageLabel;
    private static JButton startButton;
    private static JButton menuButton;
    private static JButton settingsButton;
    private static JButton invisibleButton;
    private static JLabel background;
    private static ImageIcon menuBg;

    public static boolean isMusicPlaying;
    public static Settings settings;

    private boolean startGry =  false;

    static {
        isMusicPlaying = false;
    }


    MainMenu() {
        File file = new File(System.getProperty("user.dir") + "/gameData/playerFunds.txt");

        Scanner scanner = null;
        try {
            scanner = new Scanner(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(() ->
        {


            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "/images/iconBlackjack.png");

            if (!isMusicPlaying) {
                isMusicPlaying = true;
                PlayMusic musicPlayer = new PlayMusic();
                try {
                    PlayMusic.playMusic("Nuta #1");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnsupportedAudioFileException e) {
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }
            }

            frame = new JFrame("Gra w Czołgi");
            frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
            frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
            frame.setLayout(null);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setIconImage(icon.getImage());

            menuBg = new ImageIcon(new ImageIcon(System.getProperty("user.dir") + "/images/background.png").
                    getImage().getScaledInstance(FRAME_WIDTH, FRAME_HEIGHT, Image.SCALE_DEFAULT));

            background = new JLabel();
            background.setIcon(menuBg);
            background.setBounds(0, 0, 1200, 700);
            background.setOpaque(true);

            imagePanel = new JPanel();
            imagePanel.setLayout(null);
            imagePanel.setBounds(300, 10, 600, 280);
            imagePanel.setOpaque(true);

            ImageIcon image = new ImageIcon(System.getProperty("user.dir") + "/images/logo.png");

            imageLabel = new JLabel();
            imageLabel.setBounds(0, 0, 600, 280);
            imageLabel.setIcon(image);
            imageLabel.setOpaque(true);
            imageLabel.setBackground(new Color(0, 0, 0, 0));

            imagePanel.add(imageLabel);


            menuPanel = new JPanel();
            menuPanel.setLayout(null);
            menuPanel.setBounds(400, 300, 400, 320);
            menuPanel.setOpaque(false);

            startButton = new JButton("Start");
            startButton.setBounds(0, 0, 400, 60);
            startButton.setBackground(new Color(45, 39, 39));
            startButton.setForeground(Color.white);

            settingsButton = new JButton("Ustawienia");
            settingsButton.setBounds(0, 240, 400, 60);
            settingsButton.setBackground(new Color(45, 39, 39));
            settingsButton.setForeground(Color.white);
            invisibleButton = new JButton();
            invisibleButton.setBounds(0, 0, 0, 0);

            menuButton = new JButton("Menu");
            menuButton.setBounds(15, 585, 200, 60);


            addPanels();


            startButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            frame.getContentPane().removeAll();
                            startGry = true;
                            frame.dispose();
                        }
                    }
            );


            menuButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            frame.getContentPane().removeAll();
                            frame.revalidate();
                            addPanels();
                            frame.repaint();
                        }
                    }
            );

            settingsButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            if (settings == null) {
                                settings = new Settings();
                                settings.displaySettings();
                            }

                        }

                    }
            );


        });
    }

    public static void addPanels() {

        frame.getContentPane().removeAll();

        File file = new File(System.getProperty("user.dir") + "/gameData/playerFunds.txt");

        Scanner scanner = null;
        try {
            scanner = new Scanner(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        background.setIcon(menuBg);

        frame.repaint();
        frame.revalidate();


        frame.add(imagePanel);
        menuPanel.add(invisibleButton);
        menuPanel.add(startButton);
        menuPanel.add(settingsButton);

        frame.add(menuPanel);
        frame.add(background);

        frame.repaint();
        frame.revalidate();

    }
    public boolean czyZaczynac() {
        return startGry;
    }
    public void ustawStart() {
        startGry = false;
    }


}
