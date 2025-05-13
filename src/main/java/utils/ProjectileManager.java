package utils;

import entities.Projectile;
import entities.Enemy;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectileManager {

    private final List<Projectile> projectiles = new ArrayList<>();

    public void addProjectile(Projectile p) {
        projectiles.add(p);
    }

    public void update(List<Enemy> enemies, int panelWidth, int panelHeight) {
        List<Projectile> toRemove = new ArrayList<>();

        for (Projectile p : projectiles) {
            p.update();

            // Mark for removal if out of bounds
            if (p.isOutOfBounds(panelWidth, panelHeight)) {
                toRemove.add(p);
                continue;
            }

            // Check for collision
            for (Enemy enemy : enemies) {
                if (p.collidesWith(enemy)) {
                    enemy.setHealth(enemy.getHealth() - p.getDamage());
                    toRemove.add(p);
                    break;
                }
            }
        }

        projectiles.removeAll(toRemove);
    }

    public void draw(Graphics g) {
        for (Projectile p : projectiles) {
            p.draw(g);
        }
    }

    public void clear() {
        projectiles.clear();
    }
}
