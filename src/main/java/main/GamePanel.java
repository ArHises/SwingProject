package main;

import entities.Bullet;
import entities.Enemy;
import entities.Player;
import menu.MainFrame;
import menu.MainMenu;
import menu.Navigation;
import utils.InputHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    private Image image;
    private final Player PLAYER;
    private final ArrayList<Enemy> ENEMIES;
    private final ArrayList<Bullet> BULLETS;
    private final GameLoop GAME_LOOP;

    public GamePanel(Navigation navigation, MainFrame frame) {
        // ✅ טען רקע כקובץ מקומי
        image = new ImageIcon("src/Resources/Backgrounds/game_screen.jpg").getImage();
        setLayout(new BorderLayout());

        // ✅ טען כפתור BACK
        ImageIcon backIcon = new ImageIcon("src/Resources/Buttons/back_button.jpg");
        Image scaledBackIcon = backIcon.getImage()
                .getScaledInstance(MainMenu.BUTTON_WIDTH, MainMenu.BUTTON_HEIGHT, Image.SCALE_SMOOTH);
        JButton backButton = new JButton(new ImageIcon(scaledBackIcon));
        backButton.setPreferredSize(new Dimension(MainMenu.BUTTON_WIDTH, MainMenu.BUTTON_HEIGHT));
        backButton.setMaximumSize(new Dimension(MainMenu.BUTTON_WIDTH, MainMenu.BUTTON_HEIGHT));
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.setOpaque(false);
        backButton.addActionListener(e -> navigation.switchToMainMenu());


        // ✅ טען כפתור PAUSE
        ImageIcon pauseIcon = new ImageIcon("src/Resources/Buttons/pause_button.jpg");
        Image scaledPauseIcon = pauseIcon.getImage()
                .getScaledInstance(MainMenu.BUTTON_WIDTH, MainMenu.BUTTON_HEIGHT, Image.SCALE_SMOOTH);
        JButton pauseButton = new JButton(new ImageIcon(scaledPauseIcon));
        pauseButton.setPreferredSize(new Dimension(MainMenu.BUTTON_WIDTH, MainMenu.BUTTON_HEIGHT));
        pauseButton.setMaximumSize(new Dimension(MainMenu.BUTTON_WIDTH, MainMenu.BUTTON_HEIGHT));
        pauseButton.setBorderPainted(false);
        pauseButton.setContentAreaFilled(false);
        pauseButton.setFocusPainted(false);
        pauseButton.setOpaque(false);
        pauseButton.addActionListener(e -> navigation.switchToPauseMenu());

        // 🔲 מיקום כפתורים
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);
        buttonPanel.add(backButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(pauseButton);

        JPanel container = new JPanel(new BorderLayout());
        container.setOpaque(false);
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        container.add(buttonPanel, BorderLayout.EAST);
        add(container, BorderLayout.NORTH);

        // --- Game Logic part:
        setFocusable(true);
        //setLayout(null);
        setBackground(Color.BLACK);
        requestFocusInWindow();

        PLAYER = new Player(375, 500, 50, 50, 5);
        ENEMIES = new ArrayList<>();
        BULLETS = new ArrayList<>();

        InputHandler inputHandler = new InputHandler(PLAYER, BULLETS);
        addKeyListener(inputHandler);
        addMouseListener(inputHandler);

        GAME_LOOP = new GameLoop(this);
        GAME_LOOP.start();
    }

    public void updateGame() {
        PLAYER.update();

        for (Enemy enemy : ENEMIES) {
            enemy.update();
        }

        for (int i = 0; i < BULLETS.size(); i++) {
            Bullet bullet = BULLETS.get(i);
            bullet.update();

            if (bullet.getX() < 0 || bullet.getX() > getWidth()
                    || bullet.getY() < 0 || bullet.getY() > getHeight()) {
                BULLETS.remove(i--);
                continue;
            }

            for (int j = 0; j < ENEMIES.size(); j++) {
                Enemy enemy = ENEMIES.get(j);
                if (bullet.collidesWith(enemy)) {
                    ENEMIES.remove(j--);
                    BULLETS.remove(i--);
                    break;
                }
            }
        }
    }

    public void setPaused(boolean paused) {
        GAME_LOOP.setPaused(paused);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        PLAYER.draw(g);

        for (Enemy enemy : ENEMIES) {
            enemy.draw(g);
        }

        for (Bullet bullet : BULLETS) {
            bullet.draw(g);
        }

        g.drawImage(image, 0, 0, getWidth(), getHeight(),this);
}
}