package entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    private boolean left, right, up, down;

    public Player(int x, int y, int width, int height, int speed, int health) {
        super(x, y, width, height,speed , health);
        setSpeed(speed);
        setSprite(createDummySprite());
    }

    @Override
    public void update() {
        if (left) setX(getX() - getSpeed());
        if (right) setX(getX() + getSpeed());
        if (up) setY(getY() - getSpeed());
        if (down) setY(getY() + getSpeed());
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

    public void setUp(boolean up) { this.up = up; }
    public void setDown(boolean down) { this.down = down; }
    public void setLeft(boolean left) { this.left = left; }
    public void setRight(boolean right) { this.right = right; }
}
