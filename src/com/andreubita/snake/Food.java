package com.andreubita.snake;

public class Food {
    @Override
    protected Food clone() throws CloneNotSupportedException {
        return (Food) super.clone();
    }
}
