public class NumberList {
    private Integer[] list;
    private int size;

//     Zero-parameter constructor
    public NumberList() {
        this.list = new Integer[2];
        this.size = 0;
    }

//     getter
    public int size() {
        return size;
    }
//     isEmpty
    public boolean isEmpty() {
        return size == 0;
    }



//     toString method
    public String toString() {
        StringBuilder appender = new StringBuilder();
        appender.append("[");
        for (int i = 0; i < size; i++) {
            appender.append(list[i]);
            if (i < size - 1) {
                appender.append(", ");
            }
        }
        appender.append("]");
        return appender.toString();
    }

//     doubleCapacity method
    private void doubleCapacity() {
        Integer[] newList = new Integer[list.length * 2];
        for (int i = 0; i < size; i++) {
            newList[i] = list[i];
        }
        list = newList; 
    }

//  --------------------------------------------------------------------------------------------------------------------------------

    //     add method
    public void add(int index, Integer val) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (size == list.length) {
            doubleCapacity();
        }
//         Shift elements to the right to make space for the new element
        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = val;
        size++;
    }
    public boolean add(Integer element) {
        add(size, element);
        return true;
    }

//    --------------------------------------------------------------------------------------------------------------------------------

    //     get method
    public Integer get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return list[index];
    }

//     set method
    public Integer set(int index, Integer value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Integer oldset = list[index];
        list[index] = value;
        return oldset;
    }

//    remove method
    public Integer remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Integer removedElement = list[index];
        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }
        list[size - 1] = null;
        size--;
        return removedElement;
    }
}

