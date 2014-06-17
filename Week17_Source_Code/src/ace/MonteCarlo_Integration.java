package ace;

import java.util.Random;

//Actual class that a thread makes use of to calculate the function value.
public class MonteCarlo_Integration implements Runnable {
	
	//lower range for the integration, this is also used for generating random numbers.
	private int a;
	//upper range for the integration, this is also used for generating random numbers.
	private int b;
	//each thread calculates part of total range, this variable denotes that.
	private long range_division;
	//this is the variable where the thread will store its result.
	//each thread has its own object to store the result, so no critical section.
	private Intermediate_Result result;
	//based on command line argument, which function to use for integration result.
	private Function current_function;
	
	//Constructor, to setup the object.
	public MonteCarlo_Integration(int function_choice, Intermediate_Result result, long range_division_count, int a, int b)
	{
		this.a = a;
		this.b = b;
		this.range_division = range_division_count;
		this.result = result;
		
		//based on the user input, which function to use.
		switch(function_choice)
		{
		case 0:
			current_function = new Function_One();
			break;
			
		case 1:
			current_function = new Function_Two();
			break;
		}
	}
	
	//this function is actually called by a thread, when we say thread.start().
	public void run()
	{
		for(long index = 0; index < range_division; ++index)
		{
			Random rnd = new java.util.Random();
			double number = a + (rnd.nextDouble() * b); //random number
			result.intermediate_result += current_function.Calculate(number);
		}
	}
}

