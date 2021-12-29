package domain.objects;

public class Paddle {

    private static Paddle instance;

    private final int height=10;
    private final int location_y = 450;
    private int width;
    private int location_x;

    private Paddle(int width, int location_x) {
        this.width = width;
        this.location_x = location_x;
    }

    public static Paddle getInstance(int width, int location_x) {
        if(instance == null)
            instance = new Paddle(width, location_x);
        return instance;
    }

    public int getX() {
        return location_x;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setX(int location_x) {
        this.location_x = location_x;
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
}
