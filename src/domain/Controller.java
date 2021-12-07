package domain;

import domain.database.DataMap;
import domain.objects.Ball;
import domain.objects.obstacles.FactoryObstacle;
import domain.objects.obstacles.Obstacle;

public class Controller {
    public int minObstacleCountSimple=75;
    public int minObstacleCountFirm=10;
    public int minObstacleCountExplosive=5;
    public int minObstacleCountGift=10;

    public int obstacleCountSimpleAdd=0;
    public int obstacleCountFirmAdd=0;
    public int obstacleCountExplosiveAdd=0;
    public int obstacleCountGiftAdd=0;

    public Obstacle obstacleSimple = FactoryObstacle.getInstance().createObstacle("simple");
    public Obstacle obstacleFirm = FactoryObstacle.getInstance().createObstacle("firm");
    public Obstacle obstacleExplosive = FactoryObstacle.getInstance().createObstacle("explosive");
    public Obstacle obstacleGift = FactoryObstacle.getInstance().createObstacle("gift");

    DataMap saveMap = new DataMap();




}
