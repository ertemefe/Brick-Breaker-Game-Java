package domain.objects;

public class Gift {

    private final int height;
    private final int width;
    private double posX;
    private double posY;

    public Gift(int height, int width, int posX, int posY) {
        this.height = height;
        this.width = width;
        this.posX = posX;
        this.posY = posY;
    }

    public void move() {
        posY++;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }
}
