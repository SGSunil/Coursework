import ncst.pgdst.*;

public class SLAD
{
	//variable declarations
	static int moveG=0;
	static int N=0;
	static int d=0, SLADS[], SLADD[];

	public static void main(String aa[]) throws IOException
		{

			SimpleInput sin=new SimpleInput();


//	System.out.println("the no. of snakes and ladder...");
	N=sin.readInt();


	SLADS=new int[N];
	SLADD=new int[N];

	int moveA =0,moveB=0; //input values for value pair.
	int indSL=0;

//	System.out.println("now enter pairs...");
	while(N>0)
		{
			SLADS[indSL]=sin.readInt();
 		 	SLADD[indSL++]=sin.readInt();
			N--;
 		}

	N=indSL;
//	System.out.println("n is "+N);

//	System.out.println("no of moves...");
	d=sin.readInt();
//	System.out.println("d is "+d);

	int a[]=new int[d/2];
	int b[]=new int[d/2];

	int indM=0;	
	d=d/2;

//	System.out.println("d is "+d);
//	System.out.println("now moves...");
	while(d>0)
		{
			 a[indM]=sin.readInt(); 
			 b[indM++]=sin.readInt(); 
			 d--;
		}

	d=indM;
//	for(int i=0;i<d;i++)
//		System.out.println("a["+i+"]="+a[i]+" --- "+"b["+i+"]="+b[i]);

//	System.out.println("d is "+d);

	// check for A'a value., where it will be at last.
	for(int i=0;i<d;i++)
		{	

				//if(moveG!=0)
				//	moveA=moveG;
				//moveG=0;
				moveA+=a[i];
//				System.out.println("*** 2 "+moveA);
				moveA=slad(moveA);	
//				System.out.println("*** 1 "+moveA);


				//if(moveG!=0)
				//	moveB=moveG;
				//moveG=0;
				moveB+=b[i];
//				System.out.println("### 2 "+moveB);
				moveB=slad(moveB);	
//				System.out.println("### 1 "+moveB);
		}


	if(moveA>moveB)
		{
			if(moveA>99)
				moveA=99;
			 System.out.println("A "+moveA);
		}
	else
		{
			if(moveB>99)
				moveB=99;
			 System.out.println("B "+moveB);
		}

	}//end of main

	public static int slad(int move){

		//System.out.println("called slad for "+move);
		for(int i=0; i<N; i++)
			if(move==SLADS[i]){
				move=SLADD[i];
				return slad(move);
			}
		
		//moveG=move;
		//		System.out.println("returning slad for "+moveG);
		return move;
	}

	};//end of class


