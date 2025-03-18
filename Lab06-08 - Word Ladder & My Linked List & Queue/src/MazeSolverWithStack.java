public class MazeSolverWithStack extends MazeSolver {
    private MyStack<Square> stack;

    public MazeSolverWithStack(Maze maze) {
        super(maze);
        stack = new MyStack<>();
    }

    @Override
    protected void makeEmpty() {
        stack = new MyStack<>();
    }

    @Override
    protected boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    protected void add(Square s) {
        stack.push(s);
    }

    @Override
    protected Square next() {
        return stack.pop();
    }
}