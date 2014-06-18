import ncst.pgdst.*;

class InsertionSort {

	static int unSortedR[];
	static int index;
	
	static void insertionSort(int toBeSortedR[]){
		int temp=0; int ind=0,ind1=0;
		for(ind=1;ind<index;ind++) {
			temp=toBeSortedR[ind];
			for(ind1=ind;ind1>0;ind1--) {
				if(temp<toBeSortedR[ind1-1]){
					toBeSortedR[ind1]=toBeSortedR[ind1-1];
				}
				else {
					break;
				}
			}
			toBeSortedR[ind1]=temp;
		}
	}

	public static void main(String args[]) throws IOException {
		SimpleInput sin=new SimpleInput();
		int numInArray=0;
		index=sin.readInt();
		unSortedR=new int[index];
		for(int ind=0;ind<index;ind++)
			unSortedR[ind]=sin.readInt();
		insertionSort(unSortedR);
		for(int ind=0;ind<index;ind++)
			System.out.println(unSortedR[ind]+" ");	
	}
}
