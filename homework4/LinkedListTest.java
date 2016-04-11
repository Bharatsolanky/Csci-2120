import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import junit.framework.TestCase;

public class LinkedListTest {
    
	@Test
	public void testIterator() {
	
	Dog fifi = new Dog("Fifi", 12, 8);
        Dog butch = new Dog("Butch", 10, 10);
        Dog leonard = new Dog("Leonard", 22, 13);
        Dog spot = new Dog("Spot", 17, 9);

        
        LinkedList<Dog> myDogList = new LinkedList<Dog>();
        
        myDogList.add(fifi);
        myDogList.add(butch);
        myDogList.add(leonard);
        myDogList.add(spot);
        
        boolean result = myDogList.contains(fifi);
 
        
        int i = 0;
        Iterator<Dog> it = myDogList.getIterator();

        while(it.hasNext()){
        	it.next();
        	i++; 
        }

        assertEquals(i,4);
	}

	@Test
	public void testPrior() {
	    
	Dog fifi = new Dog("Fifi", 12, 8);
        Dog butch = new Dog("Butch", 10, 10);
        Dog leonard = new Dog("Leonard", 22, 13);
        Dog spot = new Dog("Spot", 17, 9);

        LinkedList<Dog> myDogList = new LinkedList<Dog>();
        
        myDogList.add(fifi);
        myDogList.add(butch);
        myDogList.add(leonard);
        myDogList.add(spot);
 
        Iterator<Dog> it = myDogList.iteratorAt(spot);
        it.prior();
       assertEquals(leonard,it.prior());
	}
}