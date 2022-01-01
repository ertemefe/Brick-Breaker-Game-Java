package domain.objects.obstacles;

import javax.swing.*;
import java.awt.*;


public class ObstacleHollowPurple extends Obstacle {

    public ObstacleHollowPurple() {
        super();
        setFirmness(1);
        setMovement(Math.random() <= 0.2);
        setName("Hollow Purple");
        setColor(new Color(255, 0, 210));
        setImage(image());
        setType("hollowPurple");
    }
    private JPanel image(){
        JPanel simple = new JPanel();
        simple.setPreferredSize(new Dimension(getWidth(), 20));
        simple.setBackground(getColor());
        simple.setVisible(true);
        return simple;
    }
}
