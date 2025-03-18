import java.util.Arrays;

public class MinHeapGeneric<T extends Comparable<T>> {
    private T[] heap;
    private int size;
    private static final int DEFAULT_CAPACITY = 8;

    public MinHeapGeneric() {
        this(DEFAULT_CAPACITY);
    }

    public MinHeapGeneric(int capacity) {
        heap = (T[]) new Comparable[capacity + 1];
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T peekMinimum() {
        return isEmpty() ? null : heap[1];
    }

    private int getLeftChildIndex(int index) {
        int left = 2 * index;
        return left <= size ? left : -1;
    }

    private int getRightChildIndex(int index) {
        int right = 2 * index + 1;
        return right <= size ? right : -1;
    }

    private int getParentIndex(int index) {
        return index > 1 ? index / 2 : -1;
    }

    private void doubleCapacity() {
        heap = Arrays.copyOf(heap, heap.length * 2);
    }

    public void insert(T value) {
        if (size + 1 == heap.length) {
            doubleCapacity();
        }
        heap[++size] = value;
        bubbleUp(size);
    }

    private void bubbleUp(int index) {
        int parentIndex = getParentIndex(index);
        if (parentIndex != -1 && heap[index].compareTo(heap[parentIndex]) < 0) {
            swap(index, parentIndex);
            bubbleUp(parentIndex);
        }
    }

    public T popMinimum() {
        if (isEmpty()) return null;
        T min = heap[1];
        heap[1] = heap[size--];
        siftDown(1);
        return min;
    }

    private void siftDown(int index) {
        int left = getLeftChildIndex(index);
        int right = getRightChildIndex(index);
        int smallest = index;

        if (left != -1 && heap[left].compareTo(heap[smallest]) < 0) {
            smallest = left;
        }
        if (right != -1 && heap[right].compareTo(heap[smallest]) < 0) {
            smallest = right;
        }
        if (smallest != index) {
            swap(index, smallest);
            siftDown(smallest);
        }
    }

    private void swap(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    @Override
    public String toString() {
        if (size == 0) return "Heap is empty";
        StringBuilder output = new StringBuilder();
        for (int i = 1; i <= size; i++) {
            output.append(heap[i]).append(", ");
        }
        return output.substring(0, output.length() - 2);
    }

    public void display() {
        int nBlanks = 32, itemsPerRow = 1, column = 0, j = 1;
        String dots = "...............................";
        System.out.println(dots + dots);
        while (j <= this.getSize()) {
            if (column == 0)
                for (int k = 0; k < nBlanks; k++)
                    System.out.print(' ');

            System.out.print(heap[j] == null ? "" : heap[j]);

            if (++column == itemsPerRow) {
                nBlanks /= 2;
                itemsPerRow *= 2;
                column = 0;
                System.out.println();
            } else
                for (int k = 0; k < nBlanks * 2 - 2; k++)
                    System.out.print(' ');

            j++;
        }
        System.out.println("\n" + dots + dots);
    }

    @SafeVarargs
    public MinHeapGeneric(T... nums) {
        this(nums.length);
        System.arraycopy(nums, 0, heap, 1, nums.length);
        size = nums.length;
        buildHeap();
    }

    private void buildHeap() {
        for (int i = size / 2; i > 0; i--) {
            siftDown(i);
        }
    }

    public static void main(String[] args) {
        MinHeapGeneric<String> stringHeap = new MinHeapGeneric<>();
        stringHeap.insert("banana");
        stringHeap.insert("apple");
        stringHeap.insert("cherry");

        System.out.println("Heap: " + stringHeap);
        System.out.println("Min: " + stringHeap.popMinimum());
        System.out.println("Heap after pop: " + stringHeap);

        MinHeapGeneric<Integer> intHeap = new MinHeapGeneric<>(10, 20, 5, 7, 3, 17, 2);
        System.out.println("Heap (int): " + intHeap);
        System.out.println("Min: " + intHeap.popMinimum());
        System.out.println("Heap after pop: " + intHeap);
    }
}