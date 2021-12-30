package domain.objects.obstacles;

import javax.swing.*;
import java.awt.*;

public class Obstacle {

    public String name;
    public int width = 24;
    public int firmness;
    public boolean movement = false;
    public boolean isExplosive = false;
    public boolean gift = false;
    public Color color;
    private JPanel image;
    public Point coordinates;
    public int location;
    public String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int initialSpawnLocation) {
        this.location = initialSpawnLocation;
    }

    public JPanel getImage() {
        return image;
    }

    public void setImage(JPanel image) {
        this.image = image;
    }

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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getFirmness() {
        return firmness;
    }

    public void decreaseFirmness() {
        this.firmness--;
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

}
