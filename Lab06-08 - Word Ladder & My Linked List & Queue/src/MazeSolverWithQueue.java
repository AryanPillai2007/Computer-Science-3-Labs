import java.util.List;

public class MazeSolverWithQueue extends MazeSolver {
    private MyQueue<Square> queue;

    public MazeSolverWithQueue(Maze maze) {
        super(maze);
    }

    protected void makeEmpty() {
        queue = new MyQueue<>();
    }

    protected boolean isEmpty() {
        return queue.isEmpty();
    }

    protected void add(Square s) {
        queue.offer(s);
    }

    protected Square next() {
        return queue.poll();
    }

    public void step() {
        if (isEmpty()) return;
        
        Square current = next();
        if (current.equals(maze.getExit())) {
            solved = true;
        } else {
            List<Square> neighbors = maze.getNeighbors(current);
            for (Square neighbor : neighbors) {
                if (neighbor.getType() != Square.WALL && neighbor.getStatus() == Square.UNKNOWN) {
                    neighbor.setStatus(Square.WORKING);
                }
            }
            current.setStatus(Square.EXPLORED);
        }
    }
}
