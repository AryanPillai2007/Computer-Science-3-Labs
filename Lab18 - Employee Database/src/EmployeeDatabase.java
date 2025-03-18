import java.util.Arrays;
public class EmployeeDatabase {
    private Employee[] table;
    private int size;
    private static final int DEFAULT_CAPACITY = 11;
    private static final double LOAD_FACTOR = 0.7;
   
    public EmployeeDatabase() {
        this(DEFAULT_CAPACITY);
    }
    public EmployeeDatabase(int capacity) {
        table = new Employee[capacity];
        size = 0;
    }
    private int hash(int EID) {
        return EID % table.length;
    }
    public boolean add(Employee key) {
        if (contains(key)) return false;
        if ((double) size / table.length > LOAD_FACTOR) {
            resize(); 
        }
        int index = hash(key.getEID());
        int originalIndex = index;
        int i = 1;

        // Linear-Probing
        while (table[index] != null) {
            index = (originalIndex + i) % table.length;
            i++;
        }
        table[index] = key;
        size++;
        return true;
    }

    public boolean contains(Employee key) {
        int index = hash(key.getEID());
        int originalIndex = index;
        int i = 1;

        while (table[index] != null) {
            if (table[index].equals(key)) return true;
            index = (originalIndex + i) % table.length;
            i++;
        }
        return false;
    }

    public boolean remove(Employee key) {
        int index = hash(key.getEID());
        int originalIndex = index;
        int i = 1;

        while (table[index] != null) {
            if (table[index].equals(key)) {
                table[index] = null;
                size--;
                rehasher(index);
                return true;
            }
            index = (originalIndex + i) % table.length;
            i++;
        }
        return false;
    }

    private void rehasher(int start) {
        int index = (start + 1) % table.length;
        while (table[index] != null) {
            Employee temp = table[index];
            table[index] = null;
            size--;
            add(temp);
            index = (index + 1) % table.length;
        }
    }

    private void resize() {
        Employee[] oldTable = table;
        table = new Employee[oldTable.length * 2];
        size = 0;

        for (Employee employee : oldTable) {
            if (employee != null) {
                add(employee);
            }
        }
    }

    public int size() {
        return size;
    }
}
