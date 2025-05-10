package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A Drone osztály egy drónt reprezentál a játékban, amely képes mozogni, waypointokat követni,
 * visszatérni a töltőállomásra, és körözni a töltőállomás körül. Minden drónnak van pozíciója,
 * hitboxa, töltőállomása, valamint útvonalpontjai.
 */
public class Drone {

    private Coordinate position;
    private Rectangle hitbox;
    private static final int HITBOX_RADIUS = 1; // 3x3 hitbox (radius of 1)
    private static final int HITBOX_SIZE = (HITBOX_RADIUS * 2 + 1);
    /** A drón ára. */
    public static final int PRICE = 1500;

    private ChargingStation chargingStation;
    private int orbitStep = 0; // 0-15, a 5x5 négyzet 16 pontja körül
    private long lastMoveTime = 0; // új mező a mozgás időzítéséhez
    private static final long BASE_MOVE_DELAY = 1000; // Alap késleltetés 1 másodperc

    private List<Coordinate> waypoints = new ArrayList<>(); // Útvonalpontok
    private boolean returningToStation = false; // Jelzi, hogy a drón visszatér-e a töltőállomásra
    private long waitStartTime = 0; // A várakozás kezdési ideje
    private static final long WAIT_DURATION = 4000; // 4 másodperc várakozás
    private static final long RETURN_INTERVAL = 20000; // 20 másodpercenként visszatérés

    private GameSpeed gameSpeed;

    /**
     * Létrehoz egy új Drone példányt a megadott pozícióval, töltőállomással és játéksebességgel.
     * @param position a drón kezdőpozíciója
     * @param chargingStation a drónhoz tartozó töltőállomás
     * @param gameSpeed a játék sebessége
     */
    public Drone(Coordinate position, ChargingStation chargingStation, GameSpeed gameSpeed) {
        this.position = position;
        this.chargingStation = chargingStation;
        this.gameSpeed = gameSpeed;
        this.hitbox = calculateHitbox();
    }

    /**
     * Visszaadja a drón aktuális pozícióját.
     * @return a drón pozíciója
     */
    public Coordinate getPosition() {
        return position;
    }

    /**
     * Beállítja a drón pozícióját, és újraszámolja a hitboxot.
     * @param position az új pozíció
     */
    public void setPosition(Coordinate position) {
        this.position = position;
        this.hitbox = calculateHitbox();
    }

    /**
     * Visszaadja a drón hitboxát.
     * @return a hitboxot reprezentáló Rectangle
     */
    public Rectangle getHitbox() {
        return hitbox;
    }

    /**
     * Visszaadja a drónhoz tartozó töltőállomást.
     * @return a töltőállomás
     */
    public ChargingStation getChargingStation() {
        return chargingStation;
    }

    /**
     * Beállítja a drónhoz tartozó töltőállomást.
     * @param chargingStation az új töltőállomás
     */
    public void setChargingStation(ChargingStation chargingStation) {
        this.chargingStation = chargingStation;
    }

    /**
     * Visszaadja a drón útvonalpontjainak listáját.
     * @return a waypointok listája
     */
    public List<Coordinate> getWaypoints() {
        return waypoints;
    }

    /**
     * Hozzáad egy waypointot a drón útvonalához.
     * @param waypoint a hozzáadandó waypoint
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
     * Körbe mozgatja a drónt a töltőállomás körül egy 5x5-ös négyzet külső kerületén.
     * A mozgás sebessége a játék sebességétől függ.
     */
    public void orbitChargingStation() {
        if (chargingStation == null) {
            return;
        }

        // A késleltetés kiszámítása a játék sebességének függvényében
        long currentMoveDelay = BASE_MOVE_DELAY / gameSpeed.getMulti();

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastMoveTime < currentMoveDelay) {
            return;
        }

        int cx = chargingStation.getPosition().getPosX();
        int cy = chargingStation.getPosition().getPosY();

        // 5x5 négyzet külső kerületének pozíciói óramutató járásával megegyezően (16 pont)
        int[][] offsets = {
            {-2, -2}, {-1, -2}, {0, -2}, {1, -2}, {2, -2},
            {2, -1}, {2, 0}, {2, 1}, {2, 2},
            {1, 2}, {0, 2}, {-1, 2}, {-2, 2},
            {-2, 1}, {-2, 0}, {-2, -1}
        };

        orbitStep = (orbitStep + 1) % offsets.length;
        int[] offset = offsets[orbitStep];
        setPosition(new Coordinate(cx + offset[0], cy + offset[1]));

        lastMoveTime = currentTime; // Frissítjük az utolsó mozgás idejét
    }

    /**
     * Kiszámítja a drón hitboxát az aktuális pozíciója alapján. A hitbox egy
     * négyzet, amely a drón pozíciója köré van középpontozva, és méretét a
     * HITBOX_RADIUS és HITBOX_SIZE konstansok határozzák meg.
     *
     * @return Egy Rectangle objektum, amely a drón hitboxát reprezentálja.
     */
    private Rectangle calculateHitbox() {
        int x = position.getPosX() - HITBOX_RADIUS;
        int y = position.getPosY() - HITBOX_RADIUS;
        int size = HITBOX_SIZE;
        return new Rectangle(x, y, size, size);
    }

    /**
     * Kirajzolja a drón hitboxát a megadott grafikus kontextusra.
     * @param g grafikus kontextus
     * @param cameraX kamera X pozíciója
     * @param cameraY kamera Y pozíciója
     * @param tileSize tile mérete pixelben
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
}
