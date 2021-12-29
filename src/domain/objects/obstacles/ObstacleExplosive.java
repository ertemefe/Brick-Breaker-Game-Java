package domain.objects.obstacles;

import javax.swing.*;
import java.awt.*;

public class ObstacleExplosive extends Obstacle {

    public ObstacleExplosive() {
        super();
        setExplosive(true);
        setFirmness(1);
        setMovement(Math.random() <= 0.2);
        setName("Pandora’s Box");
        setColor(Color.MAGENTA);
        setImage(image());
    }
    private JPanel image(){
        JPanel explosive = new JPanel();
        explosive.setPreferredSize(new Dimension(getWidth() / 5, 20));
        explosive.setBackground(getColor());
        explosive.setVisible(true);
        return explosive;
    }
}
