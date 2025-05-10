package entities;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Enemy extends Entity{

    private Player player;
    private int damage;

    private long lastHitTime = 0;
    private static final long HIT_COOLDOWN = 500; // ms

    public Enemy(int x, int y, int height, int width, int speed, Player player , int health){
        super(x , y , height , width , speed , health);
        this.player = player;
        this.damage = 10;
        ImageIcon spriteEnemy = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Enemies/basic_enemy.png")));
        setSprite(spriteEnemy.getImage());
    }

    public void followPlayer(){
        if (player.getX() > this.getX()){
            setDx(getSpeed());
        } else if (player.getX() < this.getX()) {
            setDx(-getSpeed());
        }else {
            setDx(0);
        }

        if (player.getY() > this.getY()){
            setDy(getSpeed());
        } else if (player.getY() < this.getY()) {
            setDy(-getSpeed());
        }else {
            setDy(0);
        }
    }

    public void update(){
        followPlayer();
        move();
        makeDamage();
    }

    public void draw(Graphics g){
        if (getSprite() != null){
            g.drawImage(getSprite() , getX() , getY() , getWidth() , getHeight() , null);
        }else {
            g.setColor(Color.RED);
            g.fillRect(getX() , getY() , getWidth() , getHeight());
        }
    }


    public boolean collidesWith(Entity other) {
        if (other == this.player){
            return super.collidesWith(other);
        }
        return false;
    }


    public void makeDamage(){
        if (collidesWith(player)) {
            long now = System.currentTimeMillis();
            if (now - lastHitTime >= HIT_COOLDOWN) {
                player.setHealth(player.getHealth() - this.damage);
                lastHitTime = now;
                System.out.println("Health: " + player.getHealth());
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}