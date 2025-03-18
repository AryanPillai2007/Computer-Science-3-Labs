public class FireModel
{
    public static int SIZE = 47;
    private FireCell[][] myGrid;
    private FireView myView;

    public FireModel(FireView view)
    {
        myGrid = new FireCell[SIZE][SIZE];
        int setNum = 0;
        for (int r=0; r<SIZE; r++)
        {
            for (int c=0; c<SIZE; c++)
            {
                myGrid[r][c] = new FireCell();
            }
        }
        myView = view;
        myView.updateView(myGrid);
    }

    private void spread(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE || !myGrid[row][col].burn()) {
            return;
        }
        myGrid[row][col].setStatus(FireCell.BURNING);
        spread(row - 1, col);
        spread(row + 1, col);
        spread(row, col - 1);
        spread(row, col + 1);
    }
    public void solve() {
        for (int col = 0; col < SIZE; col++) {
            if (myGrid[SIZE - 1][col].getStatus() == FireCell.GREEN) {
                spread(SIZE - 1, col);
            }
        }
        boolean danger = false;
        for (int col = 0; col < SIZE; col++) {
            if (myGrid[0][col].getStatus() == FireCell.BURNING) {
                danger = true;
                break;
            }
        }
        if (danger) {
            System.out.println("Onett is in trouble!!");
        }
        else {
            System.out.println("Onett is safe!!");
        }
        myView.updateView(myGrid);
    }
}
