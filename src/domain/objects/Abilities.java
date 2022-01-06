package domain.objects;

public class Abilities {
    public boolean unstoppableActive = false;
    public boolean expansionActive = false;
    public boolean hexActive = false;
    private Paddle paddle = Paddle.getInstance();
    private int expansionAbilityCount = 0;
    private int hexAbilityCount = 0;
    private int unstoppableAbilityCount = 0;

    public Abilities() {
    }


    public void deactivateUnstoppableEnchantedSphere(Ball ball) {
        if (unstoppableActive) {
            ball.setDamage(1);
            unstoppableActive = false;
        }
    }

    public void deactivateExpansion(Paddle paddle) {
        if (expansionActive) {
            expansionActive = false;
            paddle.setWidth(paddle.getWidth() / 2);
        }
    }

    public void deactivateHex() {
        hexActive = false;
    }

    public void activateUnstoppableEnchantedSphere(Ball ball) {
        if (!unstoppableActive && unstoppableAbilityCount > 0) {
            unstoppableActive = true;
            ball.startUnstoppable(30000);
            ball.setDamage(999);
        }
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
