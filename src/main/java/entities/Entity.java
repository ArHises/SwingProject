package entities;
import java.awt.*;

public abstract class Entity {
    protected int x, y, width, height;
    protected int dx, dy; // תנועה
    protected Image sprite;

    public Entity(int x, int y, int width, int height, Image sprite) {
        this.x = x; this.y = y;
        this.width = width; this.height = height;
        this.sprite = sprite;
    }

    public abstract void update();
    public abstract void draw(Graphics g);

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public boolean collidesWith(Entity other) {
        return this.getBounds().intersects(other.getBounds());
    }
}

