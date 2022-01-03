package domain.objects;

import domain.Controller;
import domain.objects.obstacles.Obstacle;

import java.awt.*;
import java.util.Random;

public class Ymir {

    private static final int FROZEN_TIME = 15000;
    private static Ymir instance;
    private final int PERIOD;
    private final Controller controller = Controller.getInstance();
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

    public void updateRemainingTime(int millisecond, Ball ball) {
        remainingTime -= millisecond;
        if (remainingTime <= 0) {
            doAction(ball);
            remainingTime = PERIOD;
        }
    }

    private void doAction(Ball ball) {
        System.out.println("Ymir flipped the coin");

        if (random.nextInt(2) == 0) {

            switch (random.nextInt(3)) {
                case 0 -> {
                    System.out.println("Infinity void is activated");
                    infiniteVoid();
                }
                case 1 -> {
                    System.out.println("Double acceleration is activated");
                    doubleAccel(ball);
                }
                case 2 -> {
                    System.out.println("Hollow Purple is activated");
                    hollowPurple();
                }
            }
        }
        else System.out.println("You are lucky");
    }

    private void infiniteVoid() {
        Object[] values = controller.obstacles.values().toArray();
        for (int i = 0; i < 8; i++) {
            Obstacle randomObstacle = (Obstacle) values[random.nextInt(values.length)];
            //randomObstacle.setColor(new Color(80,80,80));
            randomObstacle.startFrozen(FROZEN_TIME);
        }
    }

    private void doubleAccel(Ball ball) {
        ball.startSlowness(15000);
    }

    private void hollowPurple() {
        for (int i = 0; i < 8; i++) {
            controller.hollowPurple();
        }
    }
}
