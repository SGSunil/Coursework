import ncst.pgdst.*;

class BubbleSort {

	static int unSortedR[];
	static int index;
	
	static void bubbleSort(int unSorted[]) {
		int temp=0;
		int ind=0,ind1=0;
		for(ind=0;ind<index-1;ind++) {
			for(ind1=0;ind1<index-ind-1;ind1++) {
				if(unSorted[ind1]>unSorted[ind1+1]){
					temp=unSorted[ind1];
					unSorted[ind1]=unSorted[ind1+1];
					unSorted[ind1+1]=temp;
				}
			}
		}
	}

	public static void main(String args[])throws IOException {
		SimpleInput sin=new SimpleInput();
		index=sin.readInt();
		unSortedR=new int[index];
		for(int ind=0;ind<index;ind++) {
			unSortedR[ind]=sin.readInt();
		}
		
		bubbleSort(unSortedR);
		
		for(int ind=0;ind<index;ind++) {
			System.out.println(unSortedR[ind]+" ");
		}
	}
}

