package domain.objects;

public class Paddle {

    private static Paddle instance;

    private final int height = 20;
    private final int location_y = 475;
    private int width;
    private int location_x;

    private Paddle() {}

    public static Paddle getInstance(int width, int location_x) {
        if (instance == null)
            instance = new Paddle();
        instance.width = width;
        instance.location_x = location_x;
        return instance;
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
