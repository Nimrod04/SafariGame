package model;

public class Tile {

    public void setType(TileType type) {
        this.type = type;
    }
    public enum TileType {
        GRASS, WATER, ROAD, TREE, BUSH, DIRT, SAND, GAZELLE, ELEPHANT, LION, CHEETAH, GATE, CAMERA, CHARGINGSTATION,DRONE,AIRSHIP
    }

    private TileType type;

    public Tile(TileType type) {
        this.type = type;
    }

    public TileType getType() {
        return type;
    }
    
    
}

