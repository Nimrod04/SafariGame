package model;

import static view.GamePanel.TILE_SIZE;

import java.awt.Rectangle;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import view.GamePanel;

public class Jeep {
    public long satisfactionPoint = 0;
    public long tourLength = 0;
    public long updateCount = 0; // Frissítések száma

    public static final int PRICE = 2000; // Jeep ára
    private Coordinate position; // Jeep aktuális pozíciója
    private List<Coordinate> path; // Az út, amin halad
    private List<Coordinate> road; // Az út, amin halad

    private Rectangle hitbox; // Hitbox a Jeep-hez
    private boolean isReadyToMove; // Állapot, hogy a Jeep elindulhat-e
    private List<Tourist> passengers; // Látogatók a Jeep-ben
    private Map<String, Integer> seenAnimals; // Állatok nyilvántartása
    private Coordinate startPosition; // Jeep aktuális pozíciója

    
    private long lastMoveTime = 0; // Az utolsó mozgás ideje
    public Jeep(Coordinate startPosition, List<Coordinate> path) {
        this.road = path; // Az útvonal, amin halad
        this.position = startPosition;
        this.startPosition = startPosition;
        this.path = path;
        this.hitbox = new Rectangle(startPosition.getPosX() - 3, startPosition.getPosY() - 3, 7, 7); // 7x7-es hitbox
        this.isReadyToMove = false; // Alapértelmezés szerint nem indul el
        this.passengers = new ArrayList<>(); // Látogatók listájának inicializálása
        this.seenAnimals = new HashMap<>(); // Állatok inicializálása
    }

    public Coordinate getPosition() {
        return position;
    }
    public void satisfaction(int n){
        satisfactionPoint += n;
    }

    public Map<String, Integer> getSeenAnimals() {
        return seenAnimals;
    }
    
    private void tourLengthInc(){
        tourLength++;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public Coordinate getStartPosition() {
        return startPosition;
    }

    public List<Coordinate> getRoad() {
        return road;
    }

    /*
     * public void move() {
        if (isReadyToMove && !path.isEmpty()) { // Csak akkor mozog, ha készen áll
            position = path.remove(0); // Következő pozícióra lép
            // hitbox.setLocation(position.getPosX() - 3, position.getPosY() - 3); // Hitbox
            // frissítése
            hitbox.setLocation(position.getPosX() -3, position.getPosY() -3); // Hitbox
                                                                                                                    // frissítése
                                                                                                                    // pixelben
            System.out.println("Jeep moved to: " + position.getPosX()+", "+position.getPosY() + ", hitbox updated to: " + hitbox.x + ", " + hitbox.y);
            tourLengthInc(); // Útvonal hosszának növelése
        }
    }
     */

    
    public void move(GameSpeed gameSpeed) {
        long currentTime = System.currentTimeMillis();
        long moveDelay = 1000 / gameSpeed.getMulti(); // Mozgás késleltetése a sebességszorzó alapján
    
        if (isReadyToMove && !path.isEmpty() && (currentTime - lastMoveTime >= moveDelay)) {
            position = path.remove(0); // Következő pozícióra lép
            hitbox.setLocation(position.getPosX() - 3, position.getPosY() - 3); // Hitbox frissítése
            //System.out.println("Jeep moved to: " + position.getPosX() + ", " + position.getPosY() + ", hitbox updated to: " + hitbox.x + ", " + hitbox.y);
            tourLengthInc(); // Útvonal hosszának növelése
            lastMoveTime = currentTime; // Frissítjük az utolsó mozgás idejét
            updateCount += (11-gameSpeed.getMulti()); // Frissítések számának növelése
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

    public List<Coordinate> getPath() {
        return path;
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

    public void recordAnimal(String animalType) {
        System.out.println("Animal recorded: " + animalType);
        seenAnimals.put(animalType, seenAnimals.getOrDefault(animalType, 0) + 1);
    }

    public void printSeenAnimals() {
        System.out.println("Animals seen during the trip:");
        for (Map.Entry<String, Integer> entry : seenAnimals.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void clearSeenAnimals() {
        seenAnimals.clear(); // Nyilvántartás törlése az új út előtt
    }
}
