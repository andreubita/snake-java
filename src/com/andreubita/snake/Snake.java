package com.andreubita.snake;

public class Snake {
    @Override
    protected Snake clone() throws CloneNotSupportedException {
        return (Snake) super.clone();
    }
}
