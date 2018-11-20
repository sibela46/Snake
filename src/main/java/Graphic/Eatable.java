package Graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.net.URL;

public class Eatable extends JPanel {
    public int x;
    public int y;
    public int width;
    public int height;

    public Eatable(){
        this.x = 250;
        this.y = 300;
        this.width = 10;
        this.height = 10;
    }

    public Rectangle foodBounds() {
        return (new Rectangle(this.x, this.y, this.width, this.height));
    }

    public void draw(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        java.awt.Color tomato = java.awt.Color.decode("#FF3C38");
        Ellipse2D e = new Ellipse2D.Double(this.x, this.y, width, height);
        g2.setColor(tomato);
        g2.fill(e);
        g2.setBackground(null);
    }
}
