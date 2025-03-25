package view;

import model.GameMap;
import model.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class GamePanel extends JPanel implements KeyListener {

    private final GameMap gameMap;
    private final Map<Tile.TileType, Image> tileImages = new HashMap<>();

    private static final int TILE_SIZE = 64;
    private static final int VIEWPORT_WIDTH = 20, VIEWPORT_HEIGHT = 10;
    private int cameraX = 0, cameraY = 0;

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

    public GamePanel(GameMap gameMap) {
        this.gameMap = gameMap;
        loadImages();
        this.setPreferredSize(new Dimension(VIEWPORT_WIDTH * TILE_SIZE, VIEWPORT_HEIGHT * TILE_SIZE));
        this.setFocusable(true);
        addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> requestFocusInWindow());
            }
        });

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
        super.paintComponent(g);

        for (int y = 0; y < VIEWPORT_HEIGHT; y++) {
            for (int x = 0; x < VIEWPORT_WIDTH; x++) {
                int worldX = cameraX + x;
                int worldY = cameraY + y;

                if (worldX >= 0 && worldX < gameMap.getWidth() && worldY >= 0 && worldY < gameMap.getHeight()) {
                    Tile tile = gameMap.getTile(worldX, worldY);
                    g.drawImage(tileImages.get(Tile.TileType.SAND), x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
                    g.drawImage(tileImages.get(tile.getType()), x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("ZZ");
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A ->
                cameraX = Math.max(0, cameraX - 1);
            case KeyEvent.VK_D ->
                cameraX = Math.min(gameMap.getWidth() - VIEWPORT_WIDTH, cameraX + 1);
            case KeyEvent.VK_W ->
                cameraY = Math.max(0, cameraY - 1);
            case KeyEvent.VK_S ->
                cameraY = Math.min(gameMap.getHeight() - VIEWPORT_HEIGHT, cameraY + 1);
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
