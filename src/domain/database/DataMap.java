package domain.database;

import domain.objects.obstacles.ObstacleExplosive;
import domain.objects.obstacles.ObstacleFirm;
import domain.objects.obstacles.ObstacleGift;
import domain.objects.obstacles.ObstacleSimple;

import java.util.ArrayList;

public class DataMap {
    int map_id;
    ArrayList<ObstacleSimple> simpleObstacles;
    ArrayList<ObstacleFirm> firmObstacles;
    ArrayList<ObstacleExplosive> explosiveObstacles;
    ArrayList<ObstacleGift> giftObstacles;
}
