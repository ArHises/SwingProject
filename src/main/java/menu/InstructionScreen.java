package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InstructionScreen extends JPanel {
    private Image image;

    public InstructionScreen(Navigation navigation) {
        image = new ImageIcon(getClass().getResource("/Backgrounds/instructions2.jpg")).getImage();

        // שליטה על מיקומים של אלמנטים במסך על פי כיוון, צפון - למעלה מזרח - שמאלה, דרום - למטה, מערב - ימינה
        setLayout(new BorderLayout());

        //  פאנל עם כפתור שנמצא בצד ימין למעלה
        JPanel topPanel = new JPanel(new BorderLayout());
        ImageIcon backIcon = new ImageIcon(getClass().getResource("/Buttons/back.jpg"));
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

        // הכנסת הכפתור בצידו הימני של הפאנל
        topPanel.add(jButton, BorderLayout.EAST);
        // ריווח חיצוני לאלמנט ביחס למסך
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // הוספת הפאנל לחלקו העליון של המסך
        add(topPanel, BorderLayout.NORTH);



        // הפיכת הפאנל לשקופים כדי שייראו את הרקע
        topPanel.setOpaque(false);

    }

    // If you want to paint stuff behind the panel (Backround, Shapes,..)
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image,0,0,getWidth(),getHeight(),this);
    }
}
