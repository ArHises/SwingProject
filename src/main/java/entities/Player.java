package entities;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

public class Player extends Entity {

    private boolean left, right, up, down;
    private int level;
    private int enemyQuota, enemiesDefeated;
    private Random rand;
    private double health;
    private LinkedHashMap<String, Boolean> powerUp;
    public Player(int x, int y, int width, int height, int speed) {
        super(x, y, width, height);
        setSprite(createDummySprite());
        this.health = 100d;
        //CONCEPTS - ability, isUnlocked
        this.powerUp = new LinkedHashMap<>(){{
            put("teledash", false); //Makes the player dash-teleport 20 units
            put("slowmo", false); //Makes the enemies much slower
            put("", false); //I don't know what else to add without worrying over it
            //possibly ruining the game. Waiting for approval.
        }};
    }

    public void applyPowerUp(String powerUp) {
        boolean isAvailable = this.powerUp.get(powerUp);
        switch (powerUp + "&&" + isAvailable){
            case "teledash && true":
                if (left) this.setX(this.getX() - 200);
                if (right) this.setX(getX() + 200);
                if (up) this.setY(getY() - 200);
                if (down) this.setY(getY() + 200);
                break;
            case "slowmo && true":
                //pass
            //This will continue on...
        }
    }

    public void unlockPowerUp(){
        List<String> lockedPowerUps = new ArrayList<>();
        for (String powerUp : this.powerUp.keySet()){
            if(!this.powerUp.get(powerUp)){
                lockedPowerUps.add(powerUp);
            }
        }
        String randomPowerUp = lockedPowerUps.get(rand.nextInt(lockedPowerUps.size()));
        this.powerUp.replace(randomPowerUp, true);
        System.out.println("You've unlocked: " + randomPowerUp + "!\n" +
                "Go and see what it can do!");
    }

    private void levelUp(){
        this.level++;
        System.out.println("You've leveled up to LVL " + this.level + ".\n" +
                "You are now slightly faster and healthier as a result.");
        this.setSpeed(this.getSpeed() + 1);
        this.health += 10.0;
    }

    public void updateToQuota(){
        this.enemiesDefeated++;
        if(this.enemiesDefeated == this.enemyQuota){
            this.enemiesDefeated = 0;
            this.levelUp();
            this.enemyQuota = 14 + (this.level * 2);
        }
    }

    public void hurt(){
        /*
        under the assumption the enemies will remove hp at insane speed
        the moment they collide with the player
         */
        this.health -= 0.1;
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> left = true;
            case KeyEvent.VK_D -> right = true;
            case KeyEvent.VK_W -> up = true;
            case KeyEvent.VK_S -> down = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> left = false;
            case KeyEvent.VK_D -> right = false;
            case KeyEvent.VK_W -> up = false;
            case KeyEvent.VK_S -> down = false;
        }
    }

    @Override
    public void update() {
        if (left) setX(getX() - getSpeed());
        if (right) setX(getX() + getSpeed());
        if (up) setY(getY() - getSpeed());
        if (down) setY(getY() + getSpeed());// Fixed: Down should increase Y
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
