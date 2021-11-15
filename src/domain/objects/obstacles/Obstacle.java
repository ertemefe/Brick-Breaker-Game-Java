package domain.objects.obstacles;

import java.awt.*;

public class Obstacle {


    //shape yada explosive üstünden strategy olabilir mi diye sor
    //burası için efficient bi çözüm bul
    private String name;
    private int L = 100;
    private int firmness;
    private int position_x;
    private int position_y;
    private int radius= 15;
    private Dimension area;
    private boolean movement = false;
    private boolean isRectangle = false;
    private boolean isCircular = false;
    private boolean isExplosive = false;
    private boolean gift = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGift() {
        return gift;
    }

    public void setGift(boolean gift) {
        this.gift = gift;
    }

    public boolean isMovement() {
        return movement;
    }

    public void setMovement(boolean movement) {
        this.movement = movement;
    }

    public int getPosition_x() {
        return position_x;
    }

    public void setPosition_x(int position_x) {
        this.position_x = position_x;
    }

    public int getPosition_y() {
        return position_y;
    }

    public void setPosition_y(int position_y) {
        this.position_y = position_y;
    }

    public int getFirmness() {
        return firmness;
    }

    public void setFirmness(int firmness) {
        this.firmness = firmness;
    }

    public boolean isRectangle() {
        return isRectangle;
    }

    public void setRectangle(boolean rectangle) {
        isRectangle = rectangle;
    }

    public Dimension getArea() {
        return area;
    }

    public void setArea() {
        if (isRectangle) {
            area.height = L / 5;
            area.width = 20;
        }
        if (isCircular) {
            radius = 15;
        }
    }

    public boolean isCircular() {
        return isCircular;
    }

    public void setCircular(boolean circular) {
        if (circular) setExplosive(true);
        isCircular = circular;
    }

    public boolean isExplosive() {
        return isExplosive;
    }

    public void setExplosive(boolean explosive) {
        isExplosive = explosive;
    }

}
