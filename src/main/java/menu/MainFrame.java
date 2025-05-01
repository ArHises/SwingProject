package menu;

import main.GamePanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel mainPanel;

    private final GamePanel gamePanel;
    private final MainMenu mainMenu;
    private final InstructionsScreen instructions;

    public MainFrame() {
        setTitle("My Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        gamePanel = new GamePanel(this);
        mainMenu = new MainMenu(this);
        instructions = new InstructionsScreen(this);

        mainPanel.add(mainMenu, "menu");
        mainPanel.add(gamePanel, "game");
        mainPanel.add(instructions, "instructions");

        add(mainPanel);
        showCard("menu");
    }

    public void showCard(String name) {
        cardLayout.show(mainPanel, name);
        gamePanel.setPaused(!"game".equals(name));

        if ("game".equals(name)) {
            gamePanel.requestFocusInWindow();
        }
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
