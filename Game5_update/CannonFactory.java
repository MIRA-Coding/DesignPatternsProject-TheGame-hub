package Game5_update;
public class CannonFactory {
    public static Cannon createCannon(String type) {
        switch (type) {
            case "ball1":
                return new StrongCannon();
            case "ball2":
                return new MediumCannon();
            case "ball3":
                return new WeakCannon();
            default:
                return new StrongCannon();
        }
    }
}