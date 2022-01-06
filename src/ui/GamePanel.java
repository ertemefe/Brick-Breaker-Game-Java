package ui;

import domain.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GamePanel extends JPanel implements KeyListener, ActionListener {

    private static final int DELAY = 10;
    public static GamePanel instance;
    private static Game game = Game.getInstance();
    private final int L = 1200;
    private final int H = 500;
    private boolean play = false;
    private boolean pause = false;
    private Timer timer;

    private GamePanel() {
        addKeyListener(this);
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(L, H));
        setFocusTraversalKeysEnabled(false);
        setVisible(true);
        timer = new Timer(DELAY, this);
    }

    public static GamePanel getInstance() {
        if (instance == null) instance = new GamePanel();
        return instance;
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        game.drawGame(g2);

        // obstacles
        /*for (Integer pos : controller.obstacles.keySet()) {
            Obstacle obstacle = controller.obstacles.get(pos);
            obstacle.updateFrozenTime(DELAY);
            obstacle.drawObstacle(g2);

            controller.game.ballBrickCollision(mainBall, obstacle, pos,*//* clock,*//* positionsToRemove);
         *//*if (mainBall.getBallRect().intersects(obstacle.getBrick())) {
                if (!obstacle.isFrozen()) {
                    hit(obstacle, pos, mainBall.getDamage());
                    if (!Abilities.unstoppableActive) brickCollision(obstacle);
                } else {
                    brickCollision(obstacle);
                    if (Abilities.unstoppableActive) hit(obstacle, pos, 1);
                }
            }*//*

         *//*for (Ball hex : hexBalls) {
                hex.setBallRect(hex.getBallposX(), hex.getBallposY());
                if (hex.getBallRect().intersects(obstacle.getBrick())) {
                    controller.game.hit(obstacle, pos, hex.getDamage());
                    hex.setDamage(0);
                }
            }*//*
        }*/

/*
        for (Integer posToRemove : positionsToRemove)
            controller.obstacles.remove(posToRemove);
*/

        // draw falling objects:
        /*for (FallingObject fo : fallingObjectList) {
            if (fo instanceof ObstacleExplosive oe) {
                g2.fillOval(oe.getCoordinates().x + oe.getWidth() / 3, oe.getCoordinates().y + oe.getWidth() / 4, oe.getWidth(), oe.getWidth());
            }
        }*/


        //the cannons
       /* if (Abilities.hexActive) {
            g2.setColor(Color.YELLOW);
            g2.rotate(Math.toRadians(paddle.getAngle()), (paddle.getX()), (paddle.getY() + paddle.getHeight()));
            //left cannon
            g2.fillRect(paddle.getX() - paddle.getWidth() / 2, paddle.getY() - paddle.getWidth() / 2 + paddle.getHeight(), paddle.getHeight(), paddle.getWidth() / 2);
            //right cannon
            g2.fillRect(paddle.getX() + paddle.getWidth() / 2 - paddle.getHeight(), paddle.getY() - paddle.getWidth() / 2 + paddle.getHeight(), paddle.getHeight(), paddle.getWidth() / 2);

            if (clock % 300 == 0) {
                Ball hexBallL = new Ball(16, 16, paddle.getX() - paddle.getWidth() / 2, paddle.getY() - paddle.getWidth() / 2, 0, -2);
                Ball hexBallR = new Ball(16, 16, paddle.getX() + paddle.getWidth() / 2 - paddle.getHeight(), paddle.getY() - paddle.getWidth() / 2, 0, -2);
                hexBallL.setDamage(1);
                hexBallR.setDamage(1);
                hexBalls.add(hexBallL);
                hexBalls.add(hexBallR);
            }

            for (Ball hex : hexBalls) {
                if (hex.getDamage() > 0)
                    g2.fillOval(hex.getBallposX(), hex.getBallposY(), 16, 16);
            }
        }*/


        if (!play) {
            g2.setFont(new Font("serif", Font.BOLD, 20));
            g2.drawString("Press W to shoot the ball", 500, 300);
        }

        if (play && pause) {
            g2.setFont(new Font("serif", Font.BOLD, 20));
            g2.drawString("Press P to continue", 500, 300);
        }

        g2.dispose(); //bu ne işe yarıyor
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (play && !pause) {
            game.tick();
            /*game.ballBrickCollision();
            game.mainBall.move();
            game.ballWallPaddleCollision();*/
            /*mainBall.move();
            mainBall.setBallRect(mainBall.getBallposX(), mainBall.getBallposY());*/

            /*for (Ball hex : hexBalls) {
                hex.move();
            }*/

            /*mainBall.updateFrozenTime(DELAY);
            paddle.updateFrozenTime(DELAY);
            ymir.updateRemainingTime(DELAY, mainBall);*/

            /*for (int i = 0; i < fallingObjectList.size(); i++) {
                FallingObject fo = fallingObjectList.get(i);
                fo.fall();
                if (fo.getY() > 470) {
                    fallingObjectList.remove(fo);
                    i--;
                }
            }*/

            if (game.dead()) {
                game.restart();
                play = false;
                timer.stop();
                if (game.remainingLives == 0) {
                    setVisible(false);
                    System.out.println("Game Over");
                }
            }
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            if (!timer.isRunning()) {
                timer.start();
                play = true;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            if (timer.isRunning()) pause = !pause;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (play && !pause) game.movePaddleRight();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (play && !pause) game.movePaddleLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            if (play && !pause) game.rotatePaddleRight();
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            if (play && !pause) game.rotatePaddleLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_T) {
            if (play && !pause) game.ability("E");
        }
        if (e.getKeyCode() == KeyEvent.VK_H) {
            if (play && !pause) game.ability("H");
        }
        if (e.getKeyCode() == KeyEvent.VK_U) {
            if (play && !pause) game.ability("U");
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
