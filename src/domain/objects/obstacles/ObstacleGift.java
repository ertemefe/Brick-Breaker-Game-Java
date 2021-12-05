package domain.objects.obstacles;

import java.awt.*;

public class ObstacleGift extends Obstacle {

    public ObstacleGift() {
        super();
        setGift(true);
        setFirmness(1);
        setMovement(Math.random() <= 0.2); //hareket var mı sor
        setName("Gift of Uranus");
        setColor(Color.CYAN);
    }
}
