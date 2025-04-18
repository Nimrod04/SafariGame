package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameMap {
    private final int width;
    private final int height;
    private Tile[][] map;
    private Random random = new Random();
    private ArrayList<ArrayList<Integer>> data;

    public ArrayList<Elephant> elephants;
    public ArrayList<Gazelle> gazelles;
    public ArrayList<Lion> lions;
    public ArrayList<Cheetah> cheetahs;



    public GameMap(int width, int height) {
        this.data = new ArrayList<>();
        this.width = width;
        this.height = height;
        this.map = new Tile[width][height];
        elephants = new ArrayList<>();
        gazelles = new ArrayList<>();
        lions = new ArrayList<>();
        cheetahs = new ArrayList<>();

        generateRandomMap();
        generateAnimals();
    }


    private void generateRandomMap() {
        for (int x = 0; x < width; x++) {
            ArrayList<Integer> tmp = new ArrayList<>();
            for (int y = 0; y < height; y++) {
                double chance = random.nextDouble();
                if (chance < 0.02) {
                    tmp.add(3);
                    map[x][y] = new Tile(Tile.TileType.WATER);
                } else if (chance < 0.04) {
                    tmp.add(2);
                    map[x][y] = new Tile(Tile.TileType.TREE);
                } else if (chance < 0.07) {
                    tmp.add(1);
                    map[x][y] = new Tile(Tile.TileType.GRASS);
                }
                else {
                    tmp.add(0);
                    map[x][y] = new Tile(Tile.TileType.SAND);
                }
            }
            data.add(tmp);
        }
        System.out.println(data.toString());
    }

    

    private void generateAnimals(){
        int num = (int) (Math.random() * 5) + 5;
        for (int i = 0; i <= num; i++){
            elephants.add(new Elephant());
        }
        num = (int) (Math.random() * 5) + 5;
        for (int i = 0; i <= num; i++){
            gazelles.add(new Gazelle());
        }
        num = (int) (Math.random() * 5) + 5;
        for (int i = 0; i <= num; i++){
            lions.add(new Lion());
        }
        num = (int) (Math.random() * 5) + 5;
        for (int i = 0; i <= num; i++){
            cheetahs.add(new Cheetah());
        }
    }

    public void updateAnimals() {
        // Elefántok csoportosítása és mozgatása
        groupAnimals(elephants);

        // Gazellák csoportosítása és mozgatása
        groupAnimals(gazelles);

        // Oroszlánok csoportosítása és mozgatása
        groupAnimals(lions);

        // Gepárdok csoportosítása és mozgatása
        groupAnimals(cheetahs);
    }

    // Általános csoportosítási logika
    private void groupAnimals(List<? extends Animal> animals) {
        for (Animal animal : animals) {
            if (!animal.isInGroup()) {
                List<Animal> newGroup = new ArrayList<>();
                newGroup.add(animal);
                for (Animal other : animals) {
                    if (animal != other && !other.isInGroup() && animal.distanceTo(other) <= Animal.GROUP_RADIUS) {
                        other.joinGroup(newGroup);
                    }
                }
            }
            animal.moveTo();
        }
    }

    public Tile getTile(int x, int y) {
        return map[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

