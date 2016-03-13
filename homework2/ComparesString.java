import java.util.*;

public class ComparesString {
    
    public static int compareTo(String s1, String s2) {
        
        s1.toLowerCase();
        s2.toLowerCase();
        
        if(s1.length() == 0 && s2.length() == 0) {
            return 0; 
        }
        else if (s1.length() == 0 && s2.length() > 0) { 
            return -1; 
    }
        else if (s1.length() > 0 && s2.length() == 0) {
                return 1;
            }
            
             
            if(s1.equals(s2) )
                {
                    return 0;   
                }


            else if(s1.charAt(0) == s2.charAt(0)){
                 
                 }    
                    return compareTo(s1.substring(1), s2.substring(1));
        
    }
     
        public static String findMinimum(ArrayList<String> stringArray) {
         
               
            String minimum;
            minimum = stringArray.get(0);
            
            return helper(stringArray, minimum, 1);
                
            }
        
       public static String helper(ArrayList<String> stringArray, String minimum, int index) {
            
            if(index >= stringArray.size()) {
                return minimum;
            }
            
            else {
                if(compareTo(minimum, stringArray.get(index)) > 0) {
                    minimum = stringArray.get(index); 
            }
        }
             return helper(stringArray, minimum, index + 1);
       }
        

}