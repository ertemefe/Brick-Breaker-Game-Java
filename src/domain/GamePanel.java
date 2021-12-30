package domain;

import domain.objects.Ball;
import domain.objects.Paddle;
import domain.objects.Ymir;
import domain.objects.obstacles.Obstacle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

    private final int L = 1200;
    private final int H = 500;
    private final Controller controller = Controller.getInstance();
    private final Ymir ymir = Ymir.getInstance(3000);
    private Paddle paddle;
    private Timer timer;
    private static final int DELAY = 10;
    private boolean play = false;
    private int rotation_angle;
    private int remainingLives = 3;

    private Ball mainBall;

    public GamePanel() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setVisible(true);
        resetPositions();
        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void resetPositions() {
        mainBall = new Ball(16,16, 120, 350, -1, -2);
        paddle = Paddle.getInstance(L/10 , L/2);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        //background
        g2.setColor(Color.black);
        g2.fillRect(0, 0, L, H);

        // obstacles
        ArrayList<Integer> positionsToRemove = new ArrayList<>();
        for (Integer pos : controller.obstacles.keySet()) {
            Obstacle obstacle = controller.obstacles.get(pos);

            int obstacleX = obstacle.getCoordinates().x;
            int obstacleY = obstacle.getCoordinates().y;
            int obstacleWidth = obstacle.getWidth();
            int obstacleHeight = 20;
            g2.setColor(obstacle.getColor());

            if (obstacle.getType().equals("explosive")) {
                g2.fillOval(obstacleX, obstacleY, obstacleWidth, obstacleWidth);
            } else g2.fillRect(obstacleX, obstacleY, obstacleWidth, obstacleHeight);

            if (obstacle.getType().equals("firm")) {
                g2.setColor(Color.black);
                g2.drawString(String.valueOf(obstacle.getFirmness()), obstacleX + 8, obstacleY + 15);
            }

            Rectangle brickrect;
            if (obstacle.getType().equals("explosive")) {
                brickrect = new Rectangle(obstacleX, obstacleY, obstacleWidth, obstacleWidth);
            } else {
                brickrect = new Rectangle(obstacleX, obstacleY, obstacleWidth, obstacleHeight);
            }

            Rectangle ballrect = new Rectangle(mainBall.getBallposX(), mainBall.getBallposY(), 16, 16);
            // todo ball-brick intersect
            if (ballrect.intersects(brickrect)) {
                obstacle.decreaseFirmness();
                if (obstacle.getFirmness() <= 0 && !obstacle.isFrozen()) {
                    positionsToRemove.add(pos);
                }
                if (mainBall.getBallposX() + 15 <= brickrect.x || mainBall.getBallposX() + 1 >= brickrect.x + obstacleWidth) {
                    mainBall.reverseDirX();
                } else {
                    mainBall.reverseDirY();
                }
            }
        }

        for (Integer posToRemove : positionsToRemove)
            controller.obstacles.remove(posToRemove);

        //the paddle
        g2.setColor(Color.magenta);
        g2.fillRect(paddle.getX() - paddle.getWidth() / 2, paddle.getY(), paddle.getWidth(), paddle.getHeight());

        //the ball
        g2.setColor(Color.red);
        // todo ball bug-fix
        g2.fillOval(mainBall.getBallposX(), mainBall.getBallposY(), 16, 16);


        if (!play) {
            g2.setFont(new Font("serif", Font.BOLD, 20));
            g2.drawString("Game is resumed press arrow keys to start", 400, 150);
            g2.setFont(new Font("serif", Font.BOLD, 20));
            g2.drawString("Save Game", 500, 200);
            g2.setFont(new Font("serif", Font.BOLD, 20));
            g2.drawString("Load Game", 500, 250);
        }

        g2.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (play) {
            mainBall.move();
            ymir.updateRemainingTime(DELAY);

            // todo ball w = 16 , h = 16
            if (new Rectangle(mainBall.getBallposX(), mainBall.getBallposY(), 15, 15).intersects(new Rectangle(paddle.getX(), paddle.getY(), 120, 10))) {
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
            if (mainBall.getBallposY() > 500) {
                remainingLives--;
                if(remainingLives==0) {
                    // todo öldükten sonraki menu vs
                    setVisible(false);
                }
                resetPositions();
                play = false;
            }
        }
        repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (paddle.getX() >= L - 60) {
                paddle.setX(L - 60);
            } else {
                paddle.moveRight(L / 60);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (paddle.getX() <= 60) {
                paddle.setX(60);
            } else {
                paddle.moveLeft(L / 60);
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_P)
            play = !play;
    }

    @Override
    public void keyReleased(KeyEvent e) {


    }
    /*
    public int getWidth() {return width;}

    public int getThickness() {
        return thickness;
    }

    public int getLocation_x() {
        return location_x;
    }

    public void setLocation_x(int location_x) {
        this.location_x = location_x;
    }

    public int getLocation_y() {
        return location_y;
    }

    public void setLocation_y(int location_y) {
        this.location_y = location_y;
    }

    public int getRotation_angle() {
        return rotation_angle;
    }

    public void setRotation_angle(int rotation_angle) {
        this.rotation_angle = rotation_angle;
    }*/
}
