package utils;

import entities.Player;
import main.Game;

import java.awt.*;

public class HealthBar {

    private final int x;
    private final int y;
    private final int height = 25;

    private final Player player;

    public HealthBar(Player player) {
        this.player = player;
        this.x = 100;
        this.y = Game.WINDOW_HEIGHT - height - 50;
    }

    public void draw(Graphics g) {
        int currentHealth = player.getHealth();
        int fullHealth = player.getFullHealth();
        int barWidth = player.getFullHealth() * 2;
        int currentWidth = (int) (((double) currentHealth / fullHealth) * barWidth);

        g.setColor(Color.DARK_GRAY);
        g.fillRoundRect(x, y, barWidth, height, 10, 10);

        g.setColor(Color.RED);
        g.fillRoundRect(x, y, currentWidth, height, 10, 10);

        g.setColor(Color.BLACK);
        g.drawRoundRect(x, y, barWidth, height, 10, 10);
    }
}
