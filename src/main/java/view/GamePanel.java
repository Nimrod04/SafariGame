package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * A GamePanel osztály a játék fő grafikus panelje.
 * Kezeli a térkép, állatok, járművek, ranger-ek, kamerák, drónok, léghajók és egyéb objektumok kirajzolását,
 * valamint az egér- és billentyűzeteseményeket, a kamera mozgatását és a játék logikájának egy részét.
 * <p>
 * Az osztály public, így más csomagokból és osztályokból is példányosítható és elérhető, például a főablakból.
 */
public class GamePanel extends JPanel implements KeyListener {

    /** A játék térképét tároló GameMap példány. */
    private final GameMap gameMap;

    /** A játék logikáját kezelő Playing példány. */
    private Playing playing;

    /** A csempe típusokhoz tartozó képek map-je. */
    private final Map<Tile.TileType, Image> tileImages = new HashMap<>();

    /** A csempe mérete pixelben. Ez a mező public static final, így más osztályokból is elérhető. */
    public static final int TILE_SIZE = 64;

    /** A nézet szélessége csempékben. Ez a mező public static final, így más osztályokból is elérhető. */
    public static final int VIEWPORT_WIDTH = 20, VIEWPORT_HEIGHT = 10;

    /** A kamera X pozíciója a térképen. */
    private int cameraX = 0, cameraY = 0;

    /** Gyorsítótárazott térkép kép. */
    private BufferedImage mapImage;

    /** A játék frissítéséhez használt időzítő. */
    private Timer gameTimer;

    /** Az aktuálisan kiválasztott ranger. */
    private Ranger selectedRanger = null;

    /** Az utak koordinátáit tároló HashMap. */
    private HashMap<Integer, Coordinate> roads = new HashMap<>();

    /**
     * Létrehoz egy új GamePanel példányt a megadott GameMap és Playing objektummal.
     * Ez a konstruktor public, így más osztályokból is példányosítható.
     * @param gameMap a játék térképe
     * @param p a Playing példány
     */
    public GamePanel(GameMap gameMap, Playing p) {
        this.playing = p;
        this.gameMap = gameMap;
        this.playing = p;
        loadImages();
        this.setPreferredSize(new Dimension(VIEWPORT_WIDTH * TILE_SIZE, VIEWPORT_HEIGHT * TILE_SIZE));
        this.setFocusable(true);
        addKeyListener(this);
        this.requestFocusInWindow();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int tileX = (e.getX() / TILE_SIZE) + cameraX;
                    int tileY = (e.getY() / TILE_SIZE) + cameraY;
                    if (gameMap.getTile(tileX, tileY).getType() == Tile.TileType.ROAD) {
                        gameMap.setTile(tileX, tileY, Tile.TileType.SAND);
                        renderMap();
                        repaint();
                        System.out.println("Sand placed at: " + tileX + ", " + tileY);
                    }

                }
                SwingUtilities.invokeLater(() -> requestFocusInWindow());
                if (playing != null /* && playing.isInRoadShop() */ && playing.isBuildingRoad()) {
                    int tileX = (e.getX() / TILE_SIZE) + cameraX;
                    int tileY = (e.getY() / TILE_SIZE) + cameraY;

                    if (tileX >= 0 && tileX < gameMap.getWidth() && tileY >= 0 && tileY < gameMap.getHeight()) {
                        if (gameMap.getTile(tileX, tileY).getType() == Tile.TileType.SAND) {
                            if (playing.getFinance().getBalance() >= Road.PRICE) {
                                gameMap.setTile(tileX, tileY, Tile.TileType.ROAD);
                                gameMap.addRoads(new Coordinate(tileX, tileY));
                                System.out.println("Van-e út? " + gameMap.isPathBetweenGates());
                                playing.setCanBuyJeeps(gameMap.isPathBetweenGates());
                                playing.getFinance().decrease(Road.PRICE);
                                playing.refreshBalance();
                                renderMap();
                                repaint();
                            } else {
                                System.out.println("Not enough money!");
                            }
                        }
                    }
                }
                // Állatok
                // Ellenőrizzük, hogy kattintott-e egy állatra
                int mouseX = e.getX() + cameraX * TILE_SIZE;
                int mouseY = e.getY() + cameraY * TILE_SIZE;

                int mouseTileX = mouseX / TILE_SIZE;
                int mouseTileY = mouseY / TILE_SIZE;

                // Ranger kiválasztása
                for (Ranger ranger : gameMap.getRangers()) {
                    if (ranger.getPosition().getPosX() == mouseTileX && ranger.getPosition().getPosY() == mouseTileY) {
                        System.out.println("Ranger clixked");
                        selectedRanger = ranger;
                        System.out.println("Ranger selected at: " + ranger.getPosition().getPosX() + ", "
                                + ranger.getPosition().getPosY());
                        return; // Ha kiválasztottunk egy Rangert, nem vizsgáljuk tovább
                    }
                    /*
                     * if (ranger.getHitbox().contains(mouseTileX, mouseTileY)) {
                     * 
                     * }
                     */
                }

                for (Animal animal : gameMap.getAllAnimals()) {
                    if (!playing.isBuilding()) {

                        // System.out.println("Hitbox: " + animal.getHitbox());
                        // Hitbox középső csempéjének kiszámítása
                        int centerTileX = (animal.getHitbox().x + TILE_SIZE) / TILE_SIZE; // Középső csempe X
                                                                                          // koordinátája
                        int centerTileY = (animal.getHitbox().y + TILE_SIZE) / TILE_SIZE; // Középső csempe Y
                                                                                          // koordinátája

                        // System.out.println("Center Tile: " + centerTileX + ", " + centerTileY);
                        // System.out.println("Mouse Tile: " + mouseTileX + ", " + mouseTileY);

                        // Csak akkor érzékeljük a kattintást, ha az a középső csempére esik
                        int tolerance = 1; // Tolerancia csempe egységekben
                        if (Math.abs(centerTileX - mouseTileX) <= tolerance
                                && Math.abs(centerTileY - mouseTileY) <= tolerance) {
                            System.out.println(selectedRanger);
                            if (selectedRanger != null) {
                                // Ha van kiválasztott Ranger, az állatot eladjuk
                                System.out.println("Animal sold: " + animal.getClass().getSimpleName());
                                playing.getFinance().increase(animal.getPrice()); // Pénz hozzáadása
                                playing.refreshBalance(); // Pénz frissítése a UI-n
                                gameMap.removeAnimal(animal); // Állat eltávolítása a térképről
                                selectedRanger = null; // Ranger deselect
                                renderMap();
                                repaint();
                            } else {
                                System.out.println("Animal clicked: " + animal.getClass().getSimpleName());

                                // Egyedi panel létrehozása az éhség és szomjúság csíkokhoz
                                JPanel panel = new JPanel() {
                                    @Override
                                    protected void paintComponent(Graphics g) {
                                        super.paintComponent(g);

                                        // Éhség szöveg
                                        g.setColor(Color.BLACK);
                                        g.drawString("Éhség:", 10, 25);

                                        // Éhség csík (zöld)
                                        int hungerWidth = (int) (animal.getFoodLevel() / 100.0 * 200); // 200px széles
                                                                                                       // csík
                                        g.setColor(Color.GREEN);
                                        g.fillRect(60, 10, hungerWidth, 20);
                                        g.setColor(Color.BLACK);
                                        g.drawRect(60, 10, 200, 20); // Keret

                                        // Szomjúság szöveg
                                        g.setColor(Color.BLACK);
                                        g.drawString("Víz:", 10, 55);

                                        // Szomjúság csík (kék)
                                        int thirstWidth = (int) (animal.getWaterLevel() / 100.0 * 200); // 200px széles
                                                                                                        // csík
                                        g.setColor(Color.BLUE);
                                        g.fillRect(60, 40, thirstWidth, 20);
                                        g.setColor(Color.BLACK);
                                        g.drawRect(60, 40, 200, 20); // Keret
                                    }

                                    @Override
                                    public Dimension getPreferredSize() {
                                        return new Dimension(280, 80); // Panel mérete
                                    }
                                };

                                // Állat információinak megjelenítése
                                String message = String.format(
                                        "Faj: %s\nÉrték: %d$",
                                        animal.getClass().getSimpleName(),
                                        animal.getPrice());

                                JOptionPane.showMessageDialog(GamePanel.this, new Object[] { message, panel },
                                        "Állat Információ",
                                        JOptionPane.INFORMATION_MESSAGE);
                                return;
                            }

                        }
                    }
                }
                // Airship

                if (playing != null && playing.isBuildingAirship()) {
                    int tileX = (e.getX() / TILE_SIZE) + cameraX;
                    int tileY = (e.getY() / TILE_SIZE) + cameraY;
                    if (tileX >= 0 && tileX < gameMap.getWidth() && tileY >= 0 && tileY < gameMap.getHeight()) {
                        if (gameMap.getTile(tileX, tileY).getType() == Tile.TileType.SAND) {
                            if (playing.getFinance().getBalance() >= Airship.PRICE) {
                                gameMap.addAirship(
                                        new Airship(new Coordinate(tileX, tileY), playing.getGame().getGameSpeed()));
                                // gameMap.setTile(tileX, tileY, Tile.TileType.AIRSHIP);
                                playing.getFinance().decrease(Airship.PRICE);
                                playing.refreshBalance();
                                renderMap();
                                repaint();
                                System.out.println("Airship placed at: " + tileX + ", " + tileY);
                            } else {
                                System.out.println("No");
                            }

                        }

                    }
                }
                // Kattintás az Airship-re
                // Ellenőrizzük, hogy van-e aktív Airship, és ha igen, akkor hozzáadunk egy
                // waypointot
                // Ellenőrizzük, hogy pontosan az Airship pozíciójára kattintottunk-e
                // Ellenőrizzük, hogy pontosan az Airship pozíciójára kattintottunk-e
                for (Airship airship : gameMap.getAirships()) {
                    int tileX = (e.getX() / TILE_SIZE) + cameraX;
                    int tileY = (e.getY() / TILE_SIZE) + cameraY;

                    if (airship.isWaypoint(tileX, tileY)) {
                        // Waypoint törlése és Airship unselect
                        airship.clearWaypoints();
                        airship.setSelected(false);
                        System.out.println("Waypoint cleared and Airship unselected.");
                        return;
                    }
                }

                // Ellenőrizzük, hogy az Airship pozíciójára kattintottunk-e
                if (SwingUtilities.isLeftMouseButton(e)) {
                    int tileX = (e.getX() / TILE_SIZE) + cameraX;
                    int tileY = (e.getY() / TILE_SIZE) + cameraY;

                    // Ellenőrizzük, hogy az Airship pozíciójára kattintottunk-e
                    for (Airship airship : gameMap.getAirships()) {
                        if (airship.isClickedOnTile(tileX, tileY)) {
                            if (airship.isSelected()) {
                                // Waypointok elmentése és mozgás indítása
                                airship.setSelected(false);
                                airship.setMoving(true); // Mozgás indítása
                                System.out.println("Waypoints saved and Airship started moving.");
                            } else {
                                // Airship kijelölése waypointok hozzáadásához
                                airship.setSelected(true);
                                airship.setMoving(false); // Mozgás leállítása kijelöléskor
                                System.out.println("Airship selected at: " + tileX + ", " + tileY);
                            }
                            return;
                        }
                    }
                }

                // Ha waypointot akarunk hozzáadni az aktív Airship-hez
                // Ha waypointot akarunk hozzáadni az aktív Airship-hez
                for (Airship airship : gameMap.getAirships()) {
                    int tileX = (e.getX() / TILE_SIZE) + cameraX;
                    int tileY = (e.getY() / TILE_SIZE) + cameraY;
                    if (airship.isSelected()) {
                        airship.addWaypoint(new Coordinate(tileX, tileY));
                        System.out.println("Waypoint added: " + tileX + ", " + tileY);
                        return;
                    }
                }

                // Camera
                if (playing != null && playing.isBuildingCamera()) {
                    int tileX = (e.getX() / TILE_SIZE) + cameraX;
                    int tileY = (e.getY() / TILE_SIZE) + cameraY;
                    if (tileX >= 0 && tileX < gameMap.getWidth() && tileY >= 0 && tileY < gameMap.getHeight()) {
                        if (gameMap.getTile(tileX, tileY).getType() == Tile.TileType.SAND) {
                            if (playing.getFinance().getBalance() >= Camera.PRICE) {
                                gameMap.addCamera(new Camera(new Coordinate(tileX, tileY)));
                                gameMap.setTile(tileX, tileY, Tile.TileType.CAMERA);
                                playing.getFinance().decrease(Camera.PRICE);
                                playing.refreshBalance();
                                renderMap();
                                repaint();
                                System.out.println("Camera placed at: " + tileX + ", " + tileY);
                            } else {
                                System.out.println("No");
                            }

                        }

                    }
                }

                // Charging station
                if (playing != null && playing.isBuildingChargingStation()) {
                    int tileX = (e.getX() / TILE_SIZE) + cameraX;
                    int tileY = (e.getY() / TILE_SIZE) + cameraY;
                    if (tileX >= 0 && tileX < gameMap.getWidth() && tileY >= 0 && tileY < gameMap.getHeight()) {
                        if (gameMap.getTile(tileX, tileY).getType() == Tile.TileType.SAND) {
                            if (playing.getFinance().getBalance() >= ChargingStation.PRICE) {
                                gameMap.addChargingStation(new ChargingStation(new Coordinate(tileX, tileY)));
                                gameMap.setTile(tileX, tileY, Tile.TileType.CHARGINGSTATION);
                                playing.getFinance().decrease(ChargingStation.PRICE);
                                playing.refreshBalance();
                                renderMap();
                                repaint();
                                System.out.println("Camera placed at: " + tileX + ", " + tileY);
                            } else {
                                System.out.println("No");
                            }

                        }

                    }
                }

                // Drone
                if (playing != null && playing.isBuildingDrone()) {
                    int tileX = (e.getX() / TILE_SIZE) + cameraX;
                    int tileY = (e.getY() / TILE_SIZE) + cameraY;
                    if (tileX >= 0 && tileX < gameMap.getWidth() && tileY >= 0 && tileY < gameMap.getHeight()) {
                        if (gameMap.getTile(tileX, tileY).getType() == Tile.TileType.SAND) {
                            if (playing.getFinance().getBalance() >= Drone.PRICE) {
                                // Keresünk egy szabad charging stationt
                                ChargingStation freeStation = null;
                                for (ChargingStation cs : gameMap.getChargingStations()) {
                                    boolean assigned = false;
                                    for (Drone d : gameMap.getDrones()) {
                                        if (d.getChargingStation() == cs) {
                                            assigned = true;
                                            break;
                                        }
                                    }
                                    if (!assigned) {
                                        freeStation = cs;
                                        break;
                                    }
                                }
                                if (freeStation == null) {
                                    System.out.println("Nincs szabad charging station!");
                                    return;
                                }
                                gameMap.addDrone(new Drone(new Coordinate(tileX, tileY), freeStation,
                                        playing.getGame().getGameSpeed()));
                                // gameMap.setTile(tileX, tileY, Tile.TileType.DRONE);
                                playing.getFinance().decrease(Drone.PRICE);
                                playing.refreshBalance();
                                renderMap();
                                repaint();
                                System.out.println("Drone placed at: " + tileX + ", " + tileY);
                            } else {
                                System.out.println("No");
                            }
                        }
                    }
                }
                // Grass
                if (playing != null && playing.isBuildingGrass()) {
                    int tileX = (e.getX() / TILE_SIZE) + cameraX;
                    int tileY = (e.getY() / TILE_SIZE) + cameraY;
                    if (tileX >= 0 && tileX < gameMap.getWidth() && tileY >= 0 && tileY < gameMap.getHeight()) {
                        if (gameMap.getTile(tileX, tileY).getType() == Tile.TileType.SAND) {
                            if (playing.getFinance().getBalance() >= Grass.PRICE) {
                                gameMap.setTile(tileX, tileY, Tile.TileType.GRASS);
                                playing.getFinance().decrease(Grass.PRICE);
                                playing.refreshBalance();
                                renderMap();
                                repaint();
                                System.out.println("Grass placed at: " + tileX + ", " + tileY);
                            } else {
                                System.out.println("No");
                            }

                        }

                    }
                }
                // Tree
                if (playing != null && playing.isBuildingTree()) {
                    int tileX = (e.getX() / TILE_SIZE) + cameraX;
                    int tileY = (e.getY() / TILE_SIZE) + cameraY;
                    if (tileX >= 0 && tileX < gameMap.getWidth() && tileY >= 0 && tileY < gameMap.getHeight()) {
                        if (gameMap.getTile(tileX, tileY).getType() == Tile.TileType.SAND) {
                            if (playing.getFinance().getBalance() >= Tree.PRICE) {
                                gameMap.setTile(tileX, tileY, Tile.TileType.TREE);
                                playing.getFinance().decrease(Tree.PRICE);
                                playing.refreshBalance();
                                renderMap();
                                repaint();
                                System.out.println("Tree placed at: " + tileX + ", " + tileY);
                            } else {
                                System.out.println("No");
                            }

                        }

                    }
                }

                // Lake
                if (playing != null && playing.isBuildingLake()) {
                    int tileX = (e.getX() / TILE_SIZE) + cameraX;
                    int tileY = (e.getY() / TILE_SIZE) + cameraY;
                    if (tileX >= 0 && tileX < gameMap.getWidth() && tileY >= 0 && tileY < gameMap.getHeight()) {
                        if (gameMap.getTile(tileX, tileY).getType() == Tile.TileType.SAND) {
                            if (playing.getFinance().getBalance() >= WaterBody.PRICE) {
                                gameMap.setTile(tileX, tileY, Tile.TileType.WATER);
                                playing.getFinance().decrease(WaterBody.PRICE);
                                playing.refreshBalance();
                                renderMap();
                                repaint();
                                System.out.println("Water placed at: " + tileX + ", " + tileY);
                            } else {
                                System.out.println("No");
                            }

                        }

                    }
                }
                // Ranger
                if (playing != null && playing.isHiringStaff()) {
                    int tileX = (e.getX() / TILE_SIZE) + cameraX;
                    int tileY = (e.getY() / TILE_SIZE) + cameraY;

                    if (tileX >= 0 && tileX < gameMap.getWidth() && tileY >= 0 && tileY < gameMap.getHeight()) {
                        if (gameMap.getTile(tileX, tileY).getType() == Tile.TileType.SAND) {
                            if (playing.getFinance().getBalance() >= Ranger.PRICE) {
                                // Ranger hozzáadása a játéktérhez
                                gameMap.addRanger(new Ranger(new Coordinate(tileX, tileY)));
                                playing.getFinance().decrease(Ranger.PRICE);
                                playing.refreshBalance();
                                renderMap();
                                repaint();
                                System.out.println("Ranger placed at: " + tileX + ", " + tileY);
                            } else {
                                System.out.println("Not enough money!");
                            }
                        }
                    }
                }

                //Gazella
                if (playing != null && playing.isBuyingGazelle()) {
                    int tileX = (e.getX() / TILE_SIZE) + cameraX;
                    int tileY = (e.getY() / TILE_SIZE) + cameraY;

                    if (tileX >= 0 && tileX < gameMap.getWidth() && tileY >= 0 && tileY < gameMap.getHeight()) {
                        if (gameMap.getTile(tileX, tileY).getType() == Tile.TileType.SAND) {
                            if (playing.getFinance().getBalance() >= Gazelle.PRICE) {
                                //Gazella hozzáadása a játéktérhez
                                Gazelle g = new Gazelle();
                                int posX = tileX * TILE_SIZE;
                                int posY = tileY * TILE_SIZE;
                                g.setActualCoordinate(new Coordinate(posX, posY));
                                gameMap.addGazelle(g);
                                playing.getFinance().decrease(Gazelle.PRICE);
                                playing.refreshBalance();
                                renderMap();
                                repaint();
                                System.out.println("Gazelle placed at: " + tileX + ", " + tileY);
                            } else {
                                System.out.println("Not enough money!");
                            }
                        }
                    }
                }

                //Elefánt
                if (playing != null && playing.isBuyingElephant()) {
                    int tileX = (e.getX() / TILE_SIZE) + cameraX;
                    int tileY = (e.getY() / TILE_SIZE) + cameraY;

                    if (tileX >= 0 && tileX < gameMap.getWidth() && tileY >= 0 && tileY < gameMap.getHeight()) {
                        if (gameMap.getTile(tileX, tileY).getType() == Tile.TileType.SAND) {
                            if (playing.getFinance().getBalance() >= Elephant.PRICE) {
                                //Elefánt hozzáadása a játéktérhez
                                Elephant g = new Elephant();
                                int posX = tileX * TILE_SIZE;
                                int posY = tileY * TILE_SIZE;
                                g.setActualCoordinate(new Coordinate(posX, posY));
                                gameMap.addElephant(g);
                                playing.getFinance().decrease(Elephant.PRICE);
                                playing.refreshBalance();
                                renderMap();
                                repaint();
                                System.out.println("Elephant placed at: " + tileX + ", " + tileY);
                            } else {
                                System.out.println("Not enough money!");
                            }
                        }
                    }
                }
                //Oroszlán
                if (playing != null && playing.isBuyingLion()) {
                    int tileX = (e.getX() / TILE_SIZE) + cameraX;
                    int tileY = (e.getY() / TILE_SIZE) + cameraY;

                    if (tileX >= 0 && tileX < gameMap.getWidth() && tileY >= 0 && tileY < gameMap.getHeight()) {
                        if (gameMap.getTile(tileX, tileY).getType() == Tile.TileType.SAND) {
                            if (playing.getFinance().getBalance() >= Lion.PRICE) {
                                //Oroszlán hozzáadása a játéktérhez
                                Lion g = new Lion();
                                int posX = tileX * TILE_SIZE;
                                int posY = tileY * TILE_SIZE;
                                g.setActualCoordinate(new Coordinate(posX, posY));
                                gameMap.addLion(g);
                                playing.getFinance().decrease(Lion.PRICE);
                                playing.refreshBalance();
                                renderMap();
                                repaint();
                                System.out.println("Lion placed at: " + tileX + ", " + tileY);
                            } else {
                                System.out.println("Not enough money!");
                            }
                        }
                    }
                }

                // Gepárd
                if (playing != null && playing.isBuyingGepard()) {
                    int tileX = (e.getX() / TILE_SIZE) + cameraX;
                    int tileY = (e.getY() / TILE_SIZE) + cameraY;

                    if (tileX >= 0 && tileX < gameMap.getWidth() && tileY >= 0 && tileY < gameMap.getHeight()) {
                        if (gameMap.getTile(tileX, tileY).getType() == Tile.TileType.SAND) {
                            if (playing.getFinance().getBalance() >= Cheetah.PRICE) {
                                //Gepárd hozzáadása a játéktérhez
                                Cheetah g = new Cheetah();
                                int posX = tileX * TILE_SIZE;
                                int posY = tileY * TILE_SIZE;
                                g.setActualCoordinate(new Coordinate(posX, posY));
                                gameMap.addCheetah(g);
                                playing.getFinance().decrease(Cheetah.PRICE);
                                playing.refreshBalance();
                                renderMap();
                                repaint();
                                System.out.println("Cheetah placed at: " + tileX + ", " + tileY);
                            } else {
                                System.out.println("Not enough money!");
                            }
                        }
                    }
                }

                // Jeeps
                if (playing != null && playing.isBuyingJeeps()) {
                    // A startGate pozíciója
                    Coordinate startGate = new Coordinate(0, 10); // Példa: a startGate pozíciója (x=0, y=10)

                    // Ellenőrizzük, hogy van-e érvényes útvonal a kapuk között
                    List<Coordinate> path = gameMap.getPathBetweenGates();
                    if (path.isEmpty()) {
                        System.out.println("Nincs érvényes útvonal a kapuk között!");
                        return;
                    }

                    if (playing.getFinance().getBalance() >= Jeep.PRICE) { // Jeep árának ellenőrzése
                        // Jeep létrehozása a startGate pozícióval és az útvonallal
                        Jeep newJeep = new Jeep(startGate, path);
                        gameMap.addJeep(newJeep); // Jeep hozzáadása a GameMap-hez

                        playing.getFinance().decrease(Jeep.PRICE); // Pénz levonása
                        playing.refreshBalance();
                        renderMap();
                        repaint();
                        System.out.println("Jeep placed at startGate: " + startGate);
                    } else {
                        System.out.println("Not enough money!");
                    }
                }

                // Delete
                if (SwingUtilities.isRightMouseButton(e)) {
                    ArrayList<Tile.TileType> toDelete = new ArrayList<>();
                    toDelete.add(Tile.TileType.ROAD);
                    toDelete.add(Tile.TileType.CAMERA);
                    toDelete.add(Tile.TileType.CHARGINGSTATION);
                    toDelete.add(Tile.TileType.DRONE);
                    toDelete.add(Tile.TileType.AIRSHIP);

                    toDelete.add(Tile.TileType.GRASS);
                    toDelete.add(Tile.TileType.TREE);
                    toDelete.add(Tile.TileType.WATER);

                    int tileX = (e.getX() / TILE_SIZE) + cameraX;
                    int tileY = (e.getY() / TILE_SIZE) + cameraY;

                    // Ellenőrizzük, hogy az adott csempe út-e
                    if (gameMap.getTile(tileX, tileY).getType() == Tile.TileType.ROAD) {
                        // Töröljük az utat a roads listából
                        gameMap.getRoads().removeIf(road -> road.getPosX() == tileX && road.getPosY() == tileY);

                        // Állítsuk vissza a csempe típusát homokra
                        gameMap.setTile(tileX, tileY, Tile.TileType.SAND);
                        System.out.println("Van-e út? " + gameMap.isPathBetweenGates());
                        playing.setCanBuyJeeps(gameMap.isPathBetweenGates());

                        // Frissítsük a térképet és a képernyőt
                        renderMap();
                        repaint();

                        System.out.println("Road removed at: " + tileX + ", " + tileY);
                    }
                    // Ellenőrizzük, hogy a kattintás egy drón pozíciójára esik-e
                    for (Drone drone : gameMap.getDrones()) {
                        if (drone.getPosition().getPosX() == tileX && drone.getPosition().getPosY() == tileY) {
                            // Töröljük a drónt a GameMap-ből
                            gameMap.getDrones().remove(drone);
                            System.out.println("Drone removed at: " + tileX + ", " + tileY);

                            // Frissítsük a térképet és a képernyőt
                            renderMap();
                            repaint();
                            return; // Kilépünk, mert a drónt megtaláltuk és töröltük
                        }
                    }
                    // Ellenőrizzük, hogy a kattintás egy Airship pozíciójára esik-e
                    for (Airship airship : gameMap.getAirships()) {
                        if (airship.getPosition().getPosX() == tileX && airship.getPosition().getPosY() == tileY) {
                            // Töröljük az Airship-et a GameMap-ből
                            gameMap.getAirships().remove(airship);
                            airship.clearWaypoints(); // Waypointok törlése
                            System.out.println("Airship removed at: " + tileX + ", " + tileY);

                            // Frissítsük a térképet és a képernyőt
                            renderMap();
                            repaint();
                            return; // Kilépünk, mert az Airship-et megtaláltuk és töröltük
                        }
                    }

                    if (toDelete.contains(gameMap.getTile(tileX, tileY).getType())) {

                        if (gameMap.getTile(tileX, tileY).getType() == Tile.TileType.CAMERA) {
                            gameMap.getCameras().removeIf(camera -> camera.getPosition().getPosX() == tileX
                                    && camera.getPosition().getPosY() == tileY);
                        }

                        if (gameMap.getTile(tileX, tileY).getType() == Tile.TileType.CHARGINGSTATION) {
                            // Töröljük a hozzá kapcsolódó drónokat
                            gameMap.getDrones().removeIf(drone -> {
                                if (drone.getChargingStation().getPosition().getPosX() == tileX
                                        && drone.getChargingStation().getPosition().getPosY() == tileY) {
                                    System.out.println(
                                            "Drone removed due to ChargingStation removal at: " + tileX + ", " + tileY);
                                    return true; // Töröljük a drónt
                                }
                                return false;
                            });

                            // Töröljük a ChargingStation-t
                            gameMap.getChargingStations()
                                    .removeIf(chargingStation -> chargingStation.getPosition().getPosX() == tileX
                                            && chargingStation.getPosition().getPosY() == tileY);
                        }
                        gameMap.setTile(tileX, tileY, Tile.TileType.SAND);
                        renderMap();
                        repaint();
                        System.out.println("Sand placed at: " + tileX + ", " + tileY);
                    }
                }

            }
        });
        renderMap(); // Térkép előzetes renderelése

        // Időzítő a játék frissítéséhez
        gameTimer = new Timer(60, e -> {
            gameMap.updateAnimals(); // Állatok frissítése
            gameMap.updateJeeps(); // Jeepek frissítése
            repaint(); // Képernyő újrarajzolása
        });

        gameTimer.start();
    }

    /**
     * Visszaadja a csempe típusokhoz tartozó képek map-jét.
     * Ez a metódus public, hogy más osztályok is lekérhessék a képeket, például a grafikus felület.
     * @return a tileImages map
     */
    public Map<Tile.TileType, Image> getTileImages() {
        return tileImages;
    }

    /**
     * Kirendereli a térképet egy gyorsítótárazott képre.
     * Ez a metódus public lehet, hogy más osztályokból is meghívható legyen, például teszteléshez vagy újrarajzoláshoz.
     */
    public void renderMap() {
        mapImage = new BufferedImage(gameMap.getWidth() * TILE_SIZE, gameMap.getHeight() * TILE_SIZE,
                BufferedImage.TYPE_INT_ARGB);
        Graphics g = mapImage.getGraphics();

        int db = 0;
        for (int y = 0; y < gameMap.getHeight(); y++) {
            for (int x = 0; x < gameMap.getWidth(); x++) {
                Tile tile = gameMap.getTile(x, y);
                g.drawImage(tileImages.get(Tile.TileType.SAND), x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE,
                        null);
                g.drawImage(tileImages.get(tile.getType()), x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
            }
        }
        g.dispose();
    }

    /**
     * Betölti a csempe típusokhoz tartozó képeket.
     * Ez a metódus private, mert csak az osztályon belül használatos.
     */
    private void loadImages() {
        try {
            tileImages.put(Tile.TileType.ROAD, new ImageIcon(getClass().getResource("/images/dirt.png")).getImage());
            tileImages.put(Tile.TileType.GRASS, new ImageIcon(getClass().getResource("/images/grass.png")).getImage());
            tileImages.put(Tile.TileType.WATER, new ImageIcon(getClass().getResource("/images/water.png")).getImage());
            tileImages.put(Tile.TileType.TREE, new ImageIcon(getClass().getResource("/images/tree.png")).getImage());
            tileImages.put(Tile.TileType.BUSH, new ImageIcon(getClass().getResource("/images/bush.png")).getImage());
            tileImages.put(Tile.TileType.DIRT, new ImageIcon(getClass().getResource("/images/dirt.png")).getImage());
            tileImages.put(Tile.TileType.SAND, new ImageIcon(getClass().getResource("/images/sand.png")).getImage());
            tileImages.put(Tile.TileType.GAZELLE,
                    new ImageIcon(getClass().getResource("/images/gazelle.png")).getImage());
            tileImages.put(Tile.TileType.ELEPHANT,
                    new ImageIcon(getClass().getResource("/images/elephant.png")).getImage());
            tileImages.put(Tile.TileType.LION, new ImageIcon(getClass().getResource("/images/lion.png")).getImage());
            tileImages.put(Tile.TileType.CHEETAH,
                    new ImageIcon(getClass().getResource("/images/cheetah.png")).getImage());
            tileImages.put(Tile.TileType.GATE, new ImageIcon(getClass().getResource("/images/gate.png")).getImage());
            tileImages.put(Tile.TileType.ROAD, new ImageIcon(getClass().getResource("/images/dirt.png")).getImage());

            tileImages.put(Tile.TileType.CAMERA,
                    new ImageIcon(getClass().getResource("/images/camera.png")).getImage());
            tileImages.put(Tile.TileType.CHARGINGSTATION,
                    new ImageIcon(getClass().getResource("/images/charging_station.png")).getImage());
            tileImages.put(Tile.TileType.DRONE,
                    new ImageIcon(getClass().getResource("/images/drone_up.png")).getImage());
            tileImages.put(Tile.TileType.AIRSHIP,
                    new ImageIcon(getClass().getResource("/images/airship.png")).getImage());

            tileImages.put(Tile.TileType.RANGER,
                    new ImageIcon(getClass().getResource("/images/ranger.png")).getImage());
            tileImages.put(Tile.TileType.JEEP,
                    new ImageIcon(getClass().getResource("/images/jeep.png")).getImage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * A panel kirajzolását végzi.
     * Ez a metódus protected, mert a Swing komponensek így használják a felüldefiniált paintComponent-et.
     * @param g a grafikus kontextus
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Vászon törlése

        for (int y = 0; y < gameMap.getHeight(); y++) {
            for (int x = 0; x < gameMap.getWidth(); x++) {
                Tile tile = gameMap.getTile(x, y);
                g.drawImage(tileImages.get(tile.getType()), x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
            }
        }
        // Csak a látható részt rajzoljuk ki a gyorsítótárazott térképből
        g.drawImage(mapImage, 0, 0, VIEWPORT_WIDTH * TILE_SIZE, VIEWPORT_HEIGHT * TILE_SIZE,
                cameraX * TILE_SIZE, cameraY * TILE_SIZE,
                (cameraX + VIEWPORT_WIDTH) * TILE_SIZE, (cameraY + VIEWPORT_HEIGHT) * TILE_SIZE, null);

        // Állatok kirajzolása
        for (Elephant e : gameMap.elephants) {
            g.drawImage(tileImages.get(Tile.TileType.ELEPHANT),
                    e.getCoordinate().getPosX() - cameraX * TILE_SIZE,
                    e.getCoordinate().getPosY() - cameraY * TILE_SIZE,
                    TILE_SIZE, TILE_SIZE, this);
            // e.drawHitbox(g, cameraX, cameraY, TILE_SIZE);
        }
        for (Gazelle e : gameMap.gazelles) {
            g.drawImage(tileImages.get(Tile.TileType.GAZELLE),
                    e.getCoordinate().getPosX() - cameraX * TILE_SIZE,
                    e.getCoordinate().getPosY() - cameraY * TILE_SIZE,
                    TILE_SIZE, TILE_SIZE, this);
            // Hitbox kirajzolása
            // e.drawHitbox(g, cameraX, cameraY, TILE_SIZE);

        }
        for (Lion e : gameMap.lions) {
            g.drawImage(tileImages.get(Tile.TileType.LION),
                    e.getCoordinate().getPosX() - cameraX * TILE_SIZE,
                    e.getCoordinate().getPosY() - cameraY * TILE_SIZE,
                    TILE_SIZE, TILE_SIZE, this);
            // Hitbox kirajzolása
            // e.drawHitbox(g, cameraX, cameraY, TILE_SIZE);

        }
        for (Cheetah e : gameMap.cheetahs) {
            int centerX = e.getHitbox().x + e.getHitbox().width / 2;
            int centerY = e.getHitbox().y + e.getHitbox().height / 2;

            g.drawImage(tileImages.get(Tile.TileType.CHEETAH),
                    e.getCoordinate().getPosX() - cameraX * TILE_SIZE,
                    e.getCoordinate().getPosY() - cameraY * TILE_SIZE,
                    TILE_SIZE, TILE_SIZE, this);

            // Hitbox kirajzolása
            // e.drawHitbox(g, cameraX, cameraY, TILE_SIZE);
        }

        for (Camera camera : gameMap.getCameras()) {
            camera.drawHitbox(g, cameraX, cameraY, TILE_SIZE);
        }
        for (Drone drone : gameMap.getDrones()) {
            drone.orbitChargingStation();
            int centerX = drone.getHitbox().x + drone.getHitbox().width / 2;
            int centerY = drone.getHitbox().y + drone.getHitbox().height / 2;

            g.drawImage(tileImages.get(Tile.TileType.DRONE),
                    (centerX - cameraX) * TILE_SIZE - TILE_SIZE / 2, // Középre igazítás
                    (centerY - cameraY) * TILE_SIZE - TILE_SIZE / 2, // Középre igazítás
                    TILE_SIZE, TILE_SIZE, this);
            drone.drawHitbox(g, cameraX, cameraY, TILE_SIZE);

        }

        // Airship-ek kirajzolása és mozgatása
        for (Airship airship : gameMap.getAirships()) {
            int centerX = airship.getHitbox().x + airship.getHitbox().width / 2;
            int centerY = airship.getHitbox().y + airship.getHitbox().height / 2;

            g.drawImage(tileImages.get(Tile.TileType.AIRSHIP),
                    (centerX - cameraX) * TILE_SIZE - TILE_SIZE / 2, // Középre igazítás
                    (centerY - cameraY) * TILE_SIZE - TILE_SIZE / 2, // Középre igazítás
                    TILE_SIZE, TILE_SIZE, this); // Egy tile méretű kép

            airship.moveToNextWaypoint(); // Mozgás a waypointok között, ha aktív
            airship.drawHitbox(g, cameraX, cameraY, TILE_SIZE);

            // Waypointok kirajzolása
            g.setColor(new Color(0, 255, 0, 120)); // Halvány zöld szín
            int circleDiameter = TILE_SIZE / 2; // Kör átmérője
            int circleRadius = circleDiameter / 2;

            for (Coordinate waypoint : airship.getWaypoints()) {
                int CcenterX = (waypoint.getPosX() - cameraX) * TILE_SIZE + TILE_SIZE / 2;
                int CcenterY = (waypoint.getPosY() - cameraY) * TILE_SIZE + TILE_SIZE / 2;

                g.fillOval(CcenterX - circleRadius, CcenterY - circleRadius, circleDiameter, circleDiameter);
            }
        }

        for (Ranger ranger : gameMap.getRangers()) {
            int centerX = ranger.getHitbox().x + ranger.getHitbox().width / 2;
            int centerY = ranger.getHitbox().y + ranger.getHitbox().height / 2;
            g.drawImage(tileImages.get(Tile.TileType.RANGER),
                    (centerX - cameraX) * TILE_SIZE - TILE_SIZE / 2, // Középre igazítás
                    (centerY - cameraY) * TILE_SIZE - TILE_SIZE / 2, // Középre igazítás
                    TILE_SIZE, TILE_SIZE, this);
            ranger.drawHitbox(g, cameraX, cameraY, TILE_SIZE);
        }
        // Jeep-ek kirajzolása
        for (Jeep jeep : gameMap.getJeeps()) {
            Coordinate jeepPos = jeep.getPosition();
            g.drawImage(tileImages.get(Tile.TileType.JEEP),
                    (jeepPos.getPosX() - cameraX) * TILE_SIZE,
                    (jeepPos.getPosY() - cameraY) * TILE_SIZE,
                    TILE_SIZE, TILE_SIZE, this);

            // Hitbox kirajzolása
            Rectangle hitbox = jeep.getHitbox();
            g.setColor(new Color(255, 0, 0, 100)); // Átlátszó piros szín
            g.fillRect((hitbox.x - cameraX) * TILE_SIZE,
                    (hitbox.y - cameraY) * TILE_SIZE,
                    hitbox.width * TILE_SIZE,
                    hitbox.height * TILE_SIZE);
        }

    }

    /**
     * A billentyű lenyomásának eseményét kezeli, mozgatja a kamerát.
     * Ez a metódus public, mert a KeyListener interfész része, így a rendszer hívja meg.
     * @param e a billentyű esemény
     */
    @Override
    public void keyPressed(KeyEvent e) {
        boolean moved = false;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> {
                if (cameraX > 0) {
                    cameraX--;
                    moved = true;
                }
            }
            case KeyEvent.VK_D -> {
                if (cameraX < gameMap.getWidth() - VIEWPORT_WIDTH) {
                    cameraX++;
                    moved = true;
                }
            }
            case KeyEvent.VK_W -> {
                if (cameraY > 0) {
                    cameraY--;
                    moved = true;
                }
            }
            case KeyEvent.VK_S -> {
                if (cameraY < gameMap.getHeight() - VIEWPORT_HEIGHT) {
                    cameraY++;
                    moved = true;
                }
            }
        }

        if (moved) {
            repaint(); // Csak akkor rajzoljuk újra, ha ténylegesen mozgott a kamera
        }
    }

    /**
     * A billentyű felengedésének eseményét kezeli.
     * Ez a metódus public, mert a KeyListener interfész része, így a rendszer hívja meg.
     * @param e a billentyű esemény
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * A billentyű leütésének eseményét kezeli.
     * Ez a metódus public, mert a KeyListener interfész része, így a rendszer hívja meg.
     * @param e a billentyű esemény
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Visszaadja a kamera X pozícióját.
     * Ez a metódus public, hogy más osztályok is lekérhessék a kamera pozícióját.
     * @return a kamera X pozíciója
     */
    public int getCameraX() {
        return cameraX;
    }

    /**
     * Beállítja a kamera X pozícióját.
     * Ez a metódus public, hogy más osztályok is módosíthassák a kamera pozícióját.
     * @param cameraX az új X pozíció
     */
    public void setCameraX(int cameraX) {
        this.cameraX = cameraX;
    }

    /**
     * Visszaadja a kamera Y pozícióját.
     * Ez a metódus public, hogy más osztályok is lekérhessék a kamera pozícióját.
     * @return a kamera Y pozíciója
     */
    public int getCameraY() {
        return cameraY;
    }

    /**
     * Beállítja a kamera Y pozícióját.
     * Ez a metódus public, hogy más osztályok is módosíthassák a kamera pozícióját.
     * @param cameraY az új Y pozíció
     */
    public void setCameraY(int cameraY) {
        this.cameraY = cameraY;
    }

    /**
     * Visszaadja a GameMap példányt.
     * Ez a metódus public, hogy más osztályok is lekérhessék a játék térképét.
     * @return a GameMap példány
     */
    public GameMap getGameMap() {
        return gameMap;
    }
}
