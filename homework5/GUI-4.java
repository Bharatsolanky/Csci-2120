import javax.swing.*;
import java.awt.*;
import java.util.Observer;
import java.util.Observable;
import java.awt.event.*;


public class GUI extends JPanel implements Observer{
    
    private Grid grid;
    private JFrame jframe;
    private int sizeinPixel;
    
    /**
     * constructor of GUI class
     * @param The instance variable grid and the size of each cell in pixels
    */
    
    
    
    public GUI(Grid grid, int sizeinPixel){
        this.grid = grid;
        this.sizeinPixel = sizeinPixel;
        this.jframe = new JFrame();
        jframe.setSize(sizeinPixel*grid.rowCount()+19,sizeinPixel*grid.columnCount()+42);	
        this.setBackground(Color.RED);
        jframe.add(this);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addMouseListener(new MouseActionListener());
        jframe.addKeyListener(new KeyActionListener());
        
    }
    
      /**
     * paintComponent method
     * fills pixels with colors dpending on wetehr their alive or dead
     * @param Graphics g
     * Doesn't return anything
    */
    
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        int row = grid.rowCount();
        int column = grid.columnCount();
        
        for(int i = 0; i < row; i++) {
            
            for(int f = 0; f < column; f++ ) {
                g.drawRect(i*this.sizeinPixel, f*this.sizeinPixel, this.sizeinPixel, this.sizeinPixel);
                if (grid.cellIsAlive(i,f)) {
                    g.setColor(Color.BLACK);
                    g.fillRect(i*this.sizeinPixel, f*this.sizeinPixel, this.sizeinPixel-1, this.sizeinPixel-1);
                
                }
                
                else {
                    g.setColor(Color.WHITE);
                    g.fillRect(i*this.sizeinPixel, f*this.sizeinPixel, this.sizeinPixel-1, this.sizeinPixel-1);
                }
            
            }
        }
        
    }
    
      /**
     * update method
     * @param Observable o abd Object arg
     * calls methods to update statuses
    */
    
    public void update(Observable o, Object arg) {
        revalidate();
        repaint();
    }
    
      /**
     * update method 2
     * no parameters
     * calls methods to update statuses
    */
    
    public void update() {
        revalidate();
        repaint();
    }
    
      /**
     * run method
     * sets setVisible to true
     * 
    */
    
    public void run() {
		
        this.jframe.setVisible(true);
    }
    
   
    private class MouseActionListener extends MouseAdapter {
        
    /**
     * mouseClicked method
     * @param MouseEvent e
     * sets x and y coordinates
    */
        
        public void mouseClicked(MouseEvent e) {
            int x = e.getX()/GUI.this.sizeinPixel;
            int y = e.getY()/GUI.this.sizeinPixel;
            if(GUI.this.grid.cellIsAlive(x, y)) {
                GUI.this.grid.setCellAlive( x, y, false);
            }
            
            else {
                 GUI.this.grid.setCellAlive( x, y, true);
            }
            GUI.this.update();
        }
      
    /**
     * mouseDragged method
     * @param MouseEvent e
     * Allows for mouse to be dragged
    */
        
        public void mouseDragged(MouseEvent e ){
			int x = e.getX()/GUI.this.sizeinPixel;
			int y = e.getY()/GUI.this.sizeinPixel;
			if( x > 0 && x<GUI.this.grid.rowCount() && y > 0 && y<GUI.this.grid.columnCount()) {
				GUI.this.grid.setCellAlive(x,y, false);
				GUI.this.update();
					
			}
			
				
		}
    }
    
    
    private class KeyActionListener extends KeyAdapter {
        
    /**
     * keyPressed method
     * finds out what key is pressed, then performs actions accordingly
     * @param KeyEvent e
     * 
    */
        
        public void keyPressed(KeyEvent e) {
            char key = e.getKeyChar();
            if(key == 'b') {
               Timer timer = new Timer(1000, new ActionListener(){
                            @Override
                                public void actionPerformed(ActionEvent e) {
                                    GUI.this.grid.update();
                                    GUI.this.update();
                                }
                            });
            
                timer.start();
            }
            
            if(key == 's') {
                 FileOutputStream out = null;
                 ObjectOutputStream obj = null;
                 
                 Cell[][] savedState = itsCurrentState;
                 
                   out = new FileOutputStream(save);
                    obj = new ObjectOutputStream(out);
                    obj.writeObject(savedState);
                    obj.close();
                }
            }
            
            if(key == 'l') {
                   ObjectInputStream LOAD = null;
                    FileInputStream load = new FileInputStream(savedState);
                 
                    LOAD = new ObjectInputStream(load);
                    itsDungeon = (Dungeon)LOAD.readObject();
                    LOAD.close();
            }
                
            
        }
            
    }
    
    
}
