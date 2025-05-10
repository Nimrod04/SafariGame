package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * A MiniMap osztály a játékban egy kicsinyített térképet (minitérképet) jelenít meg.
 * Megjeleníti a pálya csempéit és az állatokat arányosan lekicsinyítve.
 * <p>
 * Az osztály public, így más csomagokból és osztályokból is példányosítható és elérhető.
 */
public class MiniMap extends JPanel {
    /** A fő játékpanel példánya, amelyből a térképet és az állatokat lekérdezi. */
    private GamePanel gamePanel;

    /**
     * Létrehoz egy új MiniMap példányt a megadott GamePanel objektummal.
     * Ez a konstruktor public, így más osztályokból is példányosítható.
     * @param gm a fő játékpanel példánya
     */
    public MiniMap(GamePanel gm) {
        this.gamePanel = gm;
        this.setPreferredSize(new Dimension(318, 255)); // Méret a Playing.java alapján
        //loadImages(); // Képek betöltése
    }

    /**
     * A panel kirajzolását végzi, megjeleníti a térképet és az állatokat.
     * Ez a metódus protected, mert a Swing komponensek így használják a felüldefiniált paintComponent-et.
     * @param g a grafikus kontextus
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int mapWidth = gamePanel.getGameMap().getWidth();
        int mapHeight = gamePanel.getGameMap().getHeight();

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Méretarány kiszámítása
        double scaleX = (double) panelWidth / mapWidth;
        double scaleY = (double) panelHeight / mapHeight;

        // Térkép kirajzolása a `map[x][y]` alapján
        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                Tile tile = gamePanel.getGameMap().getTile(x, y); // A `map[x][y]` használata
                Image tileImage = gamePanel.getTileImages().get(tile.getType());

                if (tileImage != null) {
                    // Csempék kirajzolása méretarányosan
                    int rectX = (int) (x * scaleX);
                    int rectY = (int) (y * scaleY);
                    int rectWidth = (int) Math.ceil(scaleX);
                    int rectHeight = (int) Math.ceil(scaleY);

                    g.drawImage(tileImage, rectX, rectY, rectWidth, rectHeight, null);
                }
            }
        }

        // Állatok kirajzolása
        for (Elephant e : gamePanel.getGameMap().elephants) {
            drawAnimal(g, e.getCoordinate().getPosX(), e.getCoordinate().getPosY(), "/images/elephant.png", scaleX, scaleY);
        }
        for (Gazelle gzl : gamePanel.getGameMap().gazelles) {
            drawAnimal(g, gzl.getCoordinate().getPosX(), gzl.getCoordinate().getPosY(), "/images/gazelle.png", scaleX, scaleY);
        }
        for (Lion l : gamePanel.getGameMap().lions) {
            drawAnimal(g, l.getCoordinate().getPosX(), l.getCoordinate().getPosY(), "/images/lion.png", scaleX, scaleY);
        }
        for (Cheetah c : gamePanel.getGameMap().cheetahs) {
            drawAnimal(g, c.getCoordinate().getPosX(), c.getCoordinate().getPosY(), "/images/cheetah.png", scaleX, scaleY);
        }
    }

    /**
     * Egy állat képének kirajzolását végzi a minitérképen.
     * @param g a grafikus kontextus
     * @param x az állat X koordinátája a térképen
     * @param y az állat Y koordinátája a térképen
     * @param imagePath az állat képének elérési útja
     * @param scaleX a térkép X irányú méretaránya
     * @param scaleY a térkép Y irányú méretaránya
     */
    private void drawAnimal(Graphics g, int x, int y, String imagePath, double scaleX, double scaleY) {
        Image animalImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
        if (animalImage != null) {
            int rectX = (int) (x * scaleX);
            int rectY = (int) (y * scaleY);
            int rectWidth = (int) Math.ceil(scaleX);
            int rectHeight = (int) Math.ceil(scaleY);

            g.drawImage(animalImage, rectX, rectY, rectWidth, rectHeight, null);
        }
    }

    /**
     * Frissíti a minitérképet, újrarajzolja a térképet és az állatokat.
     * Ez a metódus public, hogy más osztályokból is meghívható legyen.
     */
    public void refresh() {
        gamePanel.renderMap();
        repaint();

        // Újrarajzolás
    }
}