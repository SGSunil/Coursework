import ncst.pgdst.*;

public class BoolOr {

	public static void main(String str[]) throws IOException {

	//simple input and output variables.

	SimpleInput sin=new SimpleInput();
	SimpleOutput sout=new SimpleOutput();
	
	// used to maintain Higher states and Lower states of both the signal.
	
	int signaL1[]=new int[100];
	int signaL2[]=new int[100];

	// used to get the no of pairs for both the signals.

	int noA=0, noB=0;

	int sCount1=0, sCount2=0;

	noA=sin.readInt(); //got the no of High states for signal 1.

	int a1=0;
	a1=noA;

	int min=0;
	
	while(a1>0)
		{
			signaL1[sCount1]=sin.readInt();
			signaL2[sCount2]=sin.readInt();
			sCount1+=1;
			sCount2+=1;
			a1--;
		}

	noB=sin.readInt();
	
	int a2=0;
	a2=noB;
	
	while(a2>0)
		{
			signaL1[sCount1]=sin.readInt();
			signaL2[sCount2]=sin.readInt();
			sCount1+=1;
			sCount2+=1;
			a2--;
		}
	
	
	for(int i=0;i<sCount1;i++)
		{
			min=i;

			//finding the maximum
			
			for(int j=i+1;j<sCount1;j++)
				{
					if(signaL1[j]<signaL1[min])
						{
							min=j;
						}
				}

			//exchange of values
			int t=0;
			t=signaL1[min];
			signaL1[min]=signaL1[i];
			signaL1[i]=t;

		}
			
//	for(int i=0;i<sCount1;i++)
//		{
//			System.out.println("***"+signaL1[i]+"***");
//		}


	for(int i=0;i<sCount2;i++)
		{
			min=i;

			//finding the maximum
			
			for(int j=i+1;j<sCount2;j++)
				{
					if(signaL2[j]<signaL2[min])
						{
							min=j;
						}
				}

			//exchange of values
			int t=0;
			t=signaL2[min];
			signaL2[min]=signaL2[i];
			signaL2[i]=t;
		

		}


/*	for(int i=0;i<sCount2;i++)
		{
			System.out.println("***"+signaL2[i]+"***");
		}
*/

		int pairs=(sCount1);
		
		for(int i=0;i<sCount1-1;i++)
			{
				if(signaL2[i]>signaL1[i+1])
					{
						pairs-=1;
						signaL2[i]=signaL1[i+1]=-1;

					}
			}

		System.out.print(pairs);
		
		for(int i=0;i<sCount1;i++)
			{
				if(signaL1[i]==-1)
				{ }
				else
				System.out.print(" "+signaL1[i]);
				
				if(signaL2[i]==-1)
				{ }
				else
				System.out.print(" "+signaL2[i]);
			}


		System.out.println();
		
	}
}

