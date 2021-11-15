package domain.objects.obstacles;

public class ObstacleExplosive extends Obstacle {

    public ObstacleExplosive() {
        super();
        double rand = Math.random();
        int firmness = 1;
        setCircular(true);
        setFirmness(firmness);
        setArea();
        setMovement(rand <= 0.2);
        setGift(false);
        setName("Pandora’s Box");
    }
}
