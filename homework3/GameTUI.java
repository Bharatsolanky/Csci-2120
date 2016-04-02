import java.util.*;
import java.io.IOException;
import java.io.EOFException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.*;
import java.io.File;

/*****************************************************************
 * Game TUI:
 * 
 * Description:
 * 
 ***************************************************************/

 

public class GameTUI
{

    private Dungeon itsDungeon;

    public GameTUI (Dungeon d) {

        itsDungeon = d;
    }

    // this is the main loop - very little logic here in the TUI
    // but all the input and output should be controlled from here

    public void run(Config config) {
        while (itsDungeon.getHero().getHealth() > 0)
        {
            //clear previous screen 
            StdDraw.clear();
            
            //draw the game world
            //drawScreen();
            itsDungeon.draw(config);

            String heroMenu = config.setupHeroText(itsDungeon.getHero());
            StdDraw.drawText(heroMenu);
            String monsterMenu = config.setupMonsterText(itsDungeon.getAdjacentMonsters());
            StdDraw.drawText(monsterMenu);
        
            //Get player's action from input
            String userInput = StdIn.readString();
            Action action = config.mapKeyToAction(userInput);
            
            //Execute player's action and Update game
                           
            if(userInput.equals("k")) {
                FileOutputStream out = null;
                 ObjectOutputStream obj = null;
              
                try {
                    Scanner scanner = new Scanner(System.in);
                     System.out.println("Provide a file name for your saved file. The file must end in .dat");
                     String fileName = scanner.nextLine();

                    //
                     File f = new File(fileName);
                     String fil = f.getName();

                     if(fil.lastIndexOf(".") == -1) {
                     System.out.println("Please provide the extension .dat");
                     }
                     else {
                         System.out.println("Saving..."+fil);
                     }
                     
                   out = new FileOutputStream (fileName);
                    obj = new ObjectOutputStream(out);
                    obj.writeObject(itsDungeon);
                    obj.close();
                }
                catch (Exception e) {
                    System.out.println("Exception has been thrown.");   
                }
            }
            
            else if(userInput.equals("l")) {
                ObjectInputStream games = null;
                try {
                    Scanner scanner1 = new Scanner(System.in);
                    System.out.println("Provide a file name for the file you would like to load. The file must end in .dat");
                    String fileName1 = scanner1.nextLine();
                
                File f = new File(fileName1);
                    String fil = f.getName();
                    //checking if user input has .dat extension
                 
        if(fil.lastIndexOf(".") == -1 ) {
  
       System.out.println("Please provide the extension .dat");}
       else {System.out.println("Loading..."+fil);}
                
                    FileInputStream load = new FileInputStream(fileName1);
                 
                    games = new ObjectInputStream(load);
                    itsDungeon = (Dungeon)games.readObject();
                    games.close();
                }
                catch (Exception e) {
                    System.out.println("Exception has been thrown.");
                }
                
            }
            
            else{
                itsDungeon.execute(config,action);
            }
        
        }
        StdDraw.drawText("You died!\n");
    }//end run

    public void drawScreen(Config config) {
        // Draw game map!
        StdDraw.initGameMap(itsDungeon.getWidth(), itsDungeon.getHeight());
        drawDungeon(config);
        drawHero(config);
        drawMonsters(config);
        //Render display
        StdDraw.draw(); //change to call called render
    }

    public void drawDungeon(Config config) {
        Cell[][] map = itsDungeon.getMap();
        for (int i=0;i<itsDungeon.getWidth();i++) 
        {
             for (int j=0;j<itsDungeon.getHeight();j++)
             {
                   map[i][j].draw(config, i,j);
             }
        }
    }

    //Draw hero
    public void drawHero(Config config) {
        Cell[][] map = itsDungeon.getMap();
        Hero stanley = itsDungeon.getHero();
        Tile heroTile = map[stanley.getX()][stanley.getY()].getTile();  //Get background tile
        stanley.draw(config,heroTile);                                      //Draw hero ontop that background
    }

    //Draw monsters
    public void drawMonsters(Config config) {
        Cell[][] map = itsDungeon.getMap();
        ArrayList<Monster> monsters = itsDungeon.getMonsters();
        for (Monster creature: monsters)
        {
            Tile monsterTile = map[creature.getX()][creature.getY()].getTile();  //Get background tile  
            creature.draw(config,monsterTile);
        }
    }

    
}//end GameTUI
