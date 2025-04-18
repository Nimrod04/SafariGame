package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Drone {
    private Coordinate position;
    private Rectangle hitbox;
    private static final int HITBOX_RADIUS = 1; // 3x3 hitbox (radius of 1)
    private static final int HITBOX_SIZE = (HITBOX_RADIUS * 2 + 1);
    public static final int PRICE = 1500;

    private ChargingStation chargingStation;
    private int orbitStep = 0; // 0-15, a 5x5 négyzet 16 pontja körül
    private long lastMoveTime = 0; // új mező a mozgás időzítéséhez
    private static final long MOVE_DELAY = 1000; // 1000ms = 1 másodperc

    private List<Coordinate> waypoints = new ArrayList<>(); // Útvonalpontok
    private boolean returningToStation = false; // Jelzi, hogy a drón visszatér-e a töltőállomásra
    private long waitStartTime = 0; // A várakozás kezdési ideje
    private static final long WAIT_DURATION = 4000; // 4 másodperc várakozás
    private static final long RETURN_INTERVAL = 20000; // 20 másodpercenként visszatérés

    public Drone(Coordinate position, ChargingStation chargingStation) {
        this.position = position;
        this.chargingStation = chargingStation;
        this.hitbox = calculateHitbox();
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
        this.hitbox = calculateHitbox();
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public ChargingStation getChargingStation() {
        return chargingStation;
    }

    public void setChargingStation(ChargingStation chargingStation) {
        this.chargingStation = chargingStation;
    }

    public List<Coordinate> getWaypoints() {
        return waypoints;
    }

    public void addWaypoint(Coordinate waypoint) {
        waypoints.add(waypoint);
    }

    public void clearWaypoints() {
        waypoints.clear();
    }

    // Körbe mozgatás a charging station körül egy 5x5 négyzeten
    public void orbitChargingStation() {
        if (chargingStation == null) return;
        
        // Ellenőrizzük, hogy eltelt-e már 1 másodperc az utolsó mozgás óta
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastMoveTime < MOVE_DELAY) {
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

    private Rectangle calculateHitbox() {
        int x = position.getPosX() - HITBOX_RADIUS;
        int y = position.getPosY() - HITBOX_RADIUS;
        int size = HITBOX_SIZE;
        return new Rectangle(x, y, size, size);
    }

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
