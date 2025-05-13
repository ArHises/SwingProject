package menu;

import javax.swing.*;
import java.awt.*;

import main.GamePanel;
import utils.SoundManager;


public class MainFrame extends JFrame implements Navigation {

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private PauseMenu pauseMenu ;
    private GamePanel gamePanel ;
    private MainMenu mainMenu ;
    private InstructionScreen instructionScreen ;
    private final SoundManager soundManager = new SoundManager();


    public MainFrame() {

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        mainMenu = new MainMenu(this);
        instructionScreen = new InstructionScreen(this);
        gamePanel = new GamePanel(this,soundManager);
        pauseMenu = new PauseMenu(this);

        cardPanel.add(instructionScreen, "Instruction");
        cardPanel.add(mainMenu,"Main Menu" );
        soundManager.playLoop("src/Resources/Music/MainMenuSound.wav");
        cardPanel.add(gamePanel,"Game Panel");
        cardPanel.add(pauseMenu,"Pause Menu");

        add(cardPanel);
        cardLayout.show(cardPanel, "Main Menu");
    }

    public void switchToInstruction () {
        gamePanel.setPaused(true);
        cardLayout.show(cardPanel, "Instruction");
    }
    public  void switchToPauseMenu () {
        gamePanel.setPaused(true);
        soundManager.playLoop("src/Resources/Music/MainMenuSound.wav");
        cardLayout.show(cardPanel, "Pause Menu");
    }
    public void switchToGamePanel() {
        soundManager.playLoop("src/Resources/Music/DuringGameMusic.wav");
        gamePanel.setPaused(false);
        gamePanel.requestFocus();
        cardLayout.show(cardPanel, "Game Panel");
    }
    public void switchToMainMenu() {
        gamePanel.setPaused(true);
        soundManager.playLoop("src/Resources/Music/MainMenuSound.wav");
        cardLayout.show(cardPanel, "Main Menu");
        cardPanel.remove(gamePanel);
        gamePanel = new GamePanel(this, soundManager);
        cardPanel.add(gamePanel, "Game Panel");
        cardPanel.revalidate();
        cardPanel.repaint();
    }
}

