package domain;

import domain.objects.obstacles.FactoryObstacle;
import domain.objects.obstacles.Obstacle;

import java.awt.*;
import java.util.HashMap;
import java.util.Random;

public class Controller {

    private final Random rand = new Random();
    public int minObstacleCountSimple = 75;
    public int minObstacleCountFirm = 10;
    public int minObstacleCountExplosive = 5;
    public int minObstacleCountGift = 10;
    public int screenWidth = 1200;
    public HashMap<Integer, Integer> spawnLocation = new HashMap<>();
    public HashMap<Integer, Integer> simpleLoc = new HashMap<>();
    public HashMap<Integer, Integer> firmLoc = new HashMap<>();
    public HashMap<Integer, Integer> explosiveLoc = new HashMap<>();
    public HashMap<Integer, Integer> giftLoc = new HashMap<>();
    private int random_generate = -1;
    private int loc;

    public Controller() {
        spawnLocation.put(-1, -1);
    }

    private int spawn() {
        while (spawnLocation.containsValue(random_generate))
            random_generate = rand.nextInt(400);
        spawnLocation.put(random_generate, random_generate);
        return random_generate;
    }

    public void updateCoordinates(Obstacle obstacle, int loc) {
        Point p = new Point();
        int height = -1;
        if (0 <= loc && loc < 40) {
            height = 0;
        }
        else if (40 <= loc && loc < 80) {
            height = 1;
        }
        else if (80 <= loc && loc < 120) {
            height = 2;
        }
        else if (120 <= loc && loc < 160) {
            height = 3;
        }
        else if (160 <= loc && loc < 200) {
            height = 4;
        }
        else if (200 <= loc && loc < 240) {
            height = 5;
        }
        else if (240 <= loc && loc < 280) {
            height = 6;
        }
        else if (280 <= loc && loc < 320) {
            height = 7;
        }
        else if (320 <= loc && loc < 360) {
            height = 8;
        }
        else if (360 <= loc && loc < 400) {
            height = 9;
        }

        p.y = 25 * height;
        p.x = 30 * (loc % 10);
        obstacle.setCoordinates(p);
        System.out.println(p);
    }


    public Obstacle addNewObstacle(String str) {
        switch (str) {
            case "simple" -> {
                Obstacle simple = FactoryObstacle.getInstance().createObstacle("simple");
                loc = spawn();
                simple.setInitialSpawnLocation(loc);
                simpleLoc.put(loc, loc);
                return simple;
            }
            case "firm" -> {
                Obstacle firm = FactoryObstacle.getInstance().createObstacle("firm");
                loc = spawn();
                firm.setInitialSpawnLocation(loc);
                firmLoc.put(loc, loc);
                return firm;
            }
            case "explosive" -> {
                Obstacle explosive = FactoryObstacle.getInstance().createObstacle("explosive");
                loc = spawn();
                explosive.setInitialSpawnLocation(loc);
                explosiveLoc.put(loc, loc);
                return explosive;
            }
            case "gift" -> {
                Obstacle gift = FactoryObstacle.getInstance().createObstacle("gift");
                loc = spawn();
                gift.setInitialSpawnLocation(loc);
                giftLoc.put(loc, loc);
                return gift;
            }
            default -> {
                return null;
            }
        }
    }


}
