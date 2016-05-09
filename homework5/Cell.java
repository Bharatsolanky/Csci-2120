
/**
 * A class representing a single cell in a Conway Grid.
 */
public class Cell {

    
	/** Whether the cell is alive or not. */
    private boolean isAlive;

	/**
	 * Creates a new, dead Cell.
	 */
    public Cell() {
        this.isAlive = false;
    }

    /**
	 * Checks whether the Cell is alive or not.
	 *
	 * @return Whether this Cell is alive.
	 */
    public boolean isAlive() {
        return this.isAlive;
    }

	/**
	 * Sets the alive status of this Cell.
	 *
	 * @param val the new alive value. True for alive or false for dead.
	 */
    public void setAlive(boolean val) {
        this.isAlive = val;
    }
    
    public String toString() {
        String returnVal = "-";
        if (isAlive)
           returnVal = "O"; 
        return returnVal;
    }

    public boolean equals(Object o) {
        boolean returnVal = false;
        if (o instanceof Cell)
            returnVal = (this.isAlive == ((Cell)o).isAlive());
        return returnVal;
    }
    
    
    
}
