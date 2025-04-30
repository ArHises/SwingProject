package main;

import entities.Player;
import entities.Enemy;
import menu.MainFrame;
import utils.EnemySpawner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private final Player player;
    private final ArrayList<Enemy> enemies;
    private final EnemySpawner spawner;
    private final GameLoop gameLoop;

    public GamePanel(MainFrame frame) {
        requestFocusInWindow();

        setFocusable(true);
        setLayout(null);
        setBackground(Color.BLACK);

        Image dummyImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
        Graphics g = dummyImage.getGraphics();
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 50, 50);

        player = new Player(375, 500, 50, 50, 5);
        enemies = new ArrayList<>();

        spawner = new EnemySpawner(enemies, player);

        gameLoop = new GameLoop(this);
        gameLoop.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                player.keyReleased(e);
            }
        });

        JButton backButton = new JButton("Back to Menu");
        backButton.setBounds(10, 10, 150, 30);
        backButton.addActionListener(e -> frame.showCard("menu"));
        add(backButton);
    }

    public void setPaused(boolean paused) {
        gameLoop.setPaused(paused);
    }

    public void updateGame() {
        player.update();
        for (Enemy enemy : enemies) {
            enemy.update();
        }
        spawner.update();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        requestFocusInWindow(); // ensures key input works
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g);
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }
    }
}
