package domain.objects;

import domain.Controller;
import domain.objects.obstacles.Obstacle;

import java.awt.*;
import java.util.Random;

import java.util.Random;

public class Ablities {
    //private static final int LONGER_TIME = 30000;
    private static Ablities instance;
    private static final int LONG_TIME = 3000;
    private final int PERIOD;
    private Random random;
    private int remainingTime;
    private final int L = 1200;
    private Paddle paddle = Paddle.getInstance(L / 10, L / 2);





    public Ablities(int period) {
        PERIOD = period;
        remainingTime = PERIOD;
        random = new Random();
    }

    public static Ablities getInstance(int period) {
        if (instance == null)
            instance = new Ablities(period);
        return instance;
    }


    private void chanceGivingAbility() {
    }
    public void noblePhantasmExpansion() {
        int paddleWidth = paddle.getWidth();
        int centerX = paddle.getX() + paddleWidth / 2;
        paddle.setWidth(paddleWidth*2);
        paddle.setX(centerX-paddleWidth);




    }




    private void magicalHex() {
    }
    private void unstoppableEnchantedSphere() {
    }
}
