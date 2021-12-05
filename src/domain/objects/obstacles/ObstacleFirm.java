package domain.objects.obstacles;

import java.awt.*;
import java.util.Random;

public class ObstacleFirm extends Obstacle {

    public ObstacleFirm() {
        super();
        setFirmness(new Random().nextInt(5));
        setMovement(Math.random() <= 0.2);
        setName("Stein's Gate");
        setColor(Color.BLACK);
    }
}