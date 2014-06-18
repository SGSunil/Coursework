/*
* Name:Sunil Kumar Mandhan
* FPGDST ID:d0557101
* YEAR: 2005
* Assignment no. 10
*
*
*/



import ncst.pgdst.*;

public class HVL {
	public static void main(String[] s) throws IOException {
		
		SimpleInput sin=new SimpleInput();
		SimpleOutput sout=new SimpleOutput();
		
		int N=0, M=0;
		int intersection=0;
		
		M=sin.readInt();
		N=sin.readInt();
		
		int V[][]=new int[M][3];
		int H[][]=new int[N][3];

		// enter input values into V and M

		for(int i=0;i<M;i++)
			for(int j=0;j<3;j++)
			{
				V[i][j]=sin.readInt();
			System.out.println("***"+i+"***"+j);
			}
		
		for(int i=0;i<N;i++)
			for(int j=0;j<3;j++)
			{
				H[i][j]=sin.readInt();
			System.out.println("..."+i+"..."+j);
			}
		//traverse by horizontal lines one by one

		for(int i=0;i<N;i++)
			for(int j=0;j<M;j++)
				{
					if(((V[j][1]<=H[i][0])&(H[i][0]<=V[j][2]))&((H[i][1]<=V[j][0])&(V[j][0]<=H[i][2])))
					{
						intersection+=1;
					}
				}

		System.out.println("the intersections are ..."+intersection);
		}
}

