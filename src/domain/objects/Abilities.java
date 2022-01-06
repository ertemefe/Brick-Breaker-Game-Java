package domain.objects;

public class Abilities {
    public static boolean unstoppableActive = false;
    public static boolean expansionActive = false;
    public static boolean hexActive = false;
    //private static final int LONGER_TIME = 30000;
    private final int L = 1200;
    private Paddle paddle = Paddle.getInstance();
    private int initCounter = 0;
    private int expansionAbilityCount = 0;
    private int hexAbilityCount = 0;
    private int unstoppableAbilityCount = 0;

    public Abilities() {}

    public void activateUnstoppableEnchantedSphere(Ball ball) {
        ball.startUnstoppable(30000);
        ball.setDamage(999);
        unstoppableActive = true;
    }

    public static void deactivateUnstoppableEnchantedSphere(Ball ball) {
        ball.setDamage(1);
        unstoppableActive = false;
    }

    public static void deactivateExpansion(Paddle paddle) {
        expansionActive = false;
        paddle.setWidth(paddle.getWidth() / 2);
    }

    public static void deactivateHex() {
        hexActive = false;
    }

    public void activateExpansion() {
        if (!expansionActive && expansionAbilityCount > 0) {
            expansionActive = true;
            paddle.startSlowness(10000);
            paddle.setWidth(paddle.getWidth() * 2);
            expansionAbilityCount--;
        }
    }

    public void activateHex() {
        if (!hexActive && hexAbilityCount > 0) {
            hexActive = true;
            paddle.startHex(30000);
            hexAbilityCount--;
        }
    }

    public int getExpansionAbilityCount() {
        return expansionAbilityCount;
    }

    public void setExpansionAbilityCount(int expansionAbilityCount) {
        this.expansionAbilityCount = expansionAbilityCount;
    }

    public int getHexAbilityCount() {
        return hexAbilityCount;
    }

    public void setHexAbilityCount(int hexAbilityCount) {
        this.hexAbilityCount = hexAbilityCount;
    }

    public int getUnstoppableAbilityCount() {
        return unstoppableAbilityCount;
    }

    public void setUnstoppableAbilityCount(int unstoppableAbilityCount) {
        this.unstoppableAbilityCount = unstoppableAbilityCount;
    }

}
