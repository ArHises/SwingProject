package entities;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Projectile extends Entity {

    private int damage;
    private double dx, dy;
    private static final int SIZE = 50;
    private static final int SPEED = 10;

    private long lastChangeTime = 0;
    private final long CHANGE_COOLDOWN = 250; // ms
    private int currentSpriteIndex = 0;

    private final ArrayList<ImageIcon> spriteProjectiles;

    public Projectile(int startX, int startY, int targetX, int targetY, int damage) {
        super(startX, startY, SIZE, SIZE, SPEED, 1); // health not relevant
        this.damage = damage;

        // Calculate normalized direction vector
        double angle = Math.atan2(targetY - startY, targetX - startX);
        dx = Math.cos(angle) * SPEED;
        dy = Math.sin(angle) * SPEED;

        spriteProjectiles = new ArrayList<>();
        spriteProjectiles.add(new ImageIcon(Objects.requireNonNull(
                getClass().getResource("/Projectiles/fire_ball_01.png"))));
        spriteProjectiles.add(new ImageIcon(Objects.requireNonNull(
                getClass().getResource("/Projectiles/fire_ball_02.png"))));
        setSprite(spriteProjectiles.getFirst().getImage());
    }

    public void update() {
        animation();
        setX((int)(getX() + dx));
        setY((int)(getY() + dy));
    }

    public void animation(){
            long now = System.currentTimeMillis();
            if (now - lastChangeTime >= CHANGE_COOLDOWN) {
                currentSpriteIndex = (currentSpriteIndex + 1) % spriteProjectiles.size();
                setSprite(spriteProjectiles.get(currentSpriteIndex).getImage());
                lastChangeTime = now;
            }
    }

    public void draw(Graphics g) {
        g.drawImage(getSprite(), getX(), getY(), getWidth(), getHeight(), null);
    }

    public boolean isOutOfBounds(int width, int height) {
        return getX() < 0 || getY() < 0 || getX() > width || getY() > height;
    }

    public int getDamage() {
        return damage;
    }
}

