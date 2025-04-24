package model;

import view.Playing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Game implements Runnable {

    private DifficultyLevel difficulty;
    private SafariPark park;
    private Finance finance;
    private GameState state;

    private JPanel gamePanel;
    private Playing playing;

    private Thread gameThread;
    private final int FPS_SET = 60; // 60 FPS-re állítva
    private final int UPS_SET = 60;

    private GameSpeed gameSpeed; // Új GameSpeed példány

    private Queue<Tourist> visitorQueue;
    private long lastVisitorAddedTime; // Az utolsó látogató hozzáadásának ideje játékbeli időben

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

        this.gameSpeed = new GameSpeed(); // GameSpeed inicializálása
        this.gameSpeed.startTimer(); // Időzítő indítása

        playing = new Playing(this);
        playing.setSafariName(safariName);
        playing.setVisible(true);
        gamePanel = playing.getJPanel();
        gamePanel.requestFocus();

        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();

        SwingUtilities.invokeLater(() -> gamePanel.requestFocusInWindow());

        gamePanel = playing.getJPanel();
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();

        playing.getGamePanel().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    System.out.println("W lenyomva!");
                }
            }
        });

        gamePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("ZZ");
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A ->
                        playing.getGamePanel().setCameraX(Math.max(0, playing.getGamePanel().getCameraX() - 1));
                    case KeyEvent.VK_D ->
                        playing.getGamePanel().setCameraX(Math.max(playing.getGamePanel().getGameMap().getWidth() - 20,
                                playing.getGamePanel().getCameraX() + 1));
                    case KeyEvent.VK_W ->
                        playing.getGamePanel().setCameraY(Math.max(0, playing.getGamePanel().getCameraY() - 1));
                    case KeyEvent.VK_S ->
                        playing.getGamePanel().setCameraX(Math.max(playing.getGamePanel().getGameMap().getHeight() - 15,
                                playing.getGamePanel().getCameraY() + 1));
                }
                gamePanel.repaint();
            }
        });
        this.visitorQueue = new LinkedList<>(); // Várólista inicializálása
        this.lastVisitorAddedTime = 0; // Kezdőérték
        startGameLoop();
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();

    }

    public void update() {
        // gameSpeed.changeGameSpeed(playing.getTimeIntensity().getMulti());
        long currentGameTime = gameSpeed.getElapsedTimeInSeconds(); // Játékbeli idő másodpercben
        if (currentGameTime - lastVisitorAddedTime >= 5) { // 5 másodperc eltelt
            Tourist newVisitor = new Tourist();
            visitorQueue.add(newVisitor); // Új látogató hozzáadása a várólistához
            playing.changeVisitorCount(playing.gameMap.getJeeps().size(), visitorQueue.size());
            System.out.println("New visitor added! Total visitors in queue: " + visitorQueue.size());
            lastVisitorAddedTime = currentGameTime; // Idő frissítése
        }

        // Jeepek indítása, ha van legalább 4 látogató
        if (visitorQueue.size() >= 4) {
            Queue<Jeep> jeepQueue = playing.gameMap.getJeepQueue();
            if (!jeepQueue.isEmpty()) {
                Jeep nextJeep = jeepQueue.peek(); // Következő Jeep a sorban
                if (!nextJeep.isReadyToMove()) {
                    // Az első 4 látogató beültetése a Jeep-be
                    for (int i = 0; i < 4; i++) {
                        Tourist visitor = visitorQueue.poll(); // Látogató eltávolítása a várólistából
                        nextJeep.pickUpTourist(visitor); // Látogató hozzáadása a Jeep-hez
                    }
                    nextJeep.startMoving(); // Jeep elindítása
                    System.out.println("Jeep started moving with 4 visitors.");
                }
            }
        }

        // playing.gameMap.updateAnimals();
        // playing.gameMap.updateJeeps(); // Jeepek frissítése
        playing.updateTime(gameSpeed.getFormattedTime()); // Idő frissítése a Playing osztályban
        // gamePanel.repaint();

    }

    public Queue<Tourist> getVisitors() {
        return visitorQueue;
    }

    public GameSpeed getGameSpeed() {
        return this.gameSpeed;
    }

    public void startJeep(Jeep jeep) {
        if (jeep != null) {
            jeep.startMoving(); // Jeep elindítása
            System.out.println("Jeep is now moving: " + jeep.getPosition());
        }
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET; // Idő egy képkockára nanosec-ben
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
                update(); // Játékállapot frissítése
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint(); // Vászon újrarajzolása
                frames++;
                deltaF--;
            }

            // Ellenőrzés másodpercenként
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                lastCheck = System.currentTimeMillis();
                frames = 0;
                updates = 0;
            }

            // Alvás a pontos FPS érdekében
            /*
             * try {
             * Thread.sleep((long) (timePerFrame / 1000000)); // Alvás milliszekundumban
             * } catch (InterruptedException e) {
             * e.printStackTrace();
             * }
             */
        }
    }
}
