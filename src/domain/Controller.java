package domain;

import domain.database.LoadMap;
import domain.database.SaveMap;
import domain.objects.obstacles.FactoryObstacle;
import domain.objects.obstacles.Obstacle;
import domain.objects.obstacles.ObstacleExplosive;
import ui.StatPanel;
import ui.buildingmode.EditingAreaPanel;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Controller {

    private static final Controller instance = new Controller();
    private final Random rand = new Random();
    private int random_generate = -1;
    public boolean mapID_exists;
    public int minObstacleCountSimple = 75;
    public int minObstacleCountFirm = 10;
    public int minObstacleCountExplosive = 5;
    public int minObstacleCountGift = 10;
    public int screenWidth = 1200;
    public Color purple = new Color(100, 50, 200);
    public ArrayList<Integer> spawnLocation = new ArrayList<>();
    public HashMap<Integer, Obstacle> obstacles = new HashMap<>();
    public ArrayList<String> mapIDList = new ArrayList<>();


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
                return FactoryObstacle.getInstance().createObstacle("firm");
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

    public void addObstacle(String type) {
        Obstacle obstacle = FactoryObstacle.getInstance().createObstacle(type);
        int loc = spawn();
        obstacle.setLocation(loc);
        obstacles.put(loc, obstacle);
        //obstacle.setCoordinates(EditingAreaPanel.getInstance().gridList.get(loc).getLocation());
        //obstacles.get(loc).setCoordinates(EditingAreaPanel.getInstance().gridList.get(loc).getLocation());
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

    public void setObstacleCoordinates() {
        for (int i = 1; i < spawnLocation.size(); i++) {
            obstacles.get(spawnLocation.get(i)).setCoordinates
                    (EditingAreaPanel.getInstance().gridList.get(spawnLocation.get(i)).getLocation());
        }
    }

    public void saveMap(String map_id) throws SQLException {
        SaveMap map = new SaveMap();
        List<Obstacle> obstacleList = new ArrayList<>(obstacles.values());
        mapID_exists = map.exists(map_id);
        if (!mapID_exists) map.saveValue(obstacleList, map_id);
    }

    public GamePanel gamePanel() {
        return GamePanel.getInstance();
    }

    public void initializeBricks() {
        for (Obstacle o : obstacles.values()) {
            if (o instanceof ObstacleExplosive)
                o.setBrick(o.getCoordinates().x, o.getCoordinates().y, o.getWidth(), o.getWidth());
            else o.setBrick(o.getCoordinates().x, o.getCoordinates().y, o.getWidth(), 20);
        }
    }

    public void maps() throws SQLException {
        LoadMap load = new LoadMap();
        for (String str : load.mapIDList) {
            if (!mapIDList.contains(str)) {
                mapIDList.add(str);
            }
        }
    }

    public void loadMap(String str) throws SQLException {
        LoadMap load = new LoadMap();
        load.getData(str);
    }

    public void hollowPurple() {
        Obstacle obstacle = FactoryObstacle.getInstance().createObstacle("simple");
        obstacle.setColor(purple);
        int loc = spawn();
        obstacle.setLocation(loc);
        obstacles.put(loc, obstacle);
        obstacles.get(loc).setCoordinates(EditingAreaPanel.getInstance().gridList.get(loc).getLocation());
        obstacle.setBrick(obstacle.getCoordinates().x, obstacle.getCoordinates().y, obstacle.getWidth(), 20);
    }

    public void setLives(int remaining) {
        StatPanel.getInstance().lives.setText("Lives: " + remaining);
    }

    public void setClock(int time) {
        StatPanel.getInstance().clock.setText("Time: " + time);
    }

    public void setScore(int score) {
        StatPanel.getInstance().score.setText("Score: " + score);
    }

}
