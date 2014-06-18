package ace.y2012.week28;


import java.io.*;
import java.util.ArrayList;

public class Lexer {
	public static String userInput = null;
	//static final private int END_OF_FILE = -1;
	public static int inputLength = 0;
	public static char[] ch;
	
	//public static Token SampleToken;
	public static String SampleToken;
	
	public static ArrayList<Token> TokenArray = new ArrayList<Token>();
	
	private static boolean showMessage = false;
	
	public static void LexerPrint(String message)
	{
		if(showMessage)
		{
			System.out.println(message);
		}
	}
	
	private static int tokenIndex  = 0;
	private static Token InvalidToken = new Token(enumTokenID.END_OF_INPUT, "End of Input", 0);
	public static Token getNextToken()
	{
		tokenIndex++;
		if(tokenIndex >= TokenArray.size())
			return InvalidToken;
		
		LexerPrint("getNext: " + TokenArray.get(tokenIndex).getType());
		return TokenArray.get(tokenIndex);
	}
	
	public static Token getToken()
	{
		if(tokenIndex >= TokenArray.size())
			return InvalidToken;
		
		LexerPrint("getCurrent: " + TokenArray.get(tokenIndex).getType());
		return TokenArray.get(tokenIndex);
	}
	
	public static Token getLastToken()
	{
		tokenIndex--;
		if(tokenIndex < 0 || tokenIndex >= TokenArray.size())
			return InvalidToken;
		
		return TokenArray.get(tokenIndex);
	}
	
	public static int TokenCounter =0;
   public static boolean ReadInput() throws Exception 
   {
	   TokenArray.clear();
	   tokenIndex = 0;
	   LexerHelper.columnNo =0;
	   
	   System.out.print("> ");

	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	   try {
		   //br.mark(0);
	   
		   userInput = br.readLine();
		   //br.reset();
	   } catch (IOException ioe) {
	   System.out.println("IO error trying to read user input!");
	   System.exit(1);
	   }

   
	   //if (!userInput.equals("exit")){
		   // Replace all tabs with 5 spaces.
		   userInput = userInput.replaceAll("\\t","     ");
		   
		   userInput = userInput + "\n";
		   
		   //System.out.print("UserInput: " + userInput);
		   
		   ch = Lexer.userInput.toCharArray();
		   while (ch[LexerHelper.columnNo]!= '\n') {
			   Token newToken = LexerHelper.searchInput();
			   if(newToken == null){
				   return false;
			   }
			   TokenArray.add(newToken);
			   LexerHelper.columnNo++;
		     } 
	   
	   return true;
	   
   }
}