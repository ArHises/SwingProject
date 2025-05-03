package entities;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    private boolean left, right, up, down;

    public Player(int x, int y, int width, int height, int speed) {
        super(x, y, width, height);
        setSprite(createDummySprite());
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
        if (down) setY(getY() + getSpeed()); // Fixed: Down should increase Y
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
