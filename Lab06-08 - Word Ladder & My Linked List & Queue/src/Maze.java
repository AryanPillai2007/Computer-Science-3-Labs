import java.io.*;
import java.util.*;

public class Maze {
    private Square[][] maze;
    private Square start;
    private Square exit;

    public Maze() {}

    public boolean loadMaze(String fileName) {
        try (Scanner sc = new Scanner(new File(fileName))) {
            int rows = sc.nextInt();
            int cols = sc.nextInt();
            maze = new Square[rows][cols];

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    int type = sc.nextInt();
                    maze[row][col] = new Square(row, col, type);
                    if (type == Square.START) {
                        start = maze[row][col];
                    } else if (type == Square.EXIT) {
                        exit = maze[row][col];
                    }
                }
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error loading maze file:" + fileName);
            return false;
        }
    }
    public List<Square> getNeighbors(Square s) {
        List<Square> neighbors = new ArrayList<>();
        int row = s.getRow(), col = s.getCol();

        if (row > 0) neighbors.add(maze[row - 1][col]);
        if (col > 0) neighbors.add(maze[row][col -1]);
        if (row < maze.length - 1) neighbors.add(maze[row+1][col]);
        if (col < maze[0].length - 1) neighbors.add(maze[row][col+1]);

        return neighbors;
    }
    public Square getStart() { return start; }
    public Square getExit() { return exit; }

    public void reset() {
        for (Square[] row : maze) {
            for (Square square : row) {
                if (square.getType() == Square.EMPTY) {
                    square.setStatus(Square.UNKNOWN);
                }
            }
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Square[] row : maze) {
            for (Square square : row) {
                sb.append(square.toString()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
