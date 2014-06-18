import ncst.pgdst.*;

public class QuickSort1 {
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
		int index=0;
		pivot=(low+high)/2;
		swap(low,pivot);
		pivotKey=unSorted[low];
		pivot=low;	
		//index=low+1;
		//int up=high,down=low;
		for(index=low+1;index<=high;index++) {
			if(unSorted[index]<unSorted[low]){
				pivot=pivot+1;
				swap(pivot,index);
			}
		}
		swap(low,pivot);
		print();
		return pivot;
		
	
	}
	
	static void print(){
		for(int i=0;i<size;i++)
			System.out.print(unSorted[i]+" ");
		System.out.println();
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
		//for(int ind=0;ind<size;ind++) {
		//	System.out.println(unSorted[ind]+" ");
	//	}

	}
}
