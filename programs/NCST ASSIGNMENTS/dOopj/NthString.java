import ncst.pgdst.*;


// main class definition***********
class NthString{

	//data part***********
	static char[] alphabet;
	static char[] alphabetOrig;
	static int alphabetP[];
	static boolean[] matched;
	static boolean[] matchedOrig;
	static int count,index=0;
	static int originalCount;
	static char[] res;

	//behaviour part**********
	static int factorial(int number){
		if(number==1)
			return 1;
		return ( number*factorial(number-1) );	
	}

	static void initialization( ){
		alphabet=new char[originalCount];
		alphabetOrig=new char[originalCount];
		alphabetP=new int[originalCount];
		matchedOrig=new boolean[originalCount];
		matched=new boolean[originalCount];
		res=new char[originalCount];

		for(int i=0; i<originalCount; i++){
			matched[i]=false;
			matchedOrig[i]=false;
			alphabetOrig[i]=alphabet[i]=(char)('a'+i);
		}	
	}

	static void reAssign(int number ){
		//matched=new boolean[number];
		char[] alphabet1=new char[number];
		alphabetP=new int[number];
		arrCalc();
		int n=0;
			//System.out.println("inside reAssign "+number);
		//for(int i=0; i<count; i++)
		//	matched[i]=false;
		for(int i=0;i<originalCount;i++){
			if(matchedOrig[i]==false){
				//System.out.println("inside reAssign* "+n);
				alphabet1[n++]=alphabetOrig[i];

			}
		}
		alphabet=alphabet1;
	}

	static void arrCalc( ){
		int fac=factorial(count);
		int atomicPerm=fac/count;
		int x=0;
		for(int i=0;i<count;i++){
			alphabetP[i]=atomicPerm+x;
			x=alphabetP[i];
		}
	}

	static void permLogic(int number, int N){
		if((N==1)&&(count==1)){
			//System.out.println("Oh Yes");
			for(int i=0;i<originalCount;i++){
				if(matchedOrig[i]==false){
					res[index]=alphabetOrig[i];
					matchedOrig[i]=true;
					break;
				}
			}
		//	printFinal();
			return;
		}

		if(N<=alphabetP[0]){
			res[index++]=alphabet[0];
			for(int i=0;i<originalCount;i++){
				if(alphabet[0]==alphabetOrig[i]){
					matchedOrig[i]=true;
				}
			}
			//matchedOrig[0]=true;
			//System.out.println("now N is in if "+N);
			//print();
			count--;	
			reAssign(count);
		}
		else{
			for(int i=0;i<count;i++){
				if(i+1<count)
				if((N>alphabetP[i])&&(N<=alphabetP[i+1])){
					//System.out.println("Yes");
					res[index++]=alphabet[i+1];
			for(int j=0;j<originalCount;j++){
				if(alphabet[i+1]==alphabetOrig[j]){
					matchedOrig[j]=true;
				}
			}
					N=N-alphabetP[i];
					break;
				}
			}
			//System.out.println("now N is in else "+N);
			//print();
			count--;	
			reAssign(count);


		}
		permLogic(count,N);

	}

	static void printFinal(){
			//System.out.println("*******************");
		for(int i=0;i<originalCount;i++){
			System.out.print(res[i]);
			/*System.out.print(alphabet[i]+" ");
			System.out.print("***"+alphabetP[i]+"***");
			System.out.print("###"+matched[i]+"###");
			*/
		}
		System.out.println();

	}



	static void print(){
		for(int i=0;i<count;i++){
			System.out.print(alphabet[i]+" ");
			System.out.print("***"+alphabetP[i]+"***");
			//System.out.print("###"+matchedOrig[i]+"###");
			
		}
		System.out.println();

	}



	public static void main(String[] argv) throws IOException{
		SimpleInput sin=new SimpleInput();
		//System.out.println("enter how long the input is and index of the perm to be found out...");
		originalCount=count=sin.readInt();
		int N=sin.readInt();

		initialization();
		arrCalc();
		permLogic(count, N);
		printFinal();	


	}




}
