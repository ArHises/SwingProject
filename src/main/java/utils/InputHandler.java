package utils;

import entities.Bullet;
import entities.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class InputHandler extends KeyAdapter {
    private final Player PLAYER;
    private final ArrayList<Bullet> BULLETS;

    public InputHandler(Player player, ArrayList<Bullet> bullets) {
        this.PLAYER = player;
        this.BULLETS = bullets;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        PLAYER.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        PLAYER.keyReleased(e);
    }
}
