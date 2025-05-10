package model;

import static view.GamePanel.TILE_SIZE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import view.Playing;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A GameMap osztály a játék térképét és annak elemeit kezeli.
 * Felelős az állatok, utak, Jeepek, kamerák, töltőállomások, drónok, léghajók és rangerek kezeléséért,
 * valamint a térkép generálásáért, állatok csoportosításáért, mozgásáért, utak ellenőrzéséért,
 * útvonal keresésért, valamint a Jeepek és állatok frissítéséért.
 * <p>
 * Az osztály public, így más csomagokból és osztályokból is példányosítható és elérhető.
 * <p>
 * Az osztályon belül több adattag is public, hogy más osztályok közvetlenül elérhessék az állatok listáit (pl. elephants, gazelles, lions, cheetahs),
 * például a játék logikája vagy a grafikus felület számára.
 */
public class GameMap {
    private GameSpeed gameSpeed;

    private final int width;
    private final int height;
    private Tile[][] map;
    private Random random = new Random();
    private ArrayList<ArrayList<Integer>> data;

    /**
     * Az elefántok listája.
     * Ez a mező public, hogy más osztályok is közvetlenül elérhessék az elefántokat, például a játék logikája vagy a grafikus felület.
     */
    public ArrayList<Elephant> elephants;
    /**
     * A gazellák listája.
     * Ez a mező public, hogy más osztályok is közvetlenül elérhessék a gazellákat, például a játék logikája vagy a grafikus felület.
     */
    public ArrayList<Gazelle> gazelles;
    /**
     * Az oroszlánok listája.
     * Ez a mező public, hogy más osztályok is közvetlenül elérhessék az oroszlánokat, például a játék logikája vagy a grafikus felület.
     */
    public ArrayList<Lion> lions;
    /**
     * A gepárdok listája.
     * Ez a mező public, hogy más osztályok is közvetlenül elérhessék a gepárdokat, például a játék logikája vagy a grafikus felület.
     */
    public ArrayList<Cheetah> cheetahs;

    private ArrayList<Camera> cameras;
    private ArrayList<ChargingStation> chargingStations;
    private ArrayList<Drone> drones;
    private List<Airship> airships;
    private ArrayList<Ranger> rangers;

    private ArrayList<Coordinate> roads; // Az utak pozícióinak tárolása
    private ArrayList<Jeep> jeeps;
    private Queue<Jeep> jeepQueue; // Jeepek várólistája
    private Playing playing; // Hozzáférés a Playing osztályhoz

    /**
     * Létrehoz egy új GameMap példányt a megadott szélességgel, magassággal, játéksebességgel és Playing példánnyal.
     * Inicializálja a térképet, állatokat, Jeepeket, kamerákat, töltőállomásokat, drónokat, léghajókat és rangereket.
     * Ez a konstruktor public, hogy más osztályokból is példányosítható legyen.
     * @param width a térkép szélessége
     * @param height a térkép magassága
     * @param gs a játék sebessége
     * @param playing a Playing példány
     */
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

    /**
     * Véletlenszerűen generálja a térképet, beállítja a csempék típusát, valamint a kapuk és utak helyét.
     */
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

    /**
     * Beállítja egy adott csempe típusát.
     * @param x a csempe x koordinátája
     * @param y a csempe y koordinátája
     * @param type a csempe típusa
     */
    public void setTile(int x, int y, Tile.TileType type) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            map[x][y].setType(type);
        }
    }

    /**
     * Véletlenszerűen generálja az állatokat a térképre.
     */
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

    /**
     * Frissíti az állatok állapotát, csoportosítja őket, és eltávolítja az elpusztult példányokat.
     */
    public void updateAnimals() {
        groupAnimals(elephants);
        groupAnimals(gazelles);
        groupAnimals(lions);
        groupAnimals(cheetahs);
        elephants = deleteElephants(elephants);
        gazelles = deleteGazelles(gazelles);
        lions = deleteLions(lions);
        cheetahs = deleteCheetahs(cheetahs);
    }

    /**
     * Eltávolítja az elpusztult elefántokat a listából.
     * @param animals az elefántok listája
     * @return az élő elefántok listája
     */
    public ArrayList<Elephant> deleteElephants(ArrayList<Elephant> animals) {
        ArrayList<Elephant> out = new ArrayList<>();
        for(Elephant animal : animals){
            if (animal.isAlive){
                out.add(animal);
            }
        }
        return out;
    }

    /**
     * Eltávolítja az elpusztult gazellákat a listából.
     * @param animals a gazellák listája
     * @return az élő gazellák listája
     */
    public ArrayList<Gazelle> deleteGazelles(ArrayList<Gazelle> animals) {
        ArrayList<Gazelle> out = new ArrayList<>();
        for(Gazelle animal : animals){
            if (animal.isAlive){
                out.add(animal);
            }
        }
        return out;
    }

    /**
     * Eltávolítja az elpusztult oroszlánokat a listából.
     * @param animals az oroszlánok listája
     * @return az élő oroszlánok listája
     */
    public ArrayList<Lion> deleteLions(ArrayList<Lion> animals) {
        ArrayList<Lion> out = new ArrayList<>();
        for(Lion animal : animals){
            if (animal.isAlive){
                out.add(animal);
            }
        }
        return out;
    }

    /**
     * Eltávolítja az elpusztult gepárdokat a listából.
     * @param animals a gepárdok listája
     * @return az élő gepárdok listája
     */
    public ArrayList<Cheetah> deleteCheetahs(ArrayList<Cheetah> animals) {
        ArrayList<Cheetah> out = new ArrayList<>();
        for(Cheetah animal : animals){
            if (animal.isAlive){
                out.add(animal);
            }
        }
        return out;
    }

    /**
     * Csoportosítja az állatokat közelség alapján, és kezeli a csoportok egyesítését.
     * @param animals az állatok listája
     */
    private void groupAnimals(List<? extends Animal> animals) {
        for (Animal animal : animals) {
            if (true) {
                // Ha az állat nincs csoportban, hozzunk létre egy új csoportot
                if (!animal.isInGroup()) {

                    List<Animal> newGroup = new ArrayList<>();
                    newGroup.add(animal);
                    for (Animal other : animals) {
                        if (animal != other && !other.isInGroup() && animal.distanceTo(other) <= Animal.GROUP_RADIUS) {
                            other.joinGroup(newGroup);
                            System.out.println("BELÉP----------------------------------");
                            System.out.println(newGroup.size());

                        }
                    }
                }

                // Ellenőrizzük, hogy két csoport találkozik-e
                for (Animal other : animals) {
                    if (animal != other && animal.isInGroup() && other.isInGroup() &&
                        animal.getGroup() != other.getGroup() && animal.distanceTo(other) <= Animal.GROUP_RADIUS) {

                        // Egyesítsük a két csoportot
                        List<Animal> group1 = animal.getGroup();
                        List<Animal> group2 = other.getGroup();
                        group1.addAll(group2);
                        for (Animal member : group2) {
                            member.joinGroup(group1);
                        }
                        group2.clear(); // A második csoportot kiürítjük
                    }
                }

            }
        }
        moveAnimals(animals);
        //System.out.println(animals.size());
    }

    /**
     * Frissíti és mozgatja az állatokat, valamint kezeli az élelem és víz hozzáadását.
     * @param animals az állatok listája
     */
    public void moveAnimals(List<? extends Animal> animals) {
        for (Animal animal : animals){
            ArrayList<Animal> novenyevok = new ArrayList<>();
            novenyevok.addAll(elephants);
            novenyevok.addAll(gazelles);
            //System.out.println("Növényevők száma: "+ novenyevok.size());
            animal.update(gameSpeed, novenyevok);

            // Aktuális csempe koordináták
            int actTileX = animal.actualCoordinate.getPosX() / TILE_SIZE;
            int actTileY = animal.actualCoordinate.getPosY() / TILE_SIZE;

            // Étel és víz hozzáadása
            animal.addVisitedWater(getTile(actTileX, actTileY), actTileX, actTileY);
            animal.addFoodIfEdible(getTile(actTileX, actTileY), actTileX, actTileY);

            if (animal.waterLevel == 0 || animal.foodLevel == 0) {
                // Debug üzenet
                //System.out.println(animal.waterLevel + " " + animal.foodLevel);
            }
        }
    }

    /**
     * Visszaadja az adott koordinátán lévő csempét.
     * @param x x koordináta
     * @param y y koordináta
     * @return a csempe
     */
    public Tile getTile(int x, int y) {
        return map[x][y];
    }

    /**
     * Visszaadja a térkép szélességét.
     * @return a szélesség
     */
    public int getWidth() {
        return width;
    }

    /**
     * Visszaadja a térkép magasságát.
     * @return a magasság
     */
    public int getHeight() {
        return height;
    }

    /**
     * Hozzáad egy kamerát a térképhez.
     * @param camera a kamera
     */
    public void addCamera(Camera camera) {
        cameras.add(camera);
    }

    /**
     * Visszaadja a kamerák listáját.
     * @return a kamerák listája
     */
    public ArrayList<Camera> getCameras() {
        return cameras;
    }

    /**
     * Hozzáad egy töltőállomást a térképhez.
     * @param chst a töltőállomás
     */
    public void addChargingStation(ChargingStation chst) {
        chargingStations.add(chst);
    }

    /**
     * Visszaadja a töltőállomások listáját.
     * @return a töltőállomások listája
     */
    public ArrayList<ChargingStation> getChargingStations() {
        return chargingStations;
    }

    /**
     * Visszaadja a drónok listáját.
     * @return a drónok listája
     */
    public ArrayList<Drone> getDrones() {
        return this.drones;
    }

    /**
     * Hozzáad egy drónt a térképhez.
     * @param drone a drón
     */
    public void addDrone(Drone drone) {
        this.drones.add(drone);
    }

    /**
     * Visszaadja a léghajók listáját.
     * @return a léghajók listája
     */
    public List<Airship> getAirships() {
        return airships;
    }

    /**
     * Hozzáad egy léghajót a térképhez.
     * @param airship a léghajó
     */
    public void addAirship(Airship airship) {
        airships.add(airship);
    }

    /**
     * Eltávolít egy léghajót a térképről.
     * @param airship a léghajó
     */
    public void removeAirship(Airship airship) {
        airships.remove(airship);
    }

    /**
     * Hozzáad egy gazellát a térképhez.
     * @param g a gazella
     */
    public void addGazelle(Gazelle g) {
        gazelles.add(g);
    }

    /**
     * Hozzáad egy elefántot a térképhez.
     * @param elephant az elefánt
     */
    public void addElephant(Elephant elephant) {
        elephants.add(elephant);
    }

    /**
     * Hozzáad egy oroszlánt a térképhez.
     * @param lion az oroszlán
     */
    public void addLion(Lion lion) {
        lions.add(lion);
    }

    /**
     * Hozzáad egy gepárdot a térképhez.
     * @param cheetah a gepárd
     */
    public void addCheetah(Cheetah cheetah) {
        cheetahs.add(cheetah);
    }

    /**
     * Hozzáad egy rangert a térképhez.
     * @param ranger a ranger
     */
    public void addRanger(Ranger ranger) {
        rangers.add(ranger);
    }

    /**
     * Visszaadja a rangerek listáját.
     * @return a rangerek listája
     */
    public ArrayList<Ranger> getRangers() {
        return rangers;
    }

    /**
     * Visszaadja a térkép csempéit.
     * @return a csempék kétdimenziós tömbje
     */
    public Tile[][] getMap() {
        return map;
    }

    /**
     * Visszaadja az utak koordinátáit.
     * @return az utak koordinátáinak listája
     */
    public ArrayList<Coordinate> getRoads() {
        return this.roads;
    }

    /**
     * Hozzáad egy utat a térképhez.
     * @param c az út koordinátája
     */
    public void addRoads(Coordinate c) {
        roads.add(c);
    }

    /**
     * Ellenőrzi, hogy van-e út a két kapu között a térképen.
     * @return true, ha van út, különben false
     */
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

    /**
     * Visszaadja a két kapu közötti útvonalat, ha létezik.
     * @return a kapuk közötti útvonal koordinátáinak listája
     */
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

    /**
     * Hozzáad egy Jeep-et a térképhez és a várólistához, ha érvényes útvonala van.
     * @param jeep a Jeep példány
     */
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

    /**
     * Visszaadja a Jeepek listáját.
     * Ez a metódus public, hogy más osztályok is lekérhessék a Jeepek listáját, például a játék logikája vagy a grafikus felület.
     * @return a Jeepek listája
     */
    public List<Jeep> getJeeps() {
        return jeeps;
    }

    /**
     * Visszaadja a Jeepek várólistáját.
     * Ez a metódus public, hogy más osztályok is lekérhessék a Jeepek várólistáját, például a játék logikája vagy a grafikus felület.
     * @return a Jeepek várólistája
     */
    public Queue<Jeep> getJeepQueue() {
        return jeepQueue;
    }

    /**
     * Frissíti a Jeepek állapotát, mozgatja őket, kezeli az állatok érzékelését és a kör végén visszaállítja őket.
     * Ez a metódus public, hogy más osztályokból is meghívható legyen, például a játék logikájából.
     */
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
            playing.getFinance().increase(adjustedSatisfaction*4); // Jövedelem növelése *4
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

    /**
     * Visszaadja az összes állatot a térképen.
     * Ez a metódus public, hogy más osztályok is lekérhessék az összes állat listáját, például a játék logikája vagy a grafikus felület.
     * @return az összes állat listája
     */
    public List<Animal> getAllAnimals() {
        List<Animal> allAnimals = new ArrayList<>();
        allAnimals.addAll(elephants);
        allAnimals.addAll(gazelles);
        allAnimals.addAll(lions);
        allAnimals.addAll(cheetahs);
        return allAnimals;
    }

    /**
     * Eltávolít egy állatot a térképről a típusának megfelelő listából.
     * Ez a metódus public, hogy más osztályokból is meghívható legyen, például a játék logikájából.
     * @param animal az eltávolítandó állat
     */
    public void removeAnimal(Animal animal) {
        if (animal instanceof Gazelle) {
            gazelles.remove(animal);
        } else if (animal instanceof Elephant) {
            elephants.remove(animal);
        } else if (animal instanceof Lion) {
            lions.remove(animal);
        } else if (animal instanceof Cheetah) {
            cheetahs.remove(animal);
        }
    }
}
