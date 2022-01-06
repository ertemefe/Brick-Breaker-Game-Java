package domain.objects;

import domain.Controller;
import domain.objects.obstacles.FactoryObstacle;
import domain.objects.obstacles.Obstacle;
import ui.buildingmode.EditingAreaPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Ymir {

    private static final int FROZEN_TIME = 15000;
    private static Ymir instance;
    private final int PERIOD;
    private final Random random;
    private int remainingTime;

    private Ymir(int period) {
        PERIOD = period;
        remainingTime = PERIOD;
        random = new Random();
    }

    public static Ymir getInstance(int period) {
        if (instance == null)
            instance = new Ymir(period);
        return instance;
    }

    public void updateRemainingTime(int millisecond, Ball ball, ArrayList<Obstacle> list) {
        remainingTime -= millisecond;
        if (remainingTime <= 0) {
            doAction(ball, list);
            remainingTime = PERIOD;
        }
    }

    private void doAction(Ball ball, ArrayList<Obstacle> list) {
        System.out.println("Ymir flipped the coin");

        if (random.nextInt(2) == 0) {

            switch (random.nextInt(3)) {
                case 0 -> {
                    System.out.println("Infinity void is activated");
                    infiniteVoid(list);
                }
                case 1 -> {
                    System.out.println("Double acceleration is activated");
                    doubleAccel(ball);
                }
                case 2 -> {
                    System.out.println("Hollow Purple is activated");
                    hollowPurple(list);
                }
            }
        } else System.out.println("You are lucky");
    }

    private void infiniteVoid(ArrayList<Obstacle> list) {
        for (int i = 0; i < 8; i++) {
            list.get(random.nextInt(list.size())).startFrozen(FROZEN_TIME);
        }
    }

    private void doubleAccel(Ball ball) {
        ball.startSlowness(15000);
    }

    private void hollowPurple(ArrayList<Obstacle> list) {
        for (int i = 0; i < 8; i++) {
            int loc = Controller.getInstance().spawn();
            Obstacle hollow = FactoryObstacle.getInstance().createObstacle("simple");
            hollow.setColor(new Color(100, 50, 200));
            hollow.setLocation(loc);
            hollow.setCoordinates(EditingAreaPanel.getInstance().gridList.get(loc).getLocation());
            hollow.setBrick(hollow.getCoordinates().x, hollow.getCoordinates().y, hollow.getWidth(), 20);
            list.add(hollow);
        }
    }
}
