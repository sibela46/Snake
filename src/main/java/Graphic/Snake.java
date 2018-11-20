package Graphic;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;


public class Snake extends JPanel {
    public double moveX, moveY;
    public int x, y;
    public int direction = 3;
    public boolean gameIsOver = false;
    public boolean up = false, down = false, left = false, right = false;
    public double speed = 1;
    public int bodyDistance = 15;
    public int size = 3;
    LinkedList<BodyPart> body = new LinkedList<>();

    public Snake() {
        this.moveX = 0;
        this.moveY = 0;
        this.x = 100;
        this.y = 100;

        BodyPart head = new BodyPart(this.x, this.y);
        body.add(head);

        for (int i=1; i<size+1; i++){
            body.add(new BodyPart(this.x - i*head.width, this.y));
        }
    }

    public void movement(){
        BodyPart head = this.getHead();
        if (right){
            body.removeLast();
            BodyPart newPart = new BodyPart(head.x + bodyDistance, head.y);
            body.add(0, newPart);
        }
        if (left){
            body.removeLast();
            BodyPart newPart = new BodyPart(head.x - bodyDistance, head.y);
            body.add(0, newPart);
        }
        if (up){
            body.removeLast();
            BodyPart newPart = new BodyPart(head.x, head.y - bodyDistance);
            body.add(0, newPart);
        }
        if (down){
            body.removeLast();
            BodyPart newPart = new BodyPart(head.x, head.y + bodyDistance);
            body.add(0, newPart);
        }
    }

    public boolean collision(Eatable food){
        Rectangle mouth = getHead().bounds();
        Rectangle fruit = food.foodBounds();

        if (mouth.intersects(fruit)){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkIfGameOver(){
        for (int i=2; i < body.size(); i++) {
            Rectangle head = body.get(0).bounds();
            Rectangle rest = body.get(i).bounds();
            if (head.intersects(rest)){
                System.out.println("BAM!");
                return true;
            }
        }
        return false;
    }

    public void endOfMap(int width, int height){
        if (this.getHead().x < 0){
            this.getHead().x = width;
        }
        if (this.getHead().x > width){
            this.getHead().x = 0;
        }
        if (this.getHead().y > height){
            this.getHead().y = 0;
        }
        if (this.getHead().y < 0){
            this.getHead().y = height;
        }
    }

    public BodyPart getHead(){
        return body.get(0);
    }

    public LinkedList<BodyPart> getBodyParts(){
        return body;
    }

    public void up() {
        moveY = -speed;
        moveX = 0;
        direction = 0;
    }

    public void down() {
        moveY = speed;
        moveX = 0;
        direction = 1;
    }

    public void left() {
        moveX = -speed;
        moveY = 0;
        direction = 2;
    }

    public void right() {
        moveX = speed;
        moveY = 0;
        direction = 3;
    }

    public void grow() {
        BodyPart last = body.getLast();
        if (right || left){
            BodyPart newPart = new BodyPart(last.x - last.width, last.y);
            this.body.add(newPart);
        }
        else if (up || down){
            BodyPart newPart = new BodyPart(last.x, last.y - last.height);
            this.body.add(newPart);
        }
    }
}
