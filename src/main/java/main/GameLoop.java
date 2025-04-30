package main;

public class GameLoop extends Thread {
    private boolean running = true;
    private volatile boolean paused = false;
    private final GamePanel gamePanel;

    public GameLoop(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    @Override
    public void run() {
        final int FPS = 60;
        final long FRAME_TIME = 1_000_000_000L / FPS;

        while (running) {
            long start = System.nanoTime();

            if (!paused) {
                gamePanel.updateGame();
                gamePanel.repaint();
            }

            long elapsed = System.nanoTime() - start;
            long sleep = Math.max(0, FRAME_TIME - elapsed);

            try {
                Thread.sleep(sleep / 1_000_000);
            } catch (InterruptedException ignored) {}
        }
    }

    public void stopLoop() {
        running = false;
    }
}
