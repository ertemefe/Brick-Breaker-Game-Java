package domain.objects;

import domain.Controller;
import domain.objects.obstacles.Obstacle;

import java.util.Random;

public class Ymir {

    private static final int FROZEN_TIME = 15000;
    private static Ymir instance;
    private final int PERIOD;
    private Controller controller = Controller.getInstance();
    private Random random;
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
        // todo
      //  if (random.nextBoolean())    // %50 probability
      //      return;
        int option = random.nextInt(3);  // 0: infinite void, 1: time alter, 2: hollow purple
        switch (option) {
            case 0:
                infiniteVoid();
                break;
            case 1:
                doubleAccel();
                break;
            case 2:
                hollowPurple();
                break;
        }

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

        for(int i=0 ; i<8 ; i++) {
            controller.hollowPurple();
        }
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }
}
