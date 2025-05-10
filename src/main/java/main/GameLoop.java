package main;

public class GameLoop extends Thread {

    private final GamePanel GAME_PANEL;

    private boolean running = true;
    private volatile boolean paused = true;

    public GameLoop(GamePanel gamePanel) {
        this.GAME_PANEL = gamePanel;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    @Override
    public void run() {
        while (running) {
            if (!paused) {
                GAME_PANEL.updateGame();
                GAME_PANEL.repaint();
            }

            try {
                Thread.sleep(16); // Roughly 60 FPS (1000 ms / 60 ≈ 16.67)
            } catch (InterruptedException ignored) {}
        }
    }

}
