package ace.y2012.week28;


enum enumTokenID {
	END_OF_INPUT,
	PLUS,
	MINUS,
	MULTIPLY,
	DIVIDE,
	POWER, 
	LPAREN,
	RPAREN,
	ASSIGN,
	SIN,
	COS,
	TAN,
	EXP,
	LOG,
	LOG10,
	FUNCTION,
	ID,
	NUMBER,
	PRINT,
	EXIT;
  }

 public class LexerHelper { 
     public static int columnNo = 0; 
        
     private static Token matchNumber() throws Exception { 
         
         StringBuilder sb = new StringBuilder(); 

         boolean decimal = false;
         char character = Lexer.ch[columnNo];
         
         int startPosition = columnNo;
         
         if(character == '+'|| character == '-') {
        	 sb.append((char) character);
             columnNo++;
        	 character = Lexer.ch[columnNo];
         }
         	         
         while ((character >= '0' && character <= '9') || character == '.') {
             if (decimal && character == '.') {
            	 LexerErrorHandler.LexerError("ERROR: Invalid input!! Numerical value has more than one decimal point!!", columnNo);
            	 return null;
             } else if (character == '.') {
                 decimal = true;
             } 
             sb.append((char) character);
             columnNo++;
        	 character = Lexer.ch[columnNo];
         }
    
         columnNo--;
         
         String word = sb.toString();
         return new Token(enumTokenID.NUMBER, word, startPosition);
         
     } 
     
//private static Token matchSignNumber() throws Exception { 
//         
//         char character = Lexer.ch[columnNo];
//         
//         
//         Token LastToken = null;
//         enumTokenID LastTok = enumTokenID.EXIT;
//         
//         if (!Lexer.TokenArray.isEmpty()) { 
//  		   LastToken = (Token)Lexer.TokenArray.get(Lexer.TokenArray.size()-1);
//  		   LastTok = LastToken.getType();
//         }
//         
//         if( (LastTok != enumTokenID.PLUS && LastTok != enumTokenID.MINUS && LastTok != enumTokenID.MULTIPLY && LastTok != enumTokenID.DIVIDE
//        		 && LastTok != enumTokenID.POWER && LastTok != enumTokenID.LPAREN && LastTok != enumTokenID.ASSIGN ) && 
//        	 (character == '+' || character == '-')) {
//        	 if(character == '+') {
//             	return new Token(enumTokenID.PLUS, '+', columnNo);
//        	 }
//             else if(character == '-') {
//             		return new Token(enumTokenID.MINUS, '-', columnNo);
//             }
//         } else {
//        	 return matchNumber();
//         }
//		return null;
//         
//     } 

  
     private static Token matchIdentifier() throws Exception { 
          
         StringBuilder sb = new StringBuilder(); 
    	 char character = Lexer.ch[columnNo];
    	 int startPosition =columnNo;
    	 if(character == '_'){
    		 LexerErrorHandler.LexerError("ERROR: Not a Valid identifier. Identifier name starts with '_' (underscore) character", columnNo);
    		 return null;
    	 }
         
    	 while ((character >= 'a' && character <= 'z') || 
                 (character >= 'A' && character <= 'Z') || 
                 (character >= '0' && character <= '9') || 
                 character == '_' || character == '.') { 
    		 if (character == '.') {
            	 LexerErrorHandler.LexerError("ERROR: Not a Valid identifier. Identifier name has '.' (period) character", columnNo);
            	 return null;
             }
    		 sb.append((char) character);
        	 columnNo++;
        	 character = Lexer.ch[columnNo];
             
         } 
         columnNo--;
         String word = sb.toString(); 
         if (word.equals("SIN")) { 
             return new Token(enumTokenID.FUNCTION, word, startPosition); 
         } else if (word.equals("COS")) { 
             return new Token(enumTokenID.FUNCTION, word, startPosition); 
         } else if (word.equals("TAN")) { 
             return new Token(enumTokenID.FUNCTION, word, startPosition); 
         } else if (word.equals("EXP")) { 
             return new Token(enumTokenID.FUNCTION, word, startPosition); 
         } else if (word.equals("LOG")) { 
             return new Token(enumTokenID.FUNCTION, word, startPosition); 
         } else if (word.equals("LOG10")) { 
             return new Token(enumTokenID.FUNCTION, word, startPosition); 
         } else if (word.equals("PRINT")) { 
             return new Token(enumTokenID.PRINT, word, startPosition); 
         } else if (word.equals("EXIT")) { 
             return new Token(enumTokenID.EXIT, word, startPosition); 
         } else { 
             return new Token(enumTokenID.ID, word, startPosition); 
         } 
     } 

     
 public static Token searchInput() throws Exception  { 
	 char character = Lexer.ch[columnNo]; 
     // Skip whitespace 
     while (character == ' ' || character == '\t' || 
             character == '\r' || character == '\n') { 
    	 columnNo++;
    	 character = Lexer.ch[columnNo]; 
     } 
     switch (character) { 
	     case '+': { 
	    	 //return matchSignNumber();
	    	 return new Token(enumTokenID.PLUS, '+', columnNo);
	     } 
	     case '-': { 
	    	 //return matchSignNumber();
	    	 return new Token(enumTokenID.MINUS, '-', columnNo);
	     } 
         case '*': { 
             return new Token(enumTokenID.MULTIPLY, '*', columnNo); 
         } 
         case '/': { 
             return new Token(enumTokenID.DIVIDE, '/', columnNo); 
         } 
         case '^': { 
             return new Token(enumTokenID.POWER, '^', columnNo); 
         } 
         case '(': { 
             return new Token(enumTokenID.LPAREN, '(', columnNo); 
         } 
         case ')': { 
             return new Token(enumTokenID.RPAREN, ')', columnNo); 
         } 
         case '=': { 
             return new Token(enumTokenID.ASSIGN, '=', columnNo); 
         } 
         default: { 
             if (character == '.' || (character >= '0' && character <= '9')) { 
                 return matchNumber(); 
             } else if ((character >= 'A' && character <= 'Z') || 
                 (character >= 'a' && character <= 'z') || 
                 character == '_') { 
            	 return matchIdentifier();
             } else { 
            	 LexerErrorHandler.LexerError("ERROR: Unexpected '" + character + "' character", columnNo);
            	 return null;  
             } 
         }
     }
     
 }


 }