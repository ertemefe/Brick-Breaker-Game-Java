package domain.objects.obstacles;

public class ObstacleFirm extends Obstacle {

    public ObstacleFirm() {
        super();
        double rand = Math.random();
        int firmness = 1;
        setRectangle(true);
        setFirmness(firmness);
        setArea();
        setMovement(rand <= 0.2);
        setGift(false);
        setName("Steins Gate");
    }
}