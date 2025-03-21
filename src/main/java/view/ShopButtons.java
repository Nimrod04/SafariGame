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
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JButton;

/**
 *
 * @author nimro
 */
public class ShopButtons extends JButton{
    private Image image; 
    private String iconPath = ""; 
    private int borderThickness = 1; // Default border thickness

    public ShopButtons() {
        setPreferredSize(new Dimension(100, 100)); // Default size
        setContentAreaFilled(false); // Make the content area transparent
        setBorderPainted(false); // Remove default border
    }

    // Getter and Setter for NetBeans GUI Builder
    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
        loadIcon(); 
        repaint();
    }

    public int getBorderThickness() {
        return borderThickness;
    }

    public void setBorderThickness(int borderThickness) {
        this.borderThickness = borderThickness;
        repaint();
    }

    private void loadIcon() {
        if (iconPath == null || iconPath.isEmpty()) return;
        try {
            BufferedImage original = ImageIO.read(new File(iconPath));
            image = original; // Store the full image
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
        g2.setClip(new Rectangle2D.Float(0, 0, panelWidth, panelHeight));
        g2.drawImage(bufferedImage, 0, 0, this);

        // Draw border using the borderThickness property
        g2.setClip(null);
        g2.setStroke(new BasicStroke(borderThickness));
        g2.setColor(Color.BLACK);
        g2.drawRect(borderThickness / 2, borderThickness / 2, panelWidth - borderThickness, panelHeight - borderThickness);
    }
}
