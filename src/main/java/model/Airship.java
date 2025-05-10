package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Az Airship osztály egy léghajót reprezentál a játékban, amely képes mozogni,
 * waypointokat követni, kijelölhető, és rendelkezik hitbox-szal.
 */
public class Airship {
    /** A hitbox sugara tile-ban. */
    private static final int HITBOX_RADIUS = 2; // 5x5-es hitbox (radius of 2)
    /** A hitbox teljes mérete tile-ban. */
    private static final int HITBOX_SIZE = (HITBOX_RADIUS * 2 + 1); // Teljes hitbox méret
    /** Az Airship ára. */
    public static final int PRICE = 4000;
    /** Az alap mozgási késleltetés milliszekundumban (3 tile/mp). */
    private static final long BASE_MOVE_DELAY = 333; // Alap késleltetés (3 tile/mp)

    private GameSpeed gameSpeed;
    private Coordinate position;
    private Rectangle hitbox; // Hitbox tárolása
    private List<Coordinate> waypoints; // Járőrözési útvonal
    private boolean selected; // Jelzi, hogy az Airship ki van-e jelölve
    private boolean isMoving; // Jelzi, hogy az Airship mozog-e
    private long lastMoveTime = 0; // Az utolsó mozgás időbélyege

    /**
     * Létrehoz egy új Airship példányt a megadott pozícióval és játéksebességgel.
     *
     * @param position   A léghajó kezdőpozíciója.
     * @param gameSpeed  A játék sebessége.
     */
    public Airship(Coordinate position, GameSpeed gameSpeed) {
        this.position = position;
        this.gameSpeed = gameSpeed;
        this.hitbox = calculateHitbox(); // Hitbox inicializálása
        this.waypoints = new ArrayList<>();
        this.selected = false; // Alapértelmezés szerint nincs kijelölve
        this.isMoving = false; // Alapértelmezés szerint nem mozog
    }

    /**
     * Visszaadja az Airship aktuális pozícióját.
     *
     * @return Az aktuális pozíció.
     */
    public Coordinate getPosition() {
        return position;
    }

    /**
     * Beállítja az Airship pozícióját, és újraszámolja a hitboxot.
     *
     * @param position Az új pozíció.
     */
    public void setPosition(Coordinate position) {
        this.position = position;
        this.hitbox = calculateHitbox(); // Hitbox újraszámítása pozícióváltáskor
    }

    /**
     * Visszaadja az Airship waypointjainak listáját.
     *
     * @return A waypointok listája.
     */
    public List<Coordinate> getWaypoints() {
        return waypoints;
    }

    /**
     * Hozzáad egy waypointot az Airship útvonalához.
     *
     * @param waypoint A hozzáadandó waypoint.
     */
    public void addWaypoint(Coordinate waypoint) {
        waypoints.add(waypoint);
    }

    /**
     * Törli az összes waypointot.
     */
    public void clearWaypoints() {
        waypoints.clear();
    }

    /**
     * Visszaadja az Airship hitboxát.
     *
     * @return A hitboxot reprezentáló Rectangle.
     */
    public Rectangle getHitbox() {
        return hitbox;
    }

    /**
     * Kiszámolja az Airship hitboxát az aktuális pozíció alapján.
     *
     * @return A hitboxot reprezentáló Rectangle.
     */
    private Rectangle calculateHitbox() {
        int x = position.getPosX() - HITBOX_RADIUS;
        int y = position.getPosY() - HITBOX_RADIUS;
        int size = HITBOX_SIZE;
        return new Rectangle(x, y, size, size);
    }

    /**
     * Visszaadja az Airship bounding boxát (5x5-ös négyzet).
     *
     * @return A bounding box Rectangle.
     */
    public Rectangle getBoundingBox() {
        int x = position.getPosX() - 2; // 5x5 hitbox bal felső sarka
        int y = position.getPosY() - 2; // 5x5 hitbox bal felső sarka
        return new Rectangle(x, y, 5, 5); // 5x5 méretű hitbox
    }

    /**
     * Megadja, hogy az Airship az adott tile-on van-e.
     *
     * @param tileX A tile X koordinátája.
     * @param tileY A tile Y koordinátája.
     * @return true, ha az Airship az adott tile-on van, különben false.
     */
    public boolean isClickedOnTile(int tileX, int tileY) {
        return position.getPosX() == tileX && position.getPosY() == tileY;
    }

    /**
     * Megadja, hogy az Airship ki van-e jelölve.
     *
     * @return true, ha ki van jelölve, különben false.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Beállítja, hogy az Airship ki van-e jelölve.
     *
     * @param selected Az új kijelölési állapot.
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * Megadja, hogy az Airship mozog-e.
     *
     * @return true, ha mozog, különben false.
     */
    public boolean isMoving() {
        return isMoving;
    }

    /**
     * Beállítja, hogy az Airship mozog-e.
     *
     * @param moving Az új mozgási állapot.
     */
    public void setMoving(boolean moving) {
        this.isMoving = moving;
    }

    /**
     * Kirajzolja az Airship hitboxát a megadott grafikus kontextusra.
     *
     * @param g        A grafikus kontextus.
     * @param cameraX  A kamera X pozíciója.
     * @param cameraY  A kamera Y pozíciója.
     * @param tileSize A tile mérete pixelben.
     */
    public void drawHitbox(Graphics g, int cameraX, int cameraY, int tileSize) {
        int drawX = (hitbox.x - cameraX) * tileSize;
        int drawY = (hitbox.y - cameraY) * tileSize;
        int drawWidth = hitbox.width * tileSize;
        int drawHeight = hitbox.height * tileSize;

        // Draw border (dark blue)
        g.setColor(new Color(0, 0, 128, 120));
        g.drawRect(drawX, drawY, drawWidth, drawHeight);

        // Fill inner area (light blue)
        g.setColor(new Color(173, 216, 230, 60));
        g.fillRect(drawX, drawY, drawWidth, drawHeight);
    }

    /**
     * Megadja, hogy az adott tile egy waypoint-e.
     *
     * @param tileX A tile X koordinátája.
     * @param tileY A tile Y koordinátája.
     * @return true, ha az adott tile egy waypoint, különben false.
     */
    public boolean isWaypoint(int tileX, int tileY) {
        for (Coordinate waypoint : waypoints) {
            if (waypoint.getPosX() == tileX && waypoint.getPosY() == tileY) {
                return true;
            }
        }
        return false;
    }

    /**
     * Az Airship-et a következő waypoint felé mozgatja, ha mozog és van waypoint.
     * A mozgás sebessége a játék sebességétől függ.
     * Ha eléri a waypointot, azt a lista végére helyezi (körkörös mozgás).
     */
    public void moveToNextWaypoint() {
        if (!isMoving || waypoints.isEmpty()) {
            return;
        }

        // Késleltetés kiszámítása a játék sebességének függvényében
        long currentMoveDelay = BASE_MOVE_DELAY / gameSpeed.getMulti();
        
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastMoveTime < currentMoveDelay) {
            return;
        }

        // Aktuális pozíció
        Coordinate currentPosition = getPosition();
        Coordinate targetWaypoint = waypoints.get(0); // Az első waypoint a cél

        // Ellenőrizzük, hogy elértük-e a cél waypointot
        if (currentPosition.getPosX() == targetWaypoint.getPosX() &&
            currentPosition.getPosY() == targetWaypoint.getPosY()) {
            // Ha elértük, eltávolítjuk a waypointot és a végére tesszük
            waypoints.remove(0);
            waypoints.add(targetWaypoint); // Körkörös mozgás
            System.out.println("Waypoint reached: " + targetWaypoint.getPosX() + ", " + targetWaypoint.getPosY());
            return;
        }

        // Mozgás a cél waypoint felé (egyszerű lépés X és Y irányban)
        int nextX = currentPosition.getPosX();
        int nextY = currentPosition.getPosY();

        if (currentPosition.getPosX() < targetWaypoint.getPosX()) {
            nextX++;
        } else if (currentPosition.getPosX() > targetWaypoint.getPosX()) {
            nextX--;
        }

        if (currentPosition.getPosY() < targetWaypoint.getPosY()) {
            nextY++;
        } else if (currentPosition.getPosY() > targetWaypoint.getPosY()) {
            nextY--;
        }

        // Frissítjük az Airship pozícióját
        setPosition(new Coordinate(nextX, nextY));
        System.out.println("Airship moved to: " + nextX + ", " + nextY);

        // Frissítjük az utolsó mozgás időbélyegét
        lastMoveTime = currentTime;
    }
}
