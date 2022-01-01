package domain.objects;


public class Ablities {
    //private static final int LONGER_TIME = 30000;
    private final int L = 1200;
    private Paddle paddle = Paddle.getInstance(L / 10, L / 2);






    public Ablities() {

    }


    private void chanceGivingAbility() {
    }
    public void noblePhantasmExpansion() {
        paddle.startSlowness(30000);
        int paddleWidth = paddle.getWidth();
        int centerX = paddle.getX() + paddleWidth / 2;
        paddle.setX(centerX-paddleWidth);
        paddle.setWidth(paddleWidth*2);


    }




    private void magicalHex() {
    }
    private void unstoppableEnchantedSphere() {

    }


}
