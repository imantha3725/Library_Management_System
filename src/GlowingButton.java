/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GlowingButton extends JButton {
    private Color defaultBorderColor = new Color(61, 106, 255);
    private Color hoverBackgroundColor = new Color(61, 106, 255);
    private Color textColor = Color.WHITE;
    private int shadowSize = 20;
    
    public GlowingButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(textColor);
        setFont(new Font("Arial", Font.BOLD, 14));
        setPreferredSize(new Dimension(150, 40));
        setBorder(BorderFactory.createLineBorder(defaultBorderColor));
        setOpaque(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                repaint();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                repaint();
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        if (getModel().isRollover()) {
            g2.setColor(hoverBackgroundColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            g2.setColor(new Color(0, 142, 236, 150));
            for (int i = 0; i < shadowSize; i++) {
                g2.drawRoundRect(i, i, getWidth() - i * 2, getHeight() - i * 2, 10, 10);
            }
        } else {
            g2.setColor(new Color(0, 0, 0, 0));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        }

        g2.setColor(defaultBorderColor);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
        
        super.paintComponent(g);
        g2.dispose();
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Glowing Button Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new FlowLayout());
        
        GlowingButton button = new GlowingButton("Click Me");
        frame.add(button);
        
        frame.setVisible(true);
    }
}

