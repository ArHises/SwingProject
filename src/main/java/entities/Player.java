package entities;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import main.GamePanel;
import utils.EnemySpawner;

import javax.swing.*;

public class Player extends Entity {

    private final int PLAYER_HEIGHT = 100;
    private final int PLAYER_WIDTH = 100;
    private final int PLAYER_HEALTH = 100;
    private final int PLAYER_SPEED = 5;

    private ArrayList<Enemy> enemies;
    private Enemy boss;
    private String[] levelUp = {"speed", "strength", "health"};
    private Random rand;
    private static final long HIT_COOLDOWN = 500; //bullet class is
    // in comment quarantine - IDK how else to implement damage :/
    private static final int BOSS_ITERATION = 5;

    private ArrayList<ImageIcon> spritePlayer;

    private boolean up,down,left,right = false;

    public Player(int x, int y) {
        super(x, y);

        setSpeed(PLAYER_SPEED);
        setHealth(PLAYER_HEALTH);
        setWidth(PLAYER_WIDTH);
        setHeight(PLAYER_HEIGHT);

        spritePlayer = new ArrayList<>();
        spritePlayer.add(new ImageIcon(Objects.requireNonNull(
                getClass().getResource("/Player/player_right.png"))));
        spritePlayer.add(new ImageIcon(Objects.requireNonNull(
                getClass().getResource("/Player/player_left.png"))));
        setSprite(spritePlayer.getFirst().getImage());
    }

    private void getEnemies(){
        enemies = new EnemySpawner(this).getEnemies();
    }

    public void move(int keyCode){
        switch(keyCode){
            case KeyEvent.VK_W:
                if(this.getY() > 0){
                    up = true;
                }
                break;
            case KeyEvent.VK_A:
                setSprite(spritePlayer.get(1).getImage());
                if(this.getX() > 0){
                    left = true;
                }
                break;
            case KeyEvent.VK_S:
                if(this.getHeight() + this.getY() > GamePanel.HEIGHT){
                    down = true;
                }
                break;
            case KeyEvent.VK_D:
                setSprite(spritePlayer.getFirst().getImage());
                if(this.getHeight() + this.getX() > GamePanel.WIDTH){
                    right = true;
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

    public void stop(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_A -> left = false;
            case KeyEvent.VK_D -> right = false;
            case KeyEvent.VK_W -> up = false;
            case KeyEvent.VK_S -> down = false;
        }
    }

    private void toMove(){
        if (left) setX(getX() - getSpeed());
        if (right) setX(getX() + getSpeed());
        if (up) setY(getY() - getSpeed());
        if (down) setY(getY() + getSpeed());
    }

    @Override
    public void update() {
        getEnemies();
        toMove();

        if (new EnemySpawner(this).getWaveNumber() % BOSS_ITERATION == 0) {
            getBoss();
            levelUp();
        }
        if(this.getHealth() == 0){
            setSpeed(0);
        }
    }

    public void stopMoving(){
        left = right = down = up = false;
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
