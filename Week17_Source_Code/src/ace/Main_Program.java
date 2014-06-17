
package ace;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.*;
import java.lang.*;
import java.lang.Thread;

public class Main_Program {
	
	public static void main(String[] argv) throws InterruptedException, IOException
	{
		//Read and store Command line arguments
		long N = Integer.parseInt(argv[0]);
		int threads_count = Integer.parseInt(argv[1]);
		int a = Integer.parseInt(argv[2]);
		int b = Integer.parseInt(argv[3]);
		int function_choice = Integer.parseInt(argv[4]);
		
		//Setup file writing for the thread timing and function value results.
		String file_name = "combined_result" + "_" + "Numbers-" + N + "_" + "Threads-" + threads_count + "_" + "Function-" + function_choice + ".csv";
		FileWriter fstream = new FileWriter(file_name);
		BufferedWriter out = new BufferedWriter(fstream);
		String header = "N - total numbers" + "," + "threads_count" + "," + "Execution_time (nano seconds)" + "," + "Function_Value" + "\n";
		out.write(header);
		 
		//Loop for generating threads from 1 threads to the threads_count that user provided.
		for(int n = 1; n <= threads_count; ++n)
		{
			//prepare the array for each threads.
			Intermediate_Result[] intermediate_results = new Intermediate_Result[n];
			for(int index = 0; index < n; ++index)
			{
				intermediate_results[index] = new Intermediate_Result();
			}
			
			//Range for each thread. N numbers are equally divided among threads.
			//if N is not exact multiple of n then remaining numbers are distributed
			//one by one to each thread from starting until numbers are consumed.
			long range = N/n;
			
			//threads creation
			Thread[] threads = new Thread[n]; 
			for(int index = 0; index < n; ++index)
			{
				long current_thread_range = range;
				//Adjust remaining numbers in case N is not exact multiple of n.
				if(index < N%n)
				{
					++current_thread_range;
				}

				threads[index] = new Thread(new MonteCarlo_Integration(function_choice, intermediate_results[index], current_thread_range, a, b));
			}
		
			//Store the staring time, used in the calculation of total time execution for all the threads.
			long startTime = System.nanoTime();
			
			//starting threads
			for(int index = 0; index < n; ++index)
			{
				threads[index].start();
			}
			
			//After the threads are started, main thread waits for all thread to finish.
			//this loop is blocking loop.
			for(int index = 0; index < n; ++index)
			{
				threads[index].join();
			}
			
			//Total time taken by all the threads.
			long time_difference = (System.nanoTime() - startTime);
			
			//Club the results from each thread.
			double average = 0;
			for(int index = 0; index < n; ++index)
			{
				//System.out.println("*** range" + intermediate_results[index].intermediate_result + "***");
				average += intermediate_results[index].intermediate_result;
			}
			
			//Compute the final integral by the formula: (b - a)fcap, where fcap = average/N;
			double result = ((b - a) * average)/N;
			
			//Write to the file data captured for all the threads.
			String combined_result = N + "," + n + "," + time_difference + "," + result + "\n";
			out.write(combined_result);
			
			System.out.println(combined_result);
		}
		
		//Just printing the number of cores/processors available for the informational purpose.
		int cores = Runtime.getRuntime().availableProcessors();
		System.out.println("cores..." + cores);
		
		//close file.
		out.close();
	}

}
