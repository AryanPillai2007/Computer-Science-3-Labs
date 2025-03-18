import java.util.Random;

enum GemType {
    GREEN, BLUE, ORANGE;
}

public class Gem {
    private GemType type;
    private int points;
    private static final int[] POINT_VALUES = { 0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50 };
    private static final String[] IMAGE_FILES = { "gem_green.png", "gem_blue.png", "gem_orange.png" };

    public Gem(GemType type, int points) {
        this.type = type;
        this.points = points;
    }

    public Gem() {
        Random rand = new Random();
        this.type = GemType.values()[rand.nextInt(GemType.values().length)];
        this.points = POINT_VALUES[rand.nextInt(POINT_VALUES.length)];
    }

    public GemType getType() {
        return type;
    }

    public int getPoints() {
        return points;
    }

    public void draw(double x, double y) {
        int index = type.ordinal();
        StdDraw.picture(x, y, IMAGE_FILES[index]);
        StdDraw.text(x, y, Integer.toString(points));
    }

    @Override
    public String toString() {
        return type + " " + points;
    }
}
