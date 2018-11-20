package Graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.LinkedList;
import java.util.Random;

public class Game extends JPanel implements ActionListener, KeyListener{

    Snake snake = new Snake();
    Eatable food = new Eatable();

    private Timer timer = new Timer(20, this);
    private Random rand = new Random();
    public static int height = 480, width = 640;
    public int ticks = 0;
    public static JFrame frame = new JFrame("Snake by S.C.");
    public static Game game = new Game();
    public static Menu menu = new Menu();
    public GameOver endScreen = new GameOver();

    private enum STATE {
        MENU,
        GAME
    };

    private static STATE State = STATE.MENU;

    public Game(){
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        addKeyListener(this);
        loadFonts();
    }

    public static void main(String[] args) {
        frame.setSize(width,height);
        frame.getContentPane().add(menu);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);

        while(State == STATE.MENU){
            if(menu.started) {
                State = STATE.GAME;
            }
            else {
                State = STATE.MENU;
            }
        }

        frame.remove(menu);
        frame.getContentPane().remove(menu);
        frame.getContentPane().add(game);
        game.requestFocus();
        game.timer.start();
        frame.repaint();
        frame.revalidate();
    }

    public static void loadFonts(){
        Fonts.addFont(new Fonts("Roboto-Light.ttf"));
        Fonts.addFont(new Fonts("KronaOne-Regular.ttf"));
    }



    public void paintComponent(Graphics g){

        super.paintComponent(g);
        if(State == STATE.GAME) {
            //sets background
            URL imagePath = this.getClass().getClassLoader().getResource("background.jpg");
            g.drawImage(new ImageIcon(imagePath).getImage(), 0, 0, null);

            //paints the food
            food.draw(g);

            LinkedList<BodyPart> body = snake.getBodyParts();

            body.getFirst().drawHead(g, snake.direction);

            //paints the snake
            for (int i = 1; i < body.size(); i++) {
                BodyPart part = body.get(i);
                part.draw(g);
            }
        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(State == STATE.GAME) {
            timer.start();
            ticks++;
            BodyPart head = snake.getHead();

            head.x += snake.moveX;
            head.y += snake.moveY;
            snake.x = head.x;
            snake.y = head.y;

            if(snake.checkIfGameOver()){
                timer.stop();
                frame.remove(game);
                frame.getContentPane().add(endScreen);
                setClickEvents();
                frame.revalidate();
                repaint();
            }
            if(ticks%5 == 0){
                snake.movement();
            }
            snake.endOfMap(width, height);

            if (snake.collision(food)) {
                snake.grow();
                int nextX = rand.nextInt(width - (food.width + 30));
                int nextY = rand.nextInt(height - (food.height + 30));
                if (nextX != snake.x && nextY != snake.y){
                    food.x = nextX;
                    food.y = nextY;
                }
            }


            repaint();
        }
    }

    public void setClickEvents(){
        endScreen.quit.addActionListener(e -> frame.dispose());
        endScreen.replay.addActionListener(e -> {
            frame.remove(endScreen);
            snake.gameIsOver = false;
            game = new Game();
            frame.getContentPane().add(game);
            game.requestFocus();
            game.timer.restart();
            frame.repaint();
            frame.revalidate();
        });
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (State == STATE.GAME) {
            int code = e.getKeyCode();
            if (code == KeyEvent.VK_UP && !snake.down) {
                snake.up();
                snake.up = true;
                snake.left = false;
                snake.right = false;
            }
            if (code == KeyEvent.VK_DOWN && !snake.up) {
                snake.down();
                snake.down = true;
                snake.left = false;
                snake.right = false;
            }
            if (code == KeyEvent.VK_LEFT && !snake.right) {
                snake.left();
                snake.left = true;
                snake.up = false;
                snake.down = false;
            }
            if (code == KeyEvent.VK_RIGHT && !snake.left) {
                snake.right();
                snake.right = true;
                snake.up = false;
                snake.down = false;
            }
        }
    }
}
