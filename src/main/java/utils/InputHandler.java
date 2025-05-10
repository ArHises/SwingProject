//package utils;
//
//import entities.Bullet;
//import entities.Player;
//
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.util.ArrayList;
//
//public class InputHandler implements MouseListener {
//
//    private final ArrayList<Bullet> BULLETS;
//
//    public InputHandler(ArrayList<Bullet> bullets) {
//        this.BULLETS = bullets;
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
////        int mouseX = e.getX();
////        int mouseY = e.getY();
////        int playerCenterX = PLAYER.getX() + PLAYER.getWidth() / 2;
////        int playerCenterY = PLAYER.getY() + PLAYER.getHeight() / 2;
//
////        double dirX = mouseX - playerCenterX;
////        double dirY = mouseY - playerCenterY;
//
////        BULLETS.add(new Bullet(
////                playerCenterX,
////                playerCenterY,
////                10,
////                10,
////                10,
////                dirX,
////                dirY
////        ));
//    }
//
//    // Other MouseListener methods you must override, even if empty:
//    @Override public void mouseClicked(MouseEvent e) {}
//    @Override public void mouseReleased(MouseEvent e) {}
//    @Override public void mouseEntered(MouseEvent e) {}
//    @Override public void mouseExited(MouseEvent e) {}
//}
