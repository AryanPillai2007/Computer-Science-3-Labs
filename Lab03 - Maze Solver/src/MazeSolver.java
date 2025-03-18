import java.util.List;

public abstract class MazeSolver {
    protected Maze maze;
    private boolean solved;

    public MazeSolver(Maze maze) {
        this.maze = maze;
        makeEmpty();
        add(maze.getStart());
    }

    protected abstract void makeEmpty();
    protected abstract boolean isEmpty();
    protected abstract void add(Square s);
    protected abstract Square next();

    public boolean isSolved() {
        return solved || isEmpty();
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
                    add(neighbor);
                    neighbor.setStatus(Square.WORKING);
                }
            }
            current.setStatus(Square.EXPLORED);
        }
    }

    public String getPath() {
        return isSolved() ? "Solved!" : "Not solved yet";
    }

    public void solve() {
        while (!isSolved()) {
            step();
        }
    }
}
