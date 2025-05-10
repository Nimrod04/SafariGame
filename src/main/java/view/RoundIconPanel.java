package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * A RoundIconPanel osztály egy kör alakú képet megjelenítő panel.
 * Leszármazottja a JPanel osztálynak, így grafikus felületen használható komponensként.
 * <p>
 * Az osztály public, így más csomagokból és osztályokból is példányosítható és elérhető.
 */
public class RoundIconPanel extends JPanel {

    /** A panelen megjelenítendő kép. */
    private Image image;

    /** Az ikon elérési útja, amelyet a panel megjelenít. */
    private String iconPath = "";

    /**
     * Alapértelmezett konstruktor a RoundIconPanel osztályhoz.
     * Beállítja az alapértelmezett méretet.
     */
    public RoundIconPanel() {
        setPreferredSize(new Dimension(100, 100)); // Default size
    }

    /**
     * Visszaadja az ikon elérési útját.
     * @return az ikon elérési útja
     */
    public String getIconPath() {
        return iconPath;
    }

    /**
     * Beállítja az ikon elérési útját, betölti a képet és újrarajzolja a panelt.
     * @param iconPath az ikon elérési útja
     */
    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
        loadIcon();
        repaint();
    }

    /**
     * Betölti az ikon képet a megadott elérési út alapján.
     * Ha az elérési út üres vagy hibás, nem történik semmi.
     */
    private void loadIcon() {
        if (iconPath == null || iconPath.isEmpty()) {
            return;
        }
        try {
            BufferedImage original = ImageIO.read(new File(iconPath));
            image = original; // Store the full image
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * A panel kirajzolását végzi, kör alakban jeleníti meg a képet, fekete szegéllyel.
     * @param g a grafikus kontextus, amelyre rajzolni kell
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image == null) {
            return;
        }

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Ensure the image is scaled to fit the panel size
        Image scaledImage = image.getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create circular clipping area
        g2.setClip(new Ellipse2D.Float(0, 0, panelWidth, panelHeight));
        g2.drawImage(scaledImage, 0, 0, this);

        // Draw black border
        g2.setClip(null);
        g2.setStroke(new BasicStroke(1));
        g2.setColor(Color.BLACK);
        g2.drawOval(0, 0, panelWidth - 1, panelHeight - 1);
    }
}




