package com.andreubita.snake;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<Rectangle> body;
    private Direction direction;

    public Snake(){
        this.body = new ArrayList<>();

        Rectangle rect = new Rectangle(Game.SIDE_DIM, Game.SIDE_DIM);
        rect.setLocation(
                Game.N_BLOCK_WIDTH / 2 * Game.SIDE_DIM,
                Game.N_BLOCK_HEIGHT / 2 * Game.SIDE_DIM
        );
        this.body.add((Rectangle) rect.clone());

        rect.setLocation(
                (Game.N_BLOCK_WIDTH / 2 - 2) * Game.SIDE_DIM,
                Game.N_BLOCK_HEIGHT / 2 * Game.SIDE_DIM
        );
        this.body.add((Rectangle) rect.clone());

        rect.setLocation(
                (Game.N_BLOCK_WIDTH / 2 - 4) * Game.SIDE_DIM,
                Game.N_BLOCK_HEIGHT / 2 * Game.SIDE_DIM
        );
        this.body.add((Rectangle) rect.clone());

        this.direction = Direction.LEFT;
    }

    public void grow(){
        Rectangle newHead = new Rectangle(Game.SIDE_DIM, Game.SIDE_DIM);

        if(direction.equals(Direction.UP))
            newHead.setLocation((int) getHead().getX(), (int) getHead().getY() - Game.SIDE_DIM);
        else if(direction.equals(Direction.DOWN))
            newHead.setLocation((int) getHead().getX(), (int) getHead().getY() + Game.SIDE_DIM);
        else if(direction.equals(Direction.LEFT))
            newHead.setLocation((int) getHead().getX() - Game.SIDE_DIM, (int) getHead().getY());
        else if(direction.equals(Direction.RIGHT))
            newHead.setLocation((int) getHead().getX() + Game.SIDE_DIM, (int) getHead().getY());

        this.body.add(0, (Rectangle) newHead.clone());
    }

    public void move(){
        grow();
        this.body.remove(this.body.size() - 1);
    }

    public enum Direction{
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public List<Rectangle> getBody() {
        List<Rectangle> rectsArr = new ArrayList<>();
        for (Rectangle rect : this.body)
            rectsArr.add((Rectangle) rect.clone());
        return rectsArr;
    }

    public void setBody(List<Rectangle> body) {
        List<Rectangle> rectsArr = new ArrayList<>();
        for (Rectangle rect : body)
            rectsArr.add((Rectangle) rect.clone());
        this.body = rectsArr;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Point getHead(){
        return new Point((int) getBody().get(0).getX(), (int)  getBody().get(0).getY());
    }
}
