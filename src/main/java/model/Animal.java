package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Rectangle;

import static view.GamePanel.*;

public abstract class Animal {

    protected String species;

    protected int lifetime; //konstruktorban random értéket adni neki
    protected int age;

    Coordinate actualCoordinate;
    Coordinate targetCoordinate;

    protected double foodLevel;
    protected int maxFood;
    protected double waterLevel;
    protected int maxWater;

    ArrayList<Integer[]> food;
    ArrayList<Integer[]> drink;
    protected List<Animal> group;
    Random random = new Random();

    protected static final int GROUP_RADIUS = 200;

    public List<int[]> visitedLocations = new ArrayList<>();

    private static final int HITBOX_RADIUS = 1; // 3x3 hitbox (radius of 1)
    private static final int HITBOX_SIZE = (HITBOX_RADIUS * 2 + 1); // Teljes hitbox méret (3x3)
    public Rectangle hitbox; // Hitbox az állathoz

    public Animal() {
        this.lifetime = (int) (Math.random() * 7) + 5;
        age = 0;
        targetCoordinate = null;
        int posX = (int) (Math.random() * 40 * TILE_SIZE);
        int posY = (int) (Math.random() * 20 * TILE_SIZE);
        actualCoordinate = new Coordinate(posX, posY);
        foodLevel = 100.0;
        waterLevel = 100.0;
        this.hitbox = calculateHitbox(); // Hitbox inicializálása
    }

    public Animal(int x, int y) {
        this.lifetime = (int) (Math.random() * 7) + 5;
        age = 0;
        targetCoordinate = null;
        actualCoordinate = new Coordinate(x, y);
        foodLevel = 100.0;
        waterLevel = 100.0;
        this.hitbox = calculateHitbox(); // Hitbox inicializálása
    }

    private Rectangle calculateHitbox() {
        int x = actualCoordinate.getPosX() - HITBOX_RADIUS-(1*TILE_SIZE);
        int y = actualCoordinate.getPosY() - HITBOX_RADIUS-(1*TILE_SIZE);
        //System.out.println("Calculating hitbox for Cheetah: (" + x + ", " + y + ")");
        return new Rectangle(x, y, HITBOX_SIZE, HITBOX_SIZE);
    }

    public void findWater() {
        if (!drink.isEmpty()) {
            targetCoordinate = new Coordinate(drink.getLast()[0], drink.getLast()[1]);
        }
    }

    ;
    public void findFood() {
        if (!food.isEmpty()) {
            targetCoordinate = new Coordinate(food.getLast()[0], food.getLast()[1]);
        }
    }

    ;

    private void addFood() {
        //ha van a környéken víz vagy étel

    }

    public void setActualCoordinate(Coordinate c) {
        actualCoordinate = c;
    }

    ;
    public void setTargetCoordinate(Coordinate c) {
        targetCoordinate = c;
    }

    ;
    public Coordinate getCoordinate() {
        return actualCoordinate;
    }

    ;


    protected List<Animal> getGroup() {
        return group;
    }

    ;

    public void joinGroup(List<Animal> group) {
        this.group = group;
        group.add(this);
    }

    public boolean isInGroup() {
        return group != null && !group.isEmpty();
    }

    public double distanceTo(Animal other) {
        int dx = this.actualCoordinate.getPosX() - other.actualCoordinate.getPosX();
        int dy = this.actualCoordinate.getPosY() - other.actualCoordinate.getPosY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public void decreaseHunger(double multiplier) {
        foodLevel -= 0.2 * multiplier; // Csökkentjük az éhségszintet a sebességszorzó alapján
        if (foodLevel < 0) {
            foodLevel = 0; // Az éhségszint nem mehet 0 alá
        }
        //System.out.println(this.getClass().getSimpleName() + " éhségszintje: " + foodLevel);
    }

    public void decreaseThirst(double multiplier) {
        waterLevel -= 0.2 * multiplier; // Csökkentjük a szomjúságszintet a sebességszorzó alapján
        if (waterLevel < 0) {
            waterLevel = 0; // A szomjúságszint nem mehet 0 alá
        }
        //System.out.println(this.getClass().getSimpleName() + " szomjúságszintje: " + waterLevel);
    }

    public void addVisitedLocation(int x, int y) {
        // Ellenőrizzük, hogy a pozíció már szerepel-e a listában
        for (int[] location : visitedLocations) {
            if (location[0] == x && location[1] == y) {
                return; // Már szerepel, nem kell hozzáadni
            }
        }
        // Ha nem szerepel, hozzáadjuk
        visitedLocations.add(new int[]{x, y});
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void updateHitbox() {
        this.hitbox = calculateHitbox(); // Hitbox frissítése az aktuális pozíció alapján
        //System.out.println("Cheetah actualCoordinate: " + actualCoordinate + ", hitbox: " + hitbox);
    }

    public void drawHitbox(Graphics g, int cameraX, int cameraY, int tileSize) {
        int drawX = (hitbox.x - cameraX * tileSize);
        int drawY = (hitbox.y - cameraY * tileSize);
        int drawWidth = hitbox.width * tileSize;
        int drawHeight = hitbox.height * tileSize;
    
        // Ellenőrizzük, hogy a hitbox a kamera által látható területen belül van-e
        if (drawX + drawWidth < 0 || drawY + drawHeight < 0 || drawX > VIEWPORT_WIDTH * tileSize || drawY > VIEWPORT_HEIGHT * tileSize) {
            //System.out.println("Hitbox out of view: (" + drawX + ", " + drawY + ")");
            return; // Ne rajzoljuk ki, ha a hitbox a nézeten kívül van
        }
    
        //System.out.println("Drawing hitbox for Cheetah at: (" + drawX + ", " + drawY + ") with size: " + drawWidth + "x" + drawHeight);
    
        // Draw border (dark blue)
        g.setColor(new Color(0, 0, 128, 120));
        g.drawRect(drawX, drawY, drawWidth, drawHeight);
    
        // Fill inner area (light blue)
        g.setColor(new Color(173, 216, 230, 60));
        g.fillRect(drawX, drawY, drawWidth, drawHeight);
    }

    public abstract void eat();

    public abstract void drink();

    public abstract void nap();  // esetleg true/false értékkel

    public abstract void moveTo(GameSpeed gs);

    public abstract boolean hasReachedTarget();

    public abstract void generateRandomTarget();

    public abstract void moveTo(Coordinate target, GameSpeed gs);

    public abstract boolean hasVisited(int x, int y);
}
