package domain.objects.obstacles;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ObstacleFirm extends Obstacle {

    public ObstacleFirm() {
        super();
        setFirmness((new Random().nextInt(4)) + 1);
        setMovement(Math.random() <= 0.2);
        setName("Stein's Gate");
        setColor(Color.lightGray);
        setImage(image());
    }

    private JPanel image() {
        JPanel firm = new JPanel();
        JLabel l = new JLabel(String.valueOf(getFirmness()));
        firm.setPreferredSize(new Dimension(getWidth() / 5, 20));
        firm.setBackground(getColor());
        firm.add(l);
        firm.setVisible(true);
        return firm;
    }
}