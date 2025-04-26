package model;

import static view.GamePanel.TILE_SIZE;

public class Lion extends Carnivore {
    public static final int PRICE = 2000;

    public Lion() {
        this.lifetime = (int) (Math.random() * 7) + 5;
        age = 0;
        targetCoordinate = null;
        int posX = (int) (Math.random() * 40 * TILE_SIZE);
        int posY = (int) (Math.random() * 20 * TILE_SIZE);
        actualCoordinate = new Coordinate(posX, posY);
        foodLevel = 100.0;
        waterLevel = 100.0;
        this.hitbox = calculateHitbox(); // Hitbox inicializálása
    }

    public Lion(int x, int y) {
        this.lifetime = (int) (Math.random() * 7) + 5;
        age = 0;
        targetCoordinate = null;
        actualCoordinate = new Coordinate(x, y);
        foodLevel = 100.0;
        waterLevel = 100.0;
        this.hitbox = calculateHitbox(); // Hitbox inicializálása
    }


}
