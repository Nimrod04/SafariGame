package model;

import java.awt.Rectangle;
import java.util.List;
import java.util.ArrayList;

public class Jeep {
    public static final int PRICE = 2000; // Maximum kapacitás
    private int capacity;
    private Coordinate position;
    private List<Coordinate> path; // Az út, amin halad
    private Rectangle hitbox; // Hitbox a Jeep-hez
    private boolean isReadyToMove; // Állapot, hogy a Jeep elindulhat-e
    private List<Tourist> passengers; // Látogatók a Jeep-ben

    public Jeep(Coordinate startPosition, List<Coordinate> path) {
        this.position = startPosition;
        this.path = path;
        this.hitbox = new Rectangle(startPosition.getPosX() - 3, startPosition.getPosY() - 3, 7, 7); // 7x7-es hitbox
        this.isReadyToMove = false; // Alapértelmezés szerint nem indul el
        this.passengers = new ArrayList<>(); // Látogatók listájának inicializálása
    }

    public Coordinate getPosition() {
        return position;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void move() {
        if (isReadyToMove && !path.isEmpty()) { // Csak akkor mozog, ha készen áll
            position = path.remove(0); // Következő pozícióra lép
            hitbox.setLocation(position.getPosX() - 3, position.getPosY() - 3); // Hitbox frissítése
        }
    }

    public boolean hasReachedEnd() {
        return path.isEmpty();
    }

    public void startMoving() {
        this.isReadyToMove = true; // Beállítja, hogy a Jeep elindulhat
    }

    public boolean isReadyToMove() {
        return isReadyToMove;
    }

    public void pickUpTourist(Tourist tourist) {
        if (passengers.size() < 4) { // Maximum 4 látogató fér el
            passengers.add(tourist);
            System.out.println("Tourist added to Jeep. Total passengers: " + passengers.size());
        }
    }

    public void clearPassengers() {
        passengers.clear(); // Látogatók eltávolítása a Jeep-ből
        System.out.println("Jeep passengers cleared.");
    }

    public List<Tourist> getPassengers() {
        return passengers;
    }

    public void resetPosition() {
        if (!path.isEmpty()) {
            position = path.get(0); // Visszaállítás az útvonal első pozíciójára
            hitbox.setLocation(position.getPosX() - 3, position.getPosY() - 3); // Hitbox frissítése
            isReadyToMove = false; // Mozgás leállítása
        }
    }
}
