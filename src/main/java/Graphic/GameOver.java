package Graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

public class GameOver extends JPanel {
    public int width = 800, height = 600;
    public JButton quit = new JButton("QUIT");
    public JButton replay = new JButton("PLAY AGAIN");

    public GameOver(){
        setSize(width, height);
        setLayout(null);
        paintButtons();
        setVisible(true);
    }

    public void paintButtons(){

        Font newFont = new Font("Krona One", Font.PLAIN, 30);
        java.awt.Color tomato = java.awt.Color.decode("#FF3C38");

        quit.setBackground(tomato);
        quit.setBounds(230, 280, 152, 50);
        quit.setFont(newFont);
        add(quit);

        replay.setBackground(tomato);
        replay.setBounds(150, 210, 300, 50);
        replay.setFont(newFont);
        add(replay);

        setLayout(new BorderLayout());
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        URL backImagePath = this.getClass().getClassLoader().getResource("background.jpg");
        Image background = new ImageIcon(backImagePath).getImage();
        g.drawImage(background, 0, 0, null);

        URL endImagePath = this.getClass().getClassLoader().getResource("gameOver.png");
        Image endImage = new ImageIcon(endImagePath).getImage();
        int imgWidth = endImage.getWidth(null) + 180;
        int imgHeight = endImage.getHeight(null) + 300;
        g.drawImage(endImage, (width - imgWidth) / 2, (height - imgHeight) / 2, null);
    }

}
