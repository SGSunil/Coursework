package ace;

//This class implements the generic Function interface and 
//provides the implementation for the first function in the assignment. 
public class Function_Two implements Function
{
	//implemented method that does the calculation.
	public double Calculate(double x)
	{
		double under_root_x = Math.sqrt(x);
		double function_value = x + under_root_x;
		function_value = Math.sqrt(function_value);
		return function_value;
	}
	
	//just to know in case client want to print the method mathematical form.
	public String ToString()
	{
		return "(x + x^0.5)^0.5";
	}
}