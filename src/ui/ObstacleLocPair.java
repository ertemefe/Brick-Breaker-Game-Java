package ui;

import domain.objects.obstacles.Obstacle;

public class ObstacleLocPair {

    public final Obstacle obstacle;
    public final int loc;

    public ObstacleLocPair(Obstacle obstacle, int loc) {
        this.obstacle = obstacle;
        this.loc = loc;
    }
}
