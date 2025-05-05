package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PauseMenu extends JPanel {
    private Image backgroundImage;

    public PauseMenu(Navigation navigation) {
        // טען את הרקע מתיקייה מקומית
        backgroundImage = new ImageIcon("src/Resources/Backgrounds/pause_screen.jpg").getImage();

        setLayout(new BorderLayout());

        // טען את כפתור ה־Back מתיקייה מקומית
        ImageIcon backIcon = new ImageIcon("src/Resources/Buttons/back_button.jpg");

        Image scaledBackIcon = backIcon.getImage()
                .getScaledInstance(
                        MainMenu.BUTTON_WIDTH,
                        MainMenu.BUTTON_HEIGHT,
                        Image.SCALE_SMOOTH);
        JButton backButton = new JButton(new ImageIcon(scaledBackIcon));

        backButton.setPreferredSize(
                new Dimension(MainMenu.BUTTON_WIDTH,
                        MainMenu.BUTTON_HEIGHT));

        backButton.setMaximumSize(
                new Dimension(MainMenu.BUTTON_WIDTH,
                        MainMenu.BUTTON_HEIGHT));

        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.setOpaque(false);

        backButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigation.switchToGamePanel();
            }
        });

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.add(backButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
 }
}
}