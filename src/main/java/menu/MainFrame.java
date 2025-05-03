package menu;

import javax.swing.*;
import java.awt.*;

import main.GamePanel;

public class MainFrame extends JFrame implements Navigation {

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private PauseMenu pauseMenu ;
    private GamePanel gamePanel ;
    private MainMenu mainMenu ;
    private InstructionScreen instructionScreen ;

    public MainFrame() {

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        mainMenu = new MainMenu(this);
        instructionScreen = new InstructionScreen(this);
        gamePanel = new GamePanel(this);
        pauseMenu = new PauseMenu(this);

        cardPanel.add(instructionScreen, "Instruction");
        cardPanel.add(mainMenu,"Main Menu" );
        cardPanel.add(gamePanel,"Game Panel");
        cardPanel.add(pauseMenu,"Pause Menu");

        add(cardPanel);
        cardLayout.show(cardPanel, "Main Menu");
    }


    public void switchToInstruction () {
        cardLayout.show(cardPanel, "Instruction");
    }
    public  void switchToPauseMenu () {
        cardLayout.show(cardPanel, "Pause Menu");
    }
    public void switchToGamePanel() {
        cardLayout.show(cardPanel, "Game Panel");
    }
    public void switchToMainMenu() {
        cardLayout.show(cardPanel, "Main Menu");
    }
}

