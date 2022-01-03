package domain.objects;

import java.awt.*;

public class Paddle {

    private static Paddle instance;

    private int width = 120;
    private int height = 20;
    private int location_x= 600;
    private int location_y = 450;
    private int angle;
    private final Rectangle p = new Rectangle(getX() - getWidth() / 2, getY(), getWidth(), getHeight());

    public void setAngle(int angle) {
        this.angle = angle;
    }
    private int remainingSlownessTime;
    private int remainingHexTime;

    private Paddle() {}

    public static Paddle getInstance() {
        if (instance == null)
            instance = new Paddle();
        return instance;
    }

    public void drawPaddle(Graphics2D g2){
        p.x=(getX() - getWidth() / 2);
        p.y= getY();
        p.setSize(getWidth(), getHeight());
        g2.setColor(Color.BLUE);
        g2.rotate(Math.toRadians(getAngle()), (getX()), (getY() + getHeight()));
        g2.draw(p);
        g2.fill(p);
    }

    public int getAngle() {
        return angle;
    }

    public int getX() {
        return location_x;
    }

    public void setX(int location_x) {
        this.location_x = location_x;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getY() {
        return location_y;
    }

    public void moveRight(int dx) {
        location_x += dx;
    }

    public void moveLeft(int dx) {
        location_x -= dx;
    }

    public void rotate(String direction) {
        if (direction.equals("right")) {
            rotateRight();
        } else {
            rotateLeft();
        }
    }

    private void rotateLeft() {
        if (angle >= -30) {
            angle -= 10;
        } else {
            angle = -45;
        }
    }

    private void rotateRight() {
        if (angle <= 30) {
            angle += 10;
        } else {
            angle = 45;
        }
    }

    public void updateFrozenTime(int decreaseTime) {
        remainingSlownessTime -= decreaseTime;
        remainingHexTime -= decreaseTime;

        if (remainingSlownessTime < 0 && Abilities.expansionActive) {
            //Paddle.getInstance();
            Abilities.deactivateExpansion(this);
        }

        if (remainingHexTime < 0) {
            Abilities.deactivateHex();
        }

    }

    public void startSlowness(int slownessTime) {
        remainingSlownessTime = slownessTime;
    }


    public void startHex(int hexTime) {
        remainingHexTime = hexTime;
    }


}
