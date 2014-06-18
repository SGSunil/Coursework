import ncst.pgdst.*;

public class MergeSort {
	static int unSorted[];
	static int Aux[];
	static int size;

	static void mergeSort(int low,int high) {
		int mid=(low+high)/2;
		if(low<high) {	
			mergeSort(low,mid);
			mergeSort(mid+1,high);
			merging(low,mid,high);
		}
		else
			return;
	}

	static void merging(int low,int mid,int high) {
    		int i=low, j=high, k=low;

    		while (i<=mid)
        		Aux[k++]=unSorted[i++];

    		while (j>mid)
        		Aux[k++]=unSorted[j--];

 		 i=low; j=high; k=low;

    		while (i<=j)
        		if (Aux[i]<=Aux[j])
          			  unSorted[k++]=Aux[i++];
       			 else
            			unSorted[k++]=Aux[j--];
	}
	
	public static void main(String args[]) throws IOException {
		SimpleInput sin=new SimpleInput();
		size=sin.readInt();
		unSorted=new int[size];
		Aux=new int[size];
		for(int ind=0;ind<size;ind++)
			unSorted[ind]=sin.readInt();
		mergeSort(0,size-1);
		for(int ind=0;ind<size;ind++)
			System.out.println(unSorted[ind]+" ");
	}
}
			
