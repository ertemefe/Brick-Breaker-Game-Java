package domain;

import domain.objects.*;
import domain.objects.obstacles.Obstacle;
import domain.objects.obstacles.ObstacleExplosive;

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
    private boolean play = false;
    private boolean pause = false;
    private int remainingLives = 3;
    private int clock = 0;
    private int score = 0;
    private final Controller controller = Controller.getInstance();
    private Ymir ymir = Ymir.getInstance(30000);
    private Paddle paddle = Paddle.getInstance();
    private Abilities abilities = new Abilities();
    private Timer timer;
    private Ball mainBall;
    private ArrayList<Integer> positionsToRemove = new ArrayList<>();
    private List<FallingObject> fallingObjectList = new ArrayList<>();
    private List<Ball> hexBalls = new ArrayList<>();
    private final Color purple = new Color(102, 0, 153);


    private GamePanel() {
        addKeyListener(this);
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(L, H));
        setFocusTraversalKeysEnabled(false);
        setVisible(true);
        resetPositions();
        controller.initializeBricks();
        timer = new Timer(DELAY, this);

    }

    public static GamePanel getInstance() {
        return gamePanel;
    }

    private void resetPositions() {
        paddle.setAngle(0);
        paddle.setX(L / 2);
        mainBall = new Ball(16, 16, paddle.getX() - 8, paddle.getY() - 16, -1, -2);
        mainBall.setDamage(1);
    }

    private void hit(Obstacle obstacle, int pos, int damage) {
        obstacle.decreaseFirmness(damage);
        if (clock > 100 && obstacle.getColor() != purple) {
            score += (300 / (clock / 100));
            controller.setScore(score);
        }
        if (obstacle.isExplosive()) fallingObjectList.add((ObstacleExplosive) obstacle);
        if (obstacle.getFirmness() <= 0) positionsToRemove.add(pos);
    }

    private void brickCollision(Obstacle obstacle) {
        if (mainBall.getBallposX() + 15 <= obstacle.getBrick().x || mainBall.getBallposX() + 1 >= obstacle.getBrick().x + obstacle.getWidth()) {
            mainBall.reverseDirX();
        }
        else mainBall.reverseDirY();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // obstacles
        for (Integer pos : controller.obstacles.keySet()) {
            Obstacle obstacle = controller.obstacles.get(pos);
            obstacle.updateFrozenTime(DELAY);
            obstacle.drawObstacle(g2);

            if (mainBall.getBallRect().intersects(obstacle.getBrick())) {
                if (!obstacle.isFrozen()) {
                    hit(obstacle, pos, mainBall.getDamage());
                    if (!Abilities.unstoppableActive) brickCollision(obstacle);
                } else {
                    brickCollision(obstacle);
                    if (Abilities.unstoppableActive) hit(obstacle, pos, 1);
                }
            }

            for (Ball hex : hexBalls) {
                hex.setBallRect(hex.getBallposX(), hex.getBallposY());
                if (hex.getBallRect().intersects(obstacle.getBrick())) {
                    hit(obstacle, pos, hex.getDamage());
                    hex.setDamage(0);
                }
            }

        }

        for (Integer posToRemove : positionsToRemove)
            controller.obstacles.remove(posToRemove);

        // draw falling objects:
        for (FallingObject fo : fallingObjectList) {
            if (fo instanceof ObstacleExplosive oe) {
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
            mainBall.setBallRect(mainBall.getBallposX(), mainBall.getBallposY());

            for (Ball hex : hexBalls) {
                hex.move();
            }

            mainBall.updateFrozenTime(DELAY);
            paddle.updateFrozenTime(DELAY);
            ymir.updateRemainingTime(DELAY, mainBall);

            for (int i = 0; i < fallingObjectList.size(); i++) {
                FallingObject fo = fallingObjectList.get(i);
                fo.fall();
                if (fo.getY() > 470) {
                    fallingObjectList.remove(fo);
                    i--;
                }
            }

            if (mainBall.getBallRect()
                    .intersectsLine(
                            (paddle.getX()) - ((paddle.getWidth() / 2) * Math.sin(Math.toRadians(90 - paddle.getAngle()))),
                            (paddle.getY()) - ((paddle.getWidth() / 2) * Math.sin(Math.toRadians(paddle.getAngle()))),
                            (paddle.getX()) + ((paddle.getWidth() / 2) * Math.sin(Math.toRadians(90 - paddle.getAngle()))),
                            (paddle.getY()) + ((paddle.getWidth() / 2) * Math.sin(Math.toRadians(paddle.getAngle())))))
                mainBall.reverseDirY();

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
                if (paddle.getX() >= L - (paddle.getWidth()) / 2) {
                    paddle.setX(L - (paddle.getWidth() / 2));
                } else {
                    paddle.moveRight(L / 60);
                }
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (play && !pause) {
                if (paddle.getX() <= (paddle.getWidth()) / 2) {
                    paddle.setX(paddle.getWidth() / 2);
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
