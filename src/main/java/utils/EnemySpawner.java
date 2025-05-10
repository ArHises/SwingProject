package utils;

import entities.BossEnemy;
import entities.FastEnemy;
import entities.Player;
import entities.Enemy;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class EnemySpawner {

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int BASIC_ENEMY_WIDTH = 80;
    private static final int BASIC_ENEMY_HEIGHT = 80;
    private static final int BOSS_WIDTH = 120;
    private static final int BOSS_HEIGHT = 120;
    private static final long WAVE_COOLDOWN = 3000; // זמן המתנה בין גלים
    private static final int BOSS_WAVE = 5; // כל חמש גלים יגיע בוס


    private ArrayList<Enemy> enemies;
    private Player player;
    private Random random;
    private int waveNumber;
    private long lastWaveTime;


    public EnemySpawner(Player player){
        this.player = player;
        this.enemies = new ArrayList<>();
        this.waveNumber = 1;
        this.random = new Random();
        this.lastWaveTime = System.currentTimeMillis();
        spawnWave(); // initial wave
    }

    public void update(){
        for (Enemy enemy : enemies){
            enemy.update();
        }

        removeDeadEnemies();

        if (enemies.isEmpty()){ // making new waves if the enemies are dead
            long now = System.currentTimeMillis();
            if (now - lastWaveTime >= WAVE_COOLDOWN){
                waveNumber++;
                spawnWave();
                lastWaveTime = now; // נעדכן
            }
        }
    }

    public void removeDeadEnemies(){
        for (int i = 0; i <enemies.size() ; i++) {
            Enemy enemy = enemies.get(i);
            if (enemy.getHealth() <= 0){
                enemies.remove(i);
                i--;
            }
        }
    }

    public void spawnWave(){
        int numberOfEnemies = waveNumber + random.nextInt(3);
        for (int i = 0; i <numberOfEnemies ; i++) {
            int x = random.nextInt(SCREEN_WIDTH); // מיקום רנדומלי על המסך
            int y = 10;
            // מהירות ובריאות עולים לפי גל
            int speed = 2 + waveNumber / 2;
            int health = 20 + waveNumber * 5;
            Enemy enemy;
            if (random.nextBoolean()){
                enemy = new FastEnemy(x , y , BASIC_ENEMY_HEIGHT , BASIC_ENEMY_WIDTH , speed , player , health / 2); // חצי מהמקרים אויב מהיר
            }else {
                enemy = new Enemy(x , y , BASIC_ENEMY_HEIGHT , BASIC_ENEMY_WIDTH , speed / 2 , player , health);
            }
            enemies.add(enemy);
        }

        if (waveNumber % BOSS_WAVE == 0){ // אם הגל הוא כל 5 גלים נוסיף בוס
            spawnBoss();
        }
    }

    public void spawnBoss(){
        int x = random.nextInt(SCREEN_WIDTH);
        int y = 10;
        int speed = 2 + waveNumber / 2;
        int health = 300 + waveNumber * 20;

        Enemy boss = new BossEnemy(x , y , BOSS_HEIGHT , BOSS_WIDTH  , speed , player , health);
        enemies.add(boss); // הוספנו בוס לרשימת אויבים
    }

    public void draw(Graphics g){
        for (Enemy enemy : enemies){
            enemy.draw(g);
        }
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public int getWaveNumber() {
        return waveNumber;
    }
}
