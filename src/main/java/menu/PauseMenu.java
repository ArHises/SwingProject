package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PauseMenu extends JPanel {
    private Image backgroundImage;

    public PauseMenu(Navigation navigation) {
        // טוען את תמונת הרקע למסך ה־Pause (אם קיימת)
        backgroundImage = new ImageIcon(getClass().getResource("/Backgrounds/BAKEGROUNDZ.jpg")).getImage();

        // קובע פריסה כללית
        setLayout(new BorderLayout());

        // יצירת כפתור BACK עם תמונה
        ImageIcon backIcon = new ImageIcon(getClass().getResource("/Buttons/back.jpg"));
        Image scaledBackIcon = backIcon.getImage().getScaledInstance(MainMenu.BUTTON_WIDTH, MainMenu.BUTTON_HEIGHT, Image.SCALE_SMOOTH);
        JButton backButton = new JButton(new ImageIcon(scaledBackIcon));

        backButton.setPreferredSize(new Dimension(MainMenu.BUTTON_WIDTH, MainMenu.BUTTON_HEIGHT));
        backButton.setMaximumSize(new Dimension(MainMenu.BUTTON_WIDTH, MainMenu.BUTTON_HEIGHT));
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.setOpaque(false);

        // פעולה כשנלחץ כפתור BACK → חזרה למשחק
        backButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigation.switchToGamePanel(); // מעבר חזרה למסך המשחק
            }
        });

        // מיקום הכפתור בפינה הימנית העליונה
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
