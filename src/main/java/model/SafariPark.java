package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A SafariPark osztály a szafari parkot reprezentálja a játékban.
 * Kezeli a parkban található entitásokat, növényeket, vízforrásokat, Jeepeket, bejáratot, kijáratot és a térképet.
 * <p>
 * Az osztály public, így más csomagokból és osztályokból is példányosítható és elérhető.
 */
public class SafariPark {

    private List<Entity> entities;
    private List<Plant> plants;
    private List<WaterBody> waterSources;
    private List<Jeep> jeeps;
    private Entrance entrance;
    private Exit exit;
    private Path[][] mapGrid;

    private static final int MAP_SIZE = 10;
    private static final double TREE_PROBABILITY = 0.1;
    private static final double WATER_PROBABILITY = 0.1;
    private static final double BUSH_PROBABILITY = 0.1;
    private static final double GRASS_PROBABILITY = 0.7;

    /**
     * Alapértelmezett konstruktor a SafariPark osztályhoz.
     * Ez a konstruktor public, így más osztályokból is példányosítható.
     */
    public SafariPark() {
        this.entities = new ArrayList<>();
        this.plants = new ArrayList<>();
        this.waterSources = new ArrayList<>();
        this.jeeps = new ArrayList<>();
        this.mapGrid = new Path[MAP_SIZE][MAP_SIZE];
        this.entrance = new Entrance(new Coordinate(0, 0));
        this.exit = new Exit(new Coordinate(MAP_SIZE - 1, MAP_SIZE - 1));
    }

    /**
     * Legenerálja a park térképét, feltölti növényekkel, vízforrásokkal és beállítja a bejárat/kijárat pozícióját.
     * Ez a metódus public lehet, hogy más osztályokból is meghívható legyen, például a játék inicializálásakor.
     */
    public void generateMap() {
        Random random = new Random();

        for (int x = 0; x < MAP_SIZE; x++) {
            for (int y = 0; y < MAP_SIZE; y++) {
                double rand = random.nextDouble();
                String imagePath = "/resources/images/grass.png";

                if (rand < WATER_PROBABILITY) {
                    WaterBody water = new WaterBody(new Coordinate(x, y));
                    waterSources.add(water);
                    mapGrid[x][y] = new Path(new Coordinate(x, y), false);
                    imagePath = "/resources/images/water.png";
                } else if (rand < WATER_PROBABILITY + TREE_PROBABILITY) {
                    Tree tree = new Tree(new Coordinate(x, y));
                    plants.add(tree);
                    mapGrid[x][y] = new Path(new Coordinate(x, y), false);
                    imagePath = "/resources/images/tree.png";
                } else if (rand < WATER_PROBABILITY + TREE_PROBABILITY + BUSH_PROBABILITY) {
                    Bush bush = new Bush(new Coordinate(x, y));
                    plants.add(bush);
                    mapGrid[x][y] = new Path(new Coordinate(x, y), false);
                    imagePath = "/resources/images/bush.png";
                } else if (rand < WATER_PROBABILITY + TREE_PROBABILITY + BUSH_PROBABILITY + GRASS_PROBABILITY) {
                    Grass grass = new Grass(new Coordinate(x, y));
                    plants.add(grass);
                    mapGrid[x][y] = new Path(new Coordinate(x, y), false);
                    imagePath = "/resources/images/grass.png";
                } else {
                    mapGrid[x][y] = new Path(new Coordinate(x, y), false);
                }

                mapGrid[x][y].setImagePath(imagePath);
            }
        }

        mapGrid[0][0] = new Path(new Coordinate(0, 0), true);
        mapGrid[0][0].setImagePath("/resources/images/entrance.png");

        mapGrid[MAP_SIZE - 1][MAP_SIZE - 1] = new Path(new Coordinate(MAP_SIZE - 1, MAP_SIZE - 1), true);
        mapGrid[MAP_SIZE - 1][MAP_SIZE - 1].setImagePath("/resources/images/exit.png");

        System.out.println("Térkép generálása kész.");
    }

    /**
     * Frissíti az állatokat a parkban.
     * Ez a metódus public lehet, hogy más osztályokból is meghívható legyen, például a játék logikájából.
     */
    public void updateAnimals() {

    }

    /**
     * Visszaadja a park térképét.
     * Ez a metódus public, hogy más osztályok is lekérhessék a térképet, például a grafikus felület vagy a játék logikája.
     * @return a park térképe (Path mátrix)
     */
    public Path[][] getMapGrid() {
        return mapGrid;
    }

    /**
     * Visszaadja a parkban található entitásokat.
     * Ez a metódus public, hogy más osztályok is lekérhessék az entitások listáját.
     * @return az entitások listája
     */
    public List<Entity> getEntities() {
        return entities;
    }
}