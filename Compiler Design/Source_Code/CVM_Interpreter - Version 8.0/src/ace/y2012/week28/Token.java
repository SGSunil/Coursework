package ace.y2012.week28;


import java.util.HashMap;
import java.util.Map;

public class Token { 
     
    private enumTokenID type; 
    private String text;
    private int position; 
 
    public Token( enumTokenID type, String text, int position) { 
        
        this.type = type; 
        this.text = text;
        this.position = position;
        //System.out.print("Created new String token: " + "TYPE: " + this.type + ", " + "TEXT: " + this.text + ", " + "POS: " + this.position +"\n");
        
    }

	public Token(enumTokenID type, char ch, int position) {
		this.type = type; 
        this.text = "" + ch;
        this.position = position;
        //System.out.print("Created new Char token: " + "TYPE: " + this.type + ", " + "TEXT: " + this.text + ", " + "POS: " + this.position +"\n");
	}
	
	public enumTokenID getType() {
        return type;
    }

    public String getText() {
        return text;
    }
    
    public int getPosition() {
        return position;
    }
    
    private static Map<enumTokenID, String> operatorMapping = new HashMap<enumTokenID, String>(); 
    
	static 
	{
//		Add
//		Sub
//		Mul
//		Div
//		Pow
//		Print
//		Sin
//		Cos
//		Tan
//		Exp
//		Log
//		Log10

		operatorMapping.put(enumTokenID.POWER, "Pow");
		operatorMapping.put(enumTokenID.DIVIDE, "Div");
		operatorMapping.put(enumTokenID.MINUS, "Sub");
		operatorMapping.put(enumTokenID.MULTIPLY, "Mul");
		operatorMapping.put(enumTokenID.PLUS, "Add");
		
		
	}
	
	public static String getOperator(enumTokenID operator)
	{
		return operatorMapping.get(operator);
		
	}
}
