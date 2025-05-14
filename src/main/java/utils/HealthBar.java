package utils;

import entities.Player;
import main.Game;

import java.awt.*;

public class HealthBar {

    private final int x;
    private final int y;
    private final int height = 25;
    private int fullWidth = 200;

    private final Player player;
    private int fullHealth;

    public HealthBar(Player player) {
        this.player = player;
        this.fullHealth = player.getHealth();
        this.x = (Game.WINDOW_WIDTH / 2);
        this.y = Game.WINDOW_HEIGHT - height - 50;
    }

    public void draw(Graphics g) {
        int currentHealth = player.getHealth();
        this.fullHealth = player.getFullHealth();
        int currentWidth = (int) (((double) currentHealth / fullHealth) * fullWidth);

        g.setColor(Color.RED);
        g.fillRect(x, y, currentWidth, height);

        g.setColor(Color.BLACK);
        g.drawRect(x, y, fullHealth * 2, height);
    }
}
