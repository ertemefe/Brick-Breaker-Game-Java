package domain.objects.obstacles;

import javax.swing.*;
import java.awt.*;

public class ObstacleGift extends Obstacle {

    public ObstacleGift() {
        super();
        setGift(true);
        setFirmness(1);
        setMovement(Math.random() <= 0.2); //hareket var mı sor
        setName("Gift of Uranus");
        setColor(Color.CYAN);
        setImage(image());
        setType("gift");
    }

    private JPanel image(){
        JPanel gift = new JPanel();
        gift.setPreferredSize(new Dimension(getWidth(), 20));
        gift.setBackground(getColor());
        gift.setVisible(true);
        return gift;
    }
}
