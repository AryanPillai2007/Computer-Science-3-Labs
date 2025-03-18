import java.util.LinkedList;

public class PhoneBook implements IMap {
    private static final int INITIAL_CAPACITY = 16;
    // Best Initial Capacity of a Hashmap
    private static final double LOAD_FACTOR = 0.75;
    // If 75 Percent is exceeded, double table capacity and re-hash the table's contents.

    private Entry[] table;
    private int size;

    public PhoneBook() {
        this.table = new Entry [INITIAL_CAPACITY];
        this.size = 0;
    }

    private static class Entry {
        Person key;
        PhoneNumber value;
        Entry next;

        Entry (Person key, PhoneNumber value)
        {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    public PhoneNumber put(Person person, PhoneNumber phone) {
        int index = getIndex(person);
        Entry current = table[index];

        while (current != null) {
            if (current.key.equals(person)) {
                PhoneNumber oldValue = current.value;
                current.value = phone;
                return oldValue;
            }
            current = current.next;
        }

        Entry newEntry = new Entry(person, phone);
        newEntry.next = table[index];
        table[index] = newEntry;
        size++;

        if ((double) size / table.length > LOAD_FACTOR) {
            resize();
        }

        return null;
    }

    public PhoneNumber get(Person person) {
        int index = getIndex(person);
        Entry current = table[index];

        while (current != null) {
            if (current.key.equals(person)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public PhoneNumber remove(Person person) {
        int index = getIndex(person);
        Entry current = table[index];
        Entry previous = null;

        while (current != null) {
            if (current.key.equals(person)) {
                if (previous == null) {
                    table[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return current.value;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }

    public int size() {
        return size;
    }

    private int getIndex(Person person) {
        return Math.abs(person.hashCode()) % table.length;
    }

    private void resize() {
        Entry[] oldTable = table;
        table = new Entry[oldTable.length * 2];
        size = 0;

        for (Entry head : oldTable) {
            while (head != null) {
                put(head.key, head.value);
                head = head.next;
            }
        }
    }
}