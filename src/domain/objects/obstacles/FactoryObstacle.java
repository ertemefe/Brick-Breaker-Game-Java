package domain.objects.obstacles;

public class FactoryObstacle {

    private static FactoryObstacle instance;

    private FactoryObstacle() {
    }

    public static FactoryObstacle getInstance() {
        if (instance == null) {
            instance = new FactoryObstacle();
        }
        return instance;
    }

    public Obstacle createObstacle(String type) {

        /*
        buraya böyle bilmem ne text field seçiliyse ve yeni sayı eski sayıdan farklıysa (?)
        yeni sayı kadar object oluştur denmeli
        yada
        */
        //ama mesela sayı küçülürse en son eklenen obstacle silinmeli
        //obstacleları min sayıdan başlatalım alt sınırdan aşağı geçmesini engelleyelim

        return switch (type) {
            case "simple" -> new ObstacleSimple();
            case "firm" -> new ObstacleFirm();
            case "explosive" -> new ObstacleExplosive();
            case "gift" -> new ObstacleGift();
            default -> null;
        };

    }
}