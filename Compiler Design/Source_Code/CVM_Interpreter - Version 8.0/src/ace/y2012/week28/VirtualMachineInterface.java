package ace.y2012.week28;

import cvm.*;

public class VirtualMachineInterface 
{
	private static Cvm cvm = new Cvm();
	
	public static void executeLine(String sourceCodeLine)
	{
		cvm.execute(sourceCodeLine);
	}
	
	public static void showStatus()
	{
		cvm.showStats();
	}

}
