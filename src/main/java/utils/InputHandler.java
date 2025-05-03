package utils;

import entities.Bullet;
import entities.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class InputHandler extends KeyAdapter implements MouseListener {

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

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        int playerCenterX = PLAYER.getX() + PLAYER.getWidth() / 2;
        int playerCenterY = PLAYER.getY() + PLAYER.getHeight() / 2;

        double dirX = mouseX - playerCenterX;
        double dirY = mouseY - playerCenterY;

        BULLETS.add(new Bullet(
                playerCenterX,
                playerCenterY,
                10,
                10,
                10,
                dirX,
                dirY
        ));
    }

    // Other MouseListener methods you must override, even if empty:
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
