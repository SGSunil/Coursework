import ncst.pgdst.*;

public class ShellSort {

	static int unSorted[];
	static int size;

	static void shellSort(int unSorted[]) {
		int h=1,ind1=0,ind=0,temp=0;
		while(h<unSorted.length) {
			h=3*h+1;
		}
		
		System.out.println("value of h: "+h);

		while(h>0) {
			for(ind=0;ind<size;ind+=h) {
				temp=unSorted[ind];
				ind1=ind;
				while(ind1>=h&&(unSorted[ind1-h]>temp)) {
					unSorted[ind1]=unSorted[ind1-h];
					ind1-=h;
				}
				unSorted[ind1]=temp;
			}
			h/=3;
		}
	}
	
	public static void main(String args[])throws IOException {
		SimpleInput sin=new SimpleInput();
		size=sin.readInt();
		unSorted=new int[size];
		for(int ind=0;ind<size;ind++) {
			unSorted[ind]=sin.readInt();
		}
		
		shellSort(unSorted);
		
		for(int ind=0;ind<size;ind++) {
			System.out.println(unSorted[ind]);
		}
	}
}

