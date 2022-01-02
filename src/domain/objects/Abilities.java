package domain.objects;


public class Abilities {
    public static boolean unstoppableActive = false;
    public static boolean expansionActive = false;
    public static boolean hexActive = false;
    //private static final int LONGER_TIME = 30000;
    private final int L = 1200;
    private Paddle paddle = Paddle.getInstance();
    private int initCounter = 0;






    public Abilities() {

    }


    private void chanceGivingAbility(int remainingLives) {
        remainingLives++;
    }


    public void noblePhantasmExpansion() {
        if (!expansionActive) {
            expansionActive = true;
            paddle.startSlowness(30000);
            paddle.setWidth(paddle.getWidth() * 2);
        }
    }


    public void activateHex() {
        hexActive = true;
        paddle.startHex(30000);
    }

    public static void deactivateHex() {
        hexActive = false;
    }

    public static void activateUnstoppableEnchantedSphere(Ball ball) {
        ball.startUnstoppable(30000);
        ball.setDamage(999);
        unstoppableActive = true;
    }

    public static void deactivateUnstoppableEnchantedSphere(Ball ball) {
        ball.setDamage(1);
        unstoppableActive = false;
    }

}
