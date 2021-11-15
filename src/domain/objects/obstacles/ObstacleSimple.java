package domain.objects.obstacles;

public class ObstacleSimple extends Obstacle {

    public ObstacleSimple() {
        super();
        double rand = Math.random();
        int firmness = 1;
        setRectangle(true);
        setFirmness(firmness);
        setArea();
        setMovement(rand <= 0.2);
        setGift(false);
        setName("Wall Maria");
    }
}
