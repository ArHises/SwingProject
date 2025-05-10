package entities;

import java.awt.*;

public abstract class Entity {
    private int x;
    private int y;
    private int height;
    private int width;
    private int dx;
    private int dy;
    private int speed;
    private Image sprite;
    private int health;


    public Entity (int x , int y , int height , int width , int speed , int health){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.speed = speed;
        this.dx = 0;
        this.dy = 0;
        this.health = health;
    }

    public void move(){
        x += dx;
        y += dy;
    }

    public Rectangle getBounds(){
        return new Rectangle(x , y , width , height);
    }

    public boolean collidesWith(Entity other) {
        return this.getBounds().intersects(other.getBounds());
    }

    public abstract void update();

    public abstract void draw(Graphics g);

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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Image getSprite() {
        return sprite;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
