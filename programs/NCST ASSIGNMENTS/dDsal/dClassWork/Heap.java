import ncst.pgdst.*;

public class Heap{
	static int tBMHeap[];
	static int size;

	public static void heapification(int index) { //tBMHeap: to be made as heapi
		System.out.println("Called Heapification for index value..."+index);

		if((index)>0) {
			if(tBMHeap[index]>tBMHeap[(index-1)/2]){
				System.out.println("inside if child>parent..."+index);
				swap(index,(index-1)/2); //swap the parent and newly added.
				heapification((index-1)/2);
			}
		}
		else{
			System.out.println("Returned from Heapification for index value..."+index);
			return; //return when index will become root or -Ve.
		}
	}

	public static void swap(int child,int parent) {
		int temp=0;
		temp=tBMHeap[child];
		tBMHeap[child]=tBMHeap[parent];
		tBMHeap[parent]=temp;
	}

	public static void printAll(int index){
		for(int i=0;i<index;i++)
			System.out.print(tBMHeap[i]+"...");
	}
			
	
				
			
	// start of the main function
	public static void main(String args[]) throws IOException {
		SimpleInput sin=new SimpleInput();
		System.out.println("Enter the size of array to be made as a heap...");
		size=sin.readInt();
		System.out.println("Now enter the numbers one by one ...");
		System.out.println("1st number");
		tBMHeap=new int[size];
		tBMHeap[0]=sin.readInt();
		printAll(0);

		for(int i=1;i<size;i++) {
		System.out.println((i+1)+" number");;
			tBMHeap[i]=sin.readInt();
			heapification(i);
			printAll(i);
			System.out.println();	
		}

		printAll(size); //prints all the heap contents.

	}//end of main. 
}
// end of class.



