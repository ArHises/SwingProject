package utils;

import entities.Player;
import main.Game;

import java.awt.*;

public class HealthBar {

    private final int x;
    private final int y;
    private final int height = 25;
    private int fullWidth;

    private final Player player;
    private int fullHealth;

    public HealthBar(Player player) {
        this.player = player;
        this.fullHealth = player.getHealth();
        this.fullWidth = player.getFullHealth() * 2;
        this.x = 100;
        this.y = Game.WINDOW_HEIGHT - height - 50;
    }

    public void draw(Graphics g) {
        int currentHealth = player.getHealth();
        this.fullWidth = player.getFullHealth() * 2;
        int currentWidth = (int) (((double) currentHealth / fullHealth) * fullWidth);

        g.setColor(Color.RED);
        g.fillRect(x, y, currentWidth, height);

        g.setColor(Color.BLACK);
        g.drawRect(x, y, fullWidth, height);
    }
}
