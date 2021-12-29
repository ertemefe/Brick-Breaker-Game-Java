package domain.objects;

public class Ball {
    // todo buraya bi singleton atılır gibi

    private final int height=16;
    private final int width=16;
    private int location_x;
    private int location_y;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
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

}
