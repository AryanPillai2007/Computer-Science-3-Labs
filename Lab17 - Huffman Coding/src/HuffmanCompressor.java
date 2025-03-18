import java.io.*;
import java.util.*;
public class HuffmanCompressor {

    // Count character freq.
    public static int[] countFrequencies(String file) throws IOException {
        int[] counts = new int[256];
        Scanner scanner = new Scanner(new File(file));
        scanner.useDelimiter("");

        while (scanner.hasNext()) {
            char ch = scanner.next().charAt(0);
            counts[ch]++;
        }
        scanner.close();
        return counts;
    }

    // file into .code and .short
    public static void compress(String file) throws IOException {
        int[] counts = countFrequencies(file);

        HuffmanTree tree = new HuffmanTree(counts);

        // structure to .code file
        String codeFile = file.replace(".txt", ".code");
        tree.write(codeFile);

        // file to .short
        String shortFile = file.replace(".txt", ".short");
        BitOutputStream bitOut = null;

        try {
            bitOut = new BitOutputStream(shortFile);
            tree.encode(bitOut, file);
        } finally {
            if (bitOut != null) {
                bitOut.close();
            }
        }
    }

    public static void expand(String codeFile, String newFile) throws IOException {
        HuffmanTree tree = new HuffmanTree(codeFile);

        // Compressed Filenames
        String shortFile = codeFile.replace(".code", ".short");
        BitInputStream bitIn = null;

        // Reading Bits, Writing into newFile
        try {
            bitIn = new BitInputStream(shortFile);
            tree.decode(bitIn, newFile);
        // Always runs, even when error
        } finally {
            if (bitIn != null) {
                bitIn.close();
            }
        }
    }

    public static void main(String[] args) {
        try {
            String file = "War and Peace.txt";
            compress(file);
            String codeFile = "War and Peace.code";
            String newFile = "War and Peace.new";
            expand(codeFile, newFile);

            System.out.println("Complete with the compression and expansion.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}