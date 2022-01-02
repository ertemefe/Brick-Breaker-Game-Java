package domain.objects;

import domain.Controller;
import domain.objects.obstacles.Obstacle;

import java.util.Random;

public class Ymir {

    private static final int FROZEN_TIME = 15000;
    private static Ymir instance;
    private final int PERIOD;
    private final Controller controller = Controller.getInstance();
    private final Random random;
    private int remainingTime;
    private Ball ball;

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

    public void updateRemainingTime(int millisecond) {
        remainingTime -= millisecond;
        if (remainingTime <= 0) {
            doAction();
            remainingTime = PERIOD;
        }
    }

    private void doAction() {
        System.out.println("Ymir flipped the coin");

        if (random.nextInt(2) == 0) {

            switch (random.nextInt(3)) {
                case 0 -> {
                    System.out.println("Infinity void is activated");
                    infiniteVoid();
                }
                case 1 -> {
                    System.out.println("Double acceleration is activated");
                    doubleAccel();
                }
                case 2 -> {
                    System.out.println("Hollow Purple void is activated");
                    hollowPurple();
                }
            }
        }
        else System.out.println("You are lucky");
    }

    private void infiniteVoid() {
        for (int i = 0; i < 8; i++) {
            Object[] values = controller.obstacles.values().toArray();
            Obstacle randomObstacle = (Obstacle) values[random.nextInt(values.length)];
            randomObstacle.startFrozen(FROZEN_TIME);
        }
    }

    private void doubleAccel() {
        ball.startSlowness(15000);
    }

    private void hollowPurple() {
        for (int i = 0; i < 8; i++) {
            controller.hollowPurple();
        }
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }
}
