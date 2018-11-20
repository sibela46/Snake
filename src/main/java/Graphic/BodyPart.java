package Graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.net.URL;

public class BodyPart {
    public int x, y, width, height;
    public Image snakeHead;

    public BodyPart (int x, int y){
        this.x = x;
        this.y = y;
        this.width = 20;
        this.height = 20;
        URL imagePath = this.getClass().getClassLoader().getResource("snake_head.png");
        snakeHead =  new ImageIcon(imagePath).getImage();
    }

    public Rectangle bounds() {
        return (new Rectangle(this.x, this.y, this.width-5, this.height-5));
    }

    public void draw (Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        java.awt.Color weldonBlue = java.awt.Color.decode("#779FA1");
        Ellipse2D r = new Ellipse2D.Double(this.x, this.y, this.width, this.height);
        g2.setColor(weldonBlue);
        g2.fill(r);
        g2.setBackground(null);
    }

    public void drawHead (Graphics g, int direction){
        switch (direction){
            case 0:
                URL imagePathUp = this.getClass().getClassLoader().getResource("snake_head_up.png");
                snakeHead =  new ImageIcon(imagePathUp).getImage();
                break;
            case 1:
                URL imagePathDown = this.getClass().getClassLoader().getResource("snake_head_down.png");
                snakeHead =  new ImageIcon(imagePathDown).getImage();
                break;
            case 2:
                URL imagePathLeft = this.getClass().getClassLoader().getResource("snake_head_left.png");
                snakeHead =  new ImageIcon(imagePathLeft).getImage();
                break;
            case 3:
                URL imagePathRight = this.getClass().getClassLoader().getResource("snake_head.png");
                snakeHead =  new ImageIcon(imagePathRight).getImage();
                break;
        }
        g.drawImage(snakeHead, this.x, this.y, null);
    }
}
