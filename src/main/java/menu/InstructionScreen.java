package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InstructionScreen extends JPanel {
    private Image image;

    public InstructionScreen(Navigation navigation) {
        image = new ImageIcon(getClass().getResource("/Backgrounds/instructions_screen.jpg")).getImage();

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        ImageIcon backIcon = new ImageIcon(getClass().getResource("/Buttons/back_button.jpg"));
        Image scaledBackIcon = backIcon.getImage().getScaledInstance(MainMenu.BUTTON_WIDTH, MainMenu.BUTTON_HEIGHT, Image.SCALE_SMOOTH);
        JButton jButton = new JButton(new ImageIcon(scaledBackIcon));
        jButton.setPreferredSize(new Dimension(MainMenu.BUTTON_WIDTH, MainMenu.BUTTON_HEIGHT));
        jButton.setMaximumSize(new Dimension(MainMenu.BUTTON_WIDTH, MainMenu.BUTTON_HEIGHT));
        jButton.setBorderPainted(false);
        jButton.setContentAreaFilled(false);
        jButton.setFocusPainted(false);
        jButton.setOpaque(false);

        jButton.setPreferredSize(new Dimension(MainMenu.BUTTON_WIDTH, MainMenu.BUTTON_HEIGHT));
        jButton.setMaximumSize(new Dimension(MainMenu.BUTTON_WIDTH, MainMenu.BUTTON_HEIGHT));

        jButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigation.switchToMainMenu();
            }
        });

        topPanel.add(jButton, BorderLayout.EAST);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(topPanel, BorderLayout.NORTH);

        topPanel.setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image,0,0,getWidth(),getHeight(),this);
    }
}
