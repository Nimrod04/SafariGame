package model;

public class Tile {
    public enum TileType {
        GRASS, WATER, ROAD, TREE, BUSH, DIRT, SAND, GAZELLE, ELEPHANT, LION, CHEETAH, GATE
    }

    private TileType type;

    public Tile(TileType type) {
        this.type = type;
    }

    public TileType getType() {
        return type;
    }
}

