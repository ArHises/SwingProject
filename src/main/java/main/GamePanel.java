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
// ---------------- logic start ------------------------
    private final Player PLAYER;
    private final ArrayList<Enemy> ENEMIES;
    private final ArrayList<Bullet> BULLETS;

//    private final EnemySpawner SPAWNER;
    private final GameLoop GAME_LOOP;

//    public GamePanel(MainFrame frame) {
//        setFocusable(true);
//        setLayout(null);
//        setBackground(Color.BLACK);
//        requestFocusInWindow();
//
//        // --- Initialize Game Entities ---
//        PLAYER = new Player(375, 500, 50, 50, 5);
//        ENEMIES = new ArrayList<>();
//        BULLETS = new ArrayList<>();
//        SPAWNER = new EnemySpawner(ENEMIES, PLAYER);
//
//        // --- Input Handling ---
//        InputHandler inputHandler = new InputHandler(PLAYER, BULLETS);
//        addKeyListener(inputHandler);
//        addMouseListener(inputHandler);
//
//        // --- Game Loop ---
//        GAME_LOOP = new GameLoop(this);
//        GAME_LOOP.start();
//
//        // --- Pause Button ---
//        JButton pauseButton = new JButton("Pause");
//        pauseButton.setBounds(10, 10, 150, 30);
//        pauseButton.addActionListener(e -> frame.showCard("pause"));
//        add(pauseButton);
//    }

//    public void setPaused(boolean paused) {
//        GAME_LOOP.setPaused(paused);
//    }

//    public void updateGame() {
//        PLAYER.update();
//        SPAWNER.update();
//
//        // Update Enemies
//        for (Enemy enemy : ENEMIES) {
//            enemy.update();
//        }
//
//        // Update Bullets
//        for (int i = 0; i < BULLETS.size(); i++) {
//            Bullet bullet = BULLETS.get(i);
//            bullet.update();
//
//            // Remove bullets off-screen
//            if (bullet.getX() < 0 || bullet.getX() > getWidth()
//                    || bullet.getY() < 0 || bullet.getY() > getHeight()) {
//                BULLETS.remove(i--);
//                continue;
//            }
//
//            // Bullet-Enemy Collision
//            for (int j = 0; j < ENEMIES.size(); j++) {
//                Enemy enemy = ENEMIES.get(j);
//                if (bullet.collidesWith(enemy)) {
//                    ENEMIES.remove(j--);
//                    BULLETS.remove(i--);
//                    break;
//                }
//            }
//        }
//    }

//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        PLAYER.draw(g);
//
//        for (Enemy enemy : ENEMIES) {
//            enemy.draw(g);
//        }
//
//        for (Bullet bullet : BULLETS) {
//            bullet.draw(g);
//        }
//    }
//    ------------- logic end --------------------------
    public GamePanel(Navigation navigation, MainFrame frame) {
        image = new ImageIcon(getClass()
                .getResource("/Backgrounds/game_screen.jpg")).getImage();

        setLayout(new BorderLayout());

        ImageIcon backIcon = new ImageIcon(getClass()
                .getResource("/Buttons/back_button.jpg"));
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

        ImageIcon pauseIcon = new ImageIcon(getClass()
                .getResource("/Buttons/pause_button.jpg"));
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

//        Game Logic part:

        setFocusable(true);
        setLayout(null);
        setBackground(Color.BLACK);
        requestFocusInWindow();

        // --- Initialize Game Entities ---
        PLAYER = new Player(375, 500, 50, 50, 5);
        ENEMIES = new ArrayList<>();
        BULLETS = new ArrayList<>();
//        SPAWNER = new EnemySpawner(ENEMIES, PLAYER);

        // --- Input Handling ---
        InputHandler inputHandler = new InputHandler(PLAYER, BULLETS);
        addKeyListener(inputHandler);
        addMouseListener(inputHandler);

        // --- Game Loop ---
        GAME_LOOP = new GameLoop(this);
        GAME_LOOP.start();

        // --- Pause Button ---
//        JButton pauseButton = new JButton("Pause");
//        pauseButton.setBounds(10, 10, 150, 30);
//        pauseButton.addActionListener(e -> frame.showCard("pause"));
//        add(pauseButton);
    }

    public void updateGame() {
        PLAYER.update();
//        SPAWNER.update();

        // Update Enemies
        for (Enemy enemy : ENEMIES) {
            enemy.update();
        }

        // Update Bullets
        for (int i = 0; i < BULLETS.size(); i++) {
            Bullet bullet = BULLETS.get(i);
            bullet.update();

            // Remove bullets off-screen
            if (bullet.getX() < 0 || bullet.getX() > getWidth()
                    || bullet.getY() < 0 || bullet.getY() > getHeight()) {
                BULLETS.remove(i--);
                continue;
            }

            // Bullet-Enemy Collision
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

        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}
