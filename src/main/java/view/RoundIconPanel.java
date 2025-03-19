package View;



import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class RoundIconPanel extends JPanel {

    private Image image;
    private String iconPath = "";

    public RoundIconPanel() {
        setPreferredSize(new Dimension(100, 100)); // Default size
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




