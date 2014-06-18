import ncst.pgdst.*;

public class HeapSort1 {

	static int unSorted[];
	static int heap[];
	static int unSorted1[];
	static int size, sizeH,sizeU;

	static void makeHeap(int size) {
					//System.out.println("Make Heap Call"); 
		int ind=0;
		for(ind=size-1;ind>0; ) {
		
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
//		swap(0,1);
	}
	
	static void print()
	{
		for(int i=0;i<size;i++)
		System.out.print(heap[i]+" ");
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
		for(int i=0;i<size;i++)
		makeHeap(size);
		//size-=2;
		}
		

			
	public static void main(String args[]) throws IOException {
		int ind=0,indexTFind;
		SimpleInput sin=new SimpleInput();
		sizeH=sin.readInt();
		heap=new int[sizeH];
		
		for(ind=0;ind<sizeH;ind++) {
			heap[ind]=sin.readInt();
		}

		//sizeU=sin.readInt();
		//unSorted1=new int[sizeU];
		//for(ind=0;ind<sizeU;ind++) {
		//	unSorted1[ind]=sin.readInt();
		//}
		//size=sizeU+sizeH;
		
		//unSorted=new int[size];
		//for(ind=0;ind<sizeH;ind++) {
		//	unSorted[ind]=heap[ind];
		//}
		

/*
		for(ind=sizeH;ind<size;ind++) {
			unSorted[ind]=unSorted1[ind-sizeH];
			heapComplete(ind);
		}
		for(ind=0;ind<size;ind++) {
			System.out.print(" "+unSorted[ind]);
		}
	*/	
		
		heapComplete(sizeH);
//		makeHeapTD();
//		indexTFind=sin.readInt();
//		System.out.print(unSorted[indexTFind]+" ");

	/*	for(ind=indexTFind;ind<size; ) {
			if((2*ind+1)<size){
				System.out.print(unSorted[2*ind+1]+" ");
			}
			else 
				System.exit(0);
			ind+=2;
		}


*/
	
		print();
	}
}
			
					
					
