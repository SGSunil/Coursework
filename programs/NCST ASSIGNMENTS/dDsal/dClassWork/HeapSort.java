import ncst.pgdst.*;

public class HeapSort {

	static int unSorted[];
	static int Aux[];
	static int size;
	static int k;

	static void makeHeap(int size) {
					//System.out.println("Make Heap Call"); 
		int ind=0;
		int i=0; 
		for(ind=size-1;ind>i; ) {
					System.out.println("ind value..."+ind); 
			if((ind%2)!=0) {
				if(unSorted[(ind-1)/2]>unSorted[ind]){
				}
				else {
					swap(ind,(ind-1)/2);
				}
			ind-=1;
			}
			else {
				max((ind-1)/2,ind-1,ind);
				ind-=2;
			}
			ind=ind-1/2;
		}
	}


	static void makeHeapTD() {
		//System.out.println("MakeHeapTD "+size); 
   		int ind=0,i=0;
		
		for(i=size-1;i>=0;i--){
			System.out.println("size "+i);
			swap(0,i);
			heapComplete(i-1);
			print();
		}
		swap(0,1);
	}
	
	static void print()
	{
		for(int i=0;i<size;i++)
		System.out.print(unSorted[i]+" ");
		System.out.println();
	}

		

		
	static void swap(int a,int b) {
					//System.out.println("swap"); 
		int temp=0;
		temp=unSorted[a];
		unSorted[a]=unSorted[b];
		unSorted[b]=temp;
	}
	
	static void max(int a,int b,int c) {
					//System.out.println("max"); 
		int k=0;

		if(unSorted[c]>unSorted[b])
			k=c;
		else
			k=b;

		if(unSorted[a]>unSorted[k]) { 
		}
		else {
			swap(k,a);
		}
		
	}

	static void heapComplete(int size ) {
		int a=0;
		for(int i=0;i<size;i++){
		makeHeap(size);
		if(size%2==0)
		size-=1;
		else
		size-=2;
		}

	}
		
	static void heapSort( ) {
		int k=0,size1=size;
		for(int i=0;i<size1;i++) {
			if(i==k)
			Aux[k]=unSorted[i];k++;
			swap(i,size-1);
			heapComplete(size);
			size1-=1;
		}	

	}	

			
	public static void main(String args[]) throws IOException {

		SimpleInput sin=new SimpleInput();
		size=sin.readInt();

		unSorted=new int[size];
		Aux=new int[size];
			
		for(int ind=0;ind<size;ind++) {
			unSorted[ind]=sin.readInt();
		}
		
		heapComplete(size);
	//	makeHeapTD();
		print();
	}
}
			
					
					
