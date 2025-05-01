package main;

import entities.Bullet;
import entities.Player;
import entities.Enemy;
import menu.MainFrame;
import utils.EnemySpawner;
import utils.InputHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    private final Player PLAYER;
    private final ArrayList<Enemy> ENEMIES;
    private final ArrayList<Bullet> BULLETS;
    private final EnemySpawner SPAWNER;
    private final GameLoop GAME_LOOP;

    public GamePanel(MainFrame frame) {
        requestFocusInWindow();

        setFocusable(true);
        setLayout(null);
        setBackground(Color.BLACK);

        // Temporary player sprite:
        Image dummyImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
        Graphics g = dummyImage.getGraphics();
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 50, 50);

        PLAYER = new Player(375, 500, 50, 50, 5);
        ENEMIES = new ArrayList<>();
        BULLETS = new ArrayList<>();

        SPAWNER = new EnemySpawner(ENEMIES, PLAYER);

        GAME_LOOP = new GameLoop(this);
        GAME_LOOP.start();

        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new InputHandler(PLAYER, BULLETS));

//        JButton backButton = new JButton("Back to Menu");
//        // Temporary button settings:
//        backButton.setBounds(10, 10, 150, 30);
//        backButton.addActionListener(e -> frame.showCard("menu"));
//        add(backButton);

        JButton pauseButton = new JButton("Pause");
        // Temporary button settings:
        pauseButton.setBounds(10, 10, 150, 30);
        pauseButton.addActionListener(e -> frame.showCard("pause"));
        add(pauseButton);


        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                int playerCenterX = PLAYER.getX() + PLAYER.getWidth() / 2;
                int playerCenterY = PLAYER.getY() + PLAYER.getHeight() / 2;

                double dirX = mouseX - playerCenterX;
                double dirY = mouseY - playerCenterY;

                BULLETS.add(new Bullet(playerCenterX, playerCenterY, 10, 10, 10, dirX, dirY));
            }
        });

    }

    public void setPaused(boolean paused) {
        GAME_LOOP.setPaused(paused);
    }

    public void updateGame() {
        PLAYER.update();
        for (Enemy enemy : ENEMIES) {
            enemy.update();
        }
        SPAWNER.update();

        // bullets:
        for (int i = 0; i < BULLETS.size(); i++) {
            Bullet bullet = BULLETS.get(i);
            bullet.update();

            // Remove bullets that are off-screen
            if (bullet.getX() < 0 || bullet.getX() > getWidth() || bullet.getY() < 0 || bullet.getY() > getHeight()) {
                BULLETS.remove(i--);
            }
        }

        for (int i = 0; i < BULLETS.size(); i++) {
            Bullet bullet = BULLETS.get(i);
            for (int j = 0; j < ENEMIES.size(); j++) {
                Enemy enemy = ENEMIES.get(j);
                if (bullet.collidesWith(enemy)) {
                    ENEMIES.remove(j--);  // Remove enemy
                    BULLETS.remove(i--);  // Remove bullet
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
