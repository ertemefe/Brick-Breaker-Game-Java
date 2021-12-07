package domain.objects.obstacles;

import java.awt.*;

public class Obstacle {

    private String name;
    private int L;
    private int firmness;
    private int positionX;
    private int positionY;
    private boolean movement = false;
    private boolean isExplosive = false;
    private boolean gift = false;
    private Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

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

    public int getL() {
        return L;
    }

    public void setL(int l) {
        L = l;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getFirmness() {
        return firmness;
    }

    public void setFirmness(int firmness) {
        this.firmness = firmness;
    }

    public boolean isExplosive() {
        return isExplosive;
    }

    public void setExplosive(boolean explosive) {
        isExplosive = explosive;
    }

    public Graphics draw(Graphics g) {
        if (isExplosive()) {
            g.setColor(getColor());
            g.fillOval(getPositionX(), getPositionY(), 15, 15);
            return g;
        } else {
            g.setColor(getColor());
            g.fillRect(getPositionX(), getPositionY(), getL() / 5, 20);
            if (getFirmness() > 1) g.drawString(String.valueOf(firmness), 0, 0);
            return g;
        }
    }
}
