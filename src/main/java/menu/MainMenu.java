package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainMenu extends JPanel {
    private Image image;
   private Image imageButton;

    public static final int BUTTON_WIDTH = 200, BUTTON_HEIGHT = 60;

    public MainMenu(Navigation navigation) {
        image = new ImageIcon(getClass().getResource("/Backgrounds/main_menu.jpg")).getImage();
        // פריסה טובה כאשר צריך לסדר אלמנטים בשורה או עמודה
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        ImageIcon startIcon = new ImageIcon(getClass().getResource("/Buttons/StartGame02.jpg"));
        JButton startGameButton = new JButton(startIcon);
        startGameButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        startGameButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        startGameButton.setAlignmentX(CENTER_ALIGNMENT);
        startGameButton.setBorderPainted(false);
        startGameButton.setContentAreaFilled(false);
        startGameButton.setFocusPainted(false);
        startGameButton.setOpaque(false);

        ImageIcon rawIcon = new ImageIcon(getClass().getResource("/Buttons/instructions02.jpg"));
        Image scaledImage = rawIcon.getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImage);

        JButton instructionButton = new JButton(icon);
        instructionButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        instructionButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        instructionButton.setAlignmentX(CENTER_ALIGNMENT);

// אופציונלי – להסיר מסגרת ורקע
        instructionButton.setBorderPainted(false);
        instructionButton.setContentAreaFilled(false);
        instructionButton.setFocusPainted(false);
        instructionButton.setOpaque(false);




        ImageIcon exitIcon = new ImageIcon(getClass().getResource("/Buttons/exit02.jpg"));
        JButton exitButten = new JButton(exitIcon);
        exitButten.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        exitButten.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        exitButten.setAlignmentX(CENTER_ALIGNMENT);
        exitButten.setBorderPainted(false);
        exitButten.setContentAreaFilled(false);
        exitButten.setFocusPainted(false);
        exitButten.setOpaque(false);


        add(Box.createVerticalStrut(310));


        this.add(startGameButton);
        this.add(Box.createVerticalStrut(5)); // spacing
        this.add(instructionButton);
        this.add(Box.createVerticalStrut(5)); // spacing
        this.add(exitButten);
        add(Box.createVerticalGlue()); // Pushes components to center vertically

        startGameButton.addActionListener(e -> {
           navigation.switchToGamePanel();
        });
        instructionButton.addActionListener(e -> {
            navigation.switchToInstruction();
        });

        exitButten.addActionListener(e -> {
            System.exit(0);
        });


    }


    // If you want to paint stuff behind the panel (Backround, Shapes,..)
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image,0,0,getWidth(),getHeight(),this);
    }


}
