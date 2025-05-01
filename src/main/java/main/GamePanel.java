package main;

import entities.Bullet;
import entities.Enemy;
import entities.Player;
import menu.MainFrame;
import utils.EnemySpawner;
import utils.InputHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    private final Player PLAYER;
    private final ArrayList<Enemy> ENEMIES;
    private final ArrayList<Bullet> BULLETS;
    private final EnemySpawner SPAWNER;
    private final GameLoop GAME_LOOP;

    public GamePanel(MainFrame frame) {
        setFocusable(true);
        setLayout(null);
        setBackground(Color.BLACK);
        requestFocusInWindow();

        // --- Initialize Game Entities ---
        PLAYER = new Player(375, 500, 50, 50, 5);
        ENEMIES = new ArrayList<>();
        BULLETS = new ArrayList<>();
        SPAWNER = new EnemySpawner(ENEMIES, PLAYER);

        // --- Input Handling ---
        InputHandler inputHandler = new InputHandler(PLAYER, BULLETS);
        addKeyListener(inputHandler);
        addMouseListener(inputHandler);

        // --- Game Loop ---
        GAME_LOOP = new GameLoop(this);
        GAME_LOOP.start();

        // --- Pause Button ---
        JButton pauseButton = new JButton("Pause");
        pauseButton.setBounds(10, 10, 150, 30);
        pauseButton.addActionListener(e -> frame.showCard("pause"));
        add(pauseButton);
    }

    public void setPaused(boolean paused) {
        GAME_LOOP.setPaused(paused);
    }

    public void updateGame() {
        PLAYER.update();
        SPAWNER.update();

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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        PLAYER.draw(g);

        for (Enemy enemy : ENEMIES) {
            enemy.draw(g);
        }

        for (Bullet bullet : BULLETS) {
            bullet.draw(g);
        }
    }
}
