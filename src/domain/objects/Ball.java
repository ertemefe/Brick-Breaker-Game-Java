package domain.objects;

public class Ball {

    private final int height;
    private final int width;
    private int ballposX;
    private int ballposY;
    private int ballXdir;
    private int ballYdir;

    public Ball(int height, int width, int startPosX, int startPosY, int ballXdir, int ballYdir) {
        this.height = height;
        this.width = width;
        ballposX = startPosX;
        ballposY = startPosY;
        this.ballXdir = ballXdir;
        this.ballYdir = ballYdir;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getBallposX() {
        return ballposX;
    }

    public int getBallposY() {
        return ballposY;
    }

    public int getBallXdir() {
        return ballXdir;
    }

    public int getBallYdir() {
        return ballYdir;
    }

    public void move() {
        ballposX += ballXdir;
        ballposY += ballYdir;
    }

    public void reverseDirX() {
        ballXdir *= -1;
    }

    public void reverseDirY() {
        ballYdir *= -1;
    }
}
