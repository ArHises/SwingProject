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
        var iterator = projectiles.iterator();
        while (iterator.hasNext()) {
            Projectile p = iterator.next();
            p.update();

            if (p.isOutOfBounds(panelWidth, panelHeight)) {
                iterator.remove();
                continue;
            }

            for (Enemy enemy : enemies) {
                if (p.collidesWith(enemy)) {
                    enemy.setHealth(enemy.getHealth() - p.getDamage());
                    iterator.remove();
                    break;
                }
            }
        }
    }

    public void draw(Graphics g) {
        // Iterate over a copy to avoid ConcurrentModificationException
        for (Projectile p : new ArrayList<>(projectiles)) {
            p.draw(g);
        }
    }
}
