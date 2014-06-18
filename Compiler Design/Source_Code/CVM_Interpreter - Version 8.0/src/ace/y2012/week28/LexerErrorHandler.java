package ace.y2012.week28;


public class LexerErrorHandler {
	
	public static void LexerError(String message, int errorPointerPosition) throws Exception {
		int i;
		String SyntaxError= "";
		for(i=0;i<errorPointerPosition+2;i++)
			SyntaxError = SyntaxError + " ";
		
		System.out.println(SyntaxError + "^Syntax Error");
		System.out.println(message);
		
		Lexer.userInput = "";
		//ReadString.ch= "" + "\n" ;
		LexerHelper.columnNo =0;
		// Clear token array
		   Lexer.TokenArray.clear();
		   System.out.println("TokenArray Cleared!!");
		   //ReadString.ReadInput();
	}

	

}
