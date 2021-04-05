package com.andreubita.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Renderer extends JPanel implements ActionListener {
    private Timer timer = new Timer(100, this);
    private Game game;

    public Renderer(Game game){
        this.game = game;
        timer.start();

        this.setBackground(Color.black);
        this.addKeyListener(this.game);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        if(game.getState().equals(Game.State.MENU)){
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("TimesRoman", Font.PLAIN, 30));
            g2d.drawString("Press any key...", Game.SCREEN_WIDTH/2-110, Game.SCREEN_HEIGHT/2-30);
        }else if(game.getState().equals(Game.State.RUNNING)){
            g2d.setColor(Color.RED);
            g2d.fillOval(
                    this.game.getFood().getX() * Game.SIDE_DIM,
                    this.game.getFood().getY() * Game.SIDE_DIM,
                    Game.SIDE_DIM,
                    Game.SIDE_DIM
            );

            g2d.setColor(Color.GREEN);
            for(Rectangle rect : this.game.getSnake().getBody())
                g2d.fill(rect);
        }else if(game.getState().equals(Game.State.ENDED)){
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("TimesRoman", Font.PLAIN, 30));
            g2d.drawString("Game Over!", Game.SCREEN_WIDTH/2-90, Game.SCREEN_HEIGHT/2-30);
            g2d.drawString("Score: " + this.game.getScore(), Game.SCREEN_WIDTH/2-65, Game.SCREEN_HEIGHT/2+10);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        this.game.update();
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Renderer renderer = (Renderer) o;

        if (!Objects.equals(timer, renderer.timer)) return false;
        return getGame() != null ? getGame().equals(renderer.getGame()) : renderer.getGame() == null;
    }

    @Override
    public int hashCode() {
        int result = timer != null ? timer.hashCode() : 0;
        result = 31 * result + (getGame() != null ? getGame().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Renderer{");
        sb.append("timer=").append(timer);
        sb.append(", game=").append(game);
        sb.append('}');
        return sb.toString();
    }
}
