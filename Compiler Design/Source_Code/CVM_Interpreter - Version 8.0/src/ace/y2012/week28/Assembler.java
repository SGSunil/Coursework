package ace.y2012.week28;

import java.util.Map;

public class Assembler 
{
	private static Map<String, Integer> symbolTable= null;
	
	public static String convertToMachineInstruction(AST ast, Map<String, Integer> symbol_Table)
	{
		symbolTable = symbol_Table;
		String result = convert(ast);
		
		//System.out.println("ASSEMBLER OUTPUT START //////////////////////");
		
		System.out.println(result);
		
		//System.out.println("ASSEMBLER OUTPUT END //////////////////////");
		
		return result;
		
	}
	
	private static String convert(AST ast)
	{
		if(ast == null)
			return null;
		
		if(ast.getClass() == BinaryAST.class)
		{
			String lhs = "";
			if(((BinaryAST)ast).Operator != enumTokenID.ASSIGN)
			{
				if(((BinaryAST)ast).LHS != null)
				{
					lhs = convert(((BinaryAST)ast).LHS);
				}
			}
			
			String rhs = "";
			if(((BinaryAST)ast).RHS != null)
			{
				rhs = convert(((BinaryAST)ast).RHS);
			}
			
			System.out.println(((BinaryAST)ast).Operator);
			
			if(((BinaryAST)ast).Operator == enumTokenID.ASSIGN)
			{
				return rhs + "STORE" + " " + Integer.toString(symbolTable.get(((IdentifierAST)(((BinaryAST)ast).LHS)).Name)) + ";";
			}
			else
			{
				return  lhs + rhs + Token.getOperator(((BinaryAST)ast).Operator) + ";";
			}
								
		
			//evaluateOperator(((BinaryAST)ast).Operator)
		}
		else if(ast.getClass() == UnaryAST.class)
		{
				
			if(((UnaryAST)ast).LHS != null)
			{
				String instructions = convert(((UnaryAST)ast).LHS);
				
				if(((UnaryAST)ast).Operator == enumTokenID.MINUS)
				{
					instructions = instructions + "CONST -1;" + " " + Token.getOperator(enumTokenID.MULTIPLY) + ";";
				}
				else if(((UnaryAST)ast).Operator == enumTokenID.PLUS)
				{
					return instructions;
				}
				
				return instructions;
			}
			
			System.out.println(((UnaryAST)ast).Operator);
		}
		else if(ast.getClass() == FunctionCallAST.class)
		{			
			//System.out.println(((FunctionCallAST)ast).FunctionName);
			
			String instructions = convert(((FunctionCallAST)ast).Argument);
			instructions += ((FunctionCallAST)ast).FunctionName + ";";
			
			return instructions;
		}
		else if(ast.getClass() == IdentifierAST.class)
		{			
			//System.out.println(((IdentifierAST)ast).Name);
			return "LOAD" + " " + Integer.toString(symbolTable.get(((IdentifierAST)ast).Name)) + ";";
		}
		else if(ast.getClass() == NumberAST.class)
		{			
			//System.out.println(((NumberAST)ast).Val);
			return "CONST" + " " + ((NumberAST)ast).Val + ";";
		}
		
		
		return null;
			
		
	}
	
}

