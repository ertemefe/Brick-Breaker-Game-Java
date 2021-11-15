package domain.database;

import domain.objects.Ball;
import domain.objects.Paddle;
import domain.objects.obstacles.ObstacleExplosive;
import domain.objects.obstacles.ObstacleFirm;
import domain.objects.obstacles.ObstacleGift;
import domain.objects.obstacles.ObstacleSimple;

import java.util.ArrayList;

public class DataGame {
    String username;
    int score;
    int lives_left;
    int time;
    int paddle_location_x;
    int paddle_location_y;
    int ball_location_x;
    int ball_location_y;
    Paddle paddle;
    Ball ball;
    int simple_obstacle_count;
    int firm_obstacle_count;
    int explosive_obstacle_count;
    int gift_obstacle_count;
    ArrayList<ObstacleSimple> simpleObstacles;
    ArrayList<ObstacleFirm> firmObstacles;
    ArrayList<ObstacleExplosive> explosiveObstacles;
    ArrayList<ObstacleGift> giftObstacles;


}