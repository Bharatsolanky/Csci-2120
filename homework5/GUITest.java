public class GUITest {
     /**
     *Main method
     * Doesn't return anything
     * 
    */
    public static void main(String [] args) {
        Grid grid = new Grid(75, 100);
        GUI gui = new GUI(grid, 10);
        gui.run();
    }
    
    
}