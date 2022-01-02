package domain;

import domain.objects.*;
import domain.objects.obstacles.Obstacle;
import domain.objects.obstacles.ObstacleExplosive;
import ui.StatPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;


public class GamePanel extends JPanel implements KeyListener, ActionListener {

    public static final GamePanel gamePanel = new GamePanel();
    private static final int DELAY = 10;
    private final int L = 1200;
    private final int H = 500;
    private final Controller controller = Controller.getInstance();
    private final Ymir ymir = Ymir.getInstance(30000);
    private Paddle paddle = Paddle.getInstance(/*L / 10, L / 2*/);
    private Abilities abilities = new Abilities();
    private Timer timer;
    private boolean play = false;
    private int remainingLives = 3;
    private int clock = 0;
    private int score = 0;
    private boolean pause = false;
    private Ball mainBall;
    private ArrayList<Integer> positionsToRemove = new ArrayList<>();
    private List<FallingObject> fallingObjectList = new ArrayList<>();
    private List<Ball> hexBalls = new ArrayList<>();


    private GamePanel() {
        addKeyListener(this);
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(L, H));
        setFocusTraversalKeysEnabled(false);
        setVisible(true);
        resetPositions();
        timer = new Timer(DELAY, this);

    }

    public static GamePanel getInstance() {
        return gamePanel;
    }

    private void resetPositions() {
        paddle.setAngle(0);
        paddle.setX(L/2);
        mainBall = new Ball(16, 16, paddle.getX() - 8, paddle.getY() - 16, -1, -2);
        mainBall.setDamage(1);
        ymir.setBall(mainBall);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // obstacles
        for (Integer pos : controller.obstacles.keySet()) {
            Obstacle obstacle = controller.obstacles.get(pos);
            obstacle.updateFrozenTime(DELAY);
            int obstacleX = obstacle.getCoordinates().x;
            int obstacleY = obstacle.getCoordinates().y;
            int obstacleWidth = obstacle.getWidth();
            int obstacleHeight = 20;

            //bütün obstacle çizimlerini obstacle classlarına gömdüm
            obstacle.drawObstacle(g2);

            Rectangle brickrect;
            if (obstacle.getType().equals("explosive")) {
                brickrect = new Rectangle(obstacleX, obstacleY, obstacleWidth, obstacleWidth);
            } else {
                brickrect = new Rectangle(obstacleX, obstacleY, obstacleWidth, obstacleHeight);
            }

            Rectangle ballrect = new Rectangle(mainBall.getBallposX(), mainBall.getBallposY(), 16, 16);

            for (Ball hex : hexBalls) {
                if (hex instanceof Ball) {
                    Ball hexB = (Ball) hex;
                    Rectangle ballrect2 = new Rectangle(hexB.getBallposX(), hexB.getBallposY(), 16, 16);
                    if (ballrect2.intersects(brickrect)) {

                        if (obstacle instanceof ObstacleExplosive)
                            fallingObjectList.add((ObstacleExplosive) obstacle);

                        obstacle.decreaseFirmness(hexB.getDamage());
                        hexB.setDamage(0);
                        if (obstacle.getFirmness() <= 0 && !obstacle.isFrozen()) {
                            positionsToRemove.add(pos);
                        }
                    }
                }
            }

            if (ballrect.intersects(brickrect)) {
                if (clock > 100) score += (300 / (clock / 100));
                controller.setScore(score);
                if (obstacle instanceof ObstacleExplosive)
                    fallingObjectList.add((ObstacleExplosive) obstacle);

                obstacle.decreaseFirmness(mainBall.getDamage());
                if (obstacle.getFirmness() <= 0 && !obstacle.isFrozen()) {
                    positionsToRemove.add(pos);
                }

                if (!Abilities.unstoppableActive) {
                    if (mainBall.getBallposX() + 15 <= brickrect.x || mainBall.getBallposX() + 1 >= brickrect.x + obstacleWidth) {
                        mainBall.reverseDirX();
                    } else {
                        mainBall.reverseDirY();
                    }
                }
            }
        }

        for (Integer posToRemove : positionsToRemove)
            controller.obstacles.remove(posToRemove);

        // draw falling objects:
        for (FallingObject fo : fallingObjectList) {
            if (fo instanceof ObstacleExplosive) {
                ObstacleExplosive oe = (ObstacleExplosive) fo;
                g2.fillOval(oe.getCoordinates().x + oe.getWidth() / 3, oe.getCoordinates().y + oe.getWidth() / 4, oe.getWidth(), oe.getWidth());
            }
        }


        //the cannons
        if (Abilities.hexActive) {
            g2.setColor(Color.YELLOW);
            g2.rotate(Math.toRadians(paddle.getAngle()), (paddle.getX()), (paddle.getY() + paddle.getHeight()));
            //left cannon
            g2.fillRect(paddle.getX() - paddle.getWidth() / 2, paddle.getY() - paddle.getWidth() / 2 + paddle.getHeight(), paddle.getHeight(), paddle.getWidth() / 2);
            //right cannon
            g2.fillRect(paddle.getX() + paddle.getWidth() / 2 - paddle.getHeight(), paddle.getY() - paddle.getWidth() / 2 + paddle.getHeight(), paddle.getHeight(), paddle.getWidth() / 2);
            Ball hexBallL = new Ball(16, 16, paddle.getX() - paddle.getWidth() / 2, paddle.getY() - paddle.getWidth() / 2, 0, -2);
            Ball hexBallR = new Ball(16, 16, paddle.getX() + paddle.getWidth() / 2 - paddle.getHeight(), paddle.getY() - paddle.getWidth() / 2, 0, -2);
            hexBallL.setDamage(1);
            hexBallR.setDamage(1);

            if (clock % 300 == 0) {
                hexBalls.add(hexBallL);
                hexBalls.add(hexBallR);
            }

            for (Ball hex : hexBalls) {
                if (hex instanceof Ball) {
                    Ball hexB = (Ball) hex;
                    if (hexB.getDamage() != 0) {
                        g2.fillOval(hex.getBallposX(), hex.getBallposY(), 16, 16);
                    }
                }
            }
        }


        if (!play) {
            g2.setFont(new Font("serif", Font.BOLD, 20));
            g2.drawString("Press W to shoot the ball", 500, 300);
        }

        if (play && pause) {
            g2.setFont(new Font("serif", Font.BOLD, 20));
            g2.drawString("Press P to continue", 500, 300);
        }

        mainBall.drawBall(g2); //the ball
        paddle.drawPaddle(g2); //the paddle

        g2.dispose(); //bu ne işe yarıyor
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (play && !pause) {
            clock++;
            controller.setClock(clock / 100);
            mainBall.move();

            for (Ball hex : hexBalls) {
                if (hex instanceof Ball) {
                    Ball hexB = (Ball) hex;
                    hexB.move();
                }
            }

            mainBall.updateFrozenTime(DELAY);
            paddle.updateFrozenTime(DELAY, L / 10, paddle.getX());
            ymir.updateRemainingTime(DELAY);

            for (int i = 0; i < fallingObjectList.size(); i++) {
                FallingObject fo = fallingObjectList.get(i);
                fo.fall();
                if (fo.getY() > 470) {
                    fallingObjectList.remove(fo);
                    i--;
                }
            }

            if (!Abilities.expansionActive) {
                if (new Rectangle(mainBall.getBallposX(), mainBall.getBallposY(), 15, 15)
                        .intersectsLine(
                                (paddle.getX()) - ((paddle.getWidth() / 2) * Math.sin(Math.toRadians(90 - paddle.getAngle()))),
                                (paddle.getY()) - ((paddle.getWidth() / 2) * Math.sin(Math.toRadians(paddle.getAngle()))),
                                (paddle.getX()) + ((paddle.getWidth() / 2) * Math.sin(Math.toRadians(90 - paddle.getAngle()))),
                                (paddle.getY()) + ((paddle.getWidth() / 2) * Math.sin(Math.toRadians(paddle.getAngle())))))
                mainBall.reverseDirY();
            } else {
                if (new Rectangle(mainBall.getBallposX(), mainBall.getBallposY(), 15, 15)
                        .intersectsLine(
                                (paddle.getX()) - ((paddle.getWidth() / 2) * Math.sin(Math.toRadians(90 - paddle.getAngle()))),
                                (paddle.getY()) - ((paddle.getWidth() / 2) * Math.sin(Math.toRadians(paddle.getAngle()))),
                                (paddle.getX()) + ((paddle.getWidth() / 2) * Math.sin(Math.toRadians(90 - paddle.getAngle()))),
                                (paddle.getY()) + ((paddle.getWidth() / 2) * Math.sin(Math.toRadians(paddle.getAngle())))))
                    mainBall.reverseDirY();
            }

            if (mainBall.getBallposX() < 0) {
                mainBall.reverseDirX();
            }
            if (mainBall.getBallposY() < 0) {
                mainBall.reverseDirY();
            }
            if (mainBall.getBallposX() > L - 20) {
                mainBall.reverseDirX();
            }
            if (mainBall.getBallposY() > 470) {
                Abilities.expansionActive = false;
                Abilities.unstoppableActive = false;
                remainingLives--;
                controller.setLives(remainingLives);
                if (remainingLives == 0) {
                    // todo öldükten sonraki menu vs
                    setVisible(false);
                }
                resetPositions();
                play = false;
                timer.stop();
            }
        }
        repaint();
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (play && !pause) {
                if (paddle.getX() >= L - (paddle.getWidth())/2) {
                    paddle.setX(L - (paddle.getWidth()/2));
                } else {
                    paddle.moveRight(L / 60);
                }
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (play && !pause) {
                if (paddle.getX() <= (paddle.getWidth())/2) {
                    paddle.setX(paddle.getWidth()/2);
                } else {
                    paddle.moveLeft(L / 60);
                }
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            if (!timer.isRunning()) {
                timer.start();
                play = true;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_P) {
            if (timer.isRunning()) pause = !pause;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            if (timer.isRunning()) paddle.rotate("left");
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            if (timer.isRunning()) paddle.rotate("right");
        }
        if (e.getKeyCode() == KeyEvent.VK_T) {
            if (timer.isRunning()) abilities.noblePhantasmExpansion();
        }
        if (e.getKeyCode() == KeyEvent.VK_H) {
            if (timer.isRunning()) abilities.activateHex();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {


    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
}
