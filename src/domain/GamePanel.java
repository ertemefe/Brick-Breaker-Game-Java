package domain;

import domain.Controller;
import domain.objects.Paddle;
import domain.objects.obstacles.Obstacle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

    private Controller controller = Controller.getInstance();
    //private final int width = 100; //%10 of screen width = L, bunu bi variable olarak mı yapmak gerekiyo resizable fln olacaksa ?
    //private final int thickness = 20;
    private int rotation_angle;
    //private int location_x;
    //private int location_y;
    private boolean play = false;


    private final int L = 1200;
    private final int H = 500;

    private Timer timer;
    private int delay = 10;

    private Paddle paddle = Paddle.getInstance(L/10,L/2);

    private int ballposX = 120;
    private int ballposY = 350;

    private int ballXdir = -1;
    private int ballYdir = -2;

    public GamePanel() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
        setVisible(true);

    }

    public void paint(Graphics g) {
        //background
        g.setColor(Color.black);
        g.fillRect(1, 1, L, H);

        //borders
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 492);
        g.fillRect(0, 0, L, 3);
        g.fillRect(L - 3, 0, 3, 492);

        // obstacles
        ArrayList<Integer> positionsToRemove = new ArrayList<>();
        for(Integer pos : controller.obstacles.keySet()) {
            Obstacle obstacle = controller.obstacles.get(pos);
            // todo buradaki 40 değerini controllera göm
            // todo buradaki hesaplama yükünü paintten al, obstacle'lar yaratılırken, obstacle içine göm
            int obstacleX = pos % 40;
            int obstacleY = pos / 40;
            int obstacleWidth = L / 40;
            int obstacleHeight = H / 25;
            g.setColor(obstacle.getColor());
            g.fillRect(obstacleX * obstacleWidth, obstacleY * obstacleHeight, obstacleWidth, obstacleHeight);
            Rectangle brickrect = new Rectangle(obstacleX * obstacleWidth, obstacleY * obstacleHeight, obstacleWidth, obstacleHeight);
            Rectangle ballrect = new Rectangle(ballposX, ballposY, 16, 16);
            // todo ball-brick intersect
            if (ballrect.intersects(brickrect)) {
                obstacle.decreaseFirmness();
                if(obstacle.getFirmness() <= 0) {
                    positionsToRemove.add(pos);
                }
                if (ballposX + 15 <= brickrect.x || ballposX + 1 >= brickrect.x + obstacleWidth) {
                    ballXdir = -ballXdir;
                } else {
                    ballYdir = -ballYdir;
                }
            }
        }
        for(Integer posToRemove : positionsToRemove)
            controller.obstacles.remove(posToRemove);

        //the paddle
        g.setColor(Color.magenta);
        g.fillRect(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight());

        //the ball
        g.setColor(Color.red);
        // todo ball bug-fix
        g.fillOval(ballposX, ballposY, 16, 16);

        if (!play) {
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Game is resumed press arrow keys to start", 400, 150);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Save Game", 500, 200);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Load Game", 500, 250);
        }


        g.dispose();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (play) {
            ballposX += ballXdir;
            ballposY += ballYdir;

            // todo ball w = 16 , h = 16
            if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(paddle.getX(), 450, 100, 8))) {
                ballYdir = -ballYdir;
            }

            if (ballposX < 0) {
                ballXdir = -ballXdir;
            }
            if (ballposY < 0) {
                ballYdir = -ballYdir;
            }
            if (ballposX > L - 20) {
                ballXdir = -ballXdir;
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
            if (paddle.getX() >= L - 120) {
                paddle.setX(L - 120);
            } else {
                paddle.moveRight(L/60);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (paddle.getX() < 10) {
                paddle.setX(10);
            } else {
                paddle.moveLeft(L/60);
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
