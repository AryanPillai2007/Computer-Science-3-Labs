import java.util.ArrayList;
import java.util.List;

public class Disk implements Comparable<Disk> {
    private static int counter = 0;
    private final int id;

    private final int capacity = 1_000_000;
    private int space;
    private List<Integer> files;

    public Disk()
    {
        this.id = counter++;
        this.space = capacity;
        this.files = new ArrayList<>();
    }

    public boolean addFile(int fileSize) {
        if (fileSize <= space) {
            files.add(fileSize);
            space -= fileSize;
            return true;
        }
        return false;
    }

    public int getRemainingSpace() {
        return space;
    }

    public int getId() {
        return id;
    }

    public List<Integer> getFiles() {
        return files;
    }

    @Override
    public int compareTo(Disk other) {
        return Integer.compare(other.space, this.space);
    }

    @Override
    public String toString() {
        return id + " " +
                space +
                ": " + files;
    }
}
