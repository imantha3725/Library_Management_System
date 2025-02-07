/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Components;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class RoundedImageComponent extends JComponent {

    private Icon image;
    private int cornerRadius = 30; // Adjust for rounded corners

    public Icon getImage() {
        return image;
    }

    public void setImage(Icon image) {
        this.image = image;
        repaint();
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Get auto-sized image bounds
            Rectangle size = getAutoSize(image);

            // Create a rounded rectangle mask
            BufferedImage masked = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = masked.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw rounded rectangle
            g2d.setComposite(AlphaComposite.Clear);
            g2d.fillRect(0, 0, size.width, size.height);
            g2d.setComposite(AlphaComposite.Src);
            g2d.setColor(Color.WHITE);
            g2d.fill(new RoundRectangle2D.Double(0, 0, size.width, size.height, cornerRadius, cornerRadius));
            g2d.setComposite(AlphaComposite.SrcAtop);
            g2d.drawImage(toImage(image), 0, 0, size.width, size.height, null);
            g2d.dispose();

            g2.drawImage(masked, size.x, size.y, size.width, size.height, null);
            g2.dispose();
        }
    }

    private Rectangle getAutoSize(Icon image) {
        int w = getWidth();
        int h = getHeight();
        int iw = image.getIconWidth();
        int ih = image.getIconHeight();
        double xScale = (double) w / iw;
        double yScale = (double) h / ih;
        double scale = Math.min(xScale, yScale);
        int width = (int) (scale * iw);
        int height = (int) (scale * ih);
        int x = (w - width) / 2;
        int y = (h - height) / 2;
        return new Rectangle(new Point(x, y), new Dimension(width, height));
    }

    private Image toImage(Icon icon) {
        if (icon instanceof ImageIcon) {
            return ((ImageIcon) icon).getImage();
        }
        return null;
    }
}

