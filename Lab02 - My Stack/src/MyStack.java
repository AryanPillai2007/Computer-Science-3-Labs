import java.util.EmptyStackException;

public class MyStack {

    private Integer[] stack;
    private int size;

    public MyStack() {
        this(10);
    }

    public MyStack(int initCap) {
        if (initCap <= 0) {
            throw new IllegalArgumentException("Initial capacity must be greater than zero");
        }
        stack = new Integer[initCap];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Integer peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack[size - 1];
    }

    public Integer pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        Integer topElement = stack[size - 1];
        stack[size - 1] = null;
        size--;
        return topElement;
    }

    public void push(Integer item) {
        if (item == null) {
            throw new NullPointerException("Cannot push a null item onto the stack");
        }
        if (size == stack.length) {
            doubleCapacity();
        }
        stack[size] = item;
        size++;
    }
    private void doubleCapacity() {
        Integer[] newStack = new Integer[stack.length*2];
        System.arraycopy(stack, 0, newStack, 0, stack.length);
        stack = newStack;
    }
}