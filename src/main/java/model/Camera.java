package model;

import java.awt.*;

public class Camera {
    private Coordinate position;
    private Rectangle hitbox; // Hitbox stored as a Rectangle
    private static final int HITBOX_RADIUS = 2; // 4x4 hitbox (radius of 2)
    private static final int HITBOX_SIZE = (HITBOX_RADIUS * 2 + 1); // Total size of the hitbox

    public static final int PRICE = 1000;
    
    public Camera(Coordinate position) {
        this.position = position;
        this.hitbox = calculateHitbox(); // Initialize the hitbox
    }


    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
        this.hitbox = calculateHitbox(); // Recalculate the hitbox when position changes
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