public class FireCell {
    public static final int DIRT = 0, GREEN = 1, BURNING = 2;
    private int status;
    public FireCell() {
        if (Math.random() <= 0.60) {
            status = GREEN;
        } else {
            status = DIRT;
        }
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int newStatus) {
        status = newStatus;
    }
    public boolean burn() {
        return status == GREEN;
    }
}