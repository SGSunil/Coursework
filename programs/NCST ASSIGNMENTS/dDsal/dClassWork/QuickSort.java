import ncst.pgdst.*;

public class QuickSort {
	static int unSorted[];
	static int size;

	static void quickSort(int low,int high) {
		int partitionLocation=0;

		if(low<high)
			partitionLocation=partition(low,high);
		else 
			return;

		quickSort(low,partitionLocation-1);
		quickSort(partitionLocation+1,high);
	}
	
	static int partition(int low,int high) {
		int pivot=0;
		int pivotKey=0;

		pivot=(low+high)/2;
		pivotKey=unSorted[pivot];
		swap(low,pivot);
		int up=high,down=low;
		while(down<up) {
			while((unSorted[down]<=pivotKey)&&(down<up)) 
				down++;
			while((unSorted[up]>pivotKey))
				up--;
			if(down<up)
				swap(down,up);
		}
		
		swap(low,up);
		return up;
	}



	static void swap(int low,int pivot) {
		int temp=0;
		temp=unSorted[low];
		unSorted[low]=unSorted[pivot];
		unSorted[pivot]=temp;
	}

	public static void main(String args[]) throws IOException {
		SimpleInput sin=new SimpleInput();
		size=sin.readInt();
		unSorted=new int[size];
		for(int ind=0;ind<size;ind++) {
			unSorted[ind]=sin.readInt();
		}
		quickSort(0,size-1);
		for(int ind=0;ind<size;ind++) {
			System.out.println(unSorted[ind]+" ");
		}

	}
}
