package view;

import model.Path;
import model.SafariPark;

import javax.swing.*;
import java.awt.*;

/**
 * A SafariParkView osztály a szafari park grafikus megjelenítéséért felelős panel.
 * Kirajzolja a park térképét a megadott SafariPark példány alapján.
 * <p>
 * Az osztály public, így más csomagokból és osztályokból is példányosítható és elérhető.
 */
public class SafariParkView extends JPanel {
    /** A megjelenítendő SafariPark példány. */
    private SafariPark safariPark;
    /** Egy cella mérete pixelben. */
    private static final int TILE_SIZE = 50; // Egy cella mérete

    /**
     * Létrehoz egy új SafariParkView példányt a megadott SafariPark objektummal.
     * Ez a konstruktor public, így más osztályokból is példányosítható.
     * @param safariPark a megjelenítendő szafari park
     */
    public SafariParkView(SafariPark safariPark) {
        this.safariPark = safariPark;
        this.setPreferredSize(new Dimension(TILE_SIZE * 10, TILE_SIZE * 10));
    }

    /**
     * A panel kirajzolását végzi, megjeleníti a park térképét.
     * Ez a metódus protected, mert a Swing komponensek így használják a felüldefiniált paintComponent-et.
     * @param g a grafikus kontextus
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Path[][] mapGrid = safariPark.getMapGrid();

        for (int x = 0; x < mapGrid.length; x++) {
            for (int y = 0; y < mapGrid[x].length; y++) {
                Path path = mapGrid[x][y];
                if (path != null) {
                    ImageIcon icon = new ImageIcon(getClass().getResource(path.getImagePath()));
                    g.drawImage(icon.getImage(), x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
                }
            }
        }
    }
}
