import java.util.Observable;
/**
 * This class represents a single instance of Conway's Game of Life.
 */
public class Grid extends Observable {

	/** The current state. */
    private Cell[][] itsCurrentState;
	
	/** The next state. */
    private Cell[][] itsNextState;

	/**
	 * Constructs a new Grid with the specified number of rows and columns.
	 *
	 * @param numRows The number of rows.
	 * @param numColumns The number of columns.
	 */
    public Grid(int numRows, int numColumns) {
        itsCurrentState = new Cell[numRows][numColumns];
        itsNextState = new Cell[numRows][numColumns];
        for (int row=0; row< itsCurrentState.length; row++) {
            for (int column=0; column < itsCurrentState[row].length; column++) {
                itsCurrentState[row][column] = new Cell();
                itsNextState[row][column] = new Cell();
            }
        }
    }

	/**
	 * Checks whether the cell at row, column is alive.
	 *
	 * @param row The row number to query.
	 * @param column The column number to query.
	 * @return Whether the requested cell is "alive".
	 */
    public boolean cellIsAlive(int row, int column) {
        return itsCurrentState[row][column].isAlive();
    }
	
	/**
	 * Sets the "alive" state of the cell at row , column 
	 *
	 * @param row The row number.
	 * @param column The column number.
	 * @param val Whether the cell should be set to alive (true) or dead (false).
	 */
	public void setCellAlive(int row, int column, boolean val){
		itsCurrentState[row][column].setAlive(val);
	}
	
	/**
	 * Returns the number of rows in this grid.
	 *
	 * @return The number of rows in this grid.
	 */
	public int rowCount(){
		return this.itsCurrentState.length;
	}
	
	/**
	 * Returns the number of columns in this grid.
	 *
	 * @return The number of columns in this grid.
	 */
	public int columnCount(){
		return this.itsCurrentState[0].length;
	}

	/**
	 * Advances the game of life a single step forward.
	 */
    public void update() {
        // loop over rows
        for (int row=0; row < itsCurrentState.length; row++) {
            // loop over columns
            for (int column=0; column < itsCurrentState[row].length; column++) {
                boolean isAliveNextRound = aliveNextRound(row,column);
                itsNextState[row][column].setAlive(isAliveNextRound);
            }
        }
        swapStates();
        setChanged();
        notifyObservers(null);

    }
	
	//////////////////////////////////////////////
	//PRIVATE METHODS:
	//Not for the faint of heart!!
	////////////////////////////////////////////// 

    private boolean aliveNextRound(int row, int column) {
        boolean aliveNextRound = false;;
        boolean currentAliveState = itsCurrentState[row][column].isAlive();
        int liveNeighbors = getCountOfLiveNeighbors(row,column);
        if (currentAliveState == true && liveNeighbors <2)
            aliveNextRound = false;
        else if ( currentAliveState == true && (liveNeighbors == 2 || liveNeighbors == 3) )
            aliveNextRound = true;
        else if ( currentAliveState == true && liveNeighbors > 3)
            aliveNextRound = false;
        else if ( currentAliveState == false && liveNeighbors == 3)
            aliveNextRound = true;
        return aliveNextRound;
    }

    private int getCountOfLiveNeighbors(int row, int column) {
        int numLiveNeighbors = 0;
        // check general case first - here we know we don't need
        // to check boundary conditions explicitly
        if (row != 0 && row != itsCurrentState.length - 1 &&
            column != 0 && column != itsCurrentState[0].length - 1) {

            for (int rowMod = -1; rowMod < 2; rowMod++) {
                for (int colMod = -1; colMod <2; colMod++) {
                    if ( itsCurrentState[row+rowMod][column+colMod].isAlive() &&
                         !(rowMod == 0 && colMod ==0) ) // don't count myself
                        numLiveNeighbors++;
                }
            }

        } // end of general case calculation
        else {  // now deal with edge cases
            int up = row-1;
            int down = row+1;
            int right = column+1;
            int left = column-1;
            if (row == 0) { // top edge case
                up = itsCurrentState.length-1;
            } else if (row == itsCurrentState.length-1) { // bottom edge case
                down = 0;
            }

            if (column == 0) { // left edge case
                left = itsCurrentState[0].length-1;
            } else if (column == itsCurrentState[0].length-1) { // right edge case
                right = 0;
            }

            int[][] neighborsToConsider = { {up,left},   {up,column}, {up,right},
                                            {row,left},               {row,right},
                                            {down,left},{down,column},{down,right} };

            for (int neighborIndex = 0; neighborIndex < neighborsToConsider.length; neighborIndex++) {
                if (itsCurrentState[neighborsToConsider[neighborIndex][0]][neighborsToConsider[neighborIndex][1]].isAlive())
                    numLiveNeighbors++;
            }

        } // end of edge cases clause
        return numLiveNeighbors;
    }

    // this swaps current and next references
    private void swapStates() {
        Cell[][] temp = itsCurrentState;
        itsCurrentState = itsNextState;
        itsNextState = temp;
    }
    
    // @Override
    public String toString() {
        String returnVal = "";
        for (int i=0; i<itsCurrentState.length; i++) {
            for (int j=0; j<itsCurrentState[i].length; j++) {
                returnVal += itsCurrentState[i][j];
                returnVal += " ";
            }
            returnVal += "\n";
        }
        return returnVal;
    }

    public void gliderSetup() {
        itsCurrentState[5][5].setAlive(true);
        itsCurrentState[6][5].setAlive(true);
        itsCurrentState[7][5].setAlive(true);
        itsCurrentState[7][4].setAlive(true);
        itsCurrentState[6][3].setAlive(true);
    }
}
