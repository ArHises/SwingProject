package main;

//import entities.Bullet;
import entities.Enemy;
import entities.Player;
import menu.MainFrame;
import menu.MainMenu;
import menu.Navigation;
import utils.EnemySpawner;
//import utils.InputHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {

    private Image image;
    private final Player PLAYER;
//    private final ArrayList<Bullet> BULLETS;

    private final EnemySpawner enemySpawner;
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
        setupKeyBindings();
        requestFocusInWindow();

        PLAYER = new Player(375, 500, 50, 50, 5, 100);
        enemySpawner = new EnemySpawner(PLAYER);
//        BULLETS = new ArrayList<>();

//        InputHandler inputHandler = new InputHandler(BULLETS);
//        addKeyListener(inputHandler);
//        addMouseListener(inputHandler);

        GAME_LOOP = new GameLoop(this);
        GAME_LOOP.start();
    }

    public void updateGame() {
        PLAYER.update();
        enemySpawner.update();
    }

    public void setPaused(boolean paused) {
        GAME_LOOP.setPaused(paused);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(),this);

        PLAYER.draw(g);
        enemySpawner.draw(g);
    }

    private void setupKeyBindings() {
        // tells the component what keys to listen for.
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        // tells it what to do when those keys are pressed or released.
        ActionMap actionMap = getActionMap();

        // Movement keys
        bindKey(inputMap,
                actionMap,
                "pressed W",
                "released W",
                KeyEvent.VK_W,
                () -> PLAYER.setUp(true),
                () -> PLAYER.setUp(false));
        bindKey(inputMap,
                actionMap,
                "pressed A",
                "released A",
                KeyEvent.VK_A,
                () -> PLAYER.setLeft(true),
                () -> PLAYER.setLeft(false));
        bindKey(inputMap,
                actionMap,
                "pressed S",
                "released S",
                KeyEvent.VK_S,
                () -> PLAYER.setDown(true),
                () -> PLAYER.setDown(false));
        bindKey(inputMap,
                actionMap,
                "pressed D",
                "released D",
                KeyEvent.VK_D,
                () -> PLAYER.setRight(true),
                () -> PLAYER.setRight(false));
    }

    private void bindKey(InputMap im,
                         ActionMap am,
                         String pressKey,
                         String releaseKey,
                         int keyCode,
                         Runnable onPress,
                         Runnable onRelease) {

        im.put(KeyStroke.getKeyStroke(keyCode,
                0, false), pressKey);
        im.put(KeyStroke.getKeyStroke(keyCode,
                0, true), releaseKey);

        am.put(pressKey, new AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                onPress.run(); // call the lambda: PLAYER.setUp(true)
            }
        });
        am.put(releaseKey, new AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                onRelease.run(); // call the lambda: PLAYER.setUp(false)
            }
        });
    }
}