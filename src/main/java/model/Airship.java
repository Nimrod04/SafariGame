package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Airship {
    private static final int HITBOX_RADIUS = 2; // 5x5-es hitbox (radius of 2)
    private static final int HITBOX_SIZE = (HITBOX_RADIUS * 2 + 1); // Teljes hitbox méret
    public static final int PRICE = 4000;
    private static final long MOVE_DELAY = 333; // 333ms = 3 tile/mp

    private Coordinate position;
    private Rectangle hitbox; // Hitbox tárolása
    private List<Coordinate> waypoints; // Járőrözési útvonal
    private boolean selected; // Jelzi, hogy az Airship ki van-e jelölve
    private boolean isMoving; // Jelzi, hogy az Airship mozog-e
    private long lastMoveTime = 0; // Az utolsó mozgás időbélyege

    public Airship(Coordinate position) {
        this.position = position;
        this.hitbox = calculateHitbox(); // Hitbox inicializálása
        this.waypoints = new ArrayList<>();
        this.selected = false; // Alapértelmezés szerint nincs kijelölve
        this.isMoving = false; // Alapértelmezés szerint nem mozog
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
        this.hitbox = calculateHitbox(); // Hitbox újraszámítása pozícióváltáskor
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

    public Rectangle getHitbox() {
        return hitbox;
    }

    private Rectangle calculateHitbox() {
        int x = position.getPosX() - HITBOX_RADIUS;
        int y = position.getPosY() - HITBOX_RADIUS;
        int size = HITBOX_SIZE;
        return new Rectangle(x, y, size, size);
    }

    public Rectangle getBoundingBox() {
        int x = position.getPosX() - 2; // 5x5 hitbox bal felső sarka
        int y = position.getPosY() - 2; // 5x5 hitbox bal felső sarka
        return new Rectangle(x, y, 5, 5); // 5x5 méretű hitbox
    }

    public boolean isClickedOnTile(int tileX, int tileY) {
        return position.getPosX() == tileX && position.getPosY() == tileY;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        this.isMoving = moving;
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

    public boolean isWaypoint(int tileX, int tileY) {
        for (Coordinate waypoint : waypoints) {
            if (waypoint.getPosX() == tileX && waypoint.getPosY() == tileY) {
                return true;
            }
        }
        return false;
    }

    public void moveToNextWaypoint() {
        if (!isMoving || waypoints.isEmpty()) {
            return; // Nem mozog vagy nincs waypoint
        }

        // Ellenőrizzük, hogy eltelt-e a szükséges idő az utolsó mozgás óta
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastMoveTime < MOVE_DELAY) {
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
