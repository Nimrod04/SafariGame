package model;

import java.util.Random;

public class GameMap {
    private final int width;
    private final int height;
    private Tile[][] map;
    private Random random = new Random();

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new Tile[width][height];
        generateRandomMap();
    }

    private void generateRandomMap() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double chance = random.nextDouble();
                if (chance < 0.02) {
                    map[x][y] = new Tile(Tile.TileType.WATER);
                } else if (chance < 0.04) {
                    map[x][y] = new Tile(Tile.TileType.TREE);
                } else if (chance < 0.07) {
                    map[x][y] = new Tile(Tile.TileType.GRASS);
                }
                else {
                    map[x][y] = new Tile(Tile.TileType.SAND);
                }
            }
        }
    }

    public Tile getTile(int x, int y) {
        return map[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

