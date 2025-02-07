/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GradientButtonComponent extends JButton {
    private Color baseColor = new Color(61, 106, 255);
    private Color hoverColor = new Color(0, 142, 236);
    private boolean hovering = false;

    public GradientButtonComponent(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 14));
        setMargin(new Insets(10, 20, 10, 20));
        setOpaque(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hovering = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovering = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (hovering) {
            g2.setColor(hoverColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            g2.setColor(new Color(hoverColor.getRed(), hoverColor.getGreen(), hoverColor.getBlue(), 180));
            g2.setStroke(new BasicStroke(5));
            g2.drawRoundRect(2, 2, getWidth() - 5, getHeight() - 5, 10, 10);
        } else {
            g2.setColor(baseColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        }
        
        g2.setColor(Color.WHITE);
        FontMetrics fm = g2.getFontMetrics();
        int textX = (getWidth() - fm.stringWidth(getText())) / 2;
        int textY = (getHeight() + fm.getAscent()) / 2 - 2;
        g2.drawString(getText(), textX, textY);
        
        g2.dispose();
        super.paintComponent(g);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Glowing Button Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLayout(new FlowLayout());

            GlowingButton button = new GlowingButton("Click Me");
            frame.add(button);

            frame.setVisible(true);
        });
    }
}


