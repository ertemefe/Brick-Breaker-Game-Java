package domain.objects;

public class Paddle {

    private final int width = 100; //%10 of screen width = L, bunu bi variable olarak mı yapmak gerekiyo resizable fln olacaksa ?
    private final int thickness = 20;
    private int rotation_angle;
    private int location_x;
    private int location_y;

    public int getWidth() {
        return width;
    }

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
    }
}
