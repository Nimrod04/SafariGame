package model;

import view.Playing;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game implements Runnable {

    private DifficultyLevel difficulty;
    private SafariPark park;
    private Finance finance;
    private GameState state;

    private JPanel gamePanel;
    private Playing playing;

    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    public final static int TILES_DEFAULT_SIZE = 16;
    public final static float SCALE = 4.0f;
    public final static int TILES_IN_WIDTH = 20;
    public final static int TILES_IN_HEIGHT = 12;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    public Game(DifficultyLevel level, String safariName) {
        this.difficulty = level;
        this.park = new SafariPark();
        this.finance = new Finance();
        this.state = GameState.READY;

        playing = new Playing();
        playing.setSafariName(safariName);
        playing.setVisible(true);
        gamePanel = playing.getJPanel();
        gamePanel.requestFocus();

        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();

        SwingUtilities.invokeLater(() -> gamePanel.requestFocusInWindow());

        gamePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A ->
                        playing.getGamePanel().setCameraX(Math.max(0, playing.getGamePanel().getCameraX() - 1));
                    case KeyEvent.VK_D ->
                        playing.getGamePanel().setCameraX(Math.max(playing.getGamePanel().getGameMap().getWidth()-20, playing.getGamePanel().getCameraX() + 1));
                    case KeyEvent.VK_W ->
                        playing.getGamePanel().setCameraY(Math.max(0, playing.getGamePanel().getCameraY() - 1));
                    case KeyEvent.VK_S ->
                        playing.getGamePanel().setCameraX(Math.max(playing.getGamePanel().getGameMap().getHeight()-15, playing.getGamePanel().getCameraY() + 1));
                }
                gamePanel.repaint();
            }
        });

        startGameLoop();
    }

    public void startGame() {
        state = GameState.RUNNING;
        //safariPark.generateMap();
        //view.updateView();
    }

    public void endGame() {
        state = GameState.ENDED;
        //view.displayGameOver();
    }

    public void checkWinCondition() {
        // Ellenőrzi a nyerési feltételeket
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * frissíti a játékmenetet
     */
    public void update() {
        //playing.update();

    }

    /**
     * kirajzolja a játékot
     *
     * @param g
     */
    public void render(Graphics g) {
        //playing.draw(g);
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            /*if (System.nanoTime()-lastFrame >= timePerFrame) {
                gamePanel.repaint();
                lastFrame = now;
                frames++;
            }*/
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                //System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }

        }

    }

    /**
     * ha másik ablakba kattintanánk, a karakter megáll
     */
    public void windowFocusLost() {
        //playing.getPlayer().resetDirBooleans();
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }
}
