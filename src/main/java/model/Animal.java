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

    protected boolean isEating = false;
    protected long lastEatTime = 0;

    ArrayList<int[]> food;;
    ArrayList<int[]> drink;;
    protected List<Animal> group;
    Random random = new Random();

    protected static final int GROUP_RADIUS = 200;

    //public List<int[]> visitedLocations = new ArrayList<>();

    private static final int HITBOX_RADIUS = 1;
    private static final int HITBOX_SIZE = (HITBOX_RADIUS * 2 + 1);
    public Rectangle hitbox;

    public boolean isAlive;
    public boolean nap;
    public int napTime;

    public Animal() {
        this.lifetime = (int) (Math.random() * 7) + 5;
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




    public Rectangle calculateHitbox() {
        int x = actualCoordinate.getPosX() - HITBOX_RADIUS-(1*TILE_SIZE);
        int y = actualCoordinate.getPosY() - HITBOX_RADIUS-(1*TILE_SIZE);
        //System.out.println("Calculating hitbox for Cheetah: (" + x + ", " + y + ")");
        return new Rectangle(x, y, HITBOX_SIZE, HITBOX_SIZE);
    }

    public void findWater() {
        if (!drink.isEmpty()) {
            int[] lastDrink = drink.get(drink.size() - 1);
            targetCoordinate = new Coordinate(lastDrink[0]*TILE_SIZE, lastDrink[1]*TILE_SIZE);
            //System.out.println("Vizet talált. A cél" +lastDrink[0] + " " +lastDrink[1]);
        } else {
            //System.out.println(this.getClass().getSimpleName() + " nem talált vizet.");
        }
    }

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
    


    public double distanceTo(Animal other) {
        int dx = this.actualCoordinate.getPosX() - other.actualCoordinate.getPosX();
        int dy = this.actualCoordinate.getPosY() - other.actualCoordinate.getPosY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public void decreaseHunger(double multiplier) {
        foodLevel -= 0.05 * multiplier;
        if (foodLevel < 0) {
            foodLevel = 0;
            //this.isAlive = false; // Az állat meghal, ha az éhségszint 0
        }
    }
    public void decreaseThirst(double multiplier) {
        waterLevel -= 0.05 * multiplier;
        if (waterLevel < 0) {
            waterLevel = 0; 
            this.isAlive= false; // Az állat meghal, ha a szomjúságszint 0
        }
            }

    public void addVisitedWater(Tile tile, int x, int y) {
        for (int[] location : drink) {
            if (location[0] == x && location[1] == y) {
                return;
            }
        }
        addDrinkIfWater(tile, x, y);
    }

    public void addVisitedFood(Tile tile, int x, int y) {
        for (int[] location : food) {
            if (location[0] == x && location[1] == y) {
                return; 
            }
        }
        food.add(new int[]{x, y});
    }

    public void addDrinkIfWater(Tile tile, int x, int y) {
        if (tile.getType() == Tile.TileType.WATER) {
            drink.add(new int[]{x, y});
        }
        if (drink.size() != 0) {
            //System.out.println(drink.size());
        }
    }
    public void addFoodIfEdible(Tile tile, int x, int y) {
        if (tile.getType() == Tile.TileType.TREE || tile.getType() == Tile.TileType.GRASS || tile.getType() == Tile.TileType.BUSH) {
            food.add(new int[]{x, y});
        }
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
            return; // Ne rajzoljuk ki, ha a hitbox a nézeten kívül van
        }
    
        g.setColor(new Color(0, 0, 128, 120));
        g.drawRect(drawX, drawY, drawWidth, drawHeight);
        g.setColor(new Color(173, 216, 230, 60));
        g.fillRect(drawX, drawY, drawWidth, drawHeight);
    }

    public void eat(){

    };
    public void drink(){
        waterLevel = 100.0;
    };
    public boolean nap(){
        return false;
    };// esetleg true/false értékkel

    public void moveTo(GameSpeed gs, List<Animal> herbivores) {
        //System.out.println("kaja szint: "+foodLevel);
        if (/*isHungry()*/false){
            findFood();
        }
        else if(isThirsty() && targetCoordinate != null && hasReachedTarget()){
            drink();
            if (!isHungry()){

            }
            System.out.println("RAGADOZO IVOTT--------------------------------------" + waterLevel);
        }
        else if(isThirsty()){
            findWater();
        }
        else if (isInGroup()) {
            Animal leader = group.get(0);
            if (leader != this) {
                int offsetX = (int) (Math.random() * 40 - 20); //eltolás  -20 és 20 között
                int offsetY = (int) (Math.random() * 40 - 20);
                Coordinate targetWithOffset = new Coordinate(
                        leader.actualCoordinate.getPosX() + offsetX,
                        leader.actualCoordinate.getPosY() + offsetY
                );
                moveTo(targetWithOffset, gs);
                return;
            }
        }
        if (targetCoordinate == null || hasReachedTarget()) {
            generateRandomTarget();
        }
        moveTo(targetCoordinate,gs);
    }


    public void moveTo(Coordinate target, GameSpeed gs) {
        int speedInPx = (int) (gs.getMulti() * 1); // A játék sebességéhez igazítva
        //int speedInPx = (int) (baseSpeed * Game.getGameSpeed().getMulti()); // Sebesség a játék sebességéhez igazítva

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


    public boolean hasReachedTarget() {
        int dx = targetCoordinate.getPosX() - actualCoordinate.getPosX();
        int dy = targetCoordinate.getPosY() - actualCoordinate.getPosY();
        return Math.sqrt(dx * dx + dy * dy) < 5;
    }

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

    public void joinGroup(List<Animal> group) {
        this.group = group;
        group.add(this);
    }

    public boolean isThirsty() {
        return waterLevel < 30;
    }
    public boolean isHungry() {
        return foodLevel < 30;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setActualCoordinate(Coordinate c) {
        actualCoordinate = c;
    }
    public void setTargetCoordinate(Coordinate c) {
        targetCoordinate = c;
    }
    public Coordinate getCoordinate() {
        return actualCoordinate;
    }
    protected List<Animal> getGroup() {
        return group;
    }
    public boolean isInGroup() {
        return group != null && !group.isEmpty();
    }


}


