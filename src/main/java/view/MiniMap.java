package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MiniMap extends JPanel {
    private GamePanel gamePanel;
    public MiniMap(GamePanel gm) {
        this.gamePanel = gm;
        this.setPreferredSize(new Dimension(318, 255)); // Méret a Playing.java alapján
        //loadImages(); // Képek betöltése
    }
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
    

    public void refresh() {
        gamePanel.renderMap();
        repaint();

        // Újrarajzolás
    }
}