package menu;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private Image image;

    public GamePanel(Navigation navigation) {
        // טעינת תמונת הרקע
        image = new ImageIcon(getClass().getResource("/Backgrounds/gameplay2.jpg")).getImage();

        // הגדרת פריסת BorderLayout לפאנל הראשי
        setLayout(new BorderLayout());

        //  יצירת כפתור BACK עם תמונה
        ImageIcon backIcon = new ImageIcon(getClass().getResource("/Buttons/back.jpg"));
        Image scaledBackIcon = backIcon.getImage().getScaledInstance(MainMenu.BUTTON_WIDTH, MainMenu.BUTTON_HEIGHT, Image.SCALE_SMOOTH);
        JButton backButton = new JButton(new ImageIcon(scaledBackIcon));
        backButton.setPreferredSize(new Dimension(MainMenu.BUTTON_WIDTH, MainMenu.BUTTON_HEIGHT));
        backButton.setMaximumSize(new Dimension(MainMenu.BUTTON_WIDTH, MainMenu.BUTTON_HEIGHT));
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.setOpaque(false);
        backButton.addActionListener(e -> navigation.switchToMainMenu());

        //  יצירת כפתור PAUSE עם תמונה-
        ImageIcon pauseIcon = new ImageIcon(getClass().getResource("/Buttons/pause.jpg"));
        Image scaledPauseIcon = pauseIcon.getImage().getScaledInstance(MainMenu.BUTTON_WIDTH, MainMenu.BUTTON_HEIGHT, Image.SCALE_SMOOTH);
        JButton pauseButton = new JButton(new ImageIcon(scaledPauseIcon));
        pauseButton.setPreferredSize(new Dimension(MainMenu.BUTTON_WIDTH, MainMenu.BUTTON_HEIGHT));
        pauseButton.setMaximumSize(new Dimension(MainMenu.BUTTON_WIDTH, MainMenu.BUTTON_HEIGHT));
        pauseButton.setBorderPainted(false);
        pauseButton.setContentAreaFilled(false);
        pauseButton.setFocusPainted(false);
        pauseButton.setOpaque(false);
        pauseButton.addActionListener(e -> navigation.switchToPauseMenu());

        //  פאנל אנכי עם שני הכפתורים
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);
        buttonPanel.add(backButton);
        buttonPanel.add(Box.createVerticalStrut(10)); // רווח בין הכפתורים
        buttonPanel.add(pauseButton);

        //  עטיפה בצד ימין למעלה
        JPanel container = new JPanel(new BorderLayout());
        container.setOpaque(false);
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        container.add(buttonPanel, BorderLayout.EAST);

        // הוספת הקונטיינר למסך
        add(container, BorderLayout.NORTH);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}
