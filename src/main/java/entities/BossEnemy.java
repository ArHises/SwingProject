package entities;

import javax.swing.*;
import java.util.Objects;

public class BossEnemy extends Enemy{

    private final int BOSS_DAMAGE = 15;

    public BossEnemy(int x , int y , int height , int width , int speed , Player player , int health){
        super(x , y , height , width , speed , player , health);
        ImageIcon spriteEnemy = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Enemies/boss_enemy.png")));
        setSprite(spriteEnemy.getImage());
        setDamage(BOSS_DAMAGE);
    }


}
