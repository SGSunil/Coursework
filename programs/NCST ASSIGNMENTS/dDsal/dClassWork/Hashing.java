import ncst.pgdst.*;

class Hashing {

	static int hashTable[];
	static int tableSize;
	
	static void hashFunc(int i) {
		int item=i;
		
		boolean isStored=false;
		int i1=0,a=0,j1=0;
		int j=i%tableSize;
		System.out.print(j+" ");
		//i1=i;
		if(hashTable[j]==-1){
			hashTable[j]=item;
			isStored=true;
			return;
			//System.out.println("stored in the begining  "+j+" ");
		}
		else {	
			while(i!=0) {
			//System.out.println("value of i in while begin "+i1+" ");
				i1=i%10;
				a=(i-i1)/10;
				j1=a%tableSize;
				
				if(i1%2==0) {
					j=j-j1;
		//			System.out.println("i%2 "+j+" ");

					if(j<0) {
						j=(tableSize+j);
					System.out.print(j+" ");


						if(hashTable[j]==-1) {
							hashTable[j]=item;
							isStored=true;
						return ;	
						}
					}
					else {
					System.out.print(j+" ");
						if(hashTable[j]==-1) {
							hashTable[j]=item;
							isStored=true;
						return ;	
						}
					}
				}
				else {
					j=j+j1;
//					System.out.println("i%3 "+j+" ");

					if(j>tableSize-1){
						j=(j)%tableSize;
					System.out.print(j+" ");

						if(hashTable[j]==-1){
							hashTable[j]=item;
							isStored=true;
						return ;	
						}
					}
					else {
					System.out.print(j+" ");
						if(hashTable[j]==-1) {

							hashTable[j]=item;
							isStored=true;
						return ;	
						}
					}
				}
				i=a;
		//		System.out.println();
					
			}
		}
				//System.out.println("*****"+i1);

			if(isStored==false){
				
				//System.out.println("inside isStored False for value "+item);
				if(i1%2==0){
						for(int ind=j-1;ind>=0;ind--){
					System.out.print(ind+" ");
						if(hashTable[ind]==-1){

							hashTable[ind]=item;
							isStored=true;
							return;
						}
					}

					for(int ind=1;ind<j-1;ind++){
					System.out.print(ind+" ");
						if(hashTable[ind]==-1){
							hashTable[ind]=item;
							isStored=true;
							return;
						}
					}
				}		
				else {
					for(int ind=j+1;ind<tableSize;ind++){
					System.out.print(+ind+" ");
						if(hashTable[ind]==-1){
							hashTable[ind]=item;
							isStored=true;
							return;
						}
					}

					for(int ind=0;ind<j;ind++){
					System.out.print(+ind+" ");
						if(hashTable[ind]==-1){
							hashTable[ind]=item;
							isStored=true;
							return;
						}
					}
				}
			}

	}

	public static void main(String 	args[]) throws IOException {
			SimpleInput sin=new SimpleInput();		
			tableSize=sin.readInt();
			int i=0;	
			hashTable=new int[tableSize];		
			for(int j=0;j<tableSize;j++) {
				hashTable[j]=-1;
			}

			while((i=sin.readInt())!=-1) {
				hashFunc(i);
				System.out.println();
			}



	}

}
				
			
							
		

		
