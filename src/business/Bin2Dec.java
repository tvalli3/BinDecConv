package business;

import java.util.ArrayList;

/**
 * @author Tom Valli
 */
public class Bin2Dec {
    public static final String VALUEDESC = "Binary";
    public static final String RESULTDESC = "Decimal";
    
    private String value, result, emsg;
    private boolean valid;
    private ArrayList<String> steps;
    
    public Bin2Dec(String v) {
        this.emsg = "";
        this.value = v;
        this.result = "";
        int numResult = 0;
        byte placeVal = 0;
        byte placeValErr = 0;
        try {
            for (int i=0; i<v.length(); i++) {                                  //start for loop at the right most value
                placeValErr = Byte.parseByte(v.substring(i,i+1));               //convert each substring to int
                if (placeValErr < 0 || placeValErr > 1) {                       //check if value is 0 or 1
                    throw new NumberFormatException("Input can be only 0's or 1's");
                }
            }
            this.valid = true;
            this.steps = new ArrayList<>();
            for (int h=0, pos=v.length()-1; h<v.length(); h++, pos--) {         //increment the exponent h and decrement the positional value pos
                placeVal = Byte.parseByte(v.substring(pos,pos+1));
                double convertVal = placeVal * (Math.pow(2,h));                 //placeVal = the postional  
                numResult += convertVal;
                if (convertVal > 0) {
                    this.steps.add("There is a(n) " + convertVal + " in the value of 2^" + h);                            
                }
            }                 
        this.result = Integer.toString(numResult);
        } catch (NumberFormatException e) {
            this.value = "";
            this.emsg = "Illegal value: " + e.getMessage();
            this.valid = false;
        } //end of try/catch loop 
    }//end of Bin2Dec 
    
    public String getErrorMsg() {
        return this.emsg;
    }//end of getErrorMsg
    
    public String getResult() {
        if (this.valid) {
            return this.result;            
        } 
        return "Invalid object.";
    }//end of getResult
    
    public ArrayList<String> getSteps() {
        return this.steps;
    }//end of getSteps
    
    private boolean isValid() {
        for (int i=0; i<this.value.length(); i++) {                                  //start for loop at the right most value
            if (this.value.charAt(i) != '0' && this.value.charAt(i) != 'i')    
            return false;
        } return true;
    }
    
}
