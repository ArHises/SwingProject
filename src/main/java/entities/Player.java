package entities;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import main.Game;
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


        // testing:
        new Thread(() -> {
            try {
                while (true){
                    System.out.println("PlayerX: " + (this.getX() + this.getWidth()) + ", PlayerY: " + (this.getY() + this.getHeight()));
                    System.out.println("Y: " + (this.getY() + this.getHeight() < Game.WINDOW_HEIGHT));
                    System.out.println("X: " + (this.getX() + this.getWidth() < Game.WINDOW_WIDTH));
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    private void getEnemies(){
        enemies = new EnemySpawner(this).getEnemies();
    }

    public void move(int keyCode){
        switch(keyCode){
            case KeyEvent.VK_W:
                if(checkTopBorder()){
                    up = true;
                } else {
                    up = false;
                }
                break;
            case KeyEvent.VK_A:
                setSprite(spritePlayer.get(1).getImage());
                if(checkLeftBorder()){
                    left = true;
                } else {
                    left = false;
                }
                break;
            case KeyEvent.VK_S:
                if(checkBottomBorder()){
                    down = true;
                } else {
                    down = false;
                }
                break;
            case KeyEvent.VK_D:
                setSprite(spritePlayer.getFirst().getImage());
                if(checkRightBorder()){
                    right = true;
                } else {
                    right = false;
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
        if (left && checkLeftBorder())
            setX(getX() - getSpeed());
        if (right && checkRightBorder())
            setX(getX() + getSpeed());
        if (up && checkTopBorder())
            setY(getY() - getSpeed());
        if (down && checkBottomBorder())
            setY(getY() + getSpeed());
    }

    private boolean checkRightBorder(){
        return this.getX() + this.getWidth() < Game.WINDOW_WIDTH;
    }

    private boolean checkLeftBorder(){
        return this.getX() > 0;
    }

    private boolean checkTopBorder(){
        return this.getY() > 0;
    }

    private boolean checkBottomBorder(){
        return this.getY() + this.getHeight() < Game.WINDOW_HEIGHT;
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
