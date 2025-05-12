package entities;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import main.GamePanel;
import utils.EnemySpawner;

public class Player extends Entity {

    private ArrayList<Enemy> enemies;
    private Enemy boss;
    private String[] levelUp = {"speed", "strength", "health"};
    private Random rand;
    private static final long HIT_COOLDOWN = 500; //bullet class is
    // in comment quarantine - IDK how else to implement damage :/
    private static final int BOSS_ITERATION = 5;

    public Player(int x, int y, int height, int width, int speed, int health) {
        super(x, y, height, width, speed , health);
        setSpeed(speed);
        setSprite(createDummySprite());
    }

    private void getEnemies(){
        enemies = new EnemySpawner(this).getEnemies();
    }

    public void move(int keyCode){
        switch(keyCode){
            case KeyEvent.VK_W:
                if(this.getY() > 0){
                    this.setY(this.getY() - this.getSpeed());
                }
                break;
            case KeyEvent.VK_A:
                if(this.getX() > 0){
                    this.setX(this.getX() - this.getSpeed());
                }
                break;
            case KeyEvent.VK_S:
                if(this.getHeight() + this.getY() > GamePanel.HEIGHT){
                    this.setY(this.getY() + this.getSpeed());
                }
                break;
            case KeyEvent.VK_D:
                if(this.getHeight() + this.getX() > GamePanel.WIDTH){
                    this.setX(this.getX() + this.getSpeed());
                }
        }
    }

    private void getBoss(){
        for(Enemy enemy : this.enemies){
            if(enemy instanceof BossEnemy){
                this.boss = enemy;
            }
        }
    }

    private void levelUp(){
        if(!this.enemies.contains(this.boss)){
            this.rand = new Random();
            System.out.println("Leveled up...");
            switch(this.levelUp[this.rand.nextInt(this.levelUp.length)]){
                case "speed": this.setSpeed(this.getSpeed() + 10);
                    System.out.println("Speed!"); break;
                case "health": this.setHealth(this.getHealth() + 10);
                    System.out.println("Health!"); break;
                case "strength":
                    System.out.println("Strength!"); break;
            }
        }
    }

    @Override
    public void update() {
        getEnemies();
        if (new EnemySpawner(this).getWaveNumber() % BOSS_ITERATION == 0) {
            getBoss();
            levelUp();
        }
        if(this.getHealth() == 0){
            setSpeed(0);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getSprite(), getX(), getY(), getWidth(), getHeight(), null);
    }

    private Image createDummySprite() {
        BufferedImage img = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 50, 50);
        return img;
    }
}
