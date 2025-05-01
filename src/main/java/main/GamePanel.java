package main;

import entities.Player;
import entities.Enemy;
import menu.MainFrame;
import utils.EnemySpawner;
import utils.InputHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    private final Player PLAYER;
    private final ArrayList<Enemy> ENEMIES;
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

        SPAWNER = new EnemySpawner(ENEMIES, PLAYER);

        GAME_LOOP = new GameLoop(this);
        GAME_LOOP.start();

        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new InputHandler(PLAYER));

        JButton backButton = new JButton("Back to Menu");
        // Temporary button settings:
        backButton.setBounds(10, 10, 150, 30);
        backButton.addActionListener(e -> frame.showCard("menu"));
        add(backButton);
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
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        PLAYER.draw(g);
        for (Enemy enemy : ENEMIES) {
            enemy.draw(g);
        }
    }
}
