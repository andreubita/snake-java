package com.andreubita.snake;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener {
    public static final int BLOCK_WIDTH = 30;
    public static final int BLOCK_HEIGHT = 30;
    public static final int SIDE_DIM = 20;

    private State state;

    private JFrame window;

    private Snake snake;
    private Food food;
    private Graphics graphics;

    public Game(){
        state = State.MENU;

        window = new JFrame();
        snake = new Snake();
        food = new Food();
        graphics = new Graphics(this);

        window.add(graphics);
        window.setTitle("Snake");
        window.setSize(BLOCK_WIDTH*SIDE_DIM+10, BLOCK_HEIGHT*SIDE_DIM+30);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void start(){
        this.state = State.RUNNING;
    }

    public void update(){

    }

    public boolean checkFoodCollision(){
        return false;
    }

    public boolean checkSelfCollision(){
        return false;
    }

    public boolean checkWallCollision(){
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public enum State{
        MENU,
        RUNNING,
        PAUSED,
        ENDED;
    }

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public JFrame getWindow() {
        return this.window;
    }

    public void setWindow(JFrame window) {
        this.window = window;
    }

    public Snake getSnake() {
        return this.snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Food getFood() {
        return this.food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Graphics getGraphics() {
        return this.graphics;
    }

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }
}
