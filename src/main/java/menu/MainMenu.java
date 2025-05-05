package menu;

import utils.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainMenu extends JPanel {

    private Image image;
    private static final SoundManager soundManager = new SoundManager();

    public static final int BUTTON_WIDTH = 200, BUTTON_HEIGHT = 60;

    public MainMenu(Navigation navigation) {
        image = new ImageIcon("src/Resources/Backgrounds/main_menu_screen.jpg").getImage();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        soundManager.play("src/Resources/Music/MainMenuSound.wav");

        ImageIcon startIcon = new ImageIcon("src/Resources/Buttons/start_game_button.jpg");
        JButton startGameButton = new JButton(startIcon);
        startGameButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        startGameButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        startGameButton.setAlignmentX(CENTER_ALIGNMENT);
        startGameButton.setBorderPainted(false);
        startGameButton.setContentAreaFilled(false);
        startGameButton.setFocusPainted(false);
        startGameButton.setOpaque(false);

        ImageIcon rawIcon = new ImageIcon("src/Resources/Buttons/instructions_button.jpg");
        Image scaledImage = rawIcon.getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImage);

        JButton instructionButton = new JButton(icon);
        instructionButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        instructionButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        instructionButton.setAlignmentX(CENTER_ALIGNMENT);
        instructionButton.setBorderPainted(false);
        instructionButton.setContentAreaFilled(false);
        instructionButton.setFocusPainted(false);
        instructionButton.setOpaque(false);

        ImageIcon exitIcon = new ImageIcon("src/Resources/Buttons/exit_button.jpg");
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
        add(Box.createVerticalGlue());


        //if we would like to have different music in each panel we will have to stop the music before moving to other panel
//        startGameButton.addActionListener(e -> {
//            navigation.switchToGamePanel();
//        });
        startGameButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundManager.stop();
                navigation.switchToGamePanel();
            }
        });

        instructionButton.addActionListener(e -> {
            navigation.switchToInstruction();
        });

        exitButten.addActionListener(e -> {
            System.exit(0);
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}