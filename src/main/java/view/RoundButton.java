/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JButton;

/**
 * A RoundButton osztály egy kör alakú gombot reprezentál, amely képet és egyedi szegélyt jelenít meg.
 * Leszármazottja a JButton osztálynak, így grafikus felületen használható komponensként.
 * <p>
 * Az osztály public, így más csomagokból és osztályokból is példányosítható és elérhető.
 *
 * @author nimro
 */
public class RoundButton extends JButton{
    /** A gombon megjelenítendő kép. */
    private Image image; 

    /** Az ikon elérési útja, amelyet a gomb megjelenít. */
    private String iconPath = ""; 

    /** A gomb szegélyének vastagsága. */
    private int borderThickness = 1; // Default border thickness

    /**
     * Alapértelmezett konstruktor a RoundButton osztályhoz.
     * Beállítja az alapértelmezett méretet, átlátszóvá teszi a tartalmi területet és eltávolítja az alapértelmezett szegélyt.
     */
    public RoundButton() {
        setPreferredSize(new Dimension(100, 100)); // Default size
        setContentAreaFilled(false); // Make the content area transparent
        setBorderPainted(false); // Remove default border
    }

    /**
     * Visszaadja az ikon elérési útját.
     * @return az ikon elérési útja
     */
    public String getIconPath() {
        return iconPath;
    }

    /**
     * Beállítja az ikon elérési útját, betölti a képet és újrarajzolja a gombot.
     * @param iconPath az ikon elérési útja
     */
    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
        loadIcon(); 
        repaint();
    }

    /**
     * Visszaadja a gomb szegélyének vastagságát.
     * @return a szegély vastagsága
     */
    public int getBorderThickness() {
        return borderThickness;
    }

    /**
     * Beállítja a gomb szegélyének vastagságát és újrarajzolja a gombot.
     * @param borderThickness az új szegélyvastagság
     */
    public void setBorderThickness(int borderThickness) {
        this.borderThickness = borderThickness;
        repaint();
    }

    /**
     * Betölti az ikon képet a megadott elérési út alapján.
     * Ha az elérési út üres vagy hibás, nem történik semmi.
     */
    private void loadIcon() {
if (iconPath == null || iconPath.isEmpty()) return;
    
    try {
        // 1. Próbáld meg resource-ként betölteni (.jar fájlból)
        InputStream inputStream = getClass().getResourceAsStream("/" + iconPath);
        if (inputStream != null) {
            BufferedImage original = ImageIO.read(inputStream);
            image = original;
            return;
        }
        
        // 2. Ha nem található resource-ként, próbáld meg fájlrendszerből (IDE-ből)
        File file = new File(iconPath);
        if (file.exists()) {
            BufferedImage original = ImageIO.read(file);
            image = original;
        } else {
            System.err.println("Nem található a kép: " + iconPath);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    /**
     * A gomb komponensének kirajzolását végzi, beleértve a képet és a kör alakú szegélyt.
     * @param g a grafikus kontextus, amelyre rajzolni kell
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image == null) return;

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Scale the image with better quality (BICUBIC interpolation)
        Image scaledImage = image.getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);

        // Convert the image to a BufferedImage for better rendering control
        BufferedImage bufferedImage = new BufferedImage(panelWidth, panelHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC); // High-quality scaling
        g2d.drawImage(scaledImage, 0, 0, null);
        g2d.dispose();

        // Create Graphics2D object for drawing
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create circular clipping area
        g2.setClip(new Ellipse2D.Float(0, 0, panelWidth, panelHeight));
        g2.drawImage(bufferedImage, 0, 0, this);

        // Draw border using the borderThickness property
        g2.setClip(null);
        g2.setStroke(new BasicStroke(borderThickness));
        g2.setColor(Color.BLACK);
        g2.drawOval(borderThickness / 2, borderThickness / 2, panelWidth - borderThickness, panelHeight - borderThickness);
    }

    /**
     * Meghatározza, hogy a megadott pont a kör alakú gomb területén belül van-e.
     * @param p a vizsgálandó pont
     * @return true, ha a pont a körön belül van, különben false
     */
    @Override
    public boolean contains(Point p) {
        // Override this to make sure the button behaves like a circular button
        int x = p.x;
        int y = p.y;
        return new Ellipse2D.Float(0, 0, getWidth(), getHeight()).contains(x, y);
    }
    
}
