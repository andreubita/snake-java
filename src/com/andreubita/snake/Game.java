package com.andreubita.snake;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener {
    public static final int N_BLOCK_WIDTH = 30;
    public static final int N_BLOCK_HEIGHT = 30;
    public static final int SIDE_DIM = 20;

    public static final int SCREEN_WIDTH = N_BLOCK_WIDTH * SIDE_DIM + 10;
    public static final int SCREEN_HEIGHT = N_BLOCK_HEIGHT * SIDE_DIM + 30;

    private State state;

    private JFrame window;

    private Snake snake;
    private Food food;
    private Renderer renderer;

    private int score;

    public Game(){
        this.state = State.MENU;

        this.window = new JFrame();
        this.snake = new Snake();
        this.food = new Food(snake.getBody());
        this.renderer = new Renderer(this);

        this.window.add(renderer);
        this.window.setTitle("Snake");
        this.window.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.window.setVisible(true);
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setResizable(false);
        this.window.setLocationRelativeTo(null);
    }

    public void start(){
        this.state = State.RUNNING;
    }

    public void update(){
        if(this.state.equals(State.RUNNING)){
            System.out.println(
                    "SNAKE (" + this.snake.getHead().getX() + "," + this.snake.getHead().getY() + ")\n"
                    + "FOOD (" + this.food.getX() + "," + this.food.getY() + ")\n"
            );
            if(checkFoodCollision()){
                System.out.println("ai");
                score++;
                this.snake.grow();
                this.food.spawnFood(snake.getBody());
            }else if(checkWallCollision() || checkSelfCollision()){
                System.out.println("ui");
                this.state = State.ENDED;
            }else{
                System.out.println("ei");
                this.snake.move();
            }
        }
    }

    public boolean checkFoodCollision(){
        return this.snake.getHead().getX() == this.food.getX() * Game.SIDE_DIM
                && this.snake.getHead().getY() == this.food.getY() * Game.SIDE_DIM;
    }

    public boolean checkSelfCollision(){
        for (int i = 1; i < this.snake.getBody().size(); i++)
            if(this.snake.getHead().getX() == this.snake.getBody().get(i).getX()
            && this.snake.getHead().getY() == this.snake.getBody().get(i).getY())
                return true;
        return false;
    }

    public boolean checkWallCollision(){
        return this.snake.getHead().getX() < 0
                || this.snake.getHead().getX() >= Game.N_BLOCK_WIDTH * Game.SIDE_DIM
                || this.snake.getHead().getY() < 0
                || this.snake.getHead().getY() >= Game.N_BLOCK_HEIGHT * Game.SIDE_DIM;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(this.state.equals(State.RUNNING)){
            if(keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP
            && this.snake.getDirection() != Snake.Direction.DOWN)
                this.snake.setDirection(Snake.Direction.UP);
            else if(keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN
            && this.snake.getDirection() != Snake.Direction.UP)
                this.snake.setDirection(Snake.Direction.DOWN);
            else if(keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT
            && this.snake.getDirection() != Snake.Direction.RIGHT)
                this.snake.setDirection(Snake.Direction.LEFT);
            else if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT
            && this.snake.getDirection() != Snake.Direction.LEFT)
                this.snake.setDirection(Snake.Direction.RIGHT);
        }else if(this.state.equals(State.MENU)){
            this.start();
        }else if(this.state.equals(State.ENDED)){
            this.score = 0;
            this.snake = new Snake();
            this.food = new Food(this.snake.getBody());
            this.state = State.MENU;
        }
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

    public Renderer getRenderer() {
        return this.renderer;
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (getScore() != game.getScore()) return false;
        if (getState() != game.getState()) return false;
        if (getWindow() != null ? !getWindow().equals(game.getWindow()) : game.getWindow() != null) return false;
        if (getSnake() != null ? !getSnake().equals(game.getSnake()) : game.getSnake() != null) return false;
        if (getFood() != null ? !getFood().equals(game.getFood()) : game.getFood() != null) return false;
        return getRenderer() != null ? getRenderer().equals(game.getRenderer()) : game.getRenderer() == null;
    }

    @Override
    public int hashCode() {
        int result = getState() != null ? getState().hashCode() : 0;
        result = 31 * result + (getWindow() != null ? getWindow().hashCode() : 0);
        result = 31 * result + (getSnake() != null ? getSnake().hashCode() : 0);
        result = 31 * result + (getFood() != null ? getFood().hashCode() : 0);
        result = 31 * result + (getRenderer() != null ? getRenderer().hashCode() : 0);
        result = 31 * result + getScore();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Game{");
        sb.append("state=").append(state);
        sb.append(", window=").append(window);
        sb.append(", snake=").append(snake);
        sb.append(", food=").append(food);
        sb.append(", renderer=").append(renderer);
        sb.append(", score=").append(score);
        sb.append('}');
        return sb.toString();
    }
}
