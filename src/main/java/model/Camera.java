package model;

import java.awt.*;

/**
 * A Camera osztály egy kamerát reprezentál a játékban, amelynek van pozíciója és hitboxa.
 * A kamera képes mozogni, és kirajzolható a pályára.
 */
public class Camera {
    /** A kamera pozíciója. */
    private Coordinate position;
    /** A kamera hitboxa. */
    private Rectangle hitbox; // Hitbox stored as a Rectangle
    /** A hitbox sugara tile-ban. */
    private static final int HITBOX_RADIUS = 2; // 4x4 hitbox (radius of 2)
    /** A hitbox teljes mérete tile-ban. */
    private static final int HITBOX_SIZE = (HITBOX_RADIUS * 2 + 1); // Total size of the hitbox

    /** A kamera ára. */
    public static final int PRICE = 1000;
    
    /**
     * Létrehoz egy új Camera példányt a megadott pozícióval.
     * @param position a kamera kezdőpozíciója
     */
    public Camera(Coordinate position) {
        this.position = position;
        this.hitbox = calculateHitbox(); // Initialize the hitbox
    }

    /**
     * Visszaadja a kamera aktuális pozícióját.
     * @return a kamera pozíciója
     */
    public Coordinate getPosition() {
        return position;
    }

    /**
     * Beállítja a kamera pozícióját, és újraszámolja a hitboxot.
     * @param position az új pozíció
     */
    public void setPosition(Coordinate position) {
        this.position = position;
        this.hitbox = calculateHitbox(); // Recalculate the hitbox when position changes
    }

    /**
     * Visszaadja a kamera hitboxát.
     * @return a hitboxot reprezentáló Rectangle
     */
    public Rectangle getHitbox() {
        return hitbox;
    }

    /**
     * Kiszámolja a kamera hitboxát az aktuális pozíció alapján.
     * @return a hitboxot reprezentáló Rectangle
     */
    private Rectangle calculateHitbox() {
        int x = position.getPosX() - HITBOX_RADIUS;
        int y = position.getPosY() - HITBOX_RADIUS;
        int size = HITBOX_SIZE;
        return new Rectangle(x, y, size, size);
    }

    /**
     * Kirajzolja a kamera hitboxát a megadott grafikus kontextusra.
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