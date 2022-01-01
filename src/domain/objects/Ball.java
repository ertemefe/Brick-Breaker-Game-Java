package domain.objects;

public class Ball {

    private final int height;
    private final int width;
    private double ballposX;
    private double ballposY;
    private int ballXdir;
    private int ballYdir;
    private double speed;
    private int remainingSlownessTime;

    public Ball(int height, int width, int startPosX, int startPosY, int ballXdir, int ballYdir) {
        this.height = height;
        this.width = width;
        ballposX = startPosX;
        ballposY = startPosY;
        this.ballXdir = ballXdir;
        this.ballYdir = ballYdir;
        this.speed = 1;
        this.remainingSlownessTime = 0;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getBallposX() {
        return (int) Math.round(ballposX);
    }

    public int getBallposY() {
        return (int) Math.round(ballposY);
    }

    public int getBallXdir() {
        return ballXdir;
    }

    public int getBallYdir() {
        return ballYdir;
    }

    public void move() {
        ballposX = ballposX + ballXdir * speed;
        ballposY = ballposY + ballYdir * speed;

    }

    public void updateFrozenTime(int decreaseTime) {
        remainingSlownessTime -= decreaseTime;
        if(remainingSlownessTime < 0)
            speed = 1;
    }

    public void startSlowness(int slownessTime) {
        speed = 0.5f;
        remainingSlownessTime = slownessTime;
    }

    public void reverseDirX() {
        ballXdir *= -1;
    }

    public void reverseDirY() {
        ballYdir *= -1;
    }
}
