package domain;

import domain.objects.obstacles.FactoryObstacle;
import domain.objects.obstacles.Obstacle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Controller {

    private static final Controller instance = new Controller();
    private final Random rand = new Random();
    public int minObstacleCountSimple = 75;
    public int minObstacleCountFirm = 10;
    public int minObstacleCountExplosive = 5;
    public int minObstacleCountGift = 10;
    public int screenWidth = 1200;
    public ArrayList<Integer> spawnLocation = new ArrayList<>();
    public HashMap<Integer, Obstacle> obstacles = new HashMap<>();
    private int random_generate = -1;

    private Controller() {
        spawnLocation.add(-1);
    }

    public static Controller getInstance() {
        return instance;
    }


    private int spawn() {
        while (spawnLocation.contains(random_generate))
            random_generate = rand.nextInt(400);
        spawnLocation.add(random_generate);
        return random_generate;
    }

    public Obstacle addNewObstacle(String str) {
        switch (str) {
            case "simple" -> {
                return FactoryObstacle.getInstance().createObstacle("simple");
            }
            case "firm" -> {
                return  FactoryObstacle.getInstance().createObstacle("firm");
            }
            case "explosive" -> {
                return FactoryObstacle.getInstance().createObstacle("explosive");
            }
            case "gift" -> {
                return FactoryObstacle.getInstance().createObstacle("gift");
            }
            default -> {
                return null;
            }
        }
    }

    public void addObstacle(String str) {
        int loc;
        switch (str) {
            case "simple" -> {
                Obstacle simple = FactoryObstacle.getInstance().createObstacle("simple");
                loc = spawn();
                simple.setLocation(loc);
                obstacles.put(loc, simple);
            }
            case "firm" -> {
                Obstacle firm = FactoryObstacle.getInstance().createObstacle("firm");
                loc = spawn();
                firm.setLocation(loc);
                obstacles.put(loc, firm);
            }
            case "explosive" -> {
                Obstacle explosive = FactoryObstacle.getInstance().createObstacle("explosive");
                loc = spawn();
                explosive.setLocation(loc);
                obstacles.put(loc, explosive);
            }
            case "gift" -> {
                Obstacle gift = FactoryObstacle.getInstance().createObstacle("gift");
                loc = spawn();
                gift.setLocation(loc);
                obstacles.put(loc, gift);
            }
        }
    }

    public void init() {

        for (int k = 0; k < minObstacleCountSimple; k++) {
            addObstacle("simple");
        }
        for (int k = 0; k < minObstacleCountFirm; k++) {
            addObstacle("firm");
        }
        for (int k = 0; k < minObstacleCountExplosive; k++) {
            addObstacle("explosive");
        }
        for (int k = 0; k < minObstacleCountGift; k++) {
            addObstacle("gift");
        }
        for(int i =0 ; i<100;i++)
            System.out.println(spawnLocation.get(i));
    }

    public void newObstacles(String str, int number) {
        for (int i = 0; i < number; i++)
            addObstacle(str);
    }

    public void changeLocation(int oldLocation, int newLocation) {
        obstacles.put(newLocation, obstacles.get(oldLocation));
        obstacles.get(newLocation).setLocation(newLocation);
        spawnLocation.add(newLocation);
        obstacles.remove(oldLocation);
        spawnLocation.remove(Integer.valueOf(oldLocation));
    }

}
