import ncst.pgdst.*;

public class patternMatch{
	static char[] master;
	static char[] scan;
	static int len;
	static String mstr,sstr;
	static char[] scptr;
	static char[] mptr;
	static int mlen;
	static int slen;

	static boolean substr(){
		for(int i=0;i<mlen-1;i++)
			for(int j=i+1;j<=mlen;j++){
				if((mstr.substring(i,j)).equals(sstr))
					return true;
			}
		return false;
	}	

	public static void main(String[] argv) throws IOException{
		SimpleInput sin=new SimpleInput();

		mstr=new String(sin.readWord());
		sin.skipWhite();
		sstr=new String(sin.readWord());
		master=new char[mlen=mstr.length()];
		master=mstr.toCharArray();
		scan=new char[slen=sstr.length()];
		scan=sstr.toCharArray();
		boolean noSpec=true;
		for(int i=0;i<slen;i++){
			if((scan[i]=='.' || scan[i]=='*')){
				noSpec=false;
				break;
			}
		}
		int res[]=new int[50],r=0;	
		for(int k=0;k<50;k++){
			res[k]=0;
		}
		char prev=' ';
		int i=0, j=0,lenr=0,pos=0;
		scptr=master;
		boolean isSub=substr(),star=false, dotstar=false,nothing=true;

		if((isSub==true)&&(noSpec==true)){
			System.out.println(slen);
			System.exit(0);
		}
		else{
			if((isSub==false)&&(noSpec==true)){
				System.out.println(0);
				System.exit(0);
			}
			else{
				while(i<mlen){
					nothing=true;
					//if(j>=slen) break;
						star=false;
					System.out.println("while(i<mlen) master scan=  "+master[i]+"/"+scan[j]);
					if(scan[j]==master[i]){

						prev=scan[j]; i++; j++; lenr++;
						System.out.println("scan==master and  length is "+lenr);
						if((j>=slen)&&(dotstar==false)){
							res[r++]=lenr;
							System.out.println("inside j>=slen of master==scan "+lenr);
							j=0; lenr=0;
						}

						if((j>=slen)&&(dotstar==true)){
							res[r++]=lenr;
							System.out.println("inside j>=slen and dotstar=true "+lenr);
							j=pos; 
						}
							
					}
					else{
						if(scan[j]=='*'){
							System.out.println("flaged (*)star=true");
							dotstar=false;
							star=true;
							nothing=false;
						}

						if(scan[j]=='.'){
							pos=j;
							nothing=false;
							i++; j++; lenr++;
							System.out.println("inside (.)dot=true and and length is "+master[i]+"^^^"+lenr+"^^^"+i+"^^^"+j);
							dotstar=false; star=false;
							if(j>=slen){
								res[r++]=lenr;
								System.out.println("j>=slen of . "+lenr);
								j=0; lenr=0;
							}
							else{
								if(scan[j]=='*'){
									System.out.println("flaged (.*)dotstar=true");
									dotstar=true;
									j++;
								}
							}
						}

						if(star==true){
							if(sstr.length()==1){
								System.out.println(mstr.length());
								System.exit(1);
							}
								
							System.out.println("inside star==true");
							j++; 
							while((i<mlen)){
								//System.out.println("while(j<slen) prev= "+prev);
								if(prev==master[i]){
									System.out.println("prev==master "+master[i]);
									i++;lenr++;
									if(i>=mlen) 
										break;
								}
								else{
									System.out.println("else no match to prev");
									 break;
								}
							}
							if(j>=slen){
								//if(scan[sstr.length()-1]=='*'){
									//lenr++;
									res[r++]=lenr;
									System.out.println("j>=slen of (*)star=true "+lenr);
									j=0; lenr=0;
								//}
							}
							

						}
						else if(dotstar==true){
								System.out.println("inside dotstar=true ");
								while(j<slen){
									if(i>=mlen) break;
									if(scan[j]==master[i]){
										System.out.println("master=scan of (.*)dotstar=true and length is "+lenr);
										break;	
									}
									else{ 
										System.out.println("inside else  of (.*)dotstar=true master is at "+i+"... and length is and master[i]/scan[j] "+lenr+" "+master[i]+"/"+scan[j]);
										i++;	lenr++; 



									}
									/*if(i>=mlen){
										res[r++]=lenr;
										System.out.println("j>=slen of (.*)dotstar=true and length is "+lenr);
										j=0; lenr=0;
									}*/


								}

						}else if(nothing==true){
							if(j==0)
								i++;
							lenr=0; j=0;
							dotstar=false; star=false;	
							System.out.println("inside outer else");
						}

					}

				}

			}

		}
		int max=0;
		for(int k=0;k<50;k++){
			if(max<res[k])
				max=res[k];
		}
		System.out.println(max);
	

	}








}
