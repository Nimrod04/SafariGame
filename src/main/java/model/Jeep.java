package model;

import java.awt.Rectangle;
import java.util.List;

public class Jeep {
    public static final int PRICE = 2000; // Maximum kapacitás
    private int capacity;
    private Coordinate position;
    private List<Coordinate> path; // Az út, amin halad
    private Rectangle hitbox; // Hitbox a Jeep-hez

    public Jeep(Coordinate startPosition, List<Coordinate> path) {
        this.position = startPosition;
        this.path = path;
        this.hitbox = new Rectangle(startPosition.getPosX() - 3, startPosition.getPosY() - 3, 7, 7); // 7x7-es hitbox
    }

    public Coordinate getPosition() {
        return position;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void move() {
        if (!path.isEmpty()) {
            position = path.remove(0); // Következő pozícióra lép
            hitbox.setLocation(position.getPosX() - 3, position.getPosY() - 3); // Hitbox frissítése
        }
    }

    public boolean hasReachedEnd() {
        return path.isEmpty();
    }

    public void pickUpTourist(Tourist tourist) {
        // Turista felvétele
    }
}
