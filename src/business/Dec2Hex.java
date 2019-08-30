package business;

import java.util.ArrayList;

/**
 * @author tomva
 */
public class Dec2Hex {
    
    public static final String VALUEDESC = "Decimal";
    public static final String RESULTDESC = "Hexidecimal";
    
    private String value, result, emsg;
    private boolean valid;
    private ArrayList<String> steps;
    
    public Dec2Hex(String v) {
        this.emsg = "";
        this.value = v;
        this.result = "";
        try {
            long n = Long.parseLong(this.value);
            if (n <= 0) {
                throw new NumberFormatException("Must be > 0");
            }
            this.valid = true;
            this.steps = new ArrayList<>();
            convertByRecur(n);
        } catch (NumberFormatException e) {
            this.value = "";
            this.emsg = "Illegal value: " + e.getMessage();
            this.valid = false;
        } //end of try/catch loop
    }
    
    public String getErrorMsg() {
        return this.emsg;
    }
    
    private void convertByRecur(long n) {
        int r;
        r = (int)(n % 16); //cast as int
        long nextval = n / 16;
        this.steps.add(n + " divided by 16 = " + nextval + " w/remainder of: " + r);
        if (nextval > 0) {
            convertByRecur(nextval); //recursion!!!
        }
        this.result += String.valueOf(Integer.toHexString(r));
    }//end of convertByRecur
    
    public String getResult() {
        if (this.valid) {
            return this.result;            
        } 
        return "Invalid object.";
    }
    
    public ArrayList<String> getSteps() {
        return this.steps;
    }
    
}
