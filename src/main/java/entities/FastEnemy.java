package entities;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class FastEnemy extends Enemy{

    private final int FAST_DAMAGE = 5;

    public FastEnemy(int x , int y , int height , int width , int speed , Player player , int health){
        super(x , y , height , width , speed , player , health);
        ImageIcon spriteEnemy = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Enemies/fast_enemy.png")));
        setSprite(spriteEnemy.getImage());
        setDamage(FAST_DAMAGE);
    }

//    public void draw(Graphics g){
//        g.setColor(Color.ORANGE);
//        g.fillRect(getX() , getY() , getWidth() , getHeight());
//    }

}
