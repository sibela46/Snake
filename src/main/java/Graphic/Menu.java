package Graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Menu extends JPanel implements ActionListener {
    public int width = 640, height = 480;
    public boolean started = false;
    public JButton play = new JButton("PLAY");

    public Menu(){
        setSize(width, height);
        addButtons();
        setVisible(true);
    }

    public void addButtons(){
        Font newFont = new Font("Krona One", Font.PLAIN, 30);
        play.setBounds(240, 300, 150, 50);
        play.setFont(newFont);
        java.awt.Color tomato = java.awt.Color.decode("#FF3C38");
        play.setBackground(tomato);
        play.addActionListener(this);

        //game buttons
        setLayout(null);
        add(play);

    }

    public void paint(Graphics g){
        super.paint(g);
        URL imagePath = this.getClass().getClassLoader().getResource("startImage.jpg");
        g.drawImage(new ImageIcon(imagePath).getImage(), 0, 0, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        started = true;
    }
}
