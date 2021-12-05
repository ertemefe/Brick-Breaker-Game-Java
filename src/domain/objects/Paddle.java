package domain.objects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Paddle extends JPanel implements KeyListener, ActionListener {

    //private final int width = 100; //%10 of screen width = L, bunu bi variable olarak mı yapmak gerekiyo resizable fln olacaksa ?
    //private final int thickness = 20;
    private int rotation_angle;
    //private int location_x;
    //private int location_y;
    private boolean play = false;


    private int L = 1200;

    private Timer timer;
    private int delay = 10;


    private int playerX = 600;

    private int ballposX = 120;
    private int ballposY = 350;

    private int ballXdir = -1;
    private int ballYdir = -2;

    public Paddle() {
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
        g.fillRect(1, 1, L, 500);

        //borders
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 492);
        g.fillRect(0, 0, L, 3);
        g.fillRect(L - 3, 0, 3, 492);

        //the padle
        g.setColor(Color.magenta);
        g.fillRect(playerX, 450, L / 10, 10);

        //the ball
        g.setColor(Color.red);
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
            if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 450, 100, 8))) {
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
            if (playerX >= L - 120) {
                playerX = L - 120;
            } else {
                moveRight();
                System.out.println("Moved Right");
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {

            if (playerX < 10) {
                playerX = 10;
            } else {
                moveLeft();
                System.out.println("Moved left");
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            play = false;
        }
    }

    public void moveRight() {
        play = true;
        playerX += (L / 60);
    }

    public void moveLeft() {
        play = true;
        playerX -= (L / 60);
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
