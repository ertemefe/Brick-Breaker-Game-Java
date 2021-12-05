package domain.objects.obstacles;

import java.awt.*;

public class ObstacleExplosive extends Obstacle {

    public ObstacleExplosive() {
        super();
        setExplosive(true);
        setFirmness(1);
        setMovement(Math.random() <= 0.2);
        setName("Pandora’s Box");
        setColor(Color.MAGENTA);
    }
}
