import java.util.Objects;
import java.util.*;
import java.util.HashMap;
import java.util.LinkedList;

public class MyHashTable
        <K, V> {
    private static final int INITIAL_CAPACITY = 16;
    // Best Initial Capacity of a Hashmap
    private static final double LOAD_FACTOR = 0.75;
    // If 75 Percent is exceeded, double table capacity and re-hash the table's contents.

    private LinkedList[] table;
    private int size;

    public MyHashTable() {
        table = new LinkedList[INITIAL_CAPACITY];
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            table[i] = new LinkedList<>();
        }
        size = 0;
    }

    private int hash (K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    public V put (K key, V value) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = table[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                V oldValue = entry.value;
                entry.value = value;
                // Update that value
                return oldValue;
            }
        } bucket.add(new Entry<>(key, value));
        size++;

        if ((double) size / table.length >= LOAD_FACTOR) {
            resize();
        }

        return null;
    }

    public V get (K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = table[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
        // Not. found.
    }

    public V remove(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = table[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                bucket.remove(entry);
                size--;
                return entry.value;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    private void resize() {
        int newCapacity = table.length * 2;
        LinkedList<Entry<K, V>>[] newTable = new LinkedList[newCapacity];

        for (int i = 0; i < newCapacity; i++) {
            newTable[i] = new LinkedList<>();
        }

        for (LinkedList<Entry<K, V>> bucket : table) {
            for (Entry<K, V> entry : bucket) {
                int newIndex = Math.abs(entry.key.hashCode()) % newCapacity;
                newTable[newIndex].add(entry);
            }
        }
        table = newTable;
    }

    private static class Entry<K, V>
    {
        K key;
        V value;

        Entry(K key, V value)
        {
            this.key = key;
            this.value = value;
        }
    }
}