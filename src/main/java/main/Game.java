package main;

import menu.MainFrame;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Game {
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 800;

    public static void main(String[] args) {
        JFrame window = new MainFrame();
        window.setVisible(true);
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
//         window.setLayout(null);
        window.setResizable(false);
    }
}
