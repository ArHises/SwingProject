package entities;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Enemy extends Entity {
    private final Player player;
    private int health = 3;

    public Enemy(int x, int y, int width, int height, int speed, Player player) {
        super(x, y, width, height, speed);
        this.player = player;

        URL resource = getClass().getResource("/assets/newEnemy.png");
        if (resource == null) {
            throw new RuntimeException("Could not find /assets/Enemy.png");
        }

        ImageIcon enemySprite = new ImageIcon(resource);
        setSprite(enemySprite.getImage());
    }

    private void followPlayer() {
        if (player.getX() > getX()) {
            setDx(getSpeed());
        } else if (player.getX() < getX()) {
            setDx(-getSpeed());
        } else {
            setDx(0);
        }

        if (player.getY() > getY()) {
            setDy(getSpeed());
        } else if (player.getY() < getY()) {
            setDy(-getSpeed());
        } else {
            setDy(0);
        }
    }

    @Override
    public void update() {
        followPlayer();
        move();
    }

    @Override
    public void draw(Graphics g) {
        if (getSprite() != null) {
            g.drawImage(getSprite(), getX(), getY(), getWidth(), getHeight(), null);
        } else {
            g.setColor(Color.RED);
            g.fillRect(getX(), getY(), getWidth(), getHeight());
        }
    }

    public void takeDamage(int amount) {
        health -= amount;
    }

    public boolean isDead() {
        return health <= 0;
    }
}
