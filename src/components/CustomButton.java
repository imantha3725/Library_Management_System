/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.RoundRectangle2D;

public class CustomButton extends JButton {
    private Color buttonColor = new Color(62, 106, 255);
    private int borderRadius = 7;
    private boolean isHovered = false;
    private String buttonText = "Button";
    
    // No-args constructor required for GUI builder
    public CustomButton() {
        super();
        setupButton();
    }
    
    // Constructor with text parameter
    public CustomButton(String text) {
        super(text);
        this.buttonText = text;
        setupButton();
    }
    
    private void setupButton() {
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setText(buttonText.toUpperCase());
        setFont(getFont().deriveFont(Font.BOLD, 14f));
        setForeground(Color.WHITE);
        
        // Set default size
        setPreferredSize(new Dimension(120, 40));
        
        // Add hover effect
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                repaint();
            }
        });
    }
    
    // Bean properties for GUI builder
    public Color getButtonColor() {
        return buttonColor;
    }
    
    public void setButtonColor(Color buttonColor) {
        this.buttonColor = buttonColor;
        repaint();
    }
    
    public int getBorderRadius() {
        return borderRadius;
    }
    
    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
        repaint();
    }
    
    @Override
    public void setText(String text) {
        super.setText(text.toUpperCase());
        this.buttonText = text;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Calculate the shape
        RoundRectangle2D.Float shape = new RoundRectangle2D.Float(
            1, 1, getWidth() - 2, getHeight() - 2, borderRadius * 2, borderRadius * 2);
        
        // Draw button background
        if (getModel().isPressed()) {
            g2.setColor(buttonColor.darker());
        } else if (isHovered) {
            g2.setColor(buttonColor.brighter());
        } else {
            g2.setColor(new Color(0, 0, 0, 0)); // Transparent
        }
        g2.fill(shape);
        
        // Draw border
        g2.setStroke(new BasicStroke(1f));
        g2.setColor(buttonColor);
        g2.draw(shape);
        
        // Create a subtle shadow effect
        if (!getModel().isPressed() && isHovered) {
            g2.setColor(new Color(62, 106, 255, 50));
            g2.setStroke(new BasicStroke(2f));
            g2.draw(shape);
        }
        
        g2.dispose();
        
        // Paint the text
        super.paintComponent(g);
    }
}