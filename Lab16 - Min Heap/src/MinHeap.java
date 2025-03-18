import java.util.Arrays;

public class MinHeap {
    private Integer[] heap;
    private int size;
    private static final int DEFAULT_CAPACITY = 8;

    public MinHeap() {
        this(DEFAULT_CAPACITY);
    }

    public MinHeap(int capacity) {
        heap = new Integer[capacity + 1];
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Integer peekMinimum() {
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

    public void insert(int value) {
        if (size + 1 == heap.length) {
            doubleCapacity();
        }
        heap[++size] = value;
        bubbleUp(size);
    }

    private void bubbleUp(int index) {
        int parentIndex = getParentIndex(index);
        if (parentIndex != -1 && heap[index] < heap[parentIndex]) {
            swap(index, parentIndex);
            bubbleUp(parentIndex);
        }
    }

    public Integer popMinimum() {
        if (isEmpty()) return null;
        int min = heap[1];
        heap[1] = heap[size--];
        siftDown(1);
        return min;
    }

    private void siftDown(int index) {
        int left = getLeftChildIndex(index);
        int right = getRightChildIndex(index);
        int smallest = index;

        if (left != -1 && heap[left] < heap[smallest]) {
            smallest = left;
        }
        if (right != -1 && heap[right] < heap[smallest]) {
            smallest = right;
        }
        if (smallest != index) {
            swap(index, smallest);
            siftDown(smallest);
        }
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    @Override
    public String toString() {
        String output = "";

        for (int i = 1; i <= getSize(); i++)
            output += heap[i] + ", ";

        return output.substring(0, output.lastIndexOf(",")); //lazily truncate last comma
    }

    /**
     * method borrowed with minor modifications from the internet somewhere, for printing
     */
    public void display() {
        int nBlanks = 32, itemsPerRow = 1, column = 0, j = 1;
        String dots = "...............................";
        System.out.println(dots + dots);
        while (j <= this.getSize()) {
            if (column == 0)
                for (int k = 0; k < nBlanks; k++)
                    System.out.print(' ');

            System.out.print((heap[j] == null) ? "" : heap[j]);
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


        public MinHeap(Integer...nums) {
            this(nums.length);
            System.arraycopy(nums, 0, heap, 1, nums.length);
            size = nums.length;
            buildHeap();
        }

        private void buildHeap () {
            for (int i = size / 2; i > 0; i--) {
                siftDown(i);
            }
        }
    }