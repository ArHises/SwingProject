package utils;

import entities.Enemy;
import entities.Player;

import java.util.List;

public class EnemySpawner {
    private int wave = 1;
    private long lastSpawnTime;
    private final long waveInterval = 5000; // 5 seconds between waves
    private final List<Enemy> enemies;
    private final Player player;

    public EnemySpawner(List<Enemy> enemies, Player player) {
        this.enemies = enemies;
        this.player = player;
        this.lastSpawnTime = System.currentTimeMillis();
    }

    public void update() {
        long now = System.currentTimeMillis();
        if (now - lastSpawnTime > waveInterval) {
            spawnWave(wave);
            wave++;
            lastSpawnTime = now;
        }
    }

    private void spawnWave(int waveNumber) {
        int numEnemies = 2 + waveNumber;
        for (int i = 0; i < numEnemies; i++) {
            int x = (int) (Math.random() * 700);
            enemies.add(new Enemy(x, 0, 50, 50, 2, player));
        }
    }
}
