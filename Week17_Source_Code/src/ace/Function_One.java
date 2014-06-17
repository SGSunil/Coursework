package ace;

//This class implements the generic Function interface and 
//provides the implementation for the first function in the assignment. 
public class Function_One implements Function
{
	//implemented method that does the calculation.
	public double Calculate(double x)
	{
		double function_value = 4 / (1 + x*x);
		return function_value;
	}
	
	//just to know in case client want to print the method mathematical form.
	public String ToString()
	{
		return "4/(1+x^2)";
	}
}