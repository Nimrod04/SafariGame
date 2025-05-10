package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Rectangle;

import static view.GamePanel.*;

/**
 * Az Animal absztrakt osztály az állatok közös tulajdonságait és viselkedését írja le.
 * Minden állat ebből származik le, és implementálja a szükséges absztrakt metódusokat.
 */
public abstract class Animal {
    /**
     * Visszaadja az állat árát.
     * @return az állat ára
     */
    public abstract int getPrice();

    /** Az állat faja. */
    protected String species;

    /** Az állat maximális élettartama. */
    protected int lifetime;
    /** Az állat aktuális életkora. */
    protected int age;

    /** Az állat aktuális koordinátája. */
    Coordinate actualCoordinate;
    /** Az állat cél koordinátája. */
    Coordinate targetCoordinate;

    /** Az állat élelem szintje. */
    protected double foodLevel;
    /** Az állat maximális élelem szintje. */
    protected int maxFood;
    /** Az állat víz szintje. */
    protected double waterLevel;
    /** Az állat maximális víz szintje. */
    protected int maxWater;

    /** Jelzi, hogy az állat éppen eszik-e. */
    protected boolean isEating = false;
    /** Az utolsó evés időbélyege. */
    protected long lastEatTime = 0;

    /** Az állat által ismert élelem helyek listája. */
    ArrayList<int[]> food;
    /** Az állat által ismert víz helyek listája. */
    ArrayList<int[]> drink;
    /** Az állat csoportja. */
    protected List<Animal> group;
    /** Véletlenszám-generátor. */
    Random random = new Random();

    /** A csoportosulás sugara. */
    protected static final int GROUP_RADIUS = 200;

    //public List<int[]> visitedLocations = new ArrayList<>();

    /** A hitbox sugara. */
    private static final int HITBOX_RADIUS = 1;
    /** A hitbox teljes mérete. */
    private static final int HITBOX_SIZE = (HITBOX_RADIUS * 2 + 1);
    /** Az állat hitboxa. */
    public Rectangle hitbox;

    /** Az állat életben van-e. */
    public boolean isAlive;
    /** Az állat alszik-e. */
    public boolean nap;
    /** Az alvás ideje. */
    public int napTime;

    /**
     * Az Animal alapértelmezett konstruktora.
     * Inicializálja az élettartamot, pozíciót, élelem- és vízszintet, valamint a hitboxot.
     */
    public Animal() {
        this.lifetime = 30*60*20;
        age = 0;
        targetCoordinate = null;
        int posX = (int) (Math.random() * 40 * TILE_SIZE);
        int posY = (int) (Math.random() * 20 * TILE_SIZE);
        actualCoordinate = new Coordinate(posX, posY);
        foodLevel = 100.0;
        waterLevel = 100.0;
        food = new ArrayList<>();
        drink = new ArrayList<>();
        isAlive = true;
        nap = false;
        napTime = 0;
        this.hitbox = calculateHitbox(); // Hitbox inicializálása
    }
    /*
    public Animal(int x, int y) {
        this.lifetime = (int) (Math.random() * 7) + 5;
        age = 0;
        targetCoordinate = null;
        actualCoordinate = new Coordinate(x, y);
        foodLevel = 100.0;
        waterLevel = 100.0;
        food = new ArrayList<>();
        drink = new ArrayList<>();
        isAlive = true;
        nap = false;
        napTime = 0;
        this.hitbox = calculateHitbox(); // Hitbox inicializálása
    }*/

    /**
     * Kiszámolja az állat hitboxát az aktuális pozíció alapján.
     * @return a hitboxot reprezentáló Rectangle
     */
    public Rectangle calculateHitbox() {
        int x = actualCoordinate.getPosX() - HITBOX_RADIUS-(1*TILE_SIZE);
        int y = actualCoordinate.getPosY() - HITBOX_RADIUS-(1*TILE_SIZE);
        //System.out.println("Calculating hitbox for Cheetah: (" + x + ", " + y + ")");
        return new Rectangle(x, y, HITBOX_SIZE, HITBOX_SIZE);
    }

    /**
     * Megkeresi a legutóbb ismert vízforrást, és beállítja célpontnak.
     */
    public void findWater() {
        if (!drink.isEmpty()) {
            int[] lastDrink = drink.get(drink.size() - 1);
            targetCoordinate = new Coordinate(lastDrink[0]*TILE_SIZE, lastDrink[1]*TILE_SIZE);
            //System.out.println("Vizet talált. A cél" +lastDrink[0] + " " +lastDrink[1]);
        } else {
            //System.out.println(this.getClass().getSimpleName() + " nem talált vizet.");
        }
    }

    /**
     * Megkeresi a legutóbb ismert élelemforrást, és beállítja célpontnak.
     */
    public void findFood() {
        //System.out.println("------------------------------------------------------------------------------");
        if (!food.isEmpty()) {
            int[] lastfood = food.get(food.size() - 1); // Az utolsó elem a drink listában
            targetCoordinate = new Coordinate(lastfood[0]*TILE_SIZE, lastfood[1]*TILE_SIZE);
            //System.out.println("Élelmet talált. A cél" +lastfood[0] + " " +lastfood[1] + "------------------------------");
        } else {
            //System.out.println(this.getClass().getSimpleName() + " nem talált élelmet.");
        }
    }

    /**
     * Kiszámolja a távolságot egy másik állathoz.
     * @param other a másik állat
     * @return a két állat közötti távolság
     */
    public double distanceTo(Animal other) {
        int dx = this.actualCoordinate.getPosX() - other.actualCoordinate.getPosX();
        int dy = this.actualCoordinate.getPosY() - other.actualCoordinate.getPosY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Csökkenti az éhség szintjét a megadott szorzóval, életkor alapján gyorsabban.
     * Ha az élelem szint 0 alá csökken, az állat meghal.
     * @param multiplier a csökkentés szorzója
     */
    public void decreaseHunger(double multiplier) {
        int ageMul = 1;
        if (12000<age && age <= 24000){
            ageMul = 2;
        } else if(24000<age){
            ageMul = 3;
        }
        foodLevel -= 0.05 * multiplier * ageMul;
        if (foodLevel < 0) {
            foodLevel = 0;
            this.isAlive = false;
        }
    }

    /**
     * Csökkenti a szomjúság szintjét a megadott szorzóval.
     * Ha a víz szint 0 alá csökken, az állat meghal.
     * @param multiplier a csökkentés szorzója
     */
    public void decreaseThirst(double multiplier) {
        waterLevel -= 0.05 * multiplier;
        if (waterLevel < 0) {
            waterLevel = 0; 
            this.isAlive= false; // Az állat meghal, ha a szomjúságszint 0
        }
    }

    /**
     * Hozzáadja a meglátogatott vízforrást, ha még nem szerepel a listában.
     * @param tile a tile objektum
     * @param x x koordináta
     * @param y y koordináta
     */
    public void addVisitedWater(Tile tile, int x, int y) {
        for (int[] location : drink) {
            if (location[0] == x && location[1] == y) {
                return;
            }
        }
        addDrinkIfWater(tile, x, y);
    }

    /**
     * Hozzáadja a meglátogatott élelemforrást, ha még nem szerepel a listában.
     * @param tile a tile objektum
     * @param x x koordináta
     * @param y y koordináta
     */
    public void addVisitedFood(Tile tile, int x, int y) {
        for (int[] location : food) {
            if (location[0] == x && location[1] == y) {
                return; 
            }
        }
        food.add(new int[]{x, y});
    }

    /**
     * Hozzáadja a vízforrást, ha az adott tile víz típusú.
     * @param tile a tile objektum
     * @param x x koordináta
     * @param y y koordináta
     */
    public void addDrinkIfWater(Tile tile, int x, int y) {
        if (tile.getType() == Tile.TileType.WATER) {
            drink.add(new int[]{x, y});
        }
        if (drink.size() != 0) {
            //System.out.println(drink.size());
        }
    }

    /**
     * Hozzáadja az élelemforrást, ha az adott tile ehető típusú.
     * @param tile a tile objektum
     * @param x x koordináta
     * @param y y koordináta
     */
    public void addFoodIfEdible(Tile tile, int x, int y) {
        if (tile.getType() == Tile.TileType.TREE || tile.getType() == Tile.TileType.GRASS || tile.getType() == Tile.TileType.BUSH) {
            food.add(new int[]{x, y});
        }
    }

    /**
     * Frissíti az állat hitboxát az aktuális pozíció alapján.
     */
    public void updateHitbox() {
        this.hitbox = calculateHitbox(); // Hitbox frissítése az aktuális pozíció alapján
        //System.out.println("Cheetah actualCoordinate: " + actualCoordinate + ", hitbox: " + hitbox);
    }

    /**
     * Kirajzolja az állat hitboxát a megadott grafikus kontextusra.
     * @param g grafikus kontextus
     * @param cameraX kamera X pozíciója
     * @param cameraY kamera Y pozíciója
     * @param tileSize tile mérete pixelben
     */
    public void drawHitbox(Graphics g, int cameraX, int cameraY, int tileSize) {
        int drawX = (hitbox.x - cameraX * tileSize);
        int drawY = (hitbox.y - cameraY * tileSize);
        int drawWidth = hitbox.width * tileSize;
        int drawHeight = hitbox.height * tileSize;

        // Ellenőrizzük, hogy a hitbox a kamera által látható területen belül van-e
        if (drawX + drawWidth < 0 || drawY + drawHeight < 0 || drawX > VIEWPORT_WIDTH * tileSize || drawY > VIEWPORT_HEIGHT * tileSize) {
            return; // Ne rajzoljuk ki, ha a hitbox a nézeten kívül van
        }

        g.setColor(new Color(0, 0, 128, 120));
        g.drawRect(drawX, drawY, drawWidth, drawHeight);
        g.setColor(new Color(173, 216, 230, 60));
        g.fillRect(drawX, drawY, drawWidth, drawHeight);
    }

    /**
     * Visszaadja az állat élelem szintjét.
     * @return élelem szint
     */
    public double getFoodLevel() {
        return foodLevel;
    }

    /**
     * Visszaadja az állat víz szintjét.
     * @return víz szint
     */
    public double getWaterLevel() {
        return waterLevel;
    }

    /**
     * Az állat evésének absztrakt metódusa.
     */
    public abstract void eat();

    /**
     * Az állat iszik, visszaállítja a víz szintjét maximumra.
     */
    public void drink(){
        waterLevel = 100.0;
    };

    /**
     * Az állat alszik-e (alapértelmezett: nem).
     * @return igaz, ha alszik, különben hamis
     */
    public boolean nap(){
        return false;
    };

    /**
     * Az állat állapotának frissítése.
     * @param gs a játék sebessége
     * @param herbivores a növényevők listája
     */
    public abstract void update(GameSpeed gs, List<Animal> herbivores);

    /**
     * Az állat öregszik a játék sebességének megfelelően.
     * Ha eléri az élettartamát, meghal.
     * @param gs a játék sebessége
     */
    public void gettingOld(GameSpeed gs){
        age += gs.getMulti();
        if(age >= lifetime){
            isAlive = false;
        }
        //System.out.println(age);
    }

    /**
     * Az állat mozog a cél koordináta felé a játék sebességének megfelelően.
     * @param target a cél koordináta
     * @param gs a játék sebessége
     */
    public void moveTo(Coordinate target, GameSpeed gs) {
        int speedInPx = (int) (gs.getMulti() * 1); // A játék sebességéhez igazítva

        int deltaX = target.getPosX() - actualCoordinate.getPosX();
        int deltaY = target.getPosY() - actualCoordinate.getPosY();

        int stepX = (int) Math.signum(deltaX); // -1, 0 vagy 1
        int stepY = (int) Math.signum(deltaY); // -1, 0 vagy 1

        int nextX = actualCoordinate.getPosX() + stepX * speedInPx;
        int nextY = actualCoordinate.getPosY() + stepY * speedInPx;

        if (Math.abs(deltaX) <= Math.abs(stepX * speedInPx)) {
            nextX = target.getPosX();
        }
        if (Math.abs(deltaY) <= Math.abs(stepY * speedInPx)) {
            nextY = target.getPosY();
        }

        decreaseHunger(gs.getMulti());
        decreaseThirst(gs.getMulti());

        actualCoordinate = new Coordinate(nextX, nextY);
        updateHitbox();
        int actTileX = nextX/TILE_SIZE;
        int actTileY = nextY/TILE_SIZE;
        if ( food.isEmpty() || drink.isEmpty()) {
            //System.out.println(food.size() + " " + drink.size());
        }
        //System.out.println(this.getClass().getSimpleName() + " aktuális pozíciója: (" + actTileX + ", " + actTileY + ")");
    };

    /**
     * Megadja, hogy az állat elérte-e a célját.
     * @return igaz, ha elérte, különben hamis
     */
    public boolean hasReachedTarget() {
        if (targetCoordinate != null){
            int dx = targetCoordinate.getPosX() - actualCoordinate.getPosX();
            int dy = targetCoordinate.getPosY() - actualCoordinate.getPosY();
            return Math.sqrt(dx * dx + dy * dy) < 5;
        }
        return true;
    }

    /**
     * Véletlenszerű célpontot generál az állat számára.
     */
    public void generateRandomTarget() {
        int randomX = (int) (Math.random() * 39 * TILE_SIZE); // Adjust map size as needed
        int randomY = (int) (Math.random() * 19 * TILE_SIZE);
        targetCoordinate = new Coordinate(randomX, randomY);
    }
    /*
    public boolean hasVisited(int x, int y) {
        for (int[] location : visitedLocations) {
            if (location[0] == x && location[1] == y) {
                return true; // Már járt itt
            }
        }
        return false; // Még nem járt itt

    }*/

    /**
     * Az állat csatlakozik egy csoporthoz.
     * @param group az állatok csoportja
     */
    public void joinGroup(List<Animal> group) {
        this.group = group;
        group.add(this);
    }

    /**
     * Megadja, hogy az állat szomjas-e.
     * @return igaz, ha szomjas, különben hamis
     */
    public boolean isThirsty() {
        return waterLevel < 30;
    }

    /**
     * Megadja, hogy az állat éhes-e.
     * @return igaz, ha éhes, különben hamis
     */
    public boolean isHungry() {
        return foodLevel < 30;
    }

    /**
     * Visszaadja az állat hitboxát.
     * @return a hitbox
     */
    public Rectangle getHitbox() {
        return hitbox;
    }

    /**
     * Beállítja az állat aktuális koordinátáját.
     * @param c az új koordináta
     */
    public void setActualCoordinate(Coordinate c) {
        actualCoordinate = c;
    }

    /**
     * Beállítja az állat cél koordinátáját.
     * @param c az új cél koordináta
     */
    public void setTargetCoordinate(Coordinate c) {
        targetCoordinate = c;
    }

    /**
     * Visszaadja az állat aktuális koordinátáját.
     * @return az aktuális koordináta
     */
    public Coordinate getCoordinate() {
        return actualCoordinate;
    }

    /**
     * Visszaadja az állat csoportját.
     * @return az állat csoportja
     */
    protected List<Animal> getGroup() {
        return group;
    }

    /**
     * Megadja, hogy az állat csoportban van-e.
     * @return igaz, ha csoportban van, különben hamis
     */
    public boolean isInGroup() {
        return group != null && !group.isEmpty();
    }

    /**
     * Megadja, hogy az állat felnőtt-e.
     * @return igaz, ha felnőtt, különben hamis
     */
    public boolean isAdult(){
        if (age >=10000){
            return true;
        }
        return false;
    }
}


