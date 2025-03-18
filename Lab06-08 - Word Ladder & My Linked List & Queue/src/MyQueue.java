public class MyQueue<T> implements QueueADT<T> {

    private MyLinkedList<T> queue; // the "backbone" of the queue

    // Default constructor
    public MyQueue() {
        this.queue = new MyLinkedList<>();
    }

    // Var-args constructor
    @SafeVarargs
    public MyQueue(T... items) {
        this();
        for (T item : items) {
            offer(item); // add each item to the queue
        }
    }

    // Check if the queue is empty
    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    // Add an element to the queue (enqueue operation)
    @Override
    public void offer(T item) {
        queue.add(item); // add at the end of the list
    }

    // Remove and return the element at the head of the queue (dequeue operation)
    @Override
    public T poll() {
        if (isEmpty()) {
            return null;
        }
        return queue.remove(0); // remove from the head
    }

    // Return the number of elements in the queue
    @Override
    public int size() {
        return queue.size();
    }

    // Empty the queue
    @Override
    public void clear() {
        while (!isEmpty()) {
            poll(); // remove each item
        }
    }

    // Peek at the head element without removing it
    @Override
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return queue.get(0); // get the head element without removing
    }

    // Override toString for display purposes
    @Override
    public String toString() {
        return queue.toString();
    }
}
