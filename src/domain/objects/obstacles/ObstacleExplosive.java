package domain.objects.obstacles;

import domain.objects.FallingObject;

import javax.swing.*;
import java.awt.*;

public class ObstacleExplosive extends Obstacle implements FallingObject {

    public ObstacleExplosive() {
        super();
        setExplosive(true);
        setFirmness(1);
        setMovement(Math.random() <= 0.2);
        setName("Pandora’s Box");
        setColor(Color.MAGENTA);
        setImage(image());
        setType("explosive");
        setWidth(15);
    }
    private JPanel image(){
        JPanel explosive = new JPanel();
        explosive.setPreferredSize(new Dimension(getWidth(), getWidth()));
        explosive.setBackground(getColor());
        explosive.setVisible(true);
        return explosive;
    }

    @Override
    public void drawObstacle(Graphics2D g2d){
        g2d.setColor(getColor());
        g2d.fillOval(getCoordinates().x + getWidth() / 3, getCoordinates().y + getWidth() / 4, getWidth(), getWidth());
    }

    @Override
    public void fall() {
        Point coordinates = super.getCoordinates();
        super.setCoordinates(new Point(coordinates.x, coordinates.y+1));
    }

    @Override
    public int getY() {
        Point coordinates = super.getCoordinates();
        return coordinates.y;
    }
}
