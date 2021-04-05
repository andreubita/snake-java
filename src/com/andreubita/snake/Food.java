package com.andreubita.snake;

import java.awt.*;
import java.util.List;

public class Food {
    private int x;
    private int y;

    public Food(List<Rectangle> snakeBody){
        this.spawnFood(snakeBody);
    }

    public void spawnFood(List<Rectangle> snakeBody){
        boolean onSnake = true;
        while(onSnake){
            onSnake = false;

            this.x = (int) (Math.random() * Game.N_BLOCK_WIDTH - 1);
            this.y = (int) (Math.random() * Game.N_BLOCK_HEIGHT - 1);

            for(Rectangle rect : snakeBody)
                if(this.x == rect.getX()
                && this.y == rect.getY())
                    onSnake = true;
        }
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    protected Food clone() throws CloneNotSupportedException {
        return (Food) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Food food = (Food) o;

        if (getX() != food.getX()) return false;
        return getY() == food.getY();
    }

    @Override
    public int hashCode() {
        int result = getX();
        result = 31 * result + getY();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Food{");
        sb.append("x=").append(x);
        sb.append(", y=").append(y);
        sb.append('}');
        return sb.toString();
    }
}
