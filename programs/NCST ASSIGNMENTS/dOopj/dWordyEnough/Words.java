import ncst.pgdst.*;

class Words{
	static int now=0, noc=0, orgw=0, orgc=0;
	static String[] words;
	static char[] str;
	static boolean[] wf, cf;
	static int arr[], r=0;
	static String temp;
	
	

	public static void main(String[] args) throws IOException {
		SimpleInput sin=new SimpleInput();	
		System.out.println("enter the no of words...");
		now=orgw=sin.readInt();
		words=new String[orgw];
		arr=new int[1000];

		wf=new boolean[orgw];
		for(int i=0; i<orgw; i++){
			words[i]=sin.readWord();
			wf[i]=false;
		}
		System.out.println("enter the no of chars...");
		noc=orgc=sin.readInt();
		temp=sin.readWord();
		str=temp.toCharArray();	
		System.out.println(str);
		cf=new boolean[noc];
		makeallfalse();
		maxwords(0);
		

		
	
		




	}
	static void makeallfalse(){
		for(int i=0; i<noc; i++)
			cf[i]=false;
	}

	static boolean match(String s){
		int nw=0;
		for(int i=0; i<(nw=s.length()); i++){
			for(int j=0; i<temp.length(); j++){
				if((cf[j]==false)&& str[j]==s.charAt(i)){
					cf[i]=true;
					nw--;	

				}

			}
		}
		makeallfalse();
		if(nw==0)
			return true;
		return false;
		
	}


	int maxwords(int j){
		if((noc==0) || (now==0))
			return 0;

		for(int i=0; i<orgw; i++){
			if(wf[i]==false){
				if(match(words[i])){
					wflagtrue(i);
					cflagtrue(words[i]);	
					increment=i+maxwords(j+1);
					if(j==0)
						arr[r++]=increment;

					wflagfalse(i);
					cflagfalse(words[i]);
				}	
			}



	}



		}

}

 
