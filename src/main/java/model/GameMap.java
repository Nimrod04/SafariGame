package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import view.Playing;

import java.util.LinkedList;
import java.util.Queue;

public class GameMap {
    private GameSpeed gameSpeed;

    private final int width;
    private final int height;
    private Tile[][] map;
    private Random random = new Random();
    private ArrayList<ArrayList<Integer>> data;

    public ArrayList<Elephant> elephants;
    public ArrayList<Gazelle> gazelles;
    public ArrayList<Lion> lions;
    public ArrayList<Cheetah> cheetahs;

    private ArrayList<Camera> cameras;
    private ArrayList<ChargingStation> chargingStations;
    private ArrayList<Drone> drones;
    private List<Airship> airships; // Airship-ek tárolása
    private ArrayList<Ranger> rangers;

    private ArrayList<Coordinate> roads; // Az utak pozícióinak tárolása
    private ArrayList<Jeep> jeeps;
    private Queue<Jeep> jeepQueue; // Jeepek várólistája
    private Playing playing; // Hozzáférés a Playing osztályhoz

    public GameMap(int width, int height, GameSpeed gs, Playing playing) {
        this.playing = playing;
        this.gameSpeed = gs;
        this.data = new ArrayList<>();
        this.width = width;
        this.height = height;
        this.map = new Tile[width][height];
        elephants = new ArrayList<>();
        gazelles = new ArrayList<>();
        lions = new ArrayList<>();
        cheetahs = new ArrayList<>();

        this.rangers = new ArrayList<>();
        this.cameras = new ArrayList<>();
        this.chargingStations = new ArrayList<>();
        this.drones = new ArrayList<>();
        this.airships = new ArrayList<>();

        jeeps = new ArrayList<>();
        jeepQueue = new LinkedList<>(); // Várólista inicializálása
        roads = new ArrayList<>();

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
                } else {
                    tmp.add(0);
                    map[x][y] = new Tile(Tile.TileType.SAND);
                }
            }
            data.add(tmp);
        }
        map[0][10] = new Tile(Tile.TileType.GATE);
        map[39][10] = new Tile(Tile.TileType.GATE);

        roads.add(new Coordinate(0, 10));
        roads.add(new Coordinate(39, 10));

        System.out.println(data.toString());
    }

    public void setTile(int x, int y, Tile.TileType type) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            map[x][y].setType(type);
        }
    }

    private void generateAnimals() {
        int num = (int) (Math.random() * 5) + 5;
        for (int i = 0; i <= num; i++) {
            elephants.add(new Elephant());
        }
        num = (int) (Math.random() * 5) + 5;
        for (int i = 0; i <= num; i++) {
            gazelles.add(new Gazelle());
        }
        num = (int) (Math.random() * 5) + 5;
        for (int i = 0; i <= num; i++) {
            lions.add(new Lion());
        }
        num = (int) (Math.random() * 5) + 5;
        for (int i = 0; i <= num; i++) {
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
            animal.moveTo(gameSpeed);
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

    public void addCamera(Camera camera) {
        cameras.add(camera);
    }

    public ArrayList<Camera> getCameras() {
        return cameras;
    }

    public void addChargingStation(ChargingStation chst) {
        chargingStations.add(chst);
    }

    public ArrayList<ChargingStation> getChargingStations() {
        return chargingStations;
    }

    public ArrayList<Drone> getDrones() {
        return this.drones;
    }

    public void addDrone(Drone drone) {
        this.drones.add(drone);
    }

    public List<Airship> getAirships() {
        return airships;
    }

    public void addAirship(Airship airship) {
        airships.add(airship);
    }

    public void removeAirship(Airship airship) {
        airships.remove(airship);
    }

    public void addGazelle(Gazelle g) {
        gazelles.add(g);
        System.out.println(gazelles.size());
    }

    public void addElephant(Elephant elephant) {
        elephants.add(elephant);
        System.out.println(elephants.size());
    }

    public void addLion(Lion lion) {
        lions.add(lion);
        System.out.println(lions.size());
    }

    public void addCheetah(Cheetah cheetah) {
        cheetahs.add(cheetah);
        System.out.println(cheetahs.size());
    }

    public void addRanger(Ranger ranger) {
        rangers.add(ranger);
    }

    public ArrayList<Ranger> getRangers() {
        return rangers;
    }

    public Tile[][] getMap() {
        return map;
    }

    public ArrayList<Coordinate> getRoads() {
        return this.roads;
    }

    public void addRoads(Coordinate c) {
        roads.add(c);
    }

    public boolean isPathBetweenGates() {
        Coordinate startGate = new Coordinate(0, 10); // Fix kezdő GATE pozíció
        Coordinate endGate = new Coordinate(39, 10); // Fix végső GATE pozíció

        // BFS algoritmus az út ellenőrzésére
        boolean[][] visited = new boolean[width][height];
        System.out.println("1: " + visited.length + " 2: " + visited[0].length);
        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(startGate);
        visited[startGate.getPosX()][startGate.getPosY()] = true;

        // Négy irány: fel, le, balra, jobbra
        int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();

            System.out.println("Vizsgált koordináta: " + current.getPosX() + ", " + current.getPosY());

            // Ha elértük a második GATE-et, van út
            if (current.equals(endGate)) {
                System.out.println("Van út a két GATE között!");
                return true;
            }

            // Szomszédos csempék bejárása
            for (int[] dir : directions) {
                int newX = current.getPosX() + dir[0];
                int newY = current.getPosY() + dir[1];

                // Ellenőrizzük, hogy a szomszédos csempe az `roads` listában van-e, és még nem
                // látogattuk meg
                if (newX >= 0 && newX < width && newY >= 0 && newY < height &&
                        !visited[newX][newY] && roads.contains(new Coordinate(newX, newY))) {
                    queue.add(new Coordinate(newX, newY));
                    visited[newX][newY] = true;
                }
            }
        }

        return false; // Ha nem találtunk utat, akkor nincs összeköttetés
    }

    public List<Coordinate> getPathBetweenGates() {
        Coordinate startGate = new Coordinate(0, 10); // Fix kezdő GATE pozíció
        Coordinate endGate = new Coordinate(39, 10); // Fix végső GATE pozíció

        List<Coordinate> path = new LinkedList<>();
        boolean[][] visited = new boolean[width][height];
        Queue<Coordinate> queue = new LinkedList<>();
        Map<Coordinate, Coordinate> previous = new HashMap<>();

        queue.add(startGate);
        visited[startGate.getPosX()][startGate.getPosY()] = true;

        int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();

            if (current.equals(endGate)) {
                // Útvonal visszafejtése
                while (current != null) {
                    path.add(0, current);
                    current = previous.get(current);
                }
                return path;
            }

            for (int[] dir : directions) {
                int newX = current.getPosX() + dir[0];
                int newY = current.getPosY() + dir[1];

                if (newX >= 0 && newX < width && newY >= 0 && newY < height &&
                        !visited[newX][newY] && roads.contains(new Coordinate(newX, newY))) {
                    queue.add(new Coordinate(newX, newY));
                    visited[newX][newY] = true;
                    previous.put(new Coordinate(newX, newY), current);
                }
            }
        }

        return path; // Ha nincs út, üres lista
    }

    public void addJeep(Jeep jeep) {
        if (jeep == null) {
            System.out.println("A Jeep nem lehet null!");
            return;
        }

        // Ellenőrizzük, hogy a Jeep-nek van-e érvényes útvonala
        if (jeep.getPosition() == null || jeep.hasReachedEnd()) {
            System.out.println("A Jeep-nek érvényes útvonallal kell rendelkeznie!");
            return;
        }

        jeeps.add(jeep); // Jeep hozzáadása a listához
        jeepQueue.add(jeep); // Jeep hozzáadása a várólistához

        System.out.println("this.hashCode(): " + this.hashCode());
        System.out.println("Jeep hozzáadva: " + jeep.getPosition());
    }

    public List<Jeep> getJeeps() {
        return jeeps;
    }

    public Queue<Jeep> getJeepQueue() {
        return jeepQueue;
    }

    public void updateJeeps() {
        List<Jeep> finishedJeeps = new ArrayList<>(); // Azok a Jeepek, amelyek elérték az útvonal végét

        for (Jeep jeep : jeeps) {
            if (jeep.isReadyToMove()) {
                jeep.move(gameSpeed); // Jeep mozgatása

                // Állatok érzékelése a Jeep közelében
                for (Animal animal : getAllAnimals()) {
                    if (jeep.getHitbox().intersects(animal.getHitbox())) {
                        jeep.recordAnimal(animal.getClass().getSimpleName());
                    }
                }

                if (jeep.hasReachedEnd()) { // Ha elérte az útvonal végét
                    finishedJeeps.add(jeep); // Hozzáadjuk a befejezett Jeepek listájához
                }
            }
        }

        // Az útvonal végét elért Jeepek kezelése
        for (Jeep finishedJeep : finishedJeeps) {
            int adjustedSatisfaction = (int) (finishedJeep.tourLength + (int) (finishedJeep.satisfactionPoint / Math.max(1, finishedJeep.updateCount))); // Pénz
            System.out.println(Math.max(1, finishedJeep.updateCount));                                                                                                           // számítása
            System.out.println("Bevétel: " + adjustedSatisfaction); // Jeep jövedelme
            playing.getFinance().increase(adjustedSatisfaction); // Jövedelem növelése
            playing.refreshBalance();
            finishedJeep.printSeenAnimals(); // Látott állatok kiírása
            finishedJeep.clearPassengers(); // Utasok eltávolítása
            finishedJeep.clearSeenAnimals(); // Látott állatok törlése
            finishedJeep.resetPosition(); // Jeep visszaállítása az út elejére
            jeepQueue.remove(finishedJeep); // Jeep eltávolítása a várólistából
            jeeps.remove(finishedJeep); // Jeep eltávolítása a listából
            addJeep(new Jeep(new Coordinate(0, 10), getPathBetweenGates()));
            System.out.println("Jeep reached the exit and returned to the start.");
        }
    }

    public List<Animal> getAllAnimals() {
        List<Animal> allAnimals = new ArrayList<>();
        allAnimals.addAll(elephants);
        allAnimals.addAll(gazelles);
        allAnimals.addAll(lions);
        allAnimals.addAll(cheetahs);
        return allAnimals;
    }
}
