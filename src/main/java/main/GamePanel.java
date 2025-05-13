package main;


import entities.Player;
import menu.MainFrame;
import menu.MainMenu;
import menu.Navigation;
import utils.EnemySpawner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import utils.SoundManager;


public class GamePanel extends JPanel {

    private final SoundManager soundManager;
    private final Navigation navigation;
    private Image image;
    private final Player PLAYER;
    private final EnemySpawner enemySpawner;
    private final GameLoop GAME_LOOP;
    private boolean gameOver = false;
    private boolean gameOverSound  = false;
    private long   gameOverStart = 0L;


    public GamePanel(Navigation navigation, MainFrame frame,
                     SoundManager soundManager) {
        // ✅ טען רקע כקובץ מקומי
        image = new ImageIcon("src/Resources/Backgrounds/game_screen.jpg").getImage();
        this.navigation = navigation;
        this.soundManager = soundManager;
        setLayout(new BorderLayout());
        setFocusable(true);
        requestFocusInWindow();


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

        setupKeyBindings();


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
        if (gameOver) {
            if(!gameOverSound) {
                soundManager.playOnce("src/Resources/Music/GameOverMusic.wav");
                gameOverSound = true;
            }
            if (System.currentTimeMillis() - gameOverStart >= 5_000) {
                navigation.switchToMainMenu();
            }
            return;
        }
        PLAYER.update();
        enemySpawner.update();
        if (PLAYER.getHealth() <= 0) {
            gameOver      = true;
            gameOverStart = System.currentTimeMillis();
        }
    }

    public void setPaused(boolean paused) {
        PLAYER.stopMoving(); // to resolve a bug that the player is stack moving when paused
        GAME_LOOP.setPaused(paused);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

        PLAYER.draw(g);
        enemySpawner.draw(g);


        if (gameOver) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(new Color(255, 0, 0, 200));
            g2.fillRect(0, 0, getWidth(), getHeight());

            String msg = "YOU DIED";
            g2.setFont(new Font("Arial", Font.BOLD, 64));
            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth()  - fm.stringWidth(msg)) / 2;
            int y = (getHeight() + fm.getAscent())      / 2;
            g2.setColor(Color.WHITE);
            g2.drawString(msg, x, y);
            g2.dispose();
        }
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
                () -> PLAYER.move(KeyEvent.VK_W));
        bindKey(inputMap,
                actionMap,
                "pressed A",
                "released A",
                KeyEvent.VK_A,
                () -> PLAYER.move(KeyEvent.VK_A));
        bindKey(inputMap,
                actionMap,
                "pressed S",
                "released S",
                KeyEvent.VK_S,
                () -> PLAYER.move(KeyEvent.VK_S));
        bindKey(inputMap,
                actionMap,
                "pressed D",
                "released D",
                KeyEvent.VK_D,
                () -> PLAYER.move(KeyEvent.VK_D));
    }

    private void bindKey(InputMap im,
                         ActionMap am,
                         String pressKey,
                         String releaseKey,
                         int keyCode,
                         Runnable onPress) {

        im.put(KeyStroke.getKeyStroke(keyCode,
                0, false), pressKey);
        im.put(KeyStroke.getKeyStroke(keyCode,
                0, true), releaseKey);

        am.put(pressKey, new AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                onPress.run(); // call the lambda: PLAYER.setUp(true)
            }
        });
    }
}