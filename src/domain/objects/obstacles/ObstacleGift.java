package domain.objects.obstacles;

public class ObstacleGift extends Obstacle {

    public ObstacleGift() {
        super();
        double rand = Math.random();
        int firmness = 1;
        setRectangle(true);
        setFirmness(firmness);
        setArea();
        setMovement(rand <= 0.2); //hareket var mı sor
        setGift(true);
        setName("Gift of Uranus");
    }
}
