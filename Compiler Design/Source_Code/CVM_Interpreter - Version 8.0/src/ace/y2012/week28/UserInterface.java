package ace.y2012.week28;


import java.util.Map;
import java.util.Set;

public class UserInterface 
{

	public static void main(String[] args) throws InterruptedException
	{
	
		Parser.ParserPrint("Parser...start");
				
		//Pass the tokens from LA to Parser
		while (true)
		{
			boolean lexerStatus = false;
			
			try 
			{
				lexerStatus = Lexer.ReadInput();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			boolean parserSuccess = false;
				
			if(lexerStatus)
			{
				parserSuccess = Parser.Parse();
			}
			
			if(parserSuccess)
			{
//				System.out.println("POST ORDER TRAVERSAL *********** START");
				Parser.Traverse(Parser.ast);
//				System.out.println("POST ORDER TRAVERSAL *********** END");
//				
//				System.out.println("OPTIMIZATION ################ START");
				Parser.Optimize(Parser.ast);
//				System.out.println("OPTIMIZATION ################ END");
//				
//				System.out.println("POST ORDER TRAVERSAL *********** START");
//				Parser.Traverse(Parser.ast);
//				System.out.println("POST ORDER TRAVERSAL *********** END");
				
//				System.out.println("SYM TBL *********** START");
//				Set<Map.Entry<String, Integer>> entries = Parser.symbolTable.entrySet();
//				 for(Map.Entry<String, Integer> entry : entries) {
//					 String key = entry.getKey();
//					 Integer value = entry.getValue();
//			            System.out.printf("%s = %s%n", key, value);
//			        }
//				System.out.println("SYM TBL *********** END");
				
				//Pass the AST and ST to the Assembler
				String instructions = Assembler.convertToMachineInstruction(Parser.ast, Parser.symbolTable);
				
				//System.out.println("EXECUTED @@@@@@@@@@@@@@@@@@@ START");
				VirtualMachineInterface.executeLine(instructions);
				//System.out.println("EXECUTED @@@@@@@@@@@@@@@@@@@ END");
			}
		}
		
		
	}
}
