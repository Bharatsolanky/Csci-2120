import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;

public class ComparesStringTest {
    
    private String s1;
    private String s2;
    ArrayList<String> stringArray; 
      
    
    @Before
    public void setUp() {
        stringArray = new ArrayList<String>();
        stringArray.add("SPAre");
       stringArray.add("STRonG");
       stringArray.add("Straight");
       stringArray.add("String");
       stringArray.add("Test");
       

    }
    
    @Test
    public void testcompareTo() {
		assertTrue(ComparesString.compareTo("chair", "charter") == -1);
		assertTrue(ComparesString.compareTo("", "") == 0);
		assertTrue(ComparesString.compareTo("charter", "chair") == 1);
		assertTrue(ComparesString.compareTo("cHaRtEr", "ChaRr") == 1);
		assertTrue(ComparesString.compareTo("", "chair") == -1);
		assertTrue(ComparesString.compareTo("charter", "") == 1);
    }
    
    @Test
    public void testfindMinimum() {
     
		assertTrue(ComparesString.findMinimum(stringArray).equals("Test"));
	

    }
 
}