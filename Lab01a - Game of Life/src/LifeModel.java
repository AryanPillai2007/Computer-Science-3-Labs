import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.Timer;

public class LifeModel implements ActionListener {

    private static final int SIZE = 50; // Added final keyword for constant
    private LifeCell[][] grid;

    LifeView myView;
    Timer timer;

    // Constructor to load from a file or generate a random grid
    public LifeModel(LifeView view, String fileName) throws IOException {
        int r, c;
        grid = new LifeCell[SIZE][SIZE];
        for (r = 0; r < SIZE; r++) {
            for (c = 0; c < SIZE; c++) {
                grid[r][c] = new LifeCell();
            }
        }

        if (fileName == null) { // Random population
            randomizeGrid();
        } else { // Load from file
            loadFromFile(fileName);
        }

        myView = view;
        myView.updateView(grid);
    }

    // Constructor for a randomized model
    public LifeModel(LifeView view) throws IOException {
        this(view, null);
    }

    // Pause the simulation
    public void pause() {
        if (timer != null) {
            timer.stop();
        }
    }

    // Resume the simulation
    public void resume() {
        if (timer != null) {
            timer.start();
        }
    }

    // Run the simulation with a slower timer
    public void run() {
        timer = new Timer(100, this); // Increased delay to 300ms
        timer.setCoalesce(true);
        timer.start();
    }

    // Reset the grid to a new random state
    public void reset() {
        if (timer != null) {
            timer.stop();
        }
        randomizeGrid();
        myView.updateView(grid);
    }

    // Generate a random grid
    private void randomizeGrid() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                grid[r][c].setAliveNow(Math.random() > 0.85);
            }
        }
    }

    // Load initial configuration from a file
    private void loadFromFile(String fileName) throws IOException {
        Scanner input = new Scanner(new File(fileName));
        int numInitialCells = input.nextInt();
        for (int count = 0; count < numInitialCells; count++) {
            int r = input.nextInt();
            int c = input.nextInt();
            grid[r][c].setAliveNow(true);
        }
        input.close();
    }

    // Called each time the timer fires
    public void actionPerformed(ActionEvent e) {
        oneGeneration();
        myView.updateView(grid);
    }
    // Update the grid to the next generation
    private void oneGeneration() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                int aliveGuys = countAliveGuys(r, c);
                if (grid[r][c].isAliveNow()) {
                    grid[r][c].setAliveNext(aliveGuys == 2 || aliveGuys == 3);
                } else {
                    grid[r][c].setAliveNext(aliveGuys == 3);
                }
            }
        }
        // Update grid
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                grid[r][c].setAliveNow(grid[r][c].isAliveNext());
            }
        }
    }
    // Count the alive neighbors
    private int countAliveGuys(int r, int c) {
        int count = 0;
        for (int n = -1; n <= 1; n++) {
            for (int m = -1; m <= 1; m++) {
                if (n == 0 && m == 0) continue;
                int row = r + n;
                int col = c + m;
                if (row >= 0 && row <SIZE && col >= 0 && col <SIZE && grid[row][col].isAliveNow()) {
                    count++;
                }
            }
        }
        return count;
    }
}
