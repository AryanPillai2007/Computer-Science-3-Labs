// BinaryMaze.java
import java.io.*;
import java.util.*;

public class BinaryMaze {
    static class Point {
        int r, c, dist;
        Point(int r, int c, int dist) {
            this.r = r; this.c = c; this.dist = dist;
        }
    }

    static int[] dr = {-1, 1, 0, 0}; // directions (up/down/left/right)
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        // Loads that .dat file.
        Scanner sc = new Scanner(new File("/Users/aryanpillai2701/Library/On Disk/Files/CS3/Computer-Science-3-Labs/Lab21 - GraphIntro/src/maze.dat"));
        int R = sc.nextInt(), C = sc.nextInt(), T = sc.nextInt();
        int[][] grid = new int[R][C];

        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                grid[i][j] = sc.nextInt();

        for (int t = 0; t < T; t++) {
            int sr = sc.nextInt(), sc1 = sc.nextInt(), er = sc.nextInt(), ec = sc.nextInt();
            System.out.println(bfs(grid, R, C, sr, sc1, er, ec));
        }

        sc.close();
    }

    static int bfs(int[][] maze, int R, int C, int sr, int sc, int er, int ec) {
        boolean[][] visited = new boolean[R][C];
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(sr, sc, 0));
        visited[sr][sc] = true;

        while (!q.isEmpty()) {
            Point curr = q.poll();
            if (curr.r == er && curr.c == ec) return curr.dist;

            for (int i = 0; i < 4; i++) {
                int nr = curr.r + dr[i];
                int nc = curr.c + dc[i];
                if (nr >= 0 && nc >= 0 && nr < R && nc < C &&
                        maze[nr][nc] == 1 && !visited[nr][nc]) {
                    q.add(new Point(nr, nc, curr.dist + 1));
                    visited[nr][nc] = true;
                }
            }
        }

        return -1;
    }
}
