package ace.y2012.week28;


import java.util.*;

public  class Parser 
{
	public static Map<String, Integer> symbolTable = new HashMap<String, Integer>(); 
	public static AST ast = null;
	
	private static Map<enumTokenID, Integer> precedenceOrder = new HashMap<enumTokenID, Integer>();
	private static Stack<enumTokenID> operatorStack = new Stack<enumTokenID>();
	private static Stack<Object> operandStack = new Stack<Object>();
	
	private static boolean parsingSuccess = true;
	
	
	private static int identifierIndex = 0;
	

	public static void Traverse(AST ast)
	{
		if(ast == null)
			return;
		
		if(ast.getClass() == BinaryAST.class)
		{
			if(((BinaryAST)ast).LHS != null)
			{
				Traverse(((BinaryAST)ast).LHS);
			}
			
			if(((BinaryAST)ast).RHS != null)
			{
				Traverse(((BinaryAST)ast).RHS);
			}
								
			System.out.println(((BinaryAST)ast).Operator);
		}
		else if(ast.getClass() == UnaryAST.class)
		{
				
			if(((UnaryAST)ast).LHS != null)
			{
				Traverse(((UnaryAST)ast).LHS);
			}
			
			System.out.println(((UnaryAST)ast).Operator);
		}
		else if(ast.getClass() == FunctionCallAST.class)
		{			
			System.out.println(((FunctionCallAST)ast).FunctionName);
			Traverse(((FunctionCallAST)ast).Argument);
		}
		else if(ast.getClass() == IdentifierAST.class)
		{			
			System.out.println(((IdentifierAST)ast).Name);
		}
		else if(ast.getClass() == NumberAST.class)
		{			
			System.out.println(((NumberAST)ast).Val);
		}
		
	}
	

	private static boolean showMessage = false;
	
	static void ParserPrint(String message)
	{
		if(showMessage)
		{
			System.out.println(message);
		}
	}
	
	static AST Optimize(AST ast)
	{
		if(ast == null)
			return null;
		
		if(ast.getClass() == BinaryAST.class)
		{
			AST lhs = null;
			if(((BinaryAST)ast).LHS != null)
			{
				lhs = Optimize(((BinaryAST)ast).LHS);
				((BinaryAST)ast).LHS = lhs;
			}
			
			AST rhs = null;
			if(((BinaryAST)ast).RHS != null)
			{
				rhs = Optimize(((BinaryAST)ast).RHS);
				((BinaryAST)ast).RHS = rhs;
			}
			
			if(((BinaryAST)ast).LHS.getClass() == NumberAST.class && ((BinaryAST)ast).RHS.getClass() == NumberAST.class)
			{
				double total = 0;
				
				if(((BinaryAST)ast).Operator == enumTokenID.PLUS)
				{
					total = Double.parseDouble(((NumberAST)(((BinaryAST)ast).LHS)).Val) + Double.parseDouble(((NumberAST)(((BinaryAST)ast).RHS)).Val);	
				}
				else if(((BinaryAST)ast).Operator == enumTokenID.MINUS)
				{
					total = Double.parseDouble(((NumberAST)(((BinaryAST)ast).LHS)).Val) - Double.parseDouble(((NumberAST)(((BinaryAST)ast).RHS)).Val);	
				}
				else if(((BinaryAST)ast).Operator == enumTokenID.MULTIPLY)
				{
					total = Double.parseDouble(((NumberAST)(((BinaryAST)ast).LHS)).Val) * Double.parseDouble(((NumberAST)(((BinaryAST)ast).RHS)).Val);	
				}
				else if(((BinaryAST)ast).Operator == enumTokenID.DIVIDE)
				{
					total = Double.parseDouble(((NumberAST)(((BinaryAST)ast).LHS)).Val) / Double.parseDouble(((NumberAST)(((BinaryAST)ast).RHS)).Val);	
				}
				else if(((BinaryAST)ast).Operator == enumTokenID.POWER)
				{
					total = Math.pow(Double.parseDouble(((NumberAST)(((BinaryAST)ast).LHS)).Val) , Double.parseDouble(((NumberAST)(((BinaryAST)ast).RHS)).Val));	
				}
				
				NumberAST numberAST = new NumberAST(Double.toString(total));
				
				
				return numberAST;
			}
					
			//System.out.println(((BinaryAST)ast).Operator);
		}
		else if(ast.getClass() == UnaryAST.class)
		{
			AST lhs = null;
			if(((UnaryAST)ast).LHS != null)
			{
				lhs = Optimize(((UnaryAST)ast).LHS);
				((UnaryAST)ast).LHS = lhs;
			}
			
			//System.out.println(((UnaryAST)ast).Operator);
		}
		else if(ast.getClass() == FunctionCallAST.class)
		{			
			//System.out.println(((FunctionCallAST)ast).FunctionName);
			AST arg = Optimize(((FunctionCallAST)ast).Argument);
			((FunctionCallAST)ast).Argument = arg;
		}
		else if(ast.getClass() == IdentifierAST.class)
		{			
			//System.out.println(((IdentifierAST)ast).Name);
			return ast;
		}
		else if(ast.getClass() == NumberAST.class)
		{			
			//System.out.println(((NumberAST)ast).Val);
			return ast;
		}
		
		return ast;
		
	}
	
	//not being used in current scope, can be used when extending the compiler program.
	private static boolean checkHighPrecedence(enumTokenID operator1, enumTokenID operator2) throws Exception
	{
		if(precedenceOrder.get(operator1) == null || precedenceOrder.get(operator2) == null)
		{
			throw new Exception("operator not defined in precedence table");
		}
		
		if(precedenceOrder.get(operator1) > precedenceOrder.get(operator2))
		{
			return true;
		}
		
		return false;
	}
	
	
	public static boolean Parse( )
	{
		parsingSuccess = true;
		
		if(Lexer.getToken().getType() == enumTokenID.END_OF_INPUT)
		{
			return false;
		}
		
		ast = line();
		
		if(ast != null && (parsingSuccess == true))
		{
			return true;
		}
		
		return false;
	}
	
	public static AST line()
	{
		//if token is EXIT		
		//else
			//statement()
		AST ast;
		ParserPrint("LINE ***START");
		if(Lexer.getToken().getType() == enumTokenID.EXIT)
		{
				ast = new FunctionCallAST(Lexer.getToken().getText(), null);
				
				if(Lexer.getNextToken().getType() != enumTokenID.END_OF_INPUT)
				{
					Lexer.getLastToken();
					ParserErrorHandler.Log("Compilation error: " + "EXIT should not be followed by any token");
					parsingSuccess = false;
					return null;
				}
				else
				{
					VirtualMachineInterface.showStatus();
					System.exit(0);
				}
		}
		else
		{
			ast = statement();
		}
		
		ParserPrint("LINE ***END");
		return ast;
		
	}
	
	public static AST statement()
	{
		//if token = PRINT
			//printstmt();
		//else
			//assignment
		AST ast;
		
		ParserPrint("STATEMENT ***START");
		if(Lexer.getToken().getType() == enumTokenID.PRINT)
		{
			ast = printstmt();
		}
		else
		{
			ast = assignment();
		}
		ParserPrint("STATEMENT ***END");
		return ast;
	}
	
	public static AST printstmt()
	{
		//token is PRINT
		//expression()
		ParserPrint("PRINTSTMT ***START");
		AST arg = null;
		FunctionCallAST function = null;
		
		if(Lexer.getToken().getType() == enumTokenID.PRINT)
		{
			function = new FunctionCallAST(Lexer.getToken().getText(), null);
			
			arg = expression();
			
			function.Argument = arg;
		}
		
		ParserPrint("PRINTSTMT ***END");
		return function;
		
	}
	
	public static AST assignment()
	{
		//token is ID 
		//token is '=' 
		//expression()
		
		ParserPrint("ASSIGNMENT ***START");
		IdentifierAST id_ast = null;
		if(Lexer.getToken().getType() == enumTokenID.ID)
		{
			Token identifier = Lexer.getToken();
			if(Lexer.getNextToken().getType() != enumTokenID.ASSIGN)
			{
				ParserErrorHandler.Log("Compilation error: " + "Identifier can only be assigned if used in the left, but this operator was found " + Lexer.getToken().getType());
				parsingSuccess = false;
				return null;
			}
					
			AST rhs  = expression();
			
			id_ast = new IdentifierAST(identifier.getText());
			
			BinaryAST binaryOperation = new BinaryAST(enumTokenID.ASSIGN, id_ast, rhs);
			
			ParserPrint("ASSIGNMENT ***END");	
				
			if(parsingSuccess==true && !symbolTable.containsKey(identifier.getText()))
				symbolTable.put(identifier.getText(), identifierIndex++);
			
			return binaryOperation;
		}
		else if(Lexer.getToken().getType() != enumTokenID.END_OF_INPUT)
		{
			ParserErrorHandler.Log("Compilation error: " + "l-value not a identifier but it was found" + Lexer.getToken().getType());
			parsingSuccess = false;
			return null;
		}
		
		return null;
	}
	
	public static AST expression()
	{
		//addterm()
		//expression_p_()
		
		ParserPrint("EXPRESSION ***START");
		AST at = addterm();
		AST exp = expression_p_();
		AST ast = at;
		if(exp != null && exp.getClass() == BinaryAST.class)
		{
			((BinaryAST)exp).LHS = at;	
			ast = exp;
		}
		
		ParserPrint("EXPRESSION ***END");
		
		return ast;
	}
	
	public static AST expression_p_()
	{
//        '+' addterm expression_p_
//    |   '-' addterm expression_p_
//    |
				
		ParserPrint("EXPRESS_P_ ***START");
		if(Lexer.getNextToken() == null)
		{
		
		}
		else if(Lexer.getToken().getType() == enumTokenID.PLUS || Lexer.getToken().getType() == enumTokenID.MINUS)
		{
			BinaryAST b_ast = new BinaryAST(Lexer.getToken().getType(), null, null);
			AST at = addterm();
			AST ast;
			b_ast.RHS = at;
			ast = b_ast;
			AST exp_p = expression_p_();
			
			if(exp_p != null && exp_p.getClass() == BinaryAST.class)
			{
				((BinaryAST)exp_p).LHS = at;
				b_ast.RHS = exp_p;
				ast = b_ast;
			}
			ParserPrint("EXPRESS_P_ ***BIN TREE");
			return ast;
		}
		else
		{
			Lexer.getLastToken();
		}
		
		ParserPrint("EXPRESS_P_ ***END");
		return null;

	}
	
	public static AST addterm()
	{
		//multerm()
		//addterm_p_()
		ParserPrint("ADDTERM ***START");
		AST mt = multerm();		
		AST at_p = addterm_p_();
		AST ast = mt;
		
		if(at_p != null && at_p.getClass() == BinaryAST.class)
		{
			((BinaryAST)at_p).LHS = mt;	
			ast = at_p;
		}
		ParserPrint("ADDTERM ***END");
		
		return ast;
	}
	
	public static AST  addterm_p_()
	{
		//if token is * or /
			//multerm()
			//addterm_p_()
		
		ParserPrint("ADDTERM_P_ ***START");
		if(Lexer.getNextToken() == null)
		{
		
		}
		else if(Lexer.getToken().getType() == enumTokenID.MULTIPLY || Lexer.getToken().getType() == enumTokenID.DIVIDE )
		{
			BinaryAST b_ast = new BinaryAST(Lexer.getToken().getType(), null, null);
			AST mt = multerm();
			AST ast;
			b_ast.RHS = mt;
			ast = b_ast;
			AST at_p = addterm_p_();
			
			if(at_p != null && at_p.getClass() == BinaryAST.class)
			{
				((BinaryAST)at_p).LHS = mt;	
				b_ast.RHS = at_p;
				ast = b_ast;
			}
			
			ParserPrint("ADDTERM_P_ ***BIN TREE");
			return ast;
		}
		else
		{
			Lexer.getLastToken();
		}
		
		ParserPrint("ADDTERM_P_ ***END");
		return null;
	}
	
	public static AST  multerm()
	{
		//unaryterm()
		//multerm_p_()
		
		ParserPrint("MULTERM ***START");
		AST ut = unaryterm();
		AST mt_p = multerm_p_();
		AST ast = ut;
		
		if(mt_p != null && mt_p.getClass() == BinaryAST.class)
		{
			((BinaryAST)mt_p).LHS = ut;	
			ast = mt_p;
		}
		ParserPrint("MULTERM ***END");
		
		return ast;
	}
	
	
	public static AST  multerm_p_()
	{
		//if token is '^'
			//unaryterm()
			//multerm_p_()
		ParserPrint("MULTERM_P_ ***START");
		if(Lexer.getNextToken() == null)
		{
		
		}
		else if(Lexer.getToken().getType() == enumTokenID.POWER)
		{
			BinaryAST b_ast = new BinaryAST(Lexer.getToken().getType(), null, null);
			AST ut = unaryterm();
			AST ast;
			b_ast.RHS = ut;
			ast = b_ast;
			AST mt_p = multerm_p_();
			
			if(mt_p != null && mt_p.getClass() == BinaryAST.class)
			{
				((BinaryAST)mt_p).LHS = ut;	
				b_ast.RHS = mt_p;
				ast = b_ast;
			}
			ParserPrint("MULTERM_P_ ***BIN TREE");
			return ast;
		}
		else
		{
			Lexer.getLastToken();
		}
		
		ParserPrint("MULTERM_P_ ***END");
		return null;
	}
	
	public static AST  unaryterm()
	{
		//if token is + or -
			//factor()
		//else
			//factor()
		ParserPrint("UNARYTERM ***START");
		AST ast = null;
		if(Lexer.getNextToken().getType() == enumTokenID.PLUS || Lexer.getToken().getType() == enumTokenID.MINUS)
		{
			UnaryAST ast_u = new UnaryAST(Lexer.getToken().getType(), null);
			AST lhs = factor();
			ast_u.LHS = lhs;
			ast = ast_u;
		}
		else
		{
			Lexer.getLastToken();
			ast = factor();
		}
		ParserPrint("UNARYTERM ***END");
		return ast;
	}
	

	public static AST  factor()
	{
        //if token is ID
		//else if token is NUMBER
		//else if token is (
			//expression()
			//token is )
		//else if token is function
			//functioncall
		ParserPrint("FACTOR ***START");
		AST ast = null;
		if(Lexer.getNextToken().getType() == enumTokenID.ID)
		{
			if(!symbolTable.containsKey(Lexer.getToken().getText()))
			{
				ParserErrorHandler.Log("Compilation error: " + "symbol not defined " + Lexer.getToken().getText());
				parsingSuccess = false;
				return null;
			}
			
			ast = new IdentifierAST(Lexer.getToken().getText());
		}
		else if(Lexer.getToken().getType() == enumTokenID.NUMBER)
		{
			ast = new NumberAST(Lexer.getToken().getText());
		}
		else if(Lexer.getToken().getType() == enumTokenID.LPAREN)
		{
			ast = expression();
			
			if(Lexer.getNextToken().getType() != enumTokenID.RPAREN)
			{
				ParserErrorHandler.Log("Compilation error: " + "right brace ')' must be used after the expression, but it was found this: " + Lexer.getToken().getText());
				parsingSuccess = false;
			}
		}
		else if(Lexer.getToken().getType() == enumTokenID.FUNCTION)
		{
			Lexer.getLastToken();
			ast = functioncall();
		}
		else if(Lexer.getToken().getType() != enumTokenID.END_OF_INPUT)
		{
			ParserErrorHandler.Log("Compilation error: " + "token doesn't define either function or expression or number or identifier: " + Lexer.getToken().getText());
			parsingSuccess = false;
		}
		
		ParserPrint("FACTOR ***END");
		return ast;
	}

	public static AST  functioncall()
	{
            //token is function 
			//token is (
			//expression()
			//token is )
		ParserPrint("FUNCTIONCALL ***START");
		FunctionCallAST function = null;
		if(Lexer.getNextToken().getType() == enumTokenID.FUNCTION)
		{
			function = new FunctionCallAST(Lexer.getToken().getText(), null);
			Lexer.getLastToken();
			function();
		}
		
		if(Lexer.getNextToken().getType() != enumTokenID.LPAREN)
		{
			ParserErrorHandler.Log("Compilation error: " + "left brace '(' must be used before the expression used in the function, but it was found this: " + Lexer.getToken().getText());
			parsingSuccess = false;
		}
		
		AST arg = expression();
		
		if(Lexer.getNextToken().getType() != enumTokenID.RPAREN)
		{
			ParserErrorHandler.Log("Compilation error: " + "right brace ')' must be used after the expression used in the function, but it was found this: " + Lexer.getToken().getText());
			parsingSuccess = false;
		}
		
		ParserPrint("FUNCTIONCALL ***END");
		
		function.Argument = arg;
		return function;
		
	}
            
	public static void function()
	{
        //token is SIN | COS |   TAN  |   EXP  |   LOG  |   LOG10
		ParserPrint("FUNCTION ***START");
		if(Lexer.getNextToken().getText().equalsIgnoreCase("SIN") | Lexer.getToken().getText().equalsIgnoreCase("COS") | 
				Lexer.getToken().getText().equalsIgnoreCase("TAN") | Lexer.getToken().getText().equalsIgnoreCase("EXP") | 
				Lexer.getToken().getText().equalsIgnoreCase("LOG") |  Lexer.getToken().getText().equalsIgnoreCase("LOG10"))
		{
			
		}
		else if(Lexer.getToken().getType() != enumTokenID.END_OF_INPUT)
		{
			ParserErrorHandler.Log("Compilation error: " + "function not defined with the name " + Lexer.getToken().getText());
			parsingSuccess = false;
		}
		
		ParserPrint("FUNCTION ***END");

	}

	
	
}
