import java.util.EmptyStackException;

public class MyStack{
    private Square[] stack;
    private int size;

    public MyStack() {
        this(10);
    }

    public MyStack(int initCap) {
        if (initCap <= 0) {
            throw new IllegalArgumentException("must be more than zeor");
        }
        stack = new Square[initCap];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Square peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack[size - 1];
    }

    public Square pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        Square topElement = stack[size - 1];
        stack[size - 1] = null;
        size--;
        return topElement;
    }

    public void push(Square item) {
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
        Square[] newStack = new Square[stack.length * 2];
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