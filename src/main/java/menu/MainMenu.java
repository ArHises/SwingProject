package menu;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {
    public MainMenu(MainFrame frame) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton startButton = new JButton("Start Game");
        JButton instructionsButton = new JButton("Instructions");
        JButton exitButton = new JButton("Exit");

        startButton.addActionListener(e -> frame.showCard("game"));
        instructionsButton.addActionListener(e -> frame.showCard("instructions"));
        exitButton.addActionListener(e -> System.exit(0));

        gbc.gridy = 0;
        add(startButton, gbc);
        gbc.gridy++;
        add(instructionsButton, gbc);
        gbc.gridy++;
        add(exitButton, gbc);
    }
}
