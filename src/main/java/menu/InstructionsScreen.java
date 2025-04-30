package menu;

import javax.swing.*;
import java.awt.*;

public class InstructionsScreen extends JPanel {
    public InstructionsScreen(MainFrame frame) {
        setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea("Use arrow keys to move.\nAvoid enemies.\nCollect points.");
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> frame.showCard("menu"));
        add(backButton, BorderLayout.SOUTH);
    }
}