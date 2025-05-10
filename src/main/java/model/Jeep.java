package model;

import static view.GamePanel.TILE_SIZE;

import java.awt.Rectangle;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import view.GamePanel;

/**
 * A Jeep osztály a játékban a dzsip járművet reprezentálja.
 * Kezeli a dzsip mozgását, utasait, látott állatokat, elégedettségi pontokat, valamint az útvonalát és pozícióját.
 * Tartalmazza a dzsiphez tartozó hitboxot, mozgás logikát, valamint az utasok és állatok kezelését.
 */
public class Jeep {
    /**
     * A dzsip aktuális elégedettségi pontjai.
     */
    public long satisfactionPoint = 0;
    /**
     * A dzsip aktuális útvonalának hossza.
     */
    public long tourLength = 0;
    /**
     * A dzsip frissítéseinek száma.
     */
    public long updateCount = 0; // Frissítések száma

    /**
     * A dzsip ára.
     */
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

    /**
     * Létrehoz egy új Jeep példányt a megadott kezdőpozícióval és útvonallal.
     * @param startPosition a Jeep kezdőpozíciója
     * @param path az útvonal, amin a Jeep halad
     */
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

    /**
     * Visszaadja a Jeep aktuális pozícióját.
     * @return a Jeep pozíciója
     */
    public Coordinate getPosition() {
        return position;
    }

    /**
     * Növeli a dzsip elégedettségi pontjait.
     * @param n a növelés mértéke
     */
    public void satisfaction(int n){
        satisfactionPoint += n;
    }

    /**
     * Visszaadja a dzsip által látott állatok nyilvántartását.
     * @return a látott állatok map-je
     */
    public Map<String, Integer> getSeenAnimals() {
        return seenAnimals;
    }
    
    /**
     * Növeli az út hosszát egy egységgel.
     */
    private void tourLengthInc(){
        tourLength++;
    }

    /**
     * Visszaadja a dzsip hitboxát.
     * @return a dzsip hitboxa
     */
    public Rectangle getHitbox() {
        return hitbox;
    }

    /**
     * Visszaadja a dzsip kezdőpozícióját.
     * @return a kezdőpozíció
     */
    public Coordinate getStartPosition() {
        return startPosition;
    }

    /**
     * Visszaadja a dzsip útját.
     * @return az út koordinátái
     */
    public List<Coordinate> getRoad() {
        return road;
    }

    /*
     * A dzsip mozgását valósítja meg, ha készen áll és van útvonal.
     * (Régi implementáció, jelenleg kommentelve.)
     */

    /**
     * A dzsip mozgását valósítja meg a játék sebessége alapján.
     * @param gameSpeed a játék sebessége
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

    /**
     * Megvizsgálja, hogy a dzsip elérte-e az útvonal végét.
     * @return true, ha az útvonal üres, különben false
     */
    public boolean hasReachedEnd() {
        return path.isEmpty();
    }

    /**
     * Elindítja a dzsipet.
     */
    public void startMoving() {
        this.isReadyToMove = true; // Beállítja, hogy a Jeep elindulhat
    }

    /**
     * Megadja, hogy a dzsip készen áll-e az indulásra.
     * @return true, ha indulhat, különben false
     */
    public boolean isReadyToMove() {
        return isReadyToMove;
    }

    /**
     * Visszaadja a dzsip aktuális útvonalát.
     * @return az útvonal koordinátái
     */
    public List<Coordinate> getPath() {
        return path;
    }

    /**
     * Hozzáad egy látogatót a dzsiphez, ha van hely.
     * @param tourist a hozzáadandó látogató
     */
    public void pickUpTourist(Tourist tourist) {
        if (passengers.size() < 4) { // Maximum 4 látogató fér el
            passengers.add(tourist);
            System.out.println("Tourist added to Jeep. Total passengers: " + passengers.size());
        }
    }

    /**
     * Eltávolítja az összes utast a dzsipből.
     */
    public void clearPassengers() {
        passengers.clear(); // Látogatók eltávolítása a Jeep-ből
        System.out.println("Jeep passengers cleared.");
    }

    /**
     * Visszaadja a dzsip utasainak listáját.
     * @return az utasok listája
     */
    public List<Tourist> getPassengers() {
        return passengers;
    }

    /**
     * Visszaállítja a dzsip pozícióját az útvonal első pontjára, és leállítja a mozgást.
     */
    public void resetPosition() {
        if (!path.isEmpty()) {
            position = path.get(0); // Visszaállítás az útvonal első pozíciójára
            hitbox.setLocation(position.getPosX() - 3, position.getPosY() - 3); // Hitbox frissítése
            isReadyToMove = false; // Mozgás leállítása
        }
    }

    /**
     * Rögzíti, hogy a dzsip milyen állatot látott.
     * @param animalType az állat típusa
     */
    public void recordAnimal(String animalType) {
        System.out.println("Animal recorded: " + animalType);
        seenAnimals.put(animalType, seenAnimals.getOrDefault(animalType, 0) + 1);
    }

    /**
     * Kiírja a dzsip által látott állatokat a konzolra.
     */
    public void printSeenAnimals() {
        System.out.println("Animals seen during the trip:");
        for (Map.Entry<String, Integer> entry : seenAnimals.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    /**
     * Törli a dzsip által látott állatok nyilvántartását.
     */
    public void clearSeenAnimals() {
        seenAnimals.clear(); // Nyilvántartás törlése az új út előtt
    }
}
