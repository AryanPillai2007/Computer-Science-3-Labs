import java.util.EmptyStackException;

public class MyStack<T> {
    private T[] stack;
    private int size;

    public MyStack() {
        this(10);
    }

    public MyStack(int initCap) {
        if (initCap <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
        stack = (T[]) new Object[initCap];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack[size - 1];
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T topElement = stack[size - 1];
        stack[size - 1] = null;
        size--;
        return topElement;
    }

    public void push(T item) {
        if (item == null) {
            throw new NullPointerException("Can't push a null");
        }
        if (size == stack.length) {
            doubleCapacity();
        }
        stack[size] = item;
        size++;
    }

    private void doubleCapacity() {
        T[] newStack = (T[]) new Object[stack.length * 2];
        System.arraycopy(stack, 0, newStack, 0, stack.length);
        stack = newStack;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            stack[i] = null;
        }
        size = 0;
    }
}
