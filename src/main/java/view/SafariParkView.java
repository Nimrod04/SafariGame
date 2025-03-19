package view;

import model.Path;
import model.SafariPark;

import javax.swing.*;
import java.awt.*;

    public class SafariParkView extends JPanel {
        private SafariPark safariPark;
        private static final int TILE_SIZE = 50; // Egy cella mérete

        public SafariParkView(SafariPark safariPark) {
            this.safariPark = safariPark;
            this.setPreferredSize(new Dimension(TILE_SIZE * 10, TILE_SIZE * 10));
        }

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
}
