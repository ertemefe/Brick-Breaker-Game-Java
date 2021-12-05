package domain.objects.obstacles;

import java.awt.*;


public class ObstacleSimple extends Obstacle {

    public ObstacleSimple() {
        super();
        setFirmness(1);
        setMovement(Math.random() <= 0.2);
        setName("Wall Maria");
        setColor(Color.GRAY);
    }
    public void paint(Graphics2D g){
        g.fillRect(0,0,getL()/5, 20);
    }
}
