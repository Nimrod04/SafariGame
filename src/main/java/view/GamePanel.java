package view;

import model.GameMap;
import model.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GamePanel extends JPanel {
    private final GameMap gameMap;
    private final Map<Tile.TileType, Image> tileImages = new HashMap<>();
    private JPanel jPanel;

    public GamePanel(GameMap gameMap) {
        this.gameMap = gameMap;

        loadImages();
        this.setPreferredSize(new Dimension(640, 480));
        this.repaint();  // Force repainting
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
        int tileSize = 32;
        for (int x = 0; x < gameMap.getWidth(); x++) {
            for (int y = 0; y < gameMap.getHeight(); y++) {
                Tile tile = gameMap.getTile(x, y);

                g.drawImage(tileImages.get(Tile.TileType.SAND), x * tileSize, y * tileSize, tileSize, tileSize, this);
                g.drawImage(tileImages.get(tile.getType()), x * tileSize, y * tileSize, tileSize, tileSize, this);

            }
        }
            g.drawImage(tileImages.get(Tile.TileType.DIRT), 0 * tileSize, 8 * tileSize, tileSize, tileSize, this);
            g.drawImage(tileImages.get(Tile.TileType.DIRT), 0 * tileSize, 9 * tileSize, tileSize, tileSize, this);
            g.drawImage(tileImages.get(Tile.TileType.DIRT), 0 * tileSize, 10 * tileSize, tileSize, tileSize, this);


            g.drawImage(tileImages.get(Tile.TileType.GATE), 0 * tileSize, 8 * tileSize, tileSize, tileSize, this);
            g.drawImage(tileImages.get(Tile.TileType.GATE), 0 * tileSize, 9 * tileSize, tileSize, tileSize, this);
            g.drawImage(tileImages.get(Tile.TileType.GATE), 0 * tileSize, 10 * tileSize, tileSize, tileSize, this);



    }
}

