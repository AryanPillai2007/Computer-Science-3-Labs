import java.util.*;
import java.io.*;

public class WorstFit {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input20.txt"));
        List<Integer> fileSizes = new ArrayList<>();
        while (scanner.hasNextInt()) {
            fileSizes.add(scanner.nextInt());
        }
        scanner.close();

        worstFit(fileSizes, false);
        worstFit(fileSizes, true);
    }

    private static void worstFit(List<Integer> fileSizes, boolean decreasing) {
        if (decreasing) {
            fileSizes.sort(Collections.reverseOrder());
        }

        PriorityQueue<Disk> pqueue = new PriorityQueue<>();

        for (int fileSize:fileSizes) {
            if (pqueue.isEmpty() ||
                    pqueue.peek().getRemainingSpace() < fileSize)
            {
                Disk newDisk = new Disk();
                newDisk.addFile(fileSize);
                pqueue.add(newDisk);
            } else {
                Disk topDisk = pqueue.poll();
                topDisk.addFile(fileSize);
                pqueue.add(topDisk);
            }
        }

        double gb = fileSizes.stream().mapToInt(Integer::intValue).sum() / 1_000_000.0;
        System.out.println("Total size  = " + gb + " GB");
        System.out.println("Disks required = " + pqueue.size());

        if (fileSizes.size() < 100) {
            List<Disk> sortedDisks = new ArrayList<>(pqueue);

            sortedDisks.sort(Comparator.comparingInt(Disk :: getRemainingSpace).reversed());
            for (Disk disk : sortedDisks) {
                System.out.println(disk);
            }
        }
    }
}
