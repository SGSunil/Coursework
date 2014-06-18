import ncst.pgdst.*;

public class SALM
{
	public static void main(String aa[]) throws IOException
		{

			SimpleInput sin=new SimpleInput();
			SimpleOutput sout=new SimpleOutput();

//variable declarations
	int value[]={0,0};//moves by A and B-val[0], val[1];


	int N=0;
	N=sin.readInt();
//	System.out.println("the no. of snakes and ladder..."+N);

	int sL[]=new int[2*N];
	//int ladder[]=new int[N+1];
	//ladder[N]=snake[N]=0;

	int x=0; //track the next snake and ladder position to input their values pairs.
	int a1 =0,b1=0; //input values for value pair.
	int s=0,l=0; //count the total no snakes pos and ladder pos.
	int bite=0,up=0;

	while(N>0)
		{
			 a1=sin.readInt();
 		 	b1=sin.readInt();

			 sL[x]=a1;
			 sL[x+1]=b1;
			 x+=2;
			N--;
 		}



	int D=0; //total no. moves
	D=sin.readInt();
	int p=0,q=0; //
	int a[]=new int[D+1];
	int b[]=new int[D+1];


	while(D>0)
		{
			 a[p]=sin.readInt(); p+=1;
			 b[q]+=sin.readInt(); q+=1;
			 D=D-2;

		}

//	for(int i=0;i<p;i++)
//		System.out.println("___"+a[i]+"___");

//	System.out.println("the elements in arrary A is P..."+p+" elements in B is q..."+q);


// check for A'a value., where it will be at last.
	for(int i=0;i<p;i++)
		{	
				value[0]+=a[i];
//				System.out.println("*"+a[i]+"#");
//
				for(int j=0;j<x-1;j+=2)
					{
//						System.out.println("j value at the begining "+j+" value[0] value "+value[0]);
						if(value[0]==sL[j])
							{
								value[0]=sL[j+1];
								j=-2;
								continue;
							}
					}

		}

		
// check for B's value., where it will be at last.
	for(int i=0;i<q;i++)
		{

			 value[1]+=b[i];

				for(int j=0;j<x-1;j+=2)
					{
//						System.out.println("j value at the begining "+j+" value[1] value##### "+value[1]);
						if(value[1]==sL[j])
							{
								value[1]=sL[j+1];
								j=-2;
								continue;
							}
					}

		}
			 
 				


		   


	if(value[0]>99)
		value[0]=99;

	if(value[1]>99)
		value[1]=99;


//	System.out.println("at last"+" Value for A..."+value[0]+"value for B..."+value[1]);
				   

	if(value[0]>value[1])
		{
			 System.out.println("A "+value[0]);
		}
	else
		{
			 System.out.println("B "+value[1]);
		}

	}//end of main
	}//end of class


