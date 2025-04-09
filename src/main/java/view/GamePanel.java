package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class GamePanel extends JPanel implements KeyListener {

    private final GameMap gameMap;
    private final Map<Tile.TileType, Image> tileImages = new HashMap<>();

    public static final int TILE_SIZE = 64;
    public static final int VIEWPORT_WIDTH = 20, VIEWPORT_HEIGHT = 10;
    private int cameraX = 0, cameraY = 0;

    private BufferedImage mapImage; // Gyorsítótárazott térkép kép

    public GamePanel(GameMap gameMap) {
        this.gameMap = gameMap;
        loadImages();
        this.setPreferredSize(new Dimension(VIEWPORT_WIDTH * TILE_SIZE, VIEWPORT_HEIGHT * TILE_SIZE));
        this.setFocusable(true);
        addKeyListener(this);
        this.requestFocusInWindow();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> requestFocusInWindow());
            }
        });

        renderMap(); // Térkép előzetes renderelése
    }

    private void renderMap() {
        mapImage = new BufferedImage(gameMap.getWidth() * TILE_SIZE, gameMap.getHeight() * TILE_SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics g = mapImage.getGraphics();

        for (int y = 0; y < gameMap.getHeight(); y++) {
            for (int x = 0; x < gameMap.getWidth(); x++) {
                Tile tile = gameMap.getTile(x, y);
                g.drawImage(tileImages.get(Tile.TileType.SAND), x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
                g.drawImage(tileImages.get(tile.getType()), x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
            }
        }

        g.dispose();
    }

    private void loadImages() {
        try {
            tileImages.put(Tile.TileType.GRASS, new ImageIcon(getClass().getResource("/images/grass.png")).getImage());
            tileImages.put(Tile.TileType.WATER, new ImageIcon(getClass().getResource("/images/water.png")).getImage());
            tileImages.put(Tile.TileType.TREE, new ImageIcon(getClass().getResource("/images/tree.png")).getImage());
            tileImages.put(Tile.TileType.BUSH, new ImageIcon(getClass().getResource("/images/bush.png")).getImage());
            tileImages.put(Tile.TileType.DIRT, new ImageIcon(getClass().getResource("/images/dirt.png")).getImage());
            tileImages.put(Tile.TileType.SAND, new ImageIcon(getClass().getResource("/images/sand.png")).getImage());
            tileImages.put(Tile.TileType.GAZELLE, new ImageIcon(getClass().getResource("/images/gazelle.png")).getImage());
            tileImages.put(Tile.TileType.ELEPHANT, new ImageIcon(getClass().getResource("/images/elephant.png")).getImage());
            tileImages.put(Tile.TileType.LION, new ImageIcon(getClass().getResource("/images/lion.png")).getImage());
            tileImages.put(Tile.TileType.CHEETAH, new ImageIcon(getClass().getResource("/images/cheetah.png")).getImage());
            tileImages.put(Tile.TileType.GATE, new ImageIcon(getClass().getResource("/images/gate.png")).getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Vászon törlése

        // Csak a látható részt rajzoljuk ki a gyorsítótárazott térképből
        g.drawImage(mapImage, 0, 0, VIEWPORT_WIDTH * TILE_SIZE, VIEWPORT_HEIGHT * TILE_SIZE,
                cameraX * TILE_SIZE, cameraY * TILE_SIZE,
                (cameraX + VIEWPORT_WIDTH) * TILE_SIZE, (cameraY + VIEWPORT_HEIGHT) * TILE_SIZE, null);

        // Állatok kirajzolása
        for (Elephant e : gameMap.elephants) {
            g.drawImage(tileImages.get(Tile.TileType.ELEPHANT),
                    e.getCoordinate().getPosX() - cameraX * TILE_SIZE,
                    e.getCoordinate().getPosY() - cameraY * TILE_SIZE,
                    TILE_SIZE, TILE_SIZE, this);
        }
        for (Gazelle e : gameMap.gazelles) {
            g.drawImage(tileImages.get(Tile.TileType.GAZELLE),
                    e.getCoordinate().getPosX() - cameraX * TILE_SIZE,
                    e.getCoordinate().getPosY() - cameraY * TILE_SIZE,
                    TILE_SIZE, TILE_SIZE, this);
        }
        for (Lion e : gameMap.lions) {
            g.drawImage(tileImages.get(Tile.TileType.LION),
                    e.getCoordinate().getPosX() - cameraX * TILE_SIZE,
                    e.getCoordinate().getPosY() - cameraY * TILE_SIZE,
                    TILE_SIZE, TILE_SIZE, this);
        }
        for (Cheetah e : gameMap.cheetahs) {
            g.drawImage(tileImages.get(Tile.TileType.CHEETAH),
                    e.getCoordinate().getPosX() - cameraX * TILE_SIZE,
                    e.getCoordinate().getPosY() - cameraY * TILE_SIZE,
                    TILE_SIZE, TILE_SIZE, this);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        boolean moved = false;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> {
                if (cameraX > 0) {
                    cameraX--;
                    moved = true;
                }
            }
            case KeyEvent.VK_D -> {
                if (cameraX < gameMap.getWidth() - VIEWPORT_WIDTH) {
                    cameraX++;
                    moved = true;
                }
            }
            case KeyEvent.VK_W -> {
                if (cameraY > 0) {
                    cameraY--;
                    moved = true;
                }
            }
            case KeyEvent.VK_S -> {
                if (cameraY < gameMap.getHeight() - VIEWPORT_HEIGHT) {
                    cameraY++;
                    moved = true;
                }
            }
        }

        if (moved) {
            repaint(); // Csak akkor rajzoljuk újra, ha ténylegesen mozgott a kamera
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public int getCameraX() {
        return cameraX;
    }

    public void setCameraX(int cameraX) {
        this.cameraX = cameraX;
    }

    public int getCameraY() {
        return cameraY;
    }

    public void setCameraY(int cameraY) {
        this.cameraY = cameraY;
    }

    public GameMap getGameMap() {
        return this.gameMap;
    }
}