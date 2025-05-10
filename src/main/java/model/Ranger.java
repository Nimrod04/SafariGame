package model;

import java.awt.*;

/**
 * A Ranger osztály a játékban egy parkőrt reprezentál.
 * Kezeli a parkőr pozícióját, hitboxát, valamint a hitbox kirajzolását.
 * <p>
 * Az osztály public, így más csomagokból és osztályokból is példányosítható és elérhető.
 */
public class Ranger {
    private Coordinate position;
    private Rectangle hitbox; // Hitbox stored as a Rectangle
    private static final int HITBOX_RADIUS = 4; // 4x4 hitbox (radius of 2)
    private static final int HITBOX_SIZE = (HITBOX_RADIUS * 2 + 1); // Total size of the hitbox

    /**
     * A parkőr ára.
     * Ez a mező public static final, így az osztályból közvetlenül elérhető, konstans érték.
     */
    public static final int PRICE = 5000;
    
    /**
     * Létrehoz egy új Ranger példányt a megadott pozícióval.
     * Ez a konstruktor public, így más osztályokból is példányosítható.
     * @param position a parkőr kezdőpozíciója
     */
    public Ranger(Coordinate position) {
        this.position = position;
        this.hitbox = calculateHitbox(); // Initialize the hitbox
    }

    /**
     * Visszaadja a parkőr aktuális pozícióját.
     * Ez a metódus public, hogy más osztályok is lekérhessék a parkőr pozícióját.
     * @return a parkőr pozíciója
     */
    public Coordinate getPosition() {
        return position;
    }

    /**
     * Beállítja a parkőr pozícióját, és újraszámolja a hitboxot.
     * Ez a metódus public, hogy más osztályok is módosíthassák a parkőr pozícióját.
     * @param position az új pozíció
     */
    public void setPosition(Coordinate position) {
        this.position = position;
        this.hitbox = calculateHitbox(); // Recalculate the hitbox when position changes
    }

    /**
     * Visszaadja a parkőr hitboxát.
     * Ez a metódus public, hogy más osztályok is lekérhessék a parkőr hitboxát.
     * @return a parkőr hitboxa
     */
    public Rectangle getHitbox() {
        return hitbox;
    }

    /**
     * Kiszámolja a parkőr hitboxát a pozíció alapján.
     * Ez a metódus private, mert csak az osztályon belül használatos.
     * @return a kiszámolt hitbox
     */
    private Rectangle calculateHitbox() {
        int x = position.getPosX() - HITBOX_RADIUS;
        int y = position.getPosY() - HITBOX_RADIUS;
        int size = HITBOX_SIZE;
        return new Rectangle(x, y, size, size);
    }

    /**
     * Kirajzolja a parkőr hitboxát a megadott grafikus kontextusra.
     * Ez a metódus public, hogy más osztályokból is meghívható legyen, például a grafikus felület kirajzolásakor.
     * @param g a grafikus kontextus
     * @param cameraX a kamera X pozíciója
     * @param cameraY a kamera Y pozíciója
     * @param tileSize a csempe mérete
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
        g.setColor(new Color(161, 251, 166, 60));
        g.fillRect(drawX, drawY, drawWidth, drawHeight);
        
    }
}