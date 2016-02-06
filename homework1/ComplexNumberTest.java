import org.junit.*;
import static org.junit.Assert.*;

public class ComplexNumberTest {

	// instance variables
	private ComplexNumber c1;
	private ComplexNumber c2;
	private ComplexNumber c3;

	@Before
   /**
      * This method sets up the floats used in the tests
      */
	public void setUp(){
		c1 = new ComplexNumber(5.0f,6.0f);
		c2 = new ComplexNumber(3.0f,4.0f);
		c3 = new ComplexNumber(1.0f, 2.0f);
	}

	@Test
	 /**
      * This method test the add method
      */
	public void testAdd(){
		ComplexNumber result1 = c1.add(c2);
		assertEquals(result1.getA(), 8.0f, 0.01);
		assertEquals(result1.getB(), 10.0f, 0.01);
		
		ComplexNumber result2 = c1.add(c3);
		assertEquals(result2.getA(), 6f, 0.01);
		assertEquals(result2.getB(), 8f, 0.01);
		
	}
	
	@Test
	 /**
      * This method tests the subtract method
      */
	public void testSubtract(){
		ComplexNumber result1 = c1.subtract(c2);
		assertEquals(result1.getA(), 2f, 0.01);
		assertEquals(result1.getB(), 2f, 0.01);
		
		ComplexNumber result2 = c1.subtract(c3);
		assertEquals(result2.getA(), 4f, 0.01);
		assertEquals(result2.getB(), 4f, 0.01);
		
	}
		
	@Test
	 /**
      * This method tests the multiply method
      */
	public void testMultiply() {
		ComplexNumber result1 = c1.multiply(c2);
		assertEquals(result1.getA(), -9f, 0.01);
		assertEquals(result1.getB(), 38f, 0.01);

		ComplexNumber result2 = c1.multiply(c3);
		assertEquals(result2.getA(), -7f, 0.01);
		assertEquals(result2.getB(), 16f, 0.01);

	}
			
	@Test
	 /**
      * This method tests the divide method
      */
	public void testDivide() {
		ComplexNumber result1 = c1.divide(c2);
		assertEquals(result1.getA(), 1.56f, 0.01);
		assertEquals(result1.getB(), -0.08, 0.01);

		ComplexNumber result2 = c1.divide(c3);
		assertEquals(result2.getA(), 3.4f, 0.01);
		assertEquals(result2.getB(), -0.8f, 0.01);

	}
	
	 /**
      * This method tests the equals method
      */
	public void testEquals(){
		ComplexNumber result1 = new ComplexNumber(5.0f, 6.0f);
		assertTrue(c1.equals(result1));
		assertFalse(c1.equals("Hello"));

	}
}
	
