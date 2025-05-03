package entities;
import java.awt.*;

public abstract class Entity {
    private int x, y, width, height;
    private int dx, dy; // תנועה
    private int speed;

    private Image sprite;

    public Entity(int x, int y, int width, int height) {
        this.x = x; this.y = y;
        this.width = width; this.height = height;
    }

    public abstract void update();
    public abstract void draw(Graphics g);

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public boolean collidesWith(Entity other) {
        return this.getBounds().intersects(other.getBounds());
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public Image getSprite() {
        return sprite;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }
}

