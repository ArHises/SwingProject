package entities;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import main.Game;

import javax.swing.*;

public class Player extends Entity {

    private final int PLAYER_HEIGHT = 100;
    private final int PLAYER_WIDTH = 100;
    private final int PLAYER_HEALTH = 100;
    private final int PLAYER_SPEED = 5;
    private static final int BOSS_ITERATION = 5;

    private String[] levelUp = {"speed", "strength", "health"};
    private Random rand;
    private int fullHealth;

    private final ArrayList<ImageIcon> spritePlayer;

    private boolean up,down,left,right = false;

    private int damage = 20;

    public Player(int x, int y) {
        super(x, y);

        setSpeed(PLAYER_SPEED);
        setHealth(PLAYER_HEALTH);
        setWidth(PLAYER_WIDTH);
        setHeight(PLAYER_HEIGHT);

        this.fullHealth = PLAYER_HEALTH;

        spritePlayer = new ArrayList<>();
        spritePlayer.add(new ImageIcon(Objects.requireNonNull(
                getClass().getResource("/Player/player_right.png"))));
        spritePlayer.add(new ImageIcon(Objects.requireNonNull(
                getClass().getResource("/Player/player_left.png"))));
        setSprite(spritePlayer.getFirst().getImage());
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

    public void levelUp(){
            this.rand = new Random();
            System.out.println("Leveled up...");
            String boost = this.levelUp[this.rand.nextInt(this.levelUp.length)];
            switch(boost){
                case "speed": this.setSpeed(this.getSpeed() + 2);
                    System.out.println("Speed!"); break;
                case "health": this.setHealth(this.getHealth() + 10);
                    System.out.println("Health!");
                    fullHealth += 10;
                    break;
                case "strength": this.damage += 10;
                    System.out.println("Strength!"); break;
            }
        System.out.println("New level, bonus: " + boost);
    }

    @Override
    public void update() {
        toMove();
    }

    public void stopMoving(){
               left = right = down = up = false;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getSprite(), getX(), getY(), getWidth(), getHeight(), null);
    }

    public int getDamage() {
        return damage;
    }

    public int getFullHealth() {
        return fullHealth;
    }
}
