public class ComplexNumber {
   
   //instance variables
    private float a;
    private float b;
   
    /**
      * @param - a Complex Number
      * Constructor  
      */
    public ComplexNumber(float _a, float _b) {
        this.a = _a;
        this.b = _b;
    }
   
   //getter methods 
  
    public float getA() {
        return a;
    }
    
   
    public float getB() {
        return b;
    }
    
    
     /**
      * @param - a Complex Number
      * @return - the new complex number after addition
      * This method adds the two complex numbers 
      */
     
    public ComplexNumber add(ComplexNumber otherNumber){
		ComplexNumber newComplex;
		float newA = a + otherNumber.getA();
		float newB = b + otherNumber.getB();
		newComplex = new ComplexNumber(newA, newB);
		return newComplex;
	}
	
	/**
      * @param - a Complex Number
      * @return - the new complex number after subtraction
      * This method subtracts the two complex numbers 
      */
	
	 public ComplexNumber subtract(ComplexNumber otherNumber){
		ComplexNumber newComplex;
		float newA = a - otherNumber.getA();
		float newB = b - otherNumber.getB();
		newComplex = new ComplexNumber(newA, newB);
		return newComplex;
	}
	
	/**
      * @param - a Complex Number
      * @return - the new complex number after multiplication
      * This method multiplies the two complex numbers 
      */
	
	 public ComplexNumber multiply(ComplexNumber otherNumber){
		ComplexNumber newComplex;
		float newA = a * otherNumber.getA() - b * otherNumber.getB();
		float newB = b  * otherNumber.getA() + a * otherNumber.getB();
		newComplex = new ComplexNumber(newA, newB);
		return newComplex;
	}
	
	/**
      * @param - a Complex Number
      * @return - the new complex number after division
      * This method divides the two complex numbers 
      */
      
	 public ComplexNumber divide(ComplexNumber otherNumber){
		ComplexNumber newComplex;
		float newA = (a * otherNumber.getA() + b * otherNumber.getB()) / (otherNumber.getA() * otherNumber.getA() + otherNumber.getB() * otherNumber.getB()); 
		float newB = (b * otherNumber.getA() - a * otherNumber.getB()) / (otherNumber.getA() * otherNumber.getA() + otherNumber.getB() * otherNumber.getB()); 
		newComplex = new ComplexNumber(newA, newB);
		return newComplex;
	}
	

   	/**
   	  * @param - obj
      * @return - boolean wether the object is equal to the ComplexNumber or not
      * this method is used to see wether obj is equal to a ComplexNumber
      */
	public boolean equals(Object obj) {
		boolean returnValue = false; 
		if(obj instanceof ComplexNumber){
			ComplexNumber tempequals = (ComplexNumber) obj;
			if (this.a == tempequals.getA() && this.b == tempequals.getB()) {
				returnValue = true;
			}
		}
		return returnValue;
	}

	  /**
	  * adds i to the end of the result
      * @return - the string displaying the complex number
      */
    public String toString() {
        return a + " + " + b + "i";
    }
    
}
