package menu;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

public class PauseScreen extends JPanel {
    public PauseScreen(MainFrame frame) {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextArea textArea = new JTextArea("Paused");
        textArea.setDragEnabled(false);
        textArea.setEditable(false);

        JButton backButton = new JButton("Continue");
        backButton.addActionListener(e -> frame.showCard("game"));

        JButton menuButton = new JButton("Main Menu");
        menuButton.addActionListener(e -> frame.showCard("menu"));
        add(menuButton);

        gbc.gridy = 0;
        add(textArea, gbc);
        gbc.gridy++;
        add(backButton, gbc);
        gbc.gridy++;
        add(menuButton, gbc);
    }
}