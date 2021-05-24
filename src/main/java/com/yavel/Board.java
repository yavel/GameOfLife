package com.yavel;

/**
 * Notes:
 * 1. The only thing that should be static in here is main(). for school i always did static so it was kinda a habit.
 * 2. Renamed to Board because that is what it is.
 * 3. Move the constructor to the top.  It should always be first after the members.
 * 4. Changed a lot of your get() calls to just use the internal board with b[r][c]
 * 5. Eventually we'll combine display and toString().
 * 6. I reorganized the project to add src and test directories.
 * 7. I added a BoardTest class.  And a couple methods for testing your countNeighbors method.  One is testCountNeighborsEmptyBoard.
 *    There will be a green arrow next to the method name.  If you click on that and select run it will make a
 *    board and call that one method.  Notice it never returns (that's your bug).  You need to spend your day fixing that.
 *    Read the comments on the countNeighbors method below.
 */
public class Board {
    private int rows;
    private int cols;
    private int[][] b;
    private BoardDisplay display;

    // creates a new board
    public Board(int rows, int cols, BoardDisplay display) {
        this.rows = rows;
        this.cols = cols;
        this.b = new int[rows][cols];
        this.display = display;
    }
    
    public void initialize() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int value = (int) (Math.random() * 3);
                if (value == 0) {
                    b[r][c] = 1;
                }
            }
        }
    }

    // next need to display board
    public void display() {
        display.clearCells();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (b[r][c] == 0) {
                    System.out.print("-");
                } else if (b[r][c] == 1) {
                    System.out.print("1");
                    display.fillCell(r, c);
                }
            }
            System.out.println();
        }
    }

    public String toString() {
        String result = "";
        for (int r = 0; r < getRows(); r++) {
            for (int c = 0; c < getCols(); c++) {
                result += String.valueOf(b[r][c]);
            }
            result += "\n";
        }
        return result;
    }

    //next need to find the next generation by looking at the cells around the main cell
    public void nextGeneration() {
        Board futureBoard = new Board(rows, cols, null);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int count = countNeighbors(r,c);
                if (b[r][c] == 1 && count < 2) //countNeighbors is going to be implemented later as a method
                {
                    futureBoard.set(r, c, 0);//underpopulation and dies
                } else if (b[r][c] == 1 && count < 4) {
                    futureBoard.set(r, c, 1);//perfect population and lives
                } else if (b[r][c] == 1 && count > 3) {
                    futureBoard.set(r, c, 0);//overpopulation and dies
                } else if (b[r][c] == 0 && count == 3) {
                    futureBoard.set(r, c, 1);//migration from other cells and becomes alive
                }
            }
        }
        b = futureBoard.getBoard();
    }

    // Do this on graph paper to visualize it.  The test I wrote creates a board and calls this method with 0,0.
    // Location 0,0 is one of the hardest ones because it's easy to get an out of bounds exception.
    // A loop might not be how you want to start.  For each call you want to check and count exactly 8 locations:
    //    row-1, col-1
    //    row-1, col
    //    row-1, col+1
    //    row, col-1
    //    row, col   // skip
    //    row, col+1
    //    row+1, col-1
    //    row+1, col
    //    row+1, col+1
    //    if ( 
    // Do it without a loop.  The trick is using if statements to make sure you don't go out of bounds.
    // Get it working and add in loops. am I getting an out of bounds error while trying to check it right now?
    public int countNeighbors(int row, int col) {
        int count = 0;
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = col - 1; c <= col + 1; c++) {
                if (r >= 0 && r < rows && //to stay inside the board in rows
                        c >= 0 && c < cols && //to stay inside the board for cols
                        !(r == row && c == col) && //if it is not the main cell
                        b[r][c] == 1) //if the cell is alive or dead
                {
                    count++;
                }
            }
        }
        return count;
    }

    // get the value that is in each of the rows and cols
    public int get(int row, int col) {
        return b[row][col];
    }

    // setting a value to each row and col
    public void set(int row, int col, int value) {
        b[row][col] = value;
    }

    // to find the height of the grid
    public int getRows() {
        return b.length;
    }

    public int getCols() {
        return b[0].length;
    }

    public int[][] getBoard() {
        return b;
    }

    public static void main(String[] args) {
        BoardDisplay display = new BoardDisplay(40, 40);
        display.initialize();

        Board board = new Board(40, 40, display);
        board.initialize();
       /* Board b = new Board(3,3);
        b.set(0,0, 1);
        b.set(0, 1, 1);
        b.set(0, 2, 1);

        b.set(1,0, 1);
        b.set(1, 1, 1);
        b.set(1, 2, 1);

        b.set(2,0, 1);
        b.set(2, 1, 1);
        b.set(2, 2, 1);
        */

        for (int i = 0; i < 10000; i++) {
            board.display();
            board.nextGeneration();
            System.out.println("");
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {
            }
        }
    }
}