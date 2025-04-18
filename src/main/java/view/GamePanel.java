package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GamePanel extends JPanel implements KeyListener {

    private final GameMap gameMap;
    private Playing playing;
    private final Map<Tile.TileType, Image> tileImages = new HashMap<>();

    public static final int TILE_SIZE = 64;
    public static final int VIEWPORT_WIDTH = 20, VIEWPORT_HEIGHT = 10;
    private int cameraX = 0, cameraY = 0;

    private BufferedImage mapImage; // Gyorsítótárazott térkép kép
    private Timer gameTimer;

    public GamePanel(GameMap gameMap, Playing p) {
        this.playing = p;
        this.gameMap = gameMap;
        this.playing = playing;
        loadImages();
        this.setPreferredSize(new Dimension(VIEWPORT_WIDTH * TILE_SIZE, VIEWPORT_HEIGHT * TILE_SIZE));
        this.setFocusable(true);
        addKeyListener(this);
        this.requestFocusInWindow();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (playing != null && playing.isInRoadShop()) {
                    int tileX = (e.getX() / TILE_SIZE) + cameraX;
                    int tileY = (e.getY() / TILE_SIZE) + cameraY;
                    if (tileX >= 0 && tileX < gameMap.getWidth() && tileY >= 0 && tileY < gameMap.getHeight()) {
                        if (gameMap.getTile(tileX, tileY).getType() == Tile.TileType.SAND) {
                            gameMap.setTile(tileX, tileY, Tile.TileType.ROAD);
                            renderMap();
                            repaint();
                            System.out.println("Road built at: " + tileX + ", " + tileY);
                        }

                    }
                }
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
                if (playing != null /*&& playing.isInRoadShop()*/ && playing.isBuildingRoad()) {
                    int tileX = (e.getX() / TILE_SIZE) + cameraX;
                    int tileY = (e.getY() / TILE_SIZE) + cameraY;

                    if (tileX >= 0 && tileX < gameMap.getWidth() && tileY >= 0 && tileY < gameMap.getHeight()) {
                        if (gameMap.getTile(tileX, tileY).getType() == Tile.TileType.SAND) {
                            if (playing.getFinance().getBalance() >= Road.PRICE) {
                                gameMap.setTile(tileX, tileY, Tile.TileType.ROAD);
                                playing.getFinance().decrease(Road.PRICE);
                                playing.refreshBalance();
                                renderMap();
                                repaint();
                                System.out.println("Road built at: " + tileX + ", " + tileY);
                            } else {
                                System.out.println("Not enough money!");
                            }
                        }
                    }
                }
                //Airship

                if (playing != null && playing.isBuildingAirship()) {
                    int tileX = (e.getX() / TILE_SIZE) + cameraX;
                    int tileY = (e.getY() / TILE_SIZE) + cameraY;
                    if (tileX >= 0 && tileX < gameMap.getWidth() && tileY >= 0 && tileY < gameMap.getHeight()) {
                        if (gameMap.getTile(tileX, tileY).getType() == Tile.TileType.SAND) {
                            if (playing.getFinance().getBalance() >= Airship.PRICE) {
                                gameMap.addAirship(new Airship(new Coordinate(tileX, tileY)));
                                //gameMap.setTile(tileX, tileY, Tile.TileType.AIRSHIP);
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
                // Ellenőrizzük, hogy van-e aktív Airship, és ha igen, akkor hozzáadunk egy waypointot
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
                for (Airship airship : gameMap.getAirships()) {
                    int tileX = (e.getX() / TILE_SIZE) + cameraX;
                    int tileY = (e.getY() / TILE_SIZE) + cameraY;

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

                //Camera
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

                //Charging station
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

                //Drone
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
                                gameMap.addDrone(new Drone(new Coordinate(tileX, tileY), freeStation));
                                //gameMap.setTile(tileX, tileY, Tile.TileType.DRONE);
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

                //Delete
                if (SwingUtilities.isRightMouseButton(e)) {
                    ArrayList<Tile.TileType> toDelete = new ArrayList<>();
                    toDelete.add(Tile.TileType.ROAD);
                    toDelete.add(Tile.TileType.CAMERA);
                    toDelete.add(Tile.TileType.CHARGINGSTATION);

                    int tileX = (e.getX() / TILE_SIZE) + cameraX;
                    int tileY = (e.getY() / TILE_SIZE) + cameraY;
                    if (toDelete.contains(gameMap.getTile(tileX, tileY).getType())) {

                        if (gameMap.getTile(tileX, tileY).getType() == Tile.TileType.CAMERA) {
                            gameMap.getCameras().removeIf(camera
                                    -> camera.getPosition().getPosX() == tileX && camera.getPosition().getPosY() == tileY
                            );
                        }

                        if (gameMap.getTile(tileX, tileY).getType() == Tile.TileType.CHARGINGSTATION) {
                            gameMap.getCameras().removeIf(charging
                                    -> charging.getPosition().getPosX() == tileX && charging.getPosition().getPosY() == tileY
                            );
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
        gameTimer = new Timer(100, e -> {
            gameMap.updateAnimals(); // Állatok frissítése
            repaint(); // Képernyő újrarajzolása
        });

        gameTimer.start();
    }

    public Map<Tile.TileType, Image> getTileImages() {
        return tileImages;
    }

    public void renderMap() {
        mapImage = new BufferedImage(gameMap.getWidth() * TILE_SIZE, gameMap.getHeight() * TILE_SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics g = mapImage.getGraphics();

        for (int y = 0; y < gameMap.getHeight(); y++) {
            for (int x = 0; x < gameMap.getWidth(); x++) {
                Tile tile = gameMap.getTile(x, y);
                g.drawImage(tileImages.get(Tile.TileType.SAND), x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
                g.drawImage(tileImages.get(tile.getType()), x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
            }
        }

        for (Elephant e : gameMap.elephants) {

        }

        g.dispose();
    }

    private void loadImages() {
        try {
            tileImages.put(Tile.TileType.ROAD, new ImageIcon(getClass().getResource("/images/dirt.png")).getImage());
            tileImages.put(Tile.TileType.GRASS, new ImageIcon(getClass().getResource("/images/grass.png")).getImage());
            tileImages.put(Tile.TileType.WATER, new ImageIcon(getClass().getResource("/images/water.png")).getImage());
            tileImages.put(Tile.TileType.TREE, new ImageIcon(getClass().getResource("/images/tree.png")).getImage());
            tileImages.put(Tile.TileType.BUSH, new ImageIcon(getClass().getResource("/images/bush.png")).getImage());
            tileImages.put(Tile.TileType.DIRT, new ImageIcon(getClass().getResource("/images/dirt.png")).getImage());
            tileImages.put(Tile.TileType.SAND, new ImageIcon(getClass().getResource("/images/sand.png")).getImage());
            tileImages.put(Tile.TileType.GAZELLE, new ImageIcon(getClass().getResource("/images/gazelle.png")).getImage());
            tileImages.put(Tile.TileType.ELEPHANT, new ImageIcon(getClass().getResource("/images/elephant.png")).getImage());
            tileImages.put(Tile.TileType.LION, new ImageIcon(getClass().getResource("/images/lion.png")).getImage());
            tileImages.put(Tile.TileType.CHEETAH, new ImageIcon(getClass().getResource("/images/cheetah.png")).getImage());
            tileImages.put(Tile.TileType.GATE, new ImageIcon(getClass().getResource("/images/gate.png")).getImage());
            tileImages.put(Tile.TileType.ROAD, new ImageIcon(getClass().getResource("/images/dirt.png")).getImage());

            tileImages.put(Tile.TileType.CAMERA, new ImageIcon(getClass().getResource("/images/camera.png")).getImage());
            tileImages.put(Tile.TileType.CHARGINGSTATION, new ImageIcon(getClass().getResource("/images/charging_station.png")).getImage());
            tileImages.put(Tile.TileType.DRONE, new ImageIcon(getClass().getResource("/images/drone_up.png")).getImage());
            tileImages.put(Tile.TileType.AIRSHIP, new ImageIcon(getClass().getResource("/images/airship.png")).getImage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
            //System.out.println("hello  GamePanel(93)");
        }
        for (Gazelle e : gameMap.gazelles) {
            g.drawImage(tileImages.get(Tile.TileType.GAZELLE),
                    e.getCoordinate().getPosX() - cameraX * TILE_SIZE,
                    e.getCoordinate().getPosY() - cameraY * TILE_SIZE,
                    TILE_SIZE, TILE_SIZE, this);
        }
        for (Lion e : gameMap.lions) {
            g.drawImage(tileImages.get(Tile.TileType.LION),
                    e.getCoordinate().getPosX() - cameraX * TILE_SIZE,
                    e.getCoordinate().getPosY() - cameraY * TILE_SIZE,
                    TILE_SIZE, TILE_SIZE, this);
        }
        for (Cheetah e : gameMap.cheetahs) {
            g.drawImage(tileImages.get(Tile.TileType.CHEETAH),
                    e.getCoordinate().getPosX() - cameraX * TILE_SIZE,
                    e.getCoordinate().getPosY() - cameraY * TILE_SIZE,
                    TILE_SIZE, TILE_SIZE, this);
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
    }

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

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public int getCameraX() {
        return cameraX;
    }

    public void setCameraX(int cameraX) {
        this.cameraX = cameraX;
    }

    public int getCameraY() {
        return cameraY;
    }

    public void setCameraY(int cameraY) {
        this.cameraY = cameraY;
    }

    public GameMap getGameMap() {
        return this.gameMap;
    }
}
