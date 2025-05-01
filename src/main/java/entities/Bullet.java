package entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends Entity {
    private final double directionX, directionY;

    public Bullet(int x, int y, int width, int height, int speed, double dirX, double dirY) {
        super(x, y, width, height, speed);
        // Normalize direction
        double length = Math.sqrt(dirX * dirX + dirY * dirY);
        this.directionX = (dirX / length) * speed;
        this.directionY = (dirY / length) * speed;

        setSprite(createBulletSprite());
    }

    @Override
    public void update() {
        setX((int) (getX() + directionX));
        setY((int) (getY() + directionY));
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getSprite(), getX(), getY(), getWidth(), getHeight(), null);
    }

    private Image createBulletSprite() {
        BufferedImage img = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        g.setColor(Color.YELLOW);
        g.fillOval(0, 0, 10, 10);
        return img;
    }
}
